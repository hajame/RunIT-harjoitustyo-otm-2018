/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package runit;

import java.sql.Timestamp;

class Exercise {

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
    
        
        return 1.0 * (this.hours + this.minutes / 60 + this.seconds / 60 / 60);
    }
    
    
    
}
