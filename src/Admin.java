import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;

class Admin{

    Connection connection;
    PreparedStatement stmt;
    ResultSet rs;

    public void displayAdminLanding(){
        Scanner t= new Scanner(System.in);
        Home home= new Home();
        Employee employee= new Employee();
        Customer customer = new Customer();

        System.out.println("Please select one of the following\n");
        System.out.println("1. System Set Up\n" + "2. Add New Store\n" + "3. Add New Service\n" + "4. Logout");

        int user_choice = t.nextInt();
        t.nextLine();
        String id ="";
        switch(user_choice){
            case 1: systemSetUp();
                break;
            case 2: addNewStore();
                break;
            case 3: addNewService();
                break;
            case 4: home.displayHomepage();
                break;
            default: System.out.println("Please enter a valid choice");
                break;
        }
    }


    public void systemSetUp() {
        Scanner t = new Scanner(System.in);
        System.out.println("\nPlease select one of the following\n");
        System.out.println("1. Setup service with general information\n" + "2. Setup store with general information\n" + "3. Go Back");


        int user_choice = t.nextInt();
        t.nextLine();
        String id = "";
        switch (user_choice) {
            case 1:
                System.out.println("\nServices has been setup successfully!!\n");
                systemSetUp();
                break;

            case 2:
                System.out.println("\nStore has been setup successfully!!\n");
                systemSetUp();
                break;

            case 3:
                displayAdminLanding();
                break;

            default:
                System.out.println("Please enter a valid choice");
                break;
        }
    }

    public void addNewStore(){
        Scanner t= new Scanner(System.in);

        System.out.println("\nPlease enter the following details\n");

        System.out.print("A. Store ID:\t");
        String storeID= t.nextLine();
        System.out.println("");

        System.out.print("A. Store Name:\t");
        String store_name= t.nextLine();
        System.out.println("");

        System.out.print("B. Address:\t");
        String address= t.nextLine();
        System.out.println("");

        System.out.print("C. Manager's First Name:\t");
        String mana_fname= t.nextLine();
        System.out.println("");

        System.out.print("D. Manager's Last Name:\t");
        String mana_lname= t.nextLine();
        System.out.println("");

        System.out.print("E. Manager's Username:\t");
        String mana_username= t.nextLine();
        System.out.println("");

        System.out.print("F. Manager's Password:\t");
        String mana_password= t.nextLine();
        System.out.println("");

        System.out.print("G. Manager's Salary:\t");
        String mana_salary= t.nextLine();
        System.out.println("");

        System.out.print("H. Manager's EmployeeID:\t");
        String mana_employeeID= t.nextLine();
        System.out.println("");

        System.out.print("I. Min wage for mechanic:\t");
        int mech_minwage= t.nextInt();
        System.out.println("");

        System.out.print("J. Max wage for mechanic:\t");
        int mech_maxwage= t.nextInt();
        System.out.println("");

        System.out.print("J. Hourly wage for mechanic:\t");
        int mech_hourlywage= t.nextInt();
        System.out.println("");

        System.out.println("Please select one of the following");
        System.out.println("1. Add Store" + "\n" + "2. Go Back");

        int user_choice= t.nextInt();
        t.nextLine();
        switch (user_choice) {
            case 1:
                try{
                    connection= DBUtility.connectDB(SetupConnection.username, SetupConnection.password);

                    stmt= connection.prepareStatement("INSERT INTO Employee VALUES (?,?,?,?,?,?)");
                    stmt.setString(1,mana_employeeID);
                    stmt.setString(2,mana_username);
                    stmt.setString(3,null);
                    stmt.setString(4, null);
                    stmt.setString(5,null);
                    stmt.setString(6,null);
                    stmt.executeUpdate();

                    stmt.close();

                    stmt= connection.prepareStatement("INSERT INTO Service_Center VALUES (?,?,?,?,?,?,?)");
                    stmt.setString(1, storeID);
                    stmt.setString(2,store_name);
                    stmt.setString(3,address);
                    stmt.setString(4,null);
                    stmt.setInt(5, mech_minwage);
                    stmt.setInt(6,mech_maxwage);
                    stmt.setInt(7, mech_hourlywage);
                    stmt.executeUpdate();

                    stmt.close();

                    stmt= connection.prepareStatement("INSERT INTO WorksAt VALUES (?,?)");
                    stmt.setString(1,mana_employeeID);
                    stmt.setString(2, storeID);
                    stmt.executeUpdate();

                    stmt.close();

                    stmt= connection.prepareStatement("INSERT INTO Manager_Emp VALUES (?,?,?)");
                    stmt.setString(1, mana_employeeID);
                    stmt.setString(2,storeID);
                    stmt.setString(3,mana_username);
                    stmt.executeUpdate();

                    stmt.close();

                    stmt= connection.prepareStatement("INSERT INTO Login VALUES (?,?)");
                    stmt.setString(1, mana_username);
                    stmt.setString(2,mana_password);
                    stmt.executeUpdate();

                    stmt.close();

                    DBUtility.close(connection);

                }catch(SQLException e){
                    System.out.println("Connection Failed! Check output console");
                    e.printStackTrace();
                    DBUtility.close(connection);

                }
                System.out.println("Store Successfully Added");
                displayAdminLanding();
                break;
            case 2: displayAdminLanding();
                break;
            default: System.out.println("Enter a valid choice");
                break;
        }
    }

    public void addNewService(){
        Scanner t= new Scanner(System.in);

        System.out.println("Please enter the following details");

        System.out.print("A. Enter Service Category\n");
        String service_category= t.nextLine();
        if(service_category.toLowerCase().equals("maintenance") || service_category.toLowerCase().equals("repair") || service_category.toLowerCase().equals("management and repair") || service_category.toLowerCase().equals("repair and management")){
        }
        else{
            System.out.println("Error: Enter correct service name!");
            addNewService();
        }

        System.out.print("B. Service Name:\t");
        String service_name= t.nextLine();
        System.out.println("");

        System.out.print("B. Price Tier:\t");
        int service_pricetier= t.nextInt();
        System.out.println("");

        System.out.print("C. Duration:\t");
        int duration= t.nextInt();
        System.out.println("");


        System.out.println("Please select one of the following");
        System.out.println("1. Add Service" + "\n" + "2. Go Back");

        int user_choice= t.nextInt();
        t.nextLine();
        switch (user_choice) {
            case 1:
                int newID=0;
                if(service_category.toLowerCase().equals("maintenance")){
                    try{
                        connection= DBUtility.connectDB(SetupConnection.username, SetupConnection.password);

                        stmt=connection.prepareStatement("select max(ServiceID) from Service");
                        rs = stmt.executeQuery();
                        while (rs.next()) {
                            String newId = rs.getString(1);
                            newID=Integer.parseInt(newId)+1;
                        }
                        rs.close();

                        stmt= connection.prepareStatement("INSERT INTO Service VALUES (?,?,?,?)");
                        stmt.setInt(1,newID);
                        stmt.setString(2,service_name);
                        stmt.setInt(3,service_pricetier);
                        stmt.setInt(4, duration);
                        stmt.executeUpdate();

                        stmt.close();

                        stmt= connection.prepareStatement("INSERT INTO Maintenance VALUES (?,?)");
                        stmt.setString(1,service_name);
                        stmt.setString(2,"m");
                        stmt.executeUpdate();

                        stmt.close();

                        rs.close();


                        DBUtility.close(connection);

                    }catch(SQLException e){
                        System.out.println("Connection Failed! Check output console");
                        e.printStackTrace();
                        DBUtility.close(connection);

                    }
                    System.out.println("Service Successfully Added");
                    displayAdminLanding();
                    break;
                }
                else{
                    try{
                        connection= DBUtility.connectDB(SetupConnection.username, SetupConnection.password);

                        stmt= connection.prepareStatement("INSERT INTO Service VALUES (?,?,?,?)");
                        stmt.setInt(1,newID);
                        stmt.setString(2,service_name);
                        stmt.setInt(3,service_pricetier);
                        stmt.setInt(4, duration);
                        stmt.executeUpdate();

                        stmt.close();
                        rs.close();


                        DBUtility.close(connection);

                    }catch(SQLException e){
                        System.out.println("Connection Failed! Check output console");
                        e.printStackTrace();
                        DBUtility.close(connection);

                    }
                    System.out.println("Service Successfully Added");
                    addNewService();
                    break;
                }



            case 2: displayAdminLanding();
                break;
            default: System.out.println("Enter a valid choice");
                break;
        }
    }

    public void customerInvoicePending(){
        try{
            stmt = connection.prepareStatement("select CustID,"+
                    "centerID,"+
                    "Invoiceid,"+
                    "Date,"+
                    "total_price," +
                    " from Invoice where status = 'unpaid'");
            rs = stmt.executeQuery();
            while(rs.next()) {
                System.out.println("A. Customer ID: "+ rs.getInt(1));
//			  System.out.println("A. Customer Name: "+ rs.getInt(1));
                System.out.println("B. Invoice status: "+ rs.getInt(2));
                System.out.println("C. Date: "+ rs.getInt(3));
                System.out.println("D. Total price: "+ rs.getInt(4));
            }

            rs.close();
            stmt.close();

            stmt = connection.prepareStatement("select Cust_Name,"+
                    " from Customer where Cust_ID = 'unpaid'");
            rs = stmt.executeQuery();
            if(rs.next()) {
                System.out.println("A. Customer ID: "+ rs.getInt(1));
                System.out.println("B. Invoice status: "+ rs.getInt(2));
                System.out.println("C. Date: "+ rs.getInt(3));
                System.out.println("D. Total price: "+ rs.getInt(4));
            }

            rs.close();
            stmt.close();

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

  /*public void serviceHistory(){
    Scanner t= new Scanner(System.in);

    System.out.println("Please enter the following details");
    // run queries to add the following details into database
    System.out.print("A. Customer email address:\t");
    String customer_email= t.next();
    System.out.println("");

     based on the customer_email, display the following:

    service_id, license_plate, service_type, mechanic_name, service_start-date/time,
    service_end-date/time(expected or actual), service_status(Pending/Ongoing/Complete)

    System.out.println("Go Back? Enter 1");
    displayReceptionistLanding(UserId);
  }

  public void scheduleService(){

    Scanner t= new Scanner(System.in);

    System.out.println("Please enter the following details");
    // run queries to add the following details into database
    System.out.print("A. Customer email address:\t");
    String customer_email= t.next();
    System.out.println("");

    System.out.print("B. License Plate:\t");
    String license_plate= t.next();
    System.out.println("");

    System.out.print("C. Current Mileage:\t");
    String current_mileage= t.next();
    System.out.println("");

    System.out.print("D. Mechanic Name(Optional):\t");
    String mechanic_name= t.next();
    System.out.println("");

    //based on the above entered data, provide the users following options
    System.out.println("Please select one of the following");
    System.out.println("1. Schedule Maintenance" + "\n" + " 2. Schedule Repair" + "\n" + "3. Go Back");

    int user_choice= t.nextInt();
    switch (user_choice) {

      case 1: scheduleMaintenance();
break;
      case 2: scheduleRepair();
      break;
      case 3: displayReceptionistLanding(UserId);
      break;
      default: System.out.println("Enter a valid choice");

    }
 }
    public void scheduleMaintenance(){
      Scanner t= new Scanner(System.in);

      System.out.println("Please select one of the following");
      System.out.println("1. Find Service Date" + "\n" + " 2. Go Back");

      int user_choice= t.nextInt();
      switch (user_choice) {
        case 1: findServiceDate();
        break;
        case 2: scheduleService();
        break;
        default: System.out.println("Enter a valid choice");
        break;
      }
    }

    public void findServiceDate(){
      Scanner t = new Scanner(System.in);
      //display the 2 earliest available dates from the db
      System.out.println("Please select one of the following");
      System.out.println("1. Schedule on Date" + "\n" + " 2. Go Back");

      int user_choice= t.nextInt();
      switch (user_choice) {

        case 1:
                System.out.println("Service appointment successfully saved");
                scheduleService();
                break;
        case 2: scheduleMaintenance();
        break;
        default: System.out.println("Enter a valid choice");
        break;
      }
    }

    public void scheduleRepair(){
      Scanner t= new Scanner(System.in);

      System.out.println("Please select one of the following encountered problem");
      System.out.println("1. Engine knock" + "\n" + "2. Car drifts in a particular direction" + "\n" + "3. Battery does not hold charge" + "\n" + "4. Black/unclean exhaust" + "\n"
      + "5. A/C- Heater not working" + "\n" + "6. Headlapms/Tail lamps not working" + "\n" + "7. Check engine light" + "\n" + "8.Go Back");

      int user_choice= t.nextInt();
      switch (user_choice) {
        //based on the repair display the report, 2 identifed service dates, mechanic_name
        case 1:
        case 2:
        case 3:
        case 4:
        case 5:
        case 6:
        case 7:
        case 8: scheduleService();
        default: System.out.println("Enter a valid choice");

        selectRepairDate();
      }
    }

    public void selectRepairDate(){
      Scanner t= new Scanner(System.in);

      System.out.println("Please select one of the following");
      System.out.println("1. Repair on Date" + "\n" + "2. Go Back");

      int user_choice= t.nextInt();
      switch (user_choice) {

        case 1:
                System.out.println("Repair appointment successfully saved");
                scheduleService();

        case 2: scheduleRepair();
        default: System.out.println("Enter a valid choice");
      }
    }

    public void rescheduleService(){
      Scanner t= new Scanner(System.in);

      System.out.println("Ask user to enter customer email-id and display the following: License Plate,"
                        +  "Service ID, Service Date, Service Type(Maintenance/Repair),"
                        +  "Service Details(Service A/B/C orProblem)");

      System.out.print("Enter the email\t");
      String customer_email= t.next();
      System.out.println("");

      System.out.println("Please select one of the following");
      System.out.println("1. Pick a Service" + "\n" + "2. Go Back");

      int user_choice= t.nextInt();
      switch (user_choice) {

        case 1:
                System.out.println("If the user chooses 1,ask him to enter one of the service IDs"
                                  +"to select the service to be rescheduled and then find two"
                                    +"earliest available maintenance/repair dates that are at least"
                                  +"one day after the current service date");
                pickService();

        case 2: displayReceptionistLanding(UserId);
        default: System.out.println("Enter a valid choice");
      }
    }

    public void pickService(){
      Scanner t= new Scanner(System.in);

      System.out.println("SQL: display the 2 identified dates and mechanic name");

      System.out.println("Please select one of the following");
      System.out.println("1. Reschedule Date" + "\n" + "2. Go Back");

      int user_choice= t.nextInt();
      switch (user_choice) {

        case 1:
                System.out.println("Ask the user to pick one fo the dates shown and add to db");
                displayReceptionistLanding(UserId);

        case 2: displayReceptionistLanding(UserId);
        default: System.out.println("Enter a valid choice");
      }

    }

    public void invoices(){
      Scanner t= new Scanner(System.in);

      System.out.println("Ask user to enter customer email-id and display the following: Service ID,"
                        +  "Service Start, Date/Time, Service End, Date/Time, Licence Plate,"
                        +  "Service Type, Mechanic Name, Parts Used in service with cost of each part,"
                        +  "Total labor hours, Labor wages per hour, Total Service Cost");

      System.out.print("Enter the email\t");
      String customer_email= t.next();
      System.out.println("");

      System.out.println("Please select the following");
      System.out.println("1.Go Back");
      int user_choice= t.nextInt();
      if (user_choice == 1) displayReceptionistLanding(UserId);
    }*/

//    public void updateInventory(){
//
//
//
//
//
//
//      System.out.println("Please select the following");
//      System.out.println("1.Go Back");
//      int user_choice= t.nextInt();
//      if (user_choice == 1) displayReceptionistLanding(UserId);
//    }

//    public void recordDeliverables(){
//        Scanner t= new Scanner(System.in);
//
//        System.out.println("Please select one of the following");
//        System.out.println("1. Start Daily Update Task" + "\n" + "2. Go Back");
//
//        int user_choice= t.nextInt();
//        switch (user_choice) {
//
//            case 1:
//                List <String> pending_order_ids = new ArrayList<>();
//                String receiver_center_id = "", part_id="", car_model="";
//                int additional_quantity = 0, current_quantity=0, quantity=0;
//
//                //update the inventory table of the receivers side
//                //check status pending order id and - check center id_receiver of that order from makes table
//                //-update the inventory table- current quantity of the receiver center id
//                //update actual date and status from ordered parts table
//                try{
//                    connection= DBUtility.connectDB(SetupConnection.username, SetupConnection.password);
//
//                    stmt = connection.prepareStatement("SELECT OrderID FROM OrderPart WHERE Status = ?");
//                    stmt.setString(1, "Pending");
//                    rs = stmt.executeQuery();
//
//                    while(rs.next()) {
//                        pending_order_ids.add(rs.getString(1));
//                    }
//
//                    stmt.close();
//                    rs.close();
//
//                    for(String pending_order: pending_order_ids) {
//
//                        System.out.println("Did order number " + pending_order+" arrive?\n1. Yes\n2. No");
//                        int part_arrived = t.nextInt();
//
//                        if(part_arrived == 1) {
//                            stmt = connection.prepareStatement("SELECT CenterId_receiver FROM Makes WHERE OrderID =?");
//                            stmt.setString(1, pending_order);
//                            rs = stmt.executeQuery();
//
//                            while(rs.next()) {
//                                receiver_center_id = rs.getString(1);
//                            }
//                            stmt.close();
//                            rs.close();
//
//                            stmt = connection.prepareStatement("SELECT Part_ID, car_make FROM OrderPart WHERE CenterID = ?");
//                            stmt.setString(1, receiver_center_id);
//                            rs = stmt.executeQuery();
//                            while(rs.next()) {
//                                part_id = rs.getString(1);
//                                car_model = rs.getString(2);
//                            }
//                            stmt.close();
//                            rs.close();
//
//                            stmt = connection.prepareStatement("SELECT quantity FROM OrderPart WHERE CenterID = ? AND Part_ID = ? AND car_make = ?");
//                            stmt.setString(1, receiver_center_id);
//                            stmt.setString(2, part_id);
//                            stmt.setString(3, car_model);
//                            rs = stmt.executeQuery();
//                            while(rs.next()) {
//                                additional_quantity = rs.getInt(1);
//                            }
//                            stmt.close();
//                            rs.close();
//
//                            stmt = connection.prepareStatement("SELECT Current_quantity FROM Inventory WHERE CenterID = ? AND PartID = ? AND Car_model = ?");
//                            stmt.setString(1, receiver_center_id);
//                            stmt.setString(2, part_id);
//                            stmt.setString(3, car_model);
//                            rs = stmt.executeQuery();
//                            while(rs.next()) {
//                                current_quantity = rs.getInt(1);
//                            }
//                            stmt.close();
//                            rs.close();
//
//                            quantity = current_quantity + additional_quantity;
//
//                            stmt = connection.prepareStatement("UPDATE Inventory SET Current_quantity = ? WHERE CenterID = ? AND PartID = ? AND Car_model = ?");
//                            stmt.setInt(1, additional_quantity);
//                            stmt.setString(2, receiver_center_id);
//                            stmt.setString(3, part_id);
//                            stmt.setString(4, car_model);
//
//                            stmt.executeUpdate();
//                            stmt.close();
//
//                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
//                            Date date = new Date();
//                            String current_date = (formatter.format(date));
//
//                            stmt = connection.prepareStatement("UPDATE OrderPart SET Status = 'Complete' , Actual_date = ? WHERE CenterID = ? AND Part_ID = ? AND car_make = ?");
//                            stmt.setString(1, current_date);
//                            stmt.setString(2, receiver_center_id);
//                            stmt.setString(3, part_id);
//                            stmt.setString(4, car_model);
//
//                            stmt.executeUpdate();
//                            stmt.close();
//
//
//                        }
//                    }
//
//                    DBUtility.close(connection);
//
//                }catch(SQLException e){
//                    System.out.println("Connection Failed! Check output console");
//                    e.printStackTrace();
//                    DBUtility.close(connection);
//
//                }
//
//
//            case 2: displayReceptionistLanding(UserId);
//            default: System.out.println("Enter a valid choice");
//        }

//



    }


