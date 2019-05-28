package dboperator;
import dbmodel.School;
import dbmodel.SchoolClass;
import org.junit.Test;

import java.util.List;


public class DBWriterTest {

    public void printResults(String sqlQuery) {
        ResultPrinter printer = new ResultPrinter();
        DBReader dbReader = new DBReader(ConnectionParameters.URLTESTDB.getParameter());
        List<List<String>>queryResults=dbReader.getQueryResultAndLabels(sqlQuery);
        printer.printQueryResult(queryResults);
    }

    /**
     * executing an insert query
     */
    @Test
    public void insertIntoDB() {
        String sqlQuery = "Select * from schools";
        printResults(sqlQuery); //before insertSchool

        dboperator.DBWriter dbWriter = new dboperator.DBWriter(ConnectionParameters.URLTESTDB.getParameter());
        String insert = "insertSchool into schools values(5,'new school','some address')";
        dbWriter.executeStatement(insert);

        printResults(sqlQuery); //after insertSchool
    }

    /**
     * executing an update query
     */
    @Test
    public void updateDB() {
        String sqlQuery = "Select * from schools";
        printResults(sqlQuery); //before updateSchool

        dboperator.DBWriter dbWriter = new dboperator.DBWriter(ConnectionParameters.URLTESTDB.getParameter());
        String update = "updateSchool schools set name = 'updated name' where id=8";
        dbWriter.executeStatement(update);

        printResults(sqlQuery); //after updateSchool
    }

    /**
     * executing a delete query
     */
    @Test
    public void deleteFromDB() {
        String sqlQuery = "Select * from schools";
        printResults(sqlQuery); //before deleteSchool

        dboperator.DBWriter dbWriter = new dboperator.DBWriter(ConnectionParameters.URLTESTDB.getParameter());
        String delete = "delete from schools where id in(7,8,9,10,11)";
        dbWriter.executeStatement(delete);

        printResults(sqlQuery); //after deleteSchool
    }

    /**
     * insert an object of class School into the corresponding table in the db
     */
    @Test
    public void insertObject() {
        String sqlQuery = "Select * from schools";
        printResults(sqlQuery); //before insertSchool

        School school = new School();
        school.setAddress("Frywald3331");
        school.setName("school in 3331");

        dboperator.DBWriter writer = new dboperator.DBWriter(ConnectionParameters.URLTESTDB.getParameter());
        writer.insertSchool(school);

        printResults(sqlQuery); //after insertSchool
    }

    /**
     * insert an object of class SchoolClass into the corresponding table in the db
     * where a School is given as a parameter, ie. the class must be linked to
     * the specified school
     */
    @Test
    public void insertObjectWithParameter() {
        String sqlQuery = "Select * from schoolClasses";
        printResults(sqlQuery); //before insert
        School school = new School();
        school.setId(1);

       SchoolClass schoolClass = new SchoolClass();
       schoolClass.setCurrentYear(1999);
       schoolClass.setStartYear(1988);
       schoolClass.setProfile("new profile");

        dboperator.DBWriter writer = new dboperator.DBWriter(ConnectionParameters.URLTESTDB.getParameter());
        writer.insertSchoolClass(schoolClass,school);

        printResults(sqlQuery); //after insert
    }

    @Test
    public void updateObject() {
        String sqlQuery = "Select * from schools";
        printResults(sqlQuery); //before updateSchool

        School school = new School();
        school.setId(10);
        school.setAddress("Fcc");
        school.setName("scccc");

        dboperator.DBWriter writer = new dboperator.DBWriter(ConnectionParameters.URLTESTDB.getParameter());
        writer.updateSchool(school);

        printResults(sqlQuery); //after updateSchool
    }
    @Test
    public void queryChecker() {
        long id = 20;
        long school_id = 14;
        String profile = "mat-fiz";
        int currentYear = 1999;
        int startYear = 2010;
        String sqlQuery = "insert into schoolClasses(id,school_id,profile,startYear,CurrentYear) values" +
                "(" + id + "," + school_id+", '" + profile + "', " + startYear + ","+currentYear+")";
        System.out.println(sqlQuery);
    }

    // to run the test below change getMaxID to public
//    @Test
//    public void getMaxIDTest () {
//        dboperator.DBWriter writer = new dboperator.DBWriter(ConnectionParameters.URLTESTDB.getParameter());
//        String Id = "id";
//        String tableName = "schools";
//        Assert.assertEquals(5,writer.getMaxID(new DBReader(ConnectionParameters.URLTESTDB.getParameter()),tableName,Id));
//    }
}
