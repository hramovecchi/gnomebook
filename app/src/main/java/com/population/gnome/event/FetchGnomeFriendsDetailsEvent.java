package com.population.gnome.event;

/**
 * Created by hramovecchi on 5/2/2018.
 */

public class FetchGnomeFriendsDetailsEvent {
    private String gnomeName;

    public FetchGnomeFriendsDetailsEvent(String name) {
        this.gnomeName = name;
    }

    public String getGnomeName() {
        return gnomeName;
    }

    public void setGnomeName(String gnomeName) {
        this.gnomeName = gnomeName;
    }
}
