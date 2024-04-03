package napoleon;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class PileOfCards extends JPanel   {
    private int cardsLeft;
    protected List<Card> deck;
    protected boolean chosen;
    protected Card card;
	
    // Konstruera en kortlek
    public PileOfCards()	{
        deck = new ArrayList<>(0);
        // this.deck = deck;
        // this.cardsLeft = deck.size();
        // this.chosen = false;
        // this.card = card;
        setPreferredSize(new Dimension(75, 100));
    }
    // Få kortleken/högen
    public List<Card> getDeck() {
        return this.deck;
    }
    // Om det behövs sättas en ny kortlek, tex om  man inte klarat spelet på första rundan
    public void setDeck(List<Card> deck) {
        this.deck = deck;
        if(deck != null)
            this.cardsLeft = deck.size();
    }
    // Sätt att högen är vald/klickad på
    public void setPileChosen(boolean bool) {
        this.chosen = bool;
    }
    public boolean isPileChosen(){
        return this.chosen;
    }
	//Se hur många kort det är kvar i kortleken
	public int getDeckCount()	{
		return this.cardsLeft;
	}
    // Se om kortleken är tom
    public boolean deckEmpty()  {
        return getDeckCount() == 0;
    }
    // Få översta kortet
    public Card getTopCard()    {
        if(! deckEmpty()){
            // System.out.println("här är det" + deckEmpty() + " " + getDeckCount()); 
            return deck.get(0);
        }
        else
            return null;
    }
	// Plocka bort översta kortet och returnera det
	public Card dealCard()	{
        setLastCard(deck.remove(0));
        card.setFaceUp(true);
        setDeck(deck);
        return card;
	}
    //För att komma ihåg senaste kortet
    public void setLastCard(Card c)   {
        card = c;
    }
    public Card getLastCard()   {
        return card;
    }
    // Lägg till ett kort överst i högen
    public abstract void addCard(Card card) throws CardOrderException;
    // Lägga till alla kort, endast lämplig för deck. Tänkt att ta skräphögen som argument.
    public abstract void takeAllCards(List<Card> wastePile);
    //Ta bort alla kort
    public void removeAllCards()    {
        Iterator<Card> iterator = deck.iterator();
        while(iterator.hasNext())  {
            iterator.next();
            iterator.remove();
        }
        setDeck(deck);
    }
    //Måla upp en tom plats för högen, eller det översta kortet
    public void paintComponent(Graphics g) {
        if(getTopCard() != null)    {
            getTopCard().paintCard(g, getX() + 30, getY() + 30, 71, 96);
        }
        else    {
            g.drawRect(getX() + 30, getY() + 30, 71, 96);
            // g.drawString("hög", getX() + 25, getY() + 50);
        }
    }
}
