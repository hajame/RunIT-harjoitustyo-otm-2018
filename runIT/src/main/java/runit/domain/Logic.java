/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runit.domain;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
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
            database = new Database("jdbc:sqlite:" +file.getAbsolutePath());
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

    public Exercise addExercise(Exercise exercise) {
        exercise.setUser(user);
        user.addExercise(exercise);

        ExerciseDao dao = new ExerciseDao(database);
        try {
            Exercise a = dao.save(exercise);
            System.out.println("Onnistui");
            return a;
        } catch (Exception e) {
            System.out.println("Ei onnistunut.");
            return null;
        }

    }

}
