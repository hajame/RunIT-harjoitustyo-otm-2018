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

/**
 * DAO (Data Access Object) class for interacting with database's Exercise
 * table.
 */
public class ExerciseDao implements Dao<Exercise, Exercise> {

    private Database database;

    public ExerciseDao(Database database) {
        this.database = database;
    }

    /**
     * Finds an exercise from the database and if found returns an Exercise
     * object.
     *
     * @param exercise User id required
     * @return Exercise or null if not found.
     * @throws SQLException
     */
    @Override
    public Exercise findOne(Exercise exercise) throws SQLException {
        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM exercise WHERE time = ? AND user_id = ?");
        stmt.setString(1, exercise.time() + ":00");
        stmt.setInt(2, exercise.getUser().getId());

        ResultSet rs = stmt.executeQuery();
        if (!rs.next()) {
            return null;
        }

        Timestamp timestamp = Timestamp.valueOf(rs.getString("time"));
        Exercise found = new Exercise(timestamp, rs.getInt("duration"), rs.getDouble("distance"));
        found.setUser(exercise.getUser());
        found.setId(rs.getInt("id"));

        stmt.close();
        rs.close();
        conn.close();
        return found;
    }

    /**
     * Retrieves the user's exercise history.
     *
     * @param user User object complete with id
     * @return List of exercise objects.
     * @throws SQLException
     */
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

    /**
     * Saves or updates an Exercise row in the database.
     */
    @Override
    public Exercise saveOrUpdate(Exercise exercise) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Saves an Exercise in the database.
     *
     * @param exercise must contain user complete with id
     * @return Exercise object complete with an exercise id.
     * @throws SQLException
     */
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

    /**
     * Updates an Exercise in the database.
     *
     * @param exercise
     * @return updated exercise
     * @throws SQLException
     */
    public Exercise update(Exercise exercise) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Deletes an exercise from the database and user history.
     *
     * @param exercise must contain user with id
     * @throws SQLException
     */
    @Override
    public void delete(Exercise exercise) throws SQLException {
        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM Exercise WHERE id = ?");
        stmt.setInt(1, exercise.getId());
        stmt.executeUpdate();
        stmt.close();
        conn.close();
    }

    /**
     * @return List of all exercises found in the database.
     * @throws SQLException
     */
    @Override
    public List<Exercise> findAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
