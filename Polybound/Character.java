import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.awt.Image;
import java.util.ArrayList;

public class Character
{
	private ArrayList<String> moveSet;
	private String name;
	private String description;
	private Image charImage;
	private int maxHP;
	private int maxMana;
	private int hp;
	private int mana;
	private int defense;
	
	public Character(String nameIn)
	{
		name = nameIn;
		moveSet = new ArrayList<String>();
			
		loadData();
		charImage = GameData.loadImage(name.toLowerCase() + ".png");
	}

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
			int hpLow = Integer.parseInt(line.substring(0, line.indexOf("..")));
			int hpHigh = Integer.parseInt(line.substring(line.indexOf("..")+2));
			maxHP = (int)(Math.random() * (hpHigh-hpLow+1) + hpLow);
			
			line = read.nextLine();
			int manaLow = Integer.parseInt(line.substring(0, line.indexOf("..")));
			int manaHigh = Integer.parseInt(line.substring(line.indexOf("..")+2));
			maxMana = (int)(Math.random() * (manaHigh-manaLow+1) + manaLow);
			
			line = read.nextLine();
			int defenseLow = Integer.parseInt(line.substring(0, line.indexOf("..")));
			int defenseHigh = Integer.parseInt(line.substring(line.indexOf("..")+2));
			defense = (int)(Math.random() * (defenseHigh-defenseLow+1) + defenseLow);
			
			description = read.nextLine();
			
			int barIndex;
			line = read.nextLine();
			do
			{
				barIndex = line.indexOf("|");
				
				if(barIndex != -1)
				{
					moveSet.add(line.substring(0, barIndex));
					line = line.substring(barIndex+1);
				}
			}
			while(barIndex != -1);
			
			moveSet.add(line);
		}
		catch(FileNotFoundException e)
		{
			System.err.println("Error: Could not locate file \"characters.txt\".");
		}
	}
	
	public boolean isDefeated()
	{
		return hp < 1;
	}
	
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
	
	public void decreaseMana(int cost)
	{
		mana -= cost;
		
		if(mana < 0)
		{
			mana = 0;
		}
	}
	
	public int getHP()
	{
		return hp;
	}
	
	public int getMana()
	{
		return mana;
	}
	
	public int getDefense()
	{
		return defense;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public ArrayList<String> getMoveset()
	{
		return moveSet;
	}
	
	public void print()
	{
		System.out.println(name);
		System.out.println(maxHP);
		System.out.println(maxMana);
		System.out.println(defense);
		System.out.println(description);
		System.out.println(moveSet);
	}
}
