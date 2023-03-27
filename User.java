import java.util.UUID;
import java.util.Date;

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
	
	public Date getDateOfBirth(){
		return DoB;
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

}
