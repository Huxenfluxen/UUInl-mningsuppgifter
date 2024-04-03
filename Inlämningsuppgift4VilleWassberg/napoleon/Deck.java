package napoleon;

import java.awt.Image;
import java.util.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Deck extends PileOfCards   {
    
    private String folder = "UUInlämningsuppgifter/Inlämningsuppgift4VilleWassberg/napoleon/cards/";
    private List<Card> deck;
    private Image[][] cardImages;
	private Image backSide;
	private Card card;

    public Deck()  {
        super();
        deck = new ArrayList<>(0);
        setDeck(deck);
        addImages();
        createDeck();
        shuffleDeck();
    }
    
    // bör endast var möjligt att lägga ett kort om kortet precis har tagits.
    public void addCard(Card c) throws CardOrderException  {
        if(getLastCard().isCardsEqual(c))   {
            c.setFaceUp(false);
            deck.add(0, c);
            c.move(getX(), getY());
            setDeck(deck);
        }
        else
            throw new CardOrderException("Går inte att lägga tillbaka kort mer än en gång");
    }
    // Blanda högen inför spelets början
    public void shuffleDeck()   {
        Collections.shuffle(deck);
    }
    // Plocka bort översta kortet och returnera det
    @Override
	public Card dealCard()	{
        setLastCard(deck.remove(0));
        card = getLastCard();
        card.setFaceUp(true);
        setDeck(deck);
        return card;
	}
    //lägg till alla kort från skräphögen
    public void takeAllCards(List<Card> wastePile)  {
        Iterator<Card> iterator = wastePile.iterator();
        while(iterator.hasNext()){
            Card c = iterator.next();
            c.setFaceUp(false);
            deck.add(0, c);
        }
        setDeck(deck);
    }
    // Skapa en kortlek att skicka ut till korthögsklasserna
    public void createDeck()	{
        deck = new ArrayList<Card>();
        for(Card.Rank rank : Card.Rank.values())	{
            for(Card.Farg färg : Card.Farg.values())	{
				card = new Card(rank, färg, cardImages[rank.ordinal()][färg.ordinal()], backSide);
                deck.add(card);
				card.setPlace(this.getX(), this.getY());
            }
        }
        setDeck(deck);
    }
    // Lånad Från Sven-Olofs exempel		
	private static Image readImage(String imageFile) {
	    Image image = null;
        try {
            image = ImageIO.read(new File(imageFile));
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
	    return image;
    }
	// Lägg till alla bilderna som ska representera korten från mappen cards
	private void addImages()	{
        cardImages = new Image[13][4];
        for(Card.Rank rank : Card.Rank.values())	{
            for(Card.Farg färg : Card.Farg.values())	{
                cardImages[rank.ordinal()][färg.ordinal()] = readImage(folder + färg.getColour() + rank.getVal() + ".gif");
            }
		}
		backSide = readImage(folder + "b2fv.gif");
	}

}
