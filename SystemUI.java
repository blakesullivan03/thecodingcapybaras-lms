import java.util.Scanner;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

public class SystemUI{
    private Scanner scanner;
    private LMSSystem system;
    Scanner keyboard = new Scanner(System.in);

    public void run(){
        showLogInScreen();
    }

    public void showLogInScreen(){
        System.out.println("Please enter the following info. If you do not have an account, press enter.");
        System.out.println("Username: ");
        String username = keyboard.nextLine();
        System.out.println("Password: ");
        String password = keyboard.nextLine();
    }

    public void showWelcomeScreen(){
        System.out.println("Welcome to the LMS!");
        System.out.println("********Main Menu********");
        System.out.println("Please Choose one of the Following:");


    }
}
