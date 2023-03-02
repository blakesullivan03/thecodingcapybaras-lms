import java.util.ArrayList;

public class LMSSystem{
    private UserList users;
    private Module module;
    private ArrayList<Module> modules;
    private Comment comment;
    private User currentUser;
    private Student currentStudent;
    private CourseCreator currentCourseCreator;
    private Course currentCourse;
    private Quiz currentQuiz;
    private Topic currentTopic;
    private ArrayList<Topic> topics;
    private Question currentQuestion;
    private ArrayList<Course> courses;

    public LMSSystem(){
      users = users.getInstance();
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

   public boolean createQuestion(String quesiotion, ArrayList<String> answers, int correctAnswer){

   }

   /**
    * Student View
    */

   public ArrayList<Course> getCourses(Language language){
      ArrayList<Course> courseLanguages = new ArrayList();
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
        return courses.get(index);
   }

   public ArrayList<Course> getModules(){
      return modules;
   }

   public Module getModuleByIndex(int index){
      return modules.get(index);
   }

   public ArrayList<Course> getTopics(){
      return topics;
   }

   public Topic getTopicByIndex(int index){
      return topics.get(index);
   }

   public Quiz getQuiz(){

   }

   public Quiz takeQuiz(){

   }

   public boolean checkAnswers(ArrayList<Integer> userInput, Quiz quiz){

   }

   public void updateGrade(double grade){

   }
    
}
