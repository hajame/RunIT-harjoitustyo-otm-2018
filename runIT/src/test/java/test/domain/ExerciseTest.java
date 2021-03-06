package test.domain;

import runit.domain.Exercise;
import java.sql.Timestamp;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import runit.domain.User;

public class ExerciseTest {

    private Exercise exercise;
    private Exercise comp;
    private User user;

    @Before
    public void setUp() {
        Timestamp timestamp = Timestamp.valueOf("2018-01-31 10:10:10.0");
        exercise = new Exercise(timestamp, 3600, 10.00);
        comp = new Exercise(timestamp, 3600, 10.00);
        user = new User("user", "password");
        exercise.setUser(user);
        comp.setUser(user);
    }

    @Test
    public void toStringTest() {
        String string = exercise.toString();
        assertEquals("2018-01-31 10:10, duration 01:00:00, avgSpeed 10.00 km/h, distance 10.00 km", string);
    }

    @Test
    public void gettersTest() {
        Timestamp timestamp = Timestamp.valueOf("2018-01-31 10:10:10.0");
        assertEquals(null, exercise.getId());
        assertEquals(timestamp, exercise.getTime());
        assertEquals(3600, exercise.getDuration());
    }

    @Test
    public void setTimeTest() {
        Timestamp timestamp = Timestamp.valueOf("2018-01-31 10:50:10.0");
        exercise.setTime(timestamp);
        assertEquals(exercise.getTime(), timestamp);
    }

    @Test
    public void getUserTest() {
        User user = new User("user", "password");
        assertEquals(user.toString(), exercise.getUser().toString());
    }

    @Test
    public void equalsTest() {
        assertFalse(exercise.equals("String"));
        assertTrue(exercise.equals(exercise));
        assertFalse(exercise.equals(null));
    }

    @Test
    public void notEqualsWhenDifferentAvgSpeedTest() {
        comp.setAvgSpeed(14.5);
        assertFalse(exercise.equals(comp));
    }

    @Test
    public void notEqualsWhenDifferentTimeTest() {
        comp.setTime(Timestamp.valueOf("2300-01-31 10:10:10.0"));
        assertFalse(exercise.equals(comp));
    }

    @Test
    public void notEqualsWhenDifferentDistanceTest() {
        comp.setDuration(5);
        assertFalse(exercise.equals(comp));
    }
    
    @Test
    public void notEqualsWhenDifferentDurationTest() {
        comp.setDistance(99.0);
        assertFalse(exercise.equals(comp));
    }

    @Test
    public void notEqualsWhenDifferentUserTest() {
        comp.setUser(new User("anotherUser", "differentPassword"));
        assertFalse(exercise.equals(comp));
    }
}
