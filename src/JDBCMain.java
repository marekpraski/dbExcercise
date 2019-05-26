import java.util.List;

public class JDBCMain {
    public static void main(String[] args) {

        String sqlQuery = "SELECT s.name, s.address, sc.profile, sc.startYear FROM schoolClasses sc inner join schools s on s.id=sc.school_id";
        DBReader dbReader = new DBReader("jdbc:sqlite:school.db");
        List<List<String>> queryResults = dbReader.getQueryResult(sqlQuery);
        ResultPrinter printer = new ResultPrinter();
       // printer.printQueryResult(queryResults);

        sqlQuery = "Select * from schools";
        queryResults=dbReader.getQueryResultAndLabels(sqlQuery);
        printer.printQueryResult(queryResults);

        DBWriter dbWriter = new DBWriter("jdbc:sqlite:school.db");
//        String insert = "insert into schools values(3,'new school','some address')";
//        dbWriter.executeStatement(insert);
//        String update = "update schools set name = 'updated name' where id=3";
//        dbWriter.executeStatement(update);
        String delete = "delete from schools where id=3";
        dbWriter.executeStatement(delete);

        sqlQuery = "Select * from schools";
        queryResults=dbReader.getQueryResultAndLabels(sqlQuery);
        printer.printQueryResult(queryResults);

    }
}
