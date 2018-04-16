/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runit.domain;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import runit.dao.ExerciseDao;
import runit.dao.UserDao;
import runit.dao.util.Database;

public class Logic {

    private User user;
    private Database database;

    public Logic() {
        try {
            File file = new File("db", "database.db");
            database = new Database("jdbc:sqlite:"+file.getAbsolutePath());
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

    public boolean setUser(String name, String pass) {

        UserDao dao = new UserDao(database);

        try {
            User user = dao.findOne(name);
            if (user == null) {
                System.out.println("No such user.");
                return false;
            }
            if (user.getPassword().equals(pass)) {                
                this.user = user;
                return true;
            } else {
                System.out.println("Wrong password");
                return false;
            }
        } catch (Exception e) {
            return false;
        }

    }

    public User getUser() {
        return user;
    }

    public void addExercise(Exercise exercise) {
        user.addExercise(exercise);
    }

}
