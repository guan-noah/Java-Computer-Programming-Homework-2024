/**
 * Krish Kumar
 * Period 6
 * Character.java
 * This class represents a character (being the player or an enemy)
 * in the game.
 **/

 ///import libraries
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
	 private int level; ///level, which will scale enemy stats
	 
	/**
	 * Null character so that the game doesn't throw an error if
	 * the player has not selected a character yet.
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
		level = 1;
	}

	///Creates a new Character with the specified name and level.
	 public Character(String nameIn, int levelIn)
	 {
		 name = nameIn;
		 level = levelIn;
		 moveSet = new ArrayList<>();

		 loadData(); ///loads the character data

		 ///gets the character image if applicable
		 String imagePath = removeSpaces(name + ".png").toLowerCase();
		 charImage = GameData.loadImage(imagePath);
	 }

	 /**
	  * Manually overrides the stats of this Character. This method is
	  * only used when loading a save.
	  **/
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
		 String result = "";
		 
		 ///iterates through the String and replaces spaces with hyphens
		 for(int i=0; i<toEdit.length(); i++)
		 {
			 char curr = toEdit.charAt(i);
			 
			 if(curr == ' ') ///replaces space with hyphen
			 {
				 result += "-";
			 }
			 else ///if not a space, adds character as normal
			 {
				 result += curr;
			 }
		 }
		 
		 return result;
	 }
 
	 /**
	  * Attempts to access the "characters.txt" file and load the data of the
	  * specified Character represented by this instance.
	  **/
	 public void loadData()
	 {
		 String fileName = "characters.txt"; ///file to read from
		 File characterFile = new File(fileName);
		 Scanner read = null;
		 
		 try ///atempts to read from the file
		 {
			 read = new Scanner(characterFile);
			 cacheData(read); ///gets the actual data
		 }
		 catch(FileNotFoundException e) ///if file is not found (which shouldn't happen)
		 {
			 System.err.printf("Error: Could not locate file \"%s\".", fileName);
		 }
	 }
	 
	 ///Actually gets the data from the "characters.txt" file.
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
		 
		 line = readIn.nextLine(); //character defense data on this line
		 defense = getRandomRange(line);
		 
		 description = readIn.nextLine();			//self-explanatory
		 
		 ///gets and processes the moveset; moves are separated by bard (|)
		 line = readIn.nextLine();		   ///gets the moveset line
		 while(line.indexOf("|") != -1) //processes the moveset
		 {
			 moveSet.add(GameData.getDataTo(line, "|"));
			 line = GameData.dataAfter(line, "|");
		 }
		 moveSet.add(line);			//get last move after the last bar

		 scaleByLevel(); ///scales the stats by level
		 hp = maxHP;
		 mana = maxMana;
	 }

	 /**
	  * Scales the Character's stats by its level. This does not apply to player
	  * characters.
	  **/
	 public void scaleByLevel()
	 {
		///if not a player character, scale stats by level
		if(!(name.equals("Line") || name.equals("Quadratic") || name.equals("Cubic")))
		{
			if(level > 1) ///if level is greater than 1, scales stats
			{
				maxHP += 5*level; ///scales max HP
				maxMana += 3*level; ///scales max mana
			}
		}
	 }

	 /**
	  * Parses the random range from the specified line in the
	  * "characters.txt" file. The line should be in the format
	  * "low..high", where low and high are integers.

	  * For example, "10..20" would return a random integer between
	  * 10 and 20 inclusive.
	  */
	 public int getRandomRange(String lineIn)
	 {
		 int low = Integer.parseInt(GameData.getDataTo(lineIn, ".."));
		 int high = Integer.parseInt(GameData.dataAfter(lineIn, ".."));
		 return GameData.getRandom(low, high);
	 }
	 
	 ///If this Character is defeated (HP < 1), returns true.
	 public boolean isDefeated()
	 {
		 return hp < 1;
	 }

	 ///Increases this Character's level by 1. This is used for leveling up.
	 public void increaseLevel()
	 {
		level++;
	 }

	 /**
	  * Changes this Character's HP by the specified value.
	  * If the value is positive, the Character is healed.
	  * If the value is negative, the Character takes damage.
	  * 
	  * Additionally, if defensePierce is true, the Character's defense
	  * is ignored, and the Character takes all damage.
	  **/
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
		else ///deals damage
		{
			if(!defensePierce) ///no defense pierce
			{
				///checks if damage isn't nullified
				if(value + defense < 0)
				{
					hp += value+defense; ///deals new damage
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

	 /**
	  * Changes this Character's mana by the specified value.
	  * If the value is positive, the Character gains mana.
	  * If the value is negative, the Character loses mana.
	  **/
	 public void changeMana(int value)
	 {
		mana += value; ///changes mana by value

		if(mana > maxMana) ///prevents mana from going over max
		{
			mana = maxMana;
		}

		if(mana < 0) ///prevents mana from going below 0
		{
			mana = 0;
		}
	 }

	/**
	 * Increases this Character's max HP by the specified amount.
	 * This is used for leveling up.
	 **/
	 public void increaseMaxHP(int amount)
	 {
		maxHP += amount;
	 }


	 /**
	  * Increases this Character's max mana by the specified amount.
	  * This is used for leveling up.
	  **/
	 public void increaseMaxMana(int amount)
	 {
		maxMana += amount;
	 }

	 /**
	  * Increases this Character's defense by the specified amount.
	  * This is used for leveling up.
	  **/
	 public void increaseDefense(int amount)
	 {
		defense += amount;
	 }

	 ///Returns this Character's name.
	 public String getName()
	 {
		 return name;
	 }

	 /**
	  * Returns this Character's current hitpoints (HP).
	  * HP is the Character's health.
	  **/
	 public int getHP()
	 {
		 return hp;
	 }

	 /**
	  * Returns this Character's maximum hitpoints (HP).
	  **/
	 public int getMaxHP()
	 {
		return maxHP;
	 }
	 
	 /**
	  * Returns this Character's current mana.
	  * Mana is used to perform moves that require it.
	  **/
	 public int getMana()
	 {
		 return mana;
	 }

	 /**
	  * Returns this Character's maximum mana.
	  **/
	 public int getMaxMana()
	 {
		return maxMana;
	 }
	 
	 /**
	  * Returns this Character's defense.
	  * Defense is used to reduce damage taken from attacks.
	  **/
	 public int getDefense()
	 {
		 return defense;
	 }

	 /**
	  * Returns this Character's level.
	  * The level is used to scale enemy stats, UNLESS this
	  * Character is a player character.
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
 }
