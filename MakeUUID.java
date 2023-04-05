import java.util.UUID;


/**
 * The MakeUUID class generates and prints 10 random UUIDs.
 */
public class MakeUUID {
    /**
     * The main method generates and prints 10 random UUIDs.
     * @param args command line arguments (not used)
     */
    public static void main(String[] args){
        for(int i = 0; i < 10; i++){
            System.out.println(UUID.randomUUID());
        }
    }
}
