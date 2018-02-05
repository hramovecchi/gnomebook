package com.population.gnome.view;

import com.population.gnome.dto.GnomeDTO;

import java.util.List;

/**
 * Created by hramovecchi on 31/1/2018.
 */

public interface GnomeDetailView {
    void setName(String name);
    void setProfilePicture(String url);
    void setAge(String age);
    void setWeight(String weight);
    void setHeight(String height);
    void setHairColor(String hairColor);
    void hideProfessionsLabels();
    void showProfessionsLabels();
    void setProfessions(String professions);
    void hideFriendsLabels();
    void showFriendsLabels();
    void setFriendList(List<GnomeDTO> gnomeList);
}
