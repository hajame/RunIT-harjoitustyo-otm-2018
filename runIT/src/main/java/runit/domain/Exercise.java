package runit.domain;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.Objects;

/**
 * Class describing a single running/walking exercise.
 */
public class Exercise {

    private User user;
    private Integer id;
    private Timestamp time;
    private int duration;
    private double avgSpeed;
    private double distance;

    /**
     * Creates a new Exercise.
     *
     * @param time beginning time of the exercise
     * @param duration duration of exercise in seconds
     * @param distance distance of exercise in kilometers
     */
    public Exercise(Timestamp time, int duration, double distance) {
        this.user = null;
        this.id = null;
        this.time = time;
        this.duration = duration;
        this.distance = distance;
        this.avgSpeed = this.distance / durationAsHours();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return duration in hours
     */
    public double durationAsHours() {

        return 1.0 * ((double) this.duration / 60 / 60);
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public int getDuration() {
        return duration;
    }

    /**
     * @param seconds duration in seconds
     */
    public void setDuration(int seconds) {
        this.duration = seconds;
    }

    public double getAvgSpeed() {
        return avgSpeed;
    }

    /**
     * @param avgSpeed average speed in seconds
     */
    public void setAvgSpeed(double avgSpeed) {
        this.avgSpeed = avgSpeed;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
    
    /**
     * @return time in String format
     */
    public String time() {
        return time.toString().substring(0, 16);
    }
    
    /**
     * @return duration in HH:MM:SS format
     */
    public String durationToString() {
        int hours = duration / 3600;
        int minutes = (duration % 3600) / 60;
        int seconds = duration % 60;

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    /**
     * @return Exercise in String format
     */        
    @Override
    public String toString() {
        DecimalFormat doubleDecimal = new DecimalFormat("#0.00");
        return time() + ", duration " + durationToString() + ", avgSpeed " + doubleDecimal.format(avgSpeed) + " km/h, distance " + doubleDecimal.format(this.distance) + " km";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Exercise other = (Exercise) obj;
        if (this.duration != other.duration) {
            return false;
        }
        if (Double.doubleToLongBits(this.avgSpeed) != Double.doubleToLongBits(other.avgSpeed)) {
            return false;
        }
        if (Double.doubleToLongBits(this.distance) != Double.doubleToLongBits(other.distance)) {
            return false;
        }
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.time, other.time)) {
            return false;
        }
        return true;
    }
}
