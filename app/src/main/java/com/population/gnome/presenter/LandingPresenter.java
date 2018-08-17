package com.population.gnome.presenter;

import com.population.gnome.app.GnomebookApp;
import com.population.gnome.event.FetchGnomePopulationEvent;
import com.population.gnome.event.GnomePopulationFetched;
import com.population.gnome.view.LandingView;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;

/**
 * Created by hramovecchi on 28/1/2018.
 */

public class LandingPresenter {

    private LandingView view;

    @Inject
    Bus eventBus;

    public LandingPresenter(LandingView view){
        GnomebookApp.getApp().getGnomebookComponent().inject(this);
        this.view = view;
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
