import java.util.ArrayList;

public class Quiz {
    private ArrayList<Question> questions;
    private ArrayList<Integer> correctAnswers;
    

    public Quiz(ArrayList<Question> questions){
        this.questions = questions;
    }

    public ArrayList<Question> getQuestions(){
        return questions;
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
        questions.add(question);
    }

    public void deleteQuestion(Question question){
        questions.remove(question);
    }

    /**public ArrayList<Integer> getCorrectAnswers(){
        
    }*/

    public String toString(){

        String result = "";
        result += "\n" + "\n";
        for(Question question : questions){
            result +=  "\n" + "\n" + question;
        }
        return result;   

    }
}
