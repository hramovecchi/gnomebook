package com.population.gnome.app;

import com.population.gnome.model.GnomeModel;
import com.population.gnome.view.GnomeDetailActivity;
import com.population.gnome.view.GnomeListActivity;
import com.population.gnome.view.LandingActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by hramovecchi on 5/2/2018.
 */
@Singleton
@Component(modules = {GnomebookModule.class})
public interface GnomebookComponent {
    void inject(LandingActivity activity);
    void inject(GnomeModel model);
    void inject(GnomeListActivity activity);
    void inject(GnomeDetailActivity activity);
    void inject(GnomebookApp app);
}
