package geometri;

import java.awt.*;
import java.lang.Math;

public class Cirkel extends Figurer	{
	
	protected int r;
	// instantiera cirkelns x- och y-koordinater i omslutande rektangels övre vänstra hörn, samt radie
	public Cirkel(int cx, int cy, int cr)	{
		this.x = cx;
		this.y = cy;
		this.r = cr;
	}
	// Måla upp cirkel
	@Override
	public void måla(Graphics g)	{
		g.setColor(Color.black);
		g.fillOval(x, y, 2*r, 2*r);
	}
	// Kolla om given punkt är omsluten av cirkeln/disken. Returnerar koordinaterna i disken
	@Override
	public boolean innehåller(int x, int y)	{
		return (Math.sqrt(Math.pow(x - this.x - this.r, 2) + Math.pow(y - this.y - this.r, 2)) <= this.r);
	}
}