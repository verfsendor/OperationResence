package com.operation.resence.operationresence;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

/**
 * PopupWindow工具类
 * Created by xuzhendong on 2018/3/14.
 */

public class PopWindowBuilder<B extends ViewDataBinding> {
    private PopupWindow mWindow;
    private Activity mActivity;
    private B binding;
    private DissmissListener listener;

    public PopWindowBuilder(Activity activity, int resId, final DissmissListener listener){
        this.mActivity = activity;
        binding = DataBindingUtil.inflate(LayoutInflater.from(mActivity), resId, null, false);
        mWindow = new PopupWindow(binding.getRoot(), RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT, true);
        mWindow.setBackgroundDrawable(new BitmapDrawable(null, ""));
        mWindow.setFocusable(true);
        mWindow.setTouchable(true);
        mWindow.setOutsideTouchable(false);
        mWindow.setAnimationStyle(R.style.DialogBottom);
        mWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
            listener.dissmiss();
            backAlpha(1);
            }
        });
    }

    public PopWindowBuilder(Activity activity){
        this.mActivity = activity;
    }

    public PopWindowBuilder setResId(int resId){
        binding = DataBindingUtil.inflate(LayoutInflater.from(mActivity), resId, null, false);
        return this;
    }

    public PopWindowBuilder configWindow(int width, int height) {
        mWindow = new PopupWindow(binding.getRoot(), width, height, true);
        mWindow.setBackgroundDrawable(new BitmapDrawable(null, ""));
        mWindow.setFocusable(true);
        mWindow.setTouchable(true);
        mWindow.setOutsideTouchable(false);
        mWindow.setAnimationStyle(R.style.DialogBottom);
        mWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                if(listener != null) {
                    listener.dissmiss();
                }
                backAlpha(1);
            }
        });
        return this;
    }

    public PopWindowBuilder setDissmissListener(DissmissListener listener){
        this.listener = listener;
        return this;
    }

    public PopWindowBuilder setAnimiator(int animiate){
        //R.style.DialogBottom
        if(mWindow != null) {
            mWindow.setAnimationStyle(animiate);//
        }
        return this;
    }

    public void showAtLocation(View view, int grativity, int x, int y){
        InputMethodManager imm = (InputMethodManager)view.getContext().getSystemService( Context.INPUT_METHOD_SERVICE );
        if ( imm.isActive( ) ) {
            imm.hideSoftInputFromWindow( view.getApplicationWindowToken( ) , 0 );
        }
        backAlpha(0.7f);
        if(mWindow != null) {
            mWindow.showAtLocation(view, grativity, x, y);
        }
    }

    public void dismiss(){
        if(listener != null) {
            listener.dissmiss();
        }
        if(mWindow != null){
            mWindow.dismiss();
        }

    }



    public PopupWindow getmWindow(){
        return mWindow;
    }

    public B getBinding(){
        return binding;
    }

    /**
     * 改变窗口的透明度
     *
     * @param alpha
     */
    public void backAlpha(float alpha) {
        //产生背景变暗效果
        WindowManager.LayoutParams lp = mActivity.getWindow().getAttributes();
        lp.alpha = alpha;
        mActivity.getWindow().setAttributes(lp);
    }

    public interface DissmissListener{
        void dissmiss();
    }
}
