/*
 * Noah Guan
 * Period 6
 * Move.java
 * Modeled after Character, this class represents a move 
 * (of the player or an enemy) in the game.
 * 
 * Important note: Moves can affect both user and enemy. 
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.awt.Image;
import java.util.ArrayList;

public class Move
{
	private String name, description; //name and description of Move
	private int[] damage, mana, defense; //effects of Move; first is 
		//effect to user, second is effect to enemy 
		//positive numbers indicate an increase of the characteristic
		//negative numbers indicate a decrease. 	
	private double critChance;
	
	public Move(String nameIn)
	{
		name = nameIn;							//take in the move name
		loadData();								//load move information
	}

	/*
	 * Attempts to access the file and load the data of the specified 
	 * Character represented by this instance.
	 */
	public void loadData()
	{
		String fileName = "moves.txt";
		File movesFile = new File(fileName);
		Scanner read = null;
		
		try
		{
			read = new Scanner(movesFile);
			cacheData(read);
		}
		catch(FileNotFoundException e)
		{
			System.err.printf("Error: Could not locate file \"%s\".", fileName);
		}
	}
	/* 
	 * Helper method to loadData, improves encapsulation and actually 
	 * initializes the fvs
	 */
	public void cacheData(Scanner readIn)
	{		
		String line = new String();
		while(readIn.hasNext() && !line.equals(name))
		{
			line = readIn.nextLine();	//character name on this line
		}
		for(int i = 0; i < damage.length; i++)//2 times; once for user and once for enemy
		{
			line = readIn.nextLine();			//character damage data on this line
			damage[i] = getRandomRange(line);
			
			line = readIn.nextLine();		//character mana data on this line
			mana[i] = getRandomRange(line);
			
			line = readIn.nextLine();		//character defense data on this line 
			defense[i] = getRandomRange(line);
		}
		
		description = readIn.nextLine();
		
		/* workshopped idea -- special effects 
		line = readIn.nextLine(); //gets the extra (special) effects
		while(line.indexOf("|") != -1)
		{
			moveSet.add(GameData.getDataTo(line, "|"));
			line = GameData.dataAfter(line, "|");
		}
		moveSet.add(line);			//get last effect after the last bar
		*/
	}
	public int getRandomRange(String lineIn)//gets an int from a random range in a String
	//different from GameData number generator bec. that can be used for other functions
	{
		int low = Integer.parseInt(GameData.getDataTo(lineIn, ".."));
		int high = Integer.parseInt(GameData.dataAfter(lineIn, ".."));
		return GameData.getRandom(low, high);
	}
	
	/*
	 * Returns this Move's name.
	 */
	public String getName()
	{
		return name;
	}
	
	/*
	 * Returns this Move's description.
	 */
	public String getDescription()
	{
		return description;
	}
	
	/*	//THESE FOLLOWING METHODS WILL COME IN SETS OF 2: ONE FOR USER 
	 * 		EFFECTS AND ONE FOR ENEMY
	 * 
	 * Returns this Move's damage.
	 */
	public int getUserDamage()
	{
		return damage[0];
	}
	public int getEnemyDamage()
	{
		return damage[1];
	}
	
	/*
	 * Returns this Move's mana.
	 */
	public int getMana()
	{
		return mana[0];
	}
	public int getEnemyMana()
	{
		return mana[1];
	}
	
	/*
	 * Returns this Move's defense effect.
	 */
	public int getUserDefenseEffect()
	{
		return defense[0];
	}
	public int getEnemyDefenseEffect()
	{
		return defense[1];
	}
	
	//debug method
	public void print()
	{
		System.out.println("Name: |" + name + "|");
		//use a for loop because we repeat it twice: once for user, once for enemy
		String[] assistForLoop = new String[] {"user", "enemy"};
		for(int i = 0; i < assistForLoop.length; i++)
		{
			System.out.println("For " + assistForLoop[i] + " effects:");
			System.out.println("\tDamage effect: |" + damage[i] + "|");
			System.out.println("\tMana effect: |" + mana[i] + "|");
			System.out.println("\tDefense effect: |" + defense[i] + "|");
		}
		System.out.println("Description: |" + description + "|");
		//~ System.out.println("Special effect: |" + specialEffect + "|");
	}
	
	/* reads from a text file of moves and outputs move 
	 * loads the move in GameData and when we have to access it, we request it */
	//~ public static ArrayList<Move> getMoveset(String characterName)
	//~ {
		//~ reads from a file with a list of all the moves based on character name
		//~ returns move object arraylist 
	//~ }
}
