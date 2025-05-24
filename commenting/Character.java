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
	 
	/*
	 * Null character for placeholder. should not be used in working game. 
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
	
	/*
	 * initialize character with given name and level 
	 * should be the main constructor used. 
	 */
	 public Character(String nameIn, int levelIn)
	 {
		 name = nameIn;
		 level = levelIn;
		 moveSet = new ArrayList<>();
		 loadData();

		 String imagePath = removeSpaces(name + ".png").toLowerCase();
		 charImage = GameData.loadImage(imagePath);
	 }
	/*
	 * reinitialize the character stats (change fvs to parameter inputs)
	 */
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
		 String result = "";//create string to return
		 
		 //iterate through original string, rebuilding result string char by char
		 for(int i=0; i<toEdit.length(); i++)
		 {
			 char curr = toEdit.charAt(i);//the current char
			 
			 //if the current char is a space, add a hyphen to result. 
			 if(curr == ' ')
			 {
				 result += "-";
			 }
			 else
			 {
				 //if it's any other char, simply add it to result as well.
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
		 //like Polybound.java, just the standard loading logic 
		 String fileName = "characters.txt";
		 File characterFile = new File(fileName);
		 Scanner read = null;
		 
		 try
		 {
			 read = new Scanner(characterFile);
			 //get data from file 
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
	/*
	 * This method changes the character's stats based on user's current level.
	 */
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
	/*
	 * precondition: string is in format 
	 * int1..int2 
	 * where int1 is lower and int2 is higher
	 * This method gets an int from a random range in a String. 
	 * 	 different from GameData number generator bec. that can be used for other functions
	 */
	 public int getRandomRange(String lineIn)
	 {
		 //get low number from line 
		 int low = Integer.parseInt(GameData.getDataTo(lineIn, ".."));
		 //get high number from line 
		 int high = Integer.parseInt(GameData.dataAfter(lineIn, ".."));
		 //return a random integer between the numbers 
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
	
	/*
	 * Increments the user level. 
	 */
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
	/*
	 * This method increases user mana by parameter value. 
	 * It prevents mana from going out of bounds. 
	 */
	 public void changeMana(int value)
	 {
		 //adds value to mana 
		mana += value;
		
		//cap mana to maximum mana value 
		if(mana > maxMana)
		{
			mana = maxMana;
		}
		//cap mana to 0 points 
		if(mana < 0)
		{
			mana = 0;
		}
	 }
	 
	/*
	 * This method increases maximum hitpoints by parameter amount. 
	 */
	 public void increaseMaxHP(int amount)
	 {
		maxHP += amount;
	 }
	 
	/*
	 * This method increases maximum mana by parameter amount. 
	 */
	 public void increaseMaxMana(int amount)
	 {
		maxMana += amount;
	 }
	
	/*
	 * This method increases defense by parameter amount. 
	 */
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
	
	/*
	 * Returns this Character's maximum hitpoints. 
	 */
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
	/*
	 * Returns this Character's maximum mana. 
	 */
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
	 
	 /**
	  * Returns this Character's Image.
	  **/
	 public Image getImage()
	 {
		 return charImage;
	 }
	 
	 /* debug method
	  * Prints out everything about the character. 
	  */
	 public void print()
	 {
		 System.out.println("Name: |" + name + "|");
		 System.out.println("Maximum hitpoints: |" + maxHP + "|");
		 System.out.println("Maximum mana: |" + maxMana + "|");
		 System.out.println("Defense: |" + defense + "|");
		 System.out.println("Description: |" + description + "|");
		 System.out.println("Moveset: |" + moveSet + "|");
	 }
 }
