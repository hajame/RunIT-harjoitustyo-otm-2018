package runit.domain;

import java.sql.Timestamp;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import java.util.List;

/**
 * Class for calculating and saving statistical data.
 */
public class Statistics {

    private Integer totalExercises;
    private Exercise avgExercise;

    /**
     * Constructor enters placeholder values. The 'time' value for avgExercise is never used.
     */
    public Statistics() {
        this.totalExercises = 0;
        this.avgExercise = new Exercise(Timestamp.valueOf("2018-12-31 00:00:00.0"), 0, 0.0);
    }

    /**
     * Recalculates statistical results and saves the results in avgExercise and totalExercises.
     *
     * @param exercises List of exercises by user
     */
    public void calculate(List<Exercise> exercises) {
        DescriptiveStatistics avgDistance = new DescriptiveStatistics();
        DescriptiveStatistics avgSpeed = new DescriptiveStatistics();
        DescriptiveStatistics avgDuration = new DescriptiveStatistics();

        for (Exercise a : exercises) {
            avgDistance.addValue(a.getDistance());
            avgSpeed.addValue(a.getAvgSpeed());
            avgDuration.addValue(a.getDuration());
        }
        totalExercises = exercises.size();
        avgExercise.setDistance(avgDistance.getMean());
        avgExercise.setDuration((int) avgDuration.getMean());
        avgExercise.setAvgSpeed(avgSpeed.getMean());
    }

    public int getTotalExercises() {
        return totalExercises;
    }

    public Exercise getAvgExercise() {
        return avgExercise;
    }
}
