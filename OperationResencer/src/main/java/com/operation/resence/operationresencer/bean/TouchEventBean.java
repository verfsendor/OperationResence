package com.operation.resence.operationresencer.bean;

import android.os.SystemClock;
import android.util.Log;
import android.view.InputDevice;
import android.view.MotionEvent;

/**
 * Created by xuzhendong on 2018/9/11.
 */

public class TouchEventBean extends BaseEvent{
    private int action;
    private float rawX;
    private float rawY;
    private int pointerCount;
    private float pressure;
    private float size;
    private float xPrecision;
    private float yPrecision;
    private int deviceId;
    private int edgeFlags;
    private float x1;
    private float y1;
    private float x2;
    private float y2;
    private int pid1;
    private int pid2;
    /**
     *    long downTime, long eventTime,
     int action, int pointerCount, int[] pointerIds, PointerCoords[] pointerCoords,
     int metaState, float xPrecision, float yPrecision, int deviceId,
     int edgeFlags, int source, int flags)

     long downTime, long eventTime, int action,
     float x, float y, float pressure, float size, int metaState,
     float xPrecision, float yPrecision, int deviceId, int edgeFlags)

     (long downTime, long eventTime,
     int action, int pointerCount, PointerProperties[] pointerProperties, MotionEvent.PointerCoords[] pointerCoords,
     int metaState, int buttonState, float xPrecision, float yPrecision, int deviceId, int edgeFlags,
     int source, int flags)



     long downTime, long eventTime, int action, int pointerCount, int[] pointerIds,
     MotionEvent.PointerCoords[] pointerCoords, int metaState, float xPrecision, float yPrecision, int deviceId,
     int edgeFlags, int source, int flags)

     /**
     * (long downTime, long eventTime,
     int action, int pointerCount, PointerProperties[] pointerProperties, MotionEvent.PointerCoords[] pointerCoords,
     int metaState, int buttonState, float xPrecision, float yPrecision, int deviceId, int edgeFlags,
     int source, int flags)
     */

    /**
     * 根据touchEventBean生成MotionEvent
     * @return
     */
    public MotionEvent toMotionEvent(long downTime){

//         long downTime, long eventTime, int action, int pointerCount, float x, float y,
//         float pressure, float size, int metaState, float xPrecision, float yPrecision,
//         int deviceId, int edgeFlags)

        if(pointerCount > 1){
            MotionEvent.PointerProperties[] pointerProperties = new MotionEvent.PointerProperties[2];
            MotionEvent.PointerCoords[] pointerCoords = new MotionEvent.PointerCoords[2];
            MotionEvent.PointerCoords pointerCoord1 = new MotionEvent.PointerCoords();
            pointerCoord1.pressure = 1;
            pointerCoord1.x = getX1();//x坐标
            pointerCoord1.y = getY1();//yx坐标
            pointerCoords[0] = pointerCoord1;
            MotionEvent.PointerCoords pointerCoord2 = new MotionEvent.PointerCoords();
            pointerCoord2.pressure = 1;
            pointerCoord2.x = getX2();//x坐标
            pointerCoord2.y = getY2();//yx坐标
            pointerCoords[1] = pointerCoord2;

            MotionEvent.PointerProperties pointerPropertie1 = new MotionEvent.PointerProperties();
            pointerPropertie1.id = getPid1();//保存的id
            pointerPropertie1.toolType = 1;
            pointerProperties[0] = pointerPropertie1;
            MotionEvent.PointerProperties pointerPropertie2 = new MotionEvent.PointerProperties();
            pointerPropertie2.id = getPid2();//保存的id
            pointerPropertie2.toolType = 1;
            pointerProperties[1] = pointerPropertie2;
            return MotionEvent.obtain(downTime, SystemClock.uptimeMillis(), getAction(), 2,
                    pointerProperties, pointerCoords,0, 0, 1, 1, 0, 0, InputDevice.SOURCE_TOUCHSCREEN, 0);
        }else {
            return MotionEvent.obtain(downTime,
                    SystemClock.uptimeMillis(), getAction(), getRawX(), getRawY(), 0);
        }
    }

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

    public int getPointerCount() {
        return pointerCount;
    }

    public void setPointerCount(int pointerCount) {
        this.pointerCount = pointerCount;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public float getxPrecision() {
        return xPrecision;
    }

    public void setxPrecision(float xPrecision) {
        this.xPrecision = xPrecision;
    }

    public float getyPrecision() {
        return yPrecision;
    }

    public void setyPrecision(float yPrecision) {
        this.yPrecision = yPrecision;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public int getEdgeFlags() {
        return edgeFlags;
    }

    public void setEdgeFlags(int adgeFlags) {
        this.edgeFlags = adgeFlags;
    }

    public float getX1() {
        return x1;
    }

    public void setX1(float x1) {
        this.x1 = x1;
    }

    public float getY1() {
        return y1;
    }

    public void setY1(float y1) {
        this.y1 = y1;
    }

    public float getX2() {
        return x2;
    }

    public void setX2(float x2) {
        this.x2 = x2;
    }

    public float getY2() {
        return y2;
    }

    public void setY2(float y2) {
        this.y2 = y2;
    }

    public int getPid1() {
        return pid1;
    }

    public void setPid1(int pid1) {
        this.pid1 = pid1;
    }

    public int getPid2() {
        return pid2;
    }

    public void setPid2(int pid2) {
        this.pid2 = pid2;
    }
}
