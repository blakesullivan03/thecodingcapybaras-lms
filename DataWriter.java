import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DataWriter extends DataConstants{

    public static void saveCourses() {
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
	}

    public static void saveStudent() {
		UserList user = UserList.getInstance();
		ArrayList<UserList> friends = user.getUser();
		JSONArray jsonFriends = new JSONArray();
		
		//creating all the json objects
		for(int i=0; i< friends.size(); i++) {
			jsonFriends.add(getPersonJSON(friends.get(i)));
		}
		
		//Write JSON file
        try (FileWriter file = new FileWriter(STUDENT_FILE_NAME)) {
 
            file.write(jsonFriends.toJSONString());
            file.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

    public static void saveCourseCreator() {
		UserList user = UserList.getInstance();
		ArrayList<UserList> friends = user.getUser();
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
    public static JSONObject getStudentJSON(User user) {
		JSONObject userDetails = new JSONObject();
		userDetails.put(STUDENT_ID, user.getId().toString());
		userDetails.put(STUDENT_FIRST_NAME, user.getFirstName());
		userDetails.put(STUDENT_LAST_NAME, user.getLastName());
		userDetails.put(USER_AGE, user.getAge());
		userDetails.put(STUDENT_EMAIL, user.getEmail());
        
        return userDetails;
	}

    /**
     * 
     */
    public static JSONObject courseCreatorJSON(User user) {
		JSONObject userDetails = new JSONObject();
		userDetails.put(STUDENT_ID, user.getId().toString());
		userDetails.put(STUDENT_FIRST_NAME, user.getFirstName());
		userDetails.put(STUDENT_LAST_NAME, user.getLastName());
		userDetails.put(USER_AGE, user.getAge());
		userDetails.put(STUDENT_EMAIL, user.getPhoneNumber());
        
        return userDetails;
	}
}
