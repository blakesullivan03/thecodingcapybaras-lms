import java.util.ArrayList;

public class Question {
    private String question;
    private ArrayList<String> answers;
    private Long correctAnswer;

    /**
     * This class represents a question object with a question, a list of answer choices, and the index of the correct answer.
     * */
    public Question(String question, ArrayList<String> answers, Long correctAnswer){
        this.question = question;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
    }
    /**
     * Gets the question text.
     * @return the question text
     * */
    public String getQuestion() {
        return question;
    }
    /**
     * Gets the index of the correct answer in the list of answer choices.
     * @return the index of the correct answer
     */
    public Long getCorrectAnswer(){
        return correctAnswer;
    }
    /**
     * Gets the list of answer choices.
     * @return the list of answer choices
     * */
    public ArrayList<String> getAnswerChoices(){
        return answers;
    }
    /**
    Checks if the specified user answer is correct.
    @param userAnswer the user's answer
    @return true if the user's answer is correct, false otherwise
    */
    public boolean isCorrect(Long userAnswer){
        if(userAnswer == correctAnswer){
            return true;
        } else {
            return false;
        }
    }
    /**
    Returns a string representation of the Question object.
    @return a string representation of the Question object
    */
    public String toString(){
        int i = 0;
        String result = "";
        result += "\n" + "\n" + question;
        for(String answerChoice : answers){
            result +=  "\n" + "\n" + (i+1) + ") " + answerChoice;
            i++;
        }
        return result;

    }
    /**
    Gets the number of answer choices (including the correct answer).
    @return the number of answer choices
    */
    public int numAnswers() {
        return answers.size()+1;
    }
}
