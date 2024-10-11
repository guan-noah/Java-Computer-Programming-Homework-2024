//Noah Guan
//9-4-24 Per 6
/*
 * MathProblems.java
 * Program #4
 * This program declares and initializes variables, stores the results of an
 * expression in a variable, then prints the variable using println as well as
 * printf and Format to print a double to two places, ints and a char. 
 * 
 * Working on: Declare & Initialize variables, formatting using printf and Format
*/

public class MathProblems
{
    public static void main(String[] args)
    {
        double prob1; //this declares prob1 as a double
        prob1 = -1.0; //this initializes prob1 as -1.0 (something it can't equal in the program)
        //we could have also just used the code "double prob1 = -1.0;"

        prob1 = 5.6*17/3-49%11/2; //initializes prob1 into value I want.
        int prob2 = 16-17*5%6/3; //declares and initializes prob2 
        char prob3 = (char)(56/2+17*3-9%9); //declares and initializes prob3
        int prob4 = (int)((double)9/12*3-6.5); //declares and initializes prob4 
        double prob5 = (int)('g')+(double)('2')/7; //declares and initializes prob5 
       
        System.out.println("\n\n\n");
        System.out.println("\tMath Problems - From Aug. 20, 2024 Data Types Handout\n"); //header
        System.out.printf("1) 5.6*17/3-49%%11/2 = %-5.2f\n", prob1); //prints prob1 w/ printf
        System.out.println("\t(Made with printf.) \n"); //clarifies line was made with printf.
        System.out.println("1) 5.6*17/3-49%11/2 = " + Format.left(prob1, 5, 2)); //prints prob1 w/ Format
        System.out.println("\t(Made with Format.) \n"); //clarifies line was made with Format.
        System.out.printf("2) 16-17*5%%6/3 = %-5d\n", prob2); //prints prob2 with printf. 
        System.out.println("\t(Made with printf.) \n"); //clarifies line was made with printf.
        System.out.println("2) 16-17*5%6/3 = " + Format.left(prob2, 5)); //prints prob1 w/ Format
        System.out.println("\t(Made with Format.) \n"); //clarifies line was made with Format.
        System.out.printf("3) (char)(56/2+17*3-9%%9) = %c\n", prob3); //prints prob3 w/ printf
        System.out.println("\t(Made with printf.) \n"); //clarifies line was made with printf.
        System.out.println("3) (char)(56/2+17*3-9%9) = " + Format.left(("'" + prob3 + "'"), 5)); //prints prob1 w/ Format
        System.out.println("\t(Made with Format.) \n"); //clarifies line was made with Format.
        System.out.printf("4) (int)((double)9/12*3-6.5) = %-5d\n", prob4); //prints prob4 w/ printf
        System.out.println("\t(Made with printf.) \n"); //clarifies line was made with printf.
        System.out.println("4) (int)((double)9/12*3-6.5) = " + Format.left(prob4, 5)); //prints prob1 w/ Format
        System.out.println("\t(Made with Format.) \n"); //clarifies line was made with Format.
        System.out.printf("5) (int)('g')+(double)('2')/7 = %-5.3f\n", prob5); //prints prob5 w/ printf
        System.out.println("\t(Made with printf.) \n"); //clarifies line was made with printf.
        System.out.println("5) (int)('g')+(double)('2')/7 = " + Format.left(prob5, 5, 3)); //prints prob1 w/ Format
        System.out.println("\t(Made with Format.) \n"); //clarifies line was made with Format.
        System.out.println("\n\n\n");
    }
}
