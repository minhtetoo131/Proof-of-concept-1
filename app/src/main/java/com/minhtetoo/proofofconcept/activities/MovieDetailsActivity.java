package com.minhtetoo.proofofconcept.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.minhtetoo.proofofconcept.R;
import com.minhtetoo.proofofconcept.adapters.MovieTrailerRecyclerAdapter;
import com.minhtetoo.proofofconcept.components.EmptyViewPod;
import com.minhtetoo.proofofconcept.components.SmartRecyclerView;

/**
 * Created by min on 12/14/2017.
 */

public class MovieDetailsActivity extends BaseActivity {

    SmartRecyclerView rvMovieTrailer;
    EmptyViewPod emptyViewPod;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_details);


        rvMovieTrailer = findViewById(R.id.rv_movie_trailers);
        emptyViewPod = findViewById(R.id.vp_empty_news);

        rvMovieTrailer.setmEmptyView(emptyViewPod);

        rvMovieTrailer.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false));

        rvMovieTrailer.setAdapter(new MovieTrailerRecyclerAdapter(getApplicationContext()));





    }
}
