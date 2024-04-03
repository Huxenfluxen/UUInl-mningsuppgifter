// Ville.wassberg@gmail.com
/* En klass för högen av stickor vars syfte är att kunna hantera antalet
	stickor i högen.*/
public class Pile	{
	// Instansvariabler
	private int matchesLeft;
	
	//Klassmetoder
	//För att kunna hämta info om antalet stickor i högen
	public int getMatchesLeft()	{
		return	matchesLeft;
	}
	// Konstruera en hög av ett antal stickor
	public Pile(int matchesLeft)	{
		this.matchesLeft = matchesLeft;
	}
	// Plocka bort ett antal valda stickor från högen
	public void reducePile(int removedMatches)	{
		matchesLeft -= removedMatches;
	}
	
}