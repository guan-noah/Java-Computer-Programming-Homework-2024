import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Character
{
	private ArrayList<String> moveset;
	private String name;
	private String description;
	private int maxHP;
	private int maxMana;
	private int hp;
	private int mana;
		
	public Character(String nameIn)
	{
		name = nameIn;
		
		moveset = new ArrayList<String>();
		File characterFile = new File("characters.txt");
		Scanner read = null;
		
		try
		{
			read = new Scanner(characterFile);
			
			String line = new String();
			while(read.hasNext() && !line.equals(name))
			{
				line = read.nextLine();
			}
			
			maxHP = read.nextInt();
			maxMana = read.nextInt();
			read.nextLine();
			description = read.nextLine();
			
			int barIndex;
			line = read.nextLine();
			do
			{
				barIndex = line.indexOf("|");
				
				if(barIndex != -1)
				{
					moveset.add(line.substring(0, barIndex));
					line = line.substring(barIndex+1);
				}
			}
			while(barIndex != -1);
			
			moveset.add(line);
		}
		catch(FileNotFoundException e)
		{
			System.err.println("Error: Could not locate file \"characters.txt\".");
		}
	}
	
	public void print()
	{
		System.out.println(name);
		System.out.println(maxHP);
		System.out.println(maxMana);
		System.out.println(description);
		System.out.println(moveset);
	}
}
