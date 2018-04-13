/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runit.domain;
import java.util.List;
import java.util.Scanner;

public class Logic {

    private Scanner scanner;
    private User user;

    public Logic(Scanner scanner) {
        this.scanner = scanner;
    }

    // muuta list exercises
    // printtaa UI:n puolella
    public List<Exercise> getHistory() {
        return user.getHistory();
    }

    public void setUser(String name, String pass) {
        User user = new User(name, pass);
        this.user = user;
    }

    public User getUser() {
        return user;
    }
    
    public void addExercise(Exercise exercise) {
        user.addExercise(exercise);
    }

}
