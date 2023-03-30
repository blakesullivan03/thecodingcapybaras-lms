/**
 * The topic for a module
 */
public class Topic {
    private String title;
    private String lesson;

    /**
     * Creating a new topic
     * @param title the title of the topic
     * @param lesson the lesson of the topic
     */
    public Topic(String title, String lesson){
        this.title = title;
        this.lesson = lesson;
    }

    /**
     * Getting a title
     * @return the title of the topic
     */
    public String getTitle(){
        return title;
    }

    /**
     * Getting a lesson
     * @return the lesson of a specific topic
     */
    public String getLesson(){
        return lesson;
    }

    /**
     * Setting the title for a topic
     * @param title the title of a topic
     */
    public void setTitle(String title){
        this.title = title;
    }

    /**
     * Setting the lesson for a topic
     * @param lesson the lesson for a specific topic
     */
    public void setLesson(String lesson){
        this.lesson = lesson;
    }

    /**
     * Displaying the topic for a module
     * @return the title and lesson of the topic together
     */
    public String toString(){
        return title + " " + lesson;
    }
}
