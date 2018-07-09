package com.population.gnome.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hramovecchi on 28/1/2018.
 */

public class GnomeRestAdapter {

    private static GnomePopulationService gnomePopulationService;

    public static GnomePopulationService getInstance(){
        if (gnomePopulationService == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://raw.githubusercontent.com/hramovecchi/gnomebook/master/app/src/main/res/raw/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            gnomePopulationService = retrofit.create(GnomePopulationService.class);
        }
        return gnomePopulationService;
    }
}
