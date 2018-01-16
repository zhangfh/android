package com.lst.burns.scratch;

import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lst.burns.scratch.service.LogProcessor;
import com.lst.burns.scratch.util.LogFormattedString;

import java.io.File;
import java.util.ArrayList;

public class LogActivity extends AppCompatActivity  {

    private ListView mListView;
    private LoggerListAdapter mAdapter;
    private LayoutInflater mInflater;

    private int mLogType = 0; //represent types
    final char[] mFilters = {'D', 'E', 'I', 'V', 'W'};
    final CharSequence[] items = {"Debug", "Error", "Info", "Verbose", "Warn", "All"};
    private int mFilter = -1;//represent mFilters amd items
    private String mFilterTag = "";

    private ILogProcessor mService;
    private boolean mServiceRunning = false;
    private ProgressDialog mProgressDialog;

    private ServiceConnection mConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {
            mService = ILogProcessor.Stub.asInterface((IBinder)service);
            LogProcessor.setHandler(mHandler);

            try {
                Log.i("ZFH","Start run LogService");
                mService.run(mLogType);
                mServiceRunning = true;
            } catch (RemoteException e) {
                Log.e("Logger", "Could not start logging");
            }
        }

        public void onServiceDisconnected(ComponentName className) {
            Log.i("Logger", "onServiceDisconnected has been called");
            mService = null;
        }
    };

    public Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case LogProcessor.MSG_READ_FAIL:
                    Log.d("Logger", "MSG_READ_FAIL");
                    break;
                case LogProcessor.MSG_LOG_FAIL:
                    Log.d("Logger", "MSG_LOG_FAIL");
                    break;
                case LogProcessor.MSG_NEW_LINE:
                    mAdapter.addLine((String) msg.obj);
                    break;
                case LogProcessor.MSG_LOG_SAVE:
                    saveResult((String) msg.obj);
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedinstance){
        super.onCreate(savedinstance);
        setContentView(R.layout.activity_logger);
        Log.i("ZFH","Log activity");
        mListView = (ListView)findViewById(R.id.list_log);

        mListView.setStackFromBottom(true);
        mListView.setTranscriptMode(ListView.TRANSCRIPT_MODE_NORMAL);
        mListView.setDividerHeight(0);

        mAdapter = new LoggerListAdapter(this);
        mListView.setAdapter(mAdapter);


    }

    @Override
    public void onResume() {
        super.onResume();
        bindService(new Intent(this, LogProcessor.class), mConnection, Context.BIND_AUTO_CREATE);

        //TODO: make sure this actually deletes and doesn't append.
        File f = new File("/sdcard/tmp.log");
        if (f.exists()) {
            f.deleteOnExit();
        }

    }
    @Override
    public void onPause() {
        super.onPause();
        unbindService(mConnection);
    }
    private ListView getListView() {
            return mListView;
    }

    private void saveResult(String msg) {
        mProgressDialog.dismiss();

        if (msg.equals("error")) {
            Toast.makeText(this, "Error while saving the log to file!", Toast.LENGTH_LONG).show();
        } else if (msg.equals("saved")) {
            Toast.makeText(this, "Log has been saved to file.", Toast.LENGTH_LONG).show();
        } else if (msg.equals("attachment")) {
            Intent mail = new Intent(Intent.ACTION_SEND);
            mail.setType("text/plain");
            mail.putExtra(Intent.EXTRA_SUBJECT, "Logger Debug Output");
            mail.putExtra(Intent.EXTRA_STREAM, Uri.parse("file:///sdcard/tmp.log"));
            mail.putExtra(Intent.EXTRA_TEXT, "Here's the output from my log file. Thanks!");
            startActivity(Intent.createChooser(mail, "Email:"));
        }
    }
    /*
  * This is the list adapter for the Logger, it holds an array of strings and adds them
  * to the list view recycling views for obvious performance reasons.
*/
    public class LoggerListAdapter extends BaseAdapter {
        private Context mContext;
        private ArrayList<String> mLines;

        public LoggerListAdapter(Context c) {
            mContext = c;
            mLines = new ArrayList<String>();
            mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        @Override
        public int getCount() {
            return mLines.size();
        }

        @Override
        public Object getItem(int i) {
            return mLines.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            TextView holder;
            String line = mLines.get(i);

            if (view == null) {
                //inflate the view here because there's no existing view object.
                view = mInflater.inflate(R.layout.log_item, viewGroup, false);

                holder = (TextView) view.findViewById(R.id.log_line);
                holder.setTypeface(Typeface.MONOSPACE);

                view.setTag(holder);
            } else {
                holder = (TextView) view.getTag();
            }

            if (mLogType == 0) {
                holder.setText(new LogFormattedString(line));
            } else {
                holder.setText(line);
            }

            final boolean autoscroll =
                    (getListView().getScrollY() + getListView().getHeight() >= getListView().getBottom()) ? true : false;

            if (autoscroll) {
                getListView().setSelection(mLines.size() - 1);
            }

            return view;
        }


        public void addLine(String line) {
            if (mFilter != -1 && line.charAt(0) != mFilters[mFilter]) {
                return;
            }

            if (!mFilterTag.equals("")) {
                String tag = line.substring(2, line.indexOf("("));

                if (!mFilterTag.toLowerCase().equals(tag.toLowerCase().trim())) {
                    return;
                }
            }

            mLines.add(line);
            notifyDataSetChanged();
        }

        public void resetLines() {
            mLines.clear();
            notifyDataSetChanged();
        }

        public void updateView() {
            notifyDataSetChanged();
        }
    }



}