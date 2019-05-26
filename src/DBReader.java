import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBReader {
    private Connection connection;
    private Statement statement;

    public DBReader(String url) {
        try {
            //initialize sqlite driver
            Class.forName("org.sqlite.JDBC");
            //can establish connection with db only now, after the driver is initialized
            connection = DriverManager.getConnection(url);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }
    public List<List<String>> getQueryResult (String sqlQuery) {
        List<List<String>> queryResult = new ArrayList();
        ResultSet resultSet=null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlQuery);
            //meta data allows to iterate through columns and get info on the retrieved dataset
            ResultSetMetaData rsmd = resultSet.getMetaData();
            int numberOfColumns = rsmd.getColumnCount();
            while (resultSet.next()) {
                ArrayList row = new ArrayList();
                for (int i=1; i<=numberOfColumns; i++) {
                    row.add(resultSet.getString(i));
                }
                queryResult.add(row);
            }
            resultSet.close();
            statement.close();
            //connection.close();

        } catch (SQLException e) {
        e.printStackTrace();
    }

        return queryResult;
    }

    public List<List<String>> getQueryResultAndLabels (String sqlQuery) {
        List<List<String>> queryResult = new ArrayList();
        ResultSet resultSet=null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlQuery);
            //meta data allows to iterate through columns and get info on the retrieved dataset
            ResultSetMetaData rsmd = resultSet.getMetaData();
            int numberOfColumns = rsmd.getColumnCount();
            while (resultSet.next()) {
                ArrayList row = new ArrayList();
                for (int i=1; i<=numberOfColumns; i++) {
                    row.add("("+rsmd.getColumnLabel(i) + ") "+ resultSet.getString(i));
                }
                queryResult.add(row);
            }
            resultSet.close();
            statement.close();
            //connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return queryResult;
    }

}
