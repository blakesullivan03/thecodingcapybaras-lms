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
        this.correctAnswers = new ArrayList<Integer>();
        addCorrectAnswers();
        this.userAnswers = new ArrayList<Integer>();
    }

    public Stack<Question> getQuestions(){
        return questions;

    }

    //May need to change to loop back after all questions are done.
    //Pretty sure would return null if we don't.
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

    public static ArrayList<Double> getQuizGrade(Quiz currentQuiz){
        ArrayList<Integer> userAnswers = currentQuiz.getUserAnswers();
        ArrayList<Integer> correctAnswers = currentQuiz.getCorrectAnswers();
        ArrayList<Double> moduleGrades = new ArrayList<>();
        double result = 0;
        int correctAnswer;
        for(int i = 0; i < userAnswers.size(); i++){
           correctAnswer = correctAnswers.get(i);
           if(userAnswers.get(i) == correctAnswer){
              result++;
           }
        }
        moduleGrades.add((result/(double)userAnswers.size()) * 100);
        return moduleGrades;
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

    public ArrayList<Integer> getUserAnswers(){
        return userAnswers;
    }
    
    public ArrayList<Integer> getCorrectAnswers(){
        return correctAnswers;
    }

    public void addCorrectAnswers(){
        for(Question question : this.questions) {
            this.correctAnswers.add(question.getCorrectAnswer().intValue());
        }
    }
    /*
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
