package dboperator;

import java.util.List;

public class ResultPrinter {
    private List<List<String>> queryResult;

    public ResultPrinter(List<List<String>> queryResult) {
        this.queryResult = queryResult;
    }
    public ResultPrinter() {}

    public void printQueryResult() {
        for (List<String> row : queryResult) {
            printRow(row);
            System.out.println();
        }
    }

    public void printQueryResult(List<List<String>> queryResult) {
        for (List<String> row : queryResult) {
            printRow(row);
            System.out.println();
        }
    }

    private void printRow(List<String> row) {
        for (String entry : row) {
            System.out.print(entry + "  ");
        }
    }
}
