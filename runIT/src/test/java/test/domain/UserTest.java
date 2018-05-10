package test.domain;

import java.sql.Timestamp;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import runit.domain.Exercise;
import runit.domain.User;

public class UserTest {

    private User user;
    private Exercise exercise;

    @Before
    public void setUp() {
        user = new User("user", "password");
        Timestamp timestamp = Timestamp.valueOf("2018-01-31 10:10:10.0");
        exercise = new Exercise(timestamp, 3600, 10.00);
    }

    @After
    public void tearDown() {
    }

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
    public void setAndgetUsernameAndPasswordTest() {
        user.setUsername("test2");
        user.setPassword("password2");
        assertEquals("test2", user.getUsername());
        assertEquals("password2", user.getPassword());
    }
}
