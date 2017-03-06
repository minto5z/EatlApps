package com.blogspot.larn4android.eatlapps.apartment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blogspot.larn4android.eatlapps.R;
import com.blogspot.larn4android.eatlapps.modelclass.apartment;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

public class DetailsApartment extends AppCompatActivity {

    private ImageView mImage;
    private TextView mTitle,mDesc;
    private ArrayList<apartment> mApartments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_apartment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        mTitle = (TextView) findViewById(R.id.tv_apartment_district);
        mDesc = (TextView)findViewById(R.id.tv_apartment_thana);
        mImage = (ImageView)findViewById(R.id.img_apartment);

        int startingPosition = Integer.parseInt(getIntent().getStringExtra("position"));
        //ArrayList<PostFish> mRestaurants =(ArrayList<PostFish>)getIntent().getSerializableExtra("restaurants");

        mApartments = Parcels.unwrap(getIntent().getParcelableExtra("apartments"));

        String name = mApartments.get(startingPosition).getDistrict();
        String description = mApartments.get(startingPosition).getThana();
        String image = mApartments.get(startingPosition).getPicture();


        mTitle.setText(name);
        mDesc.setText(description);
        Picasso.with(getApplicationContext())
                .load(image)
                //.resize(200, 200)
                .placeholder(R.drawable.apartment)
                .error(R.mipmap.ic_launcher)
                //.centerCrop()
                .into(mImage);





        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}


