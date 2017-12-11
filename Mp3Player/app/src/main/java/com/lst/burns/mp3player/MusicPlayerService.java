package com.lst.burns.mp3player;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MusicPlayerService extends Service implements MusicPlayerServiceInterface{

    public final static int PAUSED = 0;
    public final static int PLAYING = 1;

    private int state;

    private MusicPlayerServiceBinder mMusicPlayerServiceBinder;
    private Queue mNowPlaying;
    private MediaPlayer mMediaPlayer;
    private MediaPlayer.OnCompletionListener mCompletionListener;

    private HeadPhoneBroadcastReceiver mHeadPhoneBroadcastReceiver;
    private SeekBar mSeekBar;
    private TextView mTextView;//music title

    private AsyncTask<Void, Integer, Void> seekBarChanger;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        mMusicPlayerServiceBinder = new MusicPlayerServiceBinder(this, this);
        state = PLAYING;

        mNowPlaying = new Queue();			// setup the now playing queue
        mMediaPlayer = new MediaPlayer();	// setup the media player

        mCompletionListener = new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {
                playNext();
            }
        };
        mMediaPlayer.setOnCompletionListener(mCompletionListener);

        mHeadPhoneBroadcastReceiver = new HeadPhoneBroadcastReceiver();
        registerReceiver(mHeadPhoneBroadcastReceiver, new IntentFilter(Intent.ACTION_HEADSET_PLUG));
        mHeadPhoneBroadcastReceiver.registerMusicPlayerService(this);

        return mMusicPlayerServiceBinder;
    }
    public void addMusicToQueue(Music music) {
        mNowPlaying.addMusicToQueue(music);
    }

    public void addMusicToQueue(List<Music> music) {
        mNowPlaying.addMusicToQueue(music);
    }

    public void changeQueue(ArrayList<Music> list) {
        mNowPlaying.clearQueue();
        mNowPlaying.addMusicToQueue(list);
    }

    public synchronized void play() {
        state = PLAYING;
        mMediaPlayer.start();
    }

    public synchronized void play(int position) {
        playFetched(mNowPlaying.playGet(position).getMusicLocation());
    }

    public synchronized void playNext() {
        Log.d("ZFH","playNext");
        if(mNowPlaying.next() != null) {
            Log.d("ZFH","playFetched");
            playFetched(mNowPlaying.next().getMusicLocation());
        }
    }

    private synchronized void playFetched(String path) {
        Log.d("ZFH","playFetched start");
        state = PLAYING;
        mMediaPlayer.stop();
        mMediaPlayer.reset();
        try {
            mMediaPlayer.setDataSource(path);

            mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

                @Override
                public void onPrepared(MediaPlayer mp) {
                    int totalTime = mNowPlaying.getCurrentlyPlaying().getTime();
                    mSeekBar.setMax(totalTime*1000);
                    mTextView.setText(mNowPlaying.getCurrentlyPlaying().getName());
                    int minutes = totalTime/60, seconds = totalTime%60;
                    if (seconds >= 10) mMusicPlayerServiceBinder.setTotalTime("/ " + minutes + ":" + seconds);
                    else mMusicPlayerServiceBinder.setTotalTime("/ " + minutes + ":0" + seconds);

                    play();
                    setSeekBarTracker();
                }
            });

            mMediaPlayer.prepare();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setSeekBarTracker() {
        if (seekBarChanger != null)
            seekBarChanger.cancel(false);
        seekBarChanger = null;
        seekBarChanger = new AsyncTask<Void, Integer, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                while (mMediaPlayer != null && mMediaPlayer.getCurrentPosition() < mMediaPlayer.getDuration()){
                    if (state == PLAYING){
                        int currentPosition = mMediaPlayer.getCurrentPosition();
                       // mSeekBar.setProgress(currentPosition);




            			int minutes = currentPosition/60, seconds = currentPosition%60;
                        publishProgress(currentPosition,minutes,seconds);
//                    	if (seconds >= 10) mMusicPlayerServiceBinder.setCurrentTime(minutes + ":" + seconds);
//                    	else mMusicPlayerServiceBinder.setCurrentTime(minutes + ":0" + seconds);
                    }

                    try { Thread.sleep(100); } catch (InterruptedException e) {}
                }
                return null;
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                super.onProgressUpdate(values);
                int currentProgress = values[0];//progress
                mSeekBar.setProgress(currentProgress);
                int maxProgress = mSeekBar.getMax();

                int totalTime = mNowPlaying.getCurrentlyPlaying().getTime();

                int currentTime = (int)((float)currentProgress/maxProgress  * totalTime);

                int minutes = currentTime/60, seconds = currentTime%60;
               // int minutes = values[1];
               // int seconds = values[2];



                if (seconds >= 10)
                    mMusicPlayerServiceBinder.setCurrentTime(minutes + ":" + seconds);
                else
                    mMusicPlayerServiceBinder.setCurrentTime(minutes + ":0" + seconds);

            }

        };
        seekBarChanger.execute();
    }

    public void pause() {
        state = PAUSED;
        mMediaPlayer.pause();
    }

    public int changeState() {
        switch(state){
            case PLAYING:
                pause(); break;
            case PAUSED:
                play(); break;
        }

        return state;									// return the value of the changed state as confirmation
    }

    public int getState() {
        return state;
    }

    @Override
    public void removeMusicFromQueue(Music music) {
        mNowPlaying.removeMusicFromQueue(music);
    }

    @Override
    public void skipToPoint(int point) {
        mMediaPlayer.seekTo(point);
    }
    @Override
    public boolean onUnbind(Intent intent) {
        unregisterReceiver(mHeadPhoneBroadcastReceiver);


        if (seekBarChanger != null)
            seekBarChanger.cancel(false);
        seekBarChanger = null;

        mMediaPlayer.stop();
        mMediaPlayer.reset();
        mMediaPlayer.release();
        Toast.makeText(this, "unBind with state: " + ((state == PLAYING) ? "PLAYING" : "PAUSED"), Toast.LENGTH_SHORT).show();
        return true;
    }

    public void registerSeekBar(SeekBar mSeekBar) {
        this.mSeekBar = mSeekBar;
    }
    public  void registerTextView(TextView mTextView){
        this.mTextView = mTextView;
    }

    public synchronized void playLast() {
        //FIXME There is a bug here.
        playFetched(mNowPlaying.last().getMusicLocation());
    }

}