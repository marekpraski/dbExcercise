package dboperator;
import dbmodel.School;
import org.junit.Test;
import org.junit.Assert;

import java.util.List;


public class DBWriterTest {

    @Test
    public void insertIntoDB() {
        String sqlQuery = "Select * from schools";
        ResultPrinter printer = new ResultPrinter();
        DBReader dbReader = new DBReader(ConnectionParameters.URLTESTDB.getParameter());

        List<List<String>>queryResults=dbReader.getQueryResultAndLabels(sqlQuery);
        printer.printQueryResult(queryResults);

        dboperator.DBWriter dbWriter = new dboperator.DBWriter(ConnectionParameters.URLTESTDB.getParameter());
        String insert = "insert into schools values(5,'new school','some address')";
        dbWriter.executeStatement(insert);

        sqlQuery = "Select * from schools";
        queryResults=dbReader.getQueryResultAndLabels(sqlQuery);
        printer.printQueryResult(queryResults);
    }

    @Test
    public void updateDB() {
        String sqlQuery = "Select * from schools";
        ResultPrinter printer = new ResultPrinter();
        DBReader dbReader = new DBReader(ConnectionParameters.URLTESTDB.getParameter());

        List<List<String>>queryResults=dbReader.getQueryResultAndLabels(sqlQuery);
        printer.printQueryResult(queryResults);

        dboperator.DBWriter dbWriter = new dboperator.DBWriter(ConnectionParameters.URLTESTDB.getParameter());
        String update = "update schools set name = 'updated name' where id=3";
        dbWriter.executeStatement(update);

        sqlQuery = "Select * from schools";
        queryResults=dbReader.getQueryResultAndLabels(sqlQuery);
        printer.printQueryResult(queryResults);
    }

    @Test
    public void deleteFromDB() {
        String sqlQuery = "Select * from schools";
        ResultPrinter printer = new ResultPrinter();
        DBReader dbReader = new DBReader(ConnectionParameters.URLTESTDB.getParameter());

        List<List<String>>queryResults=dbReader.getQueryResultAndLabels(sqlQuery);
        printer.printQueryResult(queryResults);

        dboperator.DBWriter dbWriter = new dboperator.DBWriter(ConnectionParameters.URLTESTDB.getParameter());
        String delete = "delete from schools where id=6";
        dbWriter.executeStatement(delete);

        sqlQuery = "Select * from schools";
        queryResults=dbReader.getQueryResultAndLabels(sqlQuery);
        printer.printQueryResult(queryResults);
    }

    @Test
    public void insertSchoolObject() {
        String sqlQuery = "Select * from schools";
        ResultPrinter printer = new ResultPrinter();
        DBReader dbReader = new DBReader(ConnectionParameters.URLTESTDB.getParameter());

        List<List<String>>queryResults=dbReader.getQueryResultAndLabels(sqlQuery);
        printer.printQueryResult(queryResults);

        School school = new School();
        school.setAddress("Frywald31");
        school.setName("school in 31");

        dboperator.DBWriter writer = new dboperator.DBWriter(ConnectionParameters.URLTESTDB.getParameter());
        writer.insert(school);

        sqlQuery = "Select * from schools";
        queryResults=dbReader.getQueryResultAndLabels(sqlQuery);
        printer.printQueryResult(queryResults);
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
