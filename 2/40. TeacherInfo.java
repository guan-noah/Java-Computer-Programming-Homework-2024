/* readFile early pseudocode 
String indicator = fileInput.next().trim();					//get label in header 
String nextWord = fileInput.next().trim();					//get actual info
if (indicator.equalsIgnoreCase("Teacher:"))
	teacherData[1] = nextWord;								//get the teacher name 
else if (indicator.equalsIgnoreCase("Class:"))
{
	while(nextWord.charAt(nextWord.length()-1) != ':')
	{
		String className = fileInput.next();
		teacherData[2] = nextWord.substring(0, indexOf('-'));//get everything before the dash
	}
}
else if (indicator.equalsIgnoreCase("Scores:")
{
    
    //continue scores analysis (enter scores)
    if(fileInput.hasNextDouble())
        nextScore = fileInput.nextDouble();
    else
        inBlock = false;
}

inBlock = ();

general readFile pseudocode: 
1. break up file in groups of header+body 
    a. header = all data between "teacher: " and "scores: ", only need data in teacher: and class:
2. in each header (getHeaderInfo): 
    a. getTeacherName() 
    b. getClassName()
    c. getClassNumber()
3. in each body, pull out data (getBodyInfo): 
    a. get scores count (getScores)
    b. keep going until next header starts 


*/
import java.io.File;
import java.io.FileNotFoundException; 
import java.io.PrintWriter;
import java.io.IOException; 
import java.util.Scanner;

public class TeacherInfo
{
	private int[] grades, scores;
	private String[] teacherData;
	private Scanner fileInput;
	private PrintWriter fileOutput;
	private String inFileName, outFileName;								//for reading the file and for writing to file 
	public TeacherInfo()
	{
		grades = new int[5];
		scores = new int[101];
		teacherData = new String[3];
		//fileInput = new Scanner();									//simply reminders for myself to 
		//fileOutput = new PrintWriter(fileName);						//remember to instantiate it later
		inFileName = "";
		outFileName = "";												//instantiate to inFileName-results.txt
	}
	public static void main(String args[])
	{
		TeacherInfo ti = new TeacherInfo();
		ti.fakeMain();
	}
	public void fakeMain()
	{
		do
		{
			inFileName = getInput("next", "Please enter the name of the " + 
				"teacher’s file including the extension");				//initialize inFile name
			//move initialization of file in here 
			File inFile = new File(inFileName);							//d&i input file 
			try 														//try: fileInput = new Scanner(inFile);
            {
                fileInput = new Scanner(inFile);						//initializing fileInput 
            }
            catch(FileNotFoundException)								//catch: FileNotFoundException; System.err.println(); System.exit(1);
            {
                System.err.println("Cannot find your file.");
                System.exit(1);
            }
			if (!(inFileName.endsWith(".txt"))) //and if we made it this far (can actually read the file) then we move on
			{
				System.out.println("Please enter a valid file name.");
			}
		}
		while(inFileName.endsWith(".txt");								//keep going until we can move on
		outFileName = inFileName.substring(0, indexOf(".txt")) + 
			"-results.txt";												//initialize outFile name to inFile name substringed 
		
		
		File outFile = new File(outFileName);                           //d&i output file
		//try: fileOutput = new PrintWriter(outFile);
		//catch: IOException; System.err.println(); System.exit(2)
		try
		{
            fileOutput = new PrintWriter(outFile);
		}
		catch(IOException)
		{
            System.err.println("");
            System.exit(2);
		}
		
		//outFileName = inFileName.substring(0, inFileName.indexOf('.txt') + "-results.txt";
		//call readFile
		//call outputData
	}
	/*
1. break up file in groups of header+body 
    a. header = all data between "teacher: " and "scores: ", only need data in teacher: and class:
2. in each header (getHeaderInfo): 
    a. getTeacherName() 
    b. getClassName()
    c. getClassNumber()
3. in each body, pull out data (getBodyInfo): 
    a. get scores count (getScores)
    b. keep going until next header starts 
    */
    //huge method group 1 (also method group 1)
	public void readFile()
	{
		//break up file in groups of header + body 
			//get all blocks of info using header as separation 
		//course header = look for defining text (special marks in header): a colon or smth
		//use next() to get words avoiding whitespace 
		
		String firstWord = fileInput.next(), nextWord = fileInput.next();       //for easier computing 
		boolean inBlock = true;                                                 //need to edit so it actually does something
		inBlock = (/*is after "teacher:" and nextWord is not "teacher:"*/);
            //when you see "teacher:", you start it. when you see "scores:" 
            //(end of the header), you stop it, marking end of header, then start it again. 
		while (inBlock && fileInput.hasNext())		                            //while inBlock and !endOfFile
		{
            nextWord = fileInput.next()
            inBlock = (firstWord.equalsIgnoreCase("Teacher:") && 
                !nextWord.equalsIgnoreCase("Scores:"));
            if(/*this is the class we want*/)
            {
                nextWord = getHeaderInfo();
                nextWord = getScores();
            }
		}
		//once done with one block, keep checking until end of file 
	}
	//method group 2 
	public void getHeaderInfo(String nextWord)
	{
        
        //read through file until teacher name, course name, and course number 
		//if no course, print no course 
	}
	
	//method group 3 & 3b
	    //we assume at this point that the reader is right after "scores:"
	public void getScores(nextWord)
	{
	    //use nextDouble() to get scores, format it, and input into file 
	    while(/*is after "scores:" and nextWord != "teacher:"*/)                         //function for method group 3b 
	    {
	        int nextScore = Integer.parseInt(("" + 
    	        fileInput.nextDouble()).substring(0, indexOf('.')));
    	    scores[nextScore]++;
            convertToGrades(nextScore);
	    }
	}
	//method group 3a
	//stores in grades[] array 
	public void convertToGrades(int nextScoreIn)
	{
        String grade = "";
        if (nextScoreIn >= 90)                                              //A
            grade[0]++;
        else if(nextScoreIn >= 80)                                          //B
            grade[1]++;
        else if(nextScoreIn >= 70)                                          //C
            grade[2]++;
        else if(nextScoreIn >= 60)                                          //D
            grade[3]++;
        else                                                                //F
            grade[4]++;
    }
	
	
	//misc. util method 1
	public String getNextItem()											//assuming next item is after the word with a colon at the end 
	{
		String output = "";
		String word = fileInput.next();
		if (word.charAt(word.length()-1) != ':')
		{
			output = output + getNextItem();
		}
		return output;                                                  //if has a colon at the end, returns next word 
	}
	//misc. util method 2
	public boolean isInBetween(String word, String start, String end)
	{
        String fileToString = "", nextWord = "";
        //start copying file to string when nextWord = start, then stop when nextWord = end
        while (!nextWord.equalsIgnoreCase(end))
        {
            nextWord = fileInput.next();
            if(nextWord.equalsIgnoreCase)
                fileToString.concat(nextWord + " ");
        }
        
        //within fileToString, do String methods and return result 
        return ((fileToString.indexOf(start) < fileToString.indexOf(word)) && 
            (fileToString.indexOf(word) < fileToString.indexOf(end)));
            //of course, given that the index of starts at the starting word and not a duplicate of it
	}
	
	///huge method group 2
	public void outputData()
	{
		//get class info 
		
		//for loop (all printing): 
		//1. call toBoth(classInfo)
			//sift through class info array; print out to both 
		//2. call toTerminal(scores)
			//print scores in format1
		//3. call toFile(score "graph") 
			//print scores in format2
		//4. call toBoth(frequency table)
		    //
	}
