import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DataWriterTest {
    private UserList userList = UserList.getInstance();
    private ArrayList<User> users = userList.getUsers();
    private ArrayList<Student> students = userList.getStudents();
    private ArrayList<CourseCreator> creators = userList.getCourseCreators();

    @BeforeEach
    public void setup(){
        UserList.getInstance().getUsers().clear();
        DataWriter.saveStudents();
        DataWriter.saveCourseCreators();
    }

    @AfterEach
	public void tearDown() {
		UserList.getInstance().getUsers().clear();
        DataWriter.saveStudents();
		DataWriter.saveCourseCreators();
	}
	
	
	@Test
	void testWritingZeroUsers() {
		users = DataLoader.getUsers();
		assertEquals(0, users.size());
	}

	@Test
	void testWritingOneStudentAndCourseCreator() {
        creators.add(new CourseCreator("Blake", "Turner", "IamBlakeTurner@outlook.com", "IamRelatedToIronMan1!", format(05/29/2003), "course creator"));
		DataWriter.saveStudents();
        DataWriter.saveCourseCreators();
		assertEquals("IamBlakeTurner@outlook.com", DataLoader.getUsers().get(0).getEmail());
	}


	
	@Test
	void testWritingFiveUsers() {
		users.add(new User("asmith", "Amy", "Smith", 19, "803-454-3344"));
		users.add(new User("bsmith", "Amy", "Smith", 19, "803-454-3344"));
		users.add(new User("csmith", "Amy", "Smith", 19, "803-454-3344"));
		users.add(new User("dsmith", "Amy", "Smith", 19, "803-454-3344"));
		users.add(new User("esmith", "Amy", "Smith", 19, "803-454-3344"));
		DataWriter.saveStudents();
        DataWriter.saveCourseCreators();
		assertEquals("esmith", DataLoader.getUsers().get(4).getEmail());
	}
	
	@Test
	void testWritingEmptyUser() {
		users.add(new User("", "", "", 0, ""));
		DataWriter.saveUsers();
		assertEquals("", DataLoader.getUsers().get(0).getEmail());
	}
	
	@Test
	void testWritingNullUser() {
		userList.add(new User(null, "", "", 0, ""));
		DataWriter.saveUsers();
		assertEquals(null, DataLoader.getUsers().get(0).getEmail());
	}
}
