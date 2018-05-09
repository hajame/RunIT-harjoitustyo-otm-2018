/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.domain;

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
import runit.domain.Statistics;
import runit.domain.User;

/**
 *
 * @author hajame
 */
public class LogicTest {
    
    private Exercise exercise;
    private User user;
    private Logic logic;
    private File file;
    
    public LogicTest() {
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
        logic.loginUser(user.getUsername(), user.getPassword());
        
    }
    
    @After
    public void tearDown() {
        file.delete();
    }
        
    @Test
    public void createTimestampTest() {
        String comp = "2018-01-31 10:10";        
        Timestamp timestamp = logic.createTimestamp(comp);
        assertEquals(timestamp.toString().substring(0, 16), comp);
    }
        
    @Test
    public void createDurationTest() {
        String oneHour = "01:00:00";        
        int duration = logic.createDuration(oneHour);
        assertEquals((int) 3600, duration);
    }
    
    @Test
    public void createDurationWrongFormatTest() {
        String wrongFromat1 = "100:60:60";        
        String wrongFromat2 = "-1:60:60";        
        int duration = logic.createDuration(wrongFromat1);
        assertEquals(-1, duration);
        int duration2 = logic.createDuration(wrongFromat2);
        assertEquals(-1, duration2);
    }
    
    @Test
    public void getStatisticsTest() {
        List<Exercise> history = logic.getHistory();
        Statistics comp = new Statistics();
        comp.calculate(history);
        Statistics statistics = logic.getStatistics();
        assertEquals(comp.getAvgExercise().toString(), statistics.getAvgExercise().toString());
        assertEquals(comp.getTotalExercises(), statistics.getTotalExercises());
    }
}
