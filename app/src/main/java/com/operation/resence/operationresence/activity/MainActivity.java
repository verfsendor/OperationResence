package com.operation.resence.operationresence.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.operation.resence.operationresence.R;
import com.operation.resence.operationresencer.utils.OperationResencer;

public class MainActivity extends AppCompatActivity {
    int i = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.txt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MyFragmentActivity.class));
            }
        });
        findViewById(R.id.txt).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Toast.makeText(MainActivity.this,"skkgkg " + i ,Toast.LENGTH_LONG).show();
                i ++;
                return false;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v("verf","onResume size " + OperationResencer.events.size());
        if(OperationResencer.events.size() > 0){
            OperationResencer.startTest();
        }
    }

    @Override
    protected void onStop() {
        Log.v("verf","mainactivity onstop");
        super.onStop();
    }
    @Override
    protected void onDestroy() {
        Log.v("verf","mainactivity onDestroy");
        super.onDestroy();
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }
}
