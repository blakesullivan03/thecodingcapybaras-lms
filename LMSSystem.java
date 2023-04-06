import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.UUID;
/**
*The LMSSystem class represents a Learning Management System application.
* It provides methods to manage users, courses, modules, and other LMS functionalities.
*/
public class LMSSystem{
   // TODO commented out some of the ones i think we should delete ~jonas
    private UserList userList;
    private CourseList courseList;
    private Course currentCourse;
    private Module currentModule;
    private User currentUser;
    private Student currentStudent;
    private CourseCreator currentCourseCreator;
    public Quiz currentQuiz;
    public String email;
    public String password;
    private Topic currentTopic;
    private CourseProfile courseProfile;
    // private Quiz currentQuiz;
    // private Question currentQuestion;
    private Comment comment;
    private ArrayList<User> users;
    private ArrayList<Course> courses;
    private ArrayList<Module> modules;
    private ArrayList<Topic> topics;
    private ArrayList<Question> questions;
    private ArrayList<Comment> comments;
    private ArrayList<String> answers;
    private ArrayList<Double> moduleGrades;
    private String[] moduleTitles = {"Basics", "Strings", "Functions", "Classes", "Conditional Statements", "Exceptions", "File Reading", "Boolean Expressions", "Switch Case", "10"};
    // private ArrayList<Long> array = new ArrayList<>();
      /**
     * Creates an LMSSystem object.
     * Initializes the userList and courseList attributes using the Singleton design pattern.
     */

    public LMSSystem(){
      userList = UserList.getInstance();
      courseList = CourseList.getInstance();
    }
    
    // Accessors and Mutators 
    /**
     * Returns the current user list.
     * @return an ArrayList of User objects representing the current user list
     */
    public ArrayList<User> getUserList() {
      users = userList.getUsers();
      return users;
    }
    /**
     * Returns the current course list.
     * @return an ArrayList of Course objects representing the current course list
     */
   public ArrayList<Course> getCourseList() {
      courses = courseList.getCourses();
      return courses;
   }
   /**
     * Returns the current user.
     * @return a User object representing the current user
     */
   public User getCurrentUser() {
      return currentUser;
   }
   /**
     * Returns the current student.
     * @return a Student object representing the current student
     */
   public Student getCurrentStudent() {
      return currentStudent;
   }
   /**
     * Returns the current course.
     * @return a Course object representing the current course
     */

   public Course getCurrentCourse(){
      return currentCourse;
   }
   /**
     * Returns the current module.
     * @return a Module object representing the current module
     */

   public Module getCurrentModule() {
      return currentModule;
   }
   /**
     * Returns the current course index.
     * @return an int representing the current course index
     */
   public int getCurrentCourseIndex()  {
      return courseList.indexOf(currentCourse);
   }
   /**
     * Returns the current course creator.
     * @return a CourseCreator object representing the current course creator
     */

   public CourseCreator getCurrentCreator() {
      return currentCourseCreator;
   }
   /**
     * Sets the current user or current course creator based on their email and password.
     * @param email a String representing the user's email
     * @param password a String representing the user's password
     */

   public void setUser(String email, String password) {
      UserList usersList = UserList.getInstance();
      User user = usersList.getUser(email);

      String accountType = userList.getUserType(email);
      String firstName = user.getFirstName();
		String lastName = user.getLastName();
		Date DOB = user.DoB;

      if(accountType.equalsIgnoreCase("student")){
			double overallGPA = 0.0;
			ArrayList<Language> favoriteLanguages = new ArrayList<>();
         currentStudent = new Student(firstName, lastName, email, password, DOB, overallGPA, favoriteLanguages, accountType);
      } else {
         currentCourseCreator = new CourseCreator(firstName, lastName, email, password, DOB, accountType);
      }

   }
   /**
     * Returns the account type of a user based on their email and password.
     * @param email a String representing the user's email
     * @param password a String representing the user's password
     * @return a String representing the user's account type
     */

   public String returnAccountType(String email, String password){
      User user = UserList.getInstance().getUser(email);

      String accountType = UserList.getInstance().getUserType(email);

      return accountType;
   
   }
   /**
     * Sets the current course to the first course in the course list.
     */

   public void setCurrentCourse(){
      Course course = CourseList.getInstance().getCourseByIndex(0);
      this.currentCourse = course;
   }
   /**
    * Sets the current course to the specified course.
    @param course the course to be set as the current course
    */
   public void setCurrentCourse(Course course) {
      this.currentCourse = course;
   }

    /**
     * Log In and Sign Up Functions
     */
    /**
     * Logs in the user with the specified email and password. Sets the current user to the logged-in user if successful.
     * @param email the email of the user trying to log in
     * @param password the password of the user trying to log in
     * @return true if the login is successful, false otherwise
     */
    public boolean logIn(String email, String password){
      
      User user = UserList.getInstance().getUser(email);

         if(user == null){
            return false;
         }else{
            currentUser = user;
            return true;
         }
    }
    /**
     * Checks if the specified email is already in use by a user.
     * @param email the email to be checked
     * @return true if the email is already in use, false otherwise
     */
    public boolean checkEmail(String email){
      users = getUserList();
      for(User user : users){
         if(email.equalsIgnoreCase(user.getEmail())){
            return true;
         }
      }
      return false;
    }
    /**
     * Checks if the specified password matches the password of any user.
     * @param password the password to be checked
     * @return true if the password matches the password of any user, false otherwise
     * */
    public boolean checkPassword(String password){
      users = getUserList();
      for(User user : users){
         if(password.equalsIgnoreCase(user.getPassword())){
            return true;
         }
      }
      return false;
   }
   /**Creates a new student user with the specified information and adds them to the user list.
    * @param firstName the first name of the student
    @param lastName the last name of the student
    @param email the email of the student
    @param password the password of the student
    @param DoB the date of birth of the student
    @param overallGPA the overall GPA of the student
    @param favoriteLanguages the list of favorite languages of the student
    @param type the type of user (in this case, "student")
    @return true if the student is successfully added to the user list, false otherwise
    */
    public boolean signupStudent(String firstName, String lastName, String email, String password, Date DoB, double overallGPA, ArrayList<Language> favoriteLanguages, String type){
      // do you need to put addStudent and addCourseCreator instead.
      //currentUser = UserList.getInstance().addStudent(firstName, lastName, email, password, DoB, overallGPA, favoriteLanguages, type);
      return currentUser != null;
    }
   /**
   Creates a new course creator user with the specified information and adds them to the user list.
   @param firstName the first name of the course creator
   @param lastName the last name of the course creator
   @param email the email of the course creator
   @param password the password of the course creator
   @param DoB the date of birth of the course creator
   @param type the type of user (in this case, "course creator")
   @return true if the course creator is successfully added to the user list, false otherwise
   */
    public boolean signupCourseCreator(String firstName, String lastName, String email, String password, Date DoB, String type){
      // do you need to put addStudent and addCourseCreator instead.
      //currentUser = UserList.getInstance().addCourseCreator(firstName, lastName, email, password, DoB, type);
      return currentUser != null;
      // the information you put in is null, like email, password, all that.
    }

    public Date getDateFromString(String data){
		try {
            return new SimpleDateFormat("MM/dd/yyyy").parse(data);
        } catch (Exception e) {
            System.out.println("here");
            return new Date();
        }
	}
    
   /**
    * Course Creator View
    */
   

   /**public boolean createStudent(String firstName, String lastName, String email, String password){
      for(Student student : students){
         if(student.getEmail().equals(email)){
            return false;
         }
      }

      Student newStudent = new Student(UUID.randomUUID(), firstName, lastName, email, password, new date(), 0.0, new Arraylist <Language>());
      students.add(newStudent);

      return true;
   }
     
   public boolean createCourseCreator(String firstName, String lastName, String email, String password){
      if(currentCourseCreator != null){
         return false;
      }else{
         UUID id = UUID.randomUUID();
         Date dob??
         CourseCreator newCourseCreator = new CourseCreator(id, firstName, lastName, email, password);

         currentCourseCreator = newCourseCreator;

         return true;
      }
   }*/
   
   //Creates a New Course
   /**
   Creates a new course with the given parameters.
   @param id the UUID of the course
   @param title the title of the course
   @param language the language of the course
   @param courseCreator the user who created the course
   @param modules an ArrayList of the modules in the course
   @return true if the course was successfully added to the course list, false otherwise
   */
   public boolean createCourse(UUID id, String title, Language language, User courseCreator, ArrayList<Module> modules){
      return courseList.addCourse(id, title, language, courseCreator, modules);
   }
   /**
   Creates a new module with the given parameters.
   @param title the title of the module
   @param topics an ArrayList of the topics in the module
   @param quiz the quiz in the module
   @param comments an ArrayList of comments in the module
   @return a new module object
   */
   public Module createModule(String title, ArrayList<Topic> topics, Quiz quiz, ArrayList<Comment> comments){
      return new Module(title, topics, quiz, comments);
   }
   /**
   Creates a new topic with the given parameters.
   @param title the title of the topic
   @param lesson the lesson content of the topic
   @return a new topic object
   */
   public Topic createTopic(String title, String lesson){
      return new Topic(title, lesson);
   }
   /**
   Creates a new question with the given parameters.
   @param question the question text
   @param answers an ArrayList of possible answers to the question
   @param correctAnswer the index of the correct answer in the answers ArrayList
   @return a new question object
   */
   public Question createQuestion(String question, ArrayList<String> answers, Long correctAnswer){
      return new Question(question, answers, correctAnswer);
   }
   /**
   Creates a new quiz with the given parameters.
   @param questions an ArrayList of questions in the quiz
   @return a new quiz object
   */

   public Quiz createQuiz(ArrayList<Question> questions){
      return new Quiz(questions);
   }

   //Edit a previously Created Course
   public void editCourse(){
      System.out.println(courseList.getCourses());
   }

   //Show Different Course Module Titles
   public void addModules(){
      System.out.println("\tModules");
      currentCourse = courseList.getCourseByIndex(0);
      modules = currentCourse.getModules();
      System.out.println("\t\t" + (1) + ") " + modules.get(0).getTitle());
      for(int i = 1; i < moduleTitles.length; i++){
         topics = null;
         currentQuiz = null;
         comments = null;
         modules.add(new Module((moduleTitles[i-1]), topics, currentQuiz, comments));
         System.out.println("\t\t" + (i+1) + ") " + modules.get(i).getTitle());
      }
   }

   /**
    * Student View
    */
    /**
   Returns an ArrayList of all courses with the given language.
   @param language the language to filter courses by
   @return an ArrayList of all courses with the given language
   */
   public ArrayList<Course> getCourses(Language language){
      ArrayList<Course> courseLanguages = new ArrayList<>();
        for(Course course : courseLanguages){
            if(course.getLanguage().equals(language)){
                courseLanguages.add(course);
            }
        }
        return courseLanguages;
   }     

   public ArrayList<Course> getCourses(String keyword){
      for(Course course : courses){
         if(course.equals(keyword)){
            courses.add(course);
            return courses;
         }
     }
     return null;
   }

   public Course getCourseByIndex(int index){
        //may just do by accessing the arraylist
        getCourseList();
        currentCourse = courses.get(index);
        return currentCourse;
   }

   public void leaveComment(User author, String text, int commentIndex){
      comments = getModuleByIndex(0).getComments();
      comment = comments.get(commentIndex-1);
      comment.reply(author, text);
      zeroOut();
   }

   public ArrayList<Module> getModules(){
      getCourseList();
      modules = currentCourse.getModules();
      return modules;
   }

   public void setCurrentModule(Module module) {
      currentModule = module;
   }

   public Module getModuleByIndex(int index){
      currentModule = getCourseByIndex(0).getModules().get(index);
      return currentModule;
   }

   public ArrayList<Topic> getTopics(){
      getCourseList();
      return topics;
   }
   

   public Topic getTopicByIndex(int index){
      return topics.get(index);
   }

   public Quiz getQuiz(){
      return currentModule.getQuiz();
   }
   /**
   Returns the grade percentage of a given quiz based on the user's answers and the correct answers.
   @param currentQuiz the quiz to grade
   @return the grade percentage as a double
   */
   public void addQuizGrade(Quiz currentQuiz){
         Double quizGrade = getQuizGrade(currentQuiz);
         currentStudent.addQuizGrade(currentCourse, quizGrade);
         System.out.println("\n" + quizGrade + " out of 100!");  
   }

   public double getQuizGrade(Quiz currentQuiz){
      ArrayList<Integer> userAnswers = currentQuiz.getUserAnswers();
      ArrayList<Integer> correctAnswers = currentQuiz.getCorrectAnswers();
      double result = 0;
      int correctAnswer;
      for(int i = 0; i < userAnswers.size(); i++){
         correctAnswer = correctAnswers.get(i);
         if(userAnswers.get(i) == correctAnswer){
            result++;
         }
      }
      return ((result/(double)userAnswers.size()) * 100);
   }

   /**
    * Clears the Console
    */
   public void zeroOut(){
      System.out.print("\033[H\033[2J");
      System.out.flush();
   }

   public void logOut(){
      courseList.saveCourses();
      userList.saveCourseCreators();
      userList.saveStudents();
 }

}
