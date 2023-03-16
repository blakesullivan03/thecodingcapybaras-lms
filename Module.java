import java.util.ArrayList;

public class Module {
    private String title;
    private Language language;
    private ArrayList<Topic> topics;
    private Quiz quiz;
    private ArrayList<Question> questions;
    private ArrayList<Comment> comments;

    public Module(String title, ArrayList<Topic> topics, ArrayList<Question> questions, ArrayList<Comment> comments){
        this.title = title;
        this.topics = topics;
        this.questions = questions;
        this.comments = comments;
    }

    public void editTitle(String title){
        this.title = title;
    }

    public static String getTitle(String title){
        return title;
    }

    public Language getLanguage(){
        return language;
    }

    public ArrayList<Topic> getTopics(){
        return topics;
    }

    public Question getQuestionByIndex(int index){
        return questions.get(index); 
    }

    public ArrayList<Question> getQuestion(){
        return questions;
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
        String result = "";
        result += "Module: " + title;
        for(Topic topic : topics){
            result += "\n" + "Topic: " + topic.getTitle() + "\n" + "\n" + topic.getLesson();
        }
        return result;
    }

    public String courseProgressToString(){
        //Input If Statements for Completion Status
        String result = "";
        result += "\n" + "\n" + "Modules: " + "\n" + "\t" + "1) " + title;
        for(Topic topic : topics){
            result += "\n" + "Topic: " + topic.getTitle() + "\n";
        }
        return result;
    }
}
