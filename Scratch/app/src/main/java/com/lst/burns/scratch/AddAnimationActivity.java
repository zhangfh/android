package com.lst.burns.scratch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class AddAnimationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_addanimation);

    }

    public void addAnimation(final View view){
        final Animation anim = AnimationUtils.loadAnimation(this, R.anim.fade_anim);

        anim.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                Log.i("ZFH","onAnimationStart");
                view.setHasTransientState(true);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                Log.i("ZFH","onAnimationRepeat");

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.i("ZFH","onAnimationEnd");

                view.setHasTransientState(false);
            }
        });
        view.startAnimation(anim);
    }
}