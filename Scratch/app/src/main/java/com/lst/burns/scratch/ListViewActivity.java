package com.lst.burns.scratch;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lst.burns.scratch.Object.Planet;

import java.util.ArrayList;
import java.util.List;

//ListView need ArrayAdapter<T>
//ListView setOnItemClickListener
//ListView ContextMenu
//ListView remove item ( adapter.remove() will remove real data and update ui.
public class ListViewActivity extends AppCompatActivity {

    // The data to show
    List<Planet> planetsList = new ArrayList<Planet>();
    PlanetAdapter aAdpt;
    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_listview);

        initList();

        // We get the ListView component from the layout
        ListView lv = (ListView) findViewById(R.id.listView);
        aAdpt = new PlanetAdapter(planetsList, this);
        lv.setAdapter(aAdpt);

        // React to user clicks on item
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parentAdapter, View view, int position,
                                    long id) {

                TextView clickedView = (TextView)view.findViewById(R.id.name);

                Toast.makeText(ListViewActivity.this, "Item with id ["+id+"] - Position ["+position+"] - Planet ["+clickedView.getText()+"]", Toast.LENGTH_SHORT).show();

            }
        });

        registerForContextMenu(lv);
    }

    private void initList() {
        // We populate the planets

        planetsList.add(new Planet("Mercury", 10));
        planetsList.add(new Planet("Venus", 20));
        planetsList.add(new Planet("Mars", 30));
        planetsList.add(new Planet("Jupiter", 40));
        planetsList.add(new Planet("Saturn", 50));
        planetsList.add(new Planet("Uranus", 60));
        planetsList.add(new Planet("Neptune", 70));


    }

    // We want to create a context Menu when the user long click on an item
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {

        super.onCreateContextMenu(menu, v, menuInfo);
        AdapterView.AdapterContextMenuInfo aInfo = (AdapterView.AdapterContextMenuInfo) menuInfo;

        // We know that each row in the adapter is a Map
        Planet planet =  aAdpt.getItem(aInfo.position);

        menu.setHeaderTitle("Options for " + planet.getName());
        menu.add(1, 1, 1, "Details");
        menu.add(1, 2, 2, "Delete");

    }

    // This method is called when user selects an Item in the Context menu
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        Log.d("ZFH","itemId " + itemId);
        AdapterView.AdapterContextMenuInfo aInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        planetsList.remove(aInfo.position);
        aAdpt.notifyDataSetChanged();
        return true;
    }
    public class PlanetAdapter extends ArrayAdapter<Planet> {

        private List<Planet> planetList;
        private Context context;

        public PlanetAdapter(List<Planet> planetList, Context ctx) {
            super(ctx, R.layout.img_row_layout, planetList);
            this.planetList = planetList;
            this.context = ctx;
        }
        @Override
        public int getCount() {
            return planetList.size();
        }
        @Override
        public Planet getItem(int position) {
            return planetList.get(position);
        }
        @Override
        public long getItemId(int position) {
            return planetList.get(position).hashCode();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;

            PlanetHolder holder = new PlanetHolder();

            // First let's verify the convertView is not null
            if (convertView == null) {
                // This a new view we inflate the new layout
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = inflater.inflate(R.layout.img_row_layout, null);
                // Now we can fill the layout with the right values
                TextView tv = (TextView) v.findViewById(R.id.name);
                TextView distView = (TextView) v.findViewById(R.id.dist);


                holder.planetNameView = tv;
                holder.distView = distView;

                v.setTag(holder);
            }
            else
                holder = (PlanetHolder) v.getTag();

            Planet p = planetList.get(position);
            holder.planetNameView.setText(p.getName());
            holder.distView.setText("" + p.getDistance());


            return v;
        }

    }
    /* *********************************
	 * We use the holder pattern
	 * It makes the view faster and avoid finding the component
	 * **********************************/

    private   class PlanetHolder {
        public TextView planetNameView;
        public TextView distView;
    }

    // Handle user click
    public void removePlanet(View view){
        Log.i("ZFH","before see how many planetsList :" + planetsList.size());
        aAdpt.remove(planetsList.get(0));//hardcode
        Log.i("ZFH","after see how many planetsList :" + planetsList.size());
    }
    public void addPlanet(View view) {
        final Dialog d = new Dialog(this);
        d.setContentView(R.layout.dialog);
        d.setTitle("Add planet");
        d.setCancelable(true);

        Window dialogWindow = d.getWindow();
        //WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.setGravity(Gravity.LEFT | Gravity.TOP);

        //lp.x = 100; // 新位置X坐标
        //lp.y = 100; // 新位置Y坐标
        //lp.width =500; // 宽度
        //lp.height = 500; // 高度
        //lp.alpha = 0.7f; // 透明度
        //dialogWindow.setAttributes(lp);
                /*
         * 将对话框的大小按屏幕大小的百分比设置
         */
        WindowManager m = getWindowManager();
        Display dp = m.getDefaultDisplay(); // 获取屏幕宽、高用
        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        p.height = (int) (dp.getHeight() * 0.6); // 高度设置为屏幕的0.6
        p.width = (int) (dp.getWidth() * 0.9); // 宽度设置为屏幕的0.65
        dialogWindow.setAttributes(p);


        final EditText edit = (EditText) d.findViewById(R.id.editTextPlanet);
        Button b = (Button) d.findViewById(R.id.add_planet);
        b.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                String planetName = edit.getText().toString();
                ListViewActivity.this.planetsList.add(new Planet(planetName, 0));
                ListViewActivity.this.aAdpt.notifyDataSetChanged(); // We notify the data model is changed
                d.dismiss();
            }
        });

        d.show();
    }
}