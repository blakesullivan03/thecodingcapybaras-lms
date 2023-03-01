import java.util.ArrayList;
import java.util.UUID;

public class UserList {
    private static UserList userList;
    private ArrayList<User> users;
    private ArrayList<Student> students;
    private ArrayList<CourseCreator> courseCreators;

    private UserList(){
        students = DataLoader.getStudent();
        courseCreators = DataLoader.getCourseCreator();
    }

    public static UserList getInstance(){
        if(userList == null) {
			userList = new UserList();
		}
		
		return userList;
    }

    public void addUser(User user){

    }
    
    public void deleteUser(User user){

    }

    public boolean haveUser(String userName) {
		for(User user : users) {
			if(user.getUserName().equals(userName)) {
				return true;
			}
		}
		
		return false;
	}

    public User getUser(String userName){
        for(User user : users) {
			if(user.getUserName().equals(userName)) {
				return user;
			}
		}
		
		return null;
    }

    public Student getStudent(String userName){
        for(Student user : students) {
			if(user.getUserName().equals(userName)) {
				return user;
			}
		}
		
		return null;
    }

    public ArrayList<User> getUser(){
        return users;
    }

    public ArrayList<Student> getStudent(){
        return students;
    }

    public ArrayList<CourseCreator> getCourseCreator(){
        return courseCreators;
    }

    public boolean createStudent(UUID id, String firstName, String lastName, String email, String username, String password, String dob){
        if(haveUser(username)){
            return false;
        }

        students.add(new Student(id,firstName, lastName, email, username, password, dob));
        return true;
    }

}
