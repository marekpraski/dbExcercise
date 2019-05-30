package dboperator;
import org.junit.Test;

import java.util.List;

public class DBReaderTest {
    @Test
    public void readQuery(){
        String sqlQuery = "SELECT s.name, s.address, sc.profile, sc.startYear FROM schoolClasses sc inner join schools s on s.id=sc.school_id";
        DBReader dbReader = new DBReader(ConnectionParameters.URLTESTDB.getParameter());
        List<List<String>> queryResults = dbReader.getQueryResult(sqlQuery);
        ResultPrinter printer = new ResultPrinter();
        printer.printQueryResult(queryResults);
    }
}
