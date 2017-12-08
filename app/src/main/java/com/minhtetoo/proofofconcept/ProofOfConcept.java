package com.minhtetoo.proofofconcept;

import android.app.Application;

import com.minhtetoo.proofofconcept.data.model.PopularMovieModel;

/**
 * Created by min on 12/7/2017.
 */


public class ProofOfConcept extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

      PopularMovieModel.getObjInstance().startloadingPopularMovie();
    }
}
