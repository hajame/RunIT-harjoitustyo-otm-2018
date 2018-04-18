/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runit.ui;

import java.sql.Timestamp;
import java.util.*;
import runit.domain.Exercise;
import runit.domain.Logic;

public class UI {

    private final Scanner scanner;
    private Logic logic;

    public UI(Scanner scanner, Logic logic) {
        this.scanner = scanner;
        this.logic = logic;
    }

    public void start() {
        System.out.println("-----------------");
        System.out.println("WELCOME TO RunIT!");
        System.out.println("");
        System.out.println("created by hajame");
        System.out.println("-----------------");
        System.out.println("");

        

        while (true) {
            signupmenu();

            System.out.print("-> ");
            String choice = scanner.nextLine();

            if (choice.equals("x")) {
                System.out.println("...");
                System.out.println("Goodbye!");
                return;
            } else {
                
                boolean login = signupinput(choice);
                if (login == true) {
                    break;
                }
            }
        }
        
        while (true) {
            menu();

            System.out.print("-> ");
            String choice = scanner.nextLine();

            if (choice.equals("x")) {
                System.out.println("...");
                System.out.println("Goodbye!");
                return;
            } else {
                input(choice);
            }
        }
    }

    public boolean signup() {

        String name;
        String pass;

        while (true) {
            System.out.println("Create a new user");
            System.out.print("Enter Username -> ");
            name = scanner.nextLine();

            if (name.isEmpty()) {
                System.out.println("Please enter a username.");
                continue;
            }
            System.out.print("Enter Password -> ");
            pass = scanner.nextLine();

            String message = logic.signupUser(name, pass);

            if (!message.equals("Login successful")) {
                System.out.println(message);
                return false;
            } else {
                System.out.println("Hello " + logic.getUser().getUsername() + ", nice to meet you! :)");
                System.out.println("");
                return true;
            }
        }

    }

    public boolean login() {

        String name;
        String pass;

        while (true) {
            System.out.println("Please log in: (username: 'test', password: 'pass')");
            System.out.print("Enter Username -> ");
            name = scanner.nextLine();

            if (name.isEmpty()) {
                System.out.println("Please enter a username.");
                continue;
            }
            System.out.print("Enter Password -> ");
            pass = scanner.nextLine();

            String message = logic.loginUser(name, pass);

            if (!message.equals("Login successful")) {
                System.out.println(message);
                return false;
            } else {
                System.out.println("Hello " + logic.getUser().getUsername() + ", nice to meet you! :)");
                System.out.println("");
                return true;
            }
        }

    }

    public void menu() {
        System.out.println("MAIN MENU");
        System.out.println("...");
        System.out.println("[1] Record a new exercise (not supported with database yet)");
        System.out.println("[2] List all exercises");
        System.out.println("[x] Quit");
    }
 
    public void signupmenu() {
        System.out.println("LOGIN");
        System.out.println("...");
        System.out.println("[1] Log in (username: 'test', password: 'pass')");
        System.out.println("[2] Create a user");
        System.out.println("[3] Delete a user");
        System.out.println("[x] Quit");
    }

    public void input(String input) {
        if (input.equals("1")) {
            addExercise();
        }

        if (input.equals("2")) {
            List<Exercise> history = logic.getHistory();

            if (logic.getUser().getHistory().isEmpty()) {
                System.out.println("...");
                System.out.println("No exercises.");
                System.out.println("");
            } else {
                System.out.println("...");
                System.out.println("Your exercises, " + logic.getUser().getUsername() + ":");
                for (Exercise exercise : history) {
                    System.out.println(exercise);
                }
                System.out.println("");
            }
        }
    }
    public boolean signupinput(String input) {
        
        boolean success = false;
        if (input.equals("1")) {
            success = login();
        }

        if (input.equals("2")) {
            success = signup();
        }
        if (input.equals("3")) {
            System.out.println("Not supported yet.");
            success = false;
        }
        
        return success;
        
    }

    public void addExercise() {

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

        int totalSeconds = hours * 60 * 60 + minutes * 60 + seconds;

        Exercise exercise = new Exercise(timestamp, totalSeconds, distance);
        Exercise a = logic.addExercise(exercise);
        System.out.println(a);
        System.out.println("*Exercise recorded successfully*");
        System.out.println();

    }

}
