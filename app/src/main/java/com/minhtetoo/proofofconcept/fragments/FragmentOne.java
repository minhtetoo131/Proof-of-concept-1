package com.minhtetoo.proofofconcept.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.minhtetoo.proofofconcept.R;
import com.minhtetoo.proofofconcept.adapters.RecyclerAdapter;


public class FragmentOne extends Fragment {
    RecyclerView mrecyclerView ;


    public FragmentOne() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View v = inflater.inflate(R.layout.fragment_one, container, false);

        mrecyclerView= v.findViewById(R.id.rv_movie_overview);

        mrecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mrecyclerView.setAdapter(new RecyclerAdapter(getActivity()));




        return v ;






    }

}
