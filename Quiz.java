import java.util.ArrayList;

public class Quiz {
    private ArrayList<Question> questions;
    private ArrayList<Integer> correctAnswers;

    public Quiz(ArrayList<Question> questions){

    }

    public ArrayList<Question> getNextQuestion(){
        if(!hasMoreQuestions()){
            System.out.println("Finished!");
            return null;
        }

        return questions;
    }

    public boolean hasMoreQuestions(){
        if(questions.isEmpty()){
            return false;
        }else{
            return true;
        }
    }

    public void addQuestion(Question question){

    }

    public void deleteQuestion(Question question){

    }

    public ArrayList<Integer> getCorrectAnswers(){
        
    }
}
