package com.minhtetoo.proofofconcept.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.minhtetoo.proofofconcept.R;

/**
 * Created by min on 11/8/2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    Context mcontext;


    public RecyclerAdapter(Context mcontext) {
        this.mcontext = mcontext;
    }

    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = (LayoutInflater) mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.item_views,parent,false);

        ViewHolder holder = new ViewHolder(v);




        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.ViewHolder holder, int position) {


        TextView txt = (TextView) holder.tv;
        Typeface font = Typeface.createFromAsset(mcontext.getAssets(), "font.ttf");
        txt.setTypeface(font);

        YoYo.with(Techniques.Shake)
                .duration(1000)

                .playOn(holder.mview);
    }

    @Override
    public int getItemCount() {
        return 100;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        View mview;
        TextView tv;


        public ViewHolder(View itemView) {
            super(itemView);
            mview =itemView;

            tv = mview.findViewById(R.id.lbl_ibm_rating);

        }
    }
}
