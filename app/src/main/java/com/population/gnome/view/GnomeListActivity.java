package com.population.gnome.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import com.population.gnome.R;
import com.population.gnome.app.GnomebookApp;
import com.population.gnome.cursor.GnomeAdapter;
import com.population.gnome.dto.GnomeDTO;
import com.population.gnome.presenter.GnomeListPresenter;
import com.squareup.otto.Bus;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by hramovecchi on 28/1/2018.
 */

public class GnomeListActivity extends Activity implements GnomeListView, SearchView.OnQueryTextListener, AdapterView.OnItemClickListener {
    private ListView listView;
    private SearchView searchView;
    private GnomeListPresenter presenter;
    private GnomeAdapter adapter;
    @Inject
    Bus bus;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gnome_list);
        ((GnomebookApp)getApplication()).getGnomebookComponent().inject(this);

        listView = findViewById(R.id.list_view);
        listView.setOnItemClickListener(this);
        searchView = findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(this);

        presenter = new GnomeListPresenter(this, bus);
        presenter.load();
    }

    @Override
    protected void onDestroy() {
        presenter.destroy();
        super.onDestroy();
    }

    @Override
    public void fillGnomeList(List<GnomeDTO> gnomeList) {
        adapter = new GnomeAdapter(getApplicationContext(), gnomeList);
        listView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        GnomeDTO gnome = (GnomeDTO)adapterView.getItemAtPosition(position);
        Intent i = new Intent(getApplicationContext(), GnomeDetailActivity.class);
        i.putExtra(GnomebookApp.GNOME_DETAIL_KEY, gnome);
        startActivity(i);
    }
    @Override
    public boolean onQueryTextSubmit(String s) {
        adapter.getFilter().filter(s);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        if ("".equals(s)){
            adapter.getFilter().filter(s);
        }
        return false;
    }
}
