package com.operation.resence.operationresencer.bean;

import java.io.Serializable;

/**
 * Created by xuzhendong on 2018/9/11.
 */

public class BaseEventBean implements Serializable{
    private long time;
    private float screenWidth;
    private float screenHeight;
    private String path;
    private String pageName;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public float getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(float screenWidth) {
        this.screenWidth = screenWidth;
    }

    public float getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(float screenHeight) {
        this.screenHeight = screenHeight;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }
}
