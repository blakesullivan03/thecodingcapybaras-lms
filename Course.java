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
         //creating new course
    }
    
    /**
     * Loading from JSON
     * @param id
     * @param title
     * @param language
     */
    public Course(UUID id, String title, Language language){

    }
    
    public UUID getID(){

    }

    public Language getLanguage(){

    }

    public String getTitle(){

    }

    public void enroll(Student student){

    }

    public void addModule(Module module){

    }

    public void deleteModule(Module module){

    }


    public void editModuleTitle(Module old, Module new){

    }

    public void leaveComment(String comment){

    }

    public ArrayList<CourseProfile> getProfiles(){

    }

    public Module getMoudleByIndex(int index){

    }


}
