package dboperator;

import dbmodel.School;
import dboperator.DBReader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBWriter {
    private Connection connection;
    private Statement statement;

    public DBWriter(String url) {
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

    /**
     *
     * @param reader
     * @param tableName name of the table to read from
     * @param Id name of the id field in the table
     * @return
     */
    private int getMaxID(DBReader reader, String tableName, String Id) {
        String query = "select max(" + Id +") "+"from "+tableName;
        return Integer.parseInt(reader.getSingleQueryResult(query));
    }

    public void insert(School school) {
        int id = getMaxID(new DBReader(connection),"schools", "id")+1;
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
