import java.util.UUID;

public class CourseCreator extends User{
    private CourseList courses;

    public CourseCreator(UUID id, String firstName, String lastName, String email, String username, String password){
        super(id, firstName, lastName, email, username, password, password);

    }

    public ArrayList<Course> addCourse(Course course){

    }

    public ArrayList<Course> getCourse(){

    }

    public boolean isMyCourse(){

    }
}
