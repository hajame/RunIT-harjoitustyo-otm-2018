/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.domain;

import java.sql.Timestamp;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import runit.domain.Exercise;
import runit.domain.User;

/**
 *
 * @author hajame
 */
public class UserTest {

    private User user;
    private Exercise exercise;

    public UserTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

        user = new User("user", "password");

        Timestamp timestamp = Timestamp.valueOf("2018-01-31 10:10:10.0");
        exercise = new Exercise(timestamp, 3600, 10.00);

    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void toStringTest() {
        String string = user.toString();
        assertEquals("user", string);
    }

    @Test
    public void getUsernameTest() {
        assertEquals("user", user.getUsername());
    }

    @Test
    public void addExerciseTest() {
        user.addExercise(exercise);
        assertEquals("2018-01-31 10:10, duration 01:00:00, avgSpeed 10.00 km/h, distance 10.00 km", user.getHistory().get(0).toString());
    }

    @Test
    public void setUsernameAndPasswordTest() {
        user.setUsername("test2");
        user.setPassword("password2");

        assertEquals("test2", user.getUsername());
        assertEquals("password2", user.getPassword());
    }
    
    @Test
    public void setHistoryTest() {
        user.addExercise(exercise);
        
        Timestamp timestamp1 = Timestamp.valueOf("2018-01-31 10:10:10.0");
        Exercise exercise1 = new Exercise(timestamp1, 3600, 10.00);
        Timestamp timestamp2 = Timestamp.valueOf("2018-02-31 10:10:10.0");
        Exercise exercise2 = new Exercise(timestamp2, 3600, 10.00);
        Timestamp timestamp3 = Timestamp.valueOf("2018-03-31 10:10:10.0");
        Exercise exercise3 = new Exercise(timestamp3, 3600, 10.00);
        
        ArrayList<Exercise> exercises = new ArrayList<>();
        exercises.add(exercise1);
        exercises.add(exercise2);
        exercises.add(exercise3);
        
        user.setHistory(exercises);
        
        assertEquals("2018-03-31 10:10, duration 01:00:00, avgSpeed 10.00 km/h, distance 10.00 km", user.getHistory().get(2).toString());
    }
//    @Test
//    public String getPassword() {
//        return password;
//    }
//    @Test
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public List<Exercise> getHistory() {
//        return history;
//    }
//
//    public void setHistory(List<Exercise> history) {
//        this.history = history;
//    }
}
