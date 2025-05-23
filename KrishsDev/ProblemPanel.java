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

import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

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
	private InfoPopup correctPopup;
	private InfoPopup incorrectPopup;
	
	private ContentPanel problemContent;
	private AnswerPanel answerPanel;

	private String[] categories;
	private ArrayList<Question> questionHeap;
	private ArrayList<Question> questionHeap2;
	private Question currQuestion;
	private String selectedAnswer;

    public ProblemPanel()
    {
        setLayout(new BorderLayout());
        
		correctPopup = new InfoPopup("Correct!");
		incorrectPopup = new InfoPopup("Incorrect");

		categories = new String[] {"Polynomial Arithmetic",
		"End Behavior", "Factoring", "Remainder Theorem"};

		questionHeap = new ArrayList<>();
		questionHeap2 = new ArrayList<>();

		JPanel problemHolder = getProblemContent();
		JPanel bottomBtnHolder = getBottomButtons();
		
		add(problemHolder, BorderLayout.CENTER);
		add(bottomBtnHolder, BorderLayout.SOUTH);

        loadProblems();
    }

	public JPanel getProblemContent()
	{
		JPanel toReturn = new JPanel(new GridLayout(2, 1));
		problemContent = new ContentPanel();
		answerPanel = new AnswerPanel();

		toReturn.add(problemContent);
		toReturn.add(answerPanel);

		return toReturn;
	}

	public void loadProblems()
	{
		String fileName = "problems.txt";
		File problemFile = new File(fileName);
		Scanner read = null;

		try
		{
			read = new Scanner(problemFile);
			
			while (read.hasNext())
			{
				String line = read.nextLine();
				if(!line.equals(""))
				{
					String category = GameData.getDataTo(line, "|");
					String question = GameData.dataAfter(line, "|");
	
					String[] choices = new String[4];
					String[] explanations = new String[4];
					int answer = -1;
					for(int i=0; i<choices.length; i++)
					{
						line = read.nextLine();
						System.out.println(i);
	
						if(line.indexOf("!") != -1)
						{
							answer = i;
							choices[i] = GameData.getDataTo(line, "!");
						}
						else
						{
							choices[i] = GameData.getDataTo(line, "|");
						}
	
						explanations[i] = GameData.dataAfter(line, "|");
					}
	
					questionHeap.add(new Question(category, question, choices, answer, explanations));
				}
			}
		}
		catch(FileNotFoundException e)
		{
			System.err.printf("Error: Could not find file \"%s\"", fileName);
		}
	}

	public void getProblem()
	{
		selectedAnswer = null;

		if(questionHeap.size() > 0)
		{
			int getQuestion = GameData.getRandom(0, questionHeap.size()-1);
			currQuestion = questionHeap.remove(getQuestion);
			questionHeap2.add(currQuestion);
		}
		else
		{
			int getQuestion = GameData.getRandom(0, questionHeap2.size()-1);
			currQuestion = questionHeap2.remove(getQuestion);
			questionHeap.add(currQuestion);
		}

		problemContent.getQuestion();
		answerPanel.getAnswerChoices();
	}

	public JPanel getBottomButtons()
	{
		JPanel toReturn = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 10));
		BottomButtonHandler bottomBtnHandler = new BottomButtonHandler();
		Button submitButton = new Button("SUBMIT", bottomBtnHandler, 60);
		Button returnButton = new Button("RETURN", bottomBtnHandler, 60);

		toReturn.add(submitButton);
		toReturn.add(returnButton);
		toReturn.setBackground(Color.DARK_GRAY);

		return toReturn;
	}

	class BottomButtonHandler implements ActionListener
	{
		public void checkQuestion(String toCheck)
		{
			String answer = currQuestion.getAnswerChoices()[currQuestion.getAnswer()];
			boolean isCorrect = toCheck.equalsIgnoreCase(answer);
			String content;

			if(isCorrect)
			{
				content = "Nice job! You got the " +
				"question right!\n\nYour Answer: " + selectedAnswer +
				"\nActual Answer: " + answer + "\n\nExplanations:";

				String[] options = currQuestion.getAnswerChoices();
				String[] explanations = currQuestion.getExplanations();
				for(int i=0; i<explanations.length; i++)
				{
					content += "\nOption " + (i+1) + " (" + options[i] + ")";

					if(i == currQuestion.getAnswer())
					{
						content += " is correct because ";
					}
					else
					{
						content += " is incorrect because ";
					}

					content += explanations[i];
				}

				correctPopup.setContent(content);
				correctPopup.show();
			}
			else
			{
				content = "Better luck next time... You got the " +
				"question wrong.\n\nYour Answer: " + selectedAnswer +
				"\nActual Answer: " + answer + "\n\nExplanations: ";

				String[] options = currQuestion.getAnswerChoices();
				String[] explanations = currQuestion.getExplanations();
				for(int i=0; i<explanations.length; i++)
				{
					content += "\nOption " + (i+1) + " (" + options[i] + ")";

					if(i == currQuestion.getAnswer())
					{
						content += " is correct because ";
					}
					else
					{
						content += " is incorrect because ";
					}

					content += explanations[i];
				}

				incorrectPopup.setContent(content);
				incorrectPopup.show();
			}

			GameData.switchCard("game");
			GameData.executeUserMove(isCorrect);
		}

		public void actionPerformed(ActionEvent evt)
		{
			String command = evt.getActionCommand();

			if(command.equals("SUBMIT"))
			{
				if(selectedAnswer != null)
				{
					checkQuestion(selectedAnswer);
				}
			}
			else if(command.equals("RETURN"))
			{
				GameData.switchCard("game");
			}
		}
	}

    ///to be completed
    class ContentPanel extends JPanel
    {
		private Label category;
		private Label question;

		public ContentPanel()
		{
			setBackground(Color.GRAY);
			setLayout(new FlowLayout(FlowLayout.CENTER, 500, 40));

			category = new Label("", 65);
			question = new Label("", 40);
			Label demoMode = new Label("DEMO MODE ON", 40);

			add(category);
			add(question);
			if(GameData.isDemoModeOn())
			{
				add(demoMode);
			}
		}

		public void getQuestion()
		{
			category.setText(currQuestion.getCategory().toUpperCase());
			question.setText(currQuestion.getQuestion());
		}
    }
    
    class AnswerPanel extends JPanel
    {
		private JRadioButton[] answerChoices;
		private ButtonGroup bg;

		public AnswerPanel()
		{
			setBackground(Color.DARK_GRAY);
			setLayout(new FlowLayout(FlowLayout.CENTER, 20, 60));

			answerChoices = getRadioButtons();

			for(int i=0; i<answerChoices.length; i++)
			{
				add(answerChoices[i]);
			}
		}

		public TextField getAnswerField()
		{
			TextField toReturn = new TextField("Type your answer.", 30, 30);

			toReturn.setPreferredSize(new Dimension(toReturn.getWidth(), 50));

			return toReturn;
		}

		public JRadioButton[] getRadioButtons()
		{
			JRadioButton[] toReturn = new JRadioButton[4];
			bg = new ButtonGroup();
			AnswerChoiceHandler ansHandler = new AnswerChoiceHandler();

			for(int i=0; i<toReturn.length; i++)
			{
				toReturn[i] = new JRadioButton("");
				toReturn[i].setFont(new Font("Oswald Regular", Font.BOLD, 25));
				toReturn[i].setForeground(Color.WHITE);
				toReturn[i].setOpaque(false);
				toReturn[i].addActionListener(ansHandler);
				bg.add(toReturn[i]);
			}

			return toReturn;
		}

		public void getAnswerChoices()
		{
			bg.clearSelection();
			String[] choices = currQuestion.getAnswerChoices();
			for(int i=0; i<answerChoices.length; i++)
			{
				answerChoices[i].setText(choices[i]);
				System.out.println(answerChoices[i].getActionCommand());
			}
		}

		class AnswerChoiceHandler implements ActionListener
		{
			public void actionPerformed(ActionEvent evt)
			{
				selectedAnswer = evt.getActionCommand();
			}
		}
	}
}
/**
 * Krish Kumar
 * Period 6
 * Question.java
 * 
 * This class represents a multiple choice question.
 */

class Question
{
	private String category;
	private String question; ///problem
	private String[] answerChoices; ///answer choices
	private String[] explanations; ///explanations for choices
	private int answer; ///correct answer index
 
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
 
	public String[] getExplanations()
	{
		return explanations;
	}

	public String getCategory()
	{
		return category;
	}
} 