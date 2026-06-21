import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class UpdateEmployee {

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
            double newSalary = 70000;

            String sql =
                    "UPDATE employees SET salary = ? WHERE id = ?";

            PreparedStatement pst =
                    con.prepareStatement(sql);

            pst.setDouble(1, newSalary);
            pst.setInt(2, empId);

            int rows = pst.executeUpdate();

            if(rows > 0) {
                System.out.println("Employee Updated Successfully!");
            } else {
                System.out.println("Employee Not Found!");
            }

            con.close();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}