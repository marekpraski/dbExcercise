package dboperator;

import dbmodel.School;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBReader {
    private Connection connection;
    private Statement statement;

    public DBReader(String url) {
        try {
            //initialize sqlite driver
            Class.forName(ConnectionParameters.FORNAME.getParameter());
            //can establish connection with db only now, after the driver is initialized
            connection = DriverManager.getConnection(url);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public DBReader(Connection connection) {
            this.connection=connection;
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

    /**
     * returns the first item of the result set
     * meant to be used when the result set is known to contain only one item
     * @param sqlQuery
     * @return
     */
    public String getSingleQueryResult (String sqlQuery) {
        List<List<String>> queryResult = getQueryResult(sqlQuery);
        List<String> row = queryResult.get(0);
        return row.get(0);
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
    public School getSchool(int id) {
        String sqlQuery = "select * from schools where id ="+id;
        List<List<String>> queryResult = getQueryResult(sqlQuery);
        List<String> row = queryResult.get(0);
        School school = new School();
        school.setId(id);
        school.setName(row.get(1));
        school.setAddress(row.get(2));
        return school;
    }

}
