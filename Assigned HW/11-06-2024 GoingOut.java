//GoingOut.java
/* Noah Guan
 * Finished 11/12/2024 
 * Homework for 11/6/2024 
 * Part of the If/Else Exam 
*/
import java.util.Scanner;
public class GoingOut 
{
    private int randNum;
    private double money;
    private String decision;
    private Scanner keyboard;
    public GoingOut()
    {
        randNum = 0;
        money = 0.0;
        decision = new String("");
        keyboard = new Scanner(System.in);
    }
    public static void main(String[] args) 
    {
        System.out.println("\n\n\n");
        GoingOut go = new GoingOut();
        go.decide();
        go.printOut();
        System.out.println("\n\n\n");
    }
    public void decide()
    {
        randNum = (int)(Math.random()*3+3);
        System.out.print("How much money do you have?\t");
        money = keyboard.nextDouble();
        if (randNum == 3)
        {
            if (money <= 15.0)
                decision = "bowl at Homestead Lanes";
            else
                decision = "bowl at Bowlmor";
        }
        else if (randNum == 4)
        {
            if (money <= 15.0)
                decision = "dine at Tam Café";
            else
                decision = "dine at Elephant Bar";
        }
        else if (randNum == 5)
        {
            decision = "stay in and watch TV";
        }
        decisionMaking(3, "bowl at Homestead Lanes", "bowl at Bowlmor");
        decisionMaking(4, "dine at Tam Café", "dine at Elephant Bar");
        decisionMaking(5, "stay in and watch TV", "stay in and watch TV");
    }
    public void printOut()
    {
        System.out.println("Based on our random number " + randNum + " and the $" + money + " you have, let's " + decision + "! ");
    }
    public void decisionMaking(int randNumDefIn, String decisionIn1, String decisionIn2)
    //1st input is the randNum that we set it to, 2nd is the "poor" ($15 or less) option, 3rd is the "rich" (more than $15) option
    {
        if (randNum == randNumDefIn)
        {
            if (money <= 15.0)
                decision = decisionIn1;
            else
                decision = decisionIn2;
        }
    }
}
