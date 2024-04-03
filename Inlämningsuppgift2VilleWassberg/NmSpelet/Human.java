// Ville.wassberg@gmail.com
import java.util.*;
import java.util.Scanner;

/* Klassen Human ärver klassen player och definierar
en spelare av typen human, samt en metod "removeMatches"
som låter människan att välja hur många stickor som ska 
tas ifrån högen. */
public class Human extends Player	{
	// Klassvariabler
	private int removedMatches, upperLimit;
	
	public Human(String name)	{
		super(name);	//Definierar en människa med hjälp av superklassen Player
	}
	
	/*En metod som tar antalet stickor i högen som argument för att kunna sätta en övre
	gräns på hur många stickor människan max får ta bort, och som returnerar antalet
	stickor som den vill ta bort från högen. */
	public int removeMatches(int matchesLeft)	{
		upperLimit = (int) Math.floor(matchesLeft / 2);	//Sätt maxgräns
		System.out.println("How many matches between 1 and " + upperLimit +
		" do you want to remove from the pile? Input an integer, please.");
		Scanner humanInput = new Scanner(System.in);	//Skapa en ny scanner
		
	//Försök till att fånga några inputs som inte är inom önskat intervall
		try	{	
			removedMatches = humanInput.nextInt();
			while (removedMatches < 1 || removedMatches > upperLimit)	{	
				System.out.println("Please choose an integer in the interval [1, " + upperLimit + "].");
				removedMatches = humanInput.nextInt();
			}
		}
		catch (NumberFormatException e) {
			System.out.println("NumberFormatException: Please choose an integer in the interval [1, " + upperLimit + "].");
			removeMatches(matchesLeft);
		}
		catch (InputMismatchException e)	{
			System.out.println("InputMismatchException: Please choose an integer in the interval [1, " + upperLimit + "].");
			removeMatches(matchesLeft);
		}
		return removedMatches;
	}
}
