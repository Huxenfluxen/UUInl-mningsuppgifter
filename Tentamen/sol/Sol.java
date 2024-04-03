import java.awt.*;
import javax.swing.*;

class Sol extends JFrame {
    
    public Sol () {
	
        Game game = new Game();

        getContentPane().setLayout(new BorderLayout(5, 10));

        JPanel solPanel =  new SolPanel(game);
        getContentPane().add( "North", new Control(game, solPanel));
        getContentPane().add( "Center", solPanel);
                
        setBounds(100, 100, 800, 800);
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

    }
    
    static public void main(String[] args) { 
        Sol f = new Sol ();
    }
}
