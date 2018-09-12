package com.operation.resence.operationresencer;

import android.os.SystemClock;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.operation.resence.operationresencer.bean.EditTextEventBean;
import com.operation.resence.operationresencer.bean.TouchEventBean;
import com.operation.resence.operationresencer.utils.TestManager;

/**
 * Created by xuzhendong on 2018/9/10.
 */

public class ViewManager {

    /**
     * 遍历view树
     * @param view
     */
    public  static  void travelView(String page, final View view){
        if(view instanceof ViewGroup){
            for(int i = 0; i < ((ViewGroup) view).getChildCount(); i ++){
                View childView = ((ViewGroup) view).getChildAt(i);
                if(view.getTag() == null){
                    view.setTag(page + "#");
                }
                childView.setTag(view.getTag() + "-" + i);
                travelView(page, childView);
            }
            //子view数目为空，添加监听，当加入新子view时，对view进行遍历
            if(((ViewGroup) view).getChildCount() == 0) {
                view.addOnAttachStateChangeListener(new AttachListener());
            }
            HookHelper.hookOnTouchEventListener(view);
        }else {
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
                        if(TestManager.test){
                            Log.v("verf","添加 ontouch事件 ");
                            EditTextEventBean eventBean = new EditTextEventBean();
                            eventBean.setTime(SystemClock.uptimeMillis());
                            eventBean.setPageName("" + view.getTag());
                            eventBean.setTxt(((EditText) view).getText().toString());
                            TestManager.addEvent(eventBean);
                        }
//                        Log.v("verf","view id " + view.getTag() + " " + ((EditText) view).getText());
                    }
                });
            }else{
                HookHelper.hookOnTouchEventListener(view);
                if(view instanceof TextView){
//                Log.v("verf","view id " + view.getTag() + " " + ((TextView) view).getText());
                }
            }
        }
    }


    /**
     * 遍历view树,设置触屏事件
     * @param view
     */
    public  static  void setTouchEventToView(final View view, MotionEvent motionEvent, String path, String tag){
        if(tag.equals(path)){
            view.onTouchEvent(motionEvent);
        }
        if(view instanceof ViewGroup){
            String txt = "";
            for(int i = 0; i < ((ViewGroup) view).getChildCount(); i ++){
                View childView = ((ViewGroup) view).getChildAt(i);
                txt = tag + "-" + i;
                if(txt.length() <= path.length() && path.substring(0, txt.length()).equals(txt)){
                    setTouchEventToView(childView, motionEvent, path, tag + "-" + i);
                    break;
                }
            }
        }
    }

    /**
     * 遍历view树,设置文本
     * @param view
     */
    public  static  void setTextEventToView(final View view, final String txtString, String path, String tag){
        if(tag.equals(path) && view instanceof EditText){
            Log.v("kkk","settext0");
            view.post(new Runnable() {
                @Override
                public void run() {
                    Log.v("kkk","settext0000");
                    ((EditText) view).setText(txtString);
                }
            });
        }
        if(view instanceof ViewGroup){
            String txt = "";
            for(int i = 0; i < ((ViewGroup) view).getChildCount(); i ++){
                View childView = ((ViewGroup) view).getChildAt(i);
                txt = tag + "-" + i;
                if(txt.length() <= path.length() && path.substring(0, txt.length()).equals(txt)){
                    setTextEventToView(childView, txtString, path, tag + "-" + i);
                    break;
                }
            }
        }
    }


}
