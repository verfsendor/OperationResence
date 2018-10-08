package com.operation.resence.operationresencer.listener;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.operation.resence.operationresencer.utils.ViewHelper;

/**
 * Created by xuzhendong on 2018/9/18.
 * recyclerView中监听child的变化
 */

public class ChildAttachChangeListener implements RecyclerView.OnChildAttachStateChangeListener {
    RecyclerView recyclerView;
    public ChildAttachChangeListener(RecyclerView view){
        this.recyclerView = view;
    }

    @Override
    public void onChildViewAttachedToWindow(View view) {
            view.setTag("" + recyclerView.getTag() + "-" + recyclerView.getChildLayoutPosition(view));
            ViewHelper.travelView("" , view);
    }

    @Override
    public void onChildViewDetachedFromWindow(View view) {

    }
}
