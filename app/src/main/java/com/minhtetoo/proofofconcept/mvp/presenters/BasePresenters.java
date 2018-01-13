package com.minhtetoo.proofofconcept.mvp.presenters;

/**
 * Created by min on 1/13/2018.
 */

public abstract class BasePresenters<T> {

    protected T mView;


    public void onCreate(T view){

        mView =view;

    }
    public abstract void onStart();


    public void onResume(){

    }
    public void onPause(){

    }
    public abstract void onStop();


    public void onDestroy(){

    }


}

