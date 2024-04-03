import java.awt.*;
import java.util.*;
import javax.swing.*;

class SolPanel extends JPanel {
    

    private Shadow shadow = new Shadow();
    private boolean showShadow = false;
    private Game game;
    private Pile active = null;
    
    void setShadow(int w, int h, Image image) {
        shadow.set(w, h, image);
    }
    
    void moveShadow(int x, int y) {
        shadow.move(x,y);
        showShadow = true;
    }

    void hideShadow() {
        showShadow = false;
    }

    void setActive (Pile active) {
        this.active = active;
    }
	    
    public SolPanel (Game game) {
        setBackground( Color.GREEN );
        setForeground( Color.BLACK );
        Card.background = Color.GREEN;

        SolListener sl = new SolListener(this);
        addMouseListener (sl);
        addMouseMotionListener(sl);

        this.game = game;
	game.restart();
	
        repaint();
    }

    // public boolean pileCanTake(Pile p, Card c) {
    //     return game.pileCanTake(p, c);
    // }

    
    public void click(Pile p) {
        game.click(p);
    }

    public Card findCard(int x, int y) {
        return game.findCard(x,y);
    }

    public void moveCard(Card c, Pile d, int x, int y) {
        game.moveCard(c, d, x, y);
    }

    Pile findPile(Card c) {
        return game.findPile(c);
    }

    void putOnTop(Card c) {
        game.putOnTop(c);
    }

    public Pile findReceivingPile(int x, int y) {
        return game.findReceivingPile(x, y);
    }

    public Pile findContainingPile(int x, int y) {
        return game.findContainingPile(x, y);
    }

    public void paintComponent (Graphics g) {
	super.paintComponent(g);
        game.paintComponent(g);

        if (active != null) {
            active.drawActive(g);
        }
        
        if (showShadow) {
            shadow.draw(g);
        }
    }
}	
