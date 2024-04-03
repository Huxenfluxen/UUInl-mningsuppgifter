// Ville.wassberg@gmail.com
// Huvudklassen Nm. Här körs main-metoden och spelet Nm.
import javax.swing.*;
import java.io.*;
import java.util.*;
import java.util.Scanner;

public class Nm {
	
	private Player player1, player2; //Instansiera två spelare
    private Pile pile; //Instansiera en hög
	private int matchesLeft = 19; //Sätter ett defaultvärde på högens antal stickor
	private String pl1Name, pl2Name;
	
	/* Konstruktorn Nm. Här scannas användarnas namn in, spelet presenteras, användarnas
		önskade antal stickor i högen tas in och högen skapas. Spelarna skapas, och spelet
		sätts igång.*/
	private Nm()	{
		
		Scanner scanner = new Scanner(System.in);

        System.out.println("You are playing Nm! :) \n");

        System.out.println("Enter the number of matches you want to have in the pile: \n");
        try	{	 //Försöka skanna in ett heltal från användaren
			matchesLeft = scanner.nextInt();
		}
		catch (NumberFormatException e) {	//Fånga number exception
			System.out.println("NumberFormatException: Nm sets amount of matches to default value: " + matchesLeft + ".");
		}
		catch (InputMismatchException e)	{	//Fånga något annat konstigt input som sträng el likn.
			System.out.println("InputMismatchException: Nm sets amount of matches to default value: " + matchesLeft + ".");
		}
		scanner.nextLine();
        System.out.println("What is the name of player 1? ");
		try	{	
			pl1Name = scanner.nextLine();	//Skanna namn på spelare 1
		}
		catch	(Exception e)	{
			System.out.println("Exception: Nm sets name to default value.");
			pl1Name = "Player1";
		}
        System.out.println("What is the name of player 2? ");
		try	{	pl2Name = scanner.nextLine();	//Skanna namn på spelare 2
		}
		catch	(Exception e)	{
			System.out.println("Exception: Nm sets name to default value.");
			pl2Name = "Player2";
		}
		// scanner.close(); //Får inte riktigt till det med denna
				
		pile = new Pile(matchesLeft);
		
		//Skapa spelarna med deras inskannade namn
		player1 = createPlayer(pl1Name);
		player2 = createPlayer(pl2Name);

		playNm(player1, player2, pile); // starta spelet med spelarna och högen med stickor
	}

	//Definiera vad som sker vid spelarnas drag
	private void playerMove(Player player, int matchesLeft)	{
		int removedMatches = player.removeMatches(matchesLeft); //Spelarens drag
		pile.reducePile(removedMatches); //Tar bort de önskade stickorna
		System.out.println(player.getName() + " removes " + removedMatches + " matches!");
		System.out.println(pile.getMatchesLeft() + " matches left in the pile!\n");

	}
	
	/*Spelets semantik. Ordning på spelarnas drag. Vad de får göra när det är deras tur, 
	samt vad som händer när en sticka är kvar. Argumenten bör fungera, då de tas om hand
	i viss mån i main-metoden.*/
    private void playNm(Player player1, Player player2, Pile pile)	{
		matchesLeft = pile.getMatchesLeft();
		
        while (matchesLeft > 1)	{	//Loopa spelarnas turer tills det inte finns tillräckligt med stickor kvar
			System.out.println(player1.getName() +"'s turn!");
            playerMove(player1, matchesLeft);
			matchesLeft = pile.getMatchesLeft();
			
            if (matchesLeft == 1)	{
                System.out.println("Player " + player1.getName() + " wins!\n");
                break;
            }
			System.out.println(player2.getName() +"'s turn!");
			playerMove(player2, matchesLeft);
			matchesLeft = pile.getMatchesLeft();
			
			if (matchesLeft == 1)	{
                System.out.println("Player " + player2.getName() + " wins!\n");
                break;
            }
        }
    }
	/*Skapa spelartyperna! Här tillåts användaren att välja mellan Human och computer
		Tanken är att spelarna skapas via deras klass-konstruktorer och får det namn 
		som anges som argument. */
	private Player createPlayer(String name)	{
		String[] options = {"Human","Computer"};
		int playerType = JOptionPane.showOptionDialog(null, //parentComponent
		   "Is " + name +" Human or computer??", //Object message,
		   "Choose player type", //String title
		   JOptionPane.YES_NO_OPTION, //int optionType
		   JOptionPane.INFORMATION_MESSAGE, //int messageType
		   null, // No image icon,
		   options, // the alternatives,
		   options[0]);//Object initialValue, human
		   
			if(playerType == 0 )	{	//Human is chosen
				System.out.println(name + " is human.");
				return new Human(name);
			}
			else	{			//Computer is chosen
				System.out.println(name + " is a computer.\n");
				return new Computer(name);
			}
	}
	// main-metoden
	public static void main(String[] args) {
		Nm spel = new Nm();
		
	}
}
