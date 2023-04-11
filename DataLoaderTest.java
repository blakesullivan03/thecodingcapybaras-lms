import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

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
		userList.getUsers().clear();
		Date date = getDateFromString("05-29-2003");
		favLanguages.add(Language.PYTHON);
		users.add(new CourseCreator("Blake", "Turner", "IamBlakeTurner@outlook.com", "IamRelatedToIronMan1!", date, "course creator"));
		users.add(new Student("Jonas", "Kovacs", "IamJonasKovacs@outlook.com", "IamRelatedToIronMan1!", date, 0.0, favLanguages, "student"));
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
		assertEquals(2, users.size());
	}

	@Test
	void testGetUsersSizeZero() {
		UserList.getInstance().getUsers().clear();
		DataWriter.saveStudents();
        DataWriter.saveCourseCreators();
		assertEquals(0, users.size());
	}
	
	@Test
	void testGetUserFirstUserEmail() {
		users = DataLoader.getUsers();
		assertEquals("IamBlakeTurner@outlook.com", users.get(1).getEmail());
	}

	private Date getDateFromString(String data){
		try {
            return new SimpleDateFormat("MM-dd-yyyy").parse(data);
        } catch (Exception e) {
            return null;
        }
	}
}