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

    /**public void showLogInScreen(){
        System.out.println("Please enter the following info. If you do not have an account, press enter.");
        System.out.println("E-Mail: ");
        String username = scanner.nextLine();
        System.out.println("Password: ");
        String password = scanner.nextLine();
    }*/

    public void showWelcomeScreen(){
        System.out.println("********Main Menu********");
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
    }

    /**
     * Quiz Functions
     */

    private void takeQuiz(){
        System.out.println("\nAre you ready to take the Quiz?\n");

        String input = scanner.nextLine();
            
        if(input.equalsIgnoreCase("Y")){
            System.out.println("\n*************************************************************************************************\n");
            displayQuiz();
        }else{
            System.out.println("\nContinue Studying");
        }
    }

    private void displayQuiz(){
        //Print a Single Question at a Time
        System.out.println("Module Quiz");
        for(int i = 0; i < 2; i++){
            System.out.println(system.getQuiz().getQuestions().get(i));
            //Get Answer Choice
            System.out.println("\nAnswer:");
            String answerChoice = scanner.next();
            Integer answInteger = Integer.parseInt(answerChoice);
            Long answLong = Long.valueOf(answInteger);
            //Check Answers given Answer Choices
            if(true == system.checkAnswers(answLong)){
                System.out.println("Correct!");
            }
        }
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

    
    
}
