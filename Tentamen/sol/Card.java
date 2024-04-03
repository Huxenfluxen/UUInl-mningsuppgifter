import java.awt.*;
import javax.swing.*;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.lang.RuntimeException;

class Card {
    private int x, y;
    private boolean faceUp;
    private Image front, back, current;
    static Color background;

    private int rank;
    private int suit;

    String name;
    
    static int width = 71;
    static int height = 96;



    public Card (int s, int r,
                 String name,
                 String frontFile, String backFile) {
        suit = s;
        rank = r;
        this.name = name;
        try {
            back = ImageIO.read(new File(backFile));
            front = ImageIO.read(new File(frontFile));
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        
        current = back;

        if (width == 0) {
            width = current.getWidth(null);
            height = current.getHeight(null);
        }

        
    }

    public int getX() { return x; }

    public int getY() { return y; }

    public int getRank() {
        return rank;
    }

    public int getSuit() {
        return suit;
    }

    
    public int getWidth() { return width; }

    public int getHeight() { return height; }

    public Image getImageUp() { return current; }
    
    // public void draw(Graphics g) {
    //     g.drawImage(current, x, y, null);
    // }

    public void draw(Graphics g) {
        if (faceUp) {
            g.setColor(java.awt.Color.WHITE);
            g.fillRect(x, y, width, height);
            g.setColor(java.awt.Color.BLACK);
            g.drawRect(x, y, width, height);
            
            if (suit==0 || suit==3) {
                g.setColor(java.awt.Color.BLACK);
            }
            else {
                g.setColor(java.awt.Color.RED);
            }
            Font f = new Font("SansSerif", Font.BOLD, 18);
            g.setFont(f);
            g.drawString(name, x+20, y+20);
        }

        else {
            g.setColor(java.awt.Color.GRAY);            
            g.fillRect(x, y, width, height);
            g.setColor(java.awt.Color.BLACK);
            g.drawRect(x, y, width, height);
        }
    }
            
    public void move(int x1, int y1) {
	x = x1;
	y = y1;
    }

    public void flip () {
        faceUp = !faceUp;
        if (faceUp) {
            current = front;
        }
        else {
            current = back;
        }
    }

    public void turnUp () {
	if (!faceUp) {
	    flip();
	}
    }
    
    public boolean isFaceUp () {
        return faceUp;
    }

    
    public boolean contains (int x1, int y1) {
	return x <= x1 && x1 <= x+width
            && y <= y1 && y1 <= y+height;
    }

    public String toString() {
        return "Card["+suit+" "+rank+" faceUp="+faceUp+"]";

    }
}
