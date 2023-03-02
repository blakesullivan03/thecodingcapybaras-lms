import java.util.ArrayList;
import java.util.UUID;

public class Course {
    private UUID id;
    private Language language;
    private ArrayList<Module> modules;
    private String title;
    private ArrayList<CourseProfile> profiles;
    private ArrayList<Comment> comments;

    /**
     * Creating New Instance of Course 
     * @param title
     * @param language
     */
    public Course(String title, Language language){
        this.id = UUID.randomUUID();
        this.title = title;
        this.language = language;
    }
    
    /**
     * Loading from JSON
     * @param id
     * @param title
     * @param language
     */
    public Course(UUID id, String title, Language language){
        this.id = id;
        this.title = title;
        this.language = language;
    }
    
    public UUID getID(){
        return id;
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

    public Module getMoudleByIndex(int index){

    }


}
