import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class AddEmployee {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/employee_management";
        String username = "root";
        String password = "rohit";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                    url,
                    username,
                    password
            );

            String sql = "INSERT INTO employees(name, department, salary, email) VALUES (?, ?, ?, ?)";

            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, "Rohit");
            pst.setString(2, "IT");
            pst.setDouble(3, 60000);
            pst.setString(4, "rohit@gmail.com");

            int rows = pst.executeUpdate();

            if(rows > 0) {
                System.out.println("Employee Added Successfully!");
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}