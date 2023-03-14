import java.util.ArrayList;

public class Question {
    private String question;
    private ArrayList<String> answers;
    private Integer correctAnswer;

    public Question(String question, ArrayList<String> answers, Integer correctAnswers){
        this.question = question;
        this.answers = answers;
        this.correctAnswer = correctAnswers;
    }

    public String getQuestion() {
        return this.question;

    }
    public ArrayList<String> getAnswers(){
        return answers;
    }

    public boolean isCorrect(int userAnswer){
        if(userAnswer == correctAnswer){
            return true;
        } else {
            return false;
        }
    }

    public String toString(){
        return question + " " + answers + " " + correctAnswer;
    }
}
