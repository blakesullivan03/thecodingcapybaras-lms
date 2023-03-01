import java.util.HashMap;
import java.util.ArrayList;
import java.util.UUID;

public class Student extends User{
    private HashMap<Course, CourseProfile> courses;
    private double overallGPA;
    private ArrayList<Language> favoriteLanguage;

    public Student(UUID id, String firstName, String lastName, String email, String username, String password, String DoB){
        super(id,firstName, lastName, email, username, password, DoB);
    }
    
    public void enroll(Course course){
        courses.put(course, new CourseProfile(course, new ArrayList<Double>(), this)); 
        // TODO probably better to make a new CourseProfile first and pass that in, not sure.

    }

    public double getGPA(){
        return this.overallGPA;

    }

    public void addFavoriteLanguage(Language language){
        favoriteLanguage.add(language);

    }

    public boolean removeFavoriteLanguage(Language language){
        // TODO not sure if returning a bool is right,
        // but i think we need a way to tell if it actually removed something 
        // or did nothing (the language wasnt there to begin with).
        if (!favoriteLanguage.contains(language)) return false;
        favoriteLanguage.remove(language);
        return true;

    }
    
    public void addGrade(Course course, int moduleIndex, double grade){
        // Where are we storing each quiz grade? and where are we averaging all of them together for course grade?
    }

}
