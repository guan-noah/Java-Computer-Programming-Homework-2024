/**
 * Krish Kumar
 * Period 6
 * ProblemPanel.java
 * 
 * This class stores the problem content, and also displays it.
 * 
 * NOTE: THIS CLASS IS GOING TO BE REWORKED A BIT DUE TO THE DEPRECATION
 * OF ShortAnswerQuestion
 * This also means that ShortAnswerQuestion WILL NOT BE PRESENT in the
 * Week 4 code at all.
 **/
//imports
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class ProblemPanel extends JPanel
{
	private InfoPopup correctPopup;//2 popups, shown whether user is wrong or right 
	private InfoPopup incorrectPopup;
	
	private ContentPanel problemContent;//2 panels, showing either problem or answer
	private AnswerPanel answerPanel;

	private String[] categories;//categories of questions 
	private ArrayList<Question> questionHeap;//primary question cache 
	private ArrayList<Question> questionHeap2;//move here when done 
	private Question currQuestion;//current question
	private String selectedAnswer;//user answer 
	
	/*
	 * initialize problem panel with components 
	 */
    public ProblemPanel()
    {
        setLayout(new BorderLayout());//borderlayout jpanel 
        
        //initialize popups 
		correctPopup = new InfoPopup("Correct!");
		incorrectPopup = new InfoPopup("Incorrect");
		
		//define categories as stated in help menu 
		categories = new String[] {"Polynomial Arithmetic",
		"End Behavior", "Factoring", "Remainder Theorem"};
		
		//initialize question heaps 
		questionHeap = new ArrayList<>();
		questionHeap2 = new ArrayList<>();
		
		//initialize actual problem panel holder and bottom button holder 
		JPanel problemHolder = getProblemContent();
		JPanel bottomBtnHolder = getBottomButtons();
		
		//add holders to problem panel 
		add(problemHolder, BorderLayout.CENTER);
		add(bottomBtnHolder, BorderLayout.SOUTH);
		
		//load problems in
        loadProblems();
    }

	/*
	 * return jpanel with problem and answer grid in it 
	 */
	public JPanel getProblemContent()
	{
		JPanel toReturn = new JPanel(new GridLayout(2, 1));//gridlayout, 2 rows
		problemContent = new ContentPanel();//problems stored here
		answerPanel = new AnswerPanel();//answers stored here
		
		//add panels to return panel
		toReturn.add(problemContent);
		toReturn.add(answerPanel);
		
		return toReturn;
	}

	/*
	 * load problems into panel to present as questions
	 */
	public void loadProblems()
	{
		//standard file io logic
		String fileName = "problems.txt";
		File problemFile = new File(fileName);
		Scanner read = null;

		try
		{
			read = new Scanner(problemFile);
			
			//once scanner has been properly initialized 
			//while scanner has next 
			while (read.hasNext())
			{
				String line = read.nextLine();//read in the next line 
				if(!line.equals(""))//if line isn't empty 
				{
					String category = GameData.getDataTo(line, "|");//category is the first line up to the bar 
					String question = GameData.dataAfter(line, "|");//question is the rest of the line
					
					//d&i choices and explanations string based on file formatting 
					String[] choices = new String[4];
					String[] explanations = new String[4];
					int answer = -1;
					//initialize choices and explanations, repeat a time per choice/explanation pair 
					for(int i=0; i<choices.length; i++)
					{
						line = read.nextLine();//read next line into line var
	
						if(line.indexOf("!") != -1)//if is correct answer (denoted by '!')
						{
							answer = i;//index of answer = i (where you found the correct answer)
							//d&i choices and explanations based on line parsing and gamedata methods 
							choices[i] = GameData.getDataTo(line, "!");
							explanations[i] = GameData.dataAfter(line, "|");
						}
						else
						{
							//d&i choices and explanations based on line parsing and gamedata methods 
							choices[i] = GameData.getDataTo(line, "|");
							explanations[i] = GameData.dataAfter(line, "|");
						}
					}
					//add to question heap 
					questionHeap.add(new Question(category, question, choices, answer, explanations));
				}
				//if line is empty, go to the next line. 
			}
		}
		catch(FileNotFoundException e)
		{
			//error message
			System.err.printf("Error: Could not find file \"%s\"%n", fileName);
		}
	}
	
	/*
	 * get problem loaded into fvs 
	 * and call getQuestion and getAnswerChoices in respective classes 
	 * to initialize question and answer as well 
	 */
	public void getProblem()
	{
		selectedAnswer = null;//no selected answer yet, reinitialize it to null 
		
		//logic will alternate to make sure each question doesn't repeat -- 
			//and when we run out, it restarts but from the other way. 
		//if still questions in questionHeap left (questions not asked) 
		if(questionHeap.size() > 0)
		{
			//get a random question to remove from 
			int getQuestion = GameData.getRandom(0, questionHeap.size()-1);
			//remove that question from questionHeap and add to questionHeap2 
			currQuestion = questionHeap.remove(getQuestion);//stored in fv
			questionHeap2.add(currQuestion);//stored in fv
		}
		else//reverse. get from questionheap2 and add to questionheap1 when done. 
		{
			//reverse logic from the if statement above 
			int getQuestion = GameData.getRandom(0, questionHeap2.size()-1);
			currQuestion = questionHeap2.remove(getQuestion);
			questionHeap.add(currQuestion);
		}
		
		//move on and initialize question and answer now. 
		//call getQuestion in problemContent to initialize question. 
		problemContent.getQuestion();
		//call getAnswerChoices in answerPanel to initialize answer. 
		answerPanel.getAnswerChoices();
	}

	/*
	 * returns the bottom navigation/confirmation buttons for the user. 
	 */
	public JPanel getBottomButtons()
	{
		//flowlayout jpanel 
		JPanel toReturn = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 10));
		//initialize buttons with button handler 
		BottomButtonHandler bottomBtnHandler = new BottomButtonHandler();
		Button submitButton = new Button("SUBMIT", bottomBtnHandler, 60);
		Button returnButton = new Button("RETURN", bottomBtnHandler, 60);

		//add to jpanel 
		toReturn.add(submitButton);
		toReturn.add(returnButton);
		toReturn.setBackground(Color.DARK_GRAY);

		return toReturn;
	}

	/*
	 * the handler class for the buttons on the bottom. 
	 */
	class BottomButtonHandler implements ActionListener
	{
		/*
		 * This method is split into 2 main parts: 1 for correct and 1 for incorrect. 
		 * First, it checks if answer given as a parameter input is correct. 
		 * Then, "writes" to popups and shows corresponding one depending 
		 * on if user correct. 
		 */
		public void checkQuestion(String toCheck)
		{
			//get the right answer from question's answer choices[right answer index]
			String answer = currQuestion.getAnswerChoices()[currQuestion.getAnswer()];
			//if the answer given = correct answer 
			boolean isCorrect = toCheck.equalsIgnoreCase(answer);
			String content = ""; //popup content; to be edited based on isCorrect 
			
			//if the answer is correct, edit popup content accordingly. 
			if(isCorrect)
			{
				//congratulations message and comparing answers 
				content = "Nice job! You got the " +
				"question right!\n\nYour Answer: " + selectedAnswer +
				"\nActual Answer: " + answer + "\n\nExplanations:";
				
				//matching options with explanations
				//get options and explanations 
				String[] options = currQuestion.getAnswerChoices();
				String[] explanations = currQuestion.getExplanations();
				//add each explanation with corresponding option to content 
				for(int i=0; i<options.length; i++)
				{
					//add option to content 
					content += "\nOption " + (i+1) + " [" + options[i] + "]";
					
					//add explanation to content 
					if(i == currQuestion.getAnswer())//if it's correct 
					{
						//notate that it's correct and then give reason 
						content += " is correct because: " + explanations[i];
					}
					else
					{
						//notate that it's incorrect and give reason 
						content += " is incorrect because: " + explanations[i];
					}
				}
				
				//set content and show CORRECT popup! 
				correctPopup.setContent(content);
				correctPopup.show();
			}
			else//if the answer is incorrect, edit popup content accordingly. 
			{//actually VERY similar to if answer is correct. Could make another method, used by both for polymorphism
				//encouragement message and comparing answers 
				content = "Better luck next time... You got the " +
				"question wrong.\n\nYour Answer: " + selectedAnswer +
				"\nActual Answer: " + answer + "\n\nExplanations:";

				//still match options with explanations; same logic 
				String[] options = currQuestion.getAnswerChoices();
				String[] explanations = currQuestion.getExplanations();
				
				//add each explanation with corresponding option to content 
				for(int i=0; i<options.length; i++)
				{
					//add option
					content += "\nOption " + (i+1) + " [" + options[i] + "]";
					
					//add explanation
					if(i == currQuestion.getAnswer())//if it's correct 
					{
						//notate that it's correct and add reasoning after
						content += " is correct because: " + explanations[i];
					}
					else
					{
						//notate that it's incorrect and add reasoning 
						content += " is incorrect because: " + explanations[i];
					}
				}
				
				//set content and show incorrect popup. 
				incorrectPopup.setContent(content);
				incorrectPopup.show();
			}
			
			//Switch main JFrame to the game panel. 
			GameData.switchCard("game");
			//Execute the move (and pass in if move is correct) 
			GameData.executeUserMove(isCorrect);
		}

		/*
		 * actionPerformed method; called when bottom buttons pressed. 
		 */
		public void actionPerformed(ActionEvent evt)
		{
			String command = evt.getActionCommand();//which button pressed it

			if(command.equals("SUBMIT"))//if it was the submit button 
			{
				if(selectedAnswer != null)//and user selected an answer
				{
					checkQuestion(selectedAnswer);//call checkQuestion method with selected answer. 
				}
			}
			else if(command.equals("RETURN"))//if it was the return button 
			{
				GameData.switchCard("game");//switch main JFrame to game panel. 
			}
		}
	}

	/*
	 * ContentPanel class - displays the questions and answers on screen. 
	 */
    ///to be completed
    class ContentPanel extends JPanel
    {
		private Label category;//label for problem category
		private JTextArea question;//jtextarea for (a possibly long) question

		/*
		 * Initialize content panel with components. 
		 */
		public ContentPanel()
		{
			setBackground(Color.GRAY);//gray background, flowlayout jpanel
			setLayout(new FlowLayout(FlowLayout.CENTER, 500, 40));
			
			//initialize components that show the question information 
			category = new Label("", 65);
			question = getQuestionArea();
			JScrollPane questionScroll = new JScrollPane(question);
			Label demoMode = new Label("DEMO MODE ON", 40);
			
			//add the components to the panel (and the demo mode if applicable) 
			add(category);
			add(questionScroll);
			if(GameData.isDemoModeOn())
			{
				add(demoMode);
			}
		}
		/*
		 * Returns a fully initialized question JTextArea (to add JScrollPane to). 
		 */
		public JTextArea getQuestionArea()
		{
			//initialize JTextArea with matching background
			JTextArea output = new JTextArea();
			output.setFont(new Font("Share Tech Regular", Font.PLAIN, 40));
			output.setBackground(Color.GRAY);//match colors with background
			//~ output.setForeground(Color.WHITE);
			output.setLineWrap(true);
			output.setWrapStyleWord(true);
			output.setEditable(false);//user cannot edit text area
			
			return output;
		}
		
		/*
		 * Sets the category to current question's category --> uppercase. 
		 * Also sets the question text. 
		 */
		public void getQuestion()
		{
			category.setText(currQuestion.getCategory().toUpperCase());
			question.setText(currQuestion.getQuestion());
		}
    }
    
	/*
	 * Answer panel at bottom - displays the answer choices. 
	 */
    class AnswerPanel extends JPanel
    {
		private JRadioButton[] answerChoices;//jradiobutton array 
		private ButtonGroup bg;//button group for radio buttons
		
		/*
		 * Initialize answer panel with all components. 
		 */
		public AnswerPanel()
		{
			setBackground(Color.DARK_GRAY);
			setLayout(new FlowLayout(FlowLayout.CENTER, 20, 60));//flowlayout jpanel 

			answerChoices = getRadioButtons();//call get radio buttons to initialize 
			
			//add all answer choices to jpanel 
			for(int i=0; i<answerChoices.length; i++)//iterate through choices 
			{
				add(answerChoices[i]);//add each choice
			}
		}

		/*
		 * Returns a JTextField (for FRQs -- discontinued) 
		 */
		public TextField getAnswerField()
		{
			//free user response question -- entered into a text field 
			TextField toReturn = new TextField("Type your answer.", 30, 30);
			//set preferred size of text field 
			toReturn.setPreferredSize(new Dimension(toReturn.getWidth(), 50));

			return toReturn;
		}

		/*
		 * returns the JRadioButton array of answer choices. 
		 */
		public JRadioButton[] getRadioButtons()
		{
			//initialize JRadioButtons, their button group, and their answer choice handler 
			JRadioButton[] toReturn = new JRadioButton[4];//4 choices 
			bg = new ButtonGroup();
			AnswerChoiceHandler ansHandler = new AnswerChoiceHandler();
			
			//iterate through answer choices to initialize 
			for(int i=0; i<toReturn.length; i++)
			{
				toReturn[i] = new JRadioButton("");//create button 
				//set button font 
				toReturn[i].setFont(new Font("Oswald Regular", Font.BOLD, 25));
				toReturn[i].setForeground(Color.WHITE);//make white text 
				toReturn[i].setOpaque(false);//NOT clear buttons 
				toReturn[i].addActionListener(ansHandler);//add answerchoicehandler
				bg.add(toReturn[i]);//add to button group 
			}

			return toReturn;
		}

		/*
		 * initializes the answer choices to fv answerChoices. 
		 */
		public void getAnswerChoices()
		{
			bg.clearSelection();//deselect all radio buttons 
			//get the answer choices of current question 
			String[] choices = currQuestion.getAnswerChoices();
			//for each answer choice 
			for(int i=0; i<answerChoices.length; i++)
			{
				answerChoices[i].setText(choices[i]);//set the JRadioButton text to choices
				//~ System.out.println(answerChoices[i].getActionCommand());
					//debugging print statement: prints out what the user selected 
			}
		}

		/*
		 * Handler for all JRadioButtons. 
		 * simply sets selectedAnswer to the name of the JRadioButton clicked on. 
		 */
		class AnswerChoiceHandler implements ActionListener
		{
			public void actionPerformed(ActionEvent evt)
			{
				//set selected answer to answer choice clicked on
				selectedAnswer = evt.getActionCommand();
			}
		}
	}
}
/*
 * Krish Kumar
 * Period 6
 * Question.java
 * 
 * This class represents a multiple choice question. Simply a container 
 * getter/setter class that holds data. 
 */

class Question
{
	private String category;//question category (of the 4 specified ones)
	private String question; ///problem
	private String[] answerChoices; ///answer choices
	private String[] explanations; ///explanation for question
	private int answer; ///correct answer index
 
	/*
	 * Initialize field variables to parameter input in question initialization. 
	 */
	public Question(String categoryIn, String questionIn, String[] choicesIn, int answerIn, String[] explanationsIn)
	{
		category = categoryIn;
		question = questionIn;
		answerChoices = choicesIn;
		answer = answerIn;
		explanations = explanationsIn;
	}
 
	/**
	 * Returns the question. 
	 **/
	public String getQuestion()
	{
		return question;
	}
 
	/**
	 * Returns the answer choices. 
	 **/
	public String[] getAnswerChoices()
	{
		return answerChoices;
	}
 
	/**
	 * Returns the answer. 
	 **/
	public int getAnswer()
	{
		return answer;
	}
 
	/*
	 * Returns the explanations. 
	 */
	public String[] getExplanations()
	{
		return explanations;
	}

	/*
	 * Returns the problem category. 
	 */
	public String getCategory()
	{
		return category;
	}
} 
