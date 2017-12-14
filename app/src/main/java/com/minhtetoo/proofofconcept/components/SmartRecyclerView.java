package com.minhtetoo.proofofconcept.components;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by min on 12/14/2017.
 */

public class SmartRecyclerView extends RecyclerView {

    private View mEmptyView;


    public SmartRecyclerView(Context context) {
        super(context);
    }

    public SmartRecyclerView(Context context,
                             @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SmartRecyclerView(Context context,
                             @Nullable AttributeSet attrs,
                             int defStyle) {
        super(context, attrs, defStyle);
    }

    private AdapterDataObserver dataObserver = new AdapterDataObserver() {
        @Override
        public void onChanged() {
            super.onChanged();

            checkIfEmpty();
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            super.onItemRangeInserted(positionStart, itemCount);
            checkIfEmpty();

        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            super.onItemRangeRemoved(positionStart, itemCount);
            checkIfEmpty();

        }
    };


    @Override

    public void setAdapter(Adapter adapter) {
        Adapter oldAdapter = getAdapter();
        if (oldAdapter !=null){
            oldAdapter.unregisterAdapterDataObserver(dataObserver);
        }
        super.setAdapter(adapter);

        adapter.registerAdapterDataObserver(dataObserver);
        checkIfEmpty();


    }

    public void setmEmptyView(View v){

        mEmptyView = v;

    }

    //    check adapter connected to SRV is empty : if so show empty view;
    private void checkIfEmpty(){
        boolean isEmpty = getAdapter().getItemCount() == 0;

        if (mEmptyView != null){
            mEmptyView.setVisibility(isEmpty ? View.VISIBLE : View.INVISIBLE);
            setVisibility(isEmpty ? View.INVISIBLE: View.VISIBLE);
        }


    }



}
