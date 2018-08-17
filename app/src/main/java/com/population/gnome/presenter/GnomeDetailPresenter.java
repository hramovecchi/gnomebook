package com.population.gnome.presenter;

import com.population.gnome.app.GnomebookApp;
import com.population.gnome.dto.GnomeDTO;
import com.population.gnome.event.FetchGnomeFriendsDetailsEvent;
import com.population.gnome.event.GnomeFriendsDetailsFetched;
import com.population.gnome.view.GnomeDetailView;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import java.text.DecimalFormat;

import javax.inject.Inject;

/**
 * Created by hramovecchi on 31/1/2018.
 */

public class GnomeDetailPresenter {
    private GnomeDetailView view;

    @Inject
    Bus eventBus;

    public GnomeDetailPresenter(GnomeDetailView view){
        GnomebookApp.getApp().getGnomebookComponent().inject(this);
        this.view = view;
    }

    public void loadGnomeDetails(GnomeDTO gnome) {
        if (gnome != null){
            view.setName(gnome.getName());
            view.setProfilePicture(gnome.getThumbnail());
            view.setAge(gnome.getAge().toString());
            DecimalFormat df = new DecimalFormat("#.0");
            view.setWeight(df.format(gnome.getWeight()));
            view.setHeight(df.format(gnome.getHeight()));
            view.setHairColor(gnome.getHairColor());

            if (gnome.getProfessions() != null && !gnome.getProfessions().isEmpty()){
                view.showProfessionsLabels();
                view.setProfessions(gnome.getProfessions().toString().substring(1, gnome.getProfessions().toString().length()-1));
            } else {
                view.hideProfessionsLabels();
            }

            if (gnome.getFriends() != null && !gnome.getFriends().isEmpty()){
                eventBus.post(new FetchGnomeFriendsDetailsEvent(gnome.getName()));
            } else {
                view.hideFriendsLabels();
            }
        }
    }

    @Subscribe
    public void onGnomeFriendsDetailsFetched(GnomeFriendsDetailsFetched event){
        view.showFriendsLabels();
        view.setFriendList(event.getFriendsDetails());
    }

    public void onDestroy(){
        view = null;
    }

    public void listenToEvents() {
        eventBus.register(this);
    }

    public void dontListenToEvents() {
        eventBus.unregister(this);
    }
}
