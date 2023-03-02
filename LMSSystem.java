import java.util.ArrayList;

public class LMSSystem{
    private UserList users;
    private Module module;
    private Comment comment;
    private User currentUser;
    private Course currentCourse;
    private Quiz currentQuiz;

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

   public boolean createStudent(String firstName, String lastName, String email, String userName, String password){

   }
     
   public boolean createCourseCreator(String firstName, String lastName, String email, String userName, String password){


   }
     
   public Quiz createQuiz(ArrayList<String> questions, ArrayList<String> answers){

   }

   public boolean createModule(String title){

   }

   public boolean createTopic(String title, String description){

   }

   public boolean createCourse(String title, Language language){

   }

   public boolean createQuestion(String quesiotion, ArrayList<String> answers, int correctAnswer){

   }

   /**
    * Student View
    */

   public ArrayList<Course> getCourses(Language language){
   
   }     

   public ArrayList<Course> getCourses(String keyword){

   }

   public Course getCourseByIndex(int index){
        //may just do by accessing the arraylist
   }

   public ArrayList<Course> getModules(){

   }

   public Module getModuleByIndex(int index){

   }

   public ArrayList<Course> getTopics(){
        
   }

   public Topic getTopicByIndex(int index){

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
