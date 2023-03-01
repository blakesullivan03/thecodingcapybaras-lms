import java.util.UUID;
import java.util.ArrayList;

public class CourseCreator extends User{
    private ArrayList<Course> courseArrayList;

    public CourseCreator(UUID id, String firstName, String lastName, String email, String username, String password){
        super(id, firstName, lastName, email, username, password, password);

    }

    public ArrayList<Course> addCourse(Course course){
        courseArrayList.add(course);
        return courseArrayList;
    }

    public ArrayList<Course> getCourse(){
        return courseArrayList;
    }

    public boolean isMyCourse(){

    }
}
