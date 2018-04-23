/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.domain;

import runit.domain.Exercise;
import java.sql.Timestamp;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import runit.domain.User;

/**
 *
 * @author hajame
 */
public class ExerciseTest {

    private Exercise exercise;
    private User user;

    public ExerciseTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

        Timestamp timestamp = Timestamp.valueOf("2018-01-31 10:10:10.0");

        exercise = new Exercise(timestamp, 3600, 10.00);
        user = new User("user", "password");
        exercise.setUser(user);

    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void toStringTest() {
        String string = exercise.toString();
        assertEquals("id (null) 2018-01-31 10:10, duration 01:00:00, avgSpeed 10.00 km/h, distance 10.00 km", string);
    }

    @Test
    public void gettersTest() {
        Timestamp timestamp = Timestamp.valueOf("2018-01-31 10:10:10.0");
        assertEquals(null, exercise.getId());
        assertEquals(timestamp, exercise.getTime());
        assertEquals(3600, exercise.getDuration());

    }

    @Test
    public void getUserTest() {
        User user = new User("user", "password");
        assertEquals(user.toString(), exercise.getUser().toString());
    }
}
