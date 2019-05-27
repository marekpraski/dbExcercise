package main;

import dbmodel.School;
import dboperator.*;

import java.util.List;

public class JDBCMain {
    public static void main(String[] args) {

        String sqlQuery = "SELECT s.name, s.address, sc.profile, sc.startYear FROM schoolClasses sc inner join schools s on s.id=sc.school_id";
        DBReader dbReader = new DBReader(ConnectionParameters.URL.getParameter());
        List<List<String>> queryResults = dbReader.getQueryResult(sqlQuery);
        ResultPrinter printer = new ResultPrinter();
        printer.printQueryResult(queryResults);


    }
}
