/**
 * Krish Kumar
 * Period 6
 * ProblemPanel.java
 * 
 * This class stores the problem content, and also displays it.
 **/

import javax.swing.JPanel;

import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class ProblemPanel extends JPanel
{
    ///these store the questions
    private ArrayList<ShortAnswerQuestion> polynomialArithmeticSAQ;
    private ArrayList<MultipleChoiceQuestion> polynomialArithmeticMCQ;
    private ArrayList<ShortAnswerQuestion> endBehaviorSAQ;
    private ArrayList<MultipleChoiceQuestion> endBehaviorMCQ;
    private ArrayList<ShortAnswerQuestion> factoringSAQ;
    private ArrayList<MultipleChoiceQuestion> factoringMCQ;
    private ArrayList<ShortAnswerQuestion> remainderTheoremSAQ;
    private ArrayList<MultipleChoiceQuestion> remainderTheoremMCQ;
    private ArrayList<ShortAnswerQuestion> graphingSAQ;
    private ArrayList<MultipleChoiceQuestion> graphingMCQ;

    public ProblemPanel()
    {
        setLayout(new BorderLayout());

        polynomialArithmeticMCQ = new ArrayList<>();
        polynomialArithmeticSAQ = new ArrayList<>();
        endBehaviorMCQ = new ArrayList<>();
        endBehaviorSAQ = new ArrayList<>();
        factoringMCQ = new ArrayList<>();
        factoringSAQ = new ArrayList<>();
        remainderTheoremMCQ = new ArrayList<>();
        remainderTheoremSAQ = new ArrayList<>();
        graphingMCQ = new ArrayList<>();
        graphingSAQ = new ArrayList<>();

        loadProblems();
    }

    ///to be completed
    public void showProblem()
    {}

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

                    if(questionType.equals("MultipleChoice"))
                    {
                        question = line;
                        
                        String[] answerChoices = new String[4];
                        int answer = 0;
                        for(int i=0; i<answerChoices.length; i++)
                        {
                            answerChoices[i] = read.nextLine();
                            if(answerChoices[i].indexOf("") != -1)
                            {
                                answer = i;
                                answerChoices[i] = GameData.getDataTo(answerChoices[i], "!");
                            }
                        }

                        addMCQ(category, new MultipleChoiceQuestion(question,
							answerChoices, answer));
                    }
                    else
                    {
                        question = GameData.getDataTo(line, "|");
                        line = GameData.dataAfter(line, "|");

                        String answer = line;

                        addSAQ(category, new ShortAnswerQuestion(question, answer));
                    }
                }
            }
        }
        catch(FileNotFoundException e)
        {
            System.err.printf("Error: Could not locate file \"%s\".", fileName);
        }
    }

    /**
     * Adds the multiple choice question to the correct category.
     **/
    public void addMCQ(String categoryIn, MultipleChoiceQuestion mcqIn)
    {
        if(categoryIn.equals("PolynomialArithmetic"))
        {
            polynomialArithmeticMCQ.add(mcqIn);
        }
        else if(categoryIn.equals("EndBehavior"))
        {
            endBehaviorMCQ.add(mcqIn);
        }
        else if(categoryIn.equals("Factoring"))
        {
            factoringMCQ.add(mcqIn);
        }
        else if(categoryIn.equals("PolynomialRemainderTheorem"))
        {
            remainderTheoremMCQ.add(mcqIn);
        }
        else
        {
            graphingMCQ.add(mcqIn);
        }
    }

    /**
     * Adds the short answer question to the correct category.
     **/
    public void addSAQ(String categoryIn, ShortAnswerQuestion saqIn)
    {
        if(categoryIn.equals("PolynomialArithmetic"))
        {
            polynomialArithmeticSAQ.add(saqIn);
        }
        else if(categoryIn.equals("EndBehavior"))
        {
            endBehaviorSAQ.add(saqIn);
        }
        else if(categoryIn.equals("Factoring"))
        {
            factoringSAQ.add(saqIn);
        }
        else if(categoryIn.equals("PolynomialRemainderTheorem"))
        {
            remainderTheoremSAQ.add(saqIn);
        }
        else
        {
            graphingSAQ.add(saqIn);
        }
    }

    ///to be completed
    class ContentPanel extends JPanel
    {
        public ContentPanel()
        {
            
        }
    }
}
