import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Customer{
    String userId;
    Receptionist receptionist= new Receptionist();
    Home home= new Home();
    Connection connection;
    PreparedStatement stmt=null;
    ResultSet rs= null;
    int custid;
    String license_plate="";
    int current_mileage=0;

    List<List<String>> cart = new ArrayList<>();

    String centerID;

    int RepairMaintenanceFlag=0;
    String receptionistId="";
    String managerid="";
    private static final Map<Integer, String> repairServices = new HashMap<>();
    static {
        repairServices.put(1, "1. Belt Replacement\n" + "2. Engine Repair\n");
        repairServices.put(2, "1. Exhaust Repair\n" + "2. Muffler Repair\n");
        repairServices.put(3, "1. Alternator Repair\n" + "2. Power Lock Repair\n");
        repairServices.put(4, "1. Axle Repair\n" + "2. Brake Repair\n");
        repairServices.put(5, "1. Tire Balancing\n" + "2. Wheel Alignment\n");
        repairServices.put(6, "1. Compressor Repair\n" + "2. Evaporator Repair\n");
    }
//    <scid, <day, <week, <slot, Set<mechanic>>>>>
    public static Map<String, Map<Integer, Map<Integer, Map<Integer, Set<String>>>>> slots = new HashMap();

    public Map<String, List<Integer>> getAvailableSlots(List<String> mechanics, String centreId, int maxWindow){
        Map<String, List<Integer>> res = new HashMap<>();
        int maxCount = 5;
        int count = 0;
        if(mechanics.isEmpty()){
            return res;
        }
        for(int i=0;i<mechanics.size();i++){
            String mechanic = mechanics.get(i);
            for(int j=1;j<=4;j++){
                for(int k=1;k<=7;k++){
                    for(int l=1;l<=11;l++){
                        if(count >= maxCount){
                            return res;
                        }
                        if(checkIfAvailable(mechanic, centreId, maxWindow, j, k, l)){
                            if(!res.containsKey(mechanic)){
                                res.put(mechanic, new ArrayList<Integer>());
                            }
                            res.get(mechanic).add(j);
                            res.get(mechanic).add(k);
                            res.get(mechanic).add(l);
                            count++;
                        }
                    }
                }
            }
        }
        return res;
    }

    public boolean checkIfAvailable(String mechanic, String centreId, int maxWindow, int week, int day, int slot){
        if(!slots.containsKey(centreId)){
            return true;
        }
        Map<Integer, Map<Integer, Map<Integer, Set<String>>>> mp1 = slots.get(centreId);
        int count  = 0;
        while(count <= maxWindow || week==5){
            if(!mp1.containsKey(week)){
                count = count + 55;
                week++;
            }else if(!mp1.get(week).containsKey(day)){
                count += 11;
                day = day%7 + 1;
                if(day==1) week++;
            }else if(!mp1.get(week).get(day).containsKey(slot)){
                count += 1;
                if(slot==11){
                    slot=1;
                    day = day%7 + 1;
                    if(day==1) week++;
                }else{
                    slot++;
                }
            }else if(mp1.get(week).get(day).get(slot).contains(mechanic)){
                return false;
            }else{
                count++;
                if(slot==11){
                    slot=1;
                    day = day%7 + 1;
                    if(day==1) week++;
                }else{
                    slot++;
                }
            }
        }
        return true;
    }
    public void displayCustomerLanding(String user_id){
        populateMechanicsSlots();
//        System.out.println("enter customer name");
        Scanner t= new Scanner(System.in);
//        user_id = t.nextLine();
        System.out.println("1.Profile and Update Profile" + "\n" +
                "2.View and Schedule Service\n" +
                "3.Invoices\n" +
                "4.Logout");

        int option= t.nextInt();
        switch(option){
            case 1:
                profile(user_id,"","");
                break;
            case 2:
                service(user_id,"");
                break;
            case 3:
                invoices(user_id,"","");
                break;
            case 4:
                home.displayHomepage();
                break;
        }
    }

    public void profile(String CutomerId,String recpId,String mgrId){
        receptionistId=recpId;
        managerid = mgrId;
        System.out.println("Enter one of the following");
        System.out.println("1.View Profile\n" +
                "2. Add Car\n" +
                "3. Delete Car\n" +
                "4. Go Back");

        Scanner t= new Scanner(System.in);
        int option= t.nextInt();


        switch(option){
            case 1:
                viewProfile(CutomerId);
                break;
            case 2:
                registerCar(CutomerId);
                break;
            case 3:
                deleteCar(CutomerId);
                break;
            case 4:
                displayCustomerLanding(CutomerId);
                break;
        }
    }
    public void viewProfile(String CutomerId){
        System.out.println("DB:Display details of the customer ID, name, address, email, phone, list of cars w/ details");

        try{
            connection= DBUtility.connectDB(SetupConnection.username, SetupConnection.password);

            stmt=connection.prepareStatement("select * from Customer where Cust_Name = ?");
            stmt.setString(1, CutomerId);
            rs = stmt.executeQuery();
            int id = -1;
            while (rs.next()) {
                id = rs.getInt(1);
                System.out.println ("Id:"+rs.getInt(1));
                System.out.println ("centre Id:"+rs.getInt(2));
                System.out.println ("Name:"+rs.getString(3));
                System.out.println ("Address:"+rs.getString(4));
                System.out.println ("Phone:"+rs.getInt(5));
                System.out.println("");
            }
            //rs.close();

            String query1= "SELECT GoesTo.CustID, Cars.LicensePlateID, Cars.Car_Type,Cars.Date_Purchase, Cars.Last_Mileage, Cars.Type_Recent_Service, Cars.Date_Recent_service" +
                    " FROM GoesTo" +
                    " JOIN Cars ON GoesTo.LicensePlateID = Cars.LicensePlateID" +
                    " and GoesTo.CustID = ?";
            stmt=connection.prepareStatement(query1);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println("Car details:-");
                System.out.println ("License Plate Id:"+rs.getString(2));
                System.out.println ("Car-Type:"+rs.getString(3));
                System.out.println ("Date Purchased:"+rs.getString(4));
                System.out.println ("Last mileage:"+rs.getInt(5));
                System.out.println ("Recent Service Type:"+rs.getString(6));
                System.out.println ("Recent Service Date"+rs.getString(7));
                System.out.println("");
            }
            DBUtility.close(connection);

        }catch(SQLException e){
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            DBUtility.close(connection);
        }
        System.out.println("1. Go Back");

        Scanner t= new Scanner(System.in);
        int option= t.nextInt();
        switch(option){
            case 1:
                profile(CutomerId,receptionistId,managerid);
                break;
        }
    }

    public void registerCar(String user){
        Scanner t = new Scanner(System.in);

        System.out.println("Please enter the following details");
        // run queries to add the following details into database
        System.out.print("A. VIN number:\t");
        String license_plate= t.next();
        System.out.println("");

        System.out.print("B. Car Manufacturer:\t");
        String make= t.next();
        System.out.println("");

        System.out.print("C. Current Mileage:\t");
        String current_mileage= t.next();
        System.out.println("");

        System.out.print("D. Year:\t");
        String year= t.next();
        System.out.println("");

        System.out.print("E. Model:\t");
        String model= t.next();
        System.out.println("");

        System.out.print("C. Purchase Date:\t");
        String purchase_date= t.next();
        System.out.println("");

        System.out.println("Please select one of the following");
        System.out.println("1. Save Information" + "\n" + "2. Cancel");
        int user_choice= t.nextInt();
        switch (user_choice) {
            case 1:
                CarService cs = new CarService();
                cs.addCar(user, license_plate, purchase_date, make, model, year, current_mileage);
                displayCustomerLanding(user);
                break;
            case 2:
                displayCustomerLanding(user);
                break;
        }
    }

    public void deleteCar(String useremailId){
        userId=useremailId;
        try{
            connection= DBUtility.connectDB(SetupConnection.username, SetupConnection.password);

            stmt = connection.prepareStatement("select custid from Customer where Cust_Name = ?");
            stmt.setString(1, useremailId);
            rs = stmt.executeQuery();
            int id=0;
            while (rs.next()) {
                id = rs.getInt(1);
            }

            String query1= "SELECT GoesTo.CustID, Cars.LicensePlateID, Cars.Car_Type,Cars.Date_Purchase, Cars.Last_Mileage, Cars.Type_Recent_Service, Cars.Date_Recent_service" +
                    " FROM GoesTo" +
                    " JOIN Cars ON GoesTo.LicensePlateID = Cars.LicensePlateID" +
                    " and GoesTo.CustID = ?";
            stmt=connection.prepareStatement(query1);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println("Car details:-");
                System.out.println ("License Plate Id:"+rs.getString(2));
                System.out.println ("Car-Type:"+rs.getString(3));
                System.out.println ("Date Purchased:"+rs.getString(4));
                System.out.println ("Current mileage:"+rs.getInt(5));
                System.out.println("");
            }
            DBUtility.close(connection);

        }catch(SQLException e){
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            DBUtility.close(connection);
        }

        Scanner t = new Scanner(System.in);
        System.out.println("Please select one of the following");
        System.out.println("1. Select Car" + "\n" + "2. Go back");
        int user_choice= t.nextInt();
        switch (user_choice) {
            case 1:
                CarService cs = new CarService();
                System.out.println("enter VIN to delete the car");
                String vin = t.next();
                cs.deleteCar(userId, vin);
                displayCustomerLanding(userId);
                break;
            case 2:
                displayCustomerLanding(userId);
                break;
        }
    }

    public void service(String userEmailId,String recpId){
        userId=userEmailId;
        receptionistId=recpId;
        Scanner t= new Scanner(System.in);
        System.out.println("Enter one of the following");
        System.out.println("1. View Service History\n2. Schedule Service\n3. Go Back");

        int option= t.nextInt();
        switch(option){
            case 1:
                viewServiceHistory(userId);
                break;
            case 2:
                scheduleService(userId);
                break;
            case 3:if(receptionistId.equals("")) {
                displayCustomerLanding(userId);
            } else {
                receptionist.displayReceptionistLanding(receptionistId);
            }
            break;
        }
    }

    public void viewServiceHistory(String userId){
        Scanner t= new Scanner(System.in);
        System.out.println("Enter Vehicle number: ");
        String vin = t.nextLine();

        System.out.println("1. Show Service History Details\n" + "2. Go Back\n");
        int option= t.nextInt();
        if(option==1){
            try{
                connection= DBUtility.connectDB(SetupConnection.username, SetupConnection.password);
                stmt = connection.prepareStatement("select CustID from Customer where Cust_Name = ?");
                stmt.setString(1, userId);
                rs = stmt.executeQuery();
                int custid = 0;
                if(rs.next()){
                    custid = rs.getInt(1);
                }

                stmt = connection.prepareStatement("select " +
                        "CenterID," +
                        "CustID," +
                        "LicensePlateID," +
                        "InvoiceWeek," +
                        "InvoiceDay," +
                        "start_slot," +
                        "mechanic," +
                        "total_price," +
                        "Basic_services_used," +
                        "service_types," +
                        "cost_services" +
                        " from Invoice where LicensePlateID = ? and CustID = ?");
                stmt.setString(1, vin);
                stmt.setInt(2, custid);
                rs = stmt.executeQuery();
                if(rs.next()) {
                    System.out.println("A. Service centre id: "+ rs.getString(1));
                    System.out.println("B. Customer ID: "+ rs.getInt(2));
                    System.out.println("C. VIN: "+ rs.getString(3));
                    System.out.println("D. Start Week: "+ rs.getInt(4));
                    System.out.println("D. Start Day: "+ rs.getInt(5));
                    System.out.println("D. Start Slot: "+ rs.getInt(6));
                    System.out.println("E. Mechanic: "+ rs.getString(7));
                    System.out.println("F. Total Price for all services: "+ rs.getInt(8));
                    System.out.println("G. Services Used: "+ rs.getString(9));
                    System.out.println("H. Cost for Each services: "+ rs.getString(11) + "\n");
                }
                connection.close();
            }catch (Exception ex){
                ex.printStackTrace();
            }
            service(userId, "");
        }else{
            service(userId, "");
        }
    }

    public void scheduleService(String userId) {
        System.out.println("Please enter the following details");
        // run queries to add the following details into database
        Scanner t= new Scanner(System.in);
        System.out.print("A. VIN Number:\t");
        license_plate= t.nextLine();
        System.out.println("");

        System.out.print("B. Current Mileage:\t");
        current_mileage= t.nextInt();
        System.out.println("");
        cart = new ArrayList<>();

        scheduleService(userId, license_plate, current_mileage);
    }

    public void scheduleService(String userId, String vin, int current_mileage){
        //based on the above entered data, provide the users following options
        System.out.println("Please select one of the following");
        System.out.println("1. Add Schedule Maintenance" + "\n"
                + "2. Add Schedule Repair" + "\n"
                + "3. View Cart and select schedule time" + "\n"
                + "4. Go Back");
        Scanner t= new Scanner(System.in);
        int user_choice= t.nextInt();
        RepairMaintenanceFlag=user_choice;

        switch (user_choice) {
            case 1: addMaintenanceToCart(license_plate, current_mileage);
                break;
            case 2: addRepairToCart();
                break;
            case 3: viewCartAndScheduleTime(userId, license_plate);
                break;
            case 4: if (receptionistId.equals("")) {
                displayCustomerLanding(userId);
            } else {
                receptionist.displayReceptionistLanding(receptionistId);
            }
                break;
        }
    }

    public void viewCartAndScheduleTime(String userId, String license_plate){
        System.out.println("Below are the services that are present in the cart");
        for(int i =0;i<cart.size();i++){
            System.out.println(cart.get(i));
        }
        Scanner t= new Scanner(System.in);
        System.out.println("Please select one of the following");
        System.out.println("1. Proceed with the scheduling" + "\n" + "2. Go Back");
        int user_choice= t.nextInt();
        if(user_choice==1){
            //show all available time slots
            showAvailableTimeSlots(userId, license_plate);
            //select a time slot
            //make an appointment
            //make an invoice
        }else{
            scheduleService(userId);
        }
    }

    public void showAvailableTimeSlots(String userId, String license_plate){
        //get the centre id
        try{
            connection= DBUtility.connectDB(SetupConnection.username, SetupConnection.password);
            stmt = connection.prepareStatement("select CenterID from Customer where Cust_Name = ?");
            stmt.setString(1, userId);
            rs = stmt.executeQuery();
            String centreId = "";
            if(rs.next()){
                centreId = rs.getString(1);
                List<String> mechanics = getAllMechanics(centreId);
                //get the max window
                String[] str = new String[cart.size()];
                for(int i=0;i<cart.size();i++){
                    str[i] = cart.get(i).get(0);
                }
                int maxWindow = getMaxWindow(str);
                Map<String, List<Integer>> availableTimeSlots = getAvailableSlots(mechanics, centreId, maxWindow);
                if(availableTimeSlots.isEmpty()){
                    System.out.println("No slots are available");
                    service(userId, "");
                }
                System.out.println("Showing available slots below: ");
                System.out.println("Select one of the options: ");
                Map<Integer, List<Integer>> mapping = new HashMap<>();
                Map<Integer, String> mapping1 = new HashMap<>();
                int count = 1;
                for(String mechanic : availableTimeSlots.keySet()){
                    for(int j=0;j<availableTimeSlots.get(mechanic).size();j=j+3){
                        System.out.println(String.valueOf(count)+". "+
                                "mechanic: " + mechanic +
                                "  Week:"+ availableTimeSlots.get(mechanic).get(j) +
                                "  Day:"+ availableTimeSlots.get(mechanic).get(j+1) +
                                "  Slot:"+ availableTimeSlots.get(mechanic).get(j+2)
                                );
                        mapping.put(count, new ArrayList<>());
                        mapping1.put(count, mechanic);
                        mapping.get(count).add(availableTimeSlots.get(mechanic).get(j));
                        mapping.get(count).add(availableTimeSlots.get(mechanic).get(j+1));
                        mapping.get(count).add(availableTimeSlots.get(mechanic).get(j+2));
                        count++;
                    }
                }
                System.out.println(String.valueOf(count) + ". " + "Go Back");
                Scanner sc = new Scanner(System.in);
                int choice = sc.nextInt();
                if(choice==count){
                    viewCartAndScheduleTime(userId, license_plate);
                }else{
                    scheduleSlot(userId, license_plate, mapping1.get(choice), mapping.get(choice).get(0),
                            mapping.get(choice).get(1), mapping.get(choice).get(2), maxWindow);
                    displayCustomerLanding(userId);
                }
            }
            connection.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void scheduleSlot(String userid, String license_plate, String mechanic_name, int week, int day, int slot, int maxWindow){

        try{
            //get the service centre id
            stmt = connection.prepareStatement("select CenterID from Customer where Cust_Name = ?");
            stmt.setString(1, userid);
            rs = stmt.executeQuery();
            String centreId = "";
            if (rs.next()) {
                centreId = rs.getString(1);
            }

            //update cars table with recent maintenance type
            //get the total price
            int totalPrice = getTotalPrice(centreId, userid, license_plate);

            //update hashmap with the mechanic
            updateTheSlotsWithMechanic(centreId, mechanic_name, week, day, slot, maxWindow);

            //add to the invoice table
            addInvoiceData(centreId, userid, license_plate, mechanic_name, week, day, slot, maxWindow, totalPrice);

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void addInvoiceData(String centreId, String userId,
                               String license_plate, String mechanic_name, int week, int day, int slot,
                               int maxWindow, int totalPrice){
        try{
            int invoiceId = 0;
            int customerId = 0;
            String mechanicId = "";
            String date = String.valueOf(week) + "-" + String.valueOf(day) + "-" + String.valueOf(slot);
            String basicServices = "";
            String priceLst = "";
            String serviceTypes = "";
            stmt =connection.prepareStatement("select max(Invoiceid) from Invoice");
            ResultSet rs1 = stmt.executeQuery();
            if (rs1.next()) {
                invoiceId=rs1.getInt(1)+1;
            }
            stmt =connection.prepareStatement("select CustID from Customer where Cust_Name = ?");
            stmt.setString(1, userId);
            rs1 = stmt.executeQuery();
            if (rs1.next()) {
                customerId = rs1.getInt(1);
            }

            stmt = connection.prepareStatement("select EmpID from Employee where Emp_Name = ?");
            stmt.setString(1, mechanic_name);
            rs1 = stmt.executeQuery();
            if (rs1.next()) {
                mechanicId = rs1.getString(1);
            }
            basicServices = getBasicServices();
            serviceTypes = getServiceTypes();
            priceLst = getCostOfServices(centreId, userId, license_plate);

            stmt = connection.prepareStatement("Insert into Invoice values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            stmt.setInt(1, invoiceId);
            stmt.setString(2, centreId);
            stmt.setInt(3, customerId);
            stmt.setString(4, license_plate);
            stmt.setString(5, date);
            stmt.setInt(6, week);
            stmt.setInt(7, day);
            stmt.setInt(8, slot);
            stmt.setInt(9, 0);
            stmt.setString(10, mechanic_name);
            stmt.setString(11, mechanicId);
            stmt.setInt(12, totalPrice);
            stmt.setString(13, "unpaid");
            stmt.setString(14, basicServices);
            stmt.setString(15, serviceTypes);
            stmt.setString(16, priceLst);
            stmt.executeUpdate();

            System.out.println("New Invoice is created with id: "+ String.valueOf(invoiceId));
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    String getBasicServices(){
        String res = "";
        for(int i=0;i<cart.size();i++){
            String service = cart.get(i).get(0);
            if(i==0){
                res = service;
            }else {
                res = res + "," + service;
            }
        }
        return res;
    }

    String getServiceTypes(){
        String res = "";
        for(int i=0;i<cart.size();i++){
            String service = cart.get(i).get(0);
            String type = "Repair";
            if(service.equals("A") || service.equals("B") || service.equals("C")){
                type = "Maintenance";
            }
            if(i==0){
                res = type;
            }else {
                res = res + "," + type;
            }
        }
        return res;
    }

    String getCostOfServices(String centerID, String userid, String license_plate){
        List<Integer> lst = getTotalPriceList(centerID, userid, license_plate);
        String res = "";
        for(int i=0;i<lst.size();i++){
            String price = String.valueOf(lst.get(i));
            if(i==0){
                res = price;
            }else {
                res = res + "," + price;
            }
        }
        return res;
    }

    public void updateTheSlotsWithMechanic(String centerID, String mechanic_name, int week, int day, int slot, int maxWindow){
        int count = 1;
        while(count<=maxWindow){
            Map<Integer, Map<Integer, Map<Integer, Set<String>>>> mp1 = slots.getOrDefault(centerID, new HashMap<>());
            Map<Integer, Map<Integer, Set<String>>> mp2 = mp1.getOrDefault(week, new HashMap<>());
            Map<Integer, Set<String>> mp3 = mp2.getOrDefault(day, new HashMap<>());
            Set<String> st = mp3.getOrDefault(slot, new HashSet<>());
            st.add(mechanic_name);
            mp3.put(slot, st);
            mp2.put(day, mp3);
            mp1.put(week, mp2);
            slots.put(centerID, mp1);
            count++;
            if(slot==11){
                slot = 1;
                day = day%7 + 1;
                if(day==1) week++;
            }
            else slot++;
        }
    }

    public int getTotalPrice(String centerID, String userid, String license_plate){
        int total = 0;
        List<Integer> lst = getTotalPriceList(centerID, userid, license_plate);
        for(int i=0;i<lst.size();i++){
            total += lst.get(i);
        }
        return total;
    }

    public List<Integer> getTotalPriceList(String centerID, String userid, String license_plate){
        List<Integer> res = new ArrayList<>();
        try {
            //get the car manufacturer
            String manufacturer = "";
            stmt = connection.prepareStatement("select car_type from Cars where licenseplateid = ?");
            stmt.setString(1, license_plate);
            rs = stmt.executeQuery();
            if (rs.next()) {
                manufacturer = rs.getString(1).split(",")[0];
            }

            // get the price tier and update the recent maintenance service type in cars
            for(int i=0;i<cart.size();i++) {
                String serviceName = cart.get(i).get(0);
                setTypeRecentService(license_plate, serviceName);
                stmt = connection.prepareStatement("select PriceTier from Service where ServiceName = ?");
                stmt.setString(1, serviceName);
                rs = stmt.executeQuery();
                if(rs.next()){
                    int priceTier = rs.getInt(1);
                    stmt = connection.prepareStatement("select Price from Price where StoreId = ? and Manufacturer = ? and PriceTier = ?");
                    stmt.setString(1, centerID);
                    stmt.setString(2, manufacturer);
                    stmt.setInt(3, priceTier);
                    ResultSet rs1 = stmt.executeQuery();
                    if(rs1.next()){
                        int price = rs1.getInt(1);
                        res.add(price);
                    }
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return res;
    }

    public int getMaxWindow(String[] str){
        int totalTime = 0;
        for(int i=0;i<str.length;i++){
            String serviceName = str[i];
            try{
                stmt = connection.prepareStatement("select Hours from Service where ServiceName = ?");
                stmt.setString(1, serviceName);
                ResultSet rs1 = stmt.executeQuery();
                if(rs1.next()){
                    int time = rs1.getInt(1);
                    totalTime += time;
                }
                rs1.close();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return totalTime;
    }

    public List<String> getAllMechanics(String centerID){
        List<String> mechanics = new ArrayList<>();
        try{
            stmt = connection.prepareStatement("select Mechanic_Emp.EmpID from WorksAt INNER JOIN Mechanic_Emp on WorksAt.EmpID = Mechanic_Emp.EmpID" +
                    " where CenterID = ?");
            stmt.setString(1, centerID);
            rs = stmt.executeQuery();
            while (rs.next()){
                String EmpID = rs.getString(1);
                stmt = connection.prepareStatement("select Emp_Name from Employee where EmpID = ?");
                stmt.setString(1, EmpID);
                ResultSet rs1 = stmt.executeQuery();
                if(rs1.next()){
                    String mechanic = rs1.getString(1);
                    mechanics.add(mechanic);
                }
                rs1.close();
            }
            rs.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return mechanics;
    }

    public void addMaintenanceToCart(String license_plate, int current_mileage){
        try{
            connection= DBUtility.connectDB(SetupConnection.username, SetupConnection.password);
            String serviceType = getServiceType(license_plate, current_mileage);
            System.out.println("service type " + serviceType + " is available");
            System.out.println("1. Accept and add to the cart" + "\n"
                    + " 2. Decline and Go Back");
            System.out.println("");
            Scanner t= new Scanner(System.in);
            int user_choice= t.nextInt();
            switch (user_choice) {
                case 1:
                    List<String> maintenance = new ArrayList<>();
                    maintenance.add(serviceType);
                    cart.add(maintenance);
                    scheduleService(userId, license_plate, current_mileage);
                    break;
                case 2: scheduleService(userId, license_plate, current_mileage);
                    break;
                default: System.out.println("Enter a valid choice");
                    break;
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void addRepairToCart(){
        Scanner t= new Scanner(System.in);
        System.out.println("Please select one of the following encountered problem");

        System.out.println("1. Engine Services" + "\n"
                + "2. Exhaust Services" + "\n"
                + "3. Electrical Services" + "\n"
                + "4. Transmission Services" + "\n"
                + "5. Tire Services" + "\n"
                + "6. Heating and AC Services" + "\n"
                + "7. Go Back" + "\n");

        int user_choice= t.nextInt();
        List<String> lst = new ArrayList<>();
        int user_choice_2 = 1;
        switch (user_choice) {
            //based on the repair display the report, 2 identifed service dates, mechanic_name
            case 1:
                System.out.println("Please select one of the services");
                System.out.println(repairServices.get(user_choice));
                user_choice_2= t.nextInt();
                if(user_choice_2==1){lst.add("Belt Replacement");}else{lst.add("Engine Repair");}
                break;
            case 2:
                System.out.println("Please select one of the services");
                System.out.println(repairServices.get(user_choice));
                user_choice_2= t.nextInt();
                if(user_choice_2==1){lst.add("Exhaust Repair");}else{lst.add("Muffler Repair");}
                break;
            case 3:
                System.out.println("Please select one of the services");
                System.out.println(repairServices.get(user_choice));
                user_choice_2= t.nextInt();
                if(user_choice_2==1){lst.add("Alternator Repair");}else{lst.add("Power Lock Repair");}
                break;
            case 4:
                System.out.println("Please select one of the services");
                System.out.println(repairServices.get(user_choice));
                user_choice_2= t.nextInt();
                if(user_choice_2==1){lst.add("Axle Repair");}else{lst.add("Brake Repair");}
                break;
            case 5:
                System.out.println("Please select one of the services");
                System.out.println(repairServices.get(user_choice));
                user_choice_2= t.nextInt();
                if(user_choice_2==1){lst.add("Tire Balancing");}else{lst.add("Wheel Alignment");}
                break;
            case 6:
                System.out.println("Please select one of the services");
                System.out.println(repairServices.get(user_choice));
                user_choice_2= t.nextInt();
                if(user_choice_2==1){lst.add("Compressor Repair");}else{lst.add("Evaporator Repair");}
                break;
            case 7:
                scheduleService(userId, license_plate, current_mileage);
                break;
        }
        System.out.println(lst.get(0) + " is added to the cart");
        cart.add(lst);
        scheduleService(userId, license_plate, current_mileage);
    }


    private void setTypeRecentService(String license_plate, String serviceName) {
        if(serviceName.equals("A") || serviceName.equals("B") || serviceName.equals("C")){
            try {
                stmt = connection.prepareStatement("Update Cars set type_recent_service = ? where licenseplateid = ?");
                stmt.setString(1, serviceName);
                stmt.setString(2, license_plate);
                stmt.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private String getServiceType(String license_plate, int current_mileage) throws SQLException {
        String serviceType = "";
        stmt = connection.prepareStatement("select type_recent_service,car_type,last_mileage from Cars where licenseplateid = ?");
        stmt.setString(1, license_plate);
        rs = stmt.executeQuery();
        if(rs.next()) {
            if (rs.getString(1)==null) {
                serviceType="A";
            } else if (rs.getString(1).trim().equalsIgnoreCase("A")){
                serviceType="B";
            } else if (rs.getString(1).trim().equalsIgnoreCase("B")){
                serviceType="C";
            } else {
                serviceType="A";
            }
        }
        return serviceType;
    }

    public void invoices(String userId, String receptionistId, String managerid){
        Scanner sc = new Scanner(System.in);
        //show all the invoices for that customer with status and id
        try{
            int custid = 0;
            String centreid = "";
            connection= DBUtility.connectDB(SetupConnection.username, SetupConnection.password);
            stmt =connection.prepareStatement("select CustID, CenterID from Customer where Cust_Name = ?");
            stmt.setString(1, userId);
            ResultSet rs1 = stmt.executeQuery();
            if(rs1.next()){
                custid = rs1.getInt(1);
                centreid = rs1.getString(2);
            }

            stmt = connection.prepareStatement("select Invoiceid," +
                    "status" +
                    " from Invoice where CustID = ? and CenterID = ?");
            stmt.setInt(1, custid);
            stmt.setString(2, centreid);
            rs1 = stmt.executeQuery();
            System.out.println("");
            while (rs1.next()){
                System.out.println("Invoice id: "+ String.valueOf(rs1.getInt(1))
                + " Status: " + rs1.getString(2));
            }
            System.out.println("");
            System.out.println("Please select one of the following: ");
            System.out.println("1. View Invoice Details\n" + "2. Pay Invoice\n" + "3. Go back");
            int u = sc.nextInt();
            if(u==1){
                viewInvoice(userId);
            }else if(u==2){
                payInvoice(userId);
            }else{
                displayCustomerLanding(userId);
            }

            connection.close();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void payInvoice(String userId){
        System.out.println("Please enter the invoice id to pay");
        Scanner sc = new Scanner(System.in);
        int invoiceid = sc.nextInt();
        System.out.println("Please select one of the following: ");
        System.out.println("1. Pay Invoice\n" + " 2. Go back");
        int u = sc.nextInt();
        if(u==1){
            payInvoice(userId, invoiceid);
            invoices(userId, "", "");
        }else{
            invoices(userId, "", "");
        }
    }

    public void payInvoice(String userId, int invoiceid){
        try{
            stmt =connection.prepareStatement("select status from Invoice where Invoiceid = ?");
            stmt.setInt(1, invoiceid);
            ResultSet rs1 = stmt.executeQuery();
            if(rs1.next()){
                String status = rs1.getString(1);
                if(status.equals("paid")){
                    System.out.println("Already paid the invoice");
                    Thread.sleep(1000);
                }else{
                    stmt = connection.prepareStatement("update Invoice set status=? where Invoiceid=?");
                    stmt.setString(1, "paid");
                    stmt.setInt(2,invoiceid);
                    stmt.executeUpdate();
                    System.out.println("Successfully paid the invoiceid "+ String.valueOf(invoiceid));
                    Thread.sleep(1000);
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void viewInvoice(String userId){
        System.out.println("Enter invoice id: ");
        Scanner sc = new Scanner(System.in);
        int id= sc.nextInt();
        System.out.println("Please select one of the following: ");
        System.out.println("1. View Invoice\n" + "2. Go back");
        int u = sc.nextInt();
        if(u==1){
            printInvoiceDetails(id);
            invoices(userId, "", "");
        }else{
            invoices(userId, "", "");
        }
    }
    public void printInvoiceDetails(int invoiceId){
        try{
            stmt = connection.prepareStatement("select Invoiceid," +
                    "CenterID," +
                    "CustID," +
                    "LicensePlateID," +
                    "InvoiceDate," +
                    "mechanic," +
                    "total_price," +
                    "status," +
                    "Basic_services_used," +
                    "service_types," +
                    "cost_services" +
                    " from Invoice where Invoiceid = ?");
            stmt.setInt(1, invoiceId);
            rs = stmt.executeQuery();
            if(rs.next()) {
                System.out.println("A. Invoice ID: "+ rs.getInt(1));
                System.out.println("B. Customer ID: "+ rs.getInt(3));
                System.out.println("C. VIN: "+ rs.getString(4));
                System.out.println("D. Date: "+ rs.getString(5));
                System.out.println("E. Service(s): "+ rs.getString(9));
                System.out.println("F. Service types(s): "+ rs.getString(10));
                System.out.println("G. Invoice status: "+ rs.getString(8));
                System.out.println("H. Mechanic: "+ rs.getString(6));
                System.out.println("I. Cost for each service "+ rs.getString(11));
                System.out.println("J Total Cost: "+ rs.getInt(7)+ "\n");
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void populateMechanicsSlots(){
        try{
            connection= DBUtility.connectDB(SetupConnection.username, SetupConnection.password);
            stmt =connection.prepareStatement("select CenterID from Service_Center");
            ResultSet rs1 = stmt.executeQuery();
            while(rs1.next()){
                String centreid = "";
                centreid = rs1.getString(1);
                //get all the invoices with the centre id.
                stmt = connection.prepareStatement("select Invoiceid," +
                        "CenterID," +
                        "CustID," +
                        "mechanic," +
                        "InvoiceWeek," +
                        "InvoiceDay," +
                        "start_slot," +
                        "total_price," +
                        "status," +
                        "Basic_services_used," +
                        "service_types" +
                        " from Invoice where CenterID = ?");
                stmt.setString(1, centreid);
                ResultSet rs2 = stmt.executeQuery();
                while (rs2.next()){
                    String mechanic = rs2.getString(4);
                    int week = rs2.getInt(5);
                    int day = rs2.getInt(6);
                    int slot = rs2.getInt(7);
                    String[] str = rs2.getString(10).split(",");
                    int maxWindow = getMaxWindow(str);

                    //for each invoice, get the services of a mechanic and fill the slots
                    if(mechanic!=null){
                        updateTheSlotsWithMechanic(centreid, mechanic, week, day, slot, maxWindow);
                    }

                }
            }

            connection.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

}
