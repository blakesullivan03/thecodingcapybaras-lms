import java.util.ArrayList;

public class Module {
    private String title;
    private ArrayList<Topic> topics;
    private Quiz quiz;
    private ArrayList<Comment> comments;

    public Module(String title){
        this.title = title;
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
        return "Module Title - " + title + " Topics - " + topics + " Quiz - " + quiz;
    }
}
