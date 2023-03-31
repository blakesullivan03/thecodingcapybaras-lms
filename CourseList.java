import java.util.ArrayList;
import java.util.UUID;
/**
 * A class that represents a collection of courses.
 */
public class CourseList {
    private static CourseList courseList;
    private ArrayList<Course> courses;
    /**
     * Returns the singleton instance of the CourseList class.
     *
     * @return The singleton instance of the CourseList class
     */
    private CourseList(){
        courses = DataLoader.getCourses();
    }
    /**
     * Returns the singleton instance of the CourseList class.
     *
     * @return The singleton instance of the CourseList class
     */
    public static CourseList getInstance(){
        if(courseList == null){
            courseList = new CourseList();
        }

        return courseList;
    }
    /**
     * Returns the UUID of the course with the given UUID.
     *
     * @param id The UUID of the course
     * @return The UUID of the course
     */
    public UUID getCourseByUUID(UUID id){
        return id;
    }
    /**
     * Returns the course at the specified index in the list of courses.
     *
     * @param index The index of the course to return
     * @return The course at the specified index in the list of courses
     */
    public Course getCourseByIndex(int index) {
        return courses.get(index);
    }
    /**
     * Returns the index of the specified course in the list of courses.
     *
     * @param course The course to search for
     * @return The index of the specified course in the list of courses
     */
    public int indexOf(Course course) {
        return courses.indexOf(course);
    }
    /**
     * Returns true if the course with the specified UUID is in the list of courses, false otherwise.
     *
     * @param id The UUID of the course to search for
     * @return True if the course with the specified UUID is in the list of courses, false otherwise
     */
    public boolean haveCourse(UUID id) {
		for(Course course : courses) {
			if(course.getID().equals(id)) {
				return true;
			}
		}
		
		return false;
	}
    /**
     * Adds a new course to the list of courses with the specified UUID, title, language, creator, and modules.
     *
     * @param id The UUID of the new course
     * @param title The title of the new course
     * @param language The language of the new course
     * @param courseCreatorUUID The creator of the new course
     * @param modules The modules of the new course
     * @return True if the course was added successfully, false if a course with the specified UUID already exists
     */
    public boolean addCourse(UUID id, String title, Language language, User courseCreatorUUID, ArrayList<Module> modules){
        if(haveCourse(id)){
            return false;
        }

        courses.add(new Course(id, title, language, courseCreatorUUID, modules));
        return true;
    }
    /**
     * Deletes the specified course from the list of courses.
     *
     * @param course The course to delete
     */
    public void deleteCourse(Course course){

    }
    /**
     * Returns a list of courses with the specified language.
     *
     * @param language The language to search for
     * @return A list of courses with the specified language
     */

    public ArrayList<Course> getCourse(Language language){
        ArrayList<Course> courseLanguages = new ArrayList<>();
        for(Course course : courseLanguages){
            if(course.getLanguage().equals(language)){
                courseLanguages.add(course);
            }
        }
        return courseLanguages;
    }
    /**
    This method returns an ArrayList of courses that match the given language.
    @param language The language to filter the courses by.
    @return ArrayList of courses that match the given language.
    */
    public ArrayList<Course> getCourse(String keyword){
        for(Course course : courses){
            if(course.equals(keyword)){
                courses.add(course);
                return courses;
            }
        }
        return null;
    } 
    /**
    This method returns the ArrayList of courses.
    @return The ArrayList of courses.
    */
    public ArrayList<Course> getCourses(){
        return courses;
    }
    /**
     * This method saves the current list of courses.
     */
    public void saveCourses(){
        DataWriter.saveCourses();
    }
}
