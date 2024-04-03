package napoleon;


import java.awt.*;
import java.util.List;

import javax.swing.JPanel;

public class Card extends JPanel	{
	
	private Farg färg;
	private Rank rank;
	private int x, y;
	private boolean faceUp;
	private Image face, back;

	// private List<Card> cardList = new ArrayList<Card>();

	public Card(Rank rankVar, Farg färgVar, Image face, Image back)	{
		this.färg = färgVar; 
		this.rank = rankVar;
		this.faceUp = false;
		this.face = face;
		this.back = back;
		this.x = getX();
		this.y = getY();
	}
	// Sätt värdena på korten som oföränderliga
	public enum Rank	{
		ESS("1"), TVÅ("2"), TRE("3"), FYRA("4"), FEM("5"), SEX("6"),
		 SJU("7"), ÅTTA("8"), NIO("9"), TIO("10"), KNEKT("j"), DAM("q"), KUNG("k");
		
		private String val;
		//Så bildnamnet kan kallas på
		Rank(String val)	{
			this.val = val;
		}
		// Returnera värdet på kortet		
		public String getVal() {
			return val;
		}
	}
	// Sätt färgerna på korten som oföränderliga
	public enum Farg	{
		SPADER("s"), HJÄRTER("h"), RUTER("d"), KLÖVER("c");
		private String colour;
		Farg(String colour)	{
			this.colour = colour;
		}
		public String getColour() {
			return colour;
		}
	}
	public Image getBack()	{
		return this.back;
	}

	public Image getFace()	{
		return this.face;
	}

	public void setFaceUp(boolean bool)	{
		this.faceUp = bool;
	}

	public boolean isFaceUp()	{
		return this.faceUp;
	}

	public Rank getRank()	{
		return rank;
	}
	
	public Farg getFarg()	{
		return färg;
	}
	// Så ett korts rankvärde kan kallas på
	public String getVal()	{
		return rank.getVal();
	}
	public String getColour()	{
		return färg.colour;
	}
	// Kolla om en punkt är inom figuren och returnerar true eller false
	// public boolean contains(int x, int y)	{
		// return x >= this.x && y >= this.y && x <= this.x + 60 && y <= this.y + 100;
	// }
	// Vända på kortet
	public void turnCard()	{
		this.faceUp = !this.faceUp;
	}
	// Flytta figuren till argumentets koordinater
	public void move(int x, int y)	{
		this.x = x;
		this.y = y;
	}	
	//Sätta kortets startposition
	public void setPlace(int x, int y)	{
		this.x = x;
		this.y = y;
	}
	// Returnera kort till ursprungshög
	public void returnToPile(PileOfCards pile)	{
		List<Card> copy = pile.getDeck();
		copy.add(0, this);
		move(pile.getX(), pile.getY());
		pile.setDeck(copy);
	}
	//Kolla om kort är de samma
	public boolean isCardsEqual(Card card)  {
		String r1, r2, c1, c2;
		r1 = this.getVal(); r2 = card.getVal();
		c1 = this.getColour(); c2 = card.getColour();
		return r1.equals(r2) && c1.equals(c2);
	}
	// Måla kortets tillhörande bild
	public void paintCard(Graphics g, int x, int y, int w, int h)	{
		// super.paintComponent(g);
		if(faceUp){
			g.drawImage(face, x, y, w, h, null);
		}
		else
			g.drawImage(back, x, y, w, h, null);
	}

	public void paintCard(Graphics g)	{
		if(faceUp){
			g.drawImage(face, this.x + 30, this.y + 30, null);
		}
		else
			g.drawImage(back, this.x + 30, this.y + 30, null);
	}
	// Jämföra värdet med ett annat kort och returnerar skillnaden i värdet. Om 
	public int compareCard(Card otherCard)	{
		return this.rank.ordinal() - otherCard.rank.ordinal();
	}
}
