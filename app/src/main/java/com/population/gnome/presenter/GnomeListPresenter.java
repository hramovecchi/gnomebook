package com.population.gnome.presenter;

import com.population.gnome.dto.GnomeDTO;
import com.population.gnome.event.GnomeListRequested;
import com.population.gnome.event.RequestGnomeList;
import com.population.gnome.view.GnomeListView;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import java.util.List;

/**
 * Created by hramovecchi on 28/1/2018.
 */

public class GnomeListPresenter {
    private GnomeListView view;
    private Bus eventBus;

    public GnomeListPresenter(GnomeListView view, Bus bus){
        this.view = view;
        this.eventBus = bus;
        eventBus.register(this);
    }

    public void load(){
        eventBus.post(new RequestGnomeList());
    }

    @Subscribe
    public void onGnomeListRequested(GnomeListRequested event){
        List<GnomeDTO> gnomeList = event.getList();
        view.fillGnomeList(gnomeList);
    }

    public void destroy() {
        this.view = null;
        eventBus.unregister(this);
    }
}
