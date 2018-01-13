package com.minhtetoo.proofofconcept.mvp.views;

import android.content.Context;

import com.minhtetoo.proofofconcept.data.vo.PopularMovieVO;

import java.util.List;

/**
 * Created by min on 1/13/2018.
 */

public interface PopularMovieView {
    void displayNewsList(List<PopularMovieVO> popularMovieVoList);

    Context getContext();

    Context getApplicationContext();

}
