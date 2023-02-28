import java.io.FileReader;
import java.util.ArrayList;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;;

public class DataLoader extends DataConstants{

    public static ArrayList<UserList> loadStudents() {
		ArrayList<UserList> people = new ArrayList<UserList>();
		
		try {
			FileReader reader = new FileReader(STUDENT_FILE_NAME);
			JSONParser parser = new JSONParser();
			JSONArray peopleJSON = (JSONArray)new JSONParser().parse(reader);
			
			for(int i=0; i < peopleJSON.size(); i++) {
				JSONObject personJSON = (JSONObject)peopleJSON.get(i);
				String firstName = (String)personJSON.get(STUDENT_FIRST_NAME);
				String lastName = (String)personJSON.get(STUDENT_LAST_NAME);
				String email = (String)personJSON.get(STUDENT_EMAIL);
				String username = (String)personJSON.get(STUDENT_USERNAME);
				String password = (String)personJSON.get(STUDENT_PASSWORD);

				people.add(new Student(firstName, lastName, email, username, password));
			}
			
			return people;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

    public static ArrayList<UserList> loadCourseCreator() {
		ArrayList<UserList> people = new ArrayList<UserList>();
		
		try {
			FileReader reader = new FileReader(STUDENT_FILE_NAME);
			JSONParser parser = new JSONParser();
			JSONArray peopleJSON = (JSONArray)new JSONParser().parse(reader);
			
			for(int i=0; i < peopleJSON.size(); i++) {
				JSONObject personJSON = (JSONObject)peopleJSON.get(i);
				String firstName = (String)personJSON.get(COURSE_CREATOR_FIRST_NAME);
				String lastName = (String)personJSON.get(COURSE_CREATOR_LAST_NAME);
				String email = (String)personJSON.get(COURSE_CREATOR_EMAIL);
				
				people.add(new CourseCreator(firstName, lastName, email, username, password));
			}
			
			return people;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

    public static ArrayList<CourseList> loadCourses() {
		ArrayList<CourseList> people = new ArrayList<CourseList>();
		
		try {
			FileReader reader = new FileReader(COURSE_FILE_NAME);
			JSONParser parser = new JSONParser();
			JSONArray peopleJSON = (JSONArray)new JSONParser().parse(reader);
			
			for(int i=0; i < peopleJSON.size(); i++) {
				JSONObject personJSON = (JSONObject)peopleJSON.get(i);
				String firstName = (String)personJSON.get(PEOPLE_FIRST_NAME);
				String lastName = (String)personJSON.get(PEOPLE_LAST_NAME);
				String phoneNumber = (String)personJSON.get(PEOPLE_PHONE_NUMBER);
				
				people.add(new Person(firstName, lastName, phoneNumber));
			}
			
			return people;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
