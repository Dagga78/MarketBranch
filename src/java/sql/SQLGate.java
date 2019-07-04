package sql;

public class SQLGate {

    /**
     * Ouvre la connexion à la BDD
     *
     * @param login
     * @param password
     * @return
     */
    public static SQLConnexion getConnexion(String login, String password) {
        return new SQLConnexion("jdbc:mysql://localhost:3306/?autoReconnect=true&useSSL=false", login, password);
    }
}
