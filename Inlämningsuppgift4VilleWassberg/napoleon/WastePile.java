package napoleon;

import java.awt.Font;
import java.awt.Graphics;
import java.util.*;

public class WastePile extends PileOfCards {
    
    private List<Card> wastePile = new ArrayList<Card>();

    public WastePile()  {
        super();
        wastePile = new ArrayList<>(0);
        setDeck(wastePile);
    }

    public void addCard(Card card) throws CardOrderException  {
        if(card != null)    {
            wastePile.add(0, card);
            card.move(getX(), getY());
            setDeck(wastePile);
        }
        else
            throw new CardOrderException("Kan inte lägga till ett null-värde");
        /*Kanske lägga till så att bara om kortet kommer från deck så kan det läggas här */
        // card.setFaceUp(true);
    }
    // Ta bort alla kort och returnera dem. Tänkt att kallas på när spelaren inte klarar patiensen på första försöket.
    public List<Card> takeCards()  {
        List<Card> sendCards = new ArrayList<Card>();
        sendCards.addAll(wastePile);
        wastePile.removeAll(wastePile);
        return sendCards;
    }
    public void takeAllCards(List<Card> wastePile){}

    @Override
    public void paintComponent(Graphics g)  {
        super.paintComponent(g);
        if(getTopCard() == null)    {
		    g.setFont(new Font("Serif", Font.ITALIC, 15));
            g.drawString("Skräphög", getX() + 35, getY() + 80);
        }
    }
}
