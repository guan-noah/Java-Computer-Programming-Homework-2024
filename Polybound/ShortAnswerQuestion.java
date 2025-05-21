/**
 * NOTE: This class is deprecated, and has been marked for removal.
 * 
 * Krish Kumar
 * Period 6
 * ShortAnswerQuestion.java
 * 
 * This class represents a short answer question.
 **/

public class ShortAnswerQuestion
{
    private String question; ///problem
    private String answer; ///correct answer
    private String explanation;

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

    public String getExplanation()
    {
        return explanation;
    }
}
