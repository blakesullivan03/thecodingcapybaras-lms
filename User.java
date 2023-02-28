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
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.DoB = DoB;

    }
    
    public User(String firstName, String lastName, String email,
        String username, String password, Date DoB) { // not loading from json
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.DoB = DoB;
     }
}
