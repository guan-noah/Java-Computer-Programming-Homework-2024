/*

Pseudocode: 
int[] readFile(int userCourseNumber) returns [continueProgram (0 for false, 1 for true), numOfCourses]
	create instance vars of fvs to store info 
	store info by line (while loop in while loop)
        first one to get next line while there is a next line; second one to shift from one indicator to the next
        search for indicators (words with ':')
            first and second indicator; anything between them is info 
            if no second indicator, the second indicator is the end of file 
	
	if the course number matches 
	{
        copy over ivs (add onto fvs)
        return [1 (true), numOfCourses]
	}
    else 
        return [0 (false), numOfCourses]

more readFile pseudocode: 

precondition: one class in. 
default: print all array info, then reset all arrays 
if accuratecourse 
    store class info 


*/
import java.io.File;
import java.io.FileNotFoundException; 
import java.io.PrintWriter;
import java.io.IOException; 
import java.util.Scanner;

class TeacherInfo
{
	private int[] grades, scores;                                               //3 required ivs 
	private String[] teacherData;
	
	private String[] fileNames;                                                 //for reading and writing to files 
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
        fileNames = new String[] {inFileName, outFileName};
	}
	public static void main(String[] args) 
	{
		TeacherInfo ti = new TeacherInfo();
	    ti.fakeMain();
	}
	public void fakeMain()
	{
		System.out.println("\n\n\nWelcome the TeacherInfo! This program will collect all of " + 
            "the information about the number of A's, B's ...etc to F's that you have in the " + 
            "course that you select.\n");                               //prompt user 
        
		makeFiles();
		
		int userCourseNumber = Integer.parseInt(utilMethod("getInput", "int", 
            "Please enter the course number for data you would like"));
		int[] receive = readFile(userCourseNumber);
		boolean continueProgram = (receive[0] == 1);
		int numOfCourses = receive[1];
		
		System.out.println("continueProgram = " + continueProgram);
		System.out.println("numOfCourses = " + numOfCourses);
		
		if(continueProgram)
		{
            outputData(numOfCourses);                                           //call outputData
		}
		else
		{
		    System.out.println("Since " + teacherData[0] + " does not teach " + 
                "course number " + userCourseNumber + ", there is no data to report.");
		}
		
		System.out.println("\n\n\n");
	}
	public void makeFiles()
	{
	    do
		{
			inFileName = utilMethod("getInput", "next", "Please enter the name of the " + 
				"teacher’s file including the extension");				        //initialize inFile name
			//move initialization of file in here 
			if (!inFileName.endsWith(".txt")) //and if we can actually read the file then we move on
			{
				System.out.println("Please enter a valid file name.");
			}
		}
		while(!inFileName.endsWith(".txt"));                                    //keep going until we can move on
		
		outFileName = inFileName.substring(0, inFileName.indexOf(".txt")) + 
			"-results.txt";												        //initialize outFile name to inFile name substringed 
		fileNames = new String[109];                                            //to save later because fileNames already used
		File inFile = new File(inFileName);								        //d&i input and output files 
		File outFile = new File(outFileName);
		
		try 															        //try: fileInput = new Scanner(inFile);
		{
			fileInput = new Scanner(inFile);							        //initializing fileInput 
		}
		catch(FileNotFoundException e)									//catch: FileNotFoundException; System.err.println(); System.exit(1);
		{
			System.err.println("ERROR: File " + inFileName + " was not found.");
			System.exit(1);
		}
		try
		{
		    fileOutput = new PrintWriter(outFile);
		}
		catch(IOException e)
		{
		    System.err.println("ERROR: Cannot write to " + outFileName + " file.");
		    System.exit(2);
		}
		//try: fileOutput = new PrintWriter(outFile);
		//catch: IOException; System.err.println(); System.exit(2)
		//outFileName = inFileName.substring(0, inFileName.indexOf('.txt') + "-results.txt");
	}
	public int[] readFile(int userCourseNum)
	{
        int[] ivGrades = new int[5];                                            //d&i instance storage vars; separated by class
        int[] ivScores = new int[101];
        String[] ivTeacherData = new String[3];
        
                                                                                //d&i return vars 
        int continueProgram = 0;                                                //0 if false (default), 1 if true
        int numOfCourses = 0; 
        
        String line = "";                                                       //for processing; have to keep this in here because have to store it in instance vars
        String label = "";
        String data = "";
        boolean reachedScores = false;
        boolean accurateCourse = false;

        while(fileInput.hasNextLine())                                          //you can comment this line out and still have it work
        {
            line = line + ' ' + reformat(fileInput.nextLine());
            
            while(line.length() != 0)                                           //process until line is done 
            {
                String[] labelDataRemLine = getLineInfo(line);
                label = labelDataRemLine[0];
                data = labelDataRemLine[1];
                //System.out.println("line = " + labelDataRemLine[2]);
                line = labelDataRemLine[2];
                //System.out.println("label: " + label + "\ndata: " + data + "\nline: " + line + "\n");//print data for now until I can get all sorted out and stored 
                
                //store data (using label and data)
                if (label.equalsIgnoreCase("Teacher:"))
                    ivTeacherData[0] = data;                                    //get the teacher name 
                else if (label.equalsIgnoreCase("Class:"))
                {
                    ivTeacherData[2] = data.substring(0, data.indexOf('-'));    //get everything before the dash (course number)
                    ivTeacherData[1] = data.substring(data.indexOf(' ')).trim();//course name
                    accurateCourse = (ivTeacherData[2].equalsIgnoreCase("" + userCourseNum));
                }
                else if(label.equalsIgnoreCase("Scores:"))                      //mini-method inside here to keep input line in this method
                {
                    String scoresInLine = ""; 
                    boolean continueScores = true;
                    boolean overrideContinue = true;                            //uhh... again, kinda redundant and wish I could make this more efficient
                    while(continueScores)
                    {
                        if(fileInput.hasNextLine())
                            scoresInLine = reformat(fileInput.nextLine());      //get next line
                        else
                            overrideContinue = false;                           //end of file
                        
                        if(scoresInLine.indexOf(' ') == -1)
                            continueScores = true;                              //case: random empty line
                        else
                            continueScores = !scoresInLine.substring(0, scoresInLine.indexOf(' ')).trim().equalsIgnoreCase("Teacher:");
                                                                                //keep on going until the first label in line                         
                        
                        if(!overrideContinue)
                            continueScores = overrideContinue;                  //same as declare continueScores to false
                        
                        if(continueScores)                                      //only if next line isn't label (another check... a little redundant (I wish I could think of something more efficient but this will do for now))
                        {
                            int[] receiveScores = getScores(scoresInLine);      //input line; get scores from line
                            for(int i = 0; i < receiveScores.length; i++)
                                ivScores[i] += receiveScores[i];                //input scores into instance variable
                        }
                        
                    }//end of while(continueScores)
                    line = scoresInLine.trim();                                 //append scoresInLine to line
                    ivGrades = convertToGrades(ivScores);
                    
                    /*
                    System.out.println("\n\t\t**CLASS (singular)**");           //print out class 
                    System.out.println("Teacher: " + ivTeacherData[0]);
                    System.out.println("Course Name: " + ivTeacherData[1]);
                    System.out.println("Course Number: " + ivTeacherData[2] + "\n\n");
                    System.out.println("Scores:");
                    for(int i: ivScores)
                        System.out.print(i + "\t");
                    System.out.println();
                    System.out.println("Grades:");
                    for(int i: ivGrades)
                        System.out.print(i + "\t");
                    System.out.println("\n\n");
                    */
                    
                    if(accurateCourse)                                          //only store if accurate course
                    {
                        numOfCourses++;
                        for(int i = 0; i < teacherData.length; i++)
                            teacherData[i] = ivTeacherData[i];
                        for(int i = 0; i < scores.length; i++)
                            scores[i] += ivScores[i];
                        for(int i = 0; i < grades.length; i++)
                            grades[i] += ivGrades[i];
                    }
                    else
                        teacherData[0] = ivTeacherData[0];                      //only keep teacher name for error message
                    
                    ivGrades = new int[5];                                      //reset ivs 
                    ivScores = new int[101];
                    ivTeacherData = new String[3];
                }//end of else if(label.equalsIgnoreCase("Scores:"))
            }//end of while(line.length() != 0)
        }//end of while(fileInput.hasNextLine())
        
        if(teacherData[2] != null)                                              //course has been found 
        {
            continueProgram = 1;
        }                                                                       //else continueProgram = 0;
        
        return new int[] {continueProgram, numOfCourses};
	}
	public String reformat(String lineIn)
	{
        String output = "";
        Scanner reformatScanner = new Scanner(lineIn);
        while(reformatScanner.hasNext())
        {
            output += (' ' + reformatScanner.next());
        }
        return output.trim();                                                   //takes out the beginning space 
	}
	public String[] getLineInfo(String lineIn)
	{
        //find labels, data = line.substring(endOf(label1), startOf(label2))
        //continue label1 = label2, label2 = line.indexOf(label, label);
        //System.out.println(lineIn);
        Scanner lineScanner = new Scanner(lineIn);                              //line already formatted 
        
        String label = "";                                                      //3 outputs 
        String data = "";
        String remainingLine = lineIn;
        
        String word = "";                                                       //for processing 
        int labels = 0;                                                         //the number of labels 
        while(lineScanner.hasNext())
        {
            word = lineScanner.next();
            
            //segment labels; if second label found, add it back to beginning of line and return it
            if(contains(word, ':'))                                             //check if word = label 
            {
                labels++;                                                       //found another label
                if(labels == 1)
                {
                    label = word;
                }
                else //if(labels == 2)                                          //check if more than 1 label found
                {
                    remainingLine = word + restOfLineScanner(lineScanner);      //(word = label2 at this point)
                    return new String[] {label, data.trim(), remainingLine};    //trim to remove beginning space 
                }
            }
            else                                                                //word is data 
                data = data + ' ' + word;
        }
        
        //System.out.println("Preview (in method): \n\tlabel: " + label + "\n\tdata: " + data + "\n\tremainingLine: " + remainingLine);
        return new String[] {label, data.trim(), ""};                           //edge (last) case: no other label; result: return what's left 
	}
	public boolean contains(String stringIn, char checkFor)
	{
		for(int i = 0; i < stringIn.length(); i++)
		{
			if (stringIn.charAt(i) == checkFor)
				return true;
		}
		//if we went through everything and we didn't get a match 
		return false;
	}
	public String restOfLineScanner(Scanner lineScannerIn)
	{
        String output = "";
        while(lineScannerIn.hasNext())
        {
            output += (' ' + lineScannerIn.next());
        }
        return output;
	}
	
	//stores in grades[] array 
	public int[] convertToGrades(int[] scoresIn)
	{
        int[] gradesStorage = new int[grades.length];
        for(int i = 0; i < scoresIn.length; i++)
        {
            if(scoresIn[i] >= 1)
            {
                if (i >= 90)                                                        //A
                    gradesStorage[0] += scoresIn[i];
                else if(i >= 80)                                                    //B
                    gradesStorage[1] += scoresIn[i];
                else if(i >= 70)                                                    //C
                    gradesStorage[2] += scoresIn[i];
                else if(i >= 60)                                                    //D
                    gradesStorage[3] += scoresIn[i];
                else                                                                //F
                    gradesStorage[4] += scoresIn[i];
            }
        }
        
        return gradesStorage;
    }
	/** Precondition: nextLine passed in && nextLine has (scores || empty line) 
	Postcondition: return all scores in nextLine 
	**/
	public int[] getScores(String nextLine)
	{
        //System.out.println("nextLine: " + nextLine);
        //utilMethod("getInput", "line", "continue");                           //to stop it (no problems anymore, removed)
        int[] scoresInLine = new int[101];
        Scanner scoreScanner = new Scanner(nextLine);                           //for processing 
        int nextScore = 0;
        while(scoreScanner.hasNext())                                           //while still has in line
        {
            if(scoreScanner.hasNextDouble())                                    //basically if nextInput = double; should always be true 
            {
                double scoreIn = scoreScanner.nextDouble();
                nextScore = Integer.parseInt(("" + scoreIn).substring(0, ("" + scoreIn).indexOf('.')));
                                                                                //get integer part of the double
            }
            else
                System.out.println("No more doubles in line (shouldn't be happening according to file format). ");
            
            if(nextScore >= 0 && nextScore < scores.length)
            {
                //System.out.println("score inputted: " + nextScore);
                scoresInLine[nextScore]++;
            }
            else
                System.out.println("Score value " + nextScore + " rejected: out of score bounds (0 to 100)");
                                                                                //if I have time/if I really want to, I would pass in the class name and class info as well so I can be more specific with this error
        }
        return scoresInLine;
	}
	public void outputData(int numOfCoursesIn)
	{
        System.out.println(":) you made it");
        
        int totalNumOfScores = 0;
        for(int i = 0; i < grades.length; i++)
            totalNumOfScores += grades[i];
        System.out.println("Data for: " + teacherData[0]);
        System.out.print("Course number: " + teacherData[2] + "\t");
        System.out.println("Course: " + teacherData[1]);
        System.out.println("Number of sections: " + numOfCoursesIn);
        
        System.out.println("Total # of scores = " + totalNumOfScores);
        
        System.out.println("\nData version 1: All scores printed from high to low: ");
        int numPrinted = 0;
        for(int i = scores.length - 1; i >= 0; i--)
        {
            for(int scoreRepetition = 0; scoreRepetition < scores[i]; scoreRepetition++)
            {
                System.out.printf("%5d", i);
                numPrinted++;
                if(numPrinted%15 == 0)
                    System.out.println(/*"\tnumPrinted = " + numPrinted*/);
            }
        }
        System.out.println("\nData version 2: Here are all of the scores grouped " + 
            "together by 90's, 80's etc.");
        //int numPrinted = 0;
        for(int i = scores.length - 1; i >= 0; i--)                             //i = index in array
        {
            for(int scoreRepetition = 0; scoreRepetition < scores[i]; scoreRepetition++)
            {
                System.out.printf("%5d", i);
                if(i%10 == 0)
                    System.out.println();
                //numPrinted++;
                //if(numPrinted%15 == 0)
                //    System.out.println(/*"\tnumPrinted = " + numPrinted*/);
            }
        }
        
        System.out.println();
	}
	public String utilMethod(String function, String info1, String info2)
	{
        if(function.equalsIgnoreCase("getInput")) ///getInput section of it; info1 = get, info2 = promptIn 
        {
            System.out.print(info2 + "\n\t-->\t");
            Scanner keyboard = new Scanner(System.in);
            String nextLine = keyboard.nextLine();
            Scanner numberScanner = new Scanner(nextLine);;
            
            if (info1.equalsIgnoreCase("line"))
                return nextLine;
            else if (info1.equalsIgnoreCase("word") || info1.equalsIgnoreCase("next"))
            {
                int endSubstring = nextLine.indexOf(' ');
                if(endSubstring == -1)
                    endSubstring = nextLine.length();
                return nextLine.substring(0, endSubstring);            //returns up to space
            }
            else if (info1.equalsIgnoreCase("int"))
            {
				if (true/*Boolean.valueOf(utilMethod("onlyHas", "int", nextLine))*/) // this returned false! uh oh
				{
                    int output = numberScanner.nextInt();
					return "" + output;
						//will have to Integer.parseInt(getInput("prompt", "int")) to return integer 
				}
                else 
                {
					System.out.println("Error: Received other data type when " + 
						"expected int" + 
						"\n\tfunction: " + function + 
                        "\n\tinfo1: " + info1 + 
                        "\n\tinfo2: " + info2 + 
                        "\n\tnextLine: " + nextLine + 
						"\n\t(Your number will be stored as \'0\')");
					return ("" + 0);
						//will still have to parse int
				}
            }
            else if (info1.equalsIgnoreCase("double"))
            {
				if (Boolean.parseBoolean(utilMethod("onlyHas", info1, "double")))
				{
                    double output = numberScanner.nextDouble();
					return "" + output;
						//will have to Double.parseDouble(getInput("prompt", "int")) to return double 
				}
                else 
                {
					System.out.println("Error: Received other data type when " + 
						"expected double" + 
						"\n\tfunction: " + function + 
                        "\n\tinfo1: " + info1 + 
                        "\n\tinfo2: " + info2 + 
                        "\n\tnextLine" + nextLine + 
						"\n\t(Your number will be stored as \'0.0\')");
					return ("" + 0);
						//will still have to parse double
				}
            }
            else 
            {
                System.out.println("Internal error: util method unrecognized info1 (get) parameter: " + 
                    "\n\tfunction: " + function + 
                    "\n\tinfo1: " + info1 + 
                    "\n\tinfo2: " + info2 + 
                    "\n(return: null)");
                return null;
                    //return is literally just for program to run (not technically needed)
            }
		}
		else if (function.equalsIgnoreCase("onlyHas")) // parameters: info1 = intOrDouble, info2 = getIn  
	    {
			if (info1.equalsIgnoreCase("int") || info1.equalsIgnoreCase("double"))
			{
				for (int i = 0; i < info2.length(); i++) //cycle through String info2 (getIn) 
				{
					if ((int)(info2.charAt(i)) >= 49 && (int)(info2.charAt(i)) <= 1)
					{
						//if within ascii range of numbers, continue with the program
					}
					else if (((int)(info2.charAt(i)) == 46) && info1.equalsIgnoreCase("double"))
					{
						//continue with the program but ONLY if it's in double 
					}
					else
						return String.valueOf(false); //no longer purely numbers 
				}
				return String.valueOf(true); //it made it out without returning false 
			}
			else
			{
				System.out.println("Internal error: onlyHas method unrecognized info2 (intOrDouble) parameter: " + 
					"\n\tfunction: " + function + 
                    "\n\tinfo1: " + info1 + 
                    "\n\tinfo2: " + info2 + 
					"\n\t(returned false)");
				return String.valueOf(false);
			}
		}
        //returns a String, no matter the parameters. beware. use parse____ if needed. 
        else ///catch section of it 
        {
            System.out.println("Internal error: util method unrecognized function parameter: " + 
                "\n\tfunction: " + function + 
                "\n\tinfo1: " + info1 + 
                "\n\tinfo2: " + info2 + 
                "\n(return: null)");
            return null; 
                //same purpose of return statement 
        }
	}
}
