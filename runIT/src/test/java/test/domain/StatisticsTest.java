package test.domain;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import runit.domain.Exercise;
import runit.domain.Statistics;

public class StatisticsTest {

    private List<Exercise> exercises;
    private Exercise exercise;
    private Statistics statistics;

    @Before
    public void setUp() {
        Timestamp timestamp = Timestamp.valueOf("2018-12-31 00:00:00.0");
        exercise = new Exercise(timestamp, 3600, 10.0);
        exercises = new ArrayList<>();        
        for (int i = 0; i < 10; i++) {
            exercises.add(exercise);
        }
        statistics = new Statistics();
        statistics.calculate(exercises);
    }

    @Test
    public void getAvgExerciseTest() {
        assertEquals(exercise, statistics.getAvgExercise());
    }
    
    @Test
    public void getTotalDurationTest() {
        assertEquals("10:00:00", statistics.getTotalDuration());
    }
    
    @Test
    public void getTotalDistance() {
        assertEquals(100, (int) statistics.getTotalDistance());
    }
}
