package geometri;

import java.awt.*;

// Rektangelklass som representerar ett objekt av klassen rektanglar
public class Rektangel extends Figurer	{
	
	private int w, h;
	
	public Rektangel(int rx, int ry, int rw, int rh)	{
		this.x = rx; 
		this.y = ry; 
		this.w = rw; 
		this.h = rh;
	}
	// Måla rektangel
	@Override
	public void måla(Graphics g)	{
		g.setColor(Color.magenta);
		g.fillRect(x, y, w, h);
	}
	// Kolla om en punkt är inom figuren och returnerar en tupel med koordinaterna på figuren annars null
	@Override
	public boolean innehåller(int x, int y)	{
		return (x >= this.x && y >= this.y && x <= this.x + w && y <= this.y + h);
	}
}