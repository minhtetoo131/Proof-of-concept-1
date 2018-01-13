package com.minhtetoo.proofofconcept.data.model;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.minhtetoo.proofofconcept.ProofOfConcept;
import com.minhtetoo.proofofconcept.dagger.AppComponent;
import com.minhtetoo.proofofconcept.data.vo.PopularMovieVO;
import com.minhtetoo.proofofconcept.events.RestApiEvents;
import com.minhtetoo.proofofconcept.network.PopularMovieDataAgent;
import com.minhtetoo.proofofconcept.network.PopularMovieDataAgentImpl;
import com.minhtetoo.proofofconcept.persistence.MovieContract;
import com.minhtetoo.proofofconcept.utils.AppConstants;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by min on 12/7/2017.
 */

public class PopularMovieModel {

    private List<PopularMovieVO> PopularMovies;
    int mPopularMoviePageIndex=1;

    @Inject
    PopularMovieDataAgent popularMovieDataAgent;

    public PopularMovieModel(Context context) {
        EventBus.getDefault().register(this);
        PopularMovies = new ArrayList<>();

        AppComponent appComponent = ((ProofOfConcept)(context)).getAppComponent();
        appComponent.inject(this); //register consumer


    }



    public void startloadingPopularMovie(Context context){
        popularMovieDataAgent.loadPopularMovies(AppConstants.ACESS_TOKEN,
                mPopularMoviePageIndex,context);

    }

    public List<PopularMovieVO> getPopularMovies(){

        return PopularMovies;
    }

    public void loadMoreMOvies(Context context) {
        popularMovieDataAgent.loadPopularMovies(AppConstants.ACESS_TOKEN,
               mPopularMoviePageIndex,context);

    }

    @Subscribe
    public void onPopularMovieLoaded(RestApiEvents.PopularMovieLoadedEvent event){
        PopularMovies.addAll(event.getLoadedPopularMovies());
        mPopularMoviePageIndex = event.getLoadedPageIndex() + 1;

        //logic to store in persistence layer;
        ContentValues[] popularMovieCVs = new ContentValues[event.getLoadedPopularMovies().size()] ;
        List<ContentValues> genreIdCVs = new ArrayList<>();
        List<ContentValues> genreInMovieCVs = new ArrayList<>();

        for(int index = 0 ;index < popularMovieCVs.length ; index++){
            PopularMovieVO currentMovieVO = event.getLoadedPopularMovies().get(index);
            popularMovieCVs[index] = currentMovieVO.parseToContentValue();

            for (Integer genreId : currentMovieVO.getGenreIds()){
                ContentValues genreCV = new ContentValues();
                genreCV.put(MovieContract.GenreEntry.COLUMN_GENRE_ID,genreId);
                genreIdCVs.add(genreCV);

                ContentValues genreInMovie = new ContentValues();
                genreInMovie.put(MovieContract.GenreInMovieEntry.COLUMN_GENRE_ID,genreId);
                genreInMovie.put(MovieContract.GenreInMovieEntry.COLUMN_MOVIE_ID,currentMovieVO.getId());
                genreInMovieCVs.add(genreInMovie);
            }
        }

        int insertedMovieRow = event.getContext().getContentResolver().bulkInsert(MovieContract.PopularMovieEntry.CONTENT_URI,popularMovieCVs);
        Log.d("Inserted Row ", insertedMovieRow + "");

        int insertedGenreRow = event.getContext().getContentResolver().bulkInsert(MovieContract.GenreEntry.CONTENT_URI,genreIdCVs.toArray(new ContentValues[0]));
        Log.d("Inserted Row ", insertedGenreRow + "");

        int insertedGenreInMovieRow = event.getContext().getContentResolver().bulkInsert(MovieContract.GenreInMovieEntry.CONTENT_URI,genreInMovieCVs.toArray(new ContentValues[0]));
        Log.d("Inserted Row ", insertedGenreInMovieRow + "");



    }

    public void forceRefreshNews(Context context) {
        PopularMovies = new ArrayList<>();

        mPopularMoviePageIndex = 1;
        startloadingPopularMovie(context);
    }



}
