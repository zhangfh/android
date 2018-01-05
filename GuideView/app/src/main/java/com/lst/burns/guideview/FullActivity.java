package com.lst.burns.guideview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FullActivity extends AppCompatActivity {

    private Button header_imgbtn;
    Guide guide;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_guide_view);
        header_imgbtn = (Button) findViewById(R.id.header_imgbtn);
        header_imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                Toast.makeText(FullActivity.this, "show more", Toast.LENGTH_SHORT).show();
            }
        });
        header_imgbtn.post(new Runnable() {
            @Override public void run() {
                showGuideView();
            }
        });
    }

    public void showGuideView() {

        GuideBuilder builder = new GuideBuilder();

        builder.setTargetView(header_imgbtn)
                .setAlpha(150)
                .setHighTargetCorner(20)
                .setHighTargetPadding(10)
                .setOverlayTarget(false)
                .setOutsideTouchable(false);

        builder.setOnVisibilityChangedListener(new GuideBuilder.OnVisibilityChangedListener() {
            @Override public void onShown() {
                Log.d("ZFH","OnVisibilityChangedListener onShown");
            }

            @Override public void onDismiss() {
                Log.d("ZFH","OnVisibilityChangedListener onDismiss");
            }
        });

        builder.addComponent(new SimpleComponent());

        guide = builder.createGuide();
        guide.setShouldCheckLocInWindow(true);
        guide.show(this);
    }
}