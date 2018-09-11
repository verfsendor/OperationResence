package com.operation.resence.operationresencer;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by xuzhendong on 2018/9/10.
 */

public class ViewManager {
    /**
     * 遍历view树
     * @param view
     */
    public  static  void getEvent(String page, final View view){
        Log.v("verf","getEvent " + view.getTag() + " " + view.getClass().getSimpleName());
        if(view instanceof ViewGroup){
            for(int i = 0; i < ((ViewGroup) view).getChildCount(); i ++){
                View childView = ((ViewGroup) view).getChildAt(i);
                childView.setTag(view.getTag() == null ? page : view.getTag() + "-" + i);
                getEvent(page, childView);
            }
            if(((ViewGroup) view).getChildCount() == 0) {
                view.addOnAttachStateChangeListener(new AttachListener());
            }
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
                        Log.v("verf","view id " + view.getTag() + " " + ((EditText) view).getText());
                    }
                });
            }else if(view instanceof TextView){
                Log.v("verf","view id " + view.getTag() + " " + ((TextView) view).getText());
            }
        }
    }
}
