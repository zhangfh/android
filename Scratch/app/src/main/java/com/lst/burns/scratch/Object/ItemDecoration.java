package com.lst.burns.scratch.Object;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.lst.burns.scratch.R;

public class ItemDecoration extends RecyclerView.ItemDecoration {

    private Context mContext;
    private int distance;

    Drawable drawable;
    Drawable verticalLine;
    Drawable horizontalLine;

    public ItemDecoration(Context context, int distance) {

        mContext = context;
        this.distance = distance;
        verticalLine = ContextCompat.getDrawable(mContext, R.drawable.gray_line);
        drawable = ContextCompat.getDrawable(mContext, R.drawable.time);
        horizontalLine = ContextCompat.getDrawable(mContext, R.drawable.horizontal_line);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {


        //感觉rect是针对上一个画的，所以top修改就可以
        outRect.left = distance;
        outRect.right = distance;
        outRect.bottom = 3 * distance;
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.top = distance;
        } else if (parent.getChildAdapterPosition(view) == 1) {
            outRect.top = 4 * distance;
        }

        if (parent.getChildAdapterPosition(view) % 2 == 0) {
            outRect.left = 20;
        } else {
            outRect.right = 20;
        }
        Log.e("ZFH","finally, view: " + parent.getChildAdapterPosition(view) + " rect: " + outRect.left + " " + outRect.top + " " + outRect.right + " " + outRect.bottom );

    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        Log.e("ZFH","onDraw   parent: "+parent + " state " + state);
        drawHorizontal(c, parent);
        drawVertical(c, parent);
    }

    public void drawHorizontal(Canvas c, RecyclerView parent) {
        final int top = parent.getPaddingTop();
        final int bottom = parent.getHeight() - parent.getPaddingBottom();
        final int parentWidth = parent.getMeasuredWidth();
        final int childCount = parent.getChildCount();

        //parentWidth 1080
        //top 0
        //getHeight 1558
        //getPaddingBottom 0
        //bottom 1558

        //画一次就可以 ,测试去掉for循环也没有问题
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);

            //中间的线直接画到底的话可以用bottom，根据child选择中间画线就换成child.getBottom()
            //bounds(left,top,right,bottom)
            //bounts(parentWidth/2-1,0,parentWidth / 2 + 1,,bottom)
            verticalLine.setBounds(parentWidth / 2 - 1, top, parentWidth / 2 + 1,bottom);
            verticalLine.draw(c);
        }
    }
    public void drawVertical(Canvas c, RecyclerView parent) {
        final int parentWidth = parent.getMeasuredWidth();
        Log.e("ZFH","drawVertical parentWidth:" + parentWidth);
        final int childCount = parent.getChildCount();
        Log.e("ZFH","drawVertical childCount:" + childCount);
        for (int i = 0; i < childCount; i++) {//-1最后一个不画
            final View child = parent.getChildAt(i);

            final int top = child.getTop() + (child.getBottom() - child.getTop()) / 2 - drawable.getIntrinsicHeight() / 2;
            final int bottom = top + drawable.getIntrinsicHeight();

            int horizontalLineLeft = child.getRight();
            int horizontalLineRight = parentWidth / 2;
            int horizontalLineTop = child.getTop() + (child.getBottom() - child.getTop()) / 2;

            if (parent.getChildAdapterPosition(child) % 2 == 1) {
                horizontalLineLeft = parentWidth / 2;
                horizontalLineRight = child.getLeft();
            }

            horizontalLine.setBounds(horizontalLineLeft, horizontalLineTop, horizontalLineRight, horizontalLineTop + 2);
            horizontalLine.draw(c);

            int drawableLeft = parentWidth / 2 - drawable.getIntrinsicWidth() / 2;
            int drawableRight = parentWidth / 2 + drawable.getIntrinsicWidth() / 2;

            drawable.setBounds(drawableLeft, top, drawableRight, bottom);
            drawable.draw(c);
        }
    }
}