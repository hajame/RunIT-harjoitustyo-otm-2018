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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import runit.dao.util.Database;
import runit.domain.Exercise;
import runit.domain.User;

public class ExerciseDao implements Dao<Exercise, Exercise> {

    private Database database;

    public ExerciseDao(Database database) {
        this.database = database;
    }

    @Override
    public Exercise findOne(Exercise exercise) throws SQLException {
        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM exercise WHERE time = ? AND user_id = ?");
        stmt.setString(1, exercise.time() + ":00");
        stmt.setInt(2, exercise.getUser().getId());

        ResultSet rs = stmt.executeQuery();
        if (!rs.next()) {
            System.out.println("Typerä virhe");
            return null;
        }

        Timestamp timestamp = Timestamp.valueOf(rs.getString("time"));
        Exercise found = new Exercise(timestamp, rs.getInt("duration"), rs.getDouble("distance"));
        found.setId(rs.getInt("id"));

        stmt.close();

        rs.close();

        conn.close();
        return found;
    }

    public List<Exercise> findAllByUser(User user) throws SQLException {

        List<Exercise> exercises = new ArrayList<>();

        try (Connection conn = database.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Exercise, User "
                    + "WHERE username = ? AND user_id = user.id");
            stmt.setString(1, user.getUsername());
            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                Timestamp timestamp = Timestamp.valueOf(result.getString("time"));
                Exercise a = new Exercise(timestamp, result.getInt("duration"), result.getDouble("distance"));
                a.setUser(user);
                a.setId(result.getInt("id"));
                exercises.add(a);

            }
        }

        return exercises;

    }

    @Override
    public Exercise saveOrUpdate(Exercise exercise) throws SQLException {

        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

//        if (findOne(user.getUsername()) == null) {
//            User a = save(user);
//            return a;
//        }
//
//        return update(user);
    }

    public Exercise save(Exercise exercise) throws SQLException {
        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO Exercise (user_id, time, duration, distance) values (?, ?, ?, ?)");
        stmt.setInt(1, exercise.getUser().getId());
        stmt.setString(2, exercise.time() + ":00");
        stmt.setInt(3, exercise.getDuration());
        stmt.setDouble(4, exercise.getDistance());
        stmt.executeUpdate();
        stmt.close();
        conn.close();

        Exercise newExercise = findOne(exercise);

        return newExercise;
    }

    public Exercise update(Exercise exercise) throws SQLException {

        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

//        Connection conn = database.getConnection();
//        PreparedStatement stmt = conn.prepareStatement("UPDATE USER SET password = ? WHERE username = ?");
//        stmt.setString(1, user.getPassword());
//        stmt.setString(2, user.getUsername());
//        stmt.executeUpdate();
//        stmt.close();
//        conn.close();
//
//        User updatedUser = findOne(user.getUsername());
//        return updatedUser;
    }

    @Override
    public void delete(Exercise key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Exercise> findAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
