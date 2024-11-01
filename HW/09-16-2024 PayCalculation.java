/*
Noah Guan 
9-16-2024 
P.6 Java w/ Mr. Yu 
PayCalculation.java
This program breaks down time into years to minutes. Label numbers (instead of drawing a lot of arrows_ and calculate values, plus show output int the terminal window box that follows.

Extra time? Modify this to write a methods program that breaks down dollars into 100s, 20s, 10s, 5s, 1s, quarters, dimes, nickels, and pennies (BreakDownDollars.java)
*/

import java.util.Scanner;
class BreakDownTime
{          //the code on the following line is called a field variable because it's outside the main method & accessible to all methods
    private static double questionTime;    //This equals the number of days

    public BreakDownTime()         //This is a constructor, which initializes the field variable 
    {
        questionTime = 24.674;
    }
    public static void main(String[] args)
    {
        System.out.println("\n\n\n");
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Please enter the decimal number of years you would like to convert to smaller units of time: ")
        questionTime = keyboard.nextDouble();
        System.out.println("");
        double years = 0;
        years = doubleYears();
        System.out.println(years + " years");
        double months = 0;
        months = doubleDays();
        System.out.println(months + " months");
        double weeks = 0;
        weeks = numberWeeks();
        System.out.println(weeks + " weeks");
        double days = 0;
        days = numberDays();
        System.out.println(hours + " hours");
        double minutes = 0;
        minutes = numberMinutes();
        System.out.println(minutes + " minutes");
        double seconds = 0;
        seconds = numberSeconds();
        System.out.println(seconds) + " seconds");
        System.out.println("Thank you for using BreakDownTime.java.");
        System.out.println("\n\n\n");
    }
    public static double numberYears()
    {
        double answer = 0;
        answer = questionTime;
        calRemTime(answer);
        return answer;
    }
    public static double numberMonths()
    {
        double answer = 0;
        answer = int(questionTime)*12
        calRemTime(answer/12);
        return answer;
    }
    public static double numberWeeks()
    {
        double answer = 0; 
        answer = int(questionTime)*52+1/7
        calRemTime(answer*7-1);
        return answer;
    }
    public static double numberDays()
    {
        double answer = 0;
        answer = questionTime*365
        calRemTime(answer/365);
        return answer;
    }
    public stataic double numberHours()
    {
        int answer = 0;
        answer = questionTime*24*365;
        calRemTime(answer/24/365)
        return answer;
    }
    public static double numberMinutes()
    {
        double answer = 0;
        answer = questionTime*365*24*60;
        calRemTime(answer/365/24/60);
        return answer;
    }
    public static double numberSeconds()
    {
        double answer = 0; 
        answer = questionTime*365*24*60*60;
        calRemTime(answer/365/24/60);
        return answer;
    }
    public static void calRemTime(double takeaway) //calRemTime = calculateRemainingTime
    {
        questionTime = questionTime - takeaway;
    }
