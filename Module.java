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

    }

    public ArrayList<Topic> getTopics(){
        return topics;
    }

    public Topic getTopicByIndex(){
        
    }

    public void addQuiz(Quiz quiz){

    }

    public Quiz getQuiz(){

    }

    public String toString(){
        return title + " " + quiz;
    }
}
