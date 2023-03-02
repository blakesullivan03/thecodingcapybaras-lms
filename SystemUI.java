import java.util.Scanner;
/**
 * User Interface for the System
 * @author Blake Turner
 */
public class SystemUI{
    private String[] mainMenuStrings = {"Begin Course", "Resume Course", "Check Course Progress", "Logout"};
    private String[] coursesStrings = {"Intro to JavaScript", "Intro to C", "Intro to Python"};
    private Scanner scanner;
    private LMSSystem system;
    Scanner keyboard = new Scanner(System.in);

    SystemUI(){
        scanner = new Scanner(System.in);
        system = new LMSSystem();
    }

    public void run(){
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

    public void showLogInScreen(){
        System.out.println("Please enter the following info. If you do not have an account, press enter.");
        System.out.println("E-Mail: ");
        String username = keyboard.nextLine();
        System.out.println("Password: ");
        String password = keyboard.nextLine();
    }

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

                case(1):
                    

                case(2):
                    checkCourseProgress();
                    break;
            }
        }

    }

    private void resumeCourse(){

    }

    private void checkCourseProgress(){

    }
}
