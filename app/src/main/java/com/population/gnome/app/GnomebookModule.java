package com.population.gnome.app;

import com.population.gnome.model.GnomeModel;
import com.population.gnome.rest.GnomeRestAdapter;
import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by hramovecchi on 5/2/2018.
 */
@Module
public class GnomebookModule {

    @Singleton
    @Provides
    Bus provideEventBus(){
        return new Bus(ThreadEnforcer.ANY);
    }

    @Singleton
    @Provides
    GnomeModel provideGnomeModel() {return new GnomeModel(GnomeRestAdapter.getInstance()); }
}
