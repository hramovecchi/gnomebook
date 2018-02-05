package com.population.gnome.event;

import com.population.gnome.dto.GnomeDTO;

import java.util.List;

/**
 * Created by hramovecchi on 28/1/2018.
 */

public class GnomeListRequested {
    private List<GnomeDTO> list;

    public GnomeListRequested(List<GnomeDTO> values) {
        this.list = values;
    }

    public List<GnomeDTO> getList() {
        return list;
    }

    public void setList(List<GnomeDTO> list) {
        this.list = list;
    }
}
