import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;

//import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DataLoaderTest {
    private UserList userList = UserList.getInstance();
    private ArrayList<User> users = userList.getUsers();
    private ArrayList<Language> favLanguages = new ArrayList<>();

	/**
	public void jsonTestingSetup(){
		new File("src").mkdirs();
	}*/
	
	@BeforeEach
	public void setup() {
		users.clear();
		users.add(new CourseCreator("Blake", "Turner", "IamBlakeTurner@outlook.com", "IamRelatedToIronMan1!", new Date(05/29/2003), "course creator"));
		users.add(new Student("Jonas", "Kovacs", "IamJonasKovacs@outlook.com", "IamRelatedToIronMan1!", new Date(05/29/2003), 0.0, favLanguages, "student"));
		DataWriter.saveStudents();
        DataWriter.saveCourseCreators();
	}
	
	@AfterEach
	public void tearDown() {
		userList.getUsers().clear();
		DataWriter.saveStudents();
        DataWriter.saveCourseCreators();
	}
	
	
	@Test
	void testGetUsersSize() {
		users = DataLoader.getUsers();
		assertEquals(2, users.size());
	}

	@Test
	void testGetUsersSizeZero() {
		userList.getUsers().clear();
		DataWriter.saveStudents();
        DataWriter.saveCourseCreators();
		assertEquals(0, users.size());
	}
	
	@Test
	void testGetUserFirstUserName() {
		users = DataLoader.getUsers();
		assertEquals("IamBlakeTurner@outlook.com", users.get(0).getEmail());
	}
}