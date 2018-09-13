package com.operation.resence.operationresence.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.RelativeLayout;

import com.operation.resence.operationresence.PopWindowBuilder;
import com.operation.resence.operationresence.R;
import com.operation.resence.operationresence.databinding.LayoutPopBinding;

/**
 * Created by xuzhendong on 2018/9/7.
 */

public class SecondActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    public void showPopUpWindow(){
        PopWindowBuilder <LayoutPopBinding> windowBuilder = new PopWindowBuilder<LayoutPopBinding>(this)
                .setResId(R.layout.layout_pop)
                .configWindow(RelativeLayout.LayoutParams.MATCH_PARENT, 500)
                .setAnimiator(R.style.DialogBottom);
        windowBuilder.showAtLocation(findViewById(R.id.txt), Gravity.BOTTOM,0,0);
    }
}
