import java.util.HashMap;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.UUID;
import java.util.Date;

public class Student extends User{
    private HashMap<Course, CourseProfile> courses;
    private double overallGPA;
    private ArrayList<Language> favoriteLanguages;

    public Student(String firstName, String lastName, String email, String password, Date DoB, double overallGPA, ArrayList<Language> favoriteLanguages, String type){
        super(firstName, lastName, email, password, DoB, type);
        this.overallGPA = overallGPA;
        this.favoriteLanguages = favoriteLanguages;
        this.courses = new HashMap<Course, CourseProfile>();
    }
    public Student(UUID id, String firstName, String lastName, String email, String password, Date DoB, double overallGPA, ArrayList<Language> favoriteLanguages, String type){
        super(id,firstName, lastName, email, password, DoB, type);
        this.overallGPA = overallGPA;
        this.favoriteLanguages = favoriteLanguages;
        this.courses = new HashMap<Course, CourseProfile>();
    }
    
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

    public double getGPA(){
        return this.overallGPA;

    }

    public void addFavoriteLanguage(Language language){
        favoriteLanguages.add(language);

    }

    public boolean removeFavoriteLanguage(Language language){
        if (!favoriteLanguages.contains(language)) return false;
        favoriteLanguages.remove(language);
        return true;

    }
    
    public void addNewCourseGrade(double grade){
        // this method is just going to update the average grade, individual course grades will be in courseprofile
        // Where are we storing each quiz grade? and where are we averaging all of them together for course grade?

    }

    public void addQuizGrade(Course courseName, double grade) {
        //courses.get(course).enterGrade(grade);
        /*CourseProfile courseProf = new CourseProfile(courseName, currentStudent, grade);
        courses.put(courseName, courseProf);*/
        courses.get(courseName).enterGrade(grade);
    }

    public String toString(){
        return firstName + " " + lastName + " - " + favoriteLanguages + " " + getFormattedDate(DoB);
    }

    private String getFormattedDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        return simpleDateFormat.format(date);
    }
}
