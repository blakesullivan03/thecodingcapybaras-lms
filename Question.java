import java.util.ArrayList;

public class Question {
    private String question;
    private ArrayList<String> answers;
    private Long correctAnswer;

    public Question(String question, ArrayList<String> answers, Long correctAnswer){
        this.question = question;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public Long getCorrectAnswer(){
        return correctAnswer;
    }

    public boolean isCorrect(Long userAnswer){
        if(userAnswer == correctAnswer){
            return true;
        } else {
            return false;
        }
    }

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
    public int numAnswers() {
        return answers.size()+1;
    }
}
