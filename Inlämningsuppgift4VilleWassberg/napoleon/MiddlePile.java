package napoleon;

import java.awt.Font;
import java.awt.Graphics;
import java.util.*;

public class MiddlePile extends PileOfCards {
    
    private List<Card> middlePile = new ArrayList<Card>();

    public MiddlePile()  {
        super();
        middlePile = new ArrayList<>(0);
        setDeck(middlePile);
    }

    public void addCard(Card card) throws CardOrderException  {
        if(card.getRank().ordinal() + 1 == 6 && (deckEmpty() || getTopCard().getRank().ordinal() + 1 == 1)){
            middlePile.add(0, card);
            card.move(getX(), getY());
            setDeck(middlePile);
            // card.setFaceUp(true);
        }
        else if(getTopCard() != null){
            if(getTopCard().compareCard(card) == 1 || card.getRank().ordinal() + 1 == 6)    {
                middlePile.add(0, card);
                card.move(getX(), getY());
                setDeck(middlePile);
            }
            else 
                throw new CardOrderException("Om ledigt lägg 6 och fortsätt i avtagande ordning, om ess kan 6 läggas.");
        }
        else
            throw new CardOrderException("Om ledigt lägg 6 och fortsätt i avtagande ordning, om ess kan 6 läggas.");
    }
    public void takeAllCards(List<Card> wastePile){}

    @Override
    public void paintComponent(Graphics g)  {
        super.paintComponent(g);
        if(getTopCard() == null)    {
		    g.setFont(new Font("Serif", Font.ITALIC, 18));
            g.drawString("Mitten", getX() + 40, getY() + 80);
        }
    }
}
