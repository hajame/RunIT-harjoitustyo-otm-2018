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
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
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
        
        exercise = new Exercise(timestamp, 3600, 10.0);
        user = new User("test", "pass");
        Database database = null;
        
        try {
            File file = new File("database.db");
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
    
    @Test
    public void addExerciseTest() {
        Exercise comp = logic.addExercise(exercise);
        assertEquals(comp.time(), exercise.time());
        assertEquals(comp.getDuration(), exercise.getDuration());
        assertEquals(comp.getUser().toString(), exercise.getUser().toString());
    }
    
    @Test
    public void createTimestampTest() {
        String comp = "2018-01-31 10:10";        
        Timestamp timestamp = logic.createTimestamp(comp);
        assertEquals(timestamp.toString().substring(0, 16), comp);
        
    }
    
    @Test
    public void getHistoryTest() {
        List<Exercise> comp = logic.getHistory();
        List<Exercise> history = logic.getUser().getHistory();
        
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
