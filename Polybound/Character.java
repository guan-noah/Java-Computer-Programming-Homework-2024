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
	 private int rating; ///used to measure the difficulty of the character
	 private int level; ///level, which will scale enemy stats
	 
	/**
	 * Null character for placeholder. 
	 */
	public Character()
	{
		moveSet = new ArrayList<>();
		name = "";
		description = "";
		charImage = null;
		maxHP = 0;
		hp = 0;
		maxMana = 0;
		mana = 0;
		defense = 0;
		rating = 0;
		level = 1;
	}

	 public Character(String nameIn, int levelIn)
	 {
		 name = nameIn;
		 level = levelIn;
		 moveSet = new ArrayList<>();
		 loadData();

		 String imagePath = removeSpaces(name + ".png").toLowerCase();
		 charImage = GameData.loadImage(imagePath);
	 }

	 public void overrideStats(int hpIn, int maxHPIn, int manaIn, int maxManaIn, int defenseIn)
	 {
		hp = hpIn;
		maxHP = maxHPIn;
		mana = manaIn;
		maxMana = maxManaIn;
		defense = defenseIn;
	 }
	 
	 /**
	  * Returns the passed in String with all spaces replaced as hyphens
	  * (-). This method will be used for Character image files.
	  **/
	 public String removeSpaces(String toEdit)
	 {
		 String result = new String();
		 
		 for(int i=0; i<toEdit.length(); i++)
		 {
			 char curr = toEdit.charAt(i);
			 
			 if(curr == ' ')
			 {
				 result += "-";
			 }
			 else
			 {
				 result += curr;
			 }
		 }
		 
		 return result;
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

		 rating = readIn.nextInt();
		 readIn.nextLine();
		 
		 description = readIn.nextLine();			//self-explanatory
		 
		 line = readIn.nextLine();					///gets the moveset
		 while(line.indexOf("|") != -1)//processes the moveset; moves separated by bars
		 {
			 moveSet.add(GameData.getDataTo(line, "|"));
			 line = GameData.dataAfter(line, "|");
		 }
		 moveSet.add(line);			//get last move after the last bar

		 scaleByLevel();
		 hp = maxHP;
		 mana = maxMana;
	 }

	 public void scaleByLevel()
	 {
		if(!(name.equals("Line") || name.equals("Quadratic") || name.equals("Cubic")))
		{
			if(level > 1)
			{
				maxHP += 5*level;
				maxMana += 3*level;
			}
		}
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

	 public void increaseLevel()
	 {
		level++;
	 }

	 /**
	  * This method changes the Character's HP
	  * by the specified value.
	  */
	 public void changeHP(int value, boolean defensePierce)
	 {
		if(value >= 0) ///heals
		{
			hp += value;

			if(hp > maxHP) ///prevents overheal
			{
				hp = maxHP;
			}
		}
		else
		{
			if(!defensePierce) ///no defense pierce
			{
				///checks if damage isn't nullifies
				if(value + defense < 0)
				{
					hp += value+defense;
				}
			}
			else ///defense pierce; deals all damage
			{
				hp += value;
			}

			if(hp < 0) ///prevents hp from going too low
			{
				hp = 0;
			}
		}
	 }

	 public void changeMana(int value)
	 {
		mana += value;

		if(mana > maxMana)
		{
			mana = maxMana;
		}

		if(mana < 0)
		{
			mana = 0;
		}
	 }

	 public void increaseMaxHP(int amount)
	 {
		maxHP += amount;
	 }

	 public void increaseMaxMana(int amount)
	 {
		maxMana += amount;
	 }

	 public void increaseDefense(int amount)
	 {
		defense += amount;
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

	 public int getMaxHP()
	 {
		return maxHP;
	 }
	 
	 /**
	  * Returns this Character's current mana.
	  **/
	 public int getMana()
	 {
		 return mana;
	 }

	 public int getMaxMana()
	 {
		return maxMana;
	 }
	 
	 /**
	  * Returns this Character's defense.
	  **/
	 public int getDefense()
	 {
		 return defense;
	 }

	 /**
	  * Returns this Character's level.
	  */
	 public int getLevel()
	 {
		return level;
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
