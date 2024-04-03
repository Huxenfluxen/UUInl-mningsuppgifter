package napoleon;

import java.awt.Font;
import java.awt.Graphics;

import java.util.*;

public class SquarePile extends PileOfCards {

    private List<Card> squarePile = new ArrayList<Card>(1);

    public SquarePile()  {
        super();
        squarePile = new ArrayList<>(0);
        setDeck(squarePile);
    }
    
    public void addCard(Card card) throws CardOrderException  {
        if(! deckEmpty()){
            throw new CardOrderException("Du kan bara lägga hit nya kort om platsen är ledig.");
        }
        else
            squarePile.add(0, card);
            card.move(getX(), getY());
            setDeck(squarePile);
            // card.setFaceUp(true);
    }
    public void takeAllCards(List<Card> wastePile){}
    @Override
    public void paintComponent(Graphics g)  {
        super.paintComponent(g);
        if(getTopCard() == null)    {
		    g.setFont(new Font("Serif", Font.ITALIC, 18));
            g.drawString("Fyrkant", getX() + 35, getY() + 80);
        }
    }
}
