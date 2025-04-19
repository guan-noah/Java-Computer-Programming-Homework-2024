//TypeAlong from GameModuleFiles1 
/*

read and write to files 
ask 4 questoins 

3 panels in CardLayout 
asked, enter some names 
if user doesn't enter both first and last, button on bottom doesn't go to the next game 

border layout 
north is question 
center is 2x2 grid layout with JRadioButtons 
south is submit, next question, and next panel buttons 

after clicking an answer and submit (only submit available in south before answer selection):
	(also, previous answers should not be shown in the same game.)
	if incorrect answer: highlight question answer red and correct answer green 
	if correct answer: only highlight correct answer green 
	can only go to next question after either way 

after the last question, "next panel" button highlighted and we have no choice but to click. 
	displays a high score page (border layout): 
		south is play again button / exit button 
		center is split into 2 text areas (+ jscrollpane): one is 
"Good try " + username + ", you answered " + correctAnswers + " of the " + totalQuestions + " questions correctly. Keep working at it, and maybe next time your name will be added to the list of high scores!"
		other is the high scores list with their score out of total questions and their name formatted 
	play again button leads you back to the first page (random set of 30 questions) 
	if you get a high score, your name gets added/inserted to the list BEFORE you show the panel. 
	these calculations should be done before (in another method). 

questions/answers file: 
question 
answer1
answer2
answer3
answer4
int (which number question was correct)
line break 
<next>

highscores file: 
exactly what goes into the text file 
#X		score		name 


///note: I've been living in https://www.youtube.com/watch?v=aUWsT37YEd4 GameModuleFiles2 by Scott DeRuiter 
	///timestamp 6:46 for a WHILE now. This is my final edit before I start following along. 
	///I turned up video quality to highest resolution, slowed down the video 
	///by 0.25x speed, and double-clicked spacebar to essentially catch DeRuiter's "screenshots". 
	///note to anyone else seeing this: work backwards from that timestamp to obtain original file.


*/
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java.awt.Insets;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BorderFactory;

import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.io.File;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class GameModuleFiles
{
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("This program shows how files could be used in a GUI.");
		frame.setLayout(new BorderLayout());
		CardForGameModuleFiles panel = new CardForGameModuleFiles();
		frame.add(panel, BorderLayout.CENTER);
		frame.setSize(960, 600);
		frame.setLocation(200, 140);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
class CardForGameModuleFiles extends JPanel
{
	private CardLayout listOfCards;
	private GameData data;
	
	public CardForGameModuleFiles()
	{
		data = new GameData();											//new class GameData
		data.grabQuestionFromFile();									//get question 
		
		setBackground(Color.BLACK);
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));		//interesting! sets borders with 10 px width and height and 2 more vals
		
		listOfCards = new CardLayout();
		setLayout(listOfCards);
		
		StartPanel first = new StartPanel(data, listOfCards, this);		//must feed in to access CardLayout; easier way would just to encapsulate all classes in GameModuleFiles and have fvs 
		add(first, "1");
		
		QuestionsPanel second = new QuestionsPanel(data, listOfCards, this);
		add(second, "2");
		
		HighScoresPanel third = new HighScoresPanel(data, listOfCards, this);
		add(third, "3");
	}
}

class StartPanel extends JPanel implements ActionListener				//1
{
	private GameData data;
	private CardLayout listOfCards;
	private CardForGameModuleFiles primaryPanel;
	private JTextField firstNameField, lastNameField;
	
	public StartPanel(GameData d, CardLayout c, CardForGameModuleFiles p)
	{
		data = d;
		listOfCards = c;
		primaryPanel = p;
		
		setBackground(Color.BLACK);
		setLayout(new GridLayout(3, 1, 10, 10));
		Font myFont = new Font("Tahoma", Font.BOLD, 22);
		
		JPanel firstNamePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 70));//change this to 2 lines if it doesn't work
		firstNamePanel.setBackground(Color.LIGHT_GRAY);
		add(firstNamePanel);
		
		JLabel firstNamePrompt = new JLabel("First Name: ");
		firstNamePrompt.setFont(myFont);
		firstNamePanel.add(firstNamePrompt);
		
		firstNameField = new JTextField(16);
		firstNameField.setMargin(new Insets(10, 10, 10, 10));
		firstNameField.setFont(myFont);
		firstNamePanel.add(firstNameField);
		
		JPanel lastNamePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 70));//change this to 2 lines if it doesn't work
		lastNamePanel.setBackground(Color.LIGHT_GRAY);
		add(lastNamePanel);
		
		JLabel lastNamePrompt = new JLabel("Last Name: ");
		lastNamePrompt.setFont(myFont);
		lastNamePanel.add(lastNamePrompt);
		
		lastNameField = new JTextField(16);
		lastNameField.setMargin(new Insets(10, 10, 10, 10));
		lastNameField.setFont(myFont);
		lastNamePanel.add(lastNameField);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.LIGHT_GRAY);
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 70));
		add(buttonPanel);
		
		JButton next = new JButton("NEXT PANEL");
		next.setFont(myFont);
		next.addActionListener(this);
		buttonPanel.add(next);
	}
	
	public void actionPerformed(ActionEvent evt)
	{
		String command = evt.getActionCommand();
		
		if(command.equals("NEXT PANEL") && !firstNameField.getText().equals("") && !lastNameField.getText().equals(""))
		{
			listOfCards.next(primaryPanel);
		}
	}
}

class QuestionsPanel extends JPanel implements ActionListener
{
	private GameData data;
	private CardLayout listOfCards;
	private CardForGameModuleFiles primaryPanel;
	private ButtonGroup group;
	private JTextArea questionArea;
	private JRadioButton[] answer;
	private JButton submit, nextQuestion, nextPanel;
	
	public QuestionsPanel(GameData d, CardLayout c, CardForGameModuleFiles p)
	{
		data = d;
		listOfCards = c;
		primaryPanel = p;
		
		setBackground(Color.BLACK);
		setLayout(new BorderLayout(10, 10));
		Font myFont = new Font("Tahoma", Font.BOLD, 22);
		
		answer = new JRadioButton[4];
		
		JPanel question = new JPanel();
		question.setBackground(Color.WHITE);
		question.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
		question.setLayout(new BorderLayout());
		add(question, BorderLayout.NORTH);
		
		questionArea = new JTextArea(data.getQuestion(), 3, 30);
		questionArea.setFont(myFont);
		
		questionArea = new JTextArea(data.getQuestion(), 3, 30);
		questionArea.setFont(myFont);									//what he just repeated the same code. 
		questionArea.setLineWrap(true);
		questionArea.setWrapStyleWord(true);
		questionArea.setOpaque(false);
		questionArea.setEditable(false);
		
		question.add(questionArea, BorderLayout.CENTER);
		
		JPanel answers = new JPanel();									//d&i answers jpanel gridlayout in center 
		answers.setBackground(Color.GRAY);
		answers.setLayout(new GridLayout(2, 2, 20, 20));
		add(answers, BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel();								//d&i button holder jpanel flowlayout in south 
		buttonPanel.setBackground(Color.WHITE);
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 30));
		add(buttonPanel, BorderLayout.SOUTH);
		
		nextPanel = new JButton("NEXT PANEL");							//d&i nextpanel button 
		nextPanel.setFont(myFont);
		nextPanel.addActionListener(this);
		nextPanel.setEnabled(false);									//what's setenabled? 
		buttonPanel.add(nextPanel);
	}
	
	
	
	public void actionPerformed(ActionEvent evt)
	{
		String command = evt.getActionCommand();
		if(group.getSelection() != null)								//what's getSelection?
		{
			submit.setEnabled(true);									//allow user to click submit button 
		}
		
		///duplicated command.equals("submit")?
		
		if(command.equals("SUBMIT"))
		{
			answer[data.getCorrectAnswer()].setBackground(Color.GREEN);	//set correct jradiobutton (in array answer) to green 
			for(int i = 0; i < answer.length; i++)						//iterates through all jradiobuttons in answer 
			{
				if(answer[i].isSelected())								//if the current jradiobutton is selected 
				{
					if(i != data.getCorrectAnswer())					//and it isn't the right answer 
					{
						answer[i].setBackground(Color.RED);
					}
					else
					{
						data.addOneToCorrectCount();
					}
				}
			}
			group.clearSelection();										//reset 
			for(int i = 0; i < answer.length; i++)
			{
				answer[i].setEnabled(false);							//reset
			}
			submit.setEnabled(false);
			if(data.getQuestionCount() == 4)							//what's getquestioncount
			{
				nextQuestion.setEnabled(true);							//allow user to click nextquestion button 
			}
			else
			{
				nextQuestion.setEnabled(true);
			}
		}
		else if(command.equals("NEXT QUESTION"))						///only saw a glimpse of these methods. logicking it all out now. 
		{
			resetQuestion();
			nextPanel.setEnabled(false);
			listOfCards.next(primaryPanel);
		}
		else if(command.equals("NEXT PANEL"))
		{
			data.resetAll();
			resetQuestion();
			nextQuestion.setEnabled(false);
			listOfCards.next(primaryPanel);
		}
	}
	
	public void resetQuestion()											//also only saw a glimpse of this one. 
	{
		//~ group.clearSelection();											//??? 
		data.grabQuestionFromFile();
		questionArea.setText(data.getQuestion());
		char[] letters = new char[] {'A', 'B', 'C', 'D'};
		//~ answer[0].setText("A. " + data.getAnswer(0));					//used this as a framework. mr. deruiter did it all manually. 
		for(int i = 0; i < letters.length; i++)							//have to use letters.length because deruiter only had 4 manual code snippets. too lazy to find out if I can use answers.length as well. 
		{
			answer[i].setText(letters[i] + ". " + data.getAnswer(0));	
		}
	}
}

class HighScoresPanel extends JPanel implements ActionListener
{
	private GameData data; 
	private CardLayout listOfCards;
	private CardForGameModuleFiles primaryPanel;
	JTextArea scoreInfo, highScoresArea;								//interesting. no access specifier. does this mean that all classes can access it? 
	
	public HighScoresPanel(GameData d, CardLayout c, CardForGameModuleFiles p)
	{
		data = d;
		listOfCards = c;
		primaryPanel = p;
		
		Font myFont = new Font("Tahoma", Font.BOLD, 22);				//recreate the font... again...
		
		setLayout(new BorderLayout(20, 20));							//set border layout 
		setBackground(Color.GRAY);
		setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		setFont(myFont);
		
		JPanel centerPanel = new JPanel();								//create center panel 
		centerPanel.setLayout(new GridLayout(1, 2, 10, 10));
		add(centerPanel, BorderLayout.CENTER);
		
		JPanel leftSidePanel = new JPanel();							//create center panel's center, leftSidePanel
		leftSidePanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
		leftSidePanel.setLayout(new BorderLayout());
		centerPanel.add(leftSidePanel, BorderLayout.CENTER);
		
		scoreInfo = new JTextArea("" + data.getCorrectCount(), 10, 20);	//initialize jtextarea scoreInfo 
		scoreInfo.setFont(myFont);
		scoreInfo.setLineWrap(true);
		scoreInfo.setWrapStyleWord(true);
		scoreInfo.setOpaque(false);
		scoreInfo.setEditable(false);
		leftSidePanel.add(scoreInfo);
		
		JPanel rightSidePanel = new JPanel();							//create center panel's center, rightSidePanel (wait a second, this isn't right)
		rightSidePanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
		rightSidePanel.setLayout(new BorderLayout());
		centerPanel.add(rightSidePanel, BorderLayout.CENTER);
		
		highScoresArea = new JTextArea("" + data.getHighScores(), 10, 20);//initialize jtextarea highscoresarea
		highScoresArea.setFont(myFont);
		highScoresArea.setLineWrap(true);
		highScoresArea.setWrapStyleWord(true);
		highScoresArea.setOpaque(false);
		highScoresArea.setEditable(false);
		highScoresArea.setMargin(new Insets(10, 10, 10, 10));
		JScrollPane scroller = new JScrollPane(highScoresArea);
		rightSidePanel.add(scroller);
		
		JPanel buttonPanel = new JPanel();								//create bottom panel, buttonPanel
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		add(buttonPanel, BorderLayout.SOUTH);
		
		JButton playAgain = new JButton("PLAY AGAIN");					//create play again button and add it to buttonPanel
		playAgain.setFont(myFont);
		playAgain.addActionListener(this);
		buttonPanel.add(playAgain);
		
		JButton exit = new JButton("EXIT");								//create exit button and add it to buttonPanel 
		exit.setFont(myFont);
		exit.addActionListener(this);
		buttonPanel.add(exit);
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		scoreInfo.setText("" + data.toString());						//set scoreInfo text area to data toString output 
		highScoresArea.setText("" + data.getHighScores());				//same logic, but highScoresArea text area and data getHighScores output 
		highScoresArea.setCaretPosition(0);								//forgot what this does 
	}
	
	public void actionPerformed(ActionEvent evt)
	{
		data.saveToHighScores();										//save data to high scores
		String command = evt.getActionCommand();						//get command 
		
		if (command.equals("PLAY AGAIN"))								//if button pressed = play again 
		{
			listOfCards.previous(primaryPanel);							//send user to questions panel 
		}
		else if(command.equals("EXIT"))									//if button pressed = exit 
		{
			System.exit(0);												//exit program
		}
	}
}

class GameData															//data holder class
{
	private String first, last;
	private String question;
	private String[] answerSet;
	private int correctAnswer;
	private boolean[] chosenQuestions;
	private int questionCount;
	private int correctCount, lastGameCorrectCount;
	
	public GameData()													//default constructor 
	{
		first = "";
		last = "";
		correctCount = 0;
		resetAll();
	}
	
	public void resetAll()
	{
		lastGameCorrectCount = correctCount;							//???
		answerSet = new String[4];
		question = "";
		for(int i = 0; i < answerSet.length; i++)
		{
			answerSet[i] = "";
		}
		correctAnswer = -1;												//it will never get to this value unless program manually sets it to this value 
		chosenQuestions = new boolean[30];
		questionCount = correctCount = 0;								//this may be dangeroussss! it MAY link questionCount ref to correctCount ref. idk but it's a primitive type 
	}
	
	public void grabQuestionFromFile()									//assumed implementation walkthrough later 
	{
	}
	
	public void setName(String f, String l)								//simple getter and setter methods follow 
	{
		first = f;
		last = l;
	}
	
	public String getQuestion()
	{
		return "" + questionCount + ". " + question;
	}
	
	public String getAnswer(int index)
	{
		return answerSet[index];
	}
	
	public int getCorrectAnswer()
	{
		return correctAnswer;
	}
	
	public int getQuestionCount()
	{
		return questionCount;
	}
	
	public int getCorrectCount()
	{
		return lastGameCorrectCount;
	}
	
	public void addOneToCorrectCount()
	{
		correctCount++;
	}
	
	public String toString()
	{
		String message = ", " + first + " " + last + 
			", you answered " + 
			lastGameCorrectCount + " out of 4 of the questions correctly.  ";//note: couldn't see the end in the video, but I watched the previous video so I know what it's supposed to look like. also, there is a double space between the 2 sentences. 
		if(lastGameCorrectCount > 2)
		{
			return "Congratulations" + message + "Your name will be " + 
				"added to the list of high scores, shown to the right.";
		}
		return "Good try" + message + "Keep working at it, and maybe " + 
			"next time your name will be added to the list of high scores.";//don't need an else. 
	}
	
	public String getHighScores()
	{
		String result = "";
		String fileName = "highScores.txt";
		Scanner inFile = null;
		File inputFile = new File(fileName);
		
		try																//initialize input file scanner 
		{
			inFile = new Scanner(inputFile);
		}
		catch(FileNotFoundException e)
		{
			System.err.printf("ERROR: Cannot open %s\n", fileName);
			System.out.println(e);
			System.exit(1);
			//~ no System.err.printStackTrace(); ?
		}
		
		while(inFile.hasNext())											//simply cache all file info into string format
		{
			String line = inFile.nextLine();
			result += line + "\n";
		}
		return result;
	}
	
	public void saveToHighScores()
	{
	}
}
