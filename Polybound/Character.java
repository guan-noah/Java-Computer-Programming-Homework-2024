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
	 * Attempts to load the data of the specified Character represented
	 * by this instance.
	 **/
	public void loadData()
	{
		String fileName = "characters.txt";
		File characterFile = new File(fileName);
		Scanner read = null;
		
		try
		{
			read = new Scanner(characterFile);
			
			String line = new String();
			while(read.hasNext() && !line.equals(name))
			{
				line = read.nextLine();
			}
			
			line = read.nextLine();
			int hpLow = Integer.parseInt(GameData.getDataTo(line, ".."));
			int hpHigh = Integer.parseInt(GameData.dataAfter(line, ".."));
			maxHP = GameData.getRandom(hpLow, hpHigh);
			
			line = read.nextLine();
			int manaLow = Integer.parseInt(GameData.getDataTo(line, ".."));
			int manaHigh = Integer.parseInt(GameData.dataAfter(line, ".."));
			maxMana = GameData.getRandom(manaLow, manaHigh);
			
			line = read.nextLine();
			int defenseLow = Integer.parseInt(GameData.getDataTo(line, ".."));
			int defenseHigh = Integer.parseInt(GameData.dataAfter(line, ".."));
			defense = GameData.getRandom(defenseLow, defenseHigh);
			
			description = read.nextLine();
			
			line = read.nextLine(); ///gets the moveset
			while(line.indexOf("|") != -1)
			{
				moveSet.add(GameData.getDataTo(line, "|"));
				line = GameData.dataAfter(line, "|");
			}
			moveSet.add(line);
		}
		catch(FileNotFoundException e)
		{
			System.err.printf("Error: Could not locate file \"%s\".", fileName);
		}
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
		System.out.println(name);
		System.out.println(maxHP);
		System.out.println(maxMana);
		System.out.println(defense);
		System.out.println(description);
		System.out.println(moveSet);
	}
	
	/**
	 * Returns this Character's Image.
	 **/
	public Image getImage()
	{
		return charImage;
	}
}
