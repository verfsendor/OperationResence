package com.operation.resence.operationresencer.utils;

/**
 * Created by xuzhendong on 2018/9/11.
 */

public class Util {

    public static String getActionTxt(int action){
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
            case 5:
                txt = "ACTION_POINTER_DOWN";
                break;
            case 6:
                txt = "ACTION_POINTER_UP";
                break;
        }
        return txt;
    }
}
