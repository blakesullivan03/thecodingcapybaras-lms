import java.util.Scanner;
import java.util.ArrayList;
/**
 * User Interface for the System
 * @author Blake Turner
 */
public class SystemUI{
    private static String[] mainMenuStrings = {"Begin Course", "Resume Course", "Check Course Progress", "Logout"};
    private static String[] coursesStrings = {"Intro to JavaScript", "Intro to C", "Intro to Python"};
    private static Scanner scanner;
    private static LMSSystem system;

    public static void main(String[] args){
        SystemUI systemInterface = new SystemUI();
        systemInterface.run();
    }

    SystemUI(){
        scanner = new Scanner(System.in);
        system = new LMSSystem();
    }

    public void run(){
        UserList users = UserList.getInstance();

        System.out.println("Welcome to the LMS!");

        while(true){
            showWelcomeScreen();

            int command = getUserCommand(mainMenuStrings.length);

            if(command == -1){
                System.out.println("Invalid Command");
                continue;
            }

            /**
             * Logout Instance
             */
            if(command == mainMenuStrings.length-1){
                break;
            }

            switch(command){
                case(0):
                    beginCourse();
                    break;

                case(1):
                    resumeCourse();
                    break;

                case(2):
                    checkCourseProgress();
                    break;
            }
        }

    }

    public boolean login(){
        System.out.println("Please enter the following info. If you do not have an account, press enter(1).");
        int option = scanner.nextInt();
        if (option == 1) {
            signup();
        }
        System.out.println("E-Mail: ");
        String username = scanner.nextLine();
        System.out.println("Password: ");
        String password = scanner.nextLine();
        while(!isValidPassword()) {
            System.out.println("This is not a valid password please try again");
        }
            System.out.println("Thank you, now you are successfully logged in!");
            return true;
    }

    public boolean signup(){
        System.out.println("Please enter the following info. If you have an account, press enter().");
        int option = scanner.nextInt();
        if (option == 2) {
            login();
        }
        System.out.println("E-Mail: ");
        String username = scanner.nextLine();
        System.out.println("Password: ");
        String password = scanner.nextLine();
        while(!isValidPassword()) {
            System.out.println("This is not a valid password please try again");
        }
            System.out.println("Thank you, now you are successfully logged in!");
            return true;
    }

    public void showWelcomeScreen(){
        System.out.println("\n********Main Menu********");
        System.out.println("Please Choose one of the Following:");
        for(int i = 0; i < mainMenuStrings.length; i++){
            System.out.println((i+1) + ". " + mainMenuStrings[i]);
        }
        System.out.println("\n");
    }

    private int getUserCommand(int numCommand){

        String input = scanner.nextLine();

        int command = Integer.parseInt(input) - 1;
    
        if(command >= 0 && command <= numCommand -1){
            return command;
        }

        return -1;
    }
    

    private void beginCourse(){
        System.out.println("Choose a New Course to Begin: ");
        for(int i = 0; i < coursesStrings.length; i++){
            System.out.println((i+1) + ". " + coursesStrings[i]);
        }

        while(true){
            int command = getUserCommand(coursesStrings.length);

            if(command == -1){
                System.out.println("Invalid Command");
                continue;
            }

            switch(command){
                case(0):
                    //Insert (JavaScirpt) Course Lesson Home Screen Here
                    System.out.println("\nIntro to JavaScript");
                    showCourse();
                    break;
                
                case(1):
                    System.out.println("\nIntro to C");
                    showCourse();
                    break;                
                case(2):
                    System.out.println("\nIntro to Python");
                    //Call Course Instance of Python (Modules, Topics, ETC)
                    showCourse();
                    takeQuiz();
                    break;
            }
        }

    }

    private void resumeCourse(){
        System.out.println("Resume Course");
        for(int i = 0; i < coursesStrings.length; i++){
            System.out.println((i+1) + ". " + coursesStrings[i]);
        }

        while(true){
            int command = getUserCommand(coursesStrings.length);

            if(command == -1){
                System.out.println("Invalid Command");
                continue;
            }

            switch(command){
                case(0):
                    //Insert (JavaScirpt) Course Lesson Home Screen Here
                    System.out.println("\nIntro to JavaScript");
                    showCourse();
                    break;
                
                case(1):
                    System.out.println("\nIntro to C");
                    showCourse();
                    break;                
                case(2):
                    System.out.println("\nIntro to Python");
                    //Call Course Instance of Python (Modules, Topics, ETC)
                    showCourse();
                    break;
            }
        }
    }


    private void showCourse(){
        CourseList courses = CourseList.getInstance();
        ArrayList<Course> modules = courses.getCourses();

        for(Course course : modules){
            System.out.println(course.getModuleByIndex(0));
        }
        // This is literally taking all courses and printing the first module
        // of each so I think it's going to have to change
    }

    /**
     * Quiz Functions
     */

    private void takeQuiz(){
        System.out.println("\nAre you ready to take the Quiz? Y/N\n");

        String input = scanner.nextLine();
            
        if(input.equalsIgnoreCase("Y")){
            System.out.println("\n*************************************************************************************************\n");
            displayQuiz();
        }else{
            System.out.println("\nContinue Studying");
        }
    }

    private void displayQuiz(){
        Quiz currentQuiz = system.getQuiz();
        while(currentQuiz.hasMoreQuestions()) {
            Question currentQuestion = currentQuiz.getNextQuestion();
            System.out.println(currentQuestion.toString() + "\n" + "\n" + "Answer");
            int answer = getUserCommand(currentQuestion.numAnswers());
            currentQuiz.addUserAnswer(answer);
        }
        System.out.println("\n" + system.getQuizGrade(currentQuiz) + " out of 100!");
    }



    /**
     * Check Course Progress
     */
    
    private void checkCourseProgress(){
        System.out.println("Checking Course Progress");
        showCourseProgress();
    }
    
    private void showCourseProgress(){
        CourseList courses = CourseList.getInstance();
        ArrayList<Course> modules = courses.getCourses();

        for(Course course : modules){
            System.out.println(course.getModuleByIndex(0).courseProgressToString());
        }
    } 

    /**private void showModules(){
        CourseList courses = CourseList.getInstance();
        ArrayList<Module> modules = courses.getCourses(null);

        for(CourseList courses : modules){
            System.out.println(modules.getTitle() + " ");
        }
    }*/
    // less complicated version
    private boolean isValidPassword() {
        String numofChars = scanner.nextLine();
        if (numofChars.length() >= 8) {
            return true;
        }
            return false;
    }
    /*private boolean isValidPassword() {
        int numOfNumbers = 0;
        int numofChars = 0;
        int numOfSpecialChars = 0;
        if (numOfNumbers >= 2 && (numofChars >= 8 && numofChars <= 15) && numOfSpecialChars >= 1) {
            // only start w easy checks like 6 characters.
            return true;
        }
            return false;
    }*/
    
    
}
