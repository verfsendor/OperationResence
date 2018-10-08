package com.operation.resence.operationresencer.listener;

import android.os.SystemClock;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import com.operation.resence.operationresencer.bean.EditTextEventBean;
import com.operation.resence.operationresencer.OperationResencer;

/**
 * Created by xuzhendong on 2018/9/14.
 * 记录输入框中的输入事件
 */

public class TextChangeWatcher implements TextWatcher {
    View view;

    public TextChangeWatcher(View view){
        this.view = view;
    }
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if(OperationResencer.isRecording()){
            EditTextEventBean eventBean = new EditTextEventBean();
            eventBean.setTime(SystemClock.uptimeMillis());
            eventBean.setPageName("" + view.getTag());
            eventBean.setTxt(((EditText) view).getText().toString());
            OperationResencer.addEvent(eventBean);
        }
    }
}
