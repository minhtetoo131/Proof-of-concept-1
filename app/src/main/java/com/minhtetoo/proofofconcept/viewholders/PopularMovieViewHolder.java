package com.minhtetoo.proofofconcept.viewholders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.minhtetoo.proofofconcept.R;
import com.minhtetoo.proofofconcept.data.VO.PopularMovieVO;
import com.minhtetoo.proofofconcept.delegates.PopularMovieDelegate;

/**
 * Created by min on 12/8/2017.
 */

public class PopularMovieViewHolder extends BaseViewHolder<PopularMovieVO> {

    PopularMovieDelegate mPopularmovieItemsDelegate;
    public View mItemView ;
    public TextView tvPopularity;



    public PopularMovieViewHolder(View itemView,  PopularMovieDelegate newsItemsDelegate) {
        super(itemView);


        mPopularmovieItemsDelegate = newsItemsDelegate;

        mItemView = itemView;
        tvPopularity = mItemView.findViewById(R.id.lbl_ibm_rating);






    }

    @Override
    public void setData(PopularMovieVO data) {

    }

    @Override
    public void onClick(View v) {

    }
}

