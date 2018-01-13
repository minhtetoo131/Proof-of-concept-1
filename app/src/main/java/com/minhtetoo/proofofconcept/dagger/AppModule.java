package com.minhtetoo.proofofconcept.dagger;

import android.content.Context;

import com.minhtetoo.proofofconcept.ProofOfConcept;
import com.minhtetoo.proofofconcept.data.model.PopularMovieModel;
import com.minhtetoo.proofofconcept.mvp.presenters.MainPresenter;
import com.minhtetoo.proofofconcept.mvp.presenters.MovieDetailsPresenter;
import com.minhtetoo.proofofconcept.mvp.presenters.PopularMoviePresenter;
import com.minhtetoo.proofofconcept.network.PopularMovieDataAgent;
import com.minhtetoo.proofofconcept.network.PopularMovieDataAgentImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by min on 1/13/2018.
 */
@Module
public class AppModule {

    private ProofOfConcept mApp;

    public AppModule(ProofOfConcept mApp) {
        this.mApp = mApp;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return mApp.getApplicationContext();
    }

    @Provides
    @Singleton
    public PopularMovieDataAgent providePopularMovieDataAgent() {
        return new PopularMovieDataAgentImpl();
    }

    @Provides
    @Singleton
    public PopularMovieModel providePopularMovieModel(Context context) {
        return new PopularMovieModel(context);
    }

    @Provides
    public PopularMoviePresenter providePopularMoviePresenter() {
        return new PopularMoviePresenter();
    }

    @Provides
    public MainPresenter provideMainPresenter() {
        return new MainPresenter();
    }

    @Provides
    public MovieDetailsPresenter provideMovieDetailPresenter() {
        return new MovieDetailsPresenter();
    }
}
