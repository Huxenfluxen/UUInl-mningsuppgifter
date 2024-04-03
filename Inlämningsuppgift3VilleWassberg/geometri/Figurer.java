package geometri;

import java.awt.*;

// Abstrakt klass som definierar diverse användbara metoder för figurer generellt
public abstract class Figurer 	{
	protected int x, y;
	
	abstract void måla(Graphics g);
	abstract boolean innehåller(int x, int y);
	public void move(int x, int y)	{
		this.x = x; 
		this.y = y;
	}
}
	
	