package com.operation.resence.operationresence.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.operation.resence.operationresence.Fragment.FirstFragment;
import com.operation.resence.operationresence.R;
import com.operation.resence.operationresence.utils.FragmentUtils;

/**
 * Created by xuzhendong on 2018/9/10.
 */
public class ThirdActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        findViewById(R.id.txt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ThirdActivity.this, FourthActivity.class));
            }
        });

        FragmentUtils.replaceFragmentWithAnim(getSupportFragmentManager(), FirstFragment.class, R.id.frame, null);
    }
}