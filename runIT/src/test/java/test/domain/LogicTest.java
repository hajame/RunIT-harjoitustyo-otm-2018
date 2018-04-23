/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.domain;

import java.sql.Timestamp;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import runit.domain.Exercise;
import runit.domain.Logic;
import runit.domain.User;

/**
 *
 * @author hajame
 */
public class LogicTest {

    private Exercise exercise;
    private User user;
    private Logic logic;

    public LogicTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

        Timestamp timestamp = Timestamp.valueOf("2018-01-31 10:10:00.0");

        exercise = new Exercise(timestamp, 3600, 1.0*10);
        user = new User("test", "pass");
        exercise.setUser(user);
        logic = new Logic();
//        exercise = logic.addExercise(exercise);
//        logic.deleteExercise(exercise);
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
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

    @Test
    public void addExerciseTest() {
//        Exercise comp = logic.addExercise(exercise);
//        
//        assertEquals(comp.getId(), exercise.getId());
//        List<Exercise> exercises = logic.getHistory();
//        boolean found = exercises.contains(exercise);
//        
//        assertTrue(logic.getHistory().contains(exercise));
//        logic.deleteExercise(exercise);
//        assertFalse(logic.getHistory().contains(exercise));

    }
    @Test
    public void createTimestamp() {
        Timestamp timestamp = Timestamp.valueOf("2018-01-31 10:10:00.0");
        
        String comp = "2018-01-31 10:10:00";
        
        assertEquals(exercise.time()+":00", comp);

    }
}
