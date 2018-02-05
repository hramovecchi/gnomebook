package com.population.gnome.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by hramovecchi on 28/1/2018.
 */

public class GnomePopulationDTO {

    @SerializedName("Brastlewark")
    @Expose
    private List<GnomeDTO> brastlewark;

    public List<GnomeDTO> getBrastlewark() {
        return brastlewark;
    }

    public void setBrastlewark(List<GnomeDTO> brastlewark) {
        this.brastlewark = brastlewark;
    }
}
