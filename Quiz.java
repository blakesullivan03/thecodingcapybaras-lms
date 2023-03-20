import java.util.ArrayList;
import java.util.Stack;

public class Quiz {
    private Stack<Question> questions;
    private ArrayList<Integer> correctAnswers;
    private ArrayList<Integer> userAnswers;
    // private int currQIndex = -1;
    // private Question currentQuestion;

    public Quiz(ArrayList<Question> inQuestions){
        questions = new Stack<Question>();
        for(Question question : inQuestions)
            this.questions.add(question);
        this.userAnswers = new ArrayList<Integer>();
    }

    public Stack<Question> getQuestions(){
        return questions;

    }

    public Question getNextQuestion(){
        if(!hasMoreQuestions()){
            System.out.println("Finished!");
            return null;
        }
        return questions.pop();
        /*currQIndex++;
        currentQuestion = questions.get(currQIndex)''
        return currentQuestion;*/
    }

    public boolean hasMoreQuestions(){
        return !questions.empty();
        /*if(currQIndex >= questions.size()-1){
            return false;
        }else{
            return true;
        }*/
    }

    public void addQuestion(Question question){
        questions.add(question);
    }

    public void deleteQuestion(Question question){
        questions.remove(question);
    }

    public void addUserAnswer(int userAnswer) {
        userAnswers.add(userAnswer);
    }
    /*public ArrayList<Integer> getCorrectAnswers(){
        return correctAnswers;
    }

    public void setCorrectAnswer(int index, int answer) {
        correctAnswers.set(index, answer);
    }*/

    /* public String toString(){

        String result = "";
        for(Question question : questions){
            result += question;
        }
        return result;   

    }*/
}
