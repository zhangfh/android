package com.lst.burns.scratch;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class SingleFragment extends Fragment {

    private ImageButton mPlayPause;
    private TextView mTitle;
    private TextView mSubtitle;
    private TextView mExtraInfo;
    private ImageView mAlbumArt;
    private String mArtUrl;


    private final View.OnClickListener mButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.play_pause:
                    Log.d("ZFH", "Play button pressed, in state " );

                    break;
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_playback_controls, container, false);

        mPlayPause = (ImageButton) rootView.findViewById(R.id.play_pause);
        mPlayPause.setEnabled(true);
        mPlayPause.setOnClickListener(mButtonListener);

        mTitle = (TextView) rootView.findViewById(R.id.title);
        mSubtitle = (TextView) rootView.findViewById(R.id.artist);
        mExtraInfo = (TextView) rootView.findViewById(R.id.extra_info);
        mAlbumArt = (ImageView) rootView.findViewById(R.id.album_art);
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ZFH","fragment is clicked");
            }
        });
       // mTitle.setText("Title");
        return rootView;
    }



    @Override
    public void onStart() {
        super.onStart();
        Log.d("ZFH", "fragment.onStart");

    }
    @Override
    public void onStop() {
        super.onStop();

        Log.d("ZFH", "fragment.onStop");

    }
}