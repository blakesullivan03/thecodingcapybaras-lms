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
		ArrayList<Language> languages = new ArrayList<>();

		for(int i=0; i < jsonLangs.size(); i++) {
			String langString = (String)jsonLangs.get(i);
			languages.add(Language.valueOf(langString.toUpperCase()));
		}

		return languages;
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
		ArrayList<Topic> topics = new ArrayList<>();
		ArrayList<Question> questions = new ArrayList<>();
		ArrayList<Comment> comments = new ArrayList<>();
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
				UUID courseCreatorUUID = UUID.fromString((String)personJSON.get(COURSE_CREATOR_ID));
				User courseCreatorID = (CourseCreator)user.getUserByID(courseCreatorUUID);

				ArrayList<Module> modules = getModules();

				course.add(new Course(courseID, title, language, courseCreatorID, modules));
			}
			
			return course;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private static Language getLanguage(String language){
		System.out.println(" ");
		return Enum.valueOf(Language.class, language.toUpperCase());
	}

	private static ArrayList<Module> getModules(){
		
		ArrayList<Module> modules = new ArrayList<>();
		/**ArrayList<Topic> topics = new ArrayList<>();
		ArrayList<Question> questions = new ArrayList<>();
		ArrayList<Comment> comments = new ArrayList<>();
		UserList user = UserList.getInstance();*/
		try {
			FileReader reader = new FileReader(COURSE_FILE_NAME);
			JSONParser parser = new JSONParser();
			JSONArray courseJSON = (JSONArray)new JSONParser().parse(reader);
			
			for(int i=0; i < courseJSON.size(); i++) {
				JSONObject personJSON = (JSONObject)courseJSON.get(i);

				String moduleTitle = (String)personJSON.get(MODULE_TITLE);

				/**String topicTitle = (String)personJSON.get(TOPIC_TITLE);
				String lesson = (String)personJSON.get(TOPIC_LESSON);
				topics.add(new Topic(topicTitle, lesson));

				String question = (String)personJSON.get(QUESTION_STRING);
				ArrayList<String> answers = getAnswers((JSONArray)personJSON.get(QUESTION_ANSWERS));
				Integer correctAnswer = (Integer)personJSON.get(QUESTION_CORRECT_ANSWER);
				questions.add(new Question(question, answers, correctAnswer));

				UUID studentUUID = UUID.fromString( (String)personJSON.get(COMMENT_ID) );
				User studentID = (Student)user.getUserByID(studentUUID);
				String text = (String)personJSON.get(COMMENT_TEXT);
				ArrayList<Comment> replies = getReplies( (JSONArray)personJSON.get(COMMENT_REPLIES) );
				comments.add(new Comment(studentID, text, replies));*/

				modules.add(new Module(moduleTitle));
			}
			
			return modules;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	/**private static ArrayList<String> getAnswers(JSONArray jsonLangs){
		ArrayList<String> answers = new ArrayList<String>();

		for(int i=0; i < answers.size(); i++) {
			String answerString = (String)answers.get(i);
			answers.add(answerString);
		}

		return answers;
	}
	

	private static ArrayList<Comment> getReplies(JSONArray jsonCommentArray){
		ArrayList<Comment> replies = new ArrayList<>();

		for(int i=0; i < jsonCommentArray.size(); i++) {
			Comment replyString = (Comment)jsonCommentArray.get(i);
			replies.add(replyString);
		}

		return replies;
	}*/

}
