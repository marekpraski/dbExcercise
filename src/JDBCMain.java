import java.util.List;

public class JDBCMain {
    public static void main(String[] args) {

        String sqlQuery = "SELECT s.name, s.address, sc.profile, sc.startYear FROM schoolClasses sc inner join schools s on s.id=sc.school_id";
        DBReader dbReader = new DBReader();
        List<List<String>> queryResults = dbReader.getQueryResult(sqlQuery);
        ResultPrinter printer = new ResultPrinter(queryResults);
        printer.printQueryResult();
    }
}
