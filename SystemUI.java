import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
// import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
/**
 * User Interface for the System
 * @author Blake Turner
 */
public class SystemUI {
    private static String[] mainMenuStrings = {"Begin Course", "Resume Course", "Check Course Progress", "Logout"};
    private static String[] courseCreatorStrings = {"Create Course", "Edit Course", "Logout"};
    private static String[] coursesStrings = {"Intro to JavaScript", "Intro to C", "Intro to Python"};
    private String[] moduleTitleString = {"Basics", "Strings", "Functions", "Classes", "Conditional Statements", "Exceptions", "File Reading", "9", "10", };
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

        login();

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
            System.out.println("Password: ");
            String password = scanner.nextLine();
            // check for both and say not valid log in w in while loop, and give an option to get out
            while(!(system.checkEmail(email) && system.checkPassword(password))){
                    System.out.println("This is not a valid log in please try again.");
                    System.out.println("If you want to try again press 1, if not press any other number");
                    option = scanner.nextInt();
                    scanner.nextLine();
                    if(option == 1) {
                        login();
                    }
                    System.out.println("Goodbye, thank you!");
                    System.exit(0);
            }
            //Once Logged In Needs to Set Current User
            system.setUser(email, password);
            system.zeroOut();
            if(system.returnAccountType(email, password).equalsIgnoreCase("student")){
                showStudentWelcomeScreen();
                showStudentMainMenu();
            }else{
                showTeacherWelcomeScreen();
                showCourseCreatorMainMenu();
            }
            return true;
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
        System.out.println("Account Type");
        String type = scanner.nextLine();
        

        // I think this needs to be a while loop.
        while(!isValidPassword(password)) {
            System.out.println("\nThis is not a valid password please try again");
            return false;
        }
            system.zeroOut();
            System.out.println("\nThank you, now you are successfully signed up and logged in!");
            //currentStudent = (new Student(firstName, lastName, email, password, DoB, 0.0, new ArrayList<Language>(), type));
            DataWriter.saveStudents();
            return true;
    }

    private int getUserCommand(int numCommand){

        String input = scanner.nextLine();

        int command = Integer.parseInt(input) - 1;
    
        if(command >= 0 && command <= numCommand -1){
            return command;
        }

        return -1;
    }

    public void showStudentWelcomeScreen(){
        System.out.println("\n********Main Menu********");
        System.out.println("Please Choose one of the Following:");
        for(int i = 0; i < mainMenuStrings.length; i++){
            System.out.println((i+1) + ". " + mainMenuStrings[i]);
        }
        System.out.println("\n");
    }

    public void showTeacherWelcomeScreen(){
        System.out.println("\n********Main Menu********");
        System.out.println("Please Choose one of the Following:");
        for(int i = 0; i < courseCreatorStrings.length; i++){
            System.out.println((i+1) + ". " + courseCreatorStrings[i]);
        }
        System.out.println("\n");
    }

    private void showStudentMainMenu(){
        while(true){
            int command = getUserCommand(mainMenuStrings.length);

            if(command == -1){
                System.out.println("Invalid Command");
                continue;
            }

            /**
             * Logout Instance
             */
            if(command == mainMenuStrings.length-1){
                system.zeroOut();
                System.out.println("Sucesfully Logged Out");
                system.logOut();
                System.exit(0);
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

private void showCourseCreatorMainMenu(){
    while(true){
        int command = getUserCommand(courseCreatorStrings.length);

        if(command == -1){
            System.out.println("Invalid Command");
            continue;
        }

        /**
         * Logout Instance
         */
        if(command == courseCreatorStrings.length-1){
            system.zeroOut();
            System.out.println("Sucesfully Logged Out");
            DataWriter.saveCourses();
            System.exit(0);
        }

        switch(command){
            case(0):
                system.zeroOut();
                //TODO Call Method from LMS
                //system.createCourse();
                returnToCourseCreatorHomeScreen();
                break;

            case(1):
                system.zeroOut();
                System.out.println("Editing Python Course");
                system.addModules();
                editModule();
                returnToCourseCreatorHomeScreen();
                break;
        }
    }
}

private void editModule(){
    while(true){

        int command = getUserCommand(moduleTitleString.length);

        if(command == -1){
            System.out.println("Invalid Command");
            continue;
        }else{
                system.zeroOut();
                //Variables
                int moduleNumber = command;
                ArrayList<Module> modules = system.getModules();
                Module currentModule = system.getModules().get(command);
                String moduleTitle = system.getModules().get(command).getTitle();
                Quiz currentQuiz = currentModule.getQuiz();
                ArrayList<Topic> topics = currentModule.getTopics();

                //Show Current Module
                System.out.println(currentModule);
                
                //Add a new Topic to the Exisitng Module
                System.out.println("\n**********************************************************************************");
                System.out.println("\nAdding a New Topic to the Existing Module");
                System.out.println("\nModule : " + moduleTitle);
                System.out.println("\tAdd Topic");
                System.out.print("\t\tTitle - ");
                String topicTitle = scanner.nextLine();
                System.out.print("\t\tLesson - ");
                String topicLesson = scanner.nextLine();
                Topic currentTopic = system.createTopic(topicTitle, topicLesson);
                topics.add(currentTopic);

                //Update Module to Have New Topics
                currentModule = system.createModule(moduleTitle, topics, currentQuiz, null);
                system.zeroOut();

                //Go Back to Module
                System.out.println("\nModule : " + moduleTitle);

                //Look at Quiz
                System.out.println("Quiz\n" + currentQuiz.getQuestions());

                //Add a New Question to the Current/Existing Quiz
                System.out.println("\nAdding a New Question to the Existing Module");
                System.out.println("\nModule : " + moduleTitle);
                System.out.println("\tAdd Question");
                System.out.print("\t\tQuestion - ");
                String question = scanner.nextLine();
                ArrayList<String> answerString = new ArrayList<>();
                    for(int i = 0; i < 3; i++)
                    {
                        System.out.print("\t\tAnswers - ");
                        String answers = scanner.nextLine();
                        answerString.add(answers);
                    }
                System.out.print("\t\tCorrect Answer - ");
                String correctAnswerString = scanner.nextLine();
                Long correctAnswer = Long.parseLong(correctAnswerString);

                //Create a New Question Given Input
                Question newQuestion =  system.createQuestion(question, answerString, correctAnswer);
                currentQuiz.addQuestion(newQuestion);

                currentModule = system.createModule(moduleTitle, topics, currentQuiz, null);

                modules.set(moduleNumber, currentModule);

                //System.out.println(modules.get(moduleNumber));
                break;
        }
        
    }
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
        CourseList courseList = CourseList.getInstance();
        ArrayList<Course> courses = courseList.getCourses();

        system.setCurrentCourse(courses.get(courseIndex));
        
        Course currentCourse = system.getCurrentCourse();
        Student currentStudent = system.getCurrentStudent();

        ArrayList<Module> modules = currentCourse.getModules();

        if(!currentStudent.getCourses().containsKey(currentCourse))
            currentStudent.enroll(currentCourse);
        
        int i = 1;
        for (Module module : modules) {
            System.out.println(i + ". " + module.getTitle());
            i++;
        }
        System.out.println(i + ". " + "View Course Comments");
        System.out.print("\n Select Module: ");
        int moduleSelection = getUserCommand(modules.size()+1);
        if(moduleSelection == modules.size()) {
            viewCourseComments();
        } else {
            system.setCurrentModule(currentCourse.getModuleByIndex(moduleSelection));
            showModule(system.getCurrentModule());
        }
    }

    private void showModule(Module currentModule) {
        System.out.println(currentModule);
        System.out.println("\nEnter 1 to View Comments or Enter 2 to Take Quiz");
        int viewComments = getUserCommand(1);
        if(viewComments == 0)
            viewModuleComments();
        displayQuiz();
    }

    /**
     * Quiz Functions
     */

    private void displayQuiz(){
        System.out.println("\nAre you ready to take the Quiz? Y/N\n");

        String ready = scanner.nextLine();

        if(ready.equalsIgnoreCase("Y")){
            System.out.println("\n*************************************************************************************************\n");
            System.out.println("\nModule Quiz");
            takeQuiz();
        }else if(ready.equalsIgnoreCase("N")){
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
            system.addQuizGrade(currentQuiz);
            returnToHomeScreen();
    }

    private void returnToHomeScreen(){
        System.out.println("\nWould you like to return to the Home Screen? Y/N");

        String decision = scanner.nextLine();
            
        if(decision.equalsIgnoreCase("Y")){
            system.zeroOut();
            showStudentWelcomeScreen();
            showStudentMainMenu();
        }else{
            system.zeroOut();
            System.out.println("Continue");
        }

    }

    private void returnToCourseCreatorHomeScreen(){
        System.out.println("\nWould you like to return to the Home Screen? Y/N");

        String dec = scanner.nextLine();
            
        if(dec.equalsIgnoreCase("Y")){
            system.zeroOut();
            showTeacherWelcomeScreen();
            showCourseCreatorMainMenu();
        }else{
            system.zeroOut();
            System.out.println("Continue");
        }

    }
    /**
     * Check Course Progress
     */
    
    private void checkCourseProgress(){
        system.zeroOut();
        System.out.println("Checking Course Progress");
        showCourseProgress();

        returnToHomeScreen();
    }
    
    private void showCourseProgress(){
        HashMap<Course, CourseProfile> currentUserCourses;
        Student currentUser = system.getCurrentStudent();
        currentUserCourses = currentUser.getCourses();

        if(currentUserCourses.isEmpty()){
            System.out.println("You have no courses");
            return;
        }else{
            for(Course courseKey : currentUserCourses.keySet()) {
                System.out.println(currentUserCourses.get(courseKey));
                certificate(currentUserCourses.get(courseKey));
            }

        }   
    } 

    public void viewCourseComments() {
        ArrayList<Comment> comments = system.getCurrentCourse().getComments();
        system.zeroOut();
        if(comments == null)
            System.out.println("No comments on course");
            showCourseHome(system.getCurrentCourseIndex());
        for (Comment comment : comments)
            System.out.println(comment);
    }

    public void viewModuleComments() {
        ArrayList<Comment> comments = system.getCurrentModule().getComments();
        system.zeroOut();
        if(comments == null)
            System.out.println("No comments on modules");
        for (Comment comment : comments)
            System.out.println(comment);

    }
    public void certificate(CourseProfile grade){
        if(grade.getGrades().size() < grade.getCourse().getModules().size()) {
            System.out.println("Complete the course with an 80 or greater to recieve a certificate!");
            return;
        }
        if(grade.getGrade() >= 80) {
            System.out.println("If you want to receive your certificate now press 1!");
            int option = scanner.nextInt();
            scanner.nextLine();
            if(option == 1) {
                CourseProfile.certificate();
            } else{
                System.out.println("Returning you to home screen now");
                returnToHomeScreen();
            }
        } else {
            System.out.println("You do not have a high enough score yet!");
            System.out.println("Returning you to the homes screen");
            returnToHomeScreen();
        }
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

    /**
     * Course Creator View
     */

    

}

