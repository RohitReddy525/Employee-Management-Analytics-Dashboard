import java.sql.*;
import java.util.Scanner;

public class EmployeeManagementSystem {

    static String url = "jdbc:mysql://localhost:3306/employee_management";
    static String username = "root";
    static String password = "rohit";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n===== Employee Management System =====");
            System.out.println("1. Add Employee");
            System.out.println("2. View Employees");
            System.out.println("3. Search Employee");
            System.out.println("4. Update Employee");
            System.out.println("5. Delete Employee");
            System.out.println("6. Exit");
            System.out.print("Enter Choice: ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    addEmployee(sc);
                    break;

                case 2:
                    viewEmployees();
                    break;

                case 3:
                    searchEmployees(sc);
                    break;

                case 4:
                  updateEmployee(sc);
                    break;

                case 5:
                   deleteEmployee(sc);
                    break;

                case 6:
                    System.out.println("Thank You!");
                    sc.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }

    public static void addEmployee(Scanner sc) {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                    url,
                    username,
                    password
            );

            sc.nextLine();

            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Department: ");
            String department = sc.nextLine();

            System.out.print("Enter Salary: ");
            double salary = sc.nextDouble();

            sc.nextLine();

            System.out.print("Enter Email: ");
            String email = sc.nextLine();

            String sql =
                    "INSERT INTO employees(name, department, salary, email) VALUES (?, ?, ?, ?)";

            PreparedStatement pstmt =
                    con.prepareStatement(sql);

            pstmt.setString(1, name);
            pstmt.setString(2, department);
            pstmt.setDouble(3, salary);
            pstmt.setString(4, email);

            int rows = pstmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Employee Added Successfully!");
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

        public static void viewEmployees() {

    try {

        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection con = DriverManager.getConnection(
                url,
                username,
                password
        );

        Statement stmt = con.createStatement();

        ResultSet rs = stmt.executeQuery("SELECT * FROM employees");

        System.out.println("\nID\tNAME\t\tDEPARTMENT\tSALARY");

        while (rs.next()) {

            System.out.println(
                    rs.getInt("id") + "\t" +
                    rs.getString("name") + "\t" +
                    rs.getString("department") + "\t" +
                    rs.getDouble("salary")
            );
        }

        con.close();

    } catch (Exception e) {
        e.printStackTrace();
    }

    }
    public static void searchEmployees(Scanner sc) {

    try {

        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection con = DriverManager.getConnection(
                url,
                username,
                password
        );

        System.out.print("Enter Employee ID: ");
        int empId = sc.nextInt();

        String sql = "SELECT * FROM employees WHERE id = ?";

        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, empId);

        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {

            System.out.println("\nEmployee Found");
            System.out.println("ID: " + rs.getInt("id"));
            System.out.println("Name: " + rs.getString("name"));
            System.out.println("Department: " + rs.getString("department"));
            System.out.println("Salary: " + rs.getDouble("salary"));
            System.out.println("Email: " + rs.getString("email"));

        } else {

            System.out.println("Employee Not Found!");
        }

        con.close();

    } catch (Exception e) {
        e.printStackTrace();
    }
    
}
public static void updateEmployee(Scanner sc) {

    try {

        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection con = DriverManager.getConnection(
                url,
                username,
                password
        );

        System.out.print("Enter Employee ID: ");
        int empId = sc.nextInt();

        System.out.print("Enter New Salary: ");
        double newSalary = sc.nextDouble();

        String sql = "UPDATE employees SET salary = ? WHERE id = ?";

        PreparedStatement pstmt = con.prepareStatement(sql);

        pstmt.setDouble(1, newSalary);
        pstmt.setInt(2, empId);

        int rows = pstmt.executeUpdate();

        if (rows > 0) {
            System.out.println("Employee Updated Successfully!");
        } else {
            System.out.println("Employee Not Found!");
        }

        con.close();

    } catch (Exception e) {
        e.printStackTrace();
    }
}
public static void deleteEmployee(Scanner sc) {

    try {

        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection con = DriverManager.getConnection(
                url,
                username,
                password
        );

        System.out.print("Enter Employee ID: ");
        int empId = sc.nextInt();

        String sql = "DELETE FROM employees WHERE id = ?";

        PreparedStatement pstmt = con.prepareStatement(sql);

        pstmt.setInt(1, empId);

        int rows = pstmt.executeUpdate();

        if (rows > 0) {
            System.out.println("Employee Deleted Successfully!");
        } else {
            System.out.println("Employee Not Found!");
        }

        con.close();

    } catch (Exception e) {
        e.printStackTrace();
    }
}
}