package com.minhtetoo.proofofconcept.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.minhtetoo.proofofconcept.R;
import com.minhtetoo.proofofconcept.data.vo.PopularMovieVO;
import com.minhtetoo.proofofconcept.delegates.PopularMovieDelegate;
import com.minhtetoo.proofofconcept.viewholders.PopularMovieViewHolder;

import java.util.List;

/**
 * Created by min on 11/8/2017.
 */

public class PopularMovieRecyclerAdapter extends BaseRecyclerAdapter<PopularMovieViewHolder,PopularMovieVO> {

    Context mcontext;
    PopularMovieDelegate mDelegate;


    public PopularMovieRecyclerAdapter(Context mcontext, PopularMovieDelegate delegate) {
        super(mcontext);
        this.mcontext = mcontext;
        mDelegate = delegate;
    }

    @Override
    public PopularMovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = (LayoutInflater) mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.item_views,parent,false);

        PopularMovieViewHolder holder = new PopularMovieViewHolder(v,mDelegate);
        return holder;
    }

    @Override
    public void onBindViewHolder(PopularMovieViewHolder holder, int position) {

        PopularMovieVO currentVo = mData.get(position);
        TextView popularity = (TextView) holder.tvPopularity;
        Typeface font = Typeface.createFromAsset(mcontext.getAssets(), "font.ttf");
        popularity.setTypeface(font);
        popularity.setText(String.valueOf(currentVo.getVoteAverage()));


        holder.movieTitle.setText(currentVo.getTitle());
        holder.movieRating.setRating((float)(currentVo.getPopularity()/1000*6));

        YoYo.with(Techniques.Shake)
                .duration(1000)

                .playOn(holder.mItemView);

        Glide.with(mcontext).load("https://image.tmdb.org/t/p/original/"+currentVo.getPosterPath())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.movieThumbnail);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void appendPopularMovies(List<PopularMovieVO>
                                            loadedPopularMovies) {
        mData.addAll(loadedPopularMovies);
        notifyDataSetChanged();

    }


}
