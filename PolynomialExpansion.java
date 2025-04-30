/* PolynomialExpansion.java
Vars: 
1. first num (FV)
        in data type String 
2. second num (FV)
        in data type String 
3. power num 1
        in data type int 
4. power num 2
        in data type int
5. wanted term 
        in data type int 
6. question type 1 
        //is it "find indicated term" or is it "full polynomial expansion"
        in data type String 
7. question type 2
        //if it's "find indicated term"
            //is it in form "3rd", form "middle", or form "middle2"
        //else 
            //print polynomialExpansion 

variable notes: 
    nums accepted are in fraction form (do x/1 if it's an int)
    NEVER USE DOUBLES (always use fraction form)
    edge cases (treat as a variable (e.g. x and y), not as int): 
        imaginary #s
        irrationals
    

INPUT (3 or 2 parts: 1. query1, query and polynomial OR 2. query2, polynomial): 
1. query1 (given )
    1: wanted term #
        in data type String 
    2: middle 
        in data type String 
    3: middle2
        in data type String 
    (always given): 
        polynomial in form (term1 +/- term2)^power
            term defined as (in between '(' and ' ') or (in between ' ' and ')'
        semicolon (;) to separate query and polynomial 
2. query2 (given "")
    1. 

OUTPUT: 
variable in form (finalCoef)[(var1)^power1][(var2)^power2]
    finalCoef = fraction form 
    var1 & var2 = fraction form
    power = int 

MATH FORMULAS: 
 - general term formula 
    pCr*(a^p-r)*(b^r)
        where p is the polynomial term, n is the term you want it at [CAUTION: r may change based on question type], and a and b are the first and second numbers, respectively 
 - when you give term #
    r = n-1 
 - 
    r = 0.5p 
 - 
    r1 = 0.5(p-1)
    r2 = 0.5(p+1)

CLASSES: 
 - Fraction class (cite APCSA Barron's book for the inspiration)

METHODS (from run method): 
    welcomePrompt()
        //give welcome and instructions 
    acceptUserInput()
        //for accepting input variables 
        //use `ArrayList <Object> var = new ArrayList<object>[] (var1, var2)` to make a new array list that can hold multiple data types AND add more to it (slower)
        //OR `Object[] var = new Object[] {var1, var2}`
        //make sure that you typecast back to Double/Integer so you can read it 
    
    calculate_finalCoef()
        //for calculating the coefficient in the front 
        contains: 
            coefficient from nCr 
            coefficient from var1 (if any)
            coefficient from var2
    
    print(String finalCoefIn, String var1, String var2, double power1, double power2)
        //System.out.println("(" + finalCoefIn + ")[(" + var1 + ")^" + (power1) + "][(" + var2 + ")^ + (power2) + "]")
            //prints (finalCoef)[(var1)^power1][(var2)^power2]
    nCr(double n, double r)
        //to use as another Math method 
        //returns the value of nCr 
        (  (fact(n))  /  ( fact(r) ) * ( fact(n-r) )  )
    fact(int n)
        if n <= 1
            return 1;
        else 
            return n*fact(n-1);
    formatDataTypes(Object dataTypeIn)
        //maybe don't need this method (?)
        //maybe just to test stuff 
    convertToFractionForm
    convertToDouble

CASES (as of 12/15/2024, 2:30 P.M.)
1. 




Welcome to PolynomialExpansion.java.
This program either prints one term of the polynomial (to the power of x) you give or prints the whole polynomial in expanded form. 

Limits: 
	You are limited to one polynomial in form (term1 +/- term2) ^ power.
	Each term in the polyomial must be in x/y form.
	The power has to be an integer.
	(just please try not to break the program and use it for your benefit; if you need help please contact Noah at nguan651@student.fuhsd.org.)

Would you like to find an indicated term of your choosing or a full polynomial expansion? [find indicated term/full polynomial expansion]
full polynomial expansion

Incorrect format. Please try again. 
Would you like to find an indicated term of your choosing or a full polynomial expansion? [find indicated term/full polynomial expansion]
find indicated term
What is your polynomial? (Remember: format polynomial in form (term1 +/- term2) ^ power): 	
(2a + 8b) ^ 8
What is your polynomial? (Remember: format polynomial in form (term1 +/- term2) ^ power): 	
Incorrect format. Please try again. 
What is your polynomial? (Remember: format polynomial in form (term1 +/- term2) ^ power): 	
Incorrect format. Please try again. 
What is your polynomial? (Remember: format polynomial in form (term1 +/- term2) ^ power): 	
2. 
*/
//import java.util.Integer;
import java.util.Date;
import java.util.*;
class Main 
{
    Scanner keyboard;
        //cannot put a string scanner here bec. string scanner requires an already-d&i'd string object before it to work 
    public Main()
    {
        keyboard = new Scanner(System.in);
    }
    public static void main(String[] args) 
    {
        Main m = new Main();
        m.run();
    }
    public void run()
    {
        System.out.println("\n\n\n");
        prompt();
        String ITorPE = acceptITorPE();
            //indicated term/polynomial expansion
        Object[] userPoly = acceptUserPoly();
            //accept user polynomial input 
        
        //formatDataTypes(new String());
        System.out.println("\n\n\n");
    }
    public void prompt()
    {
        System.out.println("Welcome to PolynomialExpansion.java.");
        System.out.println("This program either prints one term of the polynomial (to the power of x) you give or prints the whole polynomial in expanded form. ");
        System.out.println("\nLimits: ");
        String[] allLimits = new String[] {"You are limited to one polynomial in form (term1 +/- term2) ^ power.", "Each term in the polyomial must be in x/y form.", "The power has to be an integer.", "(just please try not to break the program and use it for your benefit; if you need help please contact Noah at nguan651@student.fuhsd.org.)"};
        promptLimits(allLimits);
        System.out.println();
    }
    public void promptLimits(String[] allLimitsIn)
    {
        /*
        for (int i = 0; i < allLimitsIn.length; i++)
        {
            System.out.println("\t" + allLimitsIn[i]);
        }
        */
        //OR
        for (String limit: allLimitsIn)
        {
            System.out.println("\t" + limit);
        }
    }
    public String[] acceptUserPoly()
    {
        String[] userPolyParts = new String[5];
            //the starting polynomial the user gives to expand 
        String term1, plusOrMinusSign, term2, powerSign, power;
            //polynomial parts including: the uncut term1, the '+'/'-' sign, the '^' sign, and the user power (all declared here, respectively (in order))
            //term defined as (in between '(' and ' ') or (in between ' ' and ')'
        boolean correctFormat1 = false;
            //because we still want to run the while loop at this point 
        while (!correctFormat1)
        {
            System.out.println("What is your polynomial? (Remember: format polynomial in form (term1 +/- term2) ^ power): \t");
                //we would have to prompt them after declaration bec. they might answer wrong 
            term1 = keyboard.next();
                //this is uncut (includes parenthesis)
            if (term1.charAt(0) == '(')
                //if the user followed directions of formatting first term
                //&& (term1.charAt(term1.length() - 1) == ' '))
                    //we don't want this second piece of code because keyboard.next() doesn't take in the space 
            {
                //move on
                term1 = term1.substring(1, term1.length()-1);
                    //get rid of the parenthesis in the front 
                getRightAnswer: 
                    //this labels the if statement to see if the user gets the format right 
                {
                    plusOrMinusSign = keyboard.next();
                        //this should be the '+' or '-' sign 
                    if ((plusOrMinusSign.length() == 1) && 
                        ((plusOrMinusSign.charAt(0) == '+')||
                            (plusOrMinusSign.charAt(0) == '-')))
                        //if the user followed directions again (only included one character in between spaces)
                    {
                        //move on
                        term2 = keyboard.next();
                            //this is uncut (includes parenthesis)
                        if (term2.charAt((term1.length() - 1)) == ')')
                            //same logic applies to term2 as term1 
                        {
                            term2 = term2.substring(0, (term2.length()-2));
                                //get rid of the parenthesis in the back 
                            powerSign = keyboard.next();
                                //this should be be '^' sign
                            if ((powerSign.length() == 1) && 
                                (powerSign.charAt(0) == '^'))
                                //same logic as plusOrMinusSign except no plus or minus, only needs to check for char '^' 
                            {
                                power = keyboard.next();
                                    //this should be the last thing the program takes in for this prompt; it doesn't matter what they put in afterward 
                                
                                /* //couldn't quite get this code to work, even in conjunction with a method 
                                userPolyParts = {"", "", "", "", ""};
                                userPolyParts = {term1, plusOrMinusSign, term2, powerSign, power};
                                for (int i = 0, i < 5, i++)
                                {
                                    userPolyParts[i] = fillInArray(i, term1)
                                }
                                */
                                
                                userPolyParts[0] = term1;
                                userPolyParts[1] = plusOrMinusSign;
                                userPolyParts[2] = term2; 
                                userPolyParts[3] = powerSign; 
                                userPolyParts[4] = power;
                                
                                correctFormat1 = true;
                                    //now and only now may we break the while loop
                            }
                            else 
                            {
                                break getRightAnswer; 
                                    //this jumps straight down to the final else
                            }
                            //anything here on this line between these two braces shouldn't execute if the user formats input incorrectly; however, it would execute if the user does format input correctly 
                        }
                        else
                        {
                            break getRightAnswer;
                                //also jumps straight down to the final else
                        }
                        //anything here also shouldn't execute if user formats incorrectly & vice versa
                    }
                    else
                    {
                        break getRightAnswer;
                            //also jumps straight down to the final else 
                    }
                    //anything here on this line also shouldn't execute if the user formats input incorrectly & vice versa
                }
            }
            else
            {
                //notify user and restart this section of acceptUserPoly
                System.out.println("Incorrect format. Please try again. ");
            }
        }
        /* //uncomment this when method stringToInteger is ready 
            //don't bother with making stringToInteger into a primitive int; we're going to have to make it back into object Integer for the arrays 
        intTerm1 = stringToInteger(term1);
        intTerm2 = stringToInteger(term2);
        */
        
        return userPolyParts;
    }
    /* //couldn't quite get this method to work 
    public String fillInArray(int i, String fillItemIn)
    {
        userPolyParts[i] = fillItemIn;
    }
    */
    public String acceptITorPE()
    {
        ///NEXT SECTION OF METHOD 
        String userDecision = "";
            //this will get changed later, but we have to initialize it to something so java doesn't throw an error 
        boolean correctFormat2 = false;
            //reuse the same variable with the same logic 
        while (!correctFormat2)
        {
            System.out.println("Would you like to find an indicated term of your choosing or a full polynomial expansion? [find indicated term/full polynomial expansion]");
            if (keyboard.nextLine().equalsIgnoreCase("find indicated term".trim()))
                //if the user input equals "find indicated term"
            {
                userDecision = "find indicated term";
                correctFormat2 = true;
            }
            else if (keyboard.nextLine().equalsIgnoreCase("full polynomial expansion".trim()))
                //for some reason, this doesn't work but find indicated term does 
            {
                userDecision = "full polynomial expansion";
                correctFormat2 = true;
            }
            else
            {
                //notify user and restart this section of acceptITorPE 
                System.out.println("Incorrect format. Please try again. ");
            }
        }
        return userDecision;
    }
    public void formatDataTypes(Object dataTypeIn)
    {
        
    }
}
