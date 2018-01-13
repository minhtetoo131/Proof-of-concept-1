package com.minhtetoo.proofofconcept.mvp.presenters;

import android.view.MenuItem;

import com.minhtetoo.proofofconcept.delegates.PopularMovieDelegate;
import com.minhtetoo.proofofconcept.mvp.views.MainView;

/**
 * Created by min on 1/13/2018.
 */

public class MainPresenter extends BasePresenters<MainView> implements PopularMovieDelegate {


    public MainPresenter(){


    }

    @Override
    public void onCreate(MainView view) {
        super.onCreate(view);
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onTapMovieItemView() {

    }

    public void onNavigationItemSelected(MenuItem menuItem) {

        menuItem.setChecked(true);
        mView.closeDrawer();

    }
}
