import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class database {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/java-ii"; // Replace with your actual DB name
        String user = "root";
        String password = ""; // Default for Laragon

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("✅ Connected to MySQL!");

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM persons");

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") +
                                   ", First Name: " + rs.getString("first_name") +
                                   ", Last Name: " + rs.getString("last_name"));
            }
        } catch (Exception e) {
            System.out.println("❌ Connection failed:");
            e.printStackTrace();
        }
    }
}
