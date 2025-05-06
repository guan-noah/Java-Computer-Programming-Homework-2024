/**
 * Krish Kumar
 * Period 6
 * Character.java
 * This class represents a character (being the player or an enemy)
 * in the game.
 **/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.awt.Image;
import java.util.ArrayList;

public class Character
{
	private ArrayList<String> moveSet; ///moveset of Character
	private String name; ///name of Character
	private String description; ///description of Character
	private Image charImage; ///image of Character
	private int maxHP; ///max health (hitpoints) of Character
	private int maxMana; ///max mana (move points) of Character
	private int hp; ///current health
	private int mana; ///current mana
	private int defense; ///defense of Character
	
	public Character(String nameIn)
	{
		name = nameIn;
		moveSet = new ArrayList<String>();
			
		loadData();
		charImage = GameData.loadImage(name.toLowerCase() + ".png");
	}

	/**
	 * Attempts to access the file and load the data of the specified 
	 * Character represented by this instance.
	 **/
	public void loadData()
	{
		String fileName = "characters.txt";
		File characterFile = new File(fileName);
		Scanner read = null;
		
		try
		{
			read = new Scanner(characterFile);
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
			System.out.println(line);
		}
		
		line = readIn.nextLine();		//character hp data on this line
		maxHP = getRandomRange(line);
		
		line = readIn.nextLine();	//character mana data on this line
		maxMana = getRandomRange(line);
		
		line = readIn.nextLine();//character defense data on this line
		defense = getRandomRange(line);
		
		description = readIn.nextLine();			//self-explanatory
		
		line = readIn.nextLine();					///gets the moveset
		while(line.indexOf("|") != -1)//processes the moveset; moves separated by bars
		{
			moveSet.add(GameData.getDataTo(line, "|"));
			line = GameData.dataAfter(line, "|");
		}
		moveSet.add(line);			//get last move after the last bar
	}
	public int getRandomRange(String lineIn)//gets an int from a random range in a String
	//different from GameData number generator bec. that can be used for other functions
	{
		int low = Integer.parseInt(GameData.getDataTo(lineIn, ".."));
		int high = Integer.parseInt(GameData.dataAfter(lineIn, ".."));
		return GameData.getRandom(low, high);
	}
	
	/**
	 * Returns true if this Character's health is less than 1.
	 * Returns false otherwise.
	 **/
	public boolean isDefeated()
	{
		return hp < 1;
	}
	
	/**
	 * Decreases this Character's health by the passed in
	 * damage value. If the health goes below 0, it returns
	 * to 0.
	 **/
	public void decreaseHP(int damage)
	{
		if(damage-defense > 0)
		{
			hp -= (damage-defense);
			
			if(hp < 0)
			{
				hp = 0;
			}
		}
	}
	
	/**
	 * Decreases this Character's mana by the passed in
	 * cost value. If the mana goes below 0, it returns
	 * to 0.
	 **/
	public void decreaseMana(int cost)
	{
		mana -= cost;
		
		if(mana < 0)
		{
			mana = 0;
		}
	}
	/*
	 * Returns this Character's name.
	 */
	public String getName()
	{
		return name;
	}
	/**
	 * Returns this Character's current health.
	 **/
	public int getHP()
	{
		return hp;
	}
	
	/**
	 * Returns this Character's current mana.
	 **/
	public int getMana()
	{
		return mana;
	}
	
	/**
	 * Returns this Character's defense.
	 **/
	public int getDefense()
	{
		return defense;
	}
	
	/**
	 * Returns this Character's description.
	 **/
	public String getDescription()
	{
		return description;
	}
	
	/**
	 * Returns this Character's moveset.
	 **/
	public ArrayList<String> getMoveset()
	{
		return moveSet;
	}
	
	///debug method
	public void print()
	{
		System.out.println("Name: |" + name + "|");
		System.out.println("Maximum hitpoints: |" + maxHP + "|");
		System.out.println("Maximum mana: |" + maxMana + "|");
		System.out.println("Defense: |" + defense + "|");
		System.out.println("Description: |" + description + "|");
		System.out.println("Moveset: |" + moveSet + "|");
	}
	
	/**
	 * Returns this Character's Image.
	 **/
	public Image getImage()
	{
		return charImage;
	}
}
