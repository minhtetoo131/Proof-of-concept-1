package com.minhtetoo.proofofconcept.components;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.minhtetoo.proofofconcept.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by min on 12/14/2017.
 */

public class EmptyViewPod extends RelativeLayout {

    @BindView(R.id.iv_empty)
    ImageView ivEmpty;

    @BindView(R.id.tv_empty)
    TextView tvEmpty;

    public EmptyViewPod(Context context) {
        super(context);
    }

    public EmptyViewPod(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EmptyViewPod(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this,this);
    }
    public void setEmptyData(int res,String emptyText){
        ivEmpty.setImageResource(res);
        tvEmpty.setText(emptyText);

    }

    public void setEmptyData(String emptyText){
        tvEmpty.setText(emptyText);

    }
}

