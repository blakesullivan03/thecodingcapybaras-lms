import java.util.ArrayList;
import java.util.UUID;

public class Course {
    private UUID id;
    private Language language;
    private ArrayList<Module> modules;
    private String title;
    private ArrayList<CourseProfile> profiles;
    private ArrayList<Comment> comments;
    private UUID courseCreatorUUID;

    /**
     * Creating New Instance of Course 
     * @param title
     * @param language
     */
    public Course(String title, Language language, UUID courseCreatorUUID){
        this.id = UUID.randomUUID();
        this.title = title;
        this.language = language;
        this.courseCreatorUUID = courseCreatorUUID;
    }
    
    /**
     * Loading from JSON
     * @param id
     * @param title
     * @param language
     */
    public Course(UUID id, String title, Language language, UUID courseCreatorUUID){
        this.id = id;
        this.title = title;
        this.language = language;
        this.courseCreatorUUID = courseCreatorUUID;
    }
    
    public UUID getID(){
        return id;
    }

    public UUID getCourseCreatorUUID(){
        return courseCreatorUUID;
    }

    public Language getLanguage(){
        return language;
    }

    public String getTitle(){
        return title;
    }

    public void enroll(Student student){

    }

    public void addModule(Module module){

    }

    public void deleteModule(Module module){

    }


    public void editModuleTitle(Module old, Module newTitle){

    }

    public void leaveComment(String comment){

    }

    public ArrayList<CourseProfile> getProfiles(){
        return profiles;
    }

    public Module getModuleByIndex(int index){
        return modules.get(index);
    }

    public String toString(){
        return title + " " + language;
    }

}
