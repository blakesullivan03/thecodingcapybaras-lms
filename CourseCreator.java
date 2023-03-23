import java.util.UUID;
import java.util.ArrayList;
import java.util.Date;

public class CourseCreator extends User{
    private ArrayList<Course> courseArrayList;
    // private ArrayList<Course> courseArrayList = new ArrayList<>();
    // pretty sure without this it will output Nullpointerexception.
    public CourseCreator(UUID id, String firstName, String lastName, String email, String password, Date DoB){
        super(id, firstName, lastName, email,password, DoB);

    }

    public ArrayList<Course> addCourse(Course course){
        courseArrayList.add(course);
        return courseArrayList;
    }

    public ArrayList<Course> getCourse(){
        return courseArrayList;
    }

    public boolean isMyCourse(){
        return true;
    }

    public String toString(){
        return firstName + " " + lastName;
    }
}
