package com.operation.resence.operationresence.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;

import com.diy.charts.beans.BarChartBean;
import com.operation.resence.operationresence.PopWindowBuilder;
import com.operation.resence.operationresence.R;
import com.operation.resence.operationresence.databinding.LayoutPopBinding;

/**
 * Created by xuzhendong on 2018/9/7.
 */

public class PopwindowActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v("popwindowing","mainactivity onCreate");
        setContentView(R.layout.activity_second);
        findViewById(R.id.btn_show_pop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopUpWindow();
            }
        });
        findViewById(R.id.btn_show_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
        findViewById(R.id.btn_show_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PopwindowActivity.this,fifthActivity.class));
            }
        });
    }

    public void showPopUpWindow(){
        PopWindowBuilder <LayoutPopBinding> windowBuilder = new PopWindowBuilder<LayoutPopBinding>(this)
                .setResId(R.layout.layout_pop)
                .configWindow(RelativeLayout.LayoutParams.MATCH_PARENT, 500)
                .setAnimiator(R.style.DialogBottom);
        windowBuilder.showAtLocation(getWindow().getDecorView(), Gravity.BOTTOM,0,0);
    }

    public void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("title")
                .setMessage("message")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        builder.create();
        builder.show();
    }

    @Override
    protected void onPause() {
        Log.v("popwindowing","mainactivity onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.v("popwindowing","mainactivity onstop");
        super.onStop();
    }
    @Override
    protected void onDestroy() {
        Log.v("popwindowing","mainactivity onDestroy");
        super.onDestroy();
    }
}
