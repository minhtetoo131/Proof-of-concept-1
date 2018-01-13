package com.minhtetoo.proofofconcept.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.minhtetoo.proofofconcept.ProofOfConcept;
import com.minhtetoo.proofofconcept.R;
import com.minhtetoo.proofofconcept.adapters.MovieTrailerRecyclerAdapter;
import com.minhtetoo.proofofconcept.components.EmptyViewPod;
import com.minhtetoo.proofofconcept.components.SmartRecyclerView;
import com.minhtetoo.proofofconcept.dagger.AppComponent;
import com.minhtetoo.proofofconcept.mvp.presenters.MovieDetailsPresenter;
import com.minhtetoo.proofofconcept.mvp.views.MovieDetailsView;

import javax.inject.Inject;

/**
 * Created by min on 12/14/2017.
 */

public class MovieDetailsActivity extends BaseActivity implements MovieDetailsView {

    SmartRecyclerView rvMovieTrailer;
    EmptyViewPod emptyViewPod;

    @Inject
    public MovieDetailsPresenter movieDetailsPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_details);

        ProofOfConcept proofOfConcept = (ProofOfConcept) getApplicationContext();
        AppComponent appComponent = proofOfConcept.getAppComponent();
        appComponent.inject(this);
        movieDetailsPresenter.onCreate(this);


        rvMovieTrailer = findViewById(R.id.rv_movie_trailers);
        emptyViewPod = findViewById(R.id.vp_empty_news);

        rvMovieTrailer.setmEmptyView(emptyViewPod);

        rvMovieTrailer.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false));

        rvMovieTrailer.setAdapter(new MovieTrailerRecyclerAdapter(getApplicationContext()));
    }

    @Override
    protected void onStart() {
        super.onStart();
        movieDetailsPresenter.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        movieDetailsPresenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        movieDetailsPresenter.onPause();
    }
    @Override
    protected void onStop() {
        super.onStop();
        movieDetailsPresenter.onStop();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        movieDetailsPresenter.onDestroy();
    }
}
