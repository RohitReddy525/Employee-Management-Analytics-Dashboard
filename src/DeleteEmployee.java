import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DeleteEmployee {

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

            String sql =
                    "DELETE FROM employees WHERE id = ?";

            PreparedStatement pst =
                    con.prepareStatement(sql);

            pst.setInt(1, empId);

            int rows = pst.executeUpdate();

            if(rows > 0) {
                System.out.println("Employee Deleted Successfully!");
            } else {
                System.out.println("Employee Not Found!");
            }

            con.close();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}