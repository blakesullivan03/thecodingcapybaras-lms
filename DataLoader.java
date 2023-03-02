import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;;

/**
 * Data Loader for the JSON
 * @author Blake Turner
 */
public class DataLoader extends DataConstants{

	public static void main(String[] args) {
	ArrayList<User> students = getUsers();
		for(User student : students){
			System.out.println(student);
		}
		//get instance of userlist
		//UserList.getInstance();
	ArrayList<Course> courses = getCourses();
		for(Course course : courses){
			System.out.println(course);
		}
	}

    public static ArrayList<User> getUsers() {
		ArrayList<User> users = new ArrayList<User>();
		
		try {
			FileReader reader = new FileReader(USER_FILE_NAME);
			JSONParser parser = new JSONParser();
			JSONArray peopleJSON = (JSONArray)new JSONParser().parse(reader);
			
			for(int i=0; i < peopleJSON.size(); i++) {
				JSONObject personJSON = (JSONObject)peopleJSON.get(i);
                UUID id = UUID.fromString((String)personJSON.get(USER_ID));
				String firstName = (String)personJSON.get(USER_FIRST_NAME);
				String lastName = (String)personJSON.get(USER_LAST_NAME);
				String email = (String)personJSON.get(USER_EMAIL);
				String type = (String)personJSON.get(STUDENT_TYPE);
				String password = (String)personJSON.get(USER_PASSWORD);
                Date dob = getDateFromString((String)personJSON.get(USER_DOB));

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
			languages.add(Language.valueOf(langString));
		}

		return languages;
	}

	private static Language getLanguage(String language){
		Language lang = Language.valueOf(language);
		return lang;
	}

	private static Date getDateFromString(String data){
		try {
            return new SimpleDateFormat("MM/dd/yyyy").parse(data);
        } catch (Exception e) {
            System.out.println("here");
            return new Date();
        }
	}


	//before I start this
	//Finish the UserList
    public static ArrayList<Course> getCourses(){
		ArrayList<Course> course = new ArrayList<Course>();
		//UserList.getInstance().getUserByID
		UserList user = UserList.getInstance();
		try {
			FileReader reader = new FileReader(COURSE_FILE_NAME);
			JSONParser parser = new JSONParser();
			JSONArray courseJSON = (JSONArray)new JSONParser().parse(reader);
			
			for(int i=0; i < courseJSON.size(); i++) {
				JSONObject personJSON = (JSONObject)courseJSON.get(i);
				UUID courseID = UUID.fromString( (String)personJSON.get(COURSE_ID) );
				String title = (String)personJSON.get(COURSE_TITLE);
				Language language = getLanguage( (String)personJSON.get(COURSE_LANGUAGE) );
				UUID courseCreatorUUID = (UUID)personJSON.get(COURSE_CREATOR_ID);
				
				course.add(new Course(courseID, title, language, courseCreatorUUID));
			}
			
			return course;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
