package napoleon;

import java.awt.*;
import java.awt.event.*;

import java.io.FileReader;
import java.io.IOException;

import javax.swing.*;

public class NapoleonBoard extends JFrame implements ActionListener   {

    private JButton exi, newRound, deal, /*undo,*/ rules, newGame;
    private NapoleonSolitaire spel;
    private int roundCount = 1;
    private final int maxRounds = 2;

    NapoleonBoard() {
        setTitle("Napoleons grav");
        setSize(700, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Skapa användarknappar
        JPanel buttonPanel = new JPanel();
        exi = new JButton("Avsluta");
        newRound = new JButton("Ta skräphög");
        deal = new JButton("Fyll ut");
        rules = new JButton("Regler");
        // undo = new JButton("Ångra drag");
        newGame = new JButton("Nytt spel");
        buttonPanel.add(newGame);
        buttonPanel.add(newRound);
        buttonPanel.add(deal);
        buttonPanel.add(rules);
        // buttonPanel.add(undo);
        buttonPanel.add(exi);
        add(buttonPanel, BorderLayout.NORTH);
        
        spel = new NapoleonSolitaire();
        getContentPane().add(spel, BorderLayout.CENTER);
        
        setVisible(true);
        //Lägga till lyssnare på knapparna så klicken fångas
        exi.addActionListener(this);
        newRound.addActionListener(this);
        deal.addActionListener(this);
        rules.addActionListener(this);
        // undo.addActionListener(this);
        newGame.addActionListener(this);
    }
    //Vad som händer när knapparna klickas på
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exi){
            System.exit(0);
        }
        // Ny runda, ta skräphögen och lägg på dealplatsen om given är tom. Gränsen på antal försök sätts av maxRounds
        if(e.getSource() == newRound){
            if(spel.deck.deckEmpty())
                roundCount ++;
            if(roundCount <= 2 && spel.deck.deckEmpty())    {
                for(PileOfCards pile:spel.cardPiles)  {
                    if(pile instanceof WastePile)   {
                        for(PileOfCards pile2:spel.cardPiles)   {
                            if(pile2 instanceof Deck){
                                pile2.takeAllCards((pile.getDeck()));
                                pile.removeAllCards();
                                break;
                            }
                        }
                        break;
                    }
                }
                roundCount++;
                spel.repaint();
            }
            else {
                JFrame info = new JFrame("Felmeddelande");
                JLabel l = new JLabel("Maximalt antal omgångar är satt till " + maxRounds + " och skräphögen kan endast bli giv om given är tom.",
								JLabel.CENTER);
		        info.add(l);
		        l.setOpaque(true); 	// Ogenomskinlig bakgrund
                l.setBackground(Color.ORANGE);
                l.setFont(new Font("SansSerif", Font.BOLD|Font.ITALIC, 14));
                info.setSize(650,250);
                info.setVisible(true);
            }
        }
        if(e.getSource() == newGame)    {
            for(PileOfCards pile:spel.cardPiles){
                if(pile instanceof Deck)  {
                    continue;
                }
                spel.deck.takeAllCards(pile.getDeck());
                pile.removeAllCards();
            }
            spel.deck.shuffleDeck();
            spel.repaint();
            roundCount = 1;
        }
        if(e.getSource() == deal){
            for(PileOfCards pile: spel.cardPiles)   {
                if((pile instanceof SquarePile) && pile.deckEmpty())    {
                    if(! spel.wastePile.deckEmpty()) {
                        try{
                            pile.addCard(spel.wastePile.dealCard());
                        }
                        catch(CardOrderException ex) {
                            System.out.println("Something unexpected happened!1");
                        }
                    }
                    // Kanske säkrare att använda instanceof, så om namnen byts funkar koden fortfarande
                    else if(! spel.deck.deckEmpty())    {
                        try{
                            pile.addCard(spel.deck.dealCard());
                        }
                        catch(CardOrderException ex) {
                            System.out.println("Something unexpected happened!2");
                        }
                    }
                }
            }
            if((spel.wastePile.deckEmpty()) && (!spel.deck.deckEmpty()))  {
                try{
                    spel.wastePile.addCard(spel.deck.dealCard());
                }
                catch(CardOrderException ex) {
                    System.out.println("Something unexpected happened!3");
                }
            }
            spel.repaint();
        }
        if(e.getSource() == rules){
            JFrame rulesFrame = new JFrame("Regler");
            JTextArea rulesText = new JTextArea();
            rulesText.setEditable(false);
            try{
                FileReader fil = new FileReader("napoleon/regler.txt");
                rulesText.read(fil, null);
             }
             catch(IOException ex) {System.out.println("gick fel!");}
            JScrollPane scrollRules = new JScrollPane(rulesText);
            rulesFrame.add(scrollRules);
            rulesFrame.setSize(450, 400);
            rulesText.setBackground(Color.LIGHT_GRAY);
            rulesFrame.setVisible(true);

        }
        // if(e.getSource() == undo){}
    }
    
    public static void main(String[] args) {
        
        new NapoleonBoard(); 

    }
}
