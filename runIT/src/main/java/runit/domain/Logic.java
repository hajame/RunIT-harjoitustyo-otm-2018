/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runit.domain;

import java.io.File;
import java.sql.Timestamp;
import java.util.List;
import runit.dao.ExerciseDao;
import runit.dao.UserDao;
import runit.dao.util.Database;

public class Logic {

    private User user;
    private Database database;

    public Logic() {
        try {
            File file = new File("database.db");
            database = new Database("jdbc:sqlite:" + file.getAbsolutePath());
            database.init();
        } catch (Exception e) {
            System.out.println("Incorrect database address. --- " + e);
        }
    }

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

    public String loginUser(String name, String pass) {

        UserDao dao = new UserDao(database);

        try {
            User user = dao.findOne(name);
            if (user == null) {
                return "User not found";
            }
            if (user.getPassword().equals(pass)) {
                this.user = user;
                return "Login successful";
            } else {
                return "Wrong password";
            }
        } catch (Exception e) {
            return "Error: " + e.toString();
        }
    }

    public void logout() {
        this.user = null;
    }

    public String signupUser(String name, String pass) {

        UserDao dao = new UserDao(database);

        try {
            User user = dao.saveOrUpdate(new User(name, pass));
            this.user = user;
            return "Login successful";
        } catch (Exception e) {
            return "Error: " + e.toString();
        }
    }

    public User getUser() {
        return user;
    }

    public Timestamp createTimestamp(String string) {
        Timestamp timestamp = Timestamp.valueOf(string + ":00.0");
        return timestamp;
    }

    public Integer createDuration(String time) {
        int hours;
        int minutes;
        int seconds;

        try {
            hours = Integer.parseInt(time.substring(0, 2));
            minutes = Integer.parseInt(time.substring(3, 5));
            seconds = Integer.parseInt(time.substring(6));
        } catch (Exception e) {
            System.out.println("Wrong duration format");
            return -1;
        }
        return hours * 60 * 60 + minutes * 60 + seconds;
    }

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

    public void deleteExercise(Exercise exercise) {
        ExerciseDao dao = new ExerciseDao(database);
        try {
            dao.delete(exercise);
            user.setHistory(dao.findAllByUser(user));
        } catch (Exception e) {
            System.out.println("Failed to delete exercise.");
        }

    }

    public void deleteUser(String username) {
        UserDao dao = new UserDao(database);
        try {
            dao.delete(username);
        } catch (Exception e) {
            System.out.println("Failed to delete user.");
        }

    }

}
