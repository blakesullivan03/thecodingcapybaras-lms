import java.util.ArrayList;

public class LMSSystem{
    private UserList users;
    private CourseList courseList;
    private Module currentModule;
    private ArrayList<Module> modules;
    private Comment comment;
    private User currentUser;
    private Student currentStudent;
    private CourseCreator currentCourseCreator;
    private Course currentCourse;
    private Topic currentTopic;
    private Quiz currentQuiz;
    private ArrayList<Topic> topics;
    private Question currentQuestion;
    private ArrayList<Course> courses;

    public LMSSystem(){
      users = UserList.getInstance();
      courseList = CourseList.getInstance();
    }

    public boolean logIn(String username, String password){
      if(username != null & password != null){
         return true;
      }
      else{
         return false;
      }
    }

    public void logOut(){

    }
    
   /**
   * Course Creator View
   */

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
     
   public Quiz createQuiz(ArrayList<String> questions, ArrayList<String> answers){

   }

   public boolean createModule(String title){
      if(module == null){
         return false;
      }else{
         module.editTitle(title);
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
      }*/
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
        currentCourse = courses.get(index);
        return currentCourse;
   }

   public ArrayList<Module> getModules(){
      return currentCourse.getModules();
   }

   public Module getModuleByIndex(int index){
      currentModule = modules.get(index);
      return currentModule;
   }

   public ArrayList<Topic> getTopics(){
      return topics;
   }

   public Topic getTopicByIndex(int index){
      return topics.get(index);
   }

   public Quiz getQuiz(){
      return currentModule.getQuiz();
   }

   public void updateGrade(double grade){
      //currentUser.setGrade(currentCourse, currentModule, grade);
   }
    
}
