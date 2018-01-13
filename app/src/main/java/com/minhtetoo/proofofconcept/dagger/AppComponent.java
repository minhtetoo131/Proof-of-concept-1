package com.minhtetoo.proofofconcept.dagger;

import com.minhtetoo.proofofconcept.ProofOfConcept;
import com.minhtetoo.proofofconcept.activities.MainActivity;
import com.minhtetoo.proofofconcept.activities.MovieDetailsActivity;
import com.minhtetoo.proofofconcept.data.model.PopularMovieModel;
import com.minhtetoo.proofofconcept.fragments.PopularMovieFragment;
import com.minhtetoo.proofofconcept.mvp.presenters.PopularMoviePresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by min on 1/13/2018.
 */
@Component(modules = {AppModule.class})
@Singleton
public interface AppComponent {
    void inject(ProofOfConcept app);

    void inject(PopularMovieModel app);

    void inject(PopularMoviePresenter app);

    void inject(PopularMovieFragment app);


    void inject(MainActivity app);

    void inject(MovieDetailsActivity app);









}
