/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runit.domain;

import runit.ui.UI;
import java.sql.Timestamp;
import java.util.*;

/**
 *
 * @author hajame
 */
public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Logic logic = new Logic();
        UI ui = new UI(scanner, logic);
        ui.start();


    }

}
