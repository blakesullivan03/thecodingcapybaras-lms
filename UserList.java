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

    public boolean addCourseCreator(String firstName, String lastName, String email, String password, Date DoB, String type){
        if(haveUser(email)){
            return false;
        }
        //add user
        // depending on type, add student or add course creator
        CourseCreator newCourseCreator = new CourseCreator(firstName, lastName, email, password, DoB, type);
        users.add(newCourseCreator);
        System.out.println(newCourseCreator);
        System.out.println(users);
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
    //Loop throught user list
    public User getUserByID(UUID id){
        for(User user : users) {
			if(user.getId().equals(id)) {
				return user;
			}
		}
        return null;
    }


    public User getUser(String email){
        for(User user : users) {
			if(user.getEmail().equalsIgnoreCase(email)) {
				return user;
			}
		}
		return null;
    }

    public String getUserType(String email) {
        for(User user : users) {
			if(user.getEmail().equalsIgnoreCase(email)) {
				return user.getAccountType();
			}
		}
		return null;
    }

    public ArrayList<User> getUsers(){
        return users;
    }

    public ArrayList<Student> getStudents(){
        return students;
    }

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
