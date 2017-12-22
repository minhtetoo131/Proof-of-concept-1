package com.minhtetoo.proofofconcept.fragments;


import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.minhtetoo.proofofconcept.R;
import com.minhtetoo.proofofconcept.adapters.PopularMovieRecyclerAdapter;
import com.minhtetoo.proofofconcept.components.SmartScrollListener;
import com.minhtetoo.proofofconcept.data.model.PopularMovieModel;
import com.minhtetoo.proofofconcept.data.vo.PopularMovieVO;
import com.minhtetoo.proofofconcept.delegates.PopularMovieDelegate;
import com.minhtetoo.proofofconcept.events.RestApiEvents;
import com.minhtetoo.proofofconcept.persistence.MovieContract;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import android.support.v4.app.LoaderManager;

import java.util.ArrayList;
import java.util.List;


public class PopularMovieFragment extends BaseFragment implements SmartScrollListener.OnSmartScrollListener, LoaderManager.LoaderCallbacks<Cursor> {
    private static final int MOVIE_LIST_LOADER_ID = 1001;
    RecyclerView mrecyclerView ;

    View  v;

    TextView txt;

    PopularMovieDelegate popularMovieDelegate;

    PopularMovieRecyclerAdapter popularMovieRecyclerAdapter;



    public PopularMovieFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();



        EventBus.getDefault().register(this);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        popularMovieDelegate  = (PopularMovieDelegate) context;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


          v = getActivity().getLayoutInflater().inflate(R.layout.fragment_one, container, false);

        mrecyclerView= v.findViewById(R.id.rv_movie_overview);



        mrecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        popularMovieRecyclerAdapter = new PopularMovieRecyclerAdapter(getContext(),popularMovieDelegate) ;

        mrecyclerView.addOnScrollListener(new SmartScrollListener(this));

        mrecyclerView.setAdapter(popularMovieRecyclerAdapter);


        return v ;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getLoaderManager().initLoader(MOVIE_LIST_LOADER_ID,null,this);

    }

    @Override
    public void onStop() {

        EventBus.getDefault().unregister(this);

        super.onStop();
    }



    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPopularMovieLoaded(RestApiEvents.PopularMovieLoadedEvent event){

//        popularMovieRecyclerAdapter.appendPopularMovies(event.getLoadedPopularMovies());

    }


    @Subscribe (threadMode = ThreadMode.MAIN)
    public void onErrorInvokingAPI(RestApiEvents.ErrorInvokingAPIEvent event){
        Snackbar.make(mrecyclerView,event.getErrorMsg(),Snackbar.LENGTH_INDEFINITE).show();

    }

    @Override
    public void onListEndReach() {
        PopularMovieModel.getObjInstance().startloadingPopularMovie(getContext());
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(getContext(),
                MovieContract.PopularMovieEntry.CONTENT_URI,
                null,
                null,
                null,
                null
        );
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (data != null &&  data.moveToFirst()){

            List<PopularMovieVO> newsVoList = new ArrayList<>();

            do{
                PopularMovieVO newsVO = PopularMovieVO.parseFromCursor(data);
                newsVoList.add(newsVO);

            }while(data.moveToNext());

            popularMovieRecyclerAdapter.setNewData(newsVoList);

        }

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
