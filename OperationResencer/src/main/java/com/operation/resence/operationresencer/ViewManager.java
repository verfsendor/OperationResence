package com.operation.resence.operationresencer;

import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.operation.resence.operationresencer.bean.BaseEvent;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * Created by xuzhendong on 2018/9/10.
 */

public class ViewManager {

    /**
     * 遍历view树
     * @param view
     */
    public  static  void getEvent(String page, final View view){
        if(view instanceof ViewGroup){
            for(int i = 0; i < ((ViewGroup) view).getChildCount(); i ++){
                View childView = ((ViewGroup) view).getChildAt(i);
                if(view.getTag() == null){
                    view.setTag(page + "#");
                }
                childView.setTag(view.getTag() + "-" + i);
                getEvent(page, childView);
            }
            //子view数目为空，添加监听，当加入新子view时，对view进行遍历
            if(((ViewGroup) view).getChildCount() == 0) {
                view.addOnAttachStateChangeListener(new AttachListener());
            }
//            HookHelper.hookOnTouchEventListener(view);
        }else {
//            HookHelper.hookOnTouchEventListener(view);
            if(view instanceof EditText){
                ((EditText) view).addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
//                        Log.v("verf","view id " + view.getTag() + " " + ((EditText) view).getText());
                    }
                });
            }else if(view instanceof TextView){
//                Log.v("verf","view id " + view.getTag() + " " + ((TextView) view).getText());
            }
        }
    }


    /**
     * 遍历view树,设置事件
     * @param view
     */
    public  static  void setEventToView(final View view, MotionEvent motionEvent, String path, String tag){
        if(tag.equals(path)){
            view.onTouchEvent(motionEvent);
        }
        if(view instanceof ViewGroup){
            String txt = "";
            for(int i = 0; i < ((ViewGroup) view).getChildCount(); i ++){
                View childView = ((ViewGroup) view).getChildAt(i);
                txt = tag + "-" + i;
                if(txt.length() <= path.length() && path.substring(0, txt.length()).equals(txt)){
                    setEventToView(childView, motionEvent, path, tag + "-" + i);
                    break;
                }
            }
        }
    }


}
