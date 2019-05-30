package dboperator;

import dbmodel.School;
import dbmodel.SchoolClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBWriter {
    private Connection connection;
    private Statement statement;

    /**
     *
     * @param url
     */
    public DBWriter(String url) {
        try {
            Class.forName(dboperator.ConnectionParameters.FORNAME.getParameter());
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
     * @param tableName name of the table to read from
     * @param Id        name of the id field in the table
     * @return
     */
    private int getMaxID(String tableName, String Id) {
        DBReader reader = new DBReader(connection);
        String query = "select max(" + Id + ") " + "from " + tableName;
        return Integer.parseInt(reader.getSingleQueryResult(query));
    }

    public void insertSchool(School school) {
        //new insertSchool must have a unique primary key id
        //the parameters' names, ie. id, name and address MUST correspond to the labels in the appropriate db table
        long id = getMaxID("schools", "id") + 1;
        String name = school.getName();
        String address = school.getAddress();
        String sqlQuery = "insert into schools values(" + id + " ,'" + name + "', '" + address + "')";
        executeStatement(sqlQuery);
    }

    public void deleteSchool(School school) {
        long id = school.getId();
        String sqlQuery = "delete from schools where id=" + id;
        executeStatement(sqlQuery);
    }

    public void updateSchool(School school) {
        long id = school.getId();
        String name = school.getName();
        String address = school.getAddress();
        String sqlQuery = "update schools set name='" + name + "', address='" + address + "' where id=" + id;
        executeStatement(sqlQuery);
    }

    public void insertSchoolClass(SchoolClass schoolClass, School school) {
        //new insertSchool must have a unique primary key id
        //the parameters' names, ie. id, name and address MUST correspond to the labels in the appropriate db table
        long id = getMaxID("schoolClasses", "id") + 1;
        long school_id = school.getId();
        String profile = schoolClass.getProfile();
        int currentYear = schoolClass.getCurrentYear();
        int startYear = schoolClass.getStartYear();
        String sqlQuery = "insert into schoolClasses(id,school_id,profile,startYear,CurrentYear) values" +
                "(" + id + "," + school_id+", '" + profile + "', " + startYear + ","+currentYear+")";
        executeStatement(sqlQuery);
    }
}
