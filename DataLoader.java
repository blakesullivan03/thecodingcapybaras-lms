import java.io.FileReader;
import java.util.ArrayList;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;;

public class DataLoader extends DataConstants{

    public static ArrayList<Student> getStudents() {
		ArrayList<Student> students = new ArrayList<Student>();
		
		try {
			FileReader reader = new FileReader(STUDENT_FILE_NAME);
			JSONParser parser = new JSONParser();
			JSONArray peopleJSON = (JSONArray)new JSONParser().parse(reader);
			
			for(int i=0; i < peopleJSON.size(); i++) {
				JSONObject personJSON = (JSONObject)peopleJSON.get(i);
                UUID id = UUID.fromString((String)personJSON.get(STUDENT_ID));
				String firstName = (String)personJSON.get(STUDENT_FIRST_NAME);
				String lastName = (String)personJSON.get(STUDENT_LAST_NAME);
				String email = (String)personJSON.get(STUDENT_EMAIL);
				String username = (String)personJSON.get(STUDENT_USERNAME);
				String password = (String)personJSON.get(STUDENT_PASSWORD);
                String dob = (String)personJSON.get(STUDENT_DOB);

                students.add(new Student(id, firstName, lastName, email, username, password, dob));
			}
			
			return students;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

    public static ArrayList<CourseCreator> getCourseCreator(){
		ArrayList<CourseCreator> courseCreator = new ArrayList<CourseCreator>();
		
		try {
			FileReader reader = new FileReader(STUDENT_FILE_NAME);
			JSONParser parser = new JSONParser();
			JSONArray peopleJSON = (JSONArray)new JSONParser().parse(reader);
			
			for(int i=0; i < peopleJSON.size(); i++) {
				JSONObject personJSON = (JSONObject)peopleJSON.get(i);
                UUID id = UUID.fromString((String)personJSON.get(STUDENT_ID));
				String firstName = (String)personJSON.get(COURSE_CREATOR_FIRST_NAME);
				String lastName = (String)personJSON.get(COURSE_CREATOR_LAST_NAME);
				String email = (String)personJSON.get(COURSE_CREATOR_EMAIL);
                String username = (String)personJSON.get(STUDENT_USERNAME);
				String password = (String)personJSON.get(STUDENT_PASSWORD);
				
				courseCreator.add(new CourseCreator(id, firstName, lastName, email, username, password));
			}
			
			return courseCreator;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

    public static ArrayList<CourseList> getCourses(){
		ArrayList<CourseList> people = new ArrayList<CourseList>();
		
		try {
			FileReader reader = new FileReader(COURSE_FILE_NAME);
			JSONParser parser = new JSONParser();
			JSONArray peopleJSON = (JSONArray)new JSONParser().parse(reader);
			
			for(int i=0; i < peopleJSON.size(); i++) {
				JSONObject personJSON = (JSONObject)peopleJSON.get(i);
				String firstName = (String)personJSON.get(COURSE_NAME);
				String lastName = (String)personJSON.get(PEOPLE_LAST_NAME);
				String phoneNumber = (String)personJSON.get(PEOPLE_PHONE_NUMBER);
				
				people.add(new Course(firstName, lastName, phoneNumber));
			}
			
			return people;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
