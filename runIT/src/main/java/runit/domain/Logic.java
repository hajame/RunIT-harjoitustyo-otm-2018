package runit.domain;

import java.io.File;
import java.sql.Timestamp;
import java.util.List;
import runit.dao.ExerciseDao;
import runit.dao.UserDao;
import runit.dao.util.Database;

/**
 * Class responsible for application logic.
 */
public class Logic {

    private User user;
    private Database database;

    /**
     * Constructs a new Logic object. Create a Database object pointing a database.
     */
    public Logic() {
        try {
            File file = new File("database.db");
            database = new Database("jdbc:sqlite:" + file.getAbsolutePath());
            database.init();
        } catch (Exception e) {
            System.out.println("Incorrect database address. --- " + e);
        }
    }

    /**
     * User's exercise history.
     *
     * @return List of Exercises by User.
     */
    public List<Exercise> getHistory() {

        ExerciseDao dao = new ExerciseDao(database);

        try {
            List<Exercise> exercises = dao.findAllByUser(user);
            user.setHistory(exercises);
            return exercises;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Searches for a user from the database. If found, creates a new User
     * object and sets it as a private attribute.
     *
     * @param username Unique username
     * @param password Password of the user
     * @return String "Login successful", "Wrong password", "User not found" or
     * "Error: *message*"
     */
    public String loginUser(String username, String password) {
        UserDao dao = new UserDao(database);
        try {
            User user = dao.findOne(username);
            if (user == null) {
                return "User not found";
            }
            if (user.getPassword().equals(password)) {
                this.user = user;
                return "Login successful";
            } else {
                return "Wrong password";
            }
        } catch (Exception e) {
            return "Error: " + e.toString();
        }
    }

    /**
     * Logs the user out. Clears the user object.
     */
    public void logout() {
        this.user = null;
    }

    /**
     * Creates a new user and inserts it to the database.
     *
     * @param username Unique username
     * @param password Password of the user
     * @return "Login successful" or "Error: + msg"
     */
    public String signupUser(String username, String password) {

        UserDao dao = new UserDao(database);

        try {
            User user = dao.saveOrUpdate(new User(username, password));
            this.user = user;
            return "Login successful";
        } catch (Exception e) {
            return "Error: " + e.toString();
        }
    }

    public User getUser() {
        return user;
    }

    /**
     * Creates a Timestamp object from a String.
     *
     * @param string in format "YYYY-MM-DD HH:MM"
     * @return Timestamp object
     */
    public Timestamp createTimestamp(String string) {
        Timestamp timestamp = Timestamp.valueOf(string + ":00.0");
        return timestamp;
    }

    /**
     * Creates a duration in seconds.
     *
     * @param duration String in format "HH:MM:SS"
     * @return duration as seconds
     */
    public Integer createDuration(String duration) {
        int hours;
        int minutes;
        int seconds;

        try {
            hours = Integer.parseInt(duration.substring(0, 2));
            minutes = Integer.parseInt(duration.substring(3, 5));
            seconds = Integer.parseInt(duration.substring(6));
        } catch (Exception e) {
            System.out.println("Wrong duration format");
            return -1;
        }
        return hours * 60 * 60 + minutes * 60 + seconds;
    }

    /**
     * Inserts an Exercise to the database and User's history.
     *
     * @param exercise Exercise object (no id or User attributes needed)
     * @return Exercise object with User and id attributes.
     */
    public Exercise addExercise(Exercise exercise) {
        exercise.setUser(user);
        user.addExercise(exercise);

        ExerciseDao dao = new ExerciseDao(database);
        try {
            Exercise a = dao.save(exercise);
            return a;
        } catch (Exception e) {
            System.out.println("Failed to add exercise.");
            return null;
        }
    }

    /**
     * Deletes an Exercise from the database and the User's history.
     *
     * @param exercise complete Exercise object.
     */
    public void deleteExercise(Exercise exercise) {
        ExerciseDao dao = new ExerciseDao(database);
        try {
            dao.delete(exercise);
            user.setHistory(dao.findAllByUser(user));
        } catch (Exception e) {
            System.out.println("Failed to delete exercise.");
        }

    }

    /**
     * Deletes a User from the database.
     *
     * @param username user's username
     */
    public void deleteUser(String username) {
        UserDao dao = new UserDao(database);
        try {
            dao.delete(username);
        } catch (Exception e) {
            System.out.println("Failed to delete user.");
        }
    }
}
