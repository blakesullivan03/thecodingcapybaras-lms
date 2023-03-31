import java.util.ArrayList;
import java.util.Stack;

/**
 * A module for a course
 */
public class Module {
    private String title;
    private Language language;
    private ArrayList<Topic> topics;
    private Quiz quiz;
    private ArrayList<Question> questions;
    private ArrayList<Comment> comments;

    /**
     * Creating a new module
     * @param title the title of the module
     * @param topics the topics of a module
     * @param quiz the quiz of a module
     * @param comments the comments in a module
     */
    public Module(String title, ArrayList<Topic> topics, Quiz quiz, ArrayList<Comment> comments){
        this.title = title;
        this.topics = topics;
        this.quiz = quiz;
        this.comments = comments;
    }

    /**
     * Editing the title of a module
     * @param title the title/description of a module
     */
    public void editTitle(String title){
        this.title = title;
    }

    /**
     * Getting the title of a module
     * @return the title of a module
     */
    public String getTitle(){
        return title;
    }

    /**
     * Getting the language (JavaScript, C, or Python) of a module
     * @return the language (JavaScript, C, or Python) of the module
     */
    public Language getLanguage(){
        return language;
    }

    /**
     * Getting the topics of a module
     * @return the list of topics for a module
     */
    public ArrayList<Topic> getTopics(){
        return topics;
    }

    /**
     * Getting the comments for a module
     * @return the list of comments under a module
     */
    public ArrayList<Comment> getComments(){
        return comments;
    }

    /**
     * Getting the question by its index
     * @param index the index of a question
     * @return the question based on its index
     */
    public Question getQuestionByIndex(int index){
        return questions.get(index); 
    }

    /**
     * Getting the questions for a quiz
     * @return the list of questions for a quiz
     */
    public ArrayList<Question> getQuestions(){
        Stack<Question> questions = quiz.getQuestions();
        ArrayList<Question> ret = new ArrayList<Question>();
        while(!questions.empty()) {
            ret.add(questions.pop());
        }
        System.out.println(ret);
        return ret;
        
    }

    /**
     * Getting the topic by index
     * @param index the index of a topic 
     * @return the topic based on its index
     */
    public Topic getTopicByIndex(int index){
        return topics.get(index); 
    }

    /**
     * Adding a quiz to the module
     * @param quiz the quiz of the module
     */
    public void addQuiz(Quiz quiz){
        this.quiz = quiz;
    }

    /**
     * Getting the quiz
     * @return the quiz with questions
     */
    public Quiz getQuiz(){
        return quiz;
    }

    /**
     * Allowing users to leave a comment in a module
     * @param author the author of the comment
     * @param comment the comment itself
     */
    public void leaveComment(User author, String comment){
        comments.add(new Comment(author, comment));
    }

    /**
     * Allowing users to reply to a comment
     * @param comment the original comment 
     * @param author the author of the reply
     * @param reply the reply to the original comment
     */
    public void leaveReply(Comment comment, User author, String reply){
        comment.reply(author, reply);
    }

    /**
     * Displaying the module's information
     * @return the module's information (topic and lesson) if there is a topic
     * or the module and its title if there is not.
     */
    public String toString(){
        String result = "";
        result += "\nModule: " + title;
        if(topics == null){
            return result;
        }
        else{
            for(Topic topic : topics){
                result += "\n\n" + "Topic: " + topic.getTitle() + "\n" + "Lesson: " + topic.getLesson();
            }
        }
        return result;
    }
}
