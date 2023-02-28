import java.util.Date;
import java.util.UUID;

public abstract class User {
    protected UUID id;
    protected String firstName; 
    protected String lastName;
    protected String email;
    protected String username;
    protected String password;
    protected Date DoB;

    public User(UUID id, String firstName, String lastName, String email,
        String username, String password, Date DoB) { // loading from json
        
    }
    
    public User(String firstName, String lastName, String email,
        String username, String password, Date DoB) { // not loading from json
        

    }
}
