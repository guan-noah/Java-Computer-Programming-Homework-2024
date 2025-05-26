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
    private boolean isSpecial; ///if the attack requires special handling
    private boolean doesAreaDamage; ///whether or not attack does AoE
                                    ///(Area of Effect) damage
    
    //Initializes this Move.
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
         String fileName = "moves.txt"; ///file to be read
         File movesFile = new File(fileName);
         Scanner read = null;
         
         try
         {
             read = new Scanner(movesFile);
             cacheData(read); ///caches the data from "moves.txt"
         }
         catch(FileNotFoundException e)
         {
             System.err.printf("Error: Could not locate file \"%s\".", fileName);
         }
     }
     /* 
      * Helper method to loadData, improves encapsulation and actually 
      * initializes the FVs.
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

     /*
      * Gets a random number out of the passed in range,
      * formatted as "low..high". 
      */
     public int getRandomRange(String lineIn)
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

            if(doesAreaDamage) ///must be an enemy, does AoE
            {
                ///deals area damage
                Character[] playerChars = GameData.getPlayerChars();
                for(int i=0; i<playerChars.length; i++)
                {
                    playerChars[i].changeHP(hp[1], false);
                    playerChars[i].changeMana(mana[1]);
                }

                ///apply effects to enemy
                executing.changeHP(hp[0], false);
                executing.changeMana(mana[0]);

                ///shakes the player if damage is dealt
                if(hp[1] < 0)
                {
                    GameData.setPlayersShake(true);
                }
            }
            else ///single-target move
            {
                ///applies effects
                executing.changeHP(hp[0], false);
                executing.changeMana(mana[0]);
                toExecuteOn.changeMana(mana[1]);
                toExecuteOn.changeHP(hp[1], false);

                ///configurates shake
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
        else ///the following moves are specially handled moves
        {
            if(name.equals("Square Buster"))
            {
                int defense = toExecuteOn.getDefense();
                int damage = -1 * (int)Math.pow(defense, 2);

                if(damage < -81) ///caps at 9^2
                {
                    damage = -81;
                }

                executing.changeMana(-1*manaCost); ///decreases mana
                ///passes in a negative value so that it recognizes damage

                toExecuteOn.changeHP(damage, true); ///pierce

                ///shakes the enemy if damage is dealt
                if(damage < 0)
                    GameData.setEnemyShake(true);
                
                return "Square Buster! Dealt " + defense + "^2 = " + -1*damage + " damage!";
            }
            else if(name.equals("Vertex of Finality"))
            {
                int hp = executing.getHP();
                int half = executing.getMaxHP() / 2;
                int maxMana = executing.getMaxMana();

                executing.changeMana(-1*maxMana); ///drains all mana

                if(hp == half) ///success
                {
                    ///drains all enemy health
                    toExecuteOn.changeHP(-1*toExecuteOn.getHP(), true);
                    GameData.setEnemyShake(true);
                    return "VERTEX OF FINALITY!!!";
                }
                else ///failed to execute
                {
                    return executing.getName() + " was not balanced enough... Vertex of Finality failed.";
                }
            }
            else if(name.equals("Quadratic Growth"))
            {
                int damage = -1 * (int)Math.pow(turn/3, 2);

                if(damage < -64) ///caps at 8^2
                {
                    damage = -64;
                }

                executing.changeMana(-1*manaCost); ///decreases mana by cost
                toExecuteOn.changeHP(damage, false); ///no pierce

                ///shakes enemy if damage is dealt
                if(damage < 0)
                    GameData.setEnemyShake(true);

                return "Quadratic Growth! Dealt " + damage + " damage!";
            }
            else if(name.equals("Perfect Square"))
            {
                int playerHP = executing.getHP();
                int playerMana = executing.getMana();
                int damage = -25;

                String toReturn = executing.getName() + " did not find balance... Dealt " + -1*damage + " damage.";

                if(playerHP == playerMana) ///critical hit
                {
                    damage = -100;
                    toReturn = "CRITICAL HIT! Perfect Square dealt " + -1*damage + " damage!";
                }

                executing.changeMana(-1*manaCost); ///decreases mana by cost
                toExecuteOn.changeHP(damage, true); ///pierce

                ///shakes enemy if damage is dealt
                if(damage < 0)
                    GameData.setEnemyShake(true);

                return toReturn;
            }
            else if(name.equals("Slope Breaker"))
            {
                int enemyHP = toExecuteOn.getHP();
                int damage = -20;
                String toReturn = "Slope Breaker! Dealt" + -1*damage + " damage!";

                executing.changeMana(-1*manaCost); ///decreases mana by cost

                if(enemyHP % 5 == 0) ///critical hit if multiple of 5
                {
                    damage *= 2;
                    toReturn = "Critical hit! Slope Breaker dealt " + -1*damage + " damage!";
                }

                toExecuteOn.changeHP(damage, true); ///pierce
                GameData.setEnemyShake(true); ///shakes enemy

                return toReturn;
            }
            else if(name.equals("Strike of Tangency"))
            {
                int maxMana = executing.getMaxMana();
                int enemyHP = toExecuteOn.getHP();
                int enemyMax = toExecuteOn.getMaxHP();
                int hpPercentage = (int)(100 * (double)enemyHP / enemyMax);
                int damage = -5;
                String toReturn = executing.getName() + " did not attack at the right time... Dealt " + -1*damage + " damage.";

                executing.changeMana(-1*maxMana); ///drains all mana

                ///sucess if percentage is a multiple of 25 (excluding 100)
                if(hpPercentage % 25 == 0 && hpPercentage != 100)
                {
                    damage = -80;
                    toReturn = "CRITICAL HIT! Strike of Tangency dealt " + -1*damage + " damage!";
                }

                toExecuteOn.changeHP(damage, true); ///pierce
                GameData.setEnemyShake(true); ///shakes enemy

                return toReturn;
            }
            else if(name.equals("Curve Flip"))
            {
                int enemyEffect = GameData.getRandom(1, 3);
                int damage;
                String toReturn;

                executing.changeMana(-1*manaCost); ///decreases mana by cost

                if(enemyEffect > 1) ///success, deals damage to enemy
                {
                    damage = GameData.getRandom(-20, -15);
                    toReturn = executing.getName() + " flipped the curve! Dealt " + -1*damage + " damage!";
                }
                else ///backfires and heals enemy
                {
                    damage = GameData.getRandom(15, 10);
                    toReturn = "Curve Flip backfired... Healed " + damage + " HP to the enemy.";
                }

                toExecuteOn.changeHP(damage, false); ///no pierce

                ///shakes enemy if damage is dealt
                if(damage < 0)
                    GameData.setEnemyShake(true);

                return toReturn;
            }
            else if(name.equals("Triple Root Surge"))
            {
                int succesfulRolls = 0;
                int damage = 0;
                String toReturn = "BACKFIRED! Triple Root Surge dealt 30 damage to the whole team...";

                executing.changeMana(-1*manaCost); ///decreases mana by cost

                ///three 50% chance rolls, awards bonus damage for more rolls
                for(int i=1; i<=3; i++)
                {
                    int roll = GameData.getRandom(1, 10);
                    if(roll > 5)
                    {
                        damage -= 15 + (10 * succesfulRolls);
                        succesfulRolls++;
                    }
                }

                if(succesfulRolls == 0) ///all rolls failed, backfires
                {
                    ///deals AoE to entire team and shakes player team
                    Character[] playerChars = GameData.getPlayerChars();
                    for(int i=0; i<playerChars.length; i++)
                    {
                        playerChars[i].changeHP(-30, true); ///pierce
                    }
                    GameData.setPlayersShake(true);
                }
                else
                {
                    executing.changeHP(damage, false); ///no pierce
                    GameData.setEnemyShake(true); ///shakes enemy
                }

                return toReturn;
            }
            else if(name.equals("Dimensions of Possibility"))
            {
                int maxMana = executing.getMaxMana();
                int getDimension = GameData.getRandom(1, 100);
                String toReturn;

                executing.changeMana(-1*maxMana); ///drains all mana

                ///Dimension of Power: Deals 200 damage to enemy
                if(getDimension <= 45)
                {
                    toExecuteOn.changeHP(-200, true); ///pierce
                    toReturn = executing.getName() + " has enacted the Dimension of Power. " +
                        "Dealt 200 damage to the enemy!";
                    GameData.setEnemyShake(true); ///shakes enemy
                }
                ///Dimension of Salvation: Restores all team stats
                else if(getDimension <= 90)
                {
                    ///restores stats for all members
                    Character[] playerChars = GameData.getPlayerChars();    
                    for(int i=0; i<playerChars.length; i++)
                    {
                        int maxPlrHP = playerChars[i].getMaxHP();
                        int maxPlrMana = playerChars[i].getMaxMana();
                        playerChars[i].changeHP(maxPlrHP, false);
                        playerChars[i].changeMana(maxPlrMana);
                    }

                    toReturn = executing.getName() + " has enacted the Dimension of Salvation. " +
                        "Healed team to max stats!";
                }
                ///Dimension of Agony: 50 AoE damage on team, and a random
                ///team member loses 25 HP.
                else
                {
                    int randomMember;
                    Character[] playerChars = GameData.getPlayerChars();

                    ///gets random player char to lose mana
                    do
                    {
                        randomMember = GameData.getRandom(0, 2);
                    }
                    while(playerChars[randomMember].isDefeated());

                    ///deals AoE to all player characters
                    for(int i=0; i<playerChars.length; i++)
                    {
                        playerChars[i].changeHP(-50, true); ///pierce

                        if(i == randomMember) ///drains mana from chosen player
                        {
                            playerChars[i].changeMana(-25);
                        }
                    }
                    toReturn = executing.getName() + " has enacted the Dimension of Agony. " +
                        "Dealt 50 damage to the whole team... " +
                        playerChars[randomMember].getName() + " lost 25 mana.";
                    GameData.setPlayersShake(true); ///shakes all player chars
                }

                return toReturn;
            }
            else if(name.equals("Pentaforce"))
            {
                int damage = 0;
                int succesfulRolls = 0;

                executing.changeMana(-1*manaCost); ///decreases mana by cost

                ///five 50% rolls, awards bonus damage for more rolls
                for(int i=1; i<=5; i++)
                {
                    int roll = GameData.getRandom(1, 10);
                    if(roll > 5)
                    {
                        damage -= 5 + (5 * succesfulRolls);
                        succesfulRolls++;
                    }
                }

                toExecuteOn.changeHP(damage, false); ///no pierce

                ///shakes enemy is damage is dealt
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

                executing.changeMana(-1*manaCost); ///decreases mana by cost

                ///chooses a random member to steal mana from
                int randomMember;
                do
                {
                    randomMember = GameData.getRandom(0, 2);
                }
                while(playerChars[randomMember].isDefeated());

                ///deals AoE to player chars
                for(int i=0; i<playerChars.length; i++)
                {
                    playerChars[i].changeHP(-40, true);

                    if(i == randomMember) ///steals mana from chosen member
                    {
                        playerChars[i].changeMana(-20);
                        executing.changeMana(20);
                        toReturn += "and stole 20 mana from " + playerChars[i].getName() +
                            "...";
                    }
                }

                GameData.setPlayersShake(true); ///shakes players

                ///random chance on whether damage is dealt to user
                int randomChance = GameData.getRandom(1, 100);
                if(randomChance < 6) ///backfires
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

                executing.changeMana(-1*manaCost); ///decreases mana by cost

                if(hp == mana) ///success, insta-defeats targetted player char
                {
                    damage = -1*toExecuteOn.getMaxHP();
                    toReturn = executing.getName() + " has achieved perfect symmetry... " +
                        "Defeated " + toExecuteOn.getName() + " instantly.";
                }
                ///otherwise does no damage

                toExecuteOn.changeHP(damage, true); ///pierce

                return toReturn;
            }
        }

        return defaultReturn;
     }
 }
