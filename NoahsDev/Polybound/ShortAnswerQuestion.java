/**
 * Krish Kumar
 * Period 6
 * ShortAnswerQuestion.java
 * 
 * This class represents a short answer question.
 **/

public class ShortAnswerQuestion
{
    private String question, answer, explanation; ///problem, correct answer, explanation

    public ShortAnswerQuestion(String questionIn, String answerIn, String explanationIn)
    {
        question = questionIn;
        answer = answerIn;
        explanation = explanationIn;
    }

    /**
     * Returns the question. 
     **/
    public String getQuestion()
    {
        return question;
    }

    /**
     * Returns the answer. 
     **/
    public String getAnswer()
    {
        return answer;
    }
    
    /*
     * Returns the explanation.
     */
    public String getExplanation()
    {
		return explanation;
	}
}
