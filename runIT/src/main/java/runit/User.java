/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package runit;

import java.util.*;


public class User {
    
    private String username;
    private String password;
    private ArrayList<Exercise> history;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.history = new ArrayList<>();
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

    public List<Exercise> getHistory() {
        return history;
    }

    public void setHistory(ArrayList<Exercise> history) {
        this.history = history;
    }

    @Override
    public String toString() {
        return username;
    }
    

    
    

}
