import java.util.UUID;
import java.util.Date;
import java.util.ArrayList;
import java.text.SimpleDateFormat;

public abstract class User {
    protected UUID id;
    protected String firstName; 
    protected String lastName;
    protected String email;
    protected String username;
    protected String password;
    protected String type;
    protected Date DoB;
    


    public User(String firstName, String lastName, String email, String password, Date DoB, String type) { // loading from json
            this.id = UUID.randomUUID();
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.password = password;
            this.DoB = DoB;
            this.type = type;
    }
    
    public User(UUID id, String firstName, String lastName, String email,String password, Date DoB, String type) { // not loading from json
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.password = password;
            this.DoB = DoB;
            this.type = type;
    }

    public UUID getId(){
		return id;
	}

	
	public String getUserName(){
		return username;
	}

    public String getPassword(){
        return password;
    }
	
	public String getFirstName(){
		return firstName;
	}
	
	public void setFirstName(String firstName){
		this.firstName = firstName;
	}
	
	public String getLastName(){
		return lastName;
	}
	
	public void setLastName(String lastName){
		this.lastName = lastName;
	}
	
	public String getDateOfBirth(){
		return getFormattedDate(DoB);
	}
	
	public String getEmail(){
		return email;
	}

    public String getAccountType(){
        return type;
    }



    public void setAccountType(String type){
        this.type = type;
    }

    private String getFormattedDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        return simpleDateFormat.format(date);
    }

    public String toString(){
        UserList users = UserList.getInstance();
		ArrayList<User> userList = users.getUsers();
        String result = "";
        if(userList == null){
            return result;
        }
        else{
            for(User currentUser : userList){
                result += "\n\n" + "UUID: " + currentUser.getId() + "First Name: " + currentUser.getFirstName() + " Last Name: " + currentUser.getLastName() + "DOB: " + currentUser.getDateOfBirth() + "Email " + currentUser.getEmail();
            }
        }
        return result;
    }

}
