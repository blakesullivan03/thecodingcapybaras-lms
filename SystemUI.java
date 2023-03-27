import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.Date;
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

            login();

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
                    system.zeroOut();
                    beginCourse();
                    break;

                case(1):
                    system.zeroOut();
                    resumeCourse();
                    break;

                case(2):
                    system.zeroOut();
                    checkCourseProgress();
                    break;
            }
        }

    }

    public boolean login(){
        System.out.println("Log In.\nIf you do not have an account, press 1. Otherwise, press any number.");
        
        String input = scanner.nextLine();
        int option = Integer.parseInt(input);
        system.zeroOut();

        if (option == 1){
            signup();
            return true;
        } else {
            System.out.println("E-Mail: ");
            String email = scanner.nextLine();
            if(system.checkEmail(email)){
                System.out.println("Password: ");
                String password = scanner.nextLine();
                if(system.checkPassword(password)){
                    System.out.println("That is not your password, please try again");
                    system.zeroOut();
                    System.out.println("\nThank you, now you are successfully logged in!");
                    return true;
                }
                else
                {
                    System.out.println("That is not your password, please try again");
                    return false;
                }

            }else{
                System.out.println("You do not have an account please sign up");
                signup();
                return false;
            }
        }
    }

    public boolean signup(){
        System.out.println("Please enter the Following Info");

        System.out.println("First Name");
        String firstName = scanner.nextLine();
        System.out.println("Last Name");
        String lastName = scanner.nextLine();
        System.out.println("E-Mail");
        String email = scanner.nextLine();
        while(system.checkEmail(email)) {
            System.out.println("You already have an account please log in now");
            login();
        }
        System.out.println("Password");
        String password = scanner.nextLine();
        System.out.println("Date of Birth");
        String dobString = scanner.nextLine();
        Date DoB = system.getDateFromString(dobString);

        if(!isValidPassword(password)) {
            System.out.println("\nThis is not a valid password please try again");
            return false;
        }else{
            system.zeroOut();
            System.out.println("\nThank you, now you are successfully signed up and logged in!");
            system.setCurrentUser(new Student(firstName, lastName, email, password, DoB, 0.0, new ArrayList<Language>()));
            DataWriter.saveStudents();
            return true;
        }
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
                // all courseindex set to 0 bc we only have one,
                // eventually they'll have to be changed somehow
                case(0):
                    //Insert (JavaScirpt) Course Lesson Home Screen Here
                    System.out.println("\nIntro to JavaScript");
                    showCourseHome(0);
                    break;
                
                case(1):
                    System.out.println("\nIntro to C");
                    showCourseHome(0);
                    break;                
                case(2):
                    System.out.println("\nIntro to Python");
                    //Call Course Instance of Python (Modules, Topics, ETC)
                    showCourseHome(0);
                    displayQuiz();
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
                // all courseIndex set to 0 right now bc we only have on course,
                // they will need to be changed to something like indexOf(course)
                case(0):
                    //Insert (JavaScirpt) Course Lesson Home Screen Here
                    System.out.println("\nIntro to JavaScript");
                    showCourseHome(0);
                    break;
                
                case(1):
                    System.out.println("\nIntro to C");
                    showCourseHome(0);
                    break;                
                case(2):
                    System.out.println("\nIntro to Python");
                    //Call Course Instance of Python (Modules, Topics, ETC)
                    showCourseHome(0);
                    break;
            }
        }
    }

    private void showCourseHome(int courseIndex){
        CourseList courses = CourseList.getInstance();
        Course currentCourse = courses.getCourseByIndex(courseIndex);
        ArrayList<Module> modules = currentCourse.getModules();
        int i = 1;
        for (Module module : modules) {
            System.out.println(i + ". " + module.getTitle());
            ++i;
        }

        System.out.print("\n Select module: ");
        int moduleSelection = getUserCommand(modules.size());
        system.setCurrentModule(currentCourse.getModuleByIndex(moduleSelection));
        showModule(currentCourse.getModuleByIndex(moduleSelection));
    }
    private void showModule(Module currentModule) {
        System.out.println(currentModule.toString());
        displayQuiz();
    }
    /**
     * Quiz Functions
     */

    private void displayQuiz(){
        System.out.println("\nAre you ready to take the Quiz? Y/N\n");

        String input = scanner.nextLine();
            
        if(input.equalsIgnoreCase("Y")){
            System.out.println("\n*************************************************************************************************\n");
            System.out.println("\nModule Quiz");
            takeQuiz();
        }else{
            System.out.println("\nContinue Studying");
        }
    }

    private void takeQuiz(){
        Quiz currentQuiz = system.getQuiz();
        while(currentQuiz.hasMoreQuestions()) {
            Question currentQuestion = currentQuiz.getNextQuestion();
            System.out.println(currentQuestion.toString() + "\n" + "\n" + "Answer");
            int answer = getUserCommand(currentQuestion.numAnswers());
            currentQuiz.addUserAnswer(answer);
        }
        System.out.println("\n" + system.getQuizGrade(currentQuiz) + " out of 100!");
        continueModules();
    }

    private void continueModules(){
        System.out.println("\nWould you like to return to the Home Screen? Y/N");

        String input = scanner.nextLine();
            
        if(input.equalsIgnoreCase("Y")){
            system.zeroOut();
            showWelcomeScreen();
        }else{
            system.zeroOut();
            System.out.println("\nModule");
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

       // currentUser

    } 

    // Credit to stackoverflow https://stackoverflow.com/questions/1795402/check-if-a-string-contains-a-special-character
    private static boolean isValidPassword(String password) { 
        if(password.length()>=8 && password.length()<=25)
        {
            Pattern character = Pattern.compile("[a-zA-z]");
            Pattern num = Pattern.compile("[0-9]");
            Pattern specialChar = Pattern.compile ("[!@#$%&*()_+=|<>?{}\\[\\]~-]");

            Matcher hasCharacter = character.matcher(password);
            Matcher hasNum = num.matcher(password);
            Matcher hasSpecialChar = specialChar.matcher(password);

            return hasCharacter.find() && hasNum.find() && hasSpecialChar.find();

        }else
            return false;
    }
// get date and convert to string, private method  
}
