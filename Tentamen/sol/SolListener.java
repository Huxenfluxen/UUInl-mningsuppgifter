import java.awt.*;
import java.awt.event.*;

class SolListener 
    extends MouseAdapter 
    implements MouseMotionListener {
    
    SolPanel panel;

    Card selected;
    int dx, dy;
    int oldx, oldy;


    SolListener (SolPanel _panel) {
        panel = _panel;
    } 

    public void mouseClicked (MouseEvent e) { 
	    
        int x = e.getX();
        int y = e.getY();

        Pile p = panel.findContainingPile(x,y);
        if(p != null)   {
            p.fillDeckPile(panel);
        
        // if (p instanceof DeckPile) {
        //     Pile d = ((DeckPile)p).getDiscard();
        //     if (p.isEmpty()) {
        //         while (!d.isEmpty()) {
        //             Card c = d.pop();
        //             c.flip();
        //             p.add(c);
        //             panel.putOnTop(c);
        //         }
        //     }
        //     else {
        //         Card c = p.pop();
        //         c.flip();
        //         d.add(c);
        //         panel.putOnTop(c);
        //     }
        // }
        }
        panel.repaint();
    }


    public void mousePressed (MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        Card c = panel.findCard(x,y);
        if (c != null) {
            selected = c;
            dx = x - c.getX();
            dy = y - c.getY();
            oldx = x;
            oldy = y;

            int w = selected.getWidth();
            int h = selected.getHeight();
            panel.setShadow(w, h, c.getImageUp());
        }
        panel.setActive(null);
        panel.repaint();
    }

    public void mouseDragged (MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        Pile d = null;

        if (selected != null) {
            oldx = x; 
            oldy = y;

            panel.moveShadow(x-dx,y-dy);

            Pile receiver =  panel.findReceivingPile(x,y);
            if (selected.isFaceUp()
                && receiver != null
                && receiver.canTake(selected)) {
                panel.setActive( receiver );
            }
            else {
                panel.setActive(null);
            }
        }

        panel.repaint();

    }    




    public void mouseReleased (MouseEvent e) {
        if (selected != null) {
            int x = e.getX();
            int y = e.getY();

            Pile d =  panel.findReceivingPile(x-dx, y-dy);
            
            panel.moveCard(selected, d, x-dx, y-dy);
            selected = null;
            panel.hideShadow();
            panel.setActive(null);
             
            panel.repaint();
        }
    }
        
    public void mouseMoved (MouseEvent e) {}
    
}
