package runit.dao.util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Offers a Database object for connecting to a SQLite database.
 */
public class Database {

    private String databaseAddress;

    /**
     * Constructor creates a Database object.
     *
     * @param databaseAddress path of the database
     * @throws java.lang.ClassNotFoundException
     */
    public Database(String databaseAddress) throws ClassNotFoundException {
        this.databaseAddress = databaseAddress;
    }

    /**
     * @return connection to database
     * @throws java.sql.SQLException
     */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(databaseAddress);
    }

    /**
     * Creates new "User" and "Exercise" SQL tables.
     */
    public void init() {
        List<String> commands = sqliteCommands();

        // "try with resources" closes the resource automatically
        try (Connection conn = getConnection()) {
            Statement st = conn.createStatement();

            for (String lause : commands) {
                st.executeUpdate(lause);
            }

        } catch (Throwable t) {
        }
    }
    
    /**
     * Creates SQL commands for new "User" and "Exercise" tables, 
     * inserts a test user and test exercises.
     * 
     * @return list of SQL commands in String format
     */    
    private List<String> sqliteCommands() {
        ArrayList<String> commands = new ArrayList<>();
        commands.add("CREATE TABLE IF NOT EXISTS User (id integer PRIMARY KEY, "
                + "username varchar(32) UNIQUE, password varchar(32));");
        commands.add("CREATE TABLE IF NOT EXISTS Exercise (id integer PRIMARY KEY, "
                + "user_id integer, time datetime, duration integer, distance real, "
                + "FOREIGN KEY (user_id) References User(id));");
        commands.add("INSERT INTO User (username, password) VALUES ('test', 'pass');");
        commands.add("INSERT INTO Exercise (user_id, time, duration, distance) "
                + "VALUES (1, '2018-01-01 10:00:00', 3600, 10.0);");
        commands.add("INSERT INTO Exercise (user_id, time, duration, distance) "
                + "VALUES (1, '2017-12-01 10:00:00', 3600, 5.0);");
        commands.add("INSERT INTO Exercise (user_id, time, duration, distance) "
                + "VALUES (1, '2017-11-01 10:00:00', 3600, 2.5);");
        return commands;
    }
}
