package napoleon;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*; 


public class NapoleonSolitaire extends JPanel implements MouseListener, MouseMotionListener {
    
    Deck deck; // Skapar kortlek med alla korten.
    PileOfCards ray1, ray2, ray3, ray4, middlePile, wastePile, sparePile, square1, square2, square3, square4;
    PileOfCards[] cardPiles;
    private Card chosenCard = null;
    private PileOfCards chosenPile = null;
    private int dx, dy;

    public NapoleonSolitaire()  {   // Konstruktor för spelet
        ray1 = new CornerPile();
        square1 = new SquarePile();  
        ray2 = new CornerPile();
        sparePile = new SparePile();
        square2 = new SquarePile();
        middlePile = new MiddlePile();
        square3 = new SquarePile();
        wastePile = new WastePile();
        ray3 = new CornerPile();
        square4 = new SquarePile();
        ray4 = new CornerPile();
        deck = new Deck();
        // Ordningen är viktig! Byt inte plats om det inte ändrar koden både här och i Board! (Har fixat så ordningen är mindra viktig,
        // men namnen är essentiella fortfarande i vissa fall.)
        cardPiles = new PileOfCards[] {ray1, square1, ray2, sparePile, square2, middlePile, square3, wastePile, ray3, square4, ray4, deck};

        addMouseListener(this);
        addMouseMotionListener(this);

        setBackground(Color.darkGray);
        // setForeground(Color.GREEN);
        // setPreferredSize(new Dimension(900, 700));

        setLayout(new GridLayout(3, 4, 10, 10));
        for(PileOfCards pile:cardPiles) {
            pile.setSize(new Dimension(71, 96));
            add(pile);
        }
    }
    //Måla upp kortens bilder
    @Override
	public void paintComponent(Graphics g)	{
        super.paintComponent(g);
        for(PileOfCards pile:cardPiles) {
            pile.paintComponent(g);
        }
        if(chosenCard != null){
            chosenCard.paintCard(g);
        }
	}
    // Kolla om en hög är klickad på och aktiverar kortet och högen
    @Override
	public void mousePressed(MouseEvent e)	{
        chosenPile = null;
        for(PileOfCards pile:cardPiles){
            // Översätta koordinaterna relativt panelen
            Point panelPoint = SwingUtilities.convertPoint(this, e.getPoint(), pile);
            // System.out.println(pile.contains(panelPoint));
            if(pile.contains(panelPoint) && !pile.deckEmpty()) {
                // pile.setPileChosen(true);
                chosenPile = pile;
                chosenCard = pile.dealCard();
                dx = panelPoint.x - chosenCard.getX();
                dy = panelPoint.y - chosenCard.getY();
                break;
            }
        }
        repaint();
    }
    // Tar kortet från den valda högen och flyttas dit användaren tar det
    @Override
	public void mouseDragged(MouseEvent e)	{
        if (chosenCard != null) {
            chosenCard.move(e.getX() - dx, e.getY() - dy);
            repaint();
        }
    }
    // Släpper kortet på högen som kortet hålls över
	@Override
	public void mouseReleased(MouseEvent e)	{
        // Hitta vilken hög som blev klickad på
        PileOfCards foundPile = null;
        for(PileOfCards pile:cardPiles){
            Point panelPoint = SwingUtilities.convertPoint(this, e.getPoint(), pile);
            if(pile.contains(panelPoint)){
                foundPile = pile;
                break;
            }
        }
        if(foundPile == null && chosenCard != null)   {
            chosenCard.returnToPile(chosenPile);
        }
        else if(chosenCard != null)   {
            try {
                foundPile.addCard(chosenCard);
            }
            //Om kort inte kan läggas returneras det till ursprungshögen
            catch (CardOrderException ex)    {
                System.out.println(ex.getMessage());
                chosenCard.returnToPile(chosenPile);
            }
        }
        //Skickar upp en vinnarruta om spelet har klarats
        if(deck.deckEmpty() && wastePile.deckEmpty())   {
            int i = 0;
            for(PileOfCards pile:cardPiles){
                if((pile instanceof CornerPile)){
                    if(pile.getDeckCount() != 7)
                        break;
                    i++;
                    continue;
                }
                else if((pile instanceof MiddlePile))   {
                    if(pile.getDeckCount() != 24)
                        break;
                    i++;
                    continue;
                }
                if(i == 5){
                    JFrame vinst = new JFrame("Vinst");
                    JLabel l = new JLabel("DU VANN!!!", JLabel.CENTER);
                    vinst.add(l);
                    l.setOpaque(true); 	// Ogenomskinlig bakgrund
                    l.setBackground(Color.magenta);
                    l.setFont(new Font("SansSerif", Font.BOLD|Font.ITALIC, 30));
                    vinst.setSize(400,250);
                    vinst.setVisible(true);
                }
                 
            }
        }
        chosenCard = null;
        repaint();
    }

    @Override
	public void mouseMoved(MouseEvent e)	{}
	
	@Override
	public void mouseClicked(MouseEvent e)	{}
	
	@Override
	public void mouseEntered(MouseEvent e)	{}
	
	@Override
	public void mouseExited(MouseEvent e)	{}

}
