/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.integrated;

import java.io.File;
import java.sql.Timestamp;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import runit.dao.ExerciseDao;
import runit.dao.UserDao;
import runit.dao.util.Database;
import runit.domain.Exercise;
import runit.domain.Logic;
import runit.domain.User;

/**
 *
 * @author hajame
 */
public class LogicUserDaoTest {

    private Exercise exercise;
    private User user;
    private Logic logic;
    private File file;

    public LogicUserDaoTest() {
    }

    @Before
    public void setUp() {

        Timestamp timestamp = Timestamp.valueOf("2018-01-31 10:10:00.0");
        exercise = new Exercise(timestamp, 3600, 10.0);
        user = new User("test", "pass");
        Database database = null;

        try {
            file = new File("test.db");
            database = new Database("jdbc:sqlite:" + file.getAbsolutePath());
            database.init();
        } catch (Exception e) {
            System.out.println("Incorrect database address. --- " + e);
        }
        UserDao userDao = new UserDao(database);
        ExerciseDao exerciseDao = new ExerciseDao(database);
        logic = new Logic(userDao, exerciseDao);
        logic.signupUser(user.getUsername(), user.getPassword());

    }

    @After
    public void tearDown() {
        file.delete();
    }

    @Test
    public void loginUserTest() {
        assertEquals(logic.loginUser(user.getUsername(), user.getPassword()), "Login successful");
        assertEquals(logic.loginUser("thisUser", "thisPassword"), "User not found");
        assertEquals(logic.loginUser(user.getUsername(), "wrongPassword"), "Wrong password");
    }

    @Test
    public void signupUserTest() {
        assertEquals(logic.signupUser("testUser", "testPass"), "Login successful");
        assertEquals(logic.loginUser("testUser", "testPass"), "Login successful");
        logic.deleteUser("testUser");
    }

}
