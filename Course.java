import java.util.ArrayList;
import java.util.UUID;

public class Course {
    private UUID id;
    private Language language;
    private ArrayList<Module> modules;
    private String title;
    private ArrayList<CourseProfile> profiles;
    private ArrayList<Comment> comments;
    private ArrayList<Topic> topics;
    private ArrayList<Question> questions;
    private User courseCreator;

    /**
     * Creating New Instance of Course 
     * @param title
     * @param language
     */
    public Course(String title, Language language, User courseCreator, ArrayList<Module> modules){
        this.id = UUID.randomUUID();
        this.title = title;
        this.language = language;
        this.courseCreator = courseCreator;
        this.modules = modules;
    }
    
    /**
     * Loading from JSON
     * @param id
     * @param title
     * @param language
     */
    public Course(UUID id, String title, Language language, User courseCreator,   ArrayList<Module> modules){
        this.id = id;
        this.title = title;
        this.language = language;
        this.courseCreator = courseCreator;
        this.modules = modules;
    }
    
    public UUID getID(){
        return id;
    }

    public UUID getCourseCreatorUUID(){
        return courseCreator.getId();
    }

    public Language getLanguage(){
        return language;
    }

     public String getLanguageString(){
        return language.toString();
    }

    public String getTitle(){
        return title;
    }
    
    public void setLanguage(Language language){
        this.language = language;
    }

    public void setTitle(String title){
        this.title = title;
    }

    // May need to add a for loop later on but getting something in here
    public void addModule(Module module){
        modules.add(module);
    }

    // Same thing as add as well
    public void deleteModule(Module module){
        modules.remove(module);
    }


    public void editModuleTitle(Module module, String newTitle){
        module.editTitle(newTitle);
    }

    public void leaveComment(User author, String comment){
        comments.add(new Comment(author, comment));
    }

    public void leaveReply(Comment comment, User author, String reply) {
        comment.reply(author, reply);
    }
    
    public ArrayList<CourseProfile> getProfiles(){
        return profiles;
    }

    public ArrayList<Module> getModules(){
        return modules;
    }

    public Module getModuleByIndex(int index){
        return modules.get(index);
    }

    public String toString(){
        return "Course: " + title + "\nLanguage: " + language + "\nCourse CreatorID: " + getCourseCreatorUUID() + modules;
    }

    public void saveCourses(){
        DataWriter.saveCourses();
    }

}
