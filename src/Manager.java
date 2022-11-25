import java.sql.*;
import java.util.Scanner;

public class Manager {
	Home home = new Home();
	Connection connection;
	PreparedStatement stmt = null;
	ResultSet rs = null;
	String userId;

	public void displayManagerLanding(String user_id) {
		Scanner t = new Scanner(System.in);
		userId = user_id;
		System.out.println("Please select one of the following: \n");
		System.out.println("1. Setup Store\n" +
				"2. Add New Employee\n" +
				"3. Logout");

		int user_choice = t.nextInt();
		t.nextLine();
		switch (user_choice) {
			case 1:
				setupStore();
				break;
			case 2:
				addEmployee();
				break;
			case 3:
				home.displayHomepage();
				break;
			default:
				System.out.println("Please enter a valid choice");
				break;
		}
		t.close();
	}

	public void setupStore() {
		Scanner t = new Scanner(System.in);
		System.out.println("Please select one of the following: \n");
		System.out.println("1. Add Employee\n" +
				"2. Setup Operational Hours\n" +
				"3. Setup Service Prices\n" +
				"4. Go Back\n");

		int user_choice = t.nextInt();
		t.nextLine();
		switch (user_choice) {
			case 1:
				addEmployee();
				setupStore();
				break;
			case 2:
				setupOperationalHours();
				setupStore();
				break;
			case 3:
				setupServicePrices();
				setupStore();
				break;
			case 4:
				displayManagerLanding(userId);
				break;
			default:
				System.out.println("Please enter a valid choice");
				break;
		}
		t.close();
	}

	public void setupOperationalHours() {
		Scanner t = new Scanner(System.in);
		System.out.println("\n");
		System.out.println("Please select one of the following: \n");
		System.out.println("1. Setup Operational Hours\n" +
				"2. Go Back\n");

		int user_choice = t.nextInt();
		switch (user_choice) {
			case 1:
				operationalSaturday();
				break;
			case 2:
				setupStore();
				break;
			default:
				System.out.println("Please enter a valid choice");
				break;
		}

		t.close();

	}

	public void operationalSaturday() {
		Scanner t = new Scanner(System.in);
		System.out.println("Will the store be operational on Saturday? \n");
		String option = t.nextLine();

		switch (option) {
			case "yes":
				System.out.println("\n");
				System.out.println("Store is operational from Monday - Saturday\n");
				break;
			case "no":
				System.out.println("\n");
				System.out.println("Store is operational from Monday - Friday\n");
				break;
			default:
				System.out.println("Please enter a valid choice");
				break;
		}
	}
	/*

        CHANGE FROM HERE TOMORROW

    */
	public void setupServicePrices() {
		Scanner t = new Scanner(System.in);
		System.out.println("Please select one of the following: \n");
		System.out.println("1. Setup Maintenance Service Prices\n" +
				"2. Setup Repair Service Prices\n" +
				"3. Go Back\n");

		int user_choice = t.nextInt();
		t.nextLine();
		switch (user_choice) {
			case 1:
				setupMaintenanceServicePrices();
				break;
			case 2:
				setupRepairServicePrices();
				break;
			case 3:
				setupStore();
				break;
			default:
				System.out.println("Please enter a valid choice");
				break;
		}
	}

		public void setupMaintenanceServicePrices(){
		    Scanner t = new Scanner(System.in);
			System.out.print("A. Schedule A price:\t");
			float aPrice= t.nextFloat();
			System.out.println("");

			System.out.print("B. Schedule B price:\t");
			float bPrice= t.nextFloat();
			System.out.println("");

			System.out.print("C. Schedule C price:\t");
			float cPrice= t.nextFloat();
			System.out.println("");

			System.out.println("Please select one of the following");

			System.out.println("1. Setup prices" + "\n" + "2. Go back");

			int userChoice= t.nextInt();
			t.nextLine();
			switch (userChoice) {
				case 1:
					System.out.println("Setup of the scheduled services prices is completed");
					setupServicePrices();
					break;
				case 2:
					setupServicePrices();
					break;
				default: System.out.println("Enter a valid choice");
					break;
			}
		}

		public void setupRepairServicePrices() {

			Scanner t = new Scanner(System.in);
			System.out.println("Please enter the prices of the following services: ");

			System.out.print("A. Belt Replacement:\t");
			float beltReplacementPrice = t.nextFloat();
			System.out.println("");

			System.out.print("B. Engine Repair:\t");
			float engineRepairPrice = t.nextFloat();
			System.out.println("");

			System.out.print("C. Catalytic Converter Repair:\t");
			float catalyticConverterPrice = t.nextFloat();
			System.out.println("");

			System.out.print("D. Muffler Repair:\t");
			float mufflerRepairPrice = t.nextFloat();
			System.out.println("");

			System.out.print("E. Alternator Repair:\t");
			float alternatorRepairPrice = t.nextFloat();
			System.out.println("");

			System.out.print("F. Power Lock:\t");
			float powerPrice = t.nextFloat();
			System.out.println("");

			System.out.print("G. Axle Repair:\t");
			float axlePrice = t.nextFloat();
			System.out.println("");

			System.out.print("H. Transmission Flush:\t");
			float flushPrice = t.nextFloat();
			System.out.println("");

			System.out.print("I. Tire Balancing:\t");
			float tirePrice = t.nextFloat();
			System.out.println("");

			System.out.print("J. Wheel Alignment:\t");
			float wheelPrice = t.nextFloat();
			System.out.println("");

			System.out.print("K. Compressor Repair:\t");
			float compPrice = t.nextFloat();
			System.out.println("");

			System.out.println("Please select one of the following");
			System.out.println("1. Setup prices" + "\n" + "2. Go back");

			int user_choice = t.nextInt();
			t.nextLine();
			switch (user_choice) {
				case 1:
					System.out.println("Setup of the scheduled services prices is completed");
					setupServicePrices();
					break;
				case 2:
					setupServicePrices();
					break;
				default:
					System.out.println("Enter a valid choice");
					break;
			}
		}

		private void addEmployee() {
			Scanner t = new Scanner(System.in);

			System.out.println("Please enter the following");


			System.out.println("Name:\t");
			String name = t.nextLine();

			System.out.println("Address:\t");
			String address = t.nextLine();

			System.out.println("email:\t");
			String email = t.nextLine();

			System.out.println("Phone Number:\t");
			String phone = t.nextLine();

			System.out.println("Add Role -Manager(1),Receptionist(2),Mechanic(3)");
			int role = t.nextInt();
			t.nextLine();

			System.out.println("Start Date:\t");
			String start_date = t.nextLine();

			System.out.println("Compensation:\t");
			float compensation = t.nextFloat();

			String pwd = "password";

			System.out.println("Please select one of the following");
			System.out.println("1. Add\n 2. Go Back");

			int user_choice = t.nextInt();
			switch (user_choice) {
				case 1:
					int newId = 0;
					try {
						connection = DBUtility.connectDB(SetupConnection.username, SetupConnection.password);

						stmt = connection.prepareStatement("select max(empid) from employee");
						rs = stmt.executeQuery();
						while (rs.next()) {
							newId = rs.getInt(1) + 1;
						}

						rs.close();

						stmt = connection.prepareStatement("insert into Employee values(?,?,?,?,?,?,?)");
						stmt.setString(1, Integer.toString(newId));
						stmt.setInt(2, role);
						stmt.setString(3, name);
						stmt.setString(4, email);
						stmt.setString(5, address);
						stmt.setString(6, phone);
						stmt.setString(7, start_date);
						stmt.executeUpdate();

						stmt = connection.prepareStatement("insert into login values(?,?)");
						stmt.setString(1, String.valueOf(newId));
						stmt.setString(2, pwd);
//						stmt.setInt(3, role);
						stmt.executeUpdate();
						System.out.println("Employee added successfully");
						DBUtility.close(connection);

					} catch (SQLException e) {
						System.out.println("Connection Failed! Check output console");
						e.printStackTrace();
						DBUtility.close(connection);

					}
				case 2:
					setupStore();
				default:
					System.out.println("Enter a valid choice");
			}
			t.close();
		}
}
