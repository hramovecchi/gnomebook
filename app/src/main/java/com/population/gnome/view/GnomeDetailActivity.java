package com.population.gnome.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.population.gnome.R;
import com.population.gnome.app.GnomebookApp;
import com.population.gnome.cursor.GnomeAdapter;
import com.population.gnome.dto.GnomeDTO;
import com.population.gnome.presenter.GnomeDetailPresenter;

import java.util.List;

/**
 * Created by hramovecchi on 28/1/2018.
 */

public class GnomeDetailActivity extends Activity implements GnomeDetailView, AdapterView.OnItemClickListener{
    private GnomeDetailPresenter presenter;

    private TextView name;
    private ImageView profilePicture;
    private TextView age;
    private TextView weight;
    private TextView height;
    private TextView hairColor;
    private TextView professionsLabel;
    private TextView professions;
    private TextView friendsLabel;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gnome_detail);

        name = findViewById(R.id.name_input);
        profilePicture = findViewById(R.id.profile_pic);
        age = findViewById(R.id.age_input);
        weight = findViewById(R.id.weight_input);
        height = findViewById(R.id.height_input);
        hairColor = findViewById(R.id.hair_color_input);
        professionsLabel = findViewById(R.id.professions_label);
        professions = findViewById(R.id.professions_input);
        friendsLabel = findViewById(R.id.friends_label);
        listView = findViewById(R.id.friends_list_view);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void setName(String name) {
        this.name.setText(name);
    }

    @Override
    public void setProfilePicture(String url) {
        Glide.with(this)
                .load(url)
                .apply(RequestOptions.centerCropTransform())
                .into(profilePicture);
    }

    @Override
    public void setAge(String age) {
        this.age.setText(age);
    }

    @Override
    public void setWeight(String weight) {
        this.weight.setText(weight);
    }

    @Override
    public void setHeight(String height) {
        this.height.setText(height);
    }

    @Override
    public void setHairColor(String hairColor) {
        this.hairColor.setText(hairColor);
    }

    @Override
    public void hideProfessionsLabels() {
        this.professionsLabel.setVisibility(View.GONE);
        this.professions.setVisibility(View.GONE);
    }

    @Override
    public void showProfessionsLabels() {
        this.professionsLabel.setVisibility(View.VISIBLE);
        this.professions.setVisibility(View.VISIBLE);
    }

    @Override
    public void setProfessions(String professions) {
        this.professions.setText(professions);
    }

    @Override
    public void hideFriendsLabels() {
        this.friendsLabel.setVisibility(View.GONE);
    }

    @Override
    public void showFriendsLabels() {
        this.friendsLabel.setVisibility(View.VISIBLE);
    }

    @Override
    public void setFriendList(List<GnomeDTO> gnomeList) {
        GnomeAdapter adapter = new GnomeAdapter(getApplicationContext(), gnomeList);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        presenter.destroy();
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        presenter = new GnomeDetailPresenter(this);
        GnomeDTO gnome = (GnomeDTO)getIntent().getSerializableExtra(GnomebookApp.GNOME_DETAIL_KEY);
        presenter.loadGnomeDetails(gnome);
        super.onResume();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        GnomeDTO gnome = (GnomeDTO)adapterView.getItemAtPosition(position);
        Intent i = new Intent(getApplicationContext(), GnomeDetailActivity.class);
        i.putExtra(GnomebookApp.GNOME_DETAIL_KEY, gnome);
        startActivity(i);
    }
}
