import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Stack;

public class Module {
    private String title;
    private Language language;
    private ArrayList<Topic> topics;
    private Quiz quiz;
    private ArrayList<Question> questions;
    private ArrayList<Comment> comments;

    public Module(String title, ArrayList<Topic> topics, Quiz quiz, ArrayList<Comment> comments){
        this.title = title;
        this.topics = topics;
        this.quiz = quiz;
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

    public ArrayList<Question> getQuestions(){
        Stack<Question> questions = quiz.getQuestions();
        ArrayList<Question> ret = new ArrayList<Question>();
        while(!questions.empty()) {
            ret.add(questions.pop());
        }
        return ret;
    }

    public Topic getTopicByIndex(int index){
        return topics.get(index); 
    }

    public void addQuiz(Quiz quiz){
        this.quiz = quiz;
    }

    public Quiz getQuiz(){
        questions = getQuestions();
        quiz = new Quiz(questions);
        return quiz;
    }

    public void leaveComment(User author, String comment){
        comments.add(new Comment(author, comment));
    }

    public void leaveReply(Comment comment, User author, String reply){
        comment.reply(author, reply);
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
