import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DataLoaderTest {
    private UserList userList = UserList.getInstance();
    private ArrayList<User> users = userList.getUsers();
    private ArrayList<Language> favLanguages = new ArrayList<>();
	

	@BeforeEach
	public void setup() {
		users.clear();
		userList = UserList.getInstance();
		users = userList.getUsers();
		users.add(new CourseCreator("Blake", "Turner", "IamBlakeTurner@outlook.com", "IamRelatedToIronMan1!", new Date(2003, 05, 29), "course creator"));
		users.add(new Student("Jonas", "Kovacs", "IamJonasKovacs@outlook.com", "IamRelatedToIronMan1!", new Date(8/07/2003), 0.0, favLanguages, "student"));
		DataWriter.saveStudents();
        DataWriter.saveCourseCreators();
	}
	
	@AfterEach
	public void tearDown() {
		//userList.getUsers().clear();
		DataWriter.saveStudents();
        DataWriter.saveCourseCreators();
	}
	
	
	@Test
	void testGetUsersSize() {
		users = DataLoader.getUsers();
		assertEquals(1, users.size());
	}

	@Test
	void testGetUsersSizeZero() {
		userList.getUsers().clear();
		DataWriter.saveStudents();
        DataWriter.saveCourseCreators();
		assertEquals(0, users.size());
	}
	
	@Test
	void testGetUserFirstUserEmail() {
		users = DataLoader.getUsers();
		assertEquals("IamBlakeTurner@outlook.com", users.get(1).getEmail());
	}
}