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

import com.minhtetoo.proofofconcept.R;
import com.minhtetoo.proofofconcept.adapters.PopularMovieRecyclerAdapter;
import com.minhtetoo.proofofconcept.delegates.PopularMovieDelegate;
import com.minhtetoo.proofofconcept.events.RestApiEvents;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


public class FragmentOne extends BaseFragment {
    RecyclerView mrecyclerView ;

    View  v;

    TextView txt;

    PopularMovieDelegate popularMovieDelegate;

    PopularMovieRecyclerAdapter popularMovieRecyclerAdapter;



    public FragmentOne() {
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

        mrecyclerView.setAdapter(popularMovieRecyclerAdapter);






        return v ;






    }

//    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//
//
//        TextView tvRating = v.findViewById(R.id.lbl_ibm_rating);
//
//        Typeface font = Typeface.createFromAsset(getActivity().getAssets(),"Font.ttf");
//
//
//        tvRating.setTypeface(font);
//
//        super.onViewCreated(view, savedInstanceState);
//
//
//
//
//
//    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPopularMovieLoaded(RestApiEvents.PopularMovieLoadedEvent event){

        popularMovieRecyclerAdapter.appendPopularMovies(event.getLoadedPopularMovies());

    }


    @Subscribe (threadMode = ThreadMode.MAIN)
    public void onErrorInvokingAPI(RestApiEvents.ErrorInvokingAPIEvent event){
        Snackbar.make(mrecyclerView,event.getErrorMsg(),Snackbar.LENGTH_INDEFINITE).show();

    }
}
