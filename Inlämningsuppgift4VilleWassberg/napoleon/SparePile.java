package napoleon;

import java.awt.Font;
import java.awt.Graphics;
import java.util.*;

public class SparePile extends PileOfCards   {
    
    static List<Card> sparePile = new ArrayList<Card>();

    public SparePile()  {
        super();
        sparePile = new ArrayList<>(0);
        setDeck(sparePile);
    }

    public void addCard(Card card) throws CardOrderException  {
        if(card.getRank().ordinal() + 1 == 6){
            sparePile.add(0, card);
            card.setFaceUp(true);
            card.move(getX(), getY());
            setDeck(sparePile);
        }
        else
            throw new CardOrderException("The card needs to be a 6 to be put here");
    }
    public void takeAllCards(List<Card> wastePile){}

    @Override
    public void paintComponent(Graphics g)  {
        super.paintComponent(g);
        if(getTopCard() == null)    {
		    g.setFont(new Font("Serif", Font.ITALIC, 18));
            g.drawString("Sexor", getX() + 40, getY() + 80);
        }
    }
}
