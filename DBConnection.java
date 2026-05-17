import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection() {

        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/quiz_system",
                    "root",
                    "root123"
            );

            System.out.println("Database Connected Successfully");
            return con;

        } catch (Exception e) {
            System.out.println("Database connection failed");
            e.printStackTrace();
            return null;
        }
    }
}
