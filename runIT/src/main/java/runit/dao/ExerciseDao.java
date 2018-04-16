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

public class ExerciseDao implements Dao<Exercise, Integer> {

    private Database database;

    public ExerciseDao(Database database) {
        this.database = database;
    }

    @Override
    public Exercise findOne(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
                    exercises.add(new Exercise(timestamp, result.getInt("duration"), result.getDouble("distance")));
                }
        }

        return exercises;

    }

    @Override
    public Exercise saveOrUpdate(Exercise object) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Exercise> findAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
