package com.minhtetoo.proofofconcept.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.minhtetoo.proofofconcept.fragments.FragmentOne;
import com.minhtetoo.proofofconcept.fragments.FragmentThree;
import com.minhtetoo.proofofconcept.fragments.FragmentTwo;

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
                fragment = new FragmentOne();
                break;
            case 1:
                fragment = new FragmentTwo();
                break;
            case 2:
                fragment = new FragmentThree();
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
                Title = "FragmentOne()";
                break;

            case 1:
                Title = "FragmentOne()";
                break;

            case 2:
                Title = "FragmentOne()";
                break;



        }


        return Title;
    }
}
