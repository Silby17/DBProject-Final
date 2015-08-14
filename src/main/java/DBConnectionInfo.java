/**
 * Yossi Silberhaft 210028924 89-281-04
 * Daniel Blinick WN654676 89-281-04
 * Binyamin Greenberg 200220671 89-281-04
 * Steven Lapp 204785240 89-281-05
 */


public class DBConnectionInfo {
    //Deceleration of variables
    private String dbURL;
    private String userName;
    private String password;

    public DBConnectionInfo(String userName, String password, String url) {
        this.dbURL = url;
        this.userName = userName;
        this.password = password;
    }

    //Gets the URL of the Database
    public String getURL() {
        return this.dbURL;
    }

    //Gets the Username to the database
    public String getUserName() {
        return this.userName;
    }

    //Gets the password of the Database
    public String getPassword() {
        return this.password;
    }
}