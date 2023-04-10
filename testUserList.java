import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
public class testUserList {
    private UserList users = UserList.getInstance();
	private ArrayList<User> userList = users.getUsers();
    ArrayList<Language> favLang = new ArrayList<Language>();

    @Test
    public void addValidStudent() {
        Date date = getDateFromString("06/20/2003");
        favLang.add(Language.PYTHON);
        boolean result = userList.add(new Student("Miles", "Morales", "IamtotallySpiderMan1@starkindustries.org", "Hypnotize123!", date, 3.5, favLang, "Student"));
        assertTrue(result);
    }
    @Test
    public void addStudentDuplicate() {
        Date date = getDateFromString("05/12/2003");
        favLang.add(Language.PYTHON);
        boolean result = users.addStudent("Peter", "Parker", "IamtotallynotSpiderMan1@starkindustries.org", "GreenGoblinDeezNuts12!", date, 3.5, favLang, "Student");
        assertFalse(result);
    }
    @Test
    public void addStudentNullFavLanguages() {
        Date date = getDateFromString("05/12/2003");
        boolean result = users.addStudent("Jack", "Parker", "JP@starkindustries.org", "GreenGoblinDeezNuts12!", date, 3.5, null, "Student");
        assertFalse(result);
    }
    @Test
    public void addStudentNoFirstName() {
        Date date = getDateFromString("05/12/2003");
        favLang.add(Language.PYTHON);
        boolean result = users.addStudent("", "Strange", "ShouldabeenSupreme@outlook.com", "", date, 3.5, favLang, "Student");
        assertFalse(result);
    }
    @Test
    public void addStudentNullFirstName() {
        Date date = getDateFromString("05/12/2003");
        favLang.add(Language.PYTHON);
        boolean result = users.addStudent(null, "Strange", "ShouldabeenSupreme@outlook.com", "", date, 3.5, favLang, "Student");
        assertFalse(result);
    }
    @Test
    public void addStudentNoLastName() {
        Date date = getDateFromString("05/12/2003");
        favLang.add(Language.PYTHON);
        boolean result = users.addStudent("Stephen", "", "ShouldabeenSupreme@outlook.com", "", date, 3.5, favLang, "Student");
        assertFalse(result);
    }
    @Test
    public void addStudentNullLastName() {
        Date date = getDateFromString("05/12/2003");
        favLang.add(Language.PYTHON);
        boolean result = users.addStudent("Stephen", null, "ShouldabeenSupreme@outlook.com", "", date, 3.5, favLang, "Student");
        assertFalse(result);
    }
    @Test
    public void addStudentNoEmail() {
        Date date = getDateFromString("05/12/2003");
        favLang.add(Language.PYTHON);
        boolean result = users.addStudent("Stephen", "Strange", "", "GreenGoblinDeezNuts12!", date, 3.5, favLang, "Student");
        assertFalse(result);
    }
    @Test
    public void addStudentNullEmail() {
        Date date = getDateFromString("05/12/2003");
        favLang.add(Language.PYTHON);
        boolean result = users.addStudent("Stephen", "Strange", null, "GreenGoblinDeezNuts12!", date, 3.5, favLang, "Student");
        assertFalse(result);
    }
    @Test
    public void addStudentInvalidEmail(){
        Date date = getDateFromString("05/12/2003");
        favLang.add(Language.PYTHON);
        boolean result = users.addStudent("Stephen", "Strange", "aaaa@", "GreenGoblinDeezNuts12!", date, 3.5, favLang, "Student");
        assertFalse(result);
    }
    @Test
    public void addStudentNoPassword() {
        Date date = getDateFromString("05/12/2003");
        favLang.add(Language.PYTHON);
        boolean result = users.addStudent("Stephen", "Strange", "ShouldabeenSupreme@outlook.com", "", date, 3.5, favLang, "Student");
        assertFalse(result);
    }
    @Test
    public void addStudentNullPassword() {
        Date date = getDateFromString("05/12/2003");
        favLang.add(Language.PYTHON);
        boolean result = users.addStudent("Stephen", "Strange", "ShouldabeenSupreme@outlook.com", null, date, 3.5, favLang, "Student");
        assertFalse(result);
    }
    @Test
    public void addStudentInvalidPassword(){
        Date date = getDateFromString("05/12/2003");
        favLang.add(Language.PYTHON);
        boolean result = users.addStudent("Stephen", "Strange", "a123@outlook.com", "Swish", date, 3.5, favLang, "Student");
        assertFalse(result);
    }
    @Test
    public void addStudentTooOld(){
        Date date = getDateFromString("05/12/1452");
        favLang.add(Language.PYTHON);
        boolean result = users.addStudent("Stephen", "Strange", "a123@outlook.com", "GreenGoblinDeezNuts12!", date, 3.5, favLang, "Student");
        assertFalse(result);
    }
    @Test
    public void addStudentTooYoung(){
        Date date = getDateFromString("05/12/2082");
        favLang.add(Language.PYTHON);
        boolean result = users.addStudent("Stephen", "Strange", "a123@outlook.com", "GreenGoblinDeezNuts12!", date, 3.5, favLang, "Student");
        assertFalse(result);
    }
    @Test
    public void addStudentGPATooHigh(){
        Date date = getDateFromString("05/12/2000");
        favLang.add(Language.PYTHON);
        boolean result = users.addStudent("Stephen", "Strange", "a123@outlook.com", "GreenGoblinDeezNuts12!", date, 8.9, favLang, "Student");
        assertFalse(result);
    }
    @Test
    public void addStudentGPATooLow(){
        Date date = getDateFromString("05/12/2001");
        favLang.add(Language.PYTHON);
        boolean result = users.addStudent("Stephen", "Strange", "a123@outlook.com", "GreenGoblinDeezNuts12!", date, -1.0, favLang, "Student");
        assertFalse(result);
    }
    @Test
    public void addStudentWrongType(){
        favLang.add(Language.PYTHON);
        Date date = getDateFromString("05/12/2003");
        boolean result = users.addStudent("Stephen", "Strange", "a123@outlook.com", "GreenGoblinDeezNuts12!", date, 3.5, favLang, "Course Creator");
        assertFalse(result);
    }
    @Test
    public void addStudentInvalidType(){
        favLang.add(Language.PYTHON);
        Date date = getDateFromString("05/12/2003");
        boolean result = users.addStudent("Stephen", "Strange", "a123@outlook.com", "GreenGoblinDeezNuts12!", date, 3.5, favLang, "Shadow Wizard Money Gang");
        assertFalse(result);
    }
    

    // Course Creator Tests
    @Test
    public void addValidCourseCreator() {
        Date date = getDateFromString("12/18/1997");
        boolean result = users.addCourseCreator("John", "Popper", "Jpop@UCLA.edu", "Hypnotize123!", date, "Course Creator");
        assertTrue(result);
    }
    @Test
    public void addCourseCreatorDuplicate() {
        Date date = getDateFromString("12/18/1997");
        boolean result = users.addCourseCreator("Georgina", "Filipina", "GeorgieF@email.sdsu.edu", "sharksarecool9?", date, "Course Creator");
        assertFalse(result);
    }
    @Test
    public void addCourseCreatorNoEmail() {
        Date date = getDateFromString("12/18/1997");
        boolean result = users.addCourseCreator("John", "Popper", "", "Hypnotize123!", date, "Course Creator");
        assertFalse(result);
    }
    @Test
    public void addCourseCreatorNonEduEmail() {
        Date date = getDateFromString("12/18/1997");
        boolean result = users.addCourseCreator("John", "Popper", "Jpop@UCLA.org", "Hypnotize123!", date, "Course Creator");
        assertFalse(result);
    }
    @Test
    public void addCourseCreatorInvalidEmail() {
        Date date = getDateFromString("12/18/1997");
        boolean result = users.addCourseCreator("John", "Popper", "Jpop@aa", "Hypnotize123!", date, "Course Creator");
        assertFalse(result);
    }
    @Test
    public void addCourseCreatorNullEmail() {
        Date date = getDateFromString("12/18/1997");
        boolean result = users.addCourseCreator("John", "Popper", null, "Hypnotize123!", date, "Course Creator");
        assertFalse(result);
    }
    @Test
    public void addCourseCreatorNoPassword() {
        Date date = getDateFromString("12/18/1997");
        boolean result = users.addCourseCreator("John", "Popper", "Jpop@UCLA.edu", "", date, "Course Creator");
        assertFalse(result);
    }
    @Test
    public void addCourseCreatorNullPassword() {
        Date date = getDateFromString("12/18/1997");
        boolean result = users.addCourseCreator("John", "Popper", "Jpop@UCLA.edu", null, date, "Course Creator");
        assertFalse(result);
    }
    @Test
    public void addCourseCreatorInvalidPassword() {
        Date date = getDateFromString("12/18/1997");
        boolean result = users.addCourseCreator("John", "Popper", "Jpop@UCLA.org", "bluearmy!", date, "Course Creator");
        assertFalse(result);
    }
    @Test
    public void addCourseCreatorTooYoung() {
        Date date = getDateFromString("12/18/2022");
        boolean result = users.addCourseCreator("John", "Popper", "Jpop@UCLA.edu", "Hypnotize123!", date, "Course Creator");
        assertFalse(result);
    }
    @Test
    public void addCourseCreatorTooOld() {
        Date date = getDateFromString("12/18/1420");
        boolean result = users.addCourseCreator("John", "Popper", "Jpop@UCLA.edu", "Hypnotize123!", date, "Course Creator");
        assertFalse(result);
    }
    @Test
    public void addCourseCreatorNoFirstName() {
        Date date = getDateFromString("12/18/1997");
        boolean result = users.addCourseCreator("", "Popper", "Jpop@UCLA.edu", "Hypnotize123!", date, "Course Creator");
        assertFalse(result);
    }
    @Test
    public void addCourseCreatorNullFirstName() {
        Date date = getDateFromString("12/18/1997");
        boolean result = users.addCourseCreator(null, "Popper", "Jpop@UCLA.edu", "Hypnotize123!", date, "Course Creator");
        assertFalse(result);
    }
    @Test
    public void addCourseCreatorNoLastName() {
        Date date = getDateFromString("12/18/1997");
        boolean result = users.addCourseCreator("John", "", "Jpop@UCLA.edu", "Hypnotize123!", date, "Course Creator");
        assertFalse(result);
    }
    @Test
    public void addCourseCreatorNullLastName() {
        Date date = getDateFromString("12/18/1997");
        boolean result = users.addCourseCreator("John", null, "Jpop@UCLA.edu", "Hypnotize123!", date, "Course Creator");
        assertFalse(result);
    }
    @Test
    public void addCourseCreatorWrongType(){
        Date date = getDateFromString("12/18/1997");
        boolean result = users.addCourseCreator("John", "Popper", "JPop@UCLA.edu", "GreenGoblinDeezNuts12!", date, "Student");
        assertFalse(result);
    }
    @Test
    public void addCourseCreatorInvalidType(){
        Date date = getDateFromString("12/18/1997");
        boolean result = users.addCourseCreator("John", "Popper", "JPop@UCLA.edu", "GreenGoblinDeezNuts12!", date, "Bees Make Honey");
        assertFalse(result);
    }
    @Test
    public void haveUserValidContains(){
        boolean result = users.haveUser("IamtotallynotSpiderMan1@starkindustries.org");
        assertTrue(result);
    }
    @Test
    public void haveUserNotContains(){
        boolean result = users.haveUser("SheWantsSpider-ManButWalksPastPeterParkerEveryday@gmail.com");
        assertFalse(result);
    }
    @Test
    public void haveUserEmptyContains(){
        boolean result = users.haveUser("");
        assertFalse(result);
    }
    @Test
    public void haveUserNullContains(){
        boolean result = users.haveUser(null);
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
