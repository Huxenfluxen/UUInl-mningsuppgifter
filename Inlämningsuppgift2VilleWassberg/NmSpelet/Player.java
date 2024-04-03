// Ville.wassberg@gmail.com
/* En klass vars främsta syfte är att instantiera gemensamma attribut
för spelarna som är tänkte att ärva från klassen. */
public abstract class Player	{
	public String name;
	
	// Konstruktor för att skapa en spelare med ett namn
	public Player(String name)	{
		this.name = name;
	}
	//Metod definierad för att kunna hämta namn på spelare
	public String getName()	{
		return name;
	}
	//För att kunna kalla på metoden removeMatches från en godtycklig spelare
	public abstract int removeMatches(int matchesLeft);
}


