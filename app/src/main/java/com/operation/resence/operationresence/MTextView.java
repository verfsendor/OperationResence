package com.operation.resence.operationresence;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by xuzhendong on 2018/9/7.
 */

public class MTextView extends android.support.v7.widget.AppCompatTextView {
    public MTextView(Context context) {
        super(context);
    }

    public MTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.v("Hook","MTextView");
        return true;
    }
}
