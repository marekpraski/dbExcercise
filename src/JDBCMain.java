import java.sql.*;


public class JDBCMain {
    public static void main(String[] args) {
        executeSQL();
    }

    private static void executeSQL() {
        Connection connection = null;
        Statement statement = null;
        try {
            //initialize sqlite driver
            Class.forName("org.sqlite.JDBC");
            //can establish connection with db only now, after the driver is initialized
            connection = DriverManager.getConnection("jdbc:sqlite:school.db", "", "");
            statement = connection.createStatement();
            String sqlQuery = "SELECT s.name, s.address, sc.profile, sc.startYear FROM schoolClasses sc inner join schools s on s.id=sc.school_id";
            ResultSet rs = statement.executeQuery(sqlQuery);
            //meta data allows to iterate through columns and get info on the retrieved dataset
            ResultSetMetaData rsmd = rs.getMetaData();
//			while (rs.next()) {
//				System.out.println("School name: " + rs.getString("name"));
//				System.out.println("       address: " + rs.getString("address"));
//			}
            int numberOfCols = rsmd.getColumnCount();
            //can only run one loop on one result set, as it only iterates from first to last and then stops, does not reset
            //trying to iterate for the second time returns an exception
            while (rs.next()) {
                for (int i = 1; i <= numberOfCols; i++) {
                    System.out.println(rsmd.getColumnLabel(i) + "  " + rs.getString(i));
                }
            }
            rs.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
