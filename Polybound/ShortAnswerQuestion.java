/**
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

    public ShortAnswerQuestion(String questionIn, String answerIn)
    {
        question = questionIn;
        answer = answerIn;
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
}
