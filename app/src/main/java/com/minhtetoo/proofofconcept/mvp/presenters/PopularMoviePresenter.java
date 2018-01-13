package com.minhtetoo.proofofconcept.mvp.presenters;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.app.FragmentActivity;

import com.minhtetoo.proofofconcept.ProofOfConcept;
import com.minhtetoo.proofofconcept.data.model.PopularMovieModel;
import com.minhtetoo.proofofconcept.data.vo.PopularMovieVO;
import com.minhtetoo.proofofconcept.events.RestApiEvents;
import com.minhtetoo.proofofconcept.mvp.views.PopularMovieView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by min on 1/13/2018.
 */

public class PopularMoviePresenter extends BasePresenters<PopularMovieView> {

    @Inject
    PopularMovieModel popularMovieModel;

    public PopularMoviePresenter() {
    }

    @Override
    public void onCreate(PopularMovieView view) {
        super.onCreate(view);
        ProofOfConcept sfcNewsApp = (ProofOfConcept) mView.getApplicationContext();
        sfcNewsApp.getAppComponent().inject(this);
    }

    @Override
    public void onStart() {


    }

    @Override
    public void onStop() {


    }

    public void onErrorInvokingAPI(RestApiEvents.ErrorInvokingAPIEvent event) {

    }

    public void onListEndReach(Context context) {
        popularMovieModel.startloadingPopularMovie(context);

    }

    public void onDataLoaded(FragmentActivity activity, Cursor data) {
        if (data != null &&  data.moveToFirst()){

            List<PopularMovieVO> popularMovieVoList = new ArrayList<>();

            do{
                PopularMovieVO popularMovieVO = PopularMovieVO.parseFromCursor((Context)activity,data);
                popularMovieVoList.add(popularMovieVO);

            }while(data.moveToNext());

            mView.displayNewsList(popularMovieVoList);

        }
    }
}
