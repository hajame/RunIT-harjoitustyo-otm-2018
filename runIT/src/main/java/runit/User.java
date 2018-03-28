/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package runit;

import java.util.*;


public class User {
    
    String username;
    String password;
    List<Exercise> history;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.history = new ArrayList<>();
    }
    
    

}
