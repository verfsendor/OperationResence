package com.operation.resence.operationresence.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import com.operation.resence.operationresence.R;
import com.operation.resence.operationresencer.utils.TestManager;
import com.operation.resence.operationresencer.utils.Util;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.txt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, fifthActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v("verf","onResume size " + TestManager.events.size());
        if(TestManager.events.size() > 0){
            TestManager.startTest();
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
//        findViewById(R.id.clicktxt).onTouchEvent(ev);
        return super.dispatchTouchEvent(ev);
    }
}
