/*Player.java
Part of SneakySnackySquirrel.java
Noah Guan 
P.6 Java w/ Mr. Yu
Program #11
10-9-2024
This program builds the player object for MySneakySnackySquirrel.java. 
It stores the player name and the acorn number. 
It also prints the score/number of acorns after each roll and if the player won or not. 
When called on, it can edit the acorn number that the player has. 
*/
///  CUT THE FOLLOWING CLASS OUT OF THIS FILE AND MAKE A NEW FILE CALLED:  Player.java
public class Player    
{
	private int acorn;
	private String playerName;
	
	public Player ( )   // default constructor for a player used if we do 
	{								   // not have the player's information.
		acorn = -5;		
		playerName = new String("");
	}
	
	public Player (String name)   // constructor method for a 
	{								// player â€“ with a name & score this overloads the constructor
		acorn = 0;  // set the playerâ€™s acorn count to zero â€“ the game 
									// has just begun!  Use the already declared field variable.
		playerName = name;  	// give the player the name that was passed to 
								// this method and save it as a field variable (already declared)
	}
	
	/* This setter method takes in a value and sets the private field variable acorn to it
	*/
	public void setAcorn(int acornIn)	
	{									
		acorn += acornIn;
	}
	
	/* This getter method gives access the private field variable and return the value of acorn
	*/
	public int getAcorn()
	{
		return acorn;
	}
	
	// This getter method returns the players name.
	public String getPlayerName()
	{
		return playerName;
	}
	
	public void printScore()  //  complete the header for printScore().  The body of the method is 
	{			//complete.  This method generates appropriate output â€“ number of acorns and a win message.
		System.out.println(playerName + " has this many acorns: " + acorn);
		if (acorn >= 5)
			System.out.println("You won!");
	}
}
