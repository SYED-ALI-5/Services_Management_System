import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;

abstract class User {
    private String Name;
    private int CNIC;
    private String Username;
    private int Contact;
    private String Email;
    private String Password;

    public User(String name, int CNIC, int contact, String email, String password, String username) {
        Name = name;
        this.CNIC = CNIC;
        Contact = contact;
        Email = email;
        Password = password;
        Username = username;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getCNIC() {
        return CNIC;
    }

    public void setCNIC(int CNIC) {
        this.CNIC = CNIC;
    }

    public int getContact() {
        return Contact;
    }

    public void setContact(int contact) {
        Contact = contact;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }
}

class Customer extends User {
    private int CustomerID;
    private String Address;
    private static int counter = 1;

    public Customer(String name, int CNIC, int contact, String email, String password, String customerUsername, String address) {
        super(name, CNIC, contact, email, password, customerUsername);
        CustomerID = counter++;
        Address = address;
    }

    public int getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(int customerID) {
        CustomerID = customerID;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
}


class StaffMember extends User {
    private int StaffID;

    enum StaffRoles {ADMIN, PLUMBER, ELECTRICIAN, PAINTER, HOUSEKEEPER, CHEF}

    ;
    private StaffRoles Role;
    private String Schedule;
    private double Salary;
    private static int counter = 1;

    public StaffMember(String name, int CNIC, int contact, String email, String password, String staffUsername, StaffRoles role, String schedule, double salary) {
        super(name, CNIC, contact, email, password, staffUsername);
        StaffID = counter++;
        this.Role = role;
        this.Schedule = schedule;
        this.Salary = salary;
    }

    public int getStaffID() {
        return StaffID;
    }

    public void setStaffID(int staffID) {
        StaffID = staffID;
    }

    public StaffRoles getRole() {
        return Role;
    }

    public void setRole(StaffRoles role) {
        Role = role;
    }

    public String getSchedule() {
        return Schedule;
    }

    public void setSchedule(String schedule) {
        Schedule = schedule;
    }

    public double getSalary() {
        return Salary;
    }

    public void setSalary(double salary) {
        Salary = salary;
    }
}


class Services {
    private int ServiceID;
    private String Name;
    private int cost;
    private String duration;
    private String Description;
    private static int counter = 1;

    public Services(String name, int cost, String duration, String description) {
        ServiceID = counter++;
        Name = name;
        this.cost = cost;
        this.duration = duration;
        Description = description;
    }

    public int getServiceID() {
        return ServiceID;
    }

    public void setServiceID(int serviceID) {
        ServiceID = serviceID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    @Override
    public String toString() {
        return String.format(ServiceID + ") " + Name + "  " + duration + "  $" + cost + "  " + Description);
    }
}

enum RequestStatus {PENDING, DONE};

class Requests {
    private int RequestID;
    private Customer customer;
    private RequestStatus RequestStatus;
    private Services service;
    private String Description;
    private static int counter = 1;

    public Requests(Customer customer, RequestStatus ReqStatus, Services service, String description) {
        RequestID = counter++;
        this.customer = customer;
        RequestStatus = ReqStatus;
        this.service = service;
        Description = description;
    }

    public int getRequestID() {
        return RequestID;
    }

    public void setRequestID(int requestID) {
        RequestID = requestID;
    }

    public RequestStatus getRequestStatus() {
        return RequestStatus;
    }

    public void setRequestStatus(RequestStatus requestStatus) {
        RequestStatus = requestStatus;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Services getService() {
        return service;
    }

    public void setService(Services service) {
        this.service = service;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    @Override
    public String toString() {
        return String.format(RequestID + ") Customer: " + customer.getName() + "  Service: " + service.getName() + "  Description: " + Description);
    }
}

enum AppoitmentStatus {PENDING, REJECTED, COMPLETED};

class Appointment {
    private int AppointmentID;
    private Customer customer;
    private StaffMember Staff;
    private String Date;
    private Services Service;
    private AppoitmentStatus status;
    private String Description;
    private static int counter = 1;

    public Appointment(Customer customer, StaffMember staff, String date, AppoitmentStatus status, Services service, String description) {
        AppointmentID = counter++;
        this.customer = customer;
        Staff = staff;
        Date = date;
        Service = service;
        this.status = status;
        Description = description;
    }

    public Services getService() {
        return Service;
    }

    public void setService(Services service) {
        Service = service;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getAppointmentID() {
        return AppointmentID;
    }

    public void setAppointmentID(int appointmentID) {
        AppointmentID = appointmentID;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public StaffMember getStaff() {
        return Staff;
    }

    public void setStaff(StaffMember staff) {
        Staff = staff;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public AppoitmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppoitmentStatus status) {
        this.status = status;
    }
}

public class Main {
    static Connection connection = Database.Connect();
    static ArrayList<User> Users = new ArrayList<>();
    static ArrayList<Requests> Requests = new ArrayList<>();
    static ArrayList<Services> services = new ArrayList<>();
    static ArrayList<Appointment> Appointments = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Database.InitializeData(Users, Requests, services, Appointments);
        boolean LoggedIn = false;
        User LoggedInUser = null;
        do {
            System.out.println("1. LOGIN to your account (Customer / Staff / Admin) \n" + "2. SIGNUP/Create an account");
            System.out.print("Enter: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1: {
                    do {
                        System.out.println("----------LOGIN----------");
                        System.out.print("Username: ");
                        String username = sc.next();
                        System.out.print("Password: ");
                        String password = sc.next();
                        for (User S : Users) {
                            if (S.getUsername().equals(username) && S.getPassword().equals(password)) {
                                LoggedIn = true;
                                LoggedInUser = S;
                                System.out.println("LOGIN SUCCESFULL !");
                                break;
                            }
                        }
                        if (!LoggedIn) {
                            System.err.println("Invalid Username or password! please Try Again.");
                            System.out.println();
                        }
                    } while (!LoggedIn);
                    break;
                }
                case 2: {
                    do {
                        System.out.println("---------- SIGNUP ----------");
                        System.out.print("Username: ");
                        String username = sc.next();
                        System.out.print("Email: ");
                        String email = sc.next();
                        System.out.print("Password: ");
                        String password = sc.next();
                        System.out.print("First Name: ");
                        String FirstName = sc.next();
                        System.out.print("Last Name: ");
                        String Lastname = sc.next();
                        String Name = FirstName + " " + Lastname;
                        System.out.print("CNIC: ");
                        int CNIC = sc.nextInt();
                        System.out.print("Contact: ");
                        int contact = sc.nextInt();
                        System.out.print("Address: ");
                        String Address = sc.next();
                        Database.CreateNewUser(username, password, Name, CNIC, contact, email, Address);
                        Users.add(new Customer(Name, CNIC, contact, email, password, username, Address));
                        break;
                    } while (true);
                }
            }
        } while (!LoggedIn);
        if (LoggedInUser instanceof StaffMember) {
            StaffMember LoggedInStaffMember = (StaffMember) LoggedInUser;
            if (LoggedInStaffMember.getRole().equals(StaffMember.StaffRoles.ADMIN)) {
                int choose;
                do {
                    System.out.println("------ MENU ------");
                    System.out.println("1. Register a new Staff Member / Admin \n" + "2. Manage Staff Members \n" + "3. check Customers Requests \n" + "4. Generate Staff Reports \n" + "0. To Exit");
                    System.out.print("Enter: ");
                    choose = sc.nextInt();
                    switch (choose) {
                        case 1: {
                            do {
                                System.out.println("---------- REGISTER ----------");
                                System.out.print("Username: ");
                                String username = sc.next();
                                System.out.print("Email: ");
                                String email = sc.next();
                                System.out.print("Password: ");
                                String password = sc.next();
                                System.out.print("First Name: ");
                                String FirstName = sc.next();
                                System.out.print("Last Name: ");
                                String Lastname = sc.next();
                                String Name = FirstName + " " + Lastname;
                                System.out.print("CNIC: ");
                                int CNIC = sc.nextInt();
                                System.out.print("Contact: ");
                                int contact = sc.nextInt();
                                System.out.print("Role: ");
                                String Role = sc.next();
                                System.out.print("Enter Schedule (format : 8:00-4:00) : ");
                                String Schedule = sc.next();
                                System.out.print("Salary: ");
                                double Salary = sc.nextDouble();
                                StaffMember.StaffRoles staffRole = StaffMember.StaffRoles.valueOf(Role.toUpperCase());
                                Database.CreateNewMember(username, password, Name, CNIC, contact, email, Role, Schedule, Salary);
                                Users.add(new StaffMember(Name, CNIC, contact, email, password, username, staffRole, Schedule, Salary));
                                break;
                            } while (true);
                            break;
                        }
                        case 2: {
                            for (User U : Users) {
                                if (U instanceof StaffMember) {
                                    StaffMember member = (StaffMember) U;
                                    if (!member.getRole().equals(StaffMember.StaffRoles.ADMIN)) {
                                        System.out.println("ID: " + member.getStaffID() + "| Name: " + member.getName());
                                    }
                                }
                            }
                            System.out.println("Select Staff Member by ID: ");
                            int SelectedMember = sc.nextInt();
                            System.out.println("1. Update Role \n" + "2. Update Schedule \n" + "3. Update Salary ");
                            System.out.print("Enter: ");
                            int Manage = sc.nextInt();
                            for (User U : Users) {
                                if (U instanceof StaffMember) {
                                    StaffMember member = (StaffMember) U;
                                    if (member.getStaffID() == SelectedMember) {
                                        switch (Manage) {
                                            case 1: {
                                                System.out.println("Current Role : " + member.getRole().name());
                                                System.out.print("Enter Updated Role: ");
                                                String UpdatedRole = sc.next();
                                                StaffMember.StaffRoles staffRole = StaffMember.StaffRoles.valueOf(UpdatedRole.toUpperCase());
                                                member.setRole(staffRole);
                                                Database.UpdateMember("UPDATE Staff SET Role = ? WHERE Username = ?", UpdatedRole, member.getUsername());
                                                break;
                                            }
                                            case 2: {
                                                System.out.println("Current Schedule : " + member.getSchedule());
                                                System.out.print("Enter Updated Schedule (format  8:00-4:00) : ");
                                                String UpdatedSchedule = sc.next();
                                                member.setSchedule(UpdatedSchedule);
                                                Database.UpdateMember("UPDATE Staff SET Schedule = ? WHERE Username = ?", UpdatedSchedule, member.getUsername());
                                                break;
                                            }
                                            case 3: {
                                                System.out.println("Current Salary : " + member.getSalary());
                                                System.out.print("Enter Updated Salary : ");
                                                double UpdatedSalary = sc.nextDouble();
                                                member.setSalary(UpdatedSalary);
                                                Database.UpdateSalary("UPDATE Staff SET Salary = ? WHERE Username = ?", UpdatedSalary, member.getUsername());
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                            break;
                        }
                        case 3: {

                            for (Requests R : Requests) {
                                if (R.getRequestStatus().equals(RequestStatus.PENDING)) {
                                    System.out.println(R.toString());
                                }
                            }
                            System.out.print("Select Request to Manage: ");
                            int RequestSelect = sc.nextInt();
                            Requests SelectedRequest = null;
                            for (Requests R : Requests) {
                                if (R.getRequestID() == RequestSelect) {
                                    SelectedRequest = R;
                                }
                            }
                            System.out.print("Press 1 to make an Appointment : ");
                            int App = sc.nextInt();
                            if (App == 1) {
                                System.out.println("Select Staff: ");
                                for (User U : Users) {
                                    if (U instanceof StaffMember) {
                                        StaffMember Selected = (StaffMember) U;
                                        if (!Selected.getRole().equals(StaffMember.StaffRoles.ADMIN)) {
                                            System.out.println(Selected.getStaffID() + ": " + Selected.getName() + "  " + Selected.getRole().name());
                                        }
                                    }
                                }
                                System.out.print("Enter Staff by ID: ");
                                StaffMember SelectedStaff = null;
                                int Staffchoice = sc.nextInt();
                                for (User U : Users) {
                                    if (U instanceof StaffMember) {
                                        StaffMember Selected = (StaffMember) U;
                                        if (Selected.getStaffID() == Staffchoice) {
                                            SelectedStaff = Selected;
                                        }
                                    }
                                }
                                System.out.print("Enter Date (0-00-0000): ");
                                String date = sc.next();
                                Appointments.add(new Appointment(SelectedRequest.getCustomer(), SelectedStaff, date, AppoitmentStatus.PENDING, SelectedRequest.getService(), SelectedRequest.getDescription()));
                                SelectedRequest.setRequestStatus(RequestStatus.DONE);
                                Database.AddAppointment(SelectedRequest.getCustomer().getUsername(), SelectedStaff.getUsername(), date, SelectedRequest.getService().getName(), AppoitmentStatus.PENDING.name(), SelectedRequest.getDescription());
                                Database.SetRequestStatusDone(SelectedRequest.getCustomer().getUsername());
                            }
                            break;
                        }
                        case 4: {
                            for (Appointment App : Appointments) {
                                int count = 0;
                                if (App.getStatus().equals(AppoitmentStatus.COMPLETED)) {
                                    System.out.println(count++ + ") " + App.getStaff().getName() + "  " + App.getService().getName() + "  $" + App.getService().getCost());
                                }
                            }
                        }
                    }
                } while (choose != 0);
            } else {
                int choose;
                do {
                    System.out.println("------ MENU ------");
                    System.out.println("1. Your Account \n" + "2. See Pending Appointments \n" + "3. Generate Completed Appointments Report \n" + "0. To Exit");
                    System.out.println("Enter: ");
                    choose = sc.nextInt();
                    switch (choose) {
                        case 1: {
                            int choice;
                            do {
                                System.out.println("----- Your Details -----");
                                System.out.println("1) Name: " + LoggedInStaffMember.getName());
                                System.out.println("2) Username: " + LoggedInStaffMember.getUsername());
                                System.out.println("3) CNIC: " + LoggedInStaffMember.getContact());
                                System.out.println("4) Contact: " + LoggedInStaffMember.getContact());
                                System.out.println("5) Role: " + LoggedInStaffMember.getRole().name());
                                System.out.println("6) Schedule: " + LoggedInStaffMember.getSchedule());
                                System.out.print("Press 0 to return: ");
                                choice = sc.nextInt();
                            } while (choice != 0);
                            break;
                        }
                        case 2: {
                            int choice;
                            do {
                                for (Appointment App : Appointments) {
                                    if (App.getStatus().equals(AppoitmentStatus.PENDING) && App.getStaff().equals(LoggedInStaffMember)) {
                                        System.out.println(App.getAppointmentID() + ")   Customer: " + App.getCustomer().getUsername() + "   Service: " + App.getService().getName() + "   Address: " + App.getCustomer().getAddress() + "   Date: " + App.getDate() + "   Description: " + App.getDescription());
                                    }
                                }
                                System.out.println("Select Appointment by ID to change status to COMPLETED");
                                System.out.print("Enter (0 to exit):");
                                choice = sc.nextInt();
                                if (choice != 0) {
                                    for (Appointment App : Appointments) {
                                        if (App.getStatus().equals(AppoitmentStatus.PENDING) && App.getStaff().equals(LoggedInStaffMember)) {
                                            if (App.getAppointmentID() == choice) {
                                                System.out.print("press 1 to confirm: ");
                                                int confirmation = sc.nextInt();
                                                if (confirmation == 1) {
                                                    App.setStatus(AppoitmentStatus.COMPLETED);
                                                    Database.UpdateAppointmentStatus(App.getAppointmentID(), "COMPLETED");
                                                }
                                            }
                                        }
                                    }
                                }
                            } while (choice != 0);
                            break;
                        }
                        case 3: {
                            for (Appointment App : Appointments) {
                                if (App.getStatus().equals(AppoitmentStatus.COMPLETED) && App.getStaff().equals(LoggedInStaffMember)) {
                                    System.out.println(App.getAppointmentID() + ")   Customer: " + App.getCustomer().getUsername() + "   Service: " + App.getService().getName() + "   Address: " + App.getCustomer().getAddress() + "   Date: " + App.getDate() + "   Description: " + App.getDescription());
                                }
                            }
                        }
                    }
                } while (choose != 0);
            }
        }
        if (LoggedInUser instanceof Customer) {
            Customer LoggedInCustomer = (Customer) LoggedInUser;
            int choose;
            do {
                System.out.println("------ MENU ------");
                System.out.println("1. Your Account \n" + "2. Request A service Appointment \n" + "3. See Pending Appointments \n" + "4. See Completed Appointments \n" + "0. To Exit");
                System.out.println("Enter: ");
                choose = sc.nextInt();
                if (choose != 0) {
                    switch (choose) {
                        case 1: {
                            int choice;
                            do {
                                System.out.println("----- Your Details -----");
                                System.out.println("1) Name: " + LoggedInCustomer.getName());
                                System.out.println("2) Username: " + LoggedInCustomer.getUsername());
                                System.out.println("3) CNIC: " + LoggedInCustomer.getContact());
                                System.out.println("4) Contact: " + LoggedInCustomer.getContact());
                                System.out.println("5) Email: " + LoggedInCustomer.getEmail());
                                System.out.println("6) Address: " + LoggedInCustomer.getAddress());
                                System.out.print("Press 0 to return: ");
                                choice = sc.nextInt();
                            } while (choice != 0);
                            break;
                        }
                        case 2: {
                            System.out.println("Available Services: ");
                            for (Services S : services) {
                                System.out.println(S.toString());
                            }
                            System.out.print("Select service by ID : ");
                            int serviceSelection = sc.nextInt();
                            Services SelectedService = null;
                            if (serviceSelection != 0) {
                                for (Services S : services) {
                                    if (S.getServiceID() == serviceSelection) {
                                        SelectedService = S;
                                    }
                                }
                                System.out.print("Enter Description: ");
                                String Description = sc.next();
                                Requests.add(new Requests(LoggedInCustomer, RequestStatus.PENDING, SelectedService, Description));
                                Database.AddNewRequest(LoggedInCustomer.getUsername(), SelectedService.getName(), Description, RequestStatus.PENDING.name());
                            }
                            break;
                        }
                        case 3: {
                            for (Appointment App : Appointments) {
                                if (App.getStatus().equals(AppoitmentStatus.PENDING) && App.getCustomer().equals(LoggedInCustomer)) {
                                    System.out.println(App.getAppointmentID() + ")   Service: " + App.getService().getName() + "   Date: " + App.getDate() + "   Cost: $" + App.getService().getCost() + "   Staff: " + App.getStaff().getName() + "   Description: " + App.getDescription());
                                }
                            }
                            System.out.println("Select Appointment by ID to Reject an Appointment or 0 to exit");
                            int Appointmentchoice = sc.nextInt();
                            if (Appointmentchoice != 0) {
                                for (Appointment App : Appointments) {
                                    if (App.getStatus().equals(AppoitmentStatus.PENDING) && App.getCustomer().equals(LoggedInCustomer)) {
                                        if (App.getAppointmentID() == Appointmentchoice) {
                                            System.out.print("Press 1 to confirm");
                                            int confirmation = sc.nextInt();
                                            if (confirmation == 1) {
                                                App.setStatus(AppoitmentStatus.REJECTED);
                                                Database.UpdateAppointmentStatus(App.getAppointmentID(), "REJECTED");
                                            }
                                        }
                                    }
                                }
                            }
                            break;
                        }
                        case 4: {
                            for (Appointment App : Appointments) {
                                if (App.getStatus().equals(AppoitmentStatus.COMPLETED) && App.getCustomer().equals(LoggedInCustomer)) {
                                    System.out.println(App.getAppointmentID() + ")   Service: " + App.getService().getName() + "   Date: " + App.getDate() + "   Cost: $" + App.getService().getCost() + "   Staff: " + App.getStaff().getName() + "   Description: " + App.getDescription());
                                }
                            }
                        }
                    }
                }
            } while (choose != 0);
        }
    }
}