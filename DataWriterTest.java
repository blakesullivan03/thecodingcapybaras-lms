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
    //private ArrayList<Student> students = userList.getStudents();
    //private ArrayList<CourseCreator> creators = userList.getCourseCreators();
	private ArrayList<Language> favLanguages = new ArrayList<>();

	

    @BeforeEach
    public void setup(){
        UserList.getInstance().getUsers().clear();
        DataWriter.saveStudents();
        DataWriter.saveCourseCreators();
    }

    //@AfterEach
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
        users.add(new CourseCreator("Blake", "Turner", "IamBlakeTurner@outlook.com", "IamRelatedToIronMan1!", new Date(05/29/2003), "course creator"));
		DataWriter.saveStudents();
        DataWriter.saveCourseCreators();
		assertEquals("IamBlakeTurner@outlook.com", DataLoader.getUsers().get(0).getEmail());
	}


	
	@Test
	void testWritingFiveUsers() {
		Date date = getDateFromString("05/12/1452");
		users.add(new CourseCreator("Blake", "Turner", "IamBlakeTurner@outlook.com", "IamRelatedToIronMan1!", date, "course creator"));
		users.add(new CourseCreator("Blake", "Turner", "IamBlakeTurner@outlook.com", "IamRelatedToIronMan1!", new Date(05/29/2003), "course creator"));
		users.add(new Student("Jonas", "Kovacs", "IamJonasKovacs@outlook.com", "IamRelatedToIronMan1!", new Date(05/29/2003), 0.0, favLanguages, "student"));
		users.add(new Student("Matt", "Fowler", "IamMattFowler@outlook.com", "IamRelatedToIronMan1!", new Date(05/29/2003), 0.0, favLanguages, "student"));
		users.add(new Student("Michael", "Hernandez", "IamMichaelHernandez@outlook.com", "IamRelatedToIronMan1!", new Date(05/29/2003), 0.0, favLanguages, "student"));
		DataWriter.saveStudents();
        DataWriter.saveCourseCreators();
		assertEquals("IamBlakeTurner@outlook.com", DataLoader.getUsers().get(4).getEmail());
	}
	
	@Test
	void testWritingEmptyUser() {
		users.add(new Student("", "", "", "", new Date(), 0.0, favLanguages, "student"));
		DataWriter.saveStudents();
		DataWriter.saveCourseCreators();
		assertEquals("", DataLoader.getUsers().get(0).getEmail());
	}
	
	@Test
	void testWritingNullUser() {
		users.add(new Student("", "", null, "", new Date(05/29/2003),0.0, favLanguages, ""));
		DataWriter.saveStudents();
		DataWriter.saveCourseCreators();
		assertEquals(null, DataLoader.getUsers().get(0).getEmail());
	}

	private Date getDateFromString(String data){
		try {
            return new SimpleDateFormat("MM/dd/yyyy").parse(data);
        } catch (Exception e) {
            System.out.println("here");
            return new Date();
        }
	}
	
}
