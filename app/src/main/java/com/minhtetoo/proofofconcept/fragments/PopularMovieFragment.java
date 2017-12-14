package com.minhtetoo.proofofconcept.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
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
import com.minhtetoo.proofofconcept.delegates.PopularMovieDelegate;
import com.minhtetoo.proofofconcept.events.RestApiEvents;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


public class PopularMovieFragment extends BaseFragment implements SmartScrollListener.OnSmartScrollListener {
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
    public void onStop() {

        EventBus.getDefault().unregister(this);

        super.onStop();
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


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPopularMovieLoaded(RestApiEvents.PopularMovieLoadedEvent event){

        popularMovieRecyclerAdapter.appendPopularMovies(event.getLoadedPopularMovies());

    }


    @Subscribe (threadMode = ThreadMode.MAIN)
    public void onErrorInvokingAPI(RestApiEvents.ErrorInvokingAPIEvent event){
        Snackbar.make(mrecyclerView,event.getErrorMsg(),Snackbar.LENGTH_INDEFINITE).show();

    }

    @Override
    public void onListEndReach() {

    }
}
