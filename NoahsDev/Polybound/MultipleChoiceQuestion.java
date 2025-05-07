/**
 * Krish Kumar
 * Period 6
 * MultipleChoiceQuestion.java
 * 
 * This class represents a multiple choice question.
 */

public class MultipleChoiceQuestion
{
    private String question; ///problem
    private String[] answerChoices, explanations; ///answer choices, explanations for choices
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
     * Returns the answer index. 
     **/
    public int getAnswer()
    {
        return answer;//problemPanel implementation requires an index. 
    }
    /*
     * Returns the explanations.
     */
    public String[] getExplanations()
    {
		return explanations;
	}
}
