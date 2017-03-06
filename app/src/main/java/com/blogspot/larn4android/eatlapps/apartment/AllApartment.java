package com.blogspot.larn4android.eatlapps.apartment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.blogspot.larn4android.eatlapps.R;
import com.blogspot.larn4android.eatlapps.modelclass.apartment;
import com.blogspot.larn4android.eatlapps.sign.MainAuth;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

public class AllApartment extends AppCompatActivity {

    private RecyclerView mBloglist;
    public static DatabaseReference mDatabase,mDatabaseLike;
    private String district;
    private String thana;
    private String loc;
    private String rentsale;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private boolean mProcessLike = false;
    private int taka;
    private int sqft,cheak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_apartment);


        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
               if(firebaseAuth.getCurrentUser()==null){
                   Intent i = new Intent(AllApartment.this, MainAuth.class);
                   startActivity(i);
               }
            }
        };

        mBloglist = (RecyclerView)findViewById(R.id.blog_list);
        mBloglist.setHasFixedSize(true);
        mBloglist.setLayoutManager(new LinearLayoutManager(this));

        Intent in = getIntent();
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null) {
            taka = bundle.getInt("taka");
            sqft = bundle.getInt("sqft");
            cheak = bundle.getInt("rent");
            district = bundle.getString("district");
        }



        if(cheak==1) {
            loc = new String("EasySearch/Apartment/Rent/District/" + district);
            mDatabase = FirebaseDatabase.getInstance().getReference().child(loc);
        }
        else{
            loc = new String("EasySearch/Apartment/Sale/District/" + district);
            mDatabase = FirebaseDatabase.getInstance().getReference().child(loc);
        }

        mDatabaseLike = FirebaseDatabase.getInstance().getReference().child("Favorite");
        mDatabaseLike.keepSynced(true);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<apartment,BlogViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<apartment, BlogViewHolder>(

                apartment.class,
                R.layout.blog_row,
                BlogViewHolder.class,
                mDatabase
        )
        {
            @Override
            protected void populateViewHolder(BlogViewHolder viewHolder, apartment model, int position) {

               final String post_key = getRef(position).getKey();
                viewHolder.setDistrict(model.getDistrict());
                viewHolder.setThana(model.getThana());
                viewHolder.setPicture(getApplicationContext(), model.getPicture());
                //viewHolder.setLikeBtn(post_key);
//                viewHolder.mLikebtn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        mProcessLike =true;
//
//                            mDatabaseLike.addValueEventListener(new ValueEventListener() {
//                                @Override
//                                public void onDataChange(DataSnapshot dataSnapshot) {
//                                    if(mProcessLike){
//                                    if (dataSnapshot.child(post_key).hasChild(mAuth.getCurrentUser().getUid())) {
//
//                                        mDatabaseLike.child(post_key).child(mAuth.getCurrentUser().getUid()).removeValue();
//                                        mProcessLike = false;
//                                    } else {
//                                        mDatabaseLike.child(post_key).child(mAuth.getCurrentUser().getUid()).setValue("Randome Value");
//                                        mProcessLike = false;
//                                    }
//                                }
//                                }
//
//                                @Override
//                                public void onCancelled(DatabaseError databaseError) {
//
//                                }
//                            });
//                       // }
//                    }
//                });
            }
        };
        mBloglist.setAdapter(firebaseRecyclerAdapter);
    }

    public static class BlogViewHolder extends RecyclerView.ViewHolder {//implements View.OnClickListener
        View mView;
        Context mContext;

        FirebaseAuth mAuth;
        DatabaseReference mDatabaseLike;
        ImageButton mLikebtn;
        public BlogViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            mContext = itemView.getContext();
//            mLikebtn = (ImageButton)mView.findViewById(R.id.like) ;
//
//            mDatabaseLike = FirebaseDatabase.getInstance().getReference().child("Favorite");
//            mAuth = FirebaseAuth.getInstance();
//            mDatabaseLike.keepSynced(true);

            //itemView.setOnClickListener(this);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final ArrayList<apartment> animals = new ArrayList<>();
                    //DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("EasySearch/Apartment/Sale/District/Tangail");
                    mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {

                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                animals.add(snapshot.getValue(apartment.class));
                            }

                            int itemPosition = getLayoutPosition();

                            Intent intent = new Intent(mContext, DetailsApartment.class);
                            intent.putExtra("position", itemPosition + "");
                            //intent.putExtra("restaurants", restaurants);
                            intent.putExtra("apartments", Parcels.wrap(animals));

                            mContext.startActivity(intent);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                        }
                    });
                }
            });
        }

        public void setDistrict(String district) {

            TextView post_title = (TextView) mView.findViewById(R.id.post_title);
            post_title.setText(district);
        }

        public void setThana(String thana) {

            TextView post_desc = (TextView) mView.findViewById(R.id.post_desc);
            post_desc.setText(thana);
        }

//        public void setLikeBtn(final String post_key){
//
//            mDatabaseLike.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(DataSnapshot dataSnapshot) {
//                    if(dataSnapshot.child(post_key).hasChild(mAuth.getCurrentUser().getUid())){
//                        mLikebtn.setImageResource(R.drawable.like);
//                    }
//                    else{
//                        mLikebtn.setImageResource(R.drawable.heart);
//                    }
//                }
//
//                @Override
//                public void onCancelled(DatabaseError databaseError) {
//
//                }
//            });
//        }
        public void setPicture(Context ctx, String picture) {


            ImageView post_image = (ImageView) mView.findViewById(R.id.post_image);
            Picasso.with(ctx)
                    .load(picture)
                    .error(R.mipmap.ic_launcher)
                    .into(post_image);

        }


    }
}
