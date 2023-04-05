import java.util.UUID;
import java.util.Date;
import java.util.ArrayList;
import java.text.SimpleDateFormat;

/**
 * A user for our LMS
 */
public abstract class User {
    protected UUID id;
    protected String firstName; 
    protected String lastName;
    protected String email;
    protected String username;
    protected String password;
    protected String type;
    protected Date DoB;
    protected Student student;
    

    /**
     * Creating a new User with overriden parameters for later use
     * @param firstName the user's first name
     * @param lastName the user's last name 
     * @param email the user's email
     * @param password the user's password
     * @param DoB the user's date of birth
     * @param type the user's type of account (student or course creator)
     */
    public User(String firstName, String lastName, String email, String password, Date DoB, String type) { // loading from json
            this.id = UUID.randomUUID();
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.password = password;
            this.DoB = DoB;
            this.type = type;
    }
    
     /**
     * Creating a new User
     * @param id the unique id for a user
     * @param firstName the user's first name
     * @param lastName the user's last name 
     * @param email the user's email
     * @param password the user's password
     * @param DoB the user's date of birth
     * @param type the user's type of account (student or course creator)
     */
    public User(UUID id, String firstName, String lastName, String email,String password, Date DoB, String type) { // not loading from json
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.password = password;
            this.DoB = DoB;
            this.type = type;
    }

    /**
     * Getting the user's unique id
     * @return the user's unique id
     */
    public UUID getId(){
		return id;
	}

    /**
     * Getting the user's email
     * @return the user's email
     */
    public String getEmail(){
		return email;
	}

    /**
     * Getting the user's password
     * @return the user's password
     */
    public String getPassword(){
        return password;
    }
	
    /**
     * Getting the user's first name
     * @return the user's first name
     */
	public String getFirstName(){
		return firstName;
	}
	
    /**
     * Setting the user's first name
     * @param firstName the user's first name
     */
	public void setFirstName(String firstName){
		this.firstName = firstName;
	}
	
    /**
     * Getting the user's last name
     * @return the user's last name
     */
	public String getLastName(){
		return lastName;
	}
	
    /**
     * Setting the user's last name
     * @param lastName the user's last name
     */
	public void setLastName(String lastName){
		this.lastName = lastName;
	}
	
    /**
     * Getting the user's date of birth
     * @return the user's date of birth in the correct format
     */
	public String getDateOfBirth(){
		return getFormattedDate(DoB);
	}
	
    /**
     * Getting the type of accout (student or course creator)
     * @return the account type (student or course creator)
     */
    public String getAccountType(){
        return type;
    }

    /**
     * Setting the account type
     * @param type the type of acount
     */
    public void setAccountType(String type){
        this.type = type;
    }

    /**
     * Displaying the date in the right format
     * @param date the date for date of birth
     * @return the date in the right format
     */
    private String getFormattedDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        return simpleDateFormat.format(date);
    }

    /**
     * Displaying the user's information
     * @return the user's information if there is a user
     * or a blank string if there is not a user.
     */
    public String toString(){
        UserList users = UserList.getInstance();
		ArrayList<User> userList = users.getUsers();
        String result = "";
        if(userList == null){
            return result;
        }
        else{
            for(User currentUser : userList){
                result += "\n\n" + "UUID: " + currentUser.getId() + " First Name: " + currentUser.getFirstName() + " Last Name: " + currentUser.getLastName() + " DOB: " + currentUser.getDateOfBirth() + " Email " + currentUser.getEmail() + " Type: " + currentUser.getAccountType();
            }
        }
        return result;
    }

}
