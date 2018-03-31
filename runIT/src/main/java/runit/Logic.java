/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runit;

import java.sql.Timestamp;
import java.util.Scanner;

public class Logic {

    private Scanner scanner;

    public Logic(Scanner scanner) {
        this.scanner = scanner;
    }

    public void printExercises(User user) {
        
        if (user.history.isEmpty()) {
            System.out.println("...");
            System.out.println("No exercises.");
            System.out.println("");
            return;
        }
        System.out.println("...");
        System.out.println("Your exercises, " + user.username + ":");
        for (Exercise exercise : user.history) {
            System.out.println(exercise);
        }
        System.out.println("");

    }

    public User login() {

        String name;
        String pass;

        while (true) {
            System.out.println("Please log in:");
            System.out.print("Enter Username -> ");
            name = scanner.nextLine();

            if (name.isEmpty()) {
                System.out.println("Please enter a username.");
                continue;
            }
            System.out.print("Enter Password -> ");
            pass = scanner.nextLine();

            break;
        }

        User user = new User(name, pass);
        System.out.println("Hello " + user.username + ", nice to meet you! :)");
        System.out.println("");
        return user;
    }

    public void addExercise(User user) {

        Timestamp timestamp;
        int hours;
        int minutes;
        int seconds;
        double distance;

        while (true) {
            System.out.println("Insert time, f.ex. '2018-12-31 23:59' -- ('x' to Quit)");
            System.out.print("-> ");
            String answer = scanner.nextLine();

            if (answer.equals("x")) {
                return;
            }

            try {
                timestamp = Timestamp.valueOf(answer + ":00.0");
            } catch (Exception e) {
                System.out.println("Wrong date format");
                continue;
            }
            break;
        }

        while (true) {
            System.out.println("Insert duration, f.ex. '01:59:59'");
            System.out.print("-> ");
            String time = scanner.nextLine();

            if (time.equals("x")) {
                return;
            }

            try {
                hours = Integer.parseInt(time.substring(0, 2));
                minutes = Integer.parseInt(time.substring(3, 5));
                seconds = Integer.parseInt(time.substring(6));
            } catch (Exception e) {
                System.out.println("Wrong duration format");
                continue;
            }
            break;
        }

        while (true) {
            System.out.println("Insert distance (km), f.ex. '1.99'");
            System.out.print("-> ");
            String dist = scanner.nextLine();

            if (dist.equals("x")) {
                return;
            }

            try {
                distance = Double.parseDouble(dist);
            } catch (Exception e) {
                System.out.println("Wrong distance format");
                continue;
            }
            break;
        }
        Exercise exercise = new Exercise(timestamp, hours, minutes, seconds, distance);
        user.history.add(exercise);
        System.out.println(exercise);
        System.out.println("*Exercise recorded successfully*");
        System.out.println();

    }
}
