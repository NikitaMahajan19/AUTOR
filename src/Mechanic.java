import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Mechanic {
    String UserId;
    Connection connection;
    PreparedStatement stmt;
    Home home = new Home();
    ResultSet rs;
    Scanner t = new Scanner(System.in);

    public void displayMechanicLanding(String user_id, Connection con) {
        UserId = user_id;
        connection = con;
        Scanner t = new Scanner(System.in);
        Home home = new Home();
        Employee employee = new Employee();
        Customer customer = new Customer();

        System.out.println("Please select one of the following\n");
        System.out.println("1. View Schedule\n" + "2. Request TimeOff" + "\n" + "3. Request Swap" + "\n" + "4. Accept/Reject Swap" + "\n" + "5. Logout");

        int user_choice = t.nextInt();
        t.nextLine();
        String id = "";
        switch (user_choice) {
            case 1:
                viewSchedule();
                break;
            case 2:
                RequestTimeOff();
                break;
            case 3:
                RequestSwap();
                break;
            case 4:
                AcceptRejectSwap();
                break;
            case 5:
                home.displayHomepage();
        }
    }

    public void viewSchedule(){
        try{

            stmt=connection.prepareStatement("SELECT CustId, LicensePlateID, Basic_services_used, InvoiceWeek, InvoiceDay, start_slot, end_slot FROM Invoice WHERE mechanicId=?");
            stmt.setString(1, UserId);
            rs = stmt.executeQuery();
            String s = "";
            int CustId;
            String LicensePlateID;
            String Basic_services_used;
            int InvoiceWeek;
            int InvoiceDay;
            int start_slot;
            int end_slot;

            int count = 1;

            System.out.println("Here are your schedule details: ");

            while (rs.next()) {
                CustId = rs.getInt(1);
                LicensePlateID = rs.getString(2);
                Basic_services_used = rs.getString(3);
                InvoiceWeek = rs.getInt(4);
                InvoiceDay = rs.getInt(5);
                start_slot = rs.getInt(6);
                end_slot = rs.getInt(7);

                System.out.println("\nAppointment number: " + count);
                System.out.println("Customer ID: " + CustId);
                System.out.println("Services to Provide: " + Basic_services_used);
                System.out.println("Week of the Appointment: " + InvoiceWeek);
                System.out.println("Day of the Appointment: " + InvoiceDay);
                System.out.println("Starting slot of the Appointment: " + start_slot);
                System.out.println("Ending slot of the Appointment: " + end_slot);

                count+=1;
            }

            rs.close();

            if (count==1){
                System.out.println("You have no scheduled appointments as of now");
            }

            System.out.println("\nPress 1 to go back");
            int user_choice = t.nextInt();

            if(user_choice==1){
                displayMechanicLanding(UserId, connection);
            }

        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            DBUtility.close(connection);

        }
    }

    public void RequestTimeOff(){

        try{

            stmt=connection.prepareStatement("SELECT InvoiceWeek, InvoiceDay, start_slot, end_slot FROM Invoice WHERE mechanicId=?");
            stmt.setString(1, UserId);
            rs = stmt.executeQuery();
            String s = "";
            int InvoiceWeek=0;
            int InvoiceDay=0;
            int start_slot=0;
            int end_slot=0;

            System.out.println("Please Enter the Week that you want off");
            int off_week = t.nextInt();
            System.out.println("Please Enter the Day that you want off");
            int off_day = t.nextInt();
            System.out.println("Please Enter the Starting slot that you want off");
            int off_start_slot = t.nextInt();
            System.out.println("Please Enter the Ending slot that you want off");
            int off_end_slot = t.nextInt();
            int flag = 0;
            while (rs.next()) {
                InvoiceWeek = rs.getInt(1);
                InvoiceDay = rs.getInt(2);
                start_slot = rs.getInt(3);
                end_slot = rs.getInt(4);

                if((off_week==InvoiceWeek)&&(off_day==InvoiceDay)&&(((off_start_slot>=start_slot)&&(off_start_slot<end_slot))||((off_end_slot>=start_slot)&&(off_end_slot<end_slot)))) {
                    System.out.println("Time Off cannot be approved as you already have an appointment for this time");
                    flag = 1;
                    break;
                }
            }

            if(flag==0){
                System.out.println("Your time off has been approved");
            }

            rs.close();

            System.out.println("\nPress 1 to go back");
            int user_choice = t.nextInt();

            if(user_choice==1){
                displayMechanicLanding(UserId, connection);
            }

        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            DBUtility.close(connection);

        }
    }

    public void RequestSwap(){

        try{

            stmt=connection.prepareStatement("SELECT InvoiceWeek, InvoiceDay, start_slot, end_slot FROM Invoice WHERE mechanicId=?");
            stmt.setString(1, UserId);
            rs = stmt.executeQuery();
            String RequestId = "";
            String RequestedMechanicId = "";
            int InvoiceWeek=0;
            int InvoiceDay=0;
            int start_slot=0;
            int end_slot=0;

            System.out.println("Please Enter the Week that you want to swap");
            int SelfSwapWeek = t.nextInt();
            System.out.println("Please Enter the Day that you want to swap");
            int SelfSwapDay = t.nextInt();
            System.out.println("Please Enter the Starting slot that you want to swap");
            int SelfSwapStartSlot = t.nextInt();
            System.out.println("Please Enter the Ending slot that you want to swap");
            int SelfSwapEndSlot = t.nextInt();
            t.nextLine();

            System.out.println("Please Enter the ID of the Mechanic that you want to swap with");
            RequestedMechanicId = t.nextLine();

            System.out.println("Please Enter the Week that you want to swap with");
            int RequestedSwapWeek = t.nextInt();
            System.out.println("Please Enter the Day that you want to swap with");
            int RequestedSwapDay = t.nextInt();
            System.out.println("Please Enter the Starting slot that you want to swap with");
            int RequestedSwapStartSlot = t.nextInt();
            System.out.println("Please Enter the Ending slot that you want to swap with");
            int RequestedSwapEndSlot = t.nextInt();

            int flag_self = 0;
            int flag_requested = 0;

            while (rs.next()) {
                InvoiceWeek = rs.getInt(1);
                InvoiceDay = rs.getInt(2);
                start_slot = rs.getInt(3);
                end_slot = rs.getInt(4);

                if((SelfSwapWeek==InvoiceWeek)&&(SelfSwapDay==InvoiceDay)&&(SelfSwapStartSlot==start_slot)&&(SelfSwapEndSlot==end_slot)) {
                    flag_self = 1;
                    break;
                }
            }

            rs.close();

            if(flag_self==0) {
                System.out.println("You don't have a scheduled shift to swap at your specified time");
            }
            else{
                stmt=connection.prepareStatement("SELECT InvoiceWeek, InvoiceDay, start_slot, end_slot FROM Invoice WHERE mechanicId=?");
                stmt.setString(1, RequestedMechanicId);
                rs = stmt.executeQuery();
                InvoiceWeek=0;
                InvoiceDay=0;
                start_slot=0;
                end_slot=0;

                while (rs.next()) {
                    InvoiceWeek = rs.getInt(1);
                    InvoiceDay = rs.getInt(2);
                    start_slot = rs.getInt(3);
                    end_slot = rs.getInt(4);

                    if((RequestedSwapWeek==InvoiceWeek)&&(RequestedSwapDay==InvoiceDay)&&(RequestedSwapStartSlot==start_slot)&&(RequestedSwapEndSlot==end_slot)) {
                        flag_requested = 1;
                        break;
                    }
                }
            }

            if(flag_requested==0) {
                System.out.println("The mechanic that you specified does not have a scheduled shift to swap at your specified time");
            }
            else{
                t.nextLine();
                System.out.println("Your request meets the requirements. Please give this request an ID");
                RequestId = t.nextLine();

                stmt= connection.prepareStatement("INSERT INTO Swaps (" +
                        "    RequestId," +
                        "    MechanicId," +
                        "    SelfSwapWeek," +
                        "    SelfSwapDay," +
                        "    SelfSwapStartSlot," +
                        "    SelfSwapEndSlot," +
                        "    RequestedMechanicId," +
                        "    RequestedSwapWeek," +
                        "    RequestedSwapDay," +
                        "    RequestedSwapStartSlot," +
                        "    RequestedSwapEndSlot) " +
                        "VALUES (?,?,?,?,?,?,?,?,?,?,?)");

                stmt.setString(1,RequestId);
                stmt.setString(2,UserId);
                stmt.setInt(3,SelfSwapWeek);
                stmt.setInt(4, SelfSwapDay);
                stmt.setInt(5,SelfSwapStartSlot);
                stmt.setInt(6,SelfSwapEndSlot);
                stmt.setString(7,RequestedMechanicId);
                stmt.setInt(8,RequestedSwapWeek);
                stmt.setInt(9,RequestedSwapDay);
                stmt.setInt(10,RequestedSwapStartSlot);
                stmt.setInt(11,RequestedSwapEndSlot);

                stmt.executeUpdate();

                stmt.close();

                System.out.println("Your Swap request has been added");
            }

            System.out.println("\nPress 1 to go back");
            int user_choice = t.nextInt();

            if(user_choice==1){
                displayMechanicLanding(UserId, connection);
            }

        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            DBUtility.close(connection);

        }
    }

    public void AcceptRejectSwap(){
        try{
            stmt=connection.prepareStatement("SELECT" +
                    "    RequestId," +
                    "    MechanicId," +
                    "    SelfSwapWeek," +
                    "    SelfSwapDay," +
                    "    SelfSwapStartSlot," +
                    "    SelfSwapEndSlot," +
                    "    RequestedMechanicId," +
                    "    RequestedSwapWeek," +
                    "    RequestedSwapDay," +
                    "    RequestedSwapStartSlot," +
                    "    RequestedSwapEndSlot " +
                    "FROM Swaps WHERE RequestedMechanicId=?");
            stmt.setString(1, UserId);
            rs = stmt.executeQuery();

            String RequestId = "";
            String MechanicId = "";
            String RequestedMechanicId = "";
            int SelfSwapWeek =0;
            int SelfSwapDay=0;
            int SelfSwapStartSlot=0;
            int SelfSwapEndSlot=0;
            int RequestedSwapWeek =0;
            int RequestedSwapDay=0;
            int RequestedSwapStartSlot=0;
            int RequestedSwapEndSlot=0;
            int count = 1;

            System.out.println("Here are your pending requests:");

            while (rs.next()) {
                RequestId = rs.getString(1);
                MechanicId = rs.getString(2);
                SelfSwapWeek = rs.getInt(3);
                SelfSwapDay = rs.getInt(4);
                SelfSwapStartSlot = rs.getInt(5);
                SelfSwapEndSlot = rs.getInt(6);
                RequestedMechanicId = rs.getString(7);
                RequestedSwapWeek = rs.getInt(8);
                RequestedSwapDay = rs.getInt(9);
                RequestedSwapStartSlot = rs.getInt(10);
                RequestedSwapEndSlot = rs.getInt(11);
                String MechanicName = "";

                stmt=connection.prepareStatement("SELECT Emp_Name from Employee WHERE EmpId=?");
                stmt.setString(1, MechanicId);
                ResultSet rs2 = stmt.executeQuery();

                while(rs2.next()){
                    MechanicName = rs2.getString(1);
                }
                rs2.close();

                System.out.println("\n\nRequest ID: " + RequestId);
                System.out.println("Requesting Mechanic's Name: " + MechanicName);
                System.out.println("Your Requested Week to swap: " + RequestedSwapWeek);
                System.out.println("Your Requested Day to swap: " + RequestedSwapDay);
                System.out.println("Your Requested Start Slot to swap: " + RequestedSwapStartSlot);
                System.out.println("Your Requested End Slot to swap: " + RequestedSwapEndSlot);
                System.out.println("\nOffered Week to swap with: " + SelfSwapWeek);
                System.out.println("Offered Day to swap with: " + SelfSwapDay);
                System.out.println("Offered Start Slot to swap with: " + SelfSwapStartSlot);
                System.out.println("Offered End Slot to swap with: " + SelfSwapEndSlot);


                count+=1;
            }

            if(count==1){
                System.out.println("You do not have any pending requests at this moment.");
                System.out.println("1. Go Back");
                int choice = t.nextInt();
                if(choice==1){
                    displayMechanicLanding(UserId, connection);
                }
            }
            else{
                System.out.println("\n1. Manage Requests");
                System.out.println("2. Go Back");
                if(t.nextInt()==1){
                    System.out.println("Enter the Request Id to accept");
                    t.nextLine();
                    String AcceptId = t.nextLine();
                    stmt=connection.prepareStatement("SELECT" +
                            "    RequestId," +
                            "    MechanicId," +
                            "    SelfSwapWeek," +
                            "    SelfSwapDay," +
                            "    SelfSwapStartSlot," +
                            "    SelfSwapEndSlot," +
                            "    RequestedMechanicId," +
                            "    RequestedSwapWeek," +
                            "    RequestedSwapDay," +
                            "    RequestedSwapStartSlot," +
                            "    RequestedSwapEndSlot " +
                            "FROM Swaps WHERE RequestId=?");
                    stmt.setString(1, AcceptId);
                    ResultSet rs2 = stmt.executeQuery();

                    while (rs2.next()) {
                        RequestId = rs2.getString(1);
                        MechanicId = rs2.getString(2);
                        SelfSwapWeek = rs2.getInt(3);
                        SelfSwapDay = rs2.getInt(4);
                        SelfSwapStartSlot = rs2.getInt(5);
                        SelfSwapEndSlot = rs2.getInt(6);
                        RequestedMechanicId = rs2.getString(7);
                        RequestedSwapWeek = rs2.getInt(8);
                        RequestedSwapDay = rs2.getInt(9);
                        RequestedSwapStartSlot = rs2.getInt(10);
                        RequestedSwapEndSlot = rs2.getInt(11);
                    }
                    rs2.close();

                    System.out.println("\n1. Accept Swap");
                    System.out.println("2. Reject Swap");
                    System.out.println("3. Go Back");

                    int choice = t.nextInt();

                    if(choice==1){
                        stmt=connection.prepareStatement("UPDATE Invoice" +
                                " SET MechanicId=?" +
                                " WHERE MechanicId=? AND InvoiceWeek=? AND InvoiceDay=? AND start_slot=? AND end_slot=?");
                        stmt.setString(1, RequestedMechanicId);
                        stmt.setString(2, MechanicId);
                        stmt.setInt(3, RequestedSwapWeek);
                        stmt.setInt(4, RequestedSwapDay);
                        stmt.setInt(5, RequestedSwapStartSlot);
                        stmt.setInt(6, RequestedSwapEndSlot);
                        stmt.executeUpdate();

                        stmt=connection.prepareStatement("UPDATE Invoice" +
                                " SET MechanicId=?" +
                                " WHERE MechanicId=? AND InvoiceWeek=? AND InvoiceDay=? AND start_slot=? AND end_slot=?");
                        stmt.setString(1, MechanicId);
                        stmt.setString(2, RequestedMechanicId);
                        stmt.setInt(3, RequestedSwapWeek);
                        stmt.setInt(4, RequestedSwapDay);
                        stmt.setInt(5, RequestedSwapStartSlot);
                        stmt.setInt(6, RequestedSwapEndSlot);
                        stmt.executeUpdate();

                        stmt=connection.prepareStatement("DELETE FROM Swaps WHERE RequestId=?");
                        stmt.setString(1, RequestId);
                        stmt.executeUpdate();

                        System.out.println("\nThe request has been accepted! ");
                        System.out.println("\n1. Go Back");

                        choice = t.nextInt();
                        displayMechanicLanding(UserId, connection);
                    }
                    else if(choice==2){
                        stmt=connection.prepareStatement("DELETE FROM Swaps WHERE RequestId=?");
                        stmt.setString(1, RequestId);
                        stmt.executeUpdate();

                        System.out.println("\nThe request has been rejected.");
                        System.out.println("\n1. Go Back");
                    }
                    else{
                        displayMechanicLanding(UserId, connection);
                    }
                }
                else{
                    displayMechanicLanding(UserId, connection);
                }
            }

        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            DBUtility.close(connection);

        }
    }
}