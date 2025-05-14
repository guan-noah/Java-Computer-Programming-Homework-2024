//BuckleMyShoe.java
/* Noah Guan
 * 10-30-2024
 * Per.6 Java w/ Mr. Yu
 * BuckleMyShoe.java
 * Program #25
 * This program prints a line of the poem below based on the user entry 
 * 		of a number: 
 * 	Poem is 
 * 	1, 2, buckle my shoe, 
 * 	3, 4, shut the door, 
 * 	5, 6, pick up sticks 
 * 	7, 8, lay them straight, 
 * 	9, 10, big fat hen! 
 * Working on: if - else statements 
 * Testing: Only integers between 1 & 10 (inclusive) should give a line, 
 * 		everything else gives an error message. 
 * Testing Plan: try 1-10, and then try -2, 14 (our of expected range) 
 * Pseudocode: not needed; this is a typealong in class
 * Mr. Yu's Pseudocode: 
 * FV - line
 * 
 * main 
 * 		create an instance of class, use it to call findIt
 * 
 * findIt
 * 		D&I var byte - input num
 * 		var = call getInput();
 * 		call decideWhich(pass in var)
 * 		call print(line);
 * 
 * getInput
 * 		Welcome/prompt
 * 		D&I Scanner
 * 		read input
 * 		return input
 * 
 * print(String anyString)
 * 		print the line that we give it 
 */
import java.util.Scanner;

public class BuckleMyShoe
{
	protected String line;//makes a new field variable called line to print out a String
	public BuckleMyShoe()//constructor
	{
		line = new String("");//initializes the field variable
	}
	public static void main(String[] args)
	{
		BuckleMyShoe bms = new BuckleMyShoe();//create a new instance of BuckleMyShoe
		bms.findIt();//call the method findIt from the instance 
	}
	public void findIt()
	{
		byte var = getInput();//create a new local variable and set it 
								//to the return of method getInput();
		decideWhich(var);//run method decideWhich
		print(line);//pass in var to print method 
	}
	public byte getInput()
	{
		Scanner keyboard = new Scanner(System.in);//make a new instance of Scanner
		System.out.println("\n\n\n\tWelcome to BuckleMyShoe.java!");//intro + 3 new lines
		System.out.println("\nPlease input the line of the poem that you " +
			"would like to input: ");//prompting user 
		byte userInput = keyboard.nextByte();//D&I variable userInput to 
											//the user's input
		return userInput;//return this input
	}
	public void decideWhich(byte num)//decideWhich method header, 
									//takes in user input and changes field variable 
									//"line" based on it 
	{
		if (num == 1 || num == 2)//if user input is 1 or 2, 
								//the field variable changes accordingly
		{
			line = "1, 2, Buckle my shoe, ";
		}
		else if (num == 3 || num == 4)//if user input is 1 or 2, 
								//the field variable changes accordingly
		{
			line = "3, 4, Shut the door, ";
		}
		else if (num == 5 || num == 6)//if user input is 1 or 2, 
								//the field variable changes accordingly
		{
			line = "5, 6, Pick up sticks, ";
		}
		else if (num == 7 || num == 8)//if user input is 1 or 2, 
								//the field variable changes accordingly
		{
			line = "7, 8, lay them straight, ";
		}
		else if (num == 9 || num == 10)//if user input is 1 or 2, 
								//the field variable changes accordingly
		{
			line = "9, 10, big fat hen! ";
		}
		else//if user input isn't 1-10,
			//I change the field variable to a nice error message for the user
		{
			line = "That number is not within 1-10. Please input another valid number.";
		}
	}
	public void print(String printOut)//print out whatever String the program gives it
	{
		System.out.println(printOut);//this prints out whatever the program gives it
		System.out.println("\n\n\n");//this is our 3 new lines to end the program
	}
	
}
