/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runit.domain;

import java.util.*;

/**
 * Class describing a user.
 */
public class User {

    private Integer id;
    private String username;
    private String password;
    private List<Exercise> history;

    /**
     * Creates a new User.
     * 
     * @param username a unique username
     * @param password a password for logging in
     */
    public User(String username, String password) {
        this.id = null;
        this.username = username;
        this.password = password;
        this.history = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void addExercise(Exercise exercise) {
        history.add(exercise);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * User's Exercise history
     *
     * @return list of Exercises by the User.
     */
    public List<Exercise> getHistory() {
        return history;
    }

    public void setHistory(List<Exercise> history) {
        this.history = history;
    }

    /**
     *
     * @return username
     */
    @Override
    public String toString() {
        return username;
    }

}
