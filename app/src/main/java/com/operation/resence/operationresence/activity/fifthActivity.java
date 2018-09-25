package com.operation.resence.operationresence.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.operation.resence.operationresence.R;

/**
 * Created by xuzhendong on 2018/9/10.
 */

public class fifthActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth);
        findViewById(R.id.txt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(fifthActivity.this, ListViewActivity.class));
            }
        });
        findViewById(R.id.item4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(fifthActivity.this,"点击item4",Toast.LENGTH_LONG).show();
            }
        });
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        TouchEventBean bean1 = new Gson().fromJson("{\n" +
//                "  \"action\": 0,\n" +
//                "  \"rawX\": 704.0,\n" +
//                "  \"rawY\": 1496.0,\n" +
//                "  \"pageName\": \"fifthActivity#-0-1-0-0-0-2\",\n" +
//                "  \"screenHeight\": 0.0,\n" +
//                "  \"screenWidth\": 0.0,\n" +
//                "  \"time\": 177204680\n" +
//                "}",TouchEventBean.class);
//
//        TouchEventBean bean2 = new Gson().fromJson("{\n" +
//                "  \"action\": 2,\n" +
//                "  \"rawX\": 704.0,\n" +
//                "  \"rawY\": 1496.0,\n" +
//                "  \"pageName\": \"fifthActivity#-0-1-0-0-0-2\",\n" +
//                "  \"screenHeight\": 0.0,\n" +
//                "  \"screenWidth\": 0.0,\n" +
//                "  \"time\": 177204704\n" +
//                "}",TouchEventBean.class);
//
//        TouchEventBean bean3 = new Gson().fromJson("{\n" +
//                "  \"action\": 2,\n" +
//                "  \"rawX\": 704.0,\n" +
//                "  \"rawY\": 1496.0,\n" +
//                "  \"pageName\": \"fifthActivity#-0-1-0-0-0-2\",\n" +
//                "  \"screenHeight\": 0.0,\n" +
//                "  \"screenWidth\": 0.0,\n" +
//                "  \"time\": 177204738\n" +
//                "}",TouchEventBean.class);
//
//        TouchEventBean bean4 = new Gson().fromJson("{\n" +
//                "  \"action\": 1,\n" +
//                "  \"rawX\": 704.0,\n" +
//                "  \"rawY\": 1496.0,\n" +
//                "  \"pageName\": \"fifthActivity#-0-1-0-0-0-2\",\n" +
//                "  \"screenHeight\": 0.0,\n" +
//                "  \"screenWidth\": 0.0,\n" +
//                "  \"time\": 177204746\n" +
//                "}",TouchEventBean.class);
//        String[] path = bean1.getPageName().split("#");
//        long time =  SystemClock.uptimeMillis();
//
//        MotionEvent motionEvent1 = MotionEvent.obtain( time,
//                SystemClock.uptimeMillis(), bean1.getAction(), bean1.getRawX(), bean1.getRawY(), 0);
//        //派发事件
//        ViewManager.setTouchEventToView(Constants.nowActivityName.getWindow().getDecorView(),motionEvent1,path[0],path[1],"");
//
//        MotionEvent motionEvent2 = MotionEvent.obtain( time,
//                SystemClock.uptimeMillis(), bean2.getAction(), bean2.getRawX(), bean2.getRawY(), 0);
//        //派发事件
//        ViewManager.setTouchEventToView(Constants.nowActivityName.getWindow().getDecorView(),motionEvent2,path[0],path[1],"");
//
//        MotionEvent motionEvent3 = MotionEvent.obtain( time,
//                SystemClock.uptimeMillis(), bean3.getAction(), bean3.getRawX(), bean3.getRawY(), 0);
//        //派发事件
//        ViewManager.setTouchEventToView(Constants.nowActivityName.getWindow().getDecorView(),motionEvent3,path[0],path[1],"");
//
//        MotionEvent motionEvent4 = MotionEvent.obtain( time,
//                SystemClock.uptimeMillis(), bean4.getAction(), bean4.getRawX(), bean4.getRawY(), 0);
//        //派发事件
//        ViewManager.setTouchEventToView(Constants.nowActivityName.getWindow().getDecorView(),motionEvent4,path[0],path[1],"");
//    }
}