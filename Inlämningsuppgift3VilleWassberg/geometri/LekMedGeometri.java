package geometri;

import javax.swing.*;

	// Skapa en ram och köra hela programmet

public class LekMedGeometri extends JFrame	{

	private Panelen panel;

	public LekMedGeometri()	{
		panel = new Panelen();
		setTitle("Lek med Geometri");
		setSize(1000, 750);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(panel);
	}
	
	public static void main(String[] arg)	{
		new LekMedGeometri();
	}
}