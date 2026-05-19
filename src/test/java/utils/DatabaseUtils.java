package utils;

import java.sql.*;

public class DatabaseUtils {

    private static Connection connection;

    private DatabaseUtils() {}

    public static void baslatDb() throws SQLException {
        connection = DriverManager.getConnection(
                "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1",
                "sa",
                ""
        );
        Statement st = connection.createStatement();
        st.execute("""
                CREATE TABLE IF NOT EXISTS kullanicilar (
                    id         INT PRIMARY KEY,
                    email      VARCHAR(100),
                    first_name VARCHAR(50),
                    last_name  VARCHAR(50)
                )
                """);
        st.close();
    }

    public static void kullaniciKaydet(int id, String email,
                                       String firstName, String lastName) throws SQLException {
        PreparedStatement st = connection.prepareStatement(
                "INSERT INTO kullanicilar (id, email, first_name, last_name) VALUES (?, ?, ?, ?)"
        );
        st.setInt(1, id);
        st.setString(2, email);
        st.setString(3, firstName);
        st.setString(4, lastName);
        st.executeUpdate();
        st.close();
    }

    public static ResultSet kullaniciBul(int id) throws SQLException {
        PreparedStatement st = connection.prepareStatement(
                "SELECT * FROM kullanicilar WHERE id = ?"
        );
        st.setInt(1, id);
        return st.executeQuery();
    }

    public static void baglantiKapat() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}