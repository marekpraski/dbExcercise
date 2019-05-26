import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBWriter {
    private Connection connection;
    private Statement statement;

    DBWriter(String url) {
        try {
            Class.forName(ConnectionParameters.FORNAME.getParameter());
            connection = DriverManager.getConnection(url);
            statement = connection.createStatement();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void executeStatement(String sqlStatement) {
        try {
            statement.executeUpdate(sqlStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    private int getMaxID(String tableName, String Id) {
        DBReader reader = new DBReader(ConnectionParameters.URL.getParameter());
        String query = "select max(" + Id +") "+"from "+tableName;
        return Integer.parseInt(reader.getSingleQueryResult(query));
    }

    public void insert(School school) {
        int id = getMaxID("schools", "id")+1;
        String name = school.getName();
        String address = school.getAddress();
        String sqlStatement = "insert into schools values(" + id + " ,'" + name + "', '" + address + "')";
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sqlStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
