import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

public class LMSSystem{
    private UserList users;
    private ArrayList<Integer> userAnswers;
    private CourseList courseList;
    private Module currentModule;
    private ArrayList<Module> modules;
    private Long correctAnswer;
    private Comment comment;
    private User currentUser;
    private Student currentStudent;
    private CourseCreator currentCourseCreator;
    private Course currentCourse;
    private Topic currentTopic;
    private Quiz currentQuiz;
    private ArrayList<Topic> topics;
    private ArrayList<Question> questions;
    private Question currentQuestion;
    private ArrayList<Course> courses;
    private ArrayList<String> answers;
    private ArrayList<Long> array = new ArrayList<>();


    public LMSSystem(){
      users = UserList.getInstance();
      courseList = CourseList.getInstance();
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

    public boolean signup(String username, String password){
      currentUser = UserList.getInstance().addUser();

      // the information you put in is null, like email, password, all that.
      if(currentUser == null){
         return false;
      }

      return true;
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
   

   public boolean createStudent(String firstName, String lastName, String email, String password){
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
   }
   
   public Quiz createQuiz(ArrayList<Question> questions, ArrayList<String> answers){
      Quiz quiz = new Quiz(questions);
      for (String answer : answers) {
         int correctAnswer = Integer.parseInt(answer);
         quiz.addCorrectAnswer(correctAnswer);
      }
      return quiz;
   }

   public boolean createModule(String title, ArrayList<Topic> topics, Quiz quiz, ArrayList<Comment> comments){
      Module module = new Module(title, topics, quiz, comments);
      module.language = language;
      return module;
   }

   public boolean createTopic(String title, String lesson){
      Would we need an add topic method for this?

   }
   public boolean createCourse(String title, Language language, User courseCreatorUUID, ArrayList<Module> modules){
      Course newCourse = new Course(title, language, courseCreatorUUID, modules);
      DataWriter.saveCourse(newCourse);
      return true;
}
   public boolean createQuestion(String question, ArrayList<String> answers, int correctAnswer){
      if(currentQuiz == null){
         return false;;
      }else{
         Question newQuesition = new Question(question, answers, (long)correctAnswer);
         return currentQuiz.addQuestion(newQuestion);
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

   public ArrayList<Course> getCourseList(){
      courses = courseList.getCourses();
      return courses;
   }

   public ArrayList<Module> getModules(){
      getCourseList();
      modules = currentCourse.getModules();
      return modules;
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
      currentModule = getModuleByIndex(0);
      return currentModule.getQuiz();
   }

   public double getQuizGrade(Quiz currentQuiz){
      userAnswers = currentQuiz.getUserAnswers();
      ArrayList<Integer> correctAnswers = currentQuiz.getCorrectAnswers();
      double result = 0;
      for(int i = 0; i < userAnswers.size(); i++){
         correctAnswer = (long)correctAnswers.get(i);
         int correctAnswerInt = correctAnswer.intValue();
         if(userAnswers.get(i) == correctAnswerInt){
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
