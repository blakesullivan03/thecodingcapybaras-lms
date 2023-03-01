import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.HashMap;

public class DataWriter extends DataConstants{

    /**public static void saveCourses() {
		CourseList course = CourseList.getInstance();
		ArrayList<CourseList> friends = course.getCourses();
		JSONArray jsonFriends = new JSONArray();
		
		//creating all the json objects
		for(int i=0; i< friends.size(); i++) {
			jsonFriends.add(getPersonJSON(friends.get(i)));
		}
		
		//Write JSON file
        try (FileWriter file = new FileWriter(COURSE_FILE_NAME)) {
 
            file.write(jsonFriends.toJSONString());
            file.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
	}*/

    public static void saveStudents() {
		UserList user = UserList.getInstance();
		ArrayList<Student> studentList = user.getStudent();
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
		ArrayList<CourseCreator> friends = user.getCourseCreator();
		JSONArray jsonFriends = new JSONArray();
		
		//creating all the json objects
		for(int i=0; i< friends.size(); i++) {
			jsonFriends.add(getCourseCreatorJSON(friends.get(i)));
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
    public static JSONObject getStudentJSON(Student student) {
		JSONObject userDetails = new JSONObject();
		userDetails.put(STUDENT_ID, student.getId().toString());
		userDetails.put(STUDENT_FIRST_NAME, student.getFirstName());
		userDetails.put(STUDENT_LAST_NAME, student.getLastName());
		userDetails.put(STUDENT_EMAIL, student.getEmail());

        
    return userDetails;
	}

    /**
     *
     */ 
    public static JSONObject getCourseCreatorJSON(User courseCreator) {
		JSONObject userDetails = new JSONObject();
		userDetails.put(STUDENT_ID, courseCreator.getId().toString());
		userDetails.put(STUDENT_FIRST_NAME, courseCreator.getFirstName());
		userDetails.put(STUDENT_LAST_NAME, courseCreator.getLastName());
		userDetails.put(USER_AGE, courseCreator.getAge());
		userDetails.put(STUDENT_EMAIL, courseCreator.getPhoneNumber());
        
        return userDetails;
	}

}
