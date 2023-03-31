import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.Stack;

public class DataWriter extends DataConstants{

	/**public static void main(String[] args) {
		saveCourses();
	}*/

    public static void saveCourses() {
		CourseList course = CourseList.getInstance();
		ArrayList<Course> courses = course.getCourses();
		JSONArray courseJSONArray = new JSONArray();
		
		//creating all the json objects
		for(int i=0; i< courses.size(); i++) {
			courseJSONArray.add(getCourseJSON(courses.get(i)));
		}
		
		//Write JSON file
        try (FileWriter file = new FileWriter(COURSE_FILE_NAME)) {
 
            file.write(courseJSONArray.toJSONString());
            file.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

    public static void saveUsers() {
		UserList user = UserList.getInstance();
		ArrayList<User> userList = user.getUsers();
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

    /**
     * Return Users JSON
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
			String type = user.getAccountType();
			Date DOB = user.DoB;
			double overallGPA = 0.0;
			ArrayList<Language> favoriteLanguages = new ArrayList<>();
			Student student = new Student(firstName, lastName, email, password, DOB, overallGPA, favoriteLanguages, type);
			userDetails.put(STUDENT_FAV_LANGUAGES, student.getFavoriteLanguages());
			userDetails.put(STUDENT_OVERALL_GPA, student.getGPA());
		}

		return userDetails;
	}


	/**
 	* Returning Course JSON
	*/
 	
	 public static JSONObject getCourseJSON(Course course) {

		JSONObject main = new JSONObject();
		JSONObject modules = new JSONObject();

    	ArrayList<JSONObject> items = new ArrayList<JSONObject>();
		ArrayList<JSONObject> topicArrayList = new ArrayList<JSONObject>();
		ArrayList<JSONObject> quizArrayList = new ArrayList<JSONObject>();
		ArrayList<JSONObject> commentArrayList = new ArrayList<JSONObject>();

		
		main.put(COURSE_ID, course.getID().toString());
		main.put(COURSE_TITLE, course.getTitle());
        main.put(COURSE_LANGUAGE, course.getLanguageString());
		main.put(COURSE_CREATOR_ID, course.getCourseCreatorUUID().toString());

		Module module = course.getModuleByIndex(0);

		for(int i = 0; i < module.getTopics().size(); i++){
			JSONObject topics = new JSONObject();
			topics.put(TOPIC_TITLE, module.getTopicByIndex(i).getTitle());
			topics.put(TOPIC_LESSON, module.getTopicByIndex(i).getLesson());
    		topicArrayList.add(topics);
		}

		Stack<Question> questionStack = module.getQuiz().getQuestions();
        ArrayList<Question> questionsArrayList = new ArrayList<Question>();

        while(!questionStack.isEmpty()) {
            questionsArrayList.add(questionStack.pop());
        }

		for(int i = 0; i < questionsArrayList.size(); i++){
			JSONObject questions = new JSONObject();
			questions.put(QUESTION_STRING, questionsArrayList.get(i).getQuestion());
			questions.put(QUESTION_ANSWERS, questionsArrayList.get(i).getAnswerChoices());
			questions.put(QUESTION_CORRECT_ANSWER, questionsArrayList.get(i).getCorrectAnswer());
			quizArrayList.add(questions);
		}

		commentArrayList = getCommentsJSONArray(module.getComments());

		modules.put(MODULE_TITLE, module.getTitle());
		modules.put(TOPIC_ARRAY, topicArrayList);
		modules.put(COMMENT_ARRAY, commentArrayList);
		modules.put(QUIZ_ARRAY, quizArrayList);
    	items.add(modules);

    	main.put(MODULE_ARRAY, items);

		return main;
	}

	public static JSONArray getCommentsJSONArray(ArrayList<Comment> comments){
		JSONArray commentList = new JSONArray();

		for(int i = 0; i < comments.size(); i++){
			JSONObject comment = new JSONObject();
			comment.put(STUDENT_ID, comments.get(i).getAuthor().getId().toString());
			comment.put(COMMENT_TEXT,comments.get(i).getText());
			JSONArray replyArray = getCommentsJSONArray(comments.get(i).getReplies());
			comment.put(COMMENT_REPLIES, replyArray);
			commentList.add(comment);
		}

		return commentList;
	}

	public static void certificateFile() {
		try {
			FileWriter myWriter = new FileWriter("certificate.txt");
			myWriter.write("Congratulations Scout Solace completing your Python Course!\n");
			myWriter.write("You have completed to the course with above an 80%!");
			myWriter.close();
			System.out.println("Wrote to file successfully!");
		  } catch (IOException e) {
			System.out.println("Did not succesfully write to the file");
			e.printStackTrace();
		  }
		}

		public static void studyGuide(Module currentModule) {
			try {
				FileWriter myWriter = new FileWriter("studyguide.txt");
				myWriter.write("Study Guide\n" + currentModule.toString());
				myWriter.close();
				System.out.println("Wrote to file successfully!");
			  } catch (IOException e) {
				System.out.println("Did not succesfully Write to File");
				e.printStackTrace();
			  }
			}
}
