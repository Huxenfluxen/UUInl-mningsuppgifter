import java.util.Scanner;

public class Skanner	{
	private String name1, name2;
	private int age;
	
	private Skanner()	{
		Scanner scan = new Scanner(System.in);
		System.out.println("What's your age?");
		age = scan.nextInt();
		scan.nextLine();
		System.out.println("What's your name?");
		name1 = scan.nextLine();
		System.out.println("What's your name?");
		name2 = scan.nextLine();
		System.out.println(name1 + " and " + name2);
	}
	
	public static void main (String[] args)	{
		Skanner skanning = new Skanner();
	}
}
		