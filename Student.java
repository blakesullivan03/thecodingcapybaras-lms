import java.util.HashMap;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.UUID;
import java.util.Date;

public class Student extends User{
    private HashMap<Course, CourseProfile> courses;
    private double overallGPA;
    private ArrayList<Language> favoriteLanguages;

    public Student(String firstName, String lastName, String email, String password, Date DoB, double overallGPA, ArrayList<Language> favoriteLanguages){
        super(firstName, lastName, email, password, DoB);
        this.overallGPA = overallGPA;
        this.favoriteLanguages = favoriteLanguages;
    }
    public Student(UUID id, String firstName, String lastName, String email, String password, Date DoB, double overallGPA, ArrayList<Language> favoriteLanguages){
        super(id,firstName, lastName, email, password, DoB);
        this.overallGPA = overallGPA;
        this.favoriteLanguages = favoriteLanguages;
    }
    
    public void enroll(Course course){
        courses.put(course, new CourseProfile(course, this));
    }

    public HashMap<Course, CourseProfile> getCourses() {
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

    public String toString(){
        return firstName + " " + lastName + " - " + favoriteLanguages + " " + getFormattedDate(DoB);
    }

    private String getFormattedDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        return simpleDateFormat.format(date);
    }
}
