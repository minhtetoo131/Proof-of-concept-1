package com.minhtetoo.proofofconcept;

import android.app.Application;
import android.content.Context;

import com.minhtetoo.proofofconcept.dagger.AppComponent;
import com.minhtetoo.proofofconcept.dagger.AppModule;
import com.minhtetoo.proofofconcept.dagger.DaggerAppComponent;
import com.minhtetoo.proofofconcept.data.model.PopularMovieModel;

import javax.inject.Inject;

/**
 * Created by min on 12/7/2017.
 */


public class ProofOfConcept extends Application {

    private AppComponent mppComponent;

    @Inject
    Context mContext;

    @Inject
    PopularMovieModel popularMovieModel;


    @Override
    public void onCreate() {
        super.onCreate();

        mppComponent = initDagger(); //dagger init
        mppComponent.inject(this); //register consumer

        popularMovieModel.startloadingPopularMovie(mContext);
    }


    private AppComponent initDagger() {
        //return null;
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();

    }

    public AppComponent getAppComponent() {
        return mppComponent;
    }
}
