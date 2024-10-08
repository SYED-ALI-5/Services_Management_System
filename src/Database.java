import java.sql.*;
import java.util.ArrayList;

public class Database {
    static Connection con = null;

    public static Connection Connect() {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://127.0.0.1:3306/ServicesMangementSystem";
            String username = "root";
            String password = "SyedAhmadAli";
            con = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }

    public static void InitializeData(ArrayList<User> Users, ArrayList<Requests> Requestss, ArrayList<Services> services, ArrayList<Appointment> Appointments) {
        // Getting Customers
        try {
            String Querie = "Select * from Customers";
            Statement read = con.createStatement();
            ResultSet rs = read.executeQuery(Querie);
            while (rs.next()) {
                String username = rs.getString(2);
                String password = rs.getString(3);
                String name = rs.getString(4);
                int Cnic = rs.getInt(5);
                int Contact = rs.getInt(6);
                String email = rs.getString(7);
                String Address = rs.getString(8);
                Users.add(new Customer(name, Cnic, Contact, email, password, username, Address));
            }
        } catch (Exception E) {
            E.printStackTrace();
        }
        // Getting Staff Members
        try {
            String Querie = "Select * from Staff";
            Statement read = con.createStatement();
            ResultSet rs = read.executeQuery(Querie);
            while (rs.next()) {
                String username = rs.getString(2);
                String password = rs.getString(3);
                String name = rs.getString(4);
                int Cnic = rs.getInt(5);
                int Contact = rs.getInt(6);
                String email = rs.getString(7);
                String Role = rs.getString(8);
                String Schedule = rs.getString(9);
                Double Salary = rs.getDouble(10);
                StaffMember.StaffRoles staffRole = StaffMember.StaffRoles.valueOf(Role.toUpperCase());
                Users.add(new StaffMember(name, Cnic, Contact, email, password, username, staffRole, Schedule, Salary));
            }
        } catch (Exception E) {
            E.printStackTrace();
        }
        // Getting Services
        try {
            String Querie = "Select * from Services";
            Statement read = con.createStatement();
            ResultSet rs = read.executeQuery(Querie);
            while (rs.next()) {
                String Name = rs.getString(2);
                int Cost = rs.getInt(3);
                String Duration = rs.getString(4);
                String Description = rs.getString(5);
                services.add(new Services(Name, Cost, Duration, Description));
            }
        } catch (Exception E) {
            E.printStackTrace();
        }
        // Getting Requests
        try {
            String Querie = "Select * from Requests";
            Statement read = con.createStatement();
            ResultSet rs = read.executeQuery(Querie);
            while (rs.next()) {
                String Customer = rs.getString(2);
                String Service = rs.getString(3);
                String Description = rs.getString(4);
                String Status = rs.getString(5);
                RequestStatus Rstatus = RequestStatus.valueOf(Status.toUpperCase());
                Customer customer = GetCustomer(Customer, Users);
                Services service = getService(Service, services);
                Requestss.add(new Requests(customer, Rstatus, service, Description));
            }
        } catch (Exception E) {
            E.printStackTrace();
        }
        // Getting Appointments
        try {
            String Querie = "Select * from Appointments";
            Statement read = con.createStatement();
            ResultSet rs = read.executeQuery(Querie);
            while (rs.next()) {
                String Customer = rs.getString(2);
                String staff = rs.getString(3);
                String date = rs.getString(4);
                String Service = rs.getString(5);
                String Status = rs.getString(6);
                String Description = rs.getString(7);
                AppoitmentStatus status = AppoitmentStatus.valueOf(Status);
                Customer customer = GetCustomer(Customer, Users);
                Services service = getService(Service, services);
                StaffMember Staff = GetStaffMember(staff, Users);
                Appointments.add(new Appointment(customer, Staff, date, status, service, Description));
            }
        } catch (Exception E) {
            E.printStackTrace();
        }
    }

    public static void CreateNewUser(String username, String password, String Name, int Cnic, int Contact, String email, String Address) {
        try {
            String Querie = "Insert into Customers (username,password, name, cnic,contact, email, Address) values (? , ? ,  ? ,  ? ,  ? ,  ? , ? )";
            PreparedStatement pstm = con.prepareStatement(Querie);
            pstm.setString(1, username);
            pstm.setString(2, password);
            pstm.setString(3, Name);
            pstm.setInt(4, Cnic);
            pstm.setInt(5, Contact);
            pstm.setString(6, email);
            pstm.setString(7, Address);
            pstm.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Username already present. please try again! \n");
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void CreateNewMember(String username, String password, String Name, int Cnic, int Contact, String email, String Role, String Schedule, double Salary) {
        try {
            String Querie = "Insert into Staff (username,password, name, cnic,contact, email, Role, Schedule, Salary) values (? , ? ,  ? ,  ? ,  ? ,  ? , ?, ?, ? )";
            PreparedStatement pstm = con.prepareStatement(Querie);
            pstm.setString(1, username);
            pstm.setString(2, password);
            pstm.setString(3, Name);
            pstm.setInt(4, Cnic);
            pstm.setInt(5, Contact);
            pstm.setString(6, email);
            pstm.setString(7, Role);
            pstm.setString(8, Schedule);
            pstm.setDouble(9, Salary);
            pstm.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Username already present. please try again! \n");
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void UpdateMember(String UpdatedStatement, String Update, String Username) {
        try {
            PreparedStatement pstm = con.prepareStatement(UpdatedStatement);
            pstm.setString(1, Update);
            pstm.setString(2, Username);
            pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void UpdateSalary(String UpdatedStatement, Double Salary, String Username) {
        try {
            PreparedStatement pstm = con.prepareStatement(UpdatedStatement);
            pstm.setDouble(1, Salary);
            pstm.setString(2, Username);
            pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Customer GetCustomer(String Username, ArrayList<User> Users) {
        Customer current = null;
        for (User U : Users) {
            if (U instanceof Customer) {
                Customer customer = (Customer) U;
                if (U.getUsername().equals(Username)) {
                    current = customer;
                }
            }
        }
        return current;
    }

    public static StaffMember GetStaffMember(String Username, ArrayList<User> Users) {
        StaffMember current = null;
        for (User U : Users) {
            if (U instanceof StaffMember) {
                StaffMember Staff = (StaffMember) U;
                if (U.getUsername().equals(Username)) {
                    current = Staff;
                }
            }
        }
        return current;
    }

    public static Services getService(String Name, ArrayList<Services> services) {
        Services current = null;
        for (Services S : services) {
            if (S.getName().equals(Name)) {
                current = S;
            }
        }
        return current;
    }

    public static void AddAppointment(String CustomerName, String StaffName, String Date, String ServiceName, String Status, String Description) {
        try {
            String Querie = "Insert into Appointments (CUSTOMER, STAFF, DATE, SERVICE, STATUS, DESCRIPTION) values (? , ? ,  ? ,  ? ,  ? ,  ? )";
            PreparedStatement pstm = con.prepareStatement(Querie);
            pstm.setString(1, CustomerName);
            pstm.setString(2, StaffName);
            pstm.setString(3, Date);
            pstm.setString(4, ServiceName);
            pstm.setString(5, Status);
            pstm.setString(6, Description);
            pstm.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Username already present. please try again! \n");
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void SetRequestStatusDone(String Username) {
        try {
            String Querie = "UPDATE Requests SET Status = ? WHERE CUSTOMER = ?";
            PreparedStatement pstm = con.prepareStatement(Querie);
            pstm.setString(1, "DONE");
            pstm.setString(2, Username);
            pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void AddNewRequest(String Customer, String Service, String Description, String Status) {
        try {
            String Querie = "Insert into Requests (Customer, Service, Description, Status) values (? , ? , ? , ? )";
            PreparedStatement pstm = con.prepareStatement(Querie);
            pstm.setString(1, Customer);
            pstm.setString(2, Service);
            pstm.setString(3, Description);
            pstm.setString(4, Status);
            pstm.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Username already present. please try again! \n");
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void UpdateAppointmentStatus(int ID, String Status) {
        try {
            String Querie = "UPDATE Appointments SET Status = ? WHERE ID = ?";
            PreparedStatement pstm = con.prepareStatement(Querie);
            pstm.setString(1, Status);
            pstm.setInt(2, ID);
            pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
