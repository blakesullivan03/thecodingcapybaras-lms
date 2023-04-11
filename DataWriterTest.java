import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test; 

class DataWriterTest {
    private UserList userList = UserList.getInstance();
    private ArrayList<User> users = userList.getUsers();
	private ArrayList<Language> favLanguages = new ArrayList<>();

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
		users = UserList.getInstance().getUsers();
		assertEquals(0, users.size());
	}

	@Test
	void testWritingOneStudentAndCourseCreator() {
		Date date = getDateFromString("05-29-2003");
		favLanguages.add(Language.PYTHON);
        users.add(new CourseCreator("Blake", "Turner", "IamBlakeTurner@outlook.com", "IamRelatedToIronMan1!", date, "course creator"));
		users.add(new Student("Jonas", "Kovacs", "IamJonasKovacs@outlook.com", "IamRelatedToIronMan1!", date, 0.0, favLanguages, "student"));
		DataWriter.saveStudents();
        DataWriter.saveCourseCreators();
		assertEquals("IamBlakeTurner@outlook.com", DataLoader.getUsers().get(1).getEmail());
	}


	
	@Test
	void testWritingMultipleUsers() {
		Date date = getDateFromString("05-29-2003");
		favLanguages.add(Language.PYTHON);
		users.add(new CourseCreator("Blake", "Turner", "IamBlakeTurner@outlook.com", "IamRelatedToIronMan1!", date, "course creator"));
		users.add(new Student("Jonas", "Kovacs", "IamJonasKovacs@outlook.com", "IamRelatedToIronMan1!", date, 0.0, favLanguages, "student"));
		users.add(new Student("Matt", "Fowler", "IamMattFowler@outlook.com", "IamRelatedToIronMan1!", date, 0.0, favLanguages, "student"));
		users.add(new Student("Michael", "Hernandez", "IamMichaelHernandez@outlook.com", "IamRelatedToIronMan1!", date, 0.0, favLanguages, "student"));
		DataWriter.saveStudents();
        DataWriter.saveCourseCreators();
		assertEquals("IamBlakeTurner@outlook.com", DataLoader.getUsers().get(1).getEmail());
	}
	
	@Test
	void testWritingEmptyUser() {
		Date date = getDateFromString("05-29-2003");
		users.add(new Student("", "", "", "", date, 0.0, favLanguages, "student"));
		DataWriter.saveStudents();
		DataWriter.saveCourseCreators();
		assertEquals("", DataLoader.getUsers().get(0).getEmail());
	}
	
	@Test
	void testWritingNullUser() {
		Date date = getDateFromString("");
		users.add(new Student("", "", null, "", date,0.0, favLanguages, ""));
		DataWriter.saveStudents();
		DataWriter.saveCourseCreators();
		assertEquals(null, DataLoader.getUsers().get(0).getEmail());
	}

	private Date getDateFromString(String data){
		try {
            return new SimpleDateFormat("MM-dd-yyyy").parse(data);
        } catch (Exception e) {
            return null;
        }
	}
	
}
