import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;;

/**
 * Data Loader for the JSON
 * @author Blake Turner
 */
public class DataLoader extends DataConstants{

	/**public static void main(String[] args) {
	ArrayList<User> students = getUsers();
		for(User student : students){
			System.out.println(student);
		}

	ArrayList<Course> courses = getCourses();
		for(Course course : courses){
			System.out.println(course);
		}
	}*/

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
                	users.add(new Student(id, firstName, lastName, email, password, dob, overallGPA, favoriteLanguages, type));
				} else {
					users.add(new CourseCreator(id, firstName, lastName, email, password, dob, type));
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

//********************************************************************************************************************************************************** */
	
    public static ArrayList<Course> getCourses(){
		ArrayList<Course> course = new ArrayList<Course>();
		UserList user = UserList.getInstance();
		//Hash Map of Students and Grades
		HashMap<User, ArrayList<Double>> courseHashMap = new HashMap<User, ArrayList<Double>>();
		//When Course is Made go find Student and Update
		try {
			FileReader reader = new FileReader(COURSE_FILE_NAME);
			//JSONParser parser = new JSONParser();
			JSONArray courseJSON = (JSONArray)new JSONParser().parse(reader);
			
			for(int i=0; i < courseJSON.size(); i++) {
				JSONObject personJSON = (JSONObject)courseJSON.get(i);

				UUID courseID = UUID.fromString( (String)personJSON.get(COURSE_ID) );
				String title = (String)personJSON.get(COURSE_TITLE);
				Language language = getLanguage( (String)personJSON.get(COURSE_LANGUAGE) );
				UUID courseCreatorUUID = UUID.fromString((String)personJSON.get(COURSE_CREATOR_ID));
				User courseCreatorID = (CourseCreator)user.getUserByID(courseCreatorUUID);

				ArrayList<Module> modules = getModules(personJSON);
				

				course.add(new Course(courseID, title, language, courseCreatorID, modules));

				//Course Made

				/**Find Student
				UUID studentUUID = UUID.fromString((String)personJSON.get(STUDENT_ID));
				User studentID = (Student)user.getUserByID(studentUUID);

				//Update Grade
				Quiz currentQuiz = modules.get(i).getQuiz();
				ArrayList<Double> moduleGrade = Quiz.getQuizGrade(currentQuiz);

				courseHashMap.put(studentID, moduleGrade);*/
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

	private static ArrayList<Module> getModules(JSONObject courseJSON){
		
		ArrayList<Module> modules = new ArrayList<>();
		JSONArray modulesJSON = (JSONArray)courseJSON.get(MODULE_ARRAY);
		JSONArray commentJSON = (JSONArray)courseJSON.get(COMMENT_ARRAY);
			
		for(int i=0; i < modulesJSON.size(); i++) {
			JSONObject moduleJSON = (JSONObject)modulesJSON.get(i);

			String moduleTitle = (String)moduleJSON.get(MODULE_TITLE);

			ArrayList<Topic> topics = getTopics(moduleJSON);
			
			ArrayList<Question> questions = getQuestions(moduleJSON);

			Quiz quiz = getQuiz(questions);

			ArrayList<Comment> comments = getComments(commentJSON);

			modules.add(new Module(moduleTitle, topics, quiz, comments));
		}
		
		return modules;
	}

	private static ArrayList<Topic> getTopics(JSONObject courseJSON){
		
		ArrayList<Topic> topics = new ArrayList<>();
		JSONArray topicsJSON = (JSONArray)courseJSON.get(TOPIC_ARRAY);
			
		for(int i=0; i < topicsJSON.size(); i++) {
			JSONObject topicJSON = (JSONObject)topicsJSON.get(i);

			String topicTitle = (String)topicJSON.get(TOPIC_TITLE);
			String lesson = (String)topicJSON.get(TOPIC_LESSON);
			topics.add(new Topic(topicTitle, lesson));
		}
		
		return topics;
	}

	private static Quiz getQuiz(ArrayList<Question> questions){
			return new Quiz(questions);
	}

	private static ArrayList<Question> getQuestions(JSONObject courseJSON){

		ArrayList<Question> questions = new ArrayList<>();

		JSONArray questionsJSON = (JSONArray)courseJSON.get(QUIZ_ARRAY);
			
		for(int i=0; i < questionsJSON.size(); i++) {
			JSONObject questionJSON = (JSONObject)questionsJSON.get(i);

			String question = (String)questionJSON.get(QUESTION_STRING);
			ArrayList<String> answers = (JSONArray)questionJSON.get(QUESTION_ANSWERS);
			Long correctAnswer = (Long)questionJSON.get(QUESTION_CORRECT_ANSWER);

			questions.add(new Question(question, answers, correctAnswer));
		}
		
		return questions;
	}

	private static ArrayList<Comment> getComments(JSONArray commentsJSON){
		UserList user = UserList.getInstance();

		ArrayList<Comment> comments = new ArrayList<>();

		for(int i=0; i < commentsJSON.size(); i++) {
			JSONObject commentJSON = (JSONObject)commentsJSON.get(i);

			UUID studentUUID = UUID.fromString( (String)commentJSON.get(COMMENT_ID) );
			User studentID = (Student)user.getUserByID(studentUUID);
			String text = (String)commentJSON.get(COMMENT_TEXT);
			ArrayList<Comment> replies = getComments( (JSONArray)commentJSON.get(COMMENT_REPLIES) );
			comments.add(new Comment(studentID, text, replies));
		}

		return comments;
	}
}
