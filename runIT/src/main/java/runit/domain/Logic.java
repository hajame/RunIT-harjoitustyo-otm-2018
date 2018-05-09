package runit.domain;

import java.sql.Timestamp;
import java.util.List;
import runit.dao.*;

/**
 * Class responsible for application logic. Accesses user and exercise data via
 * DAO-classes. Stores statistical data in Statistics object.
 */
public class Logic {

    private User user;
    private Statistics statistics;
    private UserDao userDao;
    private ExerciseDao exerciseDao;

    public Logic(UserDao userDao, ExerciseDao exerciseDao) {
        this.statistics = new Statistics();
        this.userDao = userDao;
        this.exerciseDao = exerciseDao;
    }

    public Statistics getStatistics() {
        return statistics;
    }

    /**
     * Fetches user's exercise history from the database.
     *
     * @return List of Exercises by User.
     */
    public List<Exercise> getHistory() {
        try {
            List<Exercise> exercises = exerciseDao.findAllByUser(user);
            user.setHistory(exercises);
            statistics.calculate(exercises);
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
        try {
            User user = userDao.findOne(username);
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
        User newUser = null;
        
        try {
            newUser = userDao.saveOrUpdate(new User(username, password));
        } catch (Exception e) {
            return "Error: " + e.toString();
        }
        if (newUser.getPassword().equals(password)) {
            newUser = user;
            return "Login successful";
        } else {
            return "Username taken";
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
        try {
            Timestamp timestamp = Timestamp.valueOf(string + ":00.0");
            return timestamp;
        } catch (Exception e) {
            System.out.println("Wrong time format");
        }
        return null;
    }

    /**
     * Creates a duration in seconds.
     *
     * @param duration String in format "HH:MM:SS"
     * @return duration as seconds
     */
    public Integer createDuration(String duration) {
        int hours, minutes, seconds;

        try {
            hours = Integer.parseInt(duration.substring(0, 2));
            minutes = Integer.parseInt(duration.substring(3, 5));
            seconds = Integer.parseInt(duration.substring(6));
        } catch (Exception e) {
            System.out.println("Wrong duration format");
            return -1;
        }

        if (hours < 0 || minutes > 59 || seconds > 59) {
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
        if (exercise == null) {
            return null;
        }

        exercise.setUser(user);
        try {
            Exercise a = exerciseDao.save(exercise);
            user.addExercise(a);
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
        try {
            exerciseDao.delete(exercise);
            user.setHistory(exerciseDao.findAllByUser(user));
        } catch (Exception e) {
            System.out.println("Failed to delete exercise.");
        }
    }

    /**
     * Deletes a User from the database.
     *
     * @param username
     */
    public void deleteUser(String username) {
        try {
            userDao.delete(username);
        } catch (Exception e) {
            System.out.println("Failed to delete user.");
        }
    }
}
