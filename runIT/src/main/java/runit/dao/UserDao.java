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
        stmt.close();
        rs.close();
        conn.close();
        return a;
    }

    @Override
    public List<User> findAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User saveOrUpdate(User user) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(String key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
