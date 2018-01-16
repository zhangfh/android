package com.lst.burns.scratch;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.lst.burns.scratch.Object.Planet;

import java.util.List;

public class GridViewActivity extends AppCompatActivity {
    private GridView mGridView;
    private List<ResolveInfo> mApps;
    static  AppsAdapter.PlanetHolder  holder = null;
    private AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //事件内容....
            Log.d("ZFH","click grid item position: " + position);
            ResolveInfo info = mApps.get(position);

            //该应用的包名
            String pkg = info.activityInfo.packageName;
            //应用的主activity类
            String cls = info.activityInfo.name;
            Log.d("ZFH","pkg " + pkg + " cls " + cls );
            ComponentName componet = new ComponentName(pkg, cls);

            Intent i = new Intent();
            i.setComponent(componet);
            startActivity(i);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_gridview);
        mGridView = (GridView)findViewById(R.id.apps_list);
        loadApps();
        mGridView.setAdapter(new AppsAdapter());
        mGridView.setOnItemClickListener(listener);
    }

    private void loadApps() {
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);

        mApps = getPackageManager().queryIntentActivities(mainIntent, 0);
    }

    public class AppsAdapter extends BaseAdapter {

        public AppsAdapter() {
        }

        @Override
        public int getCount() {
            return mApps.size();
        }

        @Override
        public Object getItem(int i) {
            return mApps.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            //ImageView iimage;
            View v = view;
            if (holder == null)
                holder = new PlanetHolder();
            Log.d("ZFH", "holder pointer " + holder);
            if (view == null) {

               // iimage = new ImageView(GridViewActivity.this);
               // iimage.setScaleType(ImageView.ScaleType.FIT_CENTER);
               // iimage.setLayoutParams(new GridView.LayoutParams(150, 150));


                // This a new view we inflate the new layout
                LayoutInflater inflater = (LayoutInflater) GridViewActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                v = inflater.inflate(R.layout.row_app_info_layout, null);
                // Now we can fill the layout with the right values
                ImageView app_icon = (ImageView) v.findViewById(R.id.app_img);
                TextView app_name = (TextView) v.findViewById(R.id.app_name);

                holder.app_icon = app_icon;
                holder.app_name = app_name;

                v.setTag(holder);
            } else {
                //iimage = (ImageView) view;
                holder = (PlanetHolder) v.getTag();
                Log.d("ZFH", "after set holder pointer " + holder);
            }

            ResolveInfo info = mApps.get(i);
            holder.app_icon.setImageDrawable(info.activityInfo.loadIcon(getPackageManager()));
            holder.app_name.setText(info.activityInfo.loadLabel(getPackageManager()));

            //iimage.setImageDrawable(info.activityInfo.loadIcon(getPackageManager()));



            return v;
           // return iimage;
        }
        private   class PlanetHolder {
            public ImageView app_icon;
            public TextView app_name;
        }

    }
}