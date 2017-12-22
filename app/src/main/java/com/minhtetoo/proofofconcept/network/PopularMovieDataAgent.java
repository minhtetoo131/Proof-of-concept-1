package com.minhtetoo.proofofconcept.network;

import android.content.Context;

/**
 * Created by min on 12/7/2017.
 */


public interface PopularMovieDataAgent {

    void loadPopularMovies(String acessToken, int pageNo,Context context);


}