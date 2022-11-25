import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

class Login {
    Connection connection;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    Admin admin = new Admin();
    Receptionist receptionist = new Receptionist();
    Customer customer = new Customer();
    Manager manager = new Manager();

    //    Mechanic mechanic=new Mechanic();
    public void displayLogin() {
        Scanner t = new Scanner(System.in);
        Home home = new Home();
        String s = "";
        System.out.println("Please enter the following");
        // write logic to implement login
        System.out.print("A. User ID:\t");
        String user_id = t.nextLine();

        System.out.print("Password:\t");
        String user_password = t.nextLine();


        try {
            connection = DBUtility.connectDB(SetupConnection.username, SetupConnection.password);

            stmt = connection.prepareStatement("SELECT Pass FROM Login WHERE LoginId=?");
            stmt.setString(1, user_id);
            rs = stmt.executeQuery();
            if(rs.next()){
                s = rs.getString(1);
            }

            rs.close();
            DBUtility.close(connection);

        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            DBUtility.close(connection);

        }

        if (user_password.equals(s)) {
            enterPortal(home, user_id, user_password);
        } else {
            System.out.println("Invalid Username/password");
            home.displayHomepage();
        }

    }

    private void enterPortal(Home home, String user_id, String user_password) {
        try {
            connection = DBUtility.connectDB(SetupConnection.username, SetupConnection.password);

            stmt = connection.prepareStatement("SELECT AdminName FROM Admin WHERE AdminName=?");
            stmt.setString(1, user_id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                admin.displayAdminLanding();
                rs.close();
            }


            stmt = connection.prepareStatement("SELECT ManagerName FROM Manager_Emp WHERE ManagerName=?");
            stmt.setString(1, user_id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                manager.displayManagerLanding(user_id);
                rs.close();
            }


            stmt = connection.prepareStatement("SELECT ReceptionistName FROM Receptionist_Emp WHERE ReceptionistName=?");
            stmt.setString(1, user_id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                receptionist.displayReceptionistLanding(user_id);
                rs.close();
            }


//        stmt=connection.prepareStatement("SELECT LoginID FROM Mechanic WHERE TRIM(LoginId)=?");
//        stmt.setString(1, user_id);
//        rs = stmt.executeQuery();

//        if (rs.next()) {
//            mechanic.displayMechanicLanding(user_id);
//        }
//        rs.close();

            stmt = connection.prepareStatement("SELECT Cust_Name FROM Customer WHERE Cust_Name=?");
            stmt.setString(1, user_id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                customer.displayCustomerLanding(user_id);
                rs.close();
            }

            DBUtility.close(connection);

        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            DBUtility.close(connection);

        }

    }

}


