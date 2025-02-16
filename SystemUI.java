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

    /**
     * Constructs a new SystemUI object.
     * Initializes the Scanner and LMSSystem objects.
     */
public class SystemUI {
    private static String[] mainMenuStrings = {"Begin Course", "Resume Course", "Check Course Progress", "Logout"};
    private static String[] courseCreatorStrings = {"Create Course", "Edit Course", "Logout"};
    private static String[] coursesStrings = {"Intro to JavaScript", "Intro to C", "Intro to Python"};
    private String[] moduleTitleString = {"Basics", "Strings", "Functions", "Classes", "Conditional Statements", "Exceptions", "File Reading", "9", "10", };
    private static Scanner scanner;
    private static LMSSystem system;
    /**
     * The main method that starts the program.
     * Creates a new SystemUI object and runs it.
     * @param args An array of strings representing command line arguments.
     */
    public static void main(String[] args){
        SystemUI systemInterface = new SystemUI();
        systemInterface.run();
    }

    SystemUI(){
        scanner = new Scanner(System.in);
        system = new LMSSystem();
    }
    /**
     * Runs the command-line interface for the LMS.
     * Prompts the user to log in or sign up, and once logged in, provides different options based on the user's account type (student or teacher/course creator).
     */
    public void run(){
        UserList users = UserList.getInstance();

        System.out.println("Welcome to the LMS!");

        login();

    }
    /**
     * Prompts the user to log in, and checks if the email and password are valid.
     * If the user does not have an account, prompts the user to sign up.
     * If the email and password are valid, sets the current user in the LMSSystem object and shows the appropriate menu based on the user's account type.
     * @return true if the user successfully logs in, false otherwise.
     */
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

    /**
     * This method allows a user to sign up for an account by providing necessary information
     * @return a boolean indicating whether the sign up process was successful
    */
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
        


        while(!isValidPassword(password)) {
            System.out.println("\nThis is not a valid password please try again");
            return false;
        }
            system.zeroOut();
            System.out.println("\nThank you, now you are successfully signed up and logged in!");
            DataWriter.saveStudents();
            DataWriter.saveCourseCreators();
            return true;
    }
    /**
    Gets the user command from input and returns it.
    @param numCommand the number of possible commands.
    @return the user command or -1 if invalid.
    */
    private int getUserCommand(int numCommand){

        String input = scanner.nextLine();

        int command = Integer.parseInt(input) - 1;
    
        if(command >= 0 && command <= numCommand -1){
            return command;
        }

        return -1;
    }
    /**
    Displays the welcome screen for students.
    */
    public void showStudentWelcomeScreen(){
        System.out.println("\n********Main Menu********");
        System.out.println("Please Choose one of the Following:");
        for(int i = 0; i < mainMenuStrings.length; i++){
            System.out.println((i+1) + ". " + mainMenuStrings[i]);
        }
        System.out.println("\n");
    }
    /**
    Displays the welcome screen for teachers.
    */
    public void showTeacherWelcomeScreen(){
        System.out.println("\n********Main Menu********");
        System.out.println("Please Choose one of the Following:");
        for(int i = 0; i < courseCreatorStrings.length; i++){
            System.out.println((i+1) + ". " + courseCreatorStrings[i]);
        }
        System.out.println("\n");
    }
    /**
    Displays the main menu for students and handles user input.
    */
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
/**
Gets the user command from input and returns it.
@param numCommand the number of possible commands.
@return the user command or -1 if invalid.
*/
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
/**
 * Method that allows the course creator to edit a module of a course.
 * The method will display a menu of available modules, allow the user to
 * select a module, and then display the options for editing the module.
 * The user can then edit the module by inputting valid commands, which are
 * based on the editModuleStrings array. If the user inputs an invalid command,
 * the method will continue to loop until a valid command is inputted.
 * @see getUserCommand
 */

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
                ArrayList<Comment> comments = currentModule.getComments();

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
                currentModule = system.createModule(moduleTitle, topics, currentQuiz, comments);
                system.zeroOut();

                //Go Back to Module
                System.out.println("\nModule : " + moduleTitle);

                //Look at Quiz
                System.out.println("Quiz\n" + currentQuiz.getQuestions());

                //Add a New Question to the Current/Existing Quiz
                System.out.println("\nAdding a New Question to the Existing Module  Quiz");
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

                currentModule = system.createModule(moduleTitle, topics, currentQuiz, comments);

                modules.set(moduleNumber, currentModule);

                //System.out.println(modules.get(moduleNumber));
                break;
        }
        
    }
}
    
/**
 * Presents the user with a list of available courses to begin and prompts the user to choose one.
 * Once a course is chosen, calls the showCourseHome method for the chosen course.
*/
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
    /**
     *Displays the menu for resuming a course and prompts the user to select a course to resume. Once a course is selected,
     *the method calls showCourseHome() to display the home screen of the selected course.
     */
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
    /**
     * Displays the home screen for a course, including all modules and an option to view comments. 
     * @param courseIndex the index of the course to display the home screen for
     */
    private void showCourseHome(int courseIndex){
        CourseList courseList = CourseList.getInstance();
        ArrayList<Course> courses = courseList.getCourses();

        system.setCurrentCourse(courses.get(courseIndex));
        
        Course currentCourse = system.getCurrentCourse();
        Student currentStudent = system.getCurrentStudent();

        ArrayList<Module> modules = currentCourse.getModules();

        if(!currentStudent.getCourses().containsKey(currentCourse))
            currentStudent.enroll(currentCourse);
            currentStudent.addFavoriteLanguage(currentCourse.getLanguage());

        
        
        int i = 1;
        for (Module module : modules) {
            System.out.println(i + ". " + module.getTitle());
            i++;
        }

        System.out.println(i + ". " + "View Comments");
        System.out.print("\n Select Option: ");
        int moduleSelection = getUserCommand(modules.size()+1);
        if(moduleSelection == modules.size()) {
            viewModuleComments(currentStudent);
            returnToHomeScreen();
        } else {
            system.setCurrentModule(currentCourse.getModuleByIndex(moduleSelection));
            showModule(system.getCurrentModule());
        }
    }
    /**
     * Displays the current module's details, and prompts the user to either view comments or take the quiz.
     * @param currentModule the module to display details for
     */
    private void showModule(Module currentModule) {
        System.out.println(currentModule);
        displayQuiz(currentModule);
    }

    /**
     * Quiz Functions
     */

     /**
      * Giving the user the option to take the quiz, if they are ready
      * they will take the quiz, if not they can keep studying
      */
    private void displayQuiz(Module currentModule){
        System.out.println("\nAre you ready to take the Quiz? Y/N\n");

        String ready = scanner.nextLine();

        if(ready.equalsIgnoreCase("Y")){
            System.out.println("\n*************************************************************************************************\n");
            System.out.println("\nModule Quiz");
            takeQuiz();
        }else if(ready.equalsIgnoreCase("N")){
            System.out.println("\nContinue Studying");
            //Print Out Information on the Module to a Text File
            studyGuide(currentModule);
        }
    }

    /**
     * While there are more questions the quiz displays that to the studwnent
     * and while there are not it gives the student a grade then brings them back
     * to the home screen
     */
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

    /**
     * If the student would like to return to the home screen the system will do so
     * but if not the student will stay put
     */
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

    private void leaveAComment(User author){
        System.out.println("\nWould you like to leave a Comment? Y/N");

        String decision = scanner.nextLine();
            
        if(decision.equalsIgnoreCase("Y")){
            System.out.println("\nWhom would you like to reply to with a Comment?");
            int commentIndex = scanner.nextInt();
            scanner.nextLine();
            System.out.println("\nEnter Comment");
            String text = scanner.nextLine();
            system.leaveComment(author, text, commentIndex);
            showStudentWelcomeScreen();
            showStudentMainMenu();
        }else{
            system.zeroOut();
            showStudentWelcomeScreen();
            showStudentMainMenu();
        }

    }



    /**
     * If the course creator would like to return to the home screen the system will do so
     * but if not the course creator will stay put
     */
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
     * Checks the Course Progress and how far along a student is
     */
    private void checkCourseProgress(){
        system.zeroOut();
        System.out.println("Checking Course Progress");
        showCourseProgress();
        returnToHomeScreen();
    }
    
    /**
     * Helper method to show the progress, if the user is not in any courses
     * then they will be told that, but if they are enrolled they can see how 
     * far along they are
     */
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


    /**
     * Allowing the user to see the comments under a module if there are any
     * comments to be seen
     */
    public void viewModuleComments(User author) {
        ArrayList<Comment> comments = system.getCurrentCourse().getModuleByIndex(0).getComments();
        system.zeroOut();
        if(comments == null)
            System.out.println("No comments on modules");
        for (int i = 0; i < comments.size(); i++)
            System.out.println((i+1) + " "  + comments.get(i));
        leaveAComment(author);

    }

    /**
     * Giving the user their certificate if their grade in the course is greater than an 80
     * If not they will just be returned to the home screen
     * @param grade the grade of the course
     */
    public void certificate(CourseProfile grade){
        if(grade.getGrades().size() < grade.getCourse().getModules().size()) {
            System.out.println("Complete the course with an 80 or greater to recieve a certificate!");
            return;
        }
        if(grade.getGrade() >= 80) {
            System.out.println("Congratulations! If you want to receive your certificate now press 1!");
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
    /**
     * Checking if a password is valid or not to be able to sign up
     * @param password the password used to sign up for an account
     * @return true if the password is between 8 and 25 characters and includes at least 
     * one number and one special character and false if it does not meet that criteria
     */
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
     * Giving the user a study guide if they feel as If they Need It
     * If not they will just be returned to the Home screen
     * @param currentModule the module for the study guide
     */
    public void studyGuide(Module currentModule){
            System.out.println("If you would like a Study Guide on the Module, Press 1");
            int option = scanner.nextInt();
            scanner.nextLine();
            if(option == 1) {
                Module.studyGuide(currentModule);
                returnToHomeScreen();
            } else{
                System.out.println("Returning you to the Home Screen");
                returnToHomeScreen();
            }
        }
}

