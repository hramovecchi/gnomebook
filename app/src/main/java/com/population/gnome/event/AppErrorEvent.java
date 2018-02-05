package com.population.gnome.event;

/**
 * Created by hramovecchi on 28/1/2018.
 */

public class AppErrorEvent {
    private String message;

    public AppErrorEvent(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
