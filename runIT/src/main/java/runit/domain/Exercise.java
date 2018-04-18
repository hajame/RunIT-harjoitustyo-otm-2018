/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runit.domain;

import java.sql.Timestamp;
import java.text.DecimalFormat;

public class Exercise {
    
    private User user;
    private Integer id;
    private Timestamp time;
    private int duration;
    private double avgSpeed;
    private double distance;

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

    public void setDuration(int seconds) {
        this.duration = seconds;
    }

    public double getAvgSpeed() {
        return avgSpeed;
    }

    public void setAvgSpeed(double avgSpeed) {
        this.avgSpeed = avgSpeed;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String time() {
        return time.toString().substring(0, 16);
    }

    public String durationToString() {
        int hours = duration / 3600;
        int minutes = (duration % 3600) / 60;
        int seconds = duration % 60;

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    @Override
    public String toString() {

        DecimalFormat doubleDecimal = new DecimalFormat("#0.00");

        return "id ("+id+") " + time() + ", duration " + durationToString() + ", avgSpeed " + doubleDecimal.format(avgSpeed) + " km/h, distance " + doubleDecimal.format(this.distance) + " km";
    }

}
