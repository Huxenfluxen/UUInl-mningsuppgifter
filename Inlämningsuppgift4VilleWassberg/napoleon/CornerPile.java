package napoleon;

import java.awt.Font;
import java.awt.Graphics;
import java.util.*;

public class CornerPile extends PileOfCards {

    private List<Card> cornerPile = new ArrayList<Card>();

    public CornerPile()  {
        super();
        cornerPile = new ArrayList<>(0);
        setDeck(cornerPile);
    }

    public void addCard(Card card) throws CardOrderException  {
        if(card.getRank().ordinal() + 1 == 7 && deckEmpty()) {
            cornerPile.add(0, card);
            card.setFaceUp(true);
            card.move(getX(), getY());
            setDeck(cornerPile);
        }
        else if(! deckEmpty())  {
            if(getTopCard().compareCard(card) == -1){
                cornerPile.add(0, card);
                card.setFaceUp(true);
                card.move(getX(), getY());
                setDeck(cornerPile); 
            }
            else 
                throw new CardOrderException("Lägg 7 på ledig stråle, annars ett kort med en valör högre upp till kung");
        }
        else
            throw new CardOrderException("Lägg 7 på ledig stråle, annars ett kort med en valör högre upp till kung");
    }
    public void takeAllCards(List<Card> wastePile){}

    @Override
    public void paintComponent(Graphics g)  {
        super.paintComponent(g);
        if(getTopCard() == null)    {
		    g.setFont(new Font("Serif", Font.ITALIC, 15));
            g.drawString("Kungshög", getX() + 35, getY() + 80);
        }
    }
}
