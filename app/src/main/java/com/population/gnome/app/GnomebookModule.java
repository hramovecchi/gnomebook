package com.population.gnome.app;

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
}
