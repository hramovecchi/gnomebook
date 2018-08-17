package com.population.gnome.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.population.gnome.R;
import com.population.gnome.app.GnomebookApp;
import com.population.gnome.presenter.LandingPresenter;
import com.squareup.otto.Bus;

import javax.inject.Inject;

/**
 * Created by hramovecchi on 28/1/2018.
 */

public class LandingActivity extends Activity implements LandingView {

    private ProgressBar progressBar;
    private LandingPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progress_bar);
        presenter = new LandingPresenter(this);
        presenter.load();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void navigateToGnomeList() {
        Intent i = new Intent(getApplicationContext(), GnomeListActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setIndeterminate(true);
    }

    @Override
    public void hideProgress() {
         progressBar.setVisibility(View.GONE);
    }
}
