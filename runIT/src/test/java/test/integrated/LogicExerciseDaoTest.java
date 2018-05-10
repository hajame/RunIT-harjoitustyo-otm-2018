package test.integrated;

import java.io.File;
import java.sql.Timestamp;
import java.util.List;
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

public class LogicExerciseDaoTest {

    private Exercise exercise;
    private User user;
    private Logic logic;
    private File file;

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
        logic.loginUser(user.getUsername(), user.getPassword());
    }

    @After
    public void tearDown() {
        file.delete();
    }

    @Test
    public void addExerciseTest() {
        Exercise comp = logic.addExercise(exercise);
        assertEquals(comp.time(), exercise.time());
        assertEquals(comp.getDuration(), exercise.getDuration());
        assertEquals(comp.getUser().toString(), exercise.getUser().toString());
    }
    
    @Test
    public void addNullExerciseTest() {
        Exercise empty = null;
        assertNull(logic.addExercise(null));
    }
    
    @Test
    public void addExerciseWhenNoUserLoggedInTest() {
        logic.logout();
        Exercise comp = logic.addExercise(exercise);        
        assertNull(logic.addExercise(comp));
    }

    @Test
    public void deleteExerciseTest() {
        Exercise newExercise = logic.addExercise(exercise);
        logic.deleteExercise(newExercise);
        List<Exercise> comp = logic.getHistory();
        assertFalse(comp.contains(newExercise));
    }

    @Test
    public void deleteExerciseWithNoIdTest() {
        List<Exercise> comp = logic.getHistory();
        logic.deleteExercise(exercise);
        List<Exercise> comp2 = logic.getHistory();
        
        assertEquals(comp, comp2);
    }

    @Test
    public void getHistoryTest() {
        List<Exercise> comp = logic.getHistory();
        String compString = "";
        String historyString = "";

        for (Exercise a : comp) {
            compString += a.toString();
        }
        for (Exercise a : logic.getUser().getHistory()) {
            historyString += a.toString();
        }
        assertEquals(compString, historyString);
    }
}
