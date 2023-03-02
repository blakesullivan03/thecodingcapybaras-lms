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
			jsonStudents.add(getUserJSON(studentList.get(i)));
		}
		
		//Write JSON file
        try (FileWriter file = new FileWriter(USER_FILE_NAME)) {
 
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
        try (FileWriter file = new FileWriter(USER_FILE_NAME)) {
 
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
		userDetails.put(USER_ID, student.getId().toString());
		userDetails.put(USER_FIRST_NAME, student.getFirstName());
		userDetails.put(USER_LAST_NAME, student.getLastName());
        userDetails.put(USER_DOB, student.getDateOfBirth());
		userDetails.put(USER_EMAIL, student.getEmail());
        userDetails.put(USER_PASSWORD, student.getPassword());
		userDetails.put(STUDENT_OVERALL_GPA, student.getGPA());
        
    	return userDetails;
	}

    /**
     *
     */ 
    public static JSONObject getCourseCreatorJSON(CourseCreator courseCreator) {
		JSONObject userDetails = new JSONObject();
		userDetails.put(USER_ID, courseCreator.getId().toString());
		userDetails.put(USER_FIRST_NAME, courseCreator.getFirstName());
		userDetails.put(USER_LAST_NAME, courseCreator.getLastName());
        userDetails.put(USER_DOB, courseCreator.getDateOfBirth());
		userDetails.put(USER_EMAIL, courseCreator.getEmail());
        userDetails.put(USER_PASSWORD, courseCreator.getPassword());
        
        return userDetails;
	}

    /**
     * 
     */
    public static JSONObject getCourseJSON(Course course) {
		JSONObject userDetails = new JSONObject();
		userDetails.put(COURSE_ID, course.getID().toString());
		userDetails.put(COURSE_TITLE, course.getTitle());
        userDetails.put(COURSE_LANGUAGE, course.getLanguage());
		userDetails.put(COURSE_CREATOR_ID, course.getCourseCreatorUUID());

        
    return userDetails;
	}

}
