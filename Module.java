import java.util.ArrayList;

public class Module {
    private String title;
    private ArrayList<Topic> topics;
    private Quiz quiz;
    private ArrayList<Question> questions;
    private ArrayList<Comment> comments;

    public Module(String title, ArrayList<Topic> topics, ArrayList<Question> questions){
        this.title = title;
        this.topics = topics;
        this.questions = questions;
    }

    public void editTitle(String title){
        this.title = title;
    }

    public static String getTitle(String title){
        return title;
    }

    public ArrayList<Topic> getTopics(){
        return topics;
    }

    public Topic getTopicByIndex(int index){
        return topics.get(index); 
    }

    public void addQuiz(Quiz quiz){
        this.quiz = quiz;
    }

    public Quiz getQuiz(){
        return this.quiz;
    }

    public String toString(){
        return "Module Title - " + title + " Topics - " + topics + " Quiz - " + questions;
    }
}
