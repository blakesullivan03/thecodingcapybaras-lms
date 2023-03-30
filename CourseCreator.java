import java.util.UUID;
import java.util.ArrayList;
import java.util.Date;

public class CourseCreator extends User{
    private ArrayList<Course> courseArrayList;
    // private ArrayList<Course> courseArrayList = new ArrayList<>();
    // pretty sure without this it will output Nullpointerexception.
    public CourseCreator(String firstName, String lastName, String email, String password, Date DoB, String type){
        super(firstName, lastName, email,password, DoB, type);
    }
    public CourseCreator(UUID id, String firstName, String lastName, String email, String password, Date DoB, String type){
        super(id, firstName, lastName, email,password, DoB, type);

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
        UserList users = UserList.getInstance();
		ArrayList<CourseCreator> courseCreators = users.getCourseCreators();
        String result = "";
        if(courseCreators == null){
            return result;
        }
        else{
            for(CourseCreator CC : courseCreators){
                result += "\n\n" + "UUID: " + CC.getId() + "First Name: " + CC.getFirstName() + " Last Name: " + CC.getLastName() + "DOB: " + CC.getDateOfBirth() + "Email " + CC.getEmail();
            }
        }
        return result;
    }
}
