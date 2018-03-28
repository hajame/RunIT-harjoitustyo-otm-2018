/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runit;

import java.io.Console;
import java.util.*;

public class UI {

    private final Scanner scanner;
    private User user;

    public UI(Scanner scanner) {
        this.scanner = scanner;
        this.user = null;
    }

    public void start() {
        System.out.println("-----------------");
        System.out.println("Welcome to RunIT!");
        System.out.println("");
        System.out.println("created by hajame");
        System.out.println("-----------------");
        System.out.println("");

        login();

        while (true) {
            menu();

            System.out.print("-> ");
            String choice = scanner.nextLine();

            if (choice.equals("x")) {
                System.out.println("Goodbye!");
                break;
            } else {
                input(choice);
            }

        }

    }

    public void input(String input) {
        if (input.equals("1")) {
            // todo new exercise
        }

        if (input.equals("2")) {
            // todo list exercises
        }

    }

    public void menu() {
        System.out.println("Main menu:");
        System.out.println("...");
        System.out.println("[1] Record a new exercise");
        System.out.println("[2] List all exercises");
        System.out.println("[x] Quit");
    }

    public void login() {

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

        this.user = new User(name, pass);
        System.out.println("Hello " + user.username + ", nice to meet you! :)");

    }

}
