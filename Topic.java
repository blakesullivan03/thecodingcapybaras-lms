public class Topic {
    private String title;
    private String lesson;

    public Topic(String title, String lesson){
        this.title = title;
        this.lesson = lesson;
    }

    public String getTitle(){
        return title;
    }

    public String getLesson(){
        return lesson;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setLesson(String lesson){
        this.lesson = lesson;
    }

    public String toString(){
        return title + " " + lesson;
    }
}
