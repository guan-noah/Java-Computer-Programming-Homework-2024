/**
 * NOTE: This class has been marked to be renamed to Question, as
 * a result of the ShortAnswerQuestion deprecation.
 * Krish Kumar
 * Period 6
 * MultipleChoiceQuestion.java
 * 
 * This class represents a multiple choice question.
 */

public class MultipleChoiceQuestion
{
    private String question; ///problem
    private String[] answerChoices; ///answer choices
    private String[] explanations; ///explanations for choices
    private int answer; ///correct answer index

    public MultipleChoiceQuestion(String questionIn, String[] choicesIn, int answerIn, String[] explanationsIn)
    {
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
}
