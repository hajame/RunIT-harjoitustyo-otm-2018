/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runit;

import java.sql.Timestamp;
import java.util.*;

/**
 *
 * @author hajame
 */
public class Main {

    public static void main(String[] args) {

//        test code 

//        User user = new User("harri", "joku");
//        
//        Timestamp timestamp = Timestamp.valueOf("2018-01-28 18:33:00.0");
//        Timestamp timestamp2 = Timestamp.valueOf("2018-01-30 18:34:00.0");
//        
//        user.history.add(new Exercise(timestamp, 0, 30, 0, 5.0));
//        user.history.add(new Exercise(timestamp2, 0, 32, 0, 5.0));
//
//        System.out.println(user.history.get(0));
//        System.out.println(user.history.get(1));
//        user.printExercises();

        Scanner scanner = new Scanner(System.in);

        UI ui = new UI(scanner);

        ui.start();
        System.out.println("...");
        System.out.println("** end of program **");

    }

}
