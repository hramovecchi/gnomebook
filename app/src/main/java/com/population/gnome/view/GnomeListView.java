package com.population.gnome.view;

import com.population.gnome.dto.GnomeDTO;

import java.util.List;

/**
 * Created by hramovecchi on 31/1/2018.
 */

public interface GnomeListView {
    void fillGnomeList(List<GnomeDTO> gnomeList);
}
