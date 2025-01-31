//TeacherInfo.java
/* Noah Guan
 * 01-31-2025
 * Per. 6 Java w/ Mr. Yu
 * TeacherInfo.java
 * Program #40
 * Pseudocode: uploaded (on whiteboard! :D)\
 */

import java.io.File;
import java.io.FileNotFoundException; 
import java.io.PrintWriter;
import java.io.IOException; 
import java.util.Scanner;

public class TeacherInfo
{
	private int[] grades, scores;
	private String[] courseInfo;
	private Scanner fileInput;
	private PrintWriter fileOutput;
	private String inFileName, outFileName;
	public TeacherInfo()
	{
		grades = new int[5];
		scores = new int[101];
		courseInfo = new String[3];
		//fileInput = new Scanner();									//simply reminders for myself to 
		//fileOutput = new PrintWriter(fileName);						//remember to instantiate it later
		inFileName = "";
		outFileName = "";												///hopefully not pointing towards the same "object" 
	}
	public static void main(String args[])
	{
		TeacherInfo ti = new TeacherInfo();
		ti.fakeMain();
	}
	public void fakeMain()
	{
		//d&i fileInput and fileOutput 
		//get inFileName 
		//outFileName = inFileName.substring(0, inFileName.indexOf('.txt') + "-results.txt";
		//File inFile = new File(fileName);
		//try: fileInput = new Scanner(inFile);
		//catch: FileNotFoundException; System.err.println(); System.exit(1);
		//try: fileOutput = new PrintWriter(outFile);
		//catch: IOException; System.err.println(); System.exit(2)
		//call readFile
		//call outputData
	}
	public void readFile()
	{
		//get name of teacher, course, and course # 
		//search through file for course number 
		//if no course, print no course 
		//else: 
		//while !endOfFile
		//get all blocks of info using header as separation 
			//course header = look for defining text: a colon or smth
			//using nextDouble and special marks in header 
			//use next() to avoid whitespace to get words 
		//once done with one block, keep checking until end of file 
	}
	public void outputData()
	{
		//for loop: 
		//1. print class info to both 
			//sift through class info; print out to both 
		//2. print scores to terminal 
			//print scores 
		//3. print score "graph" to file 
			//print scores
		//4. print frequency table to both 
	}
