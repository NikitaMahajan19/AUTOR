import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

class Receptionist{
	String UserId;
	Connection connection;
	PreparedStatement stmt;
	ResultSet rs;
	ResultSet rs1;

    public void displayReceptionistLanding(String user_id){
    UserId = user_id;
    Scanner t= new Scanner(System.in);
    Home home= new Home();
    Employee employee= new Employee();
    Customer customer = new Customer();
    
    System.out.println("Please select one of the following\n");
    System.out.println("1. Add New Customer Profile\n" + "2. Find Customer with Pending Invoices" + "\n" + "3. Logout");

    int user_choice = t.nextInt();
    t.nextLine();
    String id ="";
    switch(user_choice){
      case 1: addCustomer();
      break;
      case 2:customerInvoicePending();
		break;
      case 3: home.displayHomepage();
      break;
      default: System.out.println("Please enter a valid choice");
      break;
    }
  }


  public void addCustomer(){
    Scanner t= new Scanner(System.in);
    String recent_service="";
    String cust_id = "";
    String center_id="";
    

    System.out.println("Please enter the following details");
    // run queries to add the following details into database

    System.out.print("A. Customer Name:\t");
    String customer_name= t.nextLine();
    System.out.println("");

    System.out.print("B. Address:\t");
    String customer_address= t.nextLine();
    System.out.println("");

    System.out.print("C. Email address:\t");
    String customer_email= t.nextLine();
    System.out.println("");

    System.out.print("D. Phone Number:\t");
    String customer_phone= t.nextLine();
    System.out.println("");

    System.out.print("E. Username:\t");
    String customer_username= t.nextLine();
    System.out.println("");

    System.out.print("F. VIN Number:\t");
    String license_plate= t.nextLine();
    System.out.println("");

    System.out.print("G. Car Manufacturer:\t");
    String make= t.nextLine();
    System.out.println("");

    System.out.print("H. Current Mileage:\t");
    int current_mileage= t.nextInt();
    t.nextLine();
    System.out.println("");

    System.out.print("I. Year:\t");
    String year= t.nextLine();
    System.out.println("");

    String car_type = make + "," + year;

    System.out.println("Please select one of the following");
    System.out.println("1. Register" + "\n" + "2. Go Back");

    int user_choice= t.nextInt();
    t.nextLine();
    switch (user_choice) {
      case 1:
    	  try{
    	      connection= DBUtility.connectDB(SetupConnection.username, SetupConnection.password);
    	      
    	      stmt= connection.prepareStatement("SELECT Type_Recent_Service FROM Cars WHERE LicensePlateID = ? ");
    	      stmt.setString(1, license_plate);
    	      rs= stmt.executeQuery();
    	      
    	      while(rs.next())
    	    	  recent_service = rs.getString(1);  
    	      
    	      
    	      stmt.close();
    	      rs.close();
    	      
    	      stmt= connection.prepareStatement("INSERT INTO Cars VALUES (?,?,?,?,?,?)");
    	      stmt.setString(1,license_plate);
			  stmt.setString(2,null);
			  stmt.setString(3,null);
    	      stmt.setInt(4, current_mileage);
			  stmt.setString(5,null);
			  stmt.setString(6,null);
    	      stmt.executeUpdate();
    	      
    	      stmt.close();
    	      
    	      stmt = connection.prepareStatement("SELECT CustID FROM Customer WHERE Cust_name = ?");
    	      stmt.setString(1, customer_name);
    	      rs = stmt.executeQuery();
    	      
    	      while(rs.next()) {
    	    	  cust_id = rs.getString(1);
    	      }
    	      
    	      rs.close();
    	      stmt.close();
    	      
    	      stmt= connection.prepareStatement("SELECT CenterID FROM WorksAt WHERE EmpID = ?");
    	      stmt.setString(1, UserId);
    	      rs= stmt.executeQuery();
    	      
    	      while(rs.next()) {
    	    	  center_id = rs.getString(1);
    	      }
    	      
    	      stmt.close();
    	      rs.close();
    	      
    	      stmt= connection.prepareStatement("INSERT INTO GoesTo VALUES (?, ?, ?)");
    	      stmt.setString(1, license_plate);
    	      stmt.setString(2, cust_id);
    	      stmt.setString(3, center_id);
    	      stmt.executeUpdate();
    	      
    	      stmt.close();

    	      DBUtility.close(connection);

    	    }catch(SQLException e){
    	      System.out.println("Connection Failed! Check output console");
    	      e.printStackTrace();
    	      DBUtility.close(connection);
    	      
    	    }
            System.out.println("Customer Successfully Added");
            displayReceptionistLanding(UserId);
            break;
      case 2: displayReceptionistLanding(UserId);
              break;
      default: System.out.println("Enter a valid choice");
      break;
    }
  }


  public void customerInvoicePending(){
	  try{
		  connection= DBUtility.connectDB(SetupConnection.username, SetupConnection.password);
		  stmt = connection.prepareStatement("select CustID,"+
				  "centerID,"+
				  "Invoiceid,"+
				   "InvoiceDate,"+
				  "total_price" +
				  " from Invoice where status = ?");
		  stmt.setString(1,"Unpaid");
		  rs = stmt.executeQuery();

		  while(rs.next()) {
			  int cust_ID = rs.getInt(1);
			  String center_ID = rs.getString(2);

			  stmt = connection.prepareStatement("select Cust_Name" +
					  " from Customer where CustID=? AND CenterID=?");
			  stmt.setInt(1, cust_ID);
			  stmt.setString(2, center_ID);
			  rs1 = stmt.executeQuery();
			  if(rs1.next()){
				  System.out.println("A. Customer ID: " + rs.getInt(1));
				  System.out.println("B. Customer Name: " + rs1.getString(1));
				  System.out.println("C. Invoice status: "+ rs.getInt(2));
				  System.out.println("D. Date: "+ rs.getInt(3));
				  System.out.println("E. Total price: "+ rs.getInt(4));
				  System.out.println("");
			  }
			  rs1.close();

		  }


		  rs.close();
		  stmt.close();

	  }catch (Exception ex){
		  ex.printStackTrace();
	  }
	  displayReceptionistLanding(UserId);
  }


}
