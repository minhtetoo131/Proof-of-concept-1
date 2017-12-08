package com.minhtetoo.proofofconcept.data.model;

import com.minhtetoo.proofofconcept.data.VO.PopularMovieVO;
import com.minhtetoo.proofofconcept.events.RestApiEvents;
import com.minhtetoo.proofofconcept.network.PopularMovieDataAgentImpl;
import com.minhtetoo.proofofconcept.utils.AppConstants;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by min on 12/7/2017.
 */

public class PopularMovieModel {

    private static PopularMovieModel objInstance;
    private List<PopularMovieVO> PopularMovies;
    int mPopularMoviePageIndex=1;

    private PopularMovieModel() {
        EventBus.getDefault().register(this);
        PopularMovies = new ArrayList<>();

    }

    public static PopularMovieModel getObjInstance(){

        if(objInstance == null){

            objInstance = new PopularMovieModel();

        }

        return objInstance;
    }

    @Subscribe
    public void onPopularMovieLoaded(RestApiEvents.PopularMovieLoadedEvent event){
        PopularMovies.addAll(event.getLoadedPopularMovies());
        mPopularMoviePageIndex = event.getLoadedPageIndex() + 1;

    }

    public void startloadingPopularMovie(){
        PopularMovieDataAgentImpl.getObjInstance().loadPopularMovies(AppConstants.ACESS_TOKEN,
                mPopularMoviePageIndex);

    }

}
