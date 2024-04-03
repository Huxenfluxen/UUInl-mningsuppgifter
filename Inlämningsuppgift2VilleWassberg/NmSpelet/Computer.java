// Ville.wassberg@gmail.com
import java.util.Random;

/* Klassen Computer ärver klassen player och definierar
en spelare av typen dator, samt en metod "removeMatches"
som låter datorn att välja hur många stickor som ska 
tas ifrån högen via . */
public class Computer extends Player	{
	public Computer(String name)	{
		super(name);	//Definierar en dator med hjälp av superklassen Player
	}
	
	public int removeMatches(int matchesLeft)	{
		// Sätter ett max och min på intervallet random ska tas ifrån
		int removedMatches = (int) (Math.random() * (matchesLeft / 2 - 1) + 1);
		// System.out.println(name + " removes " + removedMatches + " matches!");
		return removedMatches;
	}
}