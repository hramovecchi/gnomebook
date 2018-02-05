package com.population.gnome.event;

import com.population.gnome.dto.GnomeDTO;

import java.util.List;

/**
 * Created by hramovecchi on 5/2/2018.
 */

public class GnomeFriendsDetailsFetched {
    private List<GnomeDTO> friendsDetails;

    public GnomeFriendsDetailsFetched(List<GnomeDTO> friendsDetails){
        this.friendsDetails = friendsDetails;
    }

    public List<GnomeDTO> getFriendsDetails() {
        return friendsDetails;
    }

    public void setFriendsDetails(List<GnomeDTO> friendsDetails) {
        this.friendsDetails = friendsDetails;
    }
}
