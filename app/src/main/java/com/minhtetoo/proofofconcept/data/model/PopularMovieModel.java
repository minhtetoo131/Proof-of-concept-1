package com.minhtetoo.proofofconcept.data.model;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.minhtetoo.proofofconcept.data.vo.PopularMovieVO;
import com.minhtetoo.proofofconcept.events.RestApiEvents;
import com.minhtetoo.proofofconcept.network.PopularMovieDataAgentImpl;
import com.minhtetoo.proofofconcept.persistence.MovieContract;
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

    public void startloadingPopularMovie(Context context){
        PopularMovieDataAgentImpl.getObjInstance().loadPopularMovies(AppConstants.ACESS_TOKEN,
                mPopularMoviePageIndex,context);

    }

    public List<PopularMovieVO> getPopularMovies(){

        return PopularMovies;
    }

    public void loadMoreMOvies(Context context) {
       PopularMovieDataAgentImpl.getObjInstance().loadPopularMovies(AppConstants.ACESS_TOKEN,
               mPopularMoviePageIndex,context);

    }

    @Subscribe
    public void onPopularMovieLoaded(RestApiEvents.PopularMovieLoadedEvent event){
        PopularMovies.addAll(event.getLoadedPopularMovies());
        mPopularMoviePageIndex = event.getLoadedPageIndex() + 1;

        //logic to store in persistence layer;
        ContentValues[] newsCVs = new ContentValues[event.getLoadedPopularMovies().size()] ;

        for(int index = 0 ;index < newsCVs.length ; index++){
            newsCVs[index] = event.getLoadedPopularMovies().get(index).parseToContentValue();
        }

        int insertedRow = event.getContext().getContentResolver().bulkInsert(MovieContract.PopularMovieEntry.CONTENT_URI,newsCVs);


        Log.d("Inserted Row ", insertedRow + "");
    }

    public void forceRefreshNews(Context context) {
        PopularMovies = new ArrayList<>();

        mPopularMoviePageIndex = 1;
        startloadingPopularMovie(context);
    }



}
