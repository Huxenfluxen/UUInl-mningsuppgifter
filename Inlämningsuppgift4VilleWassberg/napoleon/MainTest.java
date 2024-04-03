package napoleon;

import java.awt.Color;
import java.awt.Graphics;

import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.lang.model.util.ElementScanner14;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainTest extends JPanel implements MouseListener, MouseMotionListener    {
    private Deck deck = new Deck();
    private int x, y;
    private Card chosenCard = null;
    MainTest()  {
        Card card = deck.dealCard();
        System.out.println(card.getColour() + card.getVal());
        System.out.println(deck.getTopCard().getColour() + deck.getTopCard().getVal());
        setBackground( Color.GREEN );
        setLayout(new GridLayout(3, 4, 10, 10));
        add(deck);
        addMouseListener(this);
        addMouseMotionListener(this);
    }
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            if(chosenCard == null)  
                deck.paintComponent(g);
            else 
                chosenCard.paintCard(getGraphics());
        }
        
        @Override
        public void mouseDragged(MouseEvent e) {
            if(chosenCard != null){
                chosenCard.move(e.getX() - x, e.getY() - y);
                repaint();
            }
            // TODO Auto-generated method stub
            // throw new UnsupportedOperationException("Unimplemented method 'mouseDragged'");
        }
        @Override
        public void mousePressed(MouseEvent e) {
            if(deck.getTopCard().contains(e.getX(),e.getY()))   {
                x = e.getX() - deck.getTopCard().getX(); y = e.getY() - deck.getTopCard().getY();
                chosenCard = deck.dealCard();
            }
        }
        @Override
        public void mouseClicked(MouseEvent e) {
            // TODO Auto-generated method stub
            // throw new UnsupportedOperationException("Unimplemented method 'mouseClicked'");
        }
        @Override
        public void mouseMoved(MouseEvent e) {
            // TODO Auto-generated method stub
            // throw new UnsupportedOperationException("Unimplemented method 'mouseMoved'");
        }
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'mousePressed'");
        @Override
        public void mouseReleased(MouseEvent e) {
            chosenCard = null;
            // TODO Auto-generated method stub
            // throw new UnsupportedOperationException("Unimplemented method 'mouseReleased'");
        }
        @Override
        public void mouseEntered(MouseEvent e) {
            // TODO Auto-generated method stub
            // throw new UnsupportedOperationException("Unimplemented method 'mouseEntered'");
        }
        @Override
        public void mouseExited(MouseEvent e) {
            // TODO Auto-generated method stub
            // throw new UnsupportedOperationException("Unimplemented method 'mouseExited'");
        }
        
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        JPanel panel = new MainTest();
        frame.getContentPane().add(panel);
	    frame.setBounds(100, 100, 500, 300);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        
    }
}
