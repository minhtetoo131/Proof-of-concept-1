package com.minhtetoo.proofofconcept;

import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by min on 11/9/2017.
 */

public class Font extends Application {

    public  Typeface getTypeface(){

        Typeface font = Typeface.createFromAsset(getApplicationContext().getAssets(),"Font.ttf");


        return font;

    }
}
