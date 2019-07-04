package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class SQLConnexion {

    private Connection connexion = null;
    private Statement statement = null;

    /**
     * Initialize the SQL connexion with: <br>
     * - The database URL <br>
     * - The user name <br>
     * - The user password
     *
     * @param url Database URL
     * @param user User name
     * @param password User password
     */
    public SQLConnexion(String url, String user, String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connexion = DriverManager.getConnection(url, user, password);
            statement = connexion.createStatement();
            connexion.setAutoCommit(false);
        } catch (Exception e) {
            System.out.println("sql.SQLConnexion.<init>()" + e);
        }
    }

    /**
     * Execute an SQL Update. For Example <br>
     * INSERT
     *
     * @param request Update to execute
     * @return -1 if the request fail
     */
    public int executeUpdate(String request) {
        int result;
        try {
            result = statement.executeUpdate(request);
        } catch (Exception e) {
            System.out.println("sql.SQLConnexion.executeUpdate()" + e);
            return -1;
        }
        return result;
    }

    /**
     * Execute an SQL Query. For Example <br>
     * SELECT
     *
     * @param request Query to execute
     * @return Result of the Query
     */
    public ResultSet executeQuery(String request) {
        ResultSet result;
        try {
            result = statement.executeQuery(request);
        } catch (Exception e) {
            System.out.println("sql.SQLConnexion.executeQuery()" + e);
            return null;
        }
        return result;
    }

    /**
     * Execute an SQL Query with boolean of good treatment. For Example <br>
     * SELECT
     *
     * @param request Query to execute
     * @return Result of the Query
     */
    public boolean executeQueryBoolean(String request) {
        try {
            return statement.execute(request);
        } catch (Exception e) {
            System.out.println("sql.SQLConnexion.executeQueryBoolean()" + e);
            return false;
        }
    }

    public boolean commit() {
        try {
            connexion.commit();
            return true;
        } catch (SQLException e) {
            System.out.println("sql.SQLConnexion.commit()" + e);
            return false;
        }
    }

    public boolean rollback() {
        try {
            connexion.rollback();
            return true;
        } catch (SQLException e) {
            System.out.println("sql.SQLConnexion.rollback()" + e);
            return false;
        }
    }
}
