import java.util.ArrayList;
import java.util.UUID;
import java.util.Date;

public class UserList {
    private static UserList userList;
    private ArrayList<User> users;

    private ArrayList<Student> students;
    private ArrayList<CourseCreator> courseCreators;

    private UserList(){
        users = DataLoader.getUsers();
    }

    public static UserList getInstance(){
        if(userList == null) {
			userList = new UserList();
        }
		return userList;
    }

    //overload 
    public User addStudent(String firstName, String lastName, String email, String password, Date DoB, double overallGPA, ArrayList<Language> favoriteLanguages, String type){
        if(haveUser(email)){
            return null;
        }
        //add user
        // depending on type, add student or add course creator
        Student newStudent = new Student(firstName, lastName, email, password, DoB, overallGPA, favoriteLanguages, type);
        users.add(newStudent);
        return newStudent;
    }

    public User addCourseCreator(String firstName, String lastName, String email, String password, Date DoB, String type){
        if(haveUser(email)){
            return null;
        }
        //add user
        // depending on type, add student or add course creator
        CourseCreator newCourseCreator = new CourseCreator(firstName, lastName, email, password, DoB, type);
        users.add(newCourseCreator);
        return newCourseCreator;
    }
    
    public void deleteUser(User user){
        //users.remove(user);
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
    //Loop throught user list
    public User getUserByID(UUID id){
        for(User user : users) {
			if(user.getId().equals(id)) {
				return user;
			}
		}
        return null;
    }

    public User getUser(String id){
        for(User user : users) {
			if(user.getUserName().equalsIgnoreCase(id)) {
				return user;
			}
		}
		return null;
    }

    public User getUser(String email, String password) {
        for(User user : users) {
			if(user.getEmail().equalsIgnoreCase(email) && user.getPassword().equals(password)) {
				return user;
			}
		}
		return null;
    }

    public String getUserType(String email, String password) {
        for(User user : users) {
			if(user.getEmail().equalsIgnoreCase(email) && user.getPassword().equals(password)) {
				return user.getAccountType();
			}
		}
		return null;
    }

    public ArrayList<User> getUsers(){
        System.out.println(users);
        return users;
    }

    public ArrayList<Student> getStudents(){
        return students;
    }
    public ArrayList<CourseCreator> getCourseCreators(){
        return courseCreators;
    }
    public void saveStudents(){
        DataWriter.saveStudents();
    }

    /**public void saveCourseCreators(){
        DataWriter.saveCourseCreator();
    }*/
}
