package com.population.gnome.rest;

import com.population.gnome.dto.GnomePopulationDTO;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by hramovecchi on 28/1/2018.
 */

public interface GnomePopulationService {
    @GET("data.json")
    Call<GnomePopulationDTO> getGnomePopulation();
}
