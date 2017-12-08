package com.minhtetoo.proofofconcept.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by min on 12/8/2017.
 */

public abstract class BaseViewHolder<W> extends RecyclerView.ViewHolder implements View.OnClickListener {

    private W mdata;

    public BaseViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
    }

    public abstract void setData(W data);


}
