import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.UUID;
import java.util.concurrent.atomic.LongAccumulator;

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


    public LMSSystem(){
      userList = UserList.getInstance();
      courseList = CourseList.getInstance();
    }
    
    // Accessors and Mutators 

    public ArrayList<User> getUserList() {
      users = userList.getUsers();
      return users;
    }

   public ArrayList<Course> getCourseList() {
      courses = courseList.getCourses();
      return courses;
   }

   public User getCurrentUser() {
      return currentUser;
   }

   public Student getCurrentStudent() {
      return currentStudent;
   }

   public Course getCurrentCourse(){
      return currentCourse;
   }

   public Module getCurrentModule() {
      return currentModule;
   }

   public int getCurrentCourseIndex()  {
      return courseList.indexOf(currentCourse);
   }
   public CourseCreator getCurrentCreator() {
      return currentCourseCreator;
   }

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

   public String returnAccountType(String email, String password){
      User user = UserList.getInstance().getUser(email);

      String accountType = UserList.getInstance().getUserType(email);

      return accountType;
   
   }
   
   public void setCurrentCourse(){
      Course course = CourseList.getInstance().getCourseByIndex(0);
      this.currentCourse = course;
   }
   
   public void setCurrentCourse(Course course) {
      this.currentCourse = course;
   }

    /**
     * Log In and Sign Up Functions
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



    public boolean checkEmail(String email){
      users = getUserList();
      for(User user : users){
         if(email.equalsIgnoreCase(user.getEmail())){
            return true;
         }
      }
      return false;
    }

    public boolean checkPassword(String password){
      users = getUserList();
      for(User user : users){
         if(password.equalsIgnoreCase(user.getPassword())){
            return true;
         }
      }
      return false;
   }

    public boolean signupStudent(String firstName, String lastName, String email, String password, Date DoB, double overallGPA, ArrayList<Language> favoriteLanguages, String type){
      // do you need to put addStudent and addCourseCreator instead.
      //currentUser = UserList.getInstance().addStudent(firstName, lastName, email, password, DoB, overallGPA, favoriteLanguages, type);
      return currentUser != null;
    }

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
   public boolean createCourse(UUID id, String title, Language language, User courseCreator, ArrayList<Module> modules){
      return courseList.addCourse(id, title, language, courseCreator, modules);
   }
   
   public Module createModule(String title, ArrayList<Topic> topics, Quiz quiz, ArrayList<Comment> comments){
      return new Module(title, topics, quiz, comments);
   }

   public Topic createTopic(String title, String lesson){
      return new Topic(title, lesson);
   }

   public Question createQuestion(String question, ArrayList<String> answers, Long correctAnswer){
      return new Question(question, answers, correctAnswer);
   }

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
      userList.saveUsers();
 }

}
