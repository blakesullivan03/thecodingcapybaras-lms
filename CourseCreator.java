import java.util.UUID;
import java.util.ArrayList;
import java.util.Date;

/**
 * A user who is a course creator, with all their responsibilities
 */
public class CourseCreator extends User{
    private ArrayList<Course> courseArrayList;
    // private ArrayList<Course> courseArrayList = new ArrayList<>();
    // pretty sure without this it will output Nullpointerexception.
    /**
     * Creating a new course creator with overriden parameters for later use
     * @param firstName the course creator's first name
     * @param lastName the course creator's last name
     * @param email the course creator's email
     * @param password the course creator's password
     * @param DoB the course creator's Date of Birth
     * @param type the type of account (course creator)
     */
    public CourseCreator(String firstName, String lastName, String email, String password, Date DoB, String type){
        super(firstName, lastName, email,password, DoB, type);
    }
    /**
     * Creating a new course creator with the added parameter of id.
     * @param id a unique id assigned to a user
     * @param firstName the course creator's first name
     * @param lastName the course creator's last name
     * @param email the course creator's email
     * @param password the course creator's password
     * @param DoB the course creator's Date of Birth
     * @param type the type of account (course creator)
     */
    public CourseCreator(UUID id, String firstName, String lastName, String email, String password, Date DoB, String type){
        super(id, firstName, lastName, email,password, DoB, type);

    }

    /**
     * Adding a course to the list of courses
     * @param course the course that is being added
     * @return the list of courses in existence
     */
    public ArrayList<Course> addCourse(Course course){
        courseArrayList.add(course);
        return courseArrayList;
    }

    /**
     * Getting the courses
     * @return the existing courses
     */
    public ArrayList<Course> getCourse(){
        return courseArrayList;
    }

   /* 
    public boolean isMyCourse(){
        return true;
    }
*/

    /**
     * Displaying the course creator's information
     * @return the course creator's information if there is one
     * or a blank string if there is not a course creator.
     */
    public String toString(){
        UserList users = UserList.getInstance();
		ArrayList<CourseCreator> courseCreators = users.getCourseCreators();
        String result = "";
        if(courseCreators == null){
            return result;
        }
        else{
            for(CourseCreator CC : courseCreators){
                result += "\n\n" + "UUID: " + CC.getId() + " First Name: " + CC.getFirstName() + " Last Name: " + CC.getLastName() + " DOB: " + CC.getDateOfBirth() + " Email " + CC.getEmail() + " Type: " + CC.getAccountType();
            }
        }
        return result;
    }
}
