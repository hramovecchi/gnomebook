package com.population.gnome.model;

import com.google.gson.Gson;
import com.population.gnome.R;
import com.population.gnome.app.GnomebookApp;
import com.population.gnome.dto.GnomeDTO;
import com.population.gnome.dto.GnomePopulationDTO;
import com.population.gnome.event.AppErrorEvent;
import com.population.gnome.event.FetchGnomeFriendsDetailsEvent;
import com.population.gnome.event.FetchGnomePopulationEvent;
import com.population.gnome.event.GnomeFriendsDetailsFetched;
import com.population.gnome.event.GnomeListRequested;
import com.population.gnome.event.GnomePopulationFetched;
import com.population.gnome.event.RequestGnomeList;
import com.population.gnome.rest.GnomePopulationService;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by hramovecchi on 25/1/2018.
 */

public class GnomeModel {
    @Inject
    Bus bus;
    private GnomePopulationService gnomePopulationService;
    private HashMap<String, GnomeDTO> gnomeMap;

    public GnomeModel(GnomePopulationService gnomePopulationService){
        GnomebookApp.getApp().getGnomebookComponent().inject(this);
        this.gnomePopulationService = gnomePopulationService;
        bus.register(this);
    }

    public GnomePopulationService getGnomePopulationService() {
        return gnomePopulationService;
    }

    public void setGnomePopulationService(GnomePopulationService gnomePopulationService) {
        this.gnomePopulationService = gnomePopulationService;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public Bus getBus() {
        return bus;
    }

    private void createAgenda(List<GnomeDTO> gnomePopulation) {
        gnomeMap = new HashMap<String, GnomeDTO>(gnomePopulation.size());
        for (GnomeDTO gnome : gnomePopulation){
            gnomeMap.put(gnome.getName(), gnome);
        }
    }

    public GnomeDTO getGnomeInfo(String name){
        return gnomeMap.get(name);
    }

    private List<GnomeDTO> getFriends(String name){
        GnomeDTO gnome = getGnomeInfo(name);
        List<GnomeDTO> friendList= new ArrayList<GnomeDTO>(gnome.getFriends().size());
        for (String friend: gnome.getFriends()){
            friendList.add(getGnomeInfo(friend));
        }

        return friendList;
    }

    @Subscribe
    public void onFetchGnomePopulationEvent(FetchGnomePopulationEvent event) {
        Call<GnomePopulationDTO> call = gnomePopulationService.getGnomePopulation();
        call.enqueue(new Callback<GnomePopulationDTO>() {
            @Override
            public void onResponse(Call<GnomePopulationDTO> call, Response<GnomePopulationDTO> response) {
                if (response.isSuccessful()) {
                    List<GnomeDTO> gnomeBrastlewarkPopulation = response.body().getBrastlewark();
                    createAgenda(gnomeBrastlewarkPopulation);
                    bus.post(new GnomePopulationFetched());
                } else {
                    bus.post(new AppErrorEvent("Api response with an error"));
                    onAPICallFail();
                }
            }

            @Override
            public void onFailure(Call<GnomePopulationDTO> call, Throwable t) {
                bus.post(new AppErrorEvent("API call fail"));
                onAPICallFail();
            }
        });
    }

    @Subscribe
    public void onGnomeListRequest(RequestGnomeList event){
        bus.post(new GnomeListRequested(new ArrayList(gnomeMap.values())));
    }

    @Subscribe
    public void onFetchGnomeFriendsDetailsEvent(FetchGnomeFriendsDetailsEvent event){
        List<GnomeDTO> detailedFriendList = getFriends(event.getGnomeName());
        bus.post(new GnomeFriendsDetailsFetched(detailedFriendList));
    }

    private void onAPICallFail(){
        try {
            InputStream in = GnomebookApp.getApp().getResources().openRawResource(R.raw.dummy_gnome_list);
            Reader reader = new InputStreamReader(in, "UTF-8");
            GnomePopulationDTO result = new Gson().fromJson(reader, GnomePopulationDTO.class);
            createAgenda(result.getBrastlewark());
            bus.post(new AppErrorEvent("API call fail, using JSON File instead"));
            bus.post(new GnomePopulationFetched());
        } catch (UnsupportedEncodingException e) {
            bus.post(new AppErrorEvent("Error parsing the JSON file"));
        }
    }
}
