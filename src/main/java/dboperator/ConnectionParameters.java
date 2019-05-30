package dboperator;

public enum ConnectionParameters {
    URL("jdbc:sqlite:school.db"),
    URLTESTDB("jdbc:sqlite:school_test.db"),
    FORNAME("org.sqlite.JDBC"),
    LOGIN(""),
    PASSWORD("");
    private String parameter;

    ConnectionParameters(String parameter) {
        this.parameter=parameter;
    }

    ;

    public String getParameter() {
        return parameter;
    }
}
