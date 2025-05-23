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
     private int manaCost;
     private int[] hp, mana;
         //effect to user, second is effect to enemy 
         //positive numbers indicate an increase of a stat
         //negative is the opposite
    private boolean isSpecial;
     
     public Move(String nameIn)
     {
         name = nameIn;							//take in the move name
         hp = new int[2];
         mana = new int[2];
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

             if (line.equals(name))
             {
                String specialCheck = line;

                manaCost = readIn.nextInt();
                readIn.nextLine();

                for(int i = 0; i < hp.length; i++)//2 times; once for user and once for enemy
                {
                    line = readIn.nextLine();			//character damage data on this line
                    hp[i] = getRandomRange(line);
                    
                    line = readIn.nextLine();		//character mana data on this line
                    mana[i] = getRandomRange(line);
                }
                
                description = readIn.nextLine();  

                isSpecial = false;
             }
             else if(line.equals(name + "!"))
             {
                manaCost = readIn.nextInt();
                readIn.nextLine();

                description = readIn.nextLine();
                isSpecial = true;
             }
         }
         
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

     /**
      * Returns this Move's mana cost.
      */
     public int getCost()
     {
        return manaCost;
     }

     /**
      * This method assumes that a mana check has already been done.
      */
     public String executeMove(Character executing, Character toExecuteOn, int turn)
     {
        String defaultReturn = executing.getName() + " used " + name + ".";
        if(!isSpecial)
        {
            executing.changeHP(hp[0], false);
            toExecuteOn.changeHP(hp[1], false);
            executing.changeMana(mana[0]);
            toExecuteOn.changeMana(mana[1]);
        }
        else
        {
            if(name.equals("Square Buster"))
            {
                int defense = toExecuteOn.getDefense();
                int damage = (int)Math.pow(defense, 2);

                executing.changeMana(-1*manaCost); ///decreases mana
                ///passes in a negative value so that it recognizes damage
                toExecuteOn.changeHP(-1*damage, true); ///pierce
                
                return "Square Buster! Dealt " + defense + "^2 = " + damage + " damage!";
            }
            else if(name.equals("Vertex of Finality"))
            {
                int hp = executing.getHP();
                int half = executing.getMaxHP() / 2;
                int maxMana = executing.getMaxMana();

                executing.changeMana(-1*maxMana); ///drains all mana

                if(hp == half)
                {
                    toExecuteOn.changeHP(-1*toExecuteOn.getHP(), true); ///OVERKILL!
                    return "VERTEX OF FINALITY!!!";
                }
                else
                {
                    return executing.getName() + " was not balanced enough... Vertex of Finality failed.";
                }
            }
            else if(name.equals("Quadratic Growth"))
            {
                int damage = (int)Math.pow(turn/3, 2);

                if(damage > 64)
                {
                    damage = 64;
                }

                executing.changeMana(-1*manaCost);
                ///passes in a negative value so that it recognizes damage
                toExecuteOn.changeHP(-1*damage, false); ///no pierce
            }
        }

        return defaultReturn;
     }
 }
