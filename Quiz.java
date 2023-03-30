import java.util.ArrayList;
import java.util.Stack;
/**
 * A Quiz class that contains a collection of questions and corresponding correct and user answers.
 */
public class Quiz {
    private Stack<Question> questions;
    private ArrayList<Integer> correctAnswers;
    private ArrayList<Integer> userAnswers;
    // private int currQIndex = -1;
    // private Question currentQuestion;
    
    /**
     * Constructs a new Quiz object with the given list of questions.
     *
     * @param inQuestions the list of questions to be added to the Quiz
     */
    public Quiz(ArrayList<Question> inQuestions){
        questions = new Stack<Question>();
        for(Question question : inQuestions)
            this.questions.add(question);
        this.correctAnswers = new ArrayList<Integer>();
        addCorrectAnswers();
        this.userAnswers = new ArrayList<Integer>();
    }
    /**
     * Returns the stack of questions in the Quiz.
     *
     * @return the stack of questions
     */
    public Stack<Question> getQuestions(){
        return questions;

    }
    /**
     * Returns the next question in the Quiz, removing it from the stack.
     *
     * @return the next question in the Quiz
     */
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
    /**
     * Returns whether there are more questions in the Quiz.
     *
     * @return true if there are more questions, false otherwise
     */
    public boolean hasMoreQuestions(){
        return !questions.empty();
        /*if(currQIndex >= questions.size()-1){
            return false;
        }else{
            return true;
        }*/
    }
    /**
     * Returns the grade for the current Quiz as an ArrayList of Doubles.
     *
     * @param currentQuiz the Quiz object to calculate the grade for
     * @return an ArrayList of Doubles containing the grade for the Quiz
     */
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
     /**
     * Adds a new question to the Quiz.
     *
     * @param question the question to be added
     */
    public void addQuestion(Question question){
        questions.add(question);
    }
    /**
     * Removes a question from the Quiz.
     *
     * @param question the question to be removed
     */
    public void deleteQuestion(Question question){
        questions.remove(question);
    }
    /**
     * Adds a user's answer to the Quiz.
     *
     * @param userAnswer the user's answer to be added
     */
    public void addUserAnswer(int userAnswer) {
        userAnswers.add(userAnswer);
    }
    /**
     * Returns the user's answers in the Quiz.
     *
     * @return an ArrayList of Integers containing the user's answers
     */
    public ArrayList<Integer> getUserAnswers(){
        return userAnswers;
    }
    /**
     * Returns the correct answers for the Quiz.
     *
     * @return an ArrayList of Integers containing the correct answers
     */
    public ArrayList<Integer> getCorrectAnswers(){
        return correctAnswers;
    }
    /**
     * Adds the correct answers to the Quiz.
     */
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
