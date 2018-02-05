package com.population.gnome.app;

import android.app.Application;
import android.widget.Toast;

import com.population.gnome.event.AppErrorEvent;
import com.population.gnome.model.GnomeModel;
import com.population.gnome.rest.GnomeRestAdapter;
import com.squareup.otto.Subscribe;

/**
 * Created by hramovecchi on 25/1/2018.
 */

public class GnomebookApp extends Application {
    public static final String GNOME_DETAIL_KEY = "GNOME_DETAIL";
    private static GnomebookApp app;

    private GnomeModel gnomeModel;
    private GnomebookComponent gnomebookComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;

        gnomebookComponent = DaggerGnomebookComponent.builder()
                .gnomebookModule(new GnomebookModule()).build();
        gnomeModel = new GnomeModel(GnomeRestAdapter.getInstance());
        gnomeModel.getBus().register(this);
    }

    @Subscribe
    public void onAppError(AppErrorEvent appErrorEvent){
        Toast.makeText(this, appErrorEvent.getMessage(), Toast.LENGTH_SHORT).show();
    }

    public static GnomebookApp getApp(){
        return app;
    }

    public GnomebookComponent getGnomebookComponent(){
        return gnomebookComponent;
    }
}
