package com.population.gnome.presenter;

import com.population.gnome.event.FetchGnomePopulationEvent;
import com.population.gnome.event.GnomePopulationFetched;
import com.population.gnome.view.LandingView;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

/**
 * Created by hramovecchi on 28/1/2018.
 */

public class LandingPresenter {
    private LandingView view;
    private Bus eventBus;

    public LandingPresenter(LandingView view, Bus bus){
        this.view = view;
        this.eventBus = bus;
        eventBus.register(this);
    }

    public void load(){
        view.showProgress();
        eventBus.post(new FetchGnomePopulationEvent());
    }

    @Subscribe
    public void onGnomePopulationFetched(GnomePopulationFetched event){
        view.hideProgress();
        view.navigateToGnomeList();
    }

    public void onDestroy(){
        eventBus.unregister(this);
        view = null;
    }
}
