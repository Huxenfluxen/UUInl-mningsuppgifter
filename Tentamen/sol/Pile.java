import java.awt.Graphics;
import java.awt.Color;
import java.util.*;
import java.awt.geom.Point2D;
import java.awt.*;

public abstract class Pile {
    // coordinates of the card pile
    private int x;
    private int y;
    private static int fanned = 20;
    
    private static double fuzz = 1.0;

    private ArrayList<Card> cards = new ArrayList<Card>();

    Pile (int x, int y) {
        this.x = x;
        this.y = y;
    }

    boolean findCard(Card c) {
        return cards.contains(c);
    }

    void move(Card c, Pile d) {
        if (!isEmpty() && c==top()) {
            pop();
            d.add(c);
        }
    }

    Card top() {
        return cards.get(cards.size()-1);
    }

    Card pop() {
        int i = cards.size()-1;
        Card r = cards.get(i);
        cards.remove(i);
        return r;
    }


    boolean isEmpty() {
        return cards.size() == 0;
    }


    void addSimple(Card c) {
        cards.add(c);
    }

    
    void add(Card c) {
        c.move(x,y);
        cards.add(c);
    }

    void addFanned(Card c)  {
        int x = this.x;
        int y = this.y;
        if (!isEmpty()) {
            Card c1 = top();
            x = c1.getX();
            y = c1.getY()+fanned;
        }
        c.move(x,y);
        addSimple(c);
    }

    void click(Game g) {
    }

    public boolean contains (int x1, int y1) {
        if (isEmpty()) {
            return x <= x1 && x1 <= x + Card.width
                && y <= y1 && y1 <= y + Card.height;
        }
        else {
            return top().contains(x1, y1);
        }
    }


    
    public boolean receives (int x1, int y1) {
        if (isEmpty()) {
            return Math.abs(x-x1) <= Card.width * fuzz &&
                Math.abs(y-y1) <= Card.height * fuzz;
        }
        else {
            Card top = top();
            int x = top.getX();
            int y = top.getY();
            return Math.abs(x-x1) <= Card.width * fuzz &&
                Math.abs(y-y1) <= Card.height * fuzz;
        }
    }

    public abstract boolean canTake(Card c);

    public abstract boolean canRelease(Card c);

    public abstract void fillDeckPile(SolPanel panel);

    public abstract void showCard();


    public void drawActive(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
                                                   0.5f)); 

        g2.setColor(Color.PINK);

        g2.fill(new Rectangle(x, y, 71, 96));
        
        for (Card c : cards) {
            c.draw(g2);
        }
    }


    public void draw(Graphics g) {

        g.setColor(Color.PINK);
        g.drawRect(x, y, 71, 96);
        g.drawRect(x+1, y+1, 71, 96);
        
        for (Card c : cards) {
            c.draw(g);
        }
    }


}

