import java.sql.SQLException;
import java.util.Scanner;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;

class Home{
  Connection connection;
  public void displayHomepage(){
    try{
      Statement stmt=null;
      ResultSet rs= null;


      Scanner t= new Scanner(System.in);
      SignUp signup= new SignUp();
      Login login= new Login();


      System.out.println("Enter choice (1-2)\n 1. Login\n 2. Exit");
      int user_choice = t.nextInt();
      switch(user_choice){
        case 1: login.displayLogin();
          break;
        case 2: System.out.println("Exiting the application!");
          break;
        default: System.out.println("Please enter a valid choice");
          displayHomepage();
          break;
      }
    }catch (Exception e){
      System.out.println("Ignoring the error please check");
//       displayHomepage();
    }
    
  }
}
