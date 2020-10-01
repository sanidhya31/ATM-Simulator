import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase {
    private Connection connect = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    public DataBase() throws Exception {
        try {

            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            connect = DriverManager
                    .getConnection("jdbc:derby://localhost:1527/customer");
            PreparedStatement statement1;
            statement1 = connect
                    .prepareStatement("SELECT * from CUSTOMER");

            resultSet = statement1.executeQuery();
            while (resultSet.next()) {
             //   String user = resultSet.getString("name");
                String number = resultSet.getString("number");
              //  System.out.println("User: " + user);
                System.out.println("ID: " + number);
            }
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
            throw e;
        } finally {
            close();
        }

    }

    private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connect != null) {
                connect.close();
            }
        } catch (SQLException e) {

        }
    }

    public static void main(String[] args) throws Exception {
        DataBase db = new DataBase();
    }

}