import java.util.HashMap;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.UUID;
import java.util.Date;

/**
 * A user who is a student, with all their attributes
 */
public class Student extends User{
    private HashMap<Course, CourseProfile> courses;
    private double overallGPA;
    private ArrayList<Language> favoriteLanguages;

    /**
     * Creating a new student with overriden parameters for later use
     * @param firstName the student's first name
     * @param lastName the student's last name
     * @param email the student's email
     * @param password the student's password
     * @param DoB the student's date of birth
     * @param overallGPA the student's overallGPA
     * @param favoriteLanguages the student's favorite languages
     * @param type the type of account (student)
     */
    public Student(String firstName, String lastName, String email, String password, Date DoB, double overallGPA, ArrayList<Language> favoriteLanguages, String type){
        super(firstName, lastName, email, password, DoB, type);
        this.overallGPA = overallGPA;
        this.favoriteLanguages = favoriteLanguages;
        this.courses = new HashMap<Course, CourseProfile>();
    }
    
    /**
     * Creating a new student with the added parameter of id.
     * @param id a unique id assigned to a user
     * @param firstName the student's first name
     * @param lastName the student's last name
     * @param email the student's email
     * @param password the student's password
     * @param DoB the student's date of birth
     * @param overallGPA the student's overallGPA
     * @param favoriteLanguages the student's favorite languages
     * @param type the type of account (student)
     */
    public Student(UUID id, String firstName, String lastName, String email, String password, Date DoB, double overallGPA, ArrayList<Language> favoriteLanguages, String type){
        super(id,firstName, lastName, email, password, DoB, type);
        this.overallGPA = overallGPA;
        this.favoriteLanguages = favoriteLanguages;
        this.courses = new HashMap<Course, CourseProfile>();
    }
    
    /**
     * Enrolling into a course
     * @param course the course being enrolled into
     */
    public void enroll(Course course){
        //System.out.println(course.getTitle() + "1");
        CourseProfile courseProfile =  new CourseProfile(course, this);
        courses.put(course, courseProfile);
    }

    public HashMap<Course, CourseProfile> getCourses() {
        //debug println
        //for (Course c : courses.keySet())
            //System.out.println(c.getTitle());
        return courses;
    }

    /**
     * Getting the averages of all the grades
     * @return the overall GPA
     */
    public double getGPA(){
        return this.overallGPA;

    }

    /**
     * The user's favorite languages
     * @return a list of the users favorite languages
     */
    public ArrayList<Language> getFavoriteLanguages(){
        return favoriteLanguages;
    }

    /**
     * Allowing a user to add a favorite language
     * @param language the language being added to the favorite language list
     */
    public void addFavoriteLanguage(Language language){
        favoriteLanguages.add(language);

    }

    /**
     * Allowing the user to remove a language from their favorite languages
     * @param language the language being removed from the list
     * @return true if the language the language being removed is already in the list
     * or false if the language trying to be removed is not in the list
     */
    public boolean removeFavoriteLanguage(Language language){
        if (!favoriteLanguages.contains(language)) return false;
        favoriteLanguages.remove(language);
        return true;

    }
    
    /* 
    public void addNewCourseGrade(double grade){
        // this method is just going to update the average grade, individual course grades will be in courseprofile
        // Where are we storing each quiz grade? and where are we averaging all of them together for course grade?

    }
*/

    /**
     * Adding the quiz grade for the student
     * @param courseName the name of the course
     * @param grade the grade for the quiz in the course
     */
    public void addQuizGrade(Course courseName, double grade) {
        //courses.get(course).enterGrade(grade);
        /*CourseProfile courseProf = new CourseProfile(courseName, currentStudent, grade);
        courses.put(courseName, courseProf);*/
        courses.get(courseName).enterGrade(grade);
    }

    /**
     * Displaying the student's information
     * @return the student's information if there is a student
     * or a blank string if there is not a student.
     */
    public String toString(){
        UserList users = UserList.getInstance();
		ArrayList<Student> students = users.getStudents();
        String result = "";
        if(students == null){
            return result;
        }
        else{
            for(Student currentStudent : students){
                result += "\n\n" + "UUID: " + currentStudent.getId() + "First Name: " + currentStudent.getFirstName() + " Last Name: " + currentStudent.getLastName() + "DOB: " + currentStudent.getDateOfBirth() + "Email " + currentStudent.getEmail();
            }
        }
        return result;
    }

    /**
     * Helper method to make a date in the right format
     * @param date the sample date 
     * @return the date in the right format
     */
    private String getFormattedDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        return simpleDateFormat.format(date);
    }
}
