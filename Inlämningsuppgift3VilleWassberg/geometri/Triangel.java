package geometri;

import java.awt.*;

// Triangelklass som representerar ett objekt av klassen trianglar
public class Triangel extends Figurer	{
	
	private int w, h;
	private int[][] nodes;
	
	public Triangel(int tx, int ty, int tw, int th)	{
		this.x = tx; 
		this.y = ty; 
		this.w = tw; 
		this.h = th;
		
	}
	// Måla upp några linjer som får representera en triangel
	@Override
	public void måla(Graphics g)	{
		// g.setColor(Color.green);
		// g.drawLine(x + w/2, y, x + w, y + h);
		// g.drawLine(x + w/2, y, x, y + h);
		// g.drawLine(x, y + h, x + w, y + h);
		nodes = new int[][] {{this.x, this.x + this.w/2, this.x + this.w}, {this.y + this.h, this.y, this.y + this.h }};
		g.setColor(Color.red);
		g.fillPolygon(nodes[0], nodes[1], 3);
		// g.drawLine(x + w/2, y, x + w/6, y + h);
		// g.drawLine(x + w/2, y, x + w/3, y + h);
		// g.drawLine(x + w/2, y, x + w/2, y + h);
		// g.drawLine(x + w/2, y, x + 2*w/3, y + h);
		// g.drawLine(x + w/2, y, x + 5*w/6, y + h);
	}
	// Kollar om en given punkt ligger inom triangelns kanter, line3 är botten, line2 är höger kant och line1 är vänster kant
	// returnerar var i triangeln som klicket skedde, relativt kanterna
	@Override
	public boolean innehåller(int p1, int p2)	{
		double line1 = (double) this.h*(2.0/this.w*(this.x - p1) + 1) + this.y;
		double line2 = (double) this.h*(2.0/this.w*(p1 - this.x) - 1) + this.y;
		int line3 = this.y + this.h;
		boolean bool1 = (p1 <= this.x + this.w/2 && p1 >= this.x && p2 <= line3 && p2 >= line1);
		boolean bool2 = (p1 > this.x + this.w/2 && p1 <= this.x + this.w && p2 <= line3 && p2 >= line2);
		return (bool1 || bool2);
	}
}