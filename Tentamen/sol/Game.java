import java.util.*;
import java.awt.*;

class Game {
    private ArrayList<Card> cardSet;
    
    ArrayList<Pile> piles ;
    
    ArrayList<Pile> suitPiles;
    DeckPile deckPile;
    Pile discardPile;
    ArrayList<Pile> tablePiles;

    private Random randomGenerator;

    public Game() {

    }

    public void restart () {
	randomGenerator = new Random();

        initPiles();
	initDeck();
	shuffleDeck();
	prepareTable();
    }

    public void fixedRestart() {
	randomGenerator = new Random(1234567);
        piles = new ArrayList<Pile>();

        initPiles();
	initDeck();
	shuffleDeck();
	prepareTable();
    }
    
    private void initPiles() {
        int vOffset =  50;
        int hOffset =  50;
        int vRow    = 140;
        int hColumn = 100;
        
        piles = new ArrayList<Pile>();

	suitPiles = new ArrayList<Pile>();
        for (int i = 0; i<4; i++) {
            Pile suitPile = new SuitPile(hOffset+hColumn*i, vOffset);
            suitPiles.add(suitPile);
	    piles.add(suitPile);
        }

        deckPile = new DeckPile(hOffset+hColumn*5, vOffset);
	piles.add(deckPile);
	
	discardPile = new DiscardPile(hOffset+hColumn*6, vOffset);
	piles.add(discardPile);

        deckPile.setDiscard(discardPile);
        
        tablePiles = new ArrayList<Pile>();
	for (int i = 0; i<7; i++) {
	    Pile tablePile
		= new TablePile(hOffset+hColumn*i, vOffset+vRow);
	    tablePiles.add(tablePile);
	    piles.add(tablePile);
	}

    }	

    private void initDeck() {
	String back = "img/b1fv.gif";
	String[] suites = {"s", "h", "d", "c"};
	String[] ranks = {"1", "2", "3", "4", "5", "6", "7", "8",
			  "9", "10", "j", "q", "k"};

        cardSet = new ArrayList<Card>();

        for (int s = 0; s<4; s++) {
	    for (int r = 0; r<13; r++) {
		String front = "img/"+suites[s]+ranks[r]+".gif";
                String name = suites[s]+ranks[r];
		Card c = new Card(s, r, name, front, back);
		cardSet.add(c);
	    }
	}
    }

    private void shuffleDeck() {
        for (int i = 0; i<52; i++) {
            int j = i+randomGenerator.nextInt(52-i);
            swap(i, j);
        }
    }

    private void swap(int i, int j) {
        Card t = cardSet.get(i);
        cardSet.set(i, cardSet.get(j));
        cardSet.set(j, t);
    }

    private void prepareTable() {
                
	int p = 0;
        
	for (int i = 0; i<7; i++) {
	    Card c = null;
	    for (int j = 0; j<i+1; j++) {
		c = cardSet.get(p);
                tablePiles.get(i).addFanned(c);
		p = p+1;
	    }
            c.flip();
	}
        
	for (int i = 28; i<52; i++) {
	    Card c = cardSet.get(i);
	    deckPile.add(c);
	}
    }

    public void addCard (Card c) {
	cardSet.add(c);

    }

    Pile findPile(Card c) {
        if (c == null) {
            return null;
        }

        for (Pile p : piles) {
            if (p.findCard(c)) {
                    return p;
            }
        }
        return null;
    }
    
    public Card findCard(int x, int y) {
        Card r = null;
	for (Card c : cardSet) {
	    if (c.contains(x,y)) {
		r = c;
	    }
	}
	return r;
    }

     public void moveCard(Card c, Pile d, int x, int y) {
        Pile source = findPile(c);

        if(source.canRelease(c)
           && d != null
           && d.canTake(c)) {

            if (source != d){
                source.pop();
                source.showCard();
                d.addFanned(c);
                
                putOnTop(c);
	    }            
        }
    }

    public Pile findContainingPile(int x, int y) {
        Pile r = null;
	for (Pile p : piles) {
	    if (p.contains(x,y)) {
		r = p;
	    }
	}
        return r;
    }


    public Pile findReceivingPile(int x, int y) {
        Pile r = null;
	Pile other = null;
	for (Pile p : piles) {
	    if (p.receives(x,y)) {
	        other = r;
		r = p;
	    }
	}
	if(other == null) {
            return r;
        }
        else {
	    return null;
	}
    }

    public void click(Pile p) {
        if (p!=null) {
            p.click(this);
        }
    }

    public void putOnTop(Card c){
	cardSet.remove(c);
	cardSet.add(c);
    }

    // public boolean pileCanRelease(Card c, Pile p) {
    //     return c == p.top()
    //         && (p instanceof DiscardPile
    //             || p instanceof TablePile);
    // }
    
    // public boolean pileCanTake(Pile p, Card c) {
    //     if (p instanceof SuitPile) {
    //         if (p.isEmpty()) {
    //             return c.getRank() == 0;
    //         }
    //         else {
    //             Card topCard = p.top();
    //             return c.getSuit() == topCard.getSuit()
    //                 && c.getRank() == 1 + topCard.getRank()
    //                 || c == topCard;
    //         }
    //     }
    //     else if (p instanceof TablePile) {
    //         if (p.isEmpty()) {
    //             return c.getRank() == 12;
    //         }
    //         else {
    //             Card topCard = p.top();
    //             return ((c.getSuit() != topCard.getSuit())
    //                     && (c.getRank() == topCard.getRank() - 1))
    //                 || topCard == c;
    //         }
    //     }
    //     else return false;
    // }  

    
    public void paintComponent (Graphics g) {

	for (Pile p : piles ) {
	    p.draw(g);
	}
    }
    

}

    
