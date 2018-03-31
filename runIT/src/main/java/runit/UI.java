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
    private Logic logic;

    public UI(Scanner scanner) {
        this.scanner = scanner;
        this.logic = new Logic(scanner);
        this.user = null;
    }

    public void start() {
        System.out.println("-----------------");
        System.out.println("Welcome to RunIT!");
        System.out.println("");
        System.out.println("created by hajame");
        System.out.println("-----------------");
        System.out.println("");

        this.user = logic.login();

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
            logic.addExercise(user);
        }

        if (input.equals("2")) {
            logic.printExercises(user);
        }

    }

    public void menu() {
        System.out.println("MAIN MENU");
        System.out.println("...");
        System.out.println("[1] Record a new exercise");
        System.out.println("[2] List all exercises");
        System.out.println("[x] Quit");
    }


}
