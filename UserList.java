import java.util.ArrayList;
import java.util.UUID;
import java.util.Date;

public class UserList {
    private static UserList userList;
    private ArrayList<User> users;

    private ArrayList<Student> students;
    private ArrayList<CourseCreator> courseCreators;
    /**
     * Constructs a new UserList object.
     * The list of users is initialized by loading data from the DataLoader class.
     * */
    private UserList(){
        users = DataLoader.getUsers();
    }
    /**
     * Returns the instance of the UserList class.
     * If the instance has not been created, it creates a new instance.
     * @return the instance of the UserList class
     **/
    public static UserList getInstance(){
        if(userList == null) {
			userList = new UserList();
        }
		return userList;
    }
    /**
     * Adds a new student to the list of users.
     * If the user with the same email already exists in the list, the method returns null.
     * @param firstName the first name of the new student
     * @param lastName the last name of the new student
     * @param email the email of the new student
     * @param password the password of the new student
     * @param DoB the date of birth of the new student
     * @param overallGPA the overall GPA of the new student
     * @param favoriteLanguages the list of favorite languages of the new student
     * @param type the account type of the new student
     * @return the new student if added successfully, null otherwise
     * */
    //overload 
    public boolean addStudent(String firstName, String lastName, String email, String password, Date DoB, double overallGPA, ArrayList<Language> favoriteLanguages, String type){
        if(haveUser(email)){
            return false;
        }
        //add user
        // depending on type, add student or add course creator
        Student newStudent = new Student(firstName, lastName, email, password, DoB, overallGPA, favoriteLanguages, type);
        users.add(newStudent);
        System.out.println(newStudent);
        //System.out.println(users);
        return true;
    }
    /**
     * Adds a new course creator to the list of users.
     * If the user with the same email already exists in the list, the method returns null.
     * @param firstName the first name of the new course creator
     * @param lastName the last name of the new course creator
     * @param email the email of the new course creator
     * @param password the password of the new course creator
     * @param DoB the date of birth of the new course creator
     * @param type the account type of the new course creator
     * @return the new course creator if added successfully, null otherwise
     * */
    public boolean addCourseCreator(String firstName, String lastName, String email, String password, Date DoB, String type){
        if(haveUser(email)){
            return false;
        }
        //add user
        // depending on type, add student or add course creator
        CourseCreator newCourseCreator = new CourseCreator(firstName, lastName, email, password, DoB, type);
        users.add(newCourseCreator);
        return true;
    }
    

    // can have the same name, same password, same DOB, but not the same email
    public boolean haveUser(String email) {
		for(User user : users) {
			if(user.getEmail().equals(email)) {
				return true;
			}
		}
		return false;
	}

   /*  public boolean haveUser(UUID id) {
		for(User user : users) {
			if(user.getId().equals(id)) {
				return true;
			}
		}
		
		return false;
	}
*/
    /**
     * Retrieves a User object based on its unique ID.
     * @param id the unique ID of the User to be retrieved
     * @return the User object corresponding to the given ID, or null if no such User exists
     * */
    //Loop throught user list
    public User getUserByID(UUID id){
        for(User user : users) {
			if(user.getId().equals(id)) {
				return user;
			}
		}
        return null;
    }



    public User getUser(String email) {
        for(User user : users) {
			if(user.getEmail().equalsIgnoreCase(email)) {
				return user;
			}
		}
		return null;
    }
    /**
     * Retrieves the account type of a User object based on its email and password.
     * @param email the email address of the User whose account type is to be retrieved
     * @param password the password of the User whose account type is to be retrieved
     * @return the account type of the User corresponding to the given email and password, or null if no such User exists
     * */

    public String getUserType(String email) {
        for(User user : users) {
			if(user.getEmail().equalsIgnoreCase(email)) {
				return user.getAccountType();
			}
		}
		return null;
    }
    /**
     * Retrieves a list of all User objects.
     * @return an ArrayList containing all User objects in the system
     * */
    public ArrayList<User> getUsers(){
        return users;
    }
    /**
     * Retrieves a list of all Student objects.
     * @return an ArrayList containing all Student objects in the system
     * */

    public ArrayList<Student> getStudents(){
        return students;
    }
    /**
     * Retrieves a list of all CourseCreator objects.
     * @return an ArrayList containing all CourseCreator objects in the system
     * */
    public ArrayList<CourseCreator> getCourseCreators(){
        return courseCreators;
    }

    public void saveUsers(){
        DataWriter.saveUsers();
    }

    /**public void saveCourseCreators(){
        DataWriter.saveCourseCreator();
    }*/
}
