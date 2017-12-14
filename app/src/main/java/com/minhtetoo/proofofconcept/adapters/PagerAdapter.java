package com.minhtetoo.proofofconcept.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.minhtetoo.proofofconcept.fragments.PopularMovieFragment;
import com.minhtetoo.proofofconcept.fragments.UpCominingFragment;
import com.minhtetoo.proofofconcept.fragments.NowOnCinema;

/**
 * Created by min on 11/8/2017.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {



    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        Fragment fragment=null;

        switch(position){
            case 0:
                fragment = new PopularMovieFragment();
                break;
            case 1:
                fragment = new NowOnCinema();
                break;
            case 2:
                fragment = new UpCominingFragment();
                break;

        }




        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        CharSequence Title ="";

        switch(position){
            case 0:
                Title = "PopularMovieFragment()";
                break;

            case 1:
                Title = "PopularMovieFragment()";
                break;

            case 2:
                Title = "PopularMovieFragment()";
                break;



        }


        return Title;
    }
}
