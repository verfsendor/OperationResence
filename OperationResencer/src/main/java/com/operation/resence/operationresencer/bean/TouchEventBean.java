package com.operation.resence.operationresencer.bean;

import android.view.MotionEvent;

/**
 * Created by xuzhendong on 2018/9/11.
 */

public class TouchEventBean extends BaseEvent{
    private int action;
    private float rawX;
    private float rawY;

    public int getAction() {
        return action;
    }

    public void setAction(int type) {
        this.action = type;
    }

    public float getRawX() {
        return rawX;
    }

    public void setRawX(float rawX) {
        this.rawX = rawX;
    }

    public float getRawY() {
        return rawY;
    }

    public void setRawY(float rawY) {
        this.rawY = rawY;
    }

    public String getActionTxt(){
        String txt = "";
        switch (action){
            case 0:
                txt = "ACTION_DOWN" ;
                break;
            case 1:
                txt = "ACTION_UP";
                break;
            case 2:
                txt = "ACTION_MOVE";
                break;

        }
        return txt;
    }
}
