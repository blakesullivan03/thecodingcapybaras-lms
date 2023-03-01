import java.util.Date;
import java.util.UUID;

public abstract class User {
    protected UUID id;
    protected String firstName; 
    protected String lastName;
    protected String email;
    protected String username;
    protected String password;
    protected String DoB;

    public User(String firstName, String lastName, String email,
        String username, String password, String DoB) { // loading from json
            this.id = UUID.randomUUID();
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.username = username;
            this.password = password;
            this.DoB = DoB;
    }
    
    public User(UUID id, String firstName, String lastName, String email,
        String username, String password, String DoB) { // not loading from json
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.username = username;
            this.password = password;
            this.DoB = DoB;
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
		return DoB;
	}
	
	public String getEmail(){
		return email;
	}
	
	public void setPhoneNumber(String email){
		this.email = email;
	}


}
