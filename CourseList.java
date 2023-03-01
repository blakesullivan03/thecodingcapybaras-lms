import java.util.ArrayList;
import java.util.UUID;
import java.util.ArrayList;

public class CourseList {
    private static CourseList courseList;
    private ArrayList<Course> courses;

    private CourseList(){
        courses = DataLoader.getCourses();
    }

    public static CourseList getInstance(){
        if(courseList == null){
            courseList = new CourseList();
        }

        return courseList;
    }

    public getCourseByUUID(UUID id){

    }

    public Course addCourse(String title, Language language){

    }

    public void deleteCourse(Course course){

    }

    public ArrayList<Course> getCourses(Language language){
        for(Course course : courses){
            if(course.getLanguage().equals(language)){
                courses.add(course);
                return courses;
            }
        }
        return null;
    }

    public ArrayList<Course> getCourses(String keyword){
        for(Course course : courses){
            if(course.equals(keyword)){
                courses.add(course);
                return courses;
            }
        }
        return null;
    } 


}
