package ConnectToDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectToDatabase {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/db-project";
    static final String USER = "root";
    static final String PASS = "thisisPASSWORD(&%";
    public static Connection openConnection() {
        Connection conn = null;

        try {
            Class.forName(JDBC_DRIVER);
            //System.out.println("Connecting...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            //System.out.println("Connected Successfully to DataBase");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Connection closed successfully.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
