import java.util.HashMap;
import java.util.ArrayList;

public class Student extends User{
    private HashMap<Course, CourseProfile> courses;
    private double overallGPA;
    private ArrayList<Language> favoriteLanguage;

    public Student(String firstName, String lastName, String email,
    String username, String password){

    }
    
    public void enroll(Course course){

    }

    public double getGPA(){

    }

    public void addFavoriteLanguage(Language Language){

    }

    public void removeFavoriteLanguage(Language Language){

    }
    
    public void addGrade(Course course, int moduleIndex, double grade){
        
    }

}
