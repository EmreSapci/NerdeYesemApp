package com.example.neredeyesemapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.neredeyesemapp.R;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class VenueActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anime);

        //Hide the default actionbar
        getSupportActionBar().hide();

        //Recieve data
        String name = getIntent().getExtras().getString("anime_name");
        String description = getIntent().getExtras().getString("anime_description");
        String studio = getIntent().getExtras().getString("anime_studio");
        String category = getIntent().getExtras().getString("anime_category");
        String image_url = getIntent().getExtras().getString("anime_img");
        String rating = getIntent().getExtras().getString("anime_rating");
        int nb_epsiode = getIntent().getExtras().getInt("anime_nb_epsiode");

        //ini views
        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsingtoolbar_id);
        collapsingToolbarLayout.setTitleEnabled(true);

        TextView tv_name = findViewById(R.id.aa_venue_name);
        TextView tv_studio = findViewById(R.id.aa_rating_text);
        TextView tv_categorie = findViewById(R.id.aa_categorie);
        TextView tv_description = findViewById(R.id.aa_address);
        TextView tv_rating = findViewById(R.id.aa_rating);
        ImageView img = findViewById(R.id.aa_thumbnail);

        tv_name.setText(name);
        tv_studio.setText(studio);
        tv_categorie.setText(category);
        tv_description.setText(description);
        tv_rating.setText(rating);

        collapsingToolbarLayout.setTitle(name);

        RequestOptions requestOptions =  new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);

        // set image using Glide
        Glide.with(this).load(image_url).apply(requestOptions).into(img);




    }
}