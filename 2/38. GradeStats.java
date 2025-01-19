//GradeStats.java
/* Noah Guan
 * 01-15-2025
 * Per. 6 Java w/ Mr. Yu
 * GradeStats.java
 * Program #38
 * Pseudocode: 
main 

calculateIt //essentially the run method (diff.name)

calcScoresUnder75Perc

max(int[] scores, int fINI) //fINI=firstIndexNumIn, assumes second number is the next indexNum 
    if(fINI == scores.length-1)
        return Math.max(firstScore, secondScore); //the max num of index and num after index 
min(int[] scores, int fINI) //same logic 
    if(fINI == scores.length-1)
        return fINI;
    else 
        return Math.min(fINI, secondScore); //the min (same logic) 
avg

orderNums

median 

Sample	run.		User	input	in	bold.
< prompt	info – make	it	clear!	>
Type	in	the	score.		Type	“Quit”	to	end	the	program	-->		95
Type	in	the	score.		Type	“Quit”	to	end	the	program	-->		65
Type	in	the	score.		Type	“Quit”	to	end	the	program	-->		70
Type	in	the	score.		Type	“Quit”	to	end	the	program	-->		78
Type	in	the	score.		Type	“Quit”	to	end	the	program	-->		85
Type	in	the	score.		Type	“Quit”	to	end	the	program	-->		77
Type	in	the	score.		Type	“Quit”	to	end	the	program	-->		QuiT
Here	is	the	data	you	entered:
Student	1’s	score:		 95
Student	2’s	score:		 65
Student	3’s	score:		 70
Student	4’s	score:		 78
Student	5’s	score:		 85
Student	6’s	score:		 77
There	were	2	students	who	scored	below	75%:		student	2,	student	3.
Number	of	scores: 6
Minimum: 65
Maximum: 95
Average: 78.3
Median: 77.5

*/
public class GradeStats
{
    public GradeStats()
    {
        //nothing
    }
    public static void main(String args[])
    {
        GradeStats gs = new GradeStats();
        gs.calculateIt("GradeStats", "takes in your grades and enters them into an array." + 
                      "\n\tThen, it will output the number of students who scored below 75%, " + 
                       "the number of scores, the minimum score, the maximum score, the average score, and the median score.");
    }
    public void calculateIt
    
}
