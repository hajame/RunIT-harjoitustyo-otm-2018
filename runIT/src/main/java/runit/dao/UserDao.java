package runit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import runit.dao.util.Database;
import runit.domain.User;

/**
 * DAO (Data Access Object) class for interacting with database's User table.
 */
public class UserDao implements Dao<User, String> {

    private Database database;

    public UserDao(Database database) {
        this.database = database;
    }

    /**
     * Finds a user from the database. If found, returns a User object.
     *
     * @param username
     * @return User or null if not found.
     * @throws SQLException
     */
    @Override
    public User findOne(String username) throws SQLException {
        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM User WHERE username = ?");
        stmt.setString(1, username);

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

    /**
     * Not supported in this class.
     */
    @Override
    public List<User> findAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported in this class.");
    }

    /**
     * Saves or Updates a User in the database.
     * 
     * @param user User object with or without id.
     * @return User object with database id.
     * @throws SQLException 
     */
    @Override
    public User saveOrUpdate(User user) throws SQLException {

        User foundUser = findOne(user.getUsername());
        
        if (foundUser == null) {
            User a = save(user);
            return a;
        }

        return foundUser;
    }

    /**
     * Saves a User in the database.
     *
     * @param user User object.
     * @return User object complete with an id.
     * @throws SQLException
     */
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

    /**
     * Not supported in this class.
     */
    public User update(User user) throws SQLException {
        throw new UnsupportedOperationException("Not supported in this class.");
    }

    /**
     * Not supported in this class.
     */
    public void delete(String username) throws SQLException {
        throw new UnsupportedOperationException("Not supported in this class.");
    }

}
