/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import runit.dao.util.Database;
import runit.domain.User;

public class UserDao implements Dao<User, String> {

    private Database database;

    public UserDao(Database database) {
        this.database = database;
    }

    @Override
    public User findOne(String key) throws SQLException {
        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM User WHERE username = ?");
        stmt.setString(1, key);

        ResultSet rs = stmt.executeQuery();
        if (!rs.next()) {
            return null;
        }

        User a = new User(rs.getString("username"), rs.getString("password"));
        a.setId(rs.getInt("id"));
        stmt.close();
        rs.close();
        conn.close();
        return a;
    }

    @Override
    public List<User> findAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public User saveOrUpdate(User user) throws SQLException {

        if (findOne(user.getUsername()) == null) {
            User a = save(user);
            return a;
        }

        return update(user);
    }

    public User save(User user) throws SQLException {
        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO User (username, password) values (?, ?)");
        stmt.setString(1, user.getUsername());
        stmt.setString(2, user.getPassword());
        stmt.executeUpdate();
        stmt.close();
        conn.close();

        User newUser = findOne(user.getUsername());
        return newUser;
    }

    public User update(User user) throws SQLException {
        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("UPDATE USER SET password = ? WHERE username = ?");
        stmt.setString(1, user.getPassword());
        stmt.setString(2, user.getUsername());
        stmt.executeUpdate();
        stmt.close();
        conn.close();

        User updatedUser = findOne(user.getUsername());
        return updatedUser;
    }

    @Override
    public void delete(String username) throws SQLException {
        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM User WHERE username = ?");
        stmt.setString(1, username);
        stmt.executeUpdate();
        stmt.close();
        conn.close();

    }

}
