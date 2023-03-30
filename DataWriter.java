import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.Stack;

public class DataWriter extends DataConstants{

    public static void saveCourses() {
		CourseList course = CourseList.getInstance();
		ArrayList<Course> courses = course.getCourses();
		JSONArray courseJSONArray = new JSONArray();
		
		//creating all the json objects
		for(int i=0; i< courses.size(); i++) {
			courseJSONArray.add(getCourseJSON(courses.get(i)));
			//courseJSONArray.add(getModuleJSONObject(courses.get(i)));
		}
		
		//Write JSON file
        try (FileWriter file = new FileWriter(COURSE_FILE_NAME)) {
 
            file.write(courseJSONArray.toJSONString());
            file.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

    public static void saveStudents() {
		UserList user = UserList.getInstance();
		ArrayList<User> userList = user.getUsers();
		//ArrayList<Student> studentList = user.getStudents();
		JSONArray jsonStudents = new JSONArray();
		
		//creating all the json objects
		for(int i=0; i < userList.size(); i++) {
			jsonStudents.add(getUserJsonObject(userList.get(i)));
		}
		
		//Write JSON file
        try (FileWriter file = new FileWriter(USER_FILE_NAME)) {
 
            file.write(jsonStudents.toJSONString());
            file.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

   /**public static void saveCourseCreator() {
		UserList user = UserList.getInstance();
		ArrayList<User> courseCreatorList = user.getUsers();

		System.out.println(courseCreatorList);

		JSONArray jsonFriends = new JSONArray();
		
		//creating all the json objects
		for(int i=0; i < courseCreatorList.size(); i++) {
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
	public static JSONObject getUserJsonObject(User user){
		JSONObject userDetails = new JSONObject();
		
		userDetails.put(USER_ID, user.getId().toString());
		userDetails.put(USER_FIRST_NAME, user.getFirstName());
		userDetails.put(USER_LAST_NAME, user.getLastName());
        userDetails.put(USER_DOB, user.getDateOfBirth());
		userDetails.put(USER_EMAIL, user.getEmail());
        userDetails.put(USER_PASSWORD, user.getPassword());
		userDetails.put(TYPE, user.getAccountType());
		if(user.getAccountType().equalsIgnoreCase("student"))
		{
			String firstName = user.getFirstName();
			String lastName = user.getLastName();
			String email = user.getEmail();
			String password = user.getPassword();
			Student student = new Student(firstName, lastName, email, password, null, 0, null, TYPE);
			userDetails.put(STUDENT_FAV_LANGUAGES, student.getFavoriteLanguages());
			userDetails.put(STUDENT_OVERALL_GPA, student.getGPA());
		}

		return userDetails;
	}

    /**
     *	Returning Course Creator JSON
     */ 
    public static JSONObject getCourseCreatorJSON(User courseCreator) {
		JSONObject userDetails = new JSONObject();
		userDetails.put(USER_ID, courseCreator.getId().toString());
		userDetails.put(USER_FIRST_NAME, courseCreator.getFirstName());
		userDetails.put(USER_LAST_NAME, courseCreator.getLastName());
        userDetails.put(USER_DOB, courseCreator.getDateOfBirth());
		userDetails.put(USER_EMAIL, courseCreator.getEmail());
        userDetails.put(USER_PASSWORD, courseCreator.getPassword());
		userDetails.put(TYPE, courseCreator.getAccountType());
        
        return userDetails;
	}

    /**
     * Returning Course JSON
     
    public static JSONObject getCourseJSON(Course course) {
		JSONObject courseDetails = new JSONObject();
		courseDetails.put(COURSE_ID, course.getID().toString());
		courseDetails.put(COURSE_TITLE, course.getTitle());
        courseDetails.put(COURSE_LANGUAGE, course.getLanguageString());
		courseDetails.put(COURSE_CREATOR_ID, course.getCourseCreator());
		return courseDetails;
	}
	*/


	/**
 	* Returning Module JSON
	*/
 	
	 public static JSONObject getCourseJSON(Course course) {

		JSONObject main = new JSONObject();
		JSONObject modules = new JSONObject();


    	ArrayList<JSONObject> items = new ArrayList<JSONObject>();
		ArrayList<JSONObject> topicArrayList = new ArrayList<JSONObject>();
		ArrayList<JSONObject> questionArrayList = new ArrayList<JSONObject>();
		//JSONArray quizArrayList = new JSONArray();
		ArrayList<JSONObject> quizArrayList = new ArrayList<JSONObject>(4);
		ArrayList<JSONObject> commentArrayList = new ArrayList<JSONObject>();

		Module module = course.getModules().get(0);;
		ArrayList<Topic> topicsArrayList2 = course.getModuleByIndex(0).getTopics();

		main.put(COURSE_ID, course.getID().toString());
		main.put(COURSE_TITLE, course.getTitle());
        main.put(COURSE_LANGUAGE, course.getLanguageString());
		main.put(COURSE_CREATOR_ID, course.getCourseCreatorUUID().toString());

		for(int i = 0; i < 2; i++){
			JSONObject topics = new JSONObject();
			topics.put(TOPIC_TITLE, course.getModuleByIndex(0).getTopicByIndex(i).getTitle());
			topics.put(TOPIC_LESSON, course.getModuleByIndex(0).getTopicByIndex(i).getLesson());
    		topicArrayList.add(topics);
		}

		Stack<Question> questionStack = module.getQuiz().getQuestions();
        ArrayList<Question> questionsArrayList = new ArrayList<Question>();

        while(!questionStack.isEmpty()) {
            questionsArrayList.add(questionStack.pop());
        }

		for(int i = 0; i < 4; i++){
			JSONObject questions = new JSONObject();
			questions.put(QUESTION_STRING, questionsArrayList.get(i).getQuestion());
			questions.put(QUESTION_ANSWERS, questionsArrayList.get(i).getAnswerChoices());
			questions.put(QUESTION_CORRECT_ANSWER, questionsArrayList.get(i).getCorrectAnswer());
			quizArrayList.add(questions);
		}

		/**for(int i = 0; i < 3; i++){
			JSONObject comments = new JSONObject();
			comments.put(STUDENT_ID, course.getModuleByIndex(0).getComments().get(i).getAuthor());
			comments.put(COMMENT_TEXT, course.getModuleByIndex(0).getComments().get(i).getText());
    		comments.put(COMMENT_REPLIES, course.getModuleByIndex(0).getComments());
			commentArrayList.add(comments);
		}*/

		modules.put(MODULE_TITLE, module.getTitle());
		modules.put(TOPIC_ARRAY, topicArrayList);
		modules.put(QUIZ_ARRAY, quizArrayList);
		modules.put(COMMENT_ARRAY, commentArrayList);
    	items.add(modules);

    	main.put(MODULE_ARRAY, items);

		return main;
	}

	public static void certificateFile() {
		try {
			FileWriter myWriter = new FileWriter("certificate.txt");
			myWriter.write("Congratulations Scout Solace completing your JavaScript course\n!");
			myWriter.write("You have completed to the course with above an 80%!");
			myWriter.close();
			System.out.println("Wrote to file successfully!");
		  } catch (IOException e) {
			System.out.println("Did not succesfully write to the file");
			e.printStackTrace();
		  }
		}
}
