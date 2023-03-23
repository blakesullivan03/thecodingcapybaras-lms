import java.util.ArrayList;
import java.util.UUID;

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

    public UUID getCourseByUUID(UUID id){
        return id;
    }
    
    public Course getCourseByIndex(int index) {
        return courses.get(index);
    }
    public boolean haveCourse(UUID id) {
		for(Course course : courses) {
			if(course.getID().equals(id)) {
				return true;
			}
		}
		
		return false;
	}

    public boolean addCourse(UUID id, String title, Language language, User courseCreatorUUID, ArrayList<Module> modules){
        if(haveCourse(id)){
            return false;
        }

        courses.add(new Course(id, title, language, courseCreatorUUID, modules));
        return true;
    }

    public void deleteCourse(Course course){

    }

    public ArrayList<Course> getCourse(Language language){
        ArrayList<Course> courseLanguages = new ArrayList<>();
        for(Course course : courseLanguages){
            if(course.getLanguage().equals(language)){
                courseLanguages.add(course);
            }
        }
        return courseLanguages;
    }

    public ArrayList<Course> getCourse(String keyword){
        for(Course course : courses){
            if(course.equals(keyword)){
                courses.add(course);
                return courses;
            }
        }
        return null;
    } 

    public ArrayList<Course> getCourses(){
        return courses;
    }

    public void saveCourses(){
        DataWriter.saveCourses();
    }
}
