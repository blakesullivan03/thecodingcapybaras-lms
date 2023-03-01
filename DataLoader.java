import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;;

public class DataLoader extends DataConstants{

	public static void main(String[] args) {
		ArrayList<User> students = getUsers();
		for(User student : students){
			System.out.println(student);
		}
	}

    public static ArrayList<User> getUsers() {
		ArrayList<User> users = new ArrayList<User>();
		
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
				String type = (String)personJSON.get(STUDENT_TYPE);
				String password = (String)personJSON.get(STUDENT_PASSWORD);
                Date dob = getDateFromString((String)personJSON.get(STUDENT_DOB));

				if(type.equalsIgnoreCase("student")){
					ArrayList<Language> favoriteLanguages = getLanguages((JSONArray)personJSON.get(STUDENT_FAV_LANGUAGES));
					double overallGPA = (Double)personJSON.get(STUDENT_OVERALL_GPA);
                	users.add(new Student(id, firstName, lastName, email, password, dob, overallGPA, favoriteLanguages));
				} else {

					users.add(new CourseCreator(id, firstName, lastName, email, password, dob));
				}
			}
			
			return users;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	private static ArrayList<Language> getLanguages(JSONArray jsonLangs){
		ArrayList<Language> languages = new ArrayList();

		for(int i=0; i < jsonLangs.size(); i++) {
			String langString = (String)jsonLangs.get(i);
			System.out.println(langString);
		}

		return languages;
	}

	private static Date getDateFromString(String data){
		return new Date();
	}

    public static ArrayList<Course> getCourses(){
		ArrayList<Course> course = new ArrayList<Course>();
		
		try {
			FileReader reader = new FileReader(COURSE_FILE_NAME);
			JSONParser parser = new JSONParser();
			JSONArray courseJSON = (JSONArray)new JSONParser().parse(reader);
			
			for(int i=0; i < courseJSON.size(); i++) {
				JSONObject personJSON = (JSONObject)peopleJSON.get(i);
				String firstName = (String)personJSON.get(COURSE_NAME);
				String lastName = (String)personJSON.get(PEOPLE_LAST_NAME);
				String phoneNumber = (String)personJSON.get(PEOPLE_PHONE_NUMBER);
				
				course.add(new Course(firstName, lastName, phoneNumber));
			}
			
			return course;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
