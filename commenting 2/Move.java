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
 
 public class Move
 {
     private String name, description; //name and description of Move
     private int manaCost;
     private int[] hp, mana;
         //effect to user, second is effect to target 
         //positive numbers indicate an increase of a stat
         //negative is the opposite
    private boolean isSpecial;
    private boolean doesAreaDamage;
     
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
        boolean found = false;		
         while(readIn.hasNext() && !found)
         {
             String line = readIn.nextLine();	//character name on this line
             line = line.trim();

             if (line.equals(name))
             {
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
                doesAreaDamage = false;
                found = true;
             }
             else if(line.equals(name + "!"))
             {
                manaCost = readIn.nextInt();
                readIn.nextLine();

                description = readIn.nextLine();

                isSpecial = true;
                doesAreaDamage = false;
                found = true;
             }
             else if(line.equals(name + "*"))
             {
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
                doesAreaDamage = true;
                found = true;
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
     public String executeMove(Character executing, Character toExecuteOn, int turn, boolean isEnemy)
     {
        String defaultReturn = executing.getName() + " used " + name + ".";
        if(!isSpecial)
        {
            executing.changeMana(-1*manaCost); ///decreases mana

            if(doesAreaDamage) ///must be an enemy
            {
                Character[] playerChars = GameData.getPlayerChars();
                for(int i=0; i<playerChars.length; i++)
                {
                    playerChars[i].changeHP(hp[1], false);
                    playerChars[i].changeMana(mana[1]);
                }

                executing.changeHP(hp[0], false);
                executing.changeMana(mana[0]);

                if(hp[1] < 0)
                {
                    GameData.setPlayersShake(true);
                }
            }
            else
            {
                executing.changeHP(hp[0], false);
                executing.changeMana(mana[0]);
                toExecuteOn.changeMana(mana[1]);
                toExecuteOn.changeHP(hp[1], false);

                if(hp[1] < 0)
                {
                    if(isEnemy)
                    {
                        GameData.setPlayerShake(true);
                    }
                    else
                    {
                        GameData.setEnemyShake(true);
                    }
                }
            }
        }
        else
        {
            if(name.equals("Square Buster"))
            {
                int defense = toExecuteOn.getDefense();
                int damage = -1 * (int)Math.pow(defense, 2);

                if(damage < -81)
                {
                    damage = -81;
                }

                executing.changeMana(-1*manaCost); ///decreases mana
                ///passes in a negative value so that it recognizes damage
                toExecuteOn.changeHP(damage, true); ///pierce

                if(damage < 0)
                    GameData.setEnemyShake(true);
                
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
                    GameData.setEnemyShake(true);
                    return "VERTEX OF FINALITY!!!";
                }
                else
                {
                    return executing.getName() + " was not balanced enough... Vertex of Finality failed.";
                }
            }
            else if(name.equals("Quadratic Growth"))
            {
                int damage = -1 * (int)Math.pow(turn/3, 2);

                if(damage < -64)
                {
                    damage = -64;
                }

                executing.changeMana(-1*manaCost); ///decreases mana by cost
                ///passes in a negative value so that it recognizes damage
                toExecuteOn.changeHP(damage, false); ///no pierce

                if(damage < 0)
                    GameData.setEnemyShake(true);

                return "Quadratic Growth! Dealt " + damage + " damage!";
            }
            else if(name.equals("Perfect Square"))
            {
                int playerHP = executing.getHP();
                int playerMana = executing.getMana();
                int damage = -25;

                String toReturn = executing.getName() + " did not find balance... Dealt " + damage + " damage.";

                if(playerHP == playerMana)
                {
                    damage = -100;
                    toReturn = "CRITICAL HIT! Perfect Square dealt " + damage + " damage!";
                }

                executing.changeMana(-1*manaCost);

                toExecuteOn.changeHP(damage, true);

                if(damage < 0)
                    GameData.setEnemyShake(true);

                return toReturn;
            }
            else if(name.equals("Slope Breaker"))
            {
                int enemyHP = toExecuteOn.getHP();
                int damage = -20;
                String toReturn = "Slope Breaker! Dealt" + damage + " damage!";

                executing.changeMana(-1*manaCost);

                if(enemyHP % 5 == 0)
                {
                    damage *= 2;
                    toReturn = "Critical hit! Slope Breaker dealt " + damage + " damage!";
                }

                toExecuteOn.changeHP(damage, true);
                GameData.setEnemyShake(true);

                return toReturn;
            }
            else if(name.equals("Strike of Tangency"))
            {
                int maxMana = executing.getMaxMana();
                int enemyHP = toExecuteOn.getHP();
                int enemyMax = toExecuteOn.getMaxHP();
                int hpPercentage = (int)(100 * (double)enemyHP / enemyMax);
                int damage = -5;
                String toReturn = executing.getName() + " did not attack at the right time... Dealt " + damage + " damage.";

                executing.changeMana(-1*maxMana); ///drains all mana

                if(hpPercentage % 25 == 0 && hpPercentage != 100)
                {
                    damage = -80;
                    toReturn = "CRITICAL HIT! Strike of Tangency dealt " + damage + " damage!";
                }

                toExecuteOn.changeHP(damage, true);
                GameData.setEnemyShake(true);

                return toReturn;
            }
            else if(name.equals("Curve Flip"))
            {
                int enemyEffect = GameData.getRandom(1, 3);
                int damage;
                String toReturn;

                executing.changeMana(-1*manaCost);

                if(enemyEffect > 1)
                {
                    damage = GameData.getRandom(-20, -15);
                    toReturn = executing.getName() + " flipped the curve! Dealt " + damage + " damage!";
                }
                else
                {
                    damage = GameData.getRandom(15, 10);
                    toReturn = "Curve Flip backfired... Healed " + damage + " HP to the enemy.";
                }

                toExecuteOn.changeHP(damage, false);

                if(damage < 0)
                    GameData.setEnemyShake(true);

                return toReturn;
            }
            else if(name.equals("Triple Root Surge"))
            {
                int succesfulRolls = 0;
                int damage = 0;
                String toReturn = "BACKFIRED! Triple Root Surge dealt 30 damage to the whole team...";

                executing.changeMana(-1*manaCost);

                for(int i=1; i<=3; i++)
                {
                    int roll = GameData.getRandom(1, 10);
                    if(roll > 5)
                    {
                        damage -= 15 + (10 * succesfulRolls);
                        succesfulRolls++;
                    }
                }

                if(succesfulRolls == 0)
                {
                    Character[] playerChars = GameData.getPlayerChars();
                    for(int i=0; i<playerChars.length; i++)
                    {
                        playerChars[i].changeHP(-30, true);
                    }
                    GameData.setPlayersShake(true);
                }
                else
                {
                    executing.changeHP(damage, false);
                    GameData.setEnemyShake(true);
                }

                return toReturn;
            }
            else if(name.equals("Dimensions of Possibility"))
            {
                int maxMana = executing.getMaxMana();
                int getDimension = GameData.getRandom(1, 100);
                String toReturn;

                executing.changeMana(-1*maxMana); ///drains all mana

                if(getDimension <= 45)
                {
                    toExecuteOn.changeHP(-200, true);
                    toReturn = executing.getName() + " has enacted the Dimension of Power. Dealt 200 damage to the enemy!";
                    GameData.setEnemyShake(true);
                }
                else if(getDimension <= 90)
                {
                    toReturn = executing.getName() + " has enacted the Dimension of Salvation. Healed team to max stats!";
                }
                else
                {
                    int randomMember;
                    Character[] playerChars = GameData.getPlayerChars();

                    do
                    {
                        randomMember = GameData.getRandom(0, 2);
                    }
                    while(playerChars[randomMember].isDefeated());

                    for(int i=0; i<playerChars.length; i++)
                    {
                        playerChars[i].changeHP(-50, true);

                        if(i == randomMember)
                        {
                            playerChars[i].changeMana(-25);
                        }
                    }
                    toReturn = executing.getName() + " has enacted the Dimension of Agony. Dealt 50 damage to the whole team... " +
                        playerChars[randomMember].getName() + " lost 25 mana.";
                    GameData.setPlayersShake(true);
                }

                return toReturn;
            }
            else if(name.equals("Pentaforce"))
            {
                int damage = 0;
                int succesfulRolls = 0;

                for(int i=1; i<=5; i++)
                {
                    int roll = GameData.getRandom(1, 10);
                    if(roll > 5)
                    {
                        damage -= 5 + (5 * succesfulRolls);
                        succesfulRolls++;
                    }
                }

                toExecuteOn.changeHP(damage, false);
                if(damage < 0)
                    GameData.setEnemyShake(true);

                return executing.getName() + " rolled " + succesfulRolls + " successful rolls! Pentaforce dealt " + 
                    damage + " damage.";
            }
            else if(name.equals("Hex Burst"))
            {
                String toReturn = executing.getName() + " succesfully used Hex Burst! Dealt "
                    + "40 damage to the whole team ";
                Character[] playerChars = GameData.getPlayerChars();

                int randomMember;
                do
                {
                    randomMember = GameData.getRandom(0, 2);
                }
                while(playerChars[randomMember].isDefeated());

                for(int i=0; i<playerChars.length; i++)
                {
                    playerChars[i].changeHP(-40, true);
                    if(i == randomMember)
                    {
                        playerChars[i].changeMana(-20);
                        toReturn += "and stole 20 mana from " + playerChars[i].getName() +
                            "...";
                    }
                }

                GameData.setPlayersShake(true);

                int randomChance = GameData.getRandom(1, 100);
                if(randomChance < 6)
                {
                    executing.changeHP(-60, true);
                    GameData.setEnemyShake(true);
                    toReturn += " but it backfired! Dealt 60 damage to itself!";
                }

                return toReturn;
            }
            else if(name.equals("Perfect Symmetry"))
            {
                int hp = executing.getHP();
                int mana = executing.getMana();
                int damage = 0;
                String toReturn = executing.getName() + " did not achieve symmetry... " +
                    "Perfect Symmetry failed.";

                executing.changeMana(-1*manaCost);

                if(hp == mana)
                {
                    damage = -1*toExecuteOn.getMaxHP();
                    toReturn = executing.getName() + " has achieved perfect symmetry... " +
                        "Defeated " + toExecuteOn.getName() + " instantly.";
                }

                toExecuteOn.changeHP(damage, true);

                return toReturn;
            }
        }

        return defaultReturn;
     }
 }
