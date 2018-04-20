/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runit.dao.util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private String databaseAddress;

    public Database(String databaseAddress) throws ClassNotFoundException {
        this.databaseAddress = databaseAddress;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(databaseAddress);
    }

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

    private List<String> sqliteCommands() {
        ArrayList<String> commands = new ArrayList<>();
        commands.add("CREATE TABLE IF NOT EXISTS User (\n"
                + "id integer PRIMARY KEY,\n"
                + "username varchar(32) UNIQUE,\n"
                + "password varchar(32)\n"
                + ");");
        commands.add("CREATE TABLE IF NOT EXISTS Exercise (\n"
                + "id integer PRIMARY KEY,\n"
                + "user_id integer,\n"
                + "time datetime,\n"
                + "duration integer,\n"
                + "distance real,\n"
                + "FOREIGN KEY (user_id) References User(id)\n"
                + ");");
        commands.add("INSERT INTO User (\n"
                + "username, password\n"
                + ") \n"
                + "VALUES (\n"
                + "'test', 'pass'\n"
                + ");");
        commands.add("INSERT INTO Exercise (\n"
                + "user_id, time, duration, distance\n"
                + ") \n"
                + "VALUES (\n"
                + "1, '2018-01-01 10:00:00', 3600, 10.0\n"
                + ");");
        commands.add("INSERT INTO Exercise (\n"
                + "user_id, time, duration, distance\n"
                + ") \n"
                + "VALUES (\n"
                + "1, '2017-12-01 10:00:00', 3600, 5.0\n"
                + ");");
        commands.add("INSERT INTO Exercise (\n"
                + "user_id, time, duration, distance\n"
                + ") \n"
                + "VALUES (\n"
                + "1, '2017-11-01 10:00:00', 3600, 2.5\n"
                + ");");
        return commands;
    }
}
