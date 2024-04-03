package geometri;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.util.List;

// Panelen där allting händer
public class Panelen extends JLayeredPane implements MouseListener, MouseMotionListener	{
	
	private Rektangel r1;
	private Triangel t1;
	private Cirkel c1;
	private int coordX, coordY;
	private List<Figurer> figurer, figurerReverse;

	private Figurer figur;
	
	public Panelen()	{
		addMouseListener(this);
		addMouseMotionListener(this);
		r1 = new Rektangel(20, 50, 100, 150);	// Konstruerar ny Rektangel på startposition rx, ry med höjd och bredd rh, rw
		t1 = new Triangel(70, 60, 200, 150);	// Konstruerar ny Triangel på startposition (tx,ty) med höjd och bredd th, tw
		c1 = new Cirkel(60, 70, 80);			// Konstruerar ny Cirkel med centrum (cx,cy) med radie cr
		figurer = new ArrayList<>();			//Array med figurerna
		figurerReverse = new ArrayList<>();
		figurer.add(r1);
		figurer.add(t1);
		figurer.add(c1);
		//Skapar en figurer baklänges för att måla upp dem i rätt ordning när de loopas
		for(Figurer fig:figurer)	{
			figurerReverse.add(0, fig);
		}
		setBackground(Color.lightGray);
		setPreferredSize(new Dimension(600, 400));
	}
	// Måla upp figurerna med deras metoder med hjälp av paintComponent.
	@Override
	public void paintComponent(Graphics g)	{
		super.paintComponent(g);
		for(Figurer fig:figurerReverse)	{
			fig.måla(g);
		}
	}
	// Kolla om en figurer hålls in med musen
	@Override
	public void mousePressed(MouseEvent e)	{
		int x = e.getX();
		int y = e.getY();
		//Här gjordes om en hel del, vilket blev betydligt snyggare
		// tack vare att Figurer blev abstrakt klass istället för gränssnitt
		figur = null;
		for(Figurer fig:figurer)	{
			if(fig.innehåller(x, y))	{
				coordX  = x - fig.x;
				coordY = y - fig.y;
				figur = fig;
				break;
			}
		}
		// byt plats på den valda figuren till först och sist i figurer och målarlistan, respektive
		if(figur != null){
			if(!figur.equals(figurer.get(0)))	{
				figurer.remove(figur);
				figurer.add(0, figur);
				int size = figurerReverse.size();
				figurerReverse.remove(figur);
				figurerReverse.add(size - 1, figur);
			}
			repaint();
		}
	}

	// Om en figur har blivit träffad av musen så flyttas/dras den dit den släpps
	@Override
	public void mouseDragged(MouseEvent e)	{
		if(figur != null){
			figur.move(e.getX() - coordX, e.getY() - coordY);
			repaint();
		}
	}
	
	// Flera metoder som inte har använts från mousebiblioteket, men som behövdes skrivas ut då klassen inte är abstrakt
	@Override
	public void mouseMoved(MouseEvent e)	{
	}
	
	@Override
	public void mouseClicked(MouseEvent e)	{
	}
	
	@Override
	public void mouseReleased(MouseEvent e)	{
	}
	
	@Override
	public void mouseEntered(MouseEvent e)	{
	}
	
	@Override
	public void mouseExited(MouseEvent e)	{
	}
}