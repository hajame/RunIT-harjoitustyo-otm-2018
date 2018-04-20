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

        // "try with resources" sulkee resurssin automaattisesti lopuksi
        try (Connection conn = getConnection()) {
            Statement st = conn.createStatement();

            // suoritetaan komennot
            for (String lause : commands) {
                System.out.println("Running command >> " + lause);
                st.executeUpdate(lause);
            }

        } catch (Throwable t) {
            // if exists, no commands are
        }
    }

    private List<String> sqliteCommands() {
        ArrayList<String> commands = new ArrayList<>();

        // tietokantataulujen luomiseen tarvittavat komennot suoritusjärjestyksessä
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
        return commands;
    }
}
