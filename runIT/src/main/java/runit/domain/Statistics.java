
package runit.domain;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import java.util.List;

public class Statistics {

    private Integer totalExercises;
    private Double avgDistance;

    public Statistics() {
        this.totalExercises = 0;
        this.avgDistance = 0.0;
    }
    
    /**
     * Recalculates statistical results
     * 
     * @param exercises List of exercises by user
     */
    public void calculate(List<Exercise> exercises) {
        DescriptiveStatistics distance = new DescriptiveStatistics();
        exercises.forEach(a -> distance.addValue(a.getDistance()));
        totalExercises = exercises.size();
        avgDistance = distance.getMean();
    }

    public int getTotalExercises() {
        return totalExercises;
    }

    public Double getAvgDistance() {
        return avgDistance;
    }
}
