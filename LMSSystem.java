import java.util.ArrayList;

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

    public boolean logIn(String username, String password){
      User user = UserList.getInstance().getUser(username, password);

      if(user == null){
         return false;
      }
      currentUser = user;
      return true;
    }

    public boolean signup(String username, String password){
      User user = UserList.getInstance().getUser(username, password);

      // does not exist in json, so its true the account is signing up
      //TODO figure out what to set currentUser to
      if(user == null){
         return true;
      }
      // false bc they are not signing up, they have account.
      return false;
    }

    public void logOut(){

    }
    
   /**
   * Course Creator View
   

   public boolean createStudent(String firstName, String lastName, String email, String password){
      if(currentStudent == null){
         return false;
      }else{
         return true;
      }

   }
     
   public boolean createCourseCreator(String firstName, String lastName, String email, String password){
      if(currentCourseCreator == null){
         return false;
      }else{
         return true;
      }
   }
     
   public Quiz createQuiz(ArrayList<Question> questions, ArrayList<String> answers){
      if(currentQuiz == null){
         return null;
      }else{
         currentQuiz = new Quiz(questions);
      }
      return currentQuiz;

   }

   public boolean createModule(String title, ArrayList<Topic> topics, Quiz quiz, ArrayList<Comment> comments){
      if(currentModule == null){
         return false;
      }else{
         currentModule.editTitle(title);

         currentModule = new Module(title, topics, quiz, comments);
         return true;
      }

   }

   public boolean createTopic(String title, String lesson){
      if(currentCourse == null){
         return false;
      }else{
         currentTopic.setTitle(title);
         currentTopic.setLesson(lesson);
         return true;
      }
   }

   public boolean createCourse(String title, Language language){
      if(currentCourse == null){
         return false;
      }else{
         currentCourse.setTitle(title);
         currentCourse.setLanguage(language);
         return true;
      }

   }

   public boolean createQuestion(String question, ArrayList<String> answers, int correctAnswer){
      /*if (currentQuiz == null){
         return false;
      }else{
         Question newQuestion = new Question(question, answers, correctAnswer);
         return currentQuiz.addQuestion(newQuestion);
      }
   }*/

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
    
}
