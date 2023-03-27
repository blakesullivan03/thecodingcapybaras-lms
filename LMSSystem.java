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
    private Topic currentTopic;
    // private Quiz currentQuiz;
    // private Question currentQuestion;
    private Comment comment;
    private ArrayList<User> users;
    private ArrayList<Course> courses;
    private ArrayList<Module> modules;
    private ArrayList<Topic> topics;
    private ArrayList<Question> questions;
    private ArrayList<String> answers;
    // private ArrayList<Long> array = new ArrayList<>();


    public LMSSystem(){
      userList = UserList.getInstance();
      courseList = CourseList.getInstance();
    }
    // Accessors and mutators 
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

   public CourseCreator getCurrentCreator() {
      return currentCourseCreator;
   }

   public void setCurrentUser(User user) {
      currentUser = user;
   }

   public void setCurrentUser(Student user) {
      currentUser = user;
      currentStudent = user;
   }

   public void setCurrentUser(CourseCreator user) {
      currentUser = user;
      currentCourseCreator = user;
   }
    /**
     * Log In and Sign Up Functions
     */

    public boolean logIn(String username, String password){
      User user = UserList.getInstance().getUser(username, password);

      if(user == null){
         return false;
      } else {
      currentUser = user;
      return true;
      }
    }

    public boolean signupStudent(String firstName, String lastName, String email, String password, Date DoB, double overallGPA, ArrayList<Language> favoriteLanguages){
      // do you need to put addStudent and addCourseCreator instead.
      currentUser = UserList.getInstance().addStudent(firstName, lastName, email, password, DoB, overallGPA, favoriteLanguages);
      return currentUser != null;
    }

    public boolean signupCourseCreator(String firstName, String lastName, String email, String password, Date DoB){
      // do you need to put addStudent and addCourseCreator instead.
      currentUser = UserList.getInstance().addCourseCreator(firstName, lastName, email, password, DoB);
      return currentUser != null;
      // the information you put in is null, like email, password, all that.
    }
    public void logOut(){

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
   public boolean createCourse(UUID id, String title, Language language, User courseCreatorUUID, ArrayList<Module> modules){
      return courseList.addCourse(id, title, language, courseCreatorUUID, modules);
   }
   
   public boolean createModule(String title, ArrayList<Topic> topics, Quiz quiz, ArrayList<Comment> comments){
      return modules.add(new Module(title, topics, quiz, comments));
   }

   public boolean createQuestion(String question, ArrayList<String> answers, Long correctAnswer){
      return questions.add(new Question(question, answers, null));
   }

   public Quiz createQuiz(ArrayList<Question> questions){
      return new Quiz(questions);
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
      return ( result / (double)userAnswers.size() ) * 100;
   }

   public void updateGrade(double grade){
      //currentUser.setGrade(currentCourse, currentModule, grade);
   }

   /**
    * Clears the Console
    */

   public void zeroOut(){
      System.out.print("\033[H\033[2J");
      System.out.flush();
   }
    
}
