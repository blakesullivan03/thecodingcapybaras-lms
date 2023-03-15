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

    public void addUser(User user){
        //users.add(user);
    }
    
    public void deleteUser(User user){
        //users.remove(user);
    }

    public boolean haveUser(UUID id) {
		for(User user : users) {
			if(user.getId().equals(id)) {
				return true;
			}
		}
		
		return false;
	}

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

    public Student getStudent(String userName){
        for(Student user : students) {
			if(user.getUserName().equals(userName)) {
				return user;
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

    public boolean createStudent(UUID id, String firstName, String lastName, String email, String password, Date dob,double overallGPA, ArrayList<Language> favoriteLanguages ){
        if(haveUser(id)){
            return false;
        }

        users.add(new Student(id,firstName, lastName, email, password, dob, overallGPA, favoriteLanguages));
        return true;
    }

    public void saveStudents(){
        DataWriter.saveStudents();
    }

    public void saveCourseCreators(){
        DataWriter.saveCourseCreator();
    }

}
