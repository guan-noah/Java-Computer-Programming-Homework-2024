/**
 * Krish Kumar
 * Period 6
 * ProblemPanel.java
 * 
 * This class stores the problem content, and also displays it.
 * ***SERIOUSLY NEEDS DOCUMENTATION***
 * //okay sir krish 
 **/

//imports
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class ProblemPanel extends JPanel//jpanel
{
	private JPanel currProblemContent;
	private int prev;
	private String prevCategory;

	private InfoPopup correctPopup;
	private InfoPopup incorrectPopup;

	private String[] categories;

    ///these store the questions
    private ArrayList<ArrayList<MultipleChoiceQuestion>> mcqList;
    private ArrayList<ArrayList<ShortAnswerQuestion>> saqList;
    
    private ShortAnswerQuestion currSAQ;
    private MultipleChoiceQuestion currMCQ;
	private String selectedAnswer;

    public ProblemPanel()
    {
        setLayout(new BorderLayout());

		currProblemContent = null;
		prev = -1;
		prevCategory = new String();
        
		correctPopup = new InfoPopup("Correct!");
		incorrectPopup = new InfoPopup("Incorrect");

		categories = new String[] {"Polynomial Arithmetic",
		"End Behavior", "Factoring", "Remainder Theorem",
		"Graphing"};

        mcqList = new ArrayList<ArrayList<MultipleChoiceQuestion>>();
        saqList = new ArrayList<ArrayList<ShortAnswerQuestion>>();

		for(int i=1; i<=5; i++)
		{
			mcqList.add(new ArrayList<>());
			saqList.add(new ArrayList<>());
		}

		JPanel bottomBtnHolder = getBottomButtons();
		add(bottomBtnHolder, BorderLayout.SOUTH);

        loadProblems();
		System.out.println(mcqList);
		System.out.println(saqList);
    }

    public void getProblem()
    {
		boolean successfulProblem = false;
		String chosenCategory = new String();
		int problemType = -1;

		while(!successfulProblem)
		{
			int chosen = GameData.getRandom(0, 4);
			problemType = GameData.getRandom(1, 2); ///mcq or saq
			
			chosenCategory = categories[chosen];

			currSAQ = null;
			currMCQ = null;
			
			if(problemType == 1) ///mcq
			{
				ArrayList<MultipleChoiceQuestion> category = mcqList.get(chosen);

				if(category.size() > 0 && !(prev == problemType && prevCategory.equals(chosenCategory)))
				{
					currMCQ = category.get(GameData.getRandom(0, category.size()-1));
					successfulProblem = true;
				}
			}
			else ///saq
			{
				ArrayList<ShortAnswerQuestion> category = saqList.get(chosen);

				if(category.size() > 0 && !(prev == problemType && prevCategory.equals(chosenCategory)))
				{
					currSAQ = category.get(GameData.getRandom(0, category.size()-1));
					successfulProblem = true;
				}
			}
		}

		prev = problemType;
		prevCategory = chosenCategory;

		JPanel contentHolder = new JPanel(new GridLayout(2,1));
		ContentPanel contentPanel = new ContentPanel(chosenCategory);
		AnswerPanel userAnswer = new AnswerPanel();
		
		contentHolder.add(contentPanel);
		contentHolder.add(userAnswer);

		if(currProblemContent != null)
		{
			remove(currProblemContent);
		}
		currProblemContent = contentHolder;
		add(contentHolder, BorderLayout.CENTER);
	}

    ///loads problems
    public void loadProblems()
    {
        String fileName = "problems.txt";
        File f = new File(fileName);
        Scanner read = null;

        try
        {
            read = new Scanner(f);

            while(read.hasNext())
            {
                String line = read.nextLine();

                String category;
                String questionType;
                String question;
                
                if(line.indexOf("|") != -1)
                {
                    category = GameData.getDataTo(line, "|");
                    line = GameData.dataAfter(line, "|");

                    questionType = GameData.getDataTo(line, "|");
                    line = GameData.dataAfter(line, "|");

                    if(questionType.equals("Multiple Choice"))
                    {
                        question = line;
                        
                        String[] answerChoices = new String[4];
						String[] explanations = new String[4];
                        int answer = -1;
                        for(int i=0; i<answerChoices.length; i++)
                        {
                            line = read.nextLine();
                            if(line.indexOf("!") != -1)
                            {
                                answer = i;
								answerChoices[i] = GameData.getDataTo(line, "!");
                            }
							else
							{
								answerChoices[i] = GameData.getDataTo(line, "|");
							}
							explanations[i] = GameData.dataAfter(line, "|");
                        }

                        addQuestion(category, new MultipleChoiceQuestion(question,
							answerChoices, answer, explanations));
                    }
                    else
                    {
                        question = GameData.getDataTo(line, "|");
                        line = GameData.dataAfter(line, "|");

                        String answer = GameData.getDataTo(line, "|");
						line = GameData.dataAfter(line, "|");

						String explanation = line;

                        addQuestion(category, new ShortAnswerQuestion(question,
							answer, explanation));
                    }
                }
            }
        }
        catch(FileNotFoundException e)
        {
            System.err.printf("Error: Could not locate file \"%s\".", fileName);
        }
    }

	public void addQuestion(String categoryIn, MultipleChoiceQuestion mcqIn)
	{
		int category = -1;
		for(int i=0; i<categories.length; i++)
		{
			if(categories[i].equals(categoryIn))
			{
				category = i;
			}
		}
		
		if(category == -1)
		{
			System.err.printf("Error: Invalid category: %s", categoryIn);
		}
		else
		{
			mcqList.get(category).add(mcqIn);
		}
	}
	
	public void addQuestion(String categoryIn, ShortAnswerQuestion saqIn)
	{
		int category = -1;
		for(int i=0; i<categories.length; i++)
		{
			if(categories[i].equals(categoryIn))
			{
				category = i;
			}
		}
		
		if(category == -1)
		{
			System.err.printf("Error: Invalid category: %s", categoryIn);
		}
		else
		{
			saqList.get(category).add(saqIn);
		}
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
		public void actionPerformed(ActionEvent evt)
		{
			String command = evt.getActionCommand();

			if(command.equals("SUBMIT"))
			{
				if(selectedAnswer != null)
				{

					String answer;
					if(currMCQ == null)
					{
						answer = currSAQ.getAnswer();
					}
					else
					{
						answer = currMCQ.getAnswerChoices()[currMCQ.getAnswer()];
					}

					if(answer.equalsIgnoreCase(selectedAnswer))
					{
						String content;
						if(currMCQ == null)
						{
							content = "Nice job! You got the " +
								"question right!\n\nYour Answer: " + selectedAnswer +
								"\nActual Answer: " + answer + "\n\nExplanation: " +
								currSAQ.getExplanation();
						}
						else
						{
							content = "Nice job! You got the " +
							"question right!\n\nYour Answer: " + selectedAnswer +
							"\nActual Answer: " + answer + "\n\nExplanations:";

							String[] options = currMCQ.getAnswerChoices();
							String[] explanations = currMCQ.getExplanations();
							for(int i=0; i<explanations.length; i++)
							{
								content += "\nOption " + (i+1) + " (" + options[i] + ")";

								if(i == currMCQ.getAnswer())
								{
									content += " is correct because ";
								}
								else
								{
									content += " is incorrect because ";
								}

								content += explanations[i];
							}
						}
						correctPopup.setContent(content);
						correctPopup.show();
					}
					else
					{
						String content;
						if(currMCQ == null)
						{
							content = "Better luck next time... You got the " +
								"question wrong.\n\nYour Answer: " + selectedAnswer +
								"\nActual Answer: " + answer + "\n\nExplanation: " +
								currSAQ.getExplanation();
						}
						else
						{
							content = "Better luck next time... You got the " +
							"question wrong.\n\nYour Answer: " + selectedAnswer +
							"\nActual Answer: " + answer + "\n\nExplanations: ";

							String[] options = currMCQ.getAnswerChoices();
							String[] explanations = currMCQ.getExplanations();
							for(int i=0; i<explanations.length; i++)
							{
								content += "\nOption " + (i+1) + " (" + options[i] + ")";

								if(i == currMCQ.getAnswer())
								{
									content += " is correct because ";
								}
								else
								{
									content += " is incorrect because ";
								}

								content += explanations[i];
							}
						}
						incorrectPopup.setContent(content);
						incorrectPopup.show();
					}

					CardLayout cards = GameData.getCardLayout();
					JPanel holder = GameData.getCardHolder();
					cards.show(holder, "intermission");
				}
			}
			else if(command.equals("RETURN"))
			{
				CardLayout cards = GameData.getCardLayout();
				JPanel holder = GameData.getCardHolder();
				cards.show(holder, "intermission");
			}
		}
	}

    ///to be completed
    class ContentPanel extends JPanel
    {
		public ContentPanel(String categoryIn)
		{
			setBackground(Color.GRAY);
			setLayout(new FlowLayout(FlowLayout.CENTER, 500, 50));
			if(currMCQ == null) ///problem type is saq
			{
				Label category = new Label(categoryIn.toUpperCase(), 65);
				Label question = new Label(currSAQ.getQuestion(), 40);
				add(category);
				add(question);
			}
			else ///problem type is mcq
			{
				Label category = new Label(categoryIn.toUpperCase(), 65);
				Label question = new Label(currMCQ.getQuestion(), 40);
				add(category);
				add(question);
			}
		}
    }
    
    class AnswerPanel extends JPanel
    {
		private JTextField answerField;

		public AnswerPanel()
		{
			setBackground(Color.DARK_GRAY);
			setLayout(new FlowLayout(FlowLayout.CENTER, 20, 60));
			selectedAnswer = null;

			if(currMCQ == null) ///problem type is saq
			{
				answerField = getAnswerField();
				add(answerField);
			}
			else ///problem type is mcq
			{
				JRadioButton[] answerChoices = getAnswerChoices();

				for(int i=0; i<answerChoices.length; i++)
				{
					add(answerChoices[i]);
				}
			}
		}

		public JTextField getAnswerField()
		{
			JTextField toReturn = new JTextField("Type your answer.", 30);
			AnswerFieldHandler answerFieldHandler = new AnswerFieldHandler(); 

			toReturn.setPreferredSize(new Dimension(toReturn.getWidth(), 50));
			toReturn.setFont(new Font("SansSerif", Font.BOLD, 30));
			toReturn.setForeground(Color.GRAY);
			toReturn.addFocusListener(answerFieldHandler);
			toReturn.addActionListener(answerFieldHandler);

			return toReturn;
		}

		public JRadioButton[] getAnswerChoices()
		{
			JRadioButton[] toReturn = new JRadioButton[4];
			ButtonGroup bg = new ButtonGroup();
			String[] answerChoices = currMCQ.getAnswerChoices();
			AnswerChoiceHandler ansHandler = new AnswerChoiceHandler();

			for(int i=0; i<answerChoices.length; i++)
			{
				toReturn[i] = new JRadioButton(answerChoices[i]);
				toReturn[i].setFont(new Font("SansSerif", Font.BOLD, 30));
				toReturn[i].setForeground(Color.WHITE);
				toReturn[i].addActionListener(ansHandler);
				bg.add(toReturn[i]);
			}

			return toReturn;
		}

		class AnswerChoiceHandler implements ActionListener
		{
			public void actionPerformed(ActionEvent evt)
			{
				selectedAnswer = evt.getActionCommand();
			}
		}
		//future: add all non-panel classes as nested classes in an Utility class 
		class AnswerFieldHandler implements ActionListener, FocusListener
		{
			public void actionPerformed(ActionEvent evt)
			{
				selectedAnswer = evt.getActionCommand();
			}

			public void focusGained(FocusEvent evt)
			{
				answerField.setForeground(Color.BLACK);
				answerField.setText("");
			}

			public void focusLost(FocusEvent evt)
			{
				answerField.setForeground(Color.GRAY);
				answerField.setText("Type your answer.");
			}
		}
	}
}
