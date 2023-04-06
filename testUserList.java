import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
public class testUserList {
    private UserList users = UserList.getInstance();
	private ArrayList<User> userList = users.getUsers();

    @Test
    public void addValidStudent() {
        Date date = getDateFromString("05/12/2003");
        boolean result = users.addStudent("Miles", "Morales", "IamtotallySpiderMan1@starkindustries.org", "Hypnotize123!", date, 3.5, new array list, "Student");
       assertTrue(result);
    }
    //null favoritelanguages
    @Test
    public void addStudentDuplicate() {
        Date date = getDateFromString("05/12/2003");
        boolean result = users.addStudent("Peter", "Parker", "IamtotallynotSpiderMan1@starkindustries.org", "GreenGoblinDeezNuts12!", date, 3.5, null, "Student");
       assertFalse(result);
    }
    @Test
    public void addStudentNullEmail() {
        Date date = getDateFromString("05/12/2003");
        boolean result = users.addStudent("Stephen", "Strange", "", "GreenGoblinDeezNuts12!", date, 3.5, null, "Student");
       assertFalse(result);
    }
    @Test
    public void addStudentNull() {
        Date date = getDateFromString("05/12/2003");
        boolean result = users.addStudent("Stephen", "Strange", null, "GreenGoblinDeezNuts12!", date, 3.5, null, "Student");
       assertFalse(result);
    }
    @Test
    public void addStudentInvalidEmail(){
        Date date = getDateFromString("05/12/2003");
        boolean result = users.addStudent("Stephen", "Strange", "aaaa@", "GreenGoblinDeezNuts12!", date, 3.5, null, "Student");
        assertFalse(result);
    }
    @Test
    public void addStudentTooOld(){
        Date date = getDateFromString("05/12/1452");
        boolean result = users.addStudent("Stephen", "Strange", "aaaa@", "GreenGoblinDeezNuts12!", date, 3.5, null, "Student");
        assertFalse(result);
    }
    @Test
    public void addStudentTooYoung(){
        Date date = getDateFromString("05/12/2082");
        boolean result = users.addStudent("Stephen", "Strange", "aaaa@", "GreenGoblinDeezNuts12!", date, 3.5, null, "Student");
        assertFalse(result);
    }
    @Test
    public void addStudentGPATooHigh(){
        Date date = getDateFromString("05/12/1452");
        boolean result = users.addStudent("Stephen", "Strange", "aaaa@", "GreenGoblinDeezNuts12!", date, 8.9, null, "Student");
        assertFalse(result);
    }
    @Test
    public void addStudentGPATooLow(){
        Date date = getDateFromString("05/12/1452");
        boolean result = users.addStudent("Stephen", "Strange", "aaaa@", "GreenGoblinDeezNuts12!", date, -1.0, null, "Student");
        assertFalse(result);
    }
    @Test
    public void addStudentNoFavLanguages(){
        Date date = getDateFromString("05/12/1452");
        boolean result = users.addStudent("Stephen", "Strange", "aaaa@", "GreenGoblinDeezNuts12!", date, 8.9, null, "Student");
        assertFalse(result);
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
