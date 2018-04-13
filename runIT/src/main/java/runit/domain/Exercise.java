/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runit.domain;

import java.sql.Timestamp;
import java.text.DecimalFormat;

public class Exercise {
    
    private Timestamp time;
    private int hours;
    private int minutes;
    private int seconds;
    private double avgSpeed;
    private double distance;

    public Exercise(Timestamp time, int hours, int minutes, int seconds, double distance) {
        this.time = time;
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
        this.distance = distance;
        this.avgSpeed = this.distance / durationAsHours();
    }


    
    public double durationAsHours() {
    
        return 1.0 * ((double) this.hours + (double) this.minutes / 60 + (double) this.seconds / 60 / 60);
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
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
    
    @Override
    public String toString() {
        
        DecimalFormat format = new DecimalFormat("00");
        DecimalFormat doubleDecimal = new DecimalFormat("#0.00");
        
        
        return time()+ ", duration " + format.format(hours) + ":" + format.format(minutes) + ":" + format.format(seconds)
                + ", avgSpeed " + doubleDecimal.format(avgSpeed) + " km/h, distance " + doubleDecimal.format(this.distance) + " km";
    }
    
    
    
    
    
}
