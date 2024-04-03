import java.awt.*;
import javax.swing.*;
import java.awt.geom.Point2D;

class Shadow {
    private int x, y;
    private int w, h;
    private Image image;
    
    public  void set(int w, int h, Image image) {
        this.w = w;
        this.h = h;
	this.image = image;
    }
    
    public void move(int x, int y){
        this.x = x;
        this.y = y;
    }

    // public void draw(Graphics g){
    //         
    //     g.setColor(Color.BLUE);
    //     g.drawRect(x, y, w, h);
    // 
    //     Graphics2D g2 = (Graphics2D) g;
    //     g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
    //                                                0.5f)); 
    //     g2.drawImage(image, x, y, null);
    //     
    // }

    public void draw(Graphics g){
            
        g.setColor(Color.BLUE);

        Graphics2D g2 = (Graphics2D) g;
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
                                                   0.5f)); 
        g2.drawRect(x, y, w, h);
	
    }

    
}
