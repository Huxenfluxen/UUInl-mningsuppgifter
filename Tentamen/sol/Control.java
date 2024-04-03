import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


class Control extends JPanel implements ActionListener {
    private JButton newButton  = new JButton("new");
    private JButton fixButton  = new JButton("fix");
    private JButton exitButton = new JButton("exit");

    private Game game;
    private JComponent solPanel;
    
    public Control (Game game, JComponent solPanel) { 

        this.game = game;
        this.solPanel = solPanel;
        
        add(newButton);
        add(fixButton);
        add(exitButton);
	
        newButton.addActionListener(this); 
        fixButton.addActionListener(this); 
        exitButton.addActionListener(this); 

    }

    public void actionPerformed(ActionEvent event) {
	if (event.getSource() == newButton) {
            game.restart();
        }
        else if (event.getSource() == fixButton) {
                game.fixedRestart();
            }
        else if  (event.getSource() == exitButton) {
	    System.exit(0);
	}
        solPanel.repaint();
    }
}
