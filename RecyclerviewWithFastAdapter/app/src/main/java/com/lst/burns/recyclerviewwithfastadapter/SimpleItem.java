package com.lst.burns.recyclerviewwithfastadapter;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.items.AbstractItem;
import com.mikepenz.materialize.holder.StringHolder;

import java.util.List;

public class SimpleItem extends AbstractItem<SimpleItem, SimpleItem.ViewHolder> {
    public String name;
    public String description;

    //The unique ID for this type of item
    @Override
    public int getType() {
        Log.d("ZFH","getType is called ");
        return R.id.fastadapter_sampleitem_id;
    }

    //The layout to be used for this type of item
    @Override
    public int getLayoutRes() {
        Log.e("ZFH","getLayoutRes");
        return R.layout.sample_item;
    }

    @Override
    public ViewHolder getViewHolder(@NonNull View v) {
        Log.e("ZFH","getViewHolder");
        return new ViewHolder(v);
    }

    /**
     * our ViewHolder
     */
    protected static class ViewHolder extends FastAdapter.ViewHolder<SimpleItem> {
       // @BindView(R.id.material_drawer_name)
        TextView name;
        //@BindView(R.id.material_drawer_description)
        TextView description;

        public ViewHolder(View view) {

            super(view);
            Log.e("ZFH","ViewHolder");
            name = (TextView)view.findViewById(R.id.material_drawer_name);
            description = (TextView)view.findViewById(R.id.material_drawer_description);
            //ButterKnife.bind(this, view);
        }

        @Override
        public void bindView(SimpleItem item, List<Object> payloads) {

            //StringHolder.applyTo(item.name, name);

            //StringHolder.applyToOrHide(item.description, description);
            Log.e("ZFH","bindView");
            name.setText(item.name);
            description.setText(item.description);
        }

        @Override
        public void unbindView(SimpleItem item) {
            Log.e("ZFH","unbindView");
            name.setText(null);
            description.setText(null);
        }
    }
}