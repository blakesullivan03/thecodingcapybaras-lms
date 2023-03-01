import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DataWriter extends DataConstants{

    public static void saveCourses() {
		CourseList course = CourseList.getInstance();
		ArrayList<Course> friends = course.getCourses();
		JSONArray jsonFriends = new JSONArray();
		
		//creating all the json objects
		for(int i=0; i< friends.size(); i++) {
			jsonFriends.add(getCourseJSON(friends.get(i)));
		}
		
		//Write JSON file
        try (FileWriter file = new FileWriter(COURSE_FILE_NAME)) {
 
            file.write(jsonFriends.toJSONString());
            file.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

    public static void saveStudents() {
		UserList user = UserList.getInstance();
		ArrayList<Student> studentList = user.getStudents();
		JSONArray jsonStudents = new JSONArray();
		
		//creating all the json objects
		for(int i=0; i < studentList.size(); i++) {
			jsonStudents.add(getStudentJSON(studentList.get(i)));
		}
		
		//Write JSON file
        try (FileWriter file = new FileWriter(STUDENT_FILE_NAME)) {
 
            file.write(jsonStudents.toJSONString());
            file.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

    public static void saveCourseCreator() {
		UserList user = UserList.getInstance();
		ArrayList<CourseCreator> courseCreatorList = user.getCourseCreators();
		JSONArray jsonFriends = new JSONArray();
		
		//creating all the json objects
		for(int i=0; i< courseCreatorList.size(); i++) {
			jsonFriends.add(getCourseCreatorJSON(courseCreatorList.get(i)));
		}
		
		//Write JSON file
        try (FileWriter file = new FileWriter(COURSE_CREATOR_FILE_NAME)) {
 
            file.write(jsonFriends.toJSONString());
            file.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

    /**
     * 
     */
    public static JSONObject getUserJSON(Student student) {
		JSONObject userDetails = new JSONObject();
		userDetails.put(STUDENT_ID, student.getId().toString());
		userDetails.put(STUDENT_FIRST_NAME, student.getFirstName());
		userDetails.put(STUDENT_LAST_NAME, student.getLastName());
        userDetails.put(STUDENT_DOB, student.getDateOfBirth());
		userDetails.put(STUDENT_EMAIL, student.getEmail());
        userDetails.put(STUDENT_USERNAME, student.getUserName());
        userDetails.put(STUDENT_PASSWORD, student.getPassword());


        
    return userDetails;
	}

    /**
     *
     */ 
    public static JSONObject getCourseCreatorJSON(CourseCreator courseCreator) {
		JSONObject userDetails = new JSONObject();
		userDetails.put(COURSE_CREATOR_ID, courseCreator.getId().toString());
		userDetails.put(COURSE_CREATOR_FIRST_NAME, courseCreator.getFirstName());
		userDetails.put(COURSE_CREATOR_LAST_NAME, courseCreator.getLastName());
		userDetails.put(COURSE_CREATOR_DOB, courseCreator.getDateOfBirth());
		userDetails.put(COURSE_CREATOR_EMAIL, courseCreator.getEmail());
        userDetails.put(COURSE_CREATOR_PASSWORD, courseCreator.getPassword());
        
        return userDetails;
	}

    /**
     * 
     */
    public static JSONObject getCourseJSON(Course course) {
		JSONObject userDetails = new JSONObject();
		userDetails.put(STUDENT_ID, course.getId().toString());
		userDetails.put(STUDENT_FIRST_NAME, course.getFirstName());
		userDetails.put(STUDENT_LAST_NAME, course.getLastName());
        userDetails.put(STUDENT_DOB, course.getDateOfBirth());
		userDetails.put(STUDENT_EMAIL, course.getEmail());
        userDetails.put(STUDENT_USERNAME, course.getUserName());
        userDetails.put(STUDENT_PASSWORD, course.getPassword());


        
    return userDetails;
	}

}
