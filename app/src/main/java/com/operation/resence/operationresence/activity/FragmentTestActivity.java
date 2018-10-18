package com.operation.resence.operationresence.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.operation.resence.operationresence.Fragment.FirstFragment;
import com.operation.resence.operationresence.Fragment.SecondFragment;
import com.operation.resence.operationresence.R;
import com.operation.resence.operationresence.utils.FragmentUtils;
import com.operation.resence.operationresencer.proxy.OnHierarchyChangeListenerProxy;
import com.operation.resence.operationresencer.utils.HookHelper;

import java.lang.reflect.Field;

/**
 * Created by xuzhendong on 2018/9/10.
 */
public class FragmentTestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        findViewById(R.id.txt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FragmentTestActivity.this, PopwindowActivity.class));
            }
        });
        findViewById(R.id.btn_change).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentUtils.replaceFragmentWithAnim(getSupportFragmentManager(), SecondFragment.class, R.id.frame1, null);
            }
        });
        FrameLayout frameLayout = (FrameLayout)findViewById(R.id.frame2);
        frameLayout.setOnHierarchyChangeListener(new OnHierarchyChangeListenerProxy(null));

        FragmentUtils.replaceFragmentWithAnim(getSupportFragmentManager(), FirstFragment.class, R.id.frame1, null);
        FragmentUtils.replaceFragmentWithAnim(getSupportFragmentManager(), FirstFragment.class, R.id.frame2, null);
        FragmentUtils.replaceFragmentWithAnim(getSupportFragmentManager(), FirstFragment.class, R.id.frame3, null);
    }


    @Override
    protected void onResume() {
        super.onResume();
        ViewGroup viewGroup1 = (ViewGroup)findViewById(R.id.frame1);
        ViewGroup viewGroup2 = (ViewGroup)findViewById(R.id.frame2);
        ViewGroup viewGroup3 = (ViewGroup)findViewById(R.id.frame3);
        Log.v("fragmenthook","设值情况 " + HookHelper.getOnHierarchyChangeListener(viewGroup1) + " "
        + HookHelper.getOnHierarchyChangeListener(viewGroup2) + " " + HookHelper.getOnHierarchyChangeListener(viewGroup3));
    }
}