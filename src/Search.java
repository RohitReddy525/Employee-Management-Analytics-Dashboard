import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Search {

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

            int empId = 11;

            String sql = "SELECT * FROM employees WHERE id = ?";

            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, empId);

            ResultSet rs = pst.executeQuery();

            if(rs.next()) {
                System.out.println("Employee Found");
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Department: " + rs.getString("department"));
                System.out.println("Salary: " + rs.getDouble("salary"));
                System.out.println("Email: " + rs.getString("email"));
            } else {
                System.out.println("Employee Not Found");
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}