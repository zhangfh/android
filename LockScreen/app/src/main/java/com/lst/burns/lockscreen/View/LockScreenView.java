package com.lst.burns.lockscreen.View;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import com.lst.burns.lockscreen.R;
import com.lst.burns.lockscreen.app.App;

public class LockScreenView extends FrameLayout {
    private GestureDetector mGestureDetector;

    public LockScreenView(Context context) {
        super(context);
        Log.d("ZFH","LockScreenView construct 1");
        init();
    }

    public LockScreenView(Context context, AttributeSet attrs) {

        super(context, attrs);
        Log.d("ZFH","LockScreenView construct 2");
        init();
    }

    public LockScreenView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.d("ZFH","LockScreenView construct 3");
        init();
    }

    private void init() {

        setSystemUiVisibility(SYSTEM_UI_FLAG_IMMERSIVE_STICKY | SYSTEM_UI_FLAG_FULLSCREEN | SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        inflate(getContext(), R.layout.view_lock, this);
        Log.e("ZFH", "context is"+getContext().getClass().getCanonicalName());
        mGestureDetector = new GestureDetector(getContext(),new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                Log.d("ZFH","velocityX " +velocityX + " velocityY " + velocityY + " e1 " + e1 + " e2 " + e2);
                if (velocityX < -10.0f || velocityX > 10.0f){
                    App.unlock();
                }
                return true;

            }
        });


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }
}