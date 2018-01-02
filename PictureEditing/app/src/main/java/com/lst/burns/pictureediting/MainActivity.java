package com.lst.burns.pictureediting;

import android.graphics.Bitmap;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private String graphPath = "";//需要编辑的原图片路径（可以是本地图片路径，也可以是网络图片URL）
    private String picPath = "";//图片编辑完成之后保存到本地的路径

    private DrawView touchView;

    private View rectRedPaint;//画笔红色点击边框
    private View rectYellowPaint;//画笔黄色点击边框
    private View rectBluePaint;//画笔蓝色点击边框

    private View rectClearDraw;//清除按钮
    private TextView lockDraw;//锁定按钮
    private View rectCompleteDraw;//完成按钮

    private View rectRedPaintSelected;//画笔红色选中状态
    private View rectYellowPaintSelected;//画笔黄色选中状态
    private View recttBluePaintSelected;//画笔蓝色选中状态

    private View line1;//线条1点击边框
    private View line2;//线条2点击边框
    private View line3;//线条3点击边框
    private View line1Selected;//线条1选中状态
    private View line2Selected;//线条2选中状态
    private View line3Selected;//线条3选中状态

    private View line1_p;//线条1
    private View line2_p;//线条2
    private View line3_p;//线条3

    private TextView tv_back;
    private TextView tv_next;

    private Toast mToast;//提示用的短时间显示Toast

    private LoadingDialog loadingDialog;//图片加载处理时显示dialog
    // 原图片bitmap对象
    private Bitmap bitmapNet;

    private Paint mPaint = null;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                if (bitmapNet != null) {
                    if (!touchView.setBitmap(bitmapNet)) {
                        showShort("显示图片失败");
                    }
                    dismissLoading();
                } else {
                    dismissLoading();
                    showShort("图片已损坏！");
                }
            } else if (msg.what == 1) {
                dismissLoading();
                if (picPath != null) {
                    //将编辑后的图片路径返回到上一页面
//                    Intent intent = new Intent();
//                    intent.putExtra("picPath", picPath);
//                    setResult(10, intent);
//                    finish();
                    showShort("图片编辑成功！");
                } else {
                    showShort("图片已损坏！");
                }
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadingDialog = new LoadingDialog(this);
        //网络图片路径
        graphPath = "http://pic2.ooopic.com/12/62/16/24bOOOPIC57_1024.jpg";
        initView();
        initDates();
        initListener();
    }
    private void initView() {
        touchView = (DrawView) findViewById(R.id.myView);
        //设置接口回调
        touchView.setInterfaceCallback(idrawview);

        rectRedPaint = findViewById(R.id.rectRedPaint);
        rectYellowPaint = findViewById(R.id.rectYellowPaint);
        rectBluePaint = findViewById(R.id.rectBluePaint);

        rectClearDraw = findViewById(R.id.rectClearDraw);
        lockDraw = (TextView) findViewById(R.id.lock);
        rectCompleteDraw = findViewById(R.id.rectCompleteDraw);

        rectRedPaintSelected = findViewById(R.id.rectRedPaintSelected);
        rectYellowPaintSelected = findViewById(R.id.rectYellowPaintSelected);
        recttBluePaintSelected = findViewById(R.id.recttBluePaintSelected);

        line1Selected = findViewById(R.id.line1Selected);
        line2Selected = findViewById(R.id.line2Selected);
        line3Selected = findViewById(R.id.line3Selected);
        line1 = findViewById(R.id.line1);
        line2 = findViewById(R.id.line2);
        line3 = findViewById(R.id.line3);
        line1_p = findViewById(R.id.line1_p);
        line2_p = findViewById(R.id.line2_p);
        line3_p = findViewById(R.id.line3_p);

        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_next = (TextView) findViewById(R.id.tv_next);
    }
    private IDrawView idrawview = new IDrawView() {
        @Override
        public void setBack(boolean savePath) {
            if (savePath) {
                tv_back.setClickable(savePath);
                tv_back.setText("回退");
            } else {
                tv_back.setClickable(savePath);
                tv_back.setText("无绘画");
            }
        }

        @Override
        public void setNext(boolean deletePath) {
            if (deletePath) {
                tv_next.setClickable(deletePath);
                tv_next.setText("前进");
            } else {
                tv_next.setClickable(deletePath);
                tv_next.setText("无记录");
            }
        }
    };

    private void initDates() {
        //andPermissions(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);//适配6.0权限
        initPaint();
        touchView.setPaint(mPaint);
        showLoading();
        loadImageNet();
    }

    private void initListener() {
        rectRedPaint.setOnClickListener(this);
        rectYellowPaint.setOnClickListener(this);
        rectBluePaint.setOnClickListener(this);

        rectClearDraw.setOnClickListener(this);
        lockDraw.setOnClickListener(this);
        rectCompleteDraw.setOnClickListener(this);

        line1.setOnClickListener(this);
        line2.setOnClickListener(this);
        line3.setOnClickListener(this);

        tv_back.setOnClickListener(this);
        tv_next.setOnClickListener(this);

    }

    /**
     * 初始化画笔
     */
    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(0xFFFF4D4B);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(3);
    }
    protected void showLoading() {
        loadingDialog.show();
    }
    /**
     * 获取原图片bitmap对象
     */
    private void loadImageNet() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                bitmapNet = BitmapUtils.getBitmap(graphPath);
                handler.sendEmptyMessage(0);
            }
        }).start();
    }
    protected void dismissLoading() {
        if (loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }

    /**
     * Toast短时间显示
     *
     * @param message 显示内容
     */
    public void showShort(CharSequence message) {
        if (mToast == null) {
            mToast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(message);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rectRedPaint://选择红色
                doSelectedPainterColor(0);
                break;
            case R.id.rectYellowPaint://选择黄色
                doSelectedPainterColor(1);
                break;
            case R.id.rectBluePaint://选择蓝色
                doSelectedPainterColor(2);
                break;
            case R.id.line1://选择细线条
                doSelectedLineSize(0);
                break;
            case R.id.line2://选择中线条
                doSelectedLineSize(1);
                break;
            case R.id.line3://选择粗线条
                doSelectedLineSize(2);
                break;
            case R.id.rectClearDraw://清除之前编辑的内容
                touchView.clearImage();
                break;
            case R.id.lock://用来判断手势，（文本显示锁定）true代表缩放偏移，（文本显示解锁）false代表编辑图片
                touchView.lock = !touchView.lock;
                lockDraw.setText(touchView.lock ? "锁定" : "解锁");
                break;
            case R.id.tv_back://回退
                touchView.undo();
                break;
            case R.id.tv_next://前进
                touchView.redo();
                break;
            case R.id.rectCompleteDraw://完成按钮，保存编辑后的图片到本地
                saveImage();
                break;
        }
    }

    /**
     * 选择画笔颜色
     *
     * @param position 0、1、2分别代表红、黄、蓝
     */
    private void doSelectedPainterColor(int position) {
        rectRedPaintSelected.setVisibility(View.INVISIBLE);
        rectYellowPaintSelected.setVisibility(View.INVISIBLE);
        recttBluePaintSelected.setVisibility(View.INVISIBLE);
        setLineColor(position);
        switch (position) {
            case 0:
                rectRedPaintSelected.setVisibility(View.VISIBLE);
                setPaiterColor(0xFFFF4D4B);
                break;
            case 1:
                rectYellowPaintSelected.setVisibility(View.VISIBLE);
                setPaiterColor(0xFFffbb00);
                break;
            case 2:
                recttBluePaintSelected.setVisibility(View.VISIBLE);
                setPaiterColor(0xFF00b8ee);
                break;
        }
    }

    /**
     * 选择线条粗细
     *
     * @param position 0、1、2分别代表线条从细到粗
     */
    private void doSelectedLineSize(int position) {
        line1Selected.setVisibility(View.INVISIBLE);
        line2Selected.setVisibility(View.INVISIBLE);
        line3Selected.setVisibility(View.INVISIBLE);
        switch (position) {
            case 0:
                line1Selected.setVisibility(View.VISIBLE);
                setPaiterLine(2);
                break;
            case 1:
                line2Selected.setVisibility(View.VISIBLE);
                setPaiterLine(5);
                break;
            case 2:
                line3Selected.setVisibility(View.VISIBLE);
                setPaiterLine(8);
                break;
        }
    }

    /**
     * 保存编辑后的图片到本地，获得编辑后的图片路径
     */
    private void saveImage() {
        showLoading();
        new Thread(new Runnable() {
            @Override
            public void run() {
                picPath = touchView.saveImage();
                handler.sendEmptyMessage(1);
            }
        }).start();
    }

    private void setLineColor(int position) {
        switch (position) {
            case 0:
                line1_p.setBackground(getResources().getDrawable(R.drawable.shape_circle_red));
                line2_p.setBackground(getResources().getDrawable(R.drawable.shape_circle_red));
                line3_p.setBackground(getResources().getDrawable(R.drawable.shape_circle_red));
                break;
            case 1:
                line1_p.setBackground(getResources().getDrawable(R.drawable.shape_circle_yellow));
                line2_p.setBackground(getResources().getDrawable(R.drawable.shape_circle_yellow));
                line3_p.setBackground(getResources().getDrawable(R.drawable.shape_circle_yellow));
                break;
            case 2:
                line1_p.setBackground(getResources().getDrawable(R.drawable.shape_circle_blue));
                line2_p.setBackground(getResources().getDrawable(R.drawable.shape_circle_blue));
                line3_p.setBackground(getResources().getDrawable(R.drawable.shape_circle_blue));
                break;
        }
    }
    /**
     * 重新设置画笔颜色
     *
     * @param color 画笔颜色
     */
    private void setPaiterColor(int color) {
        mPaint.setColor(color);
        touchView.setPaint(mPaint);
    }

    /**
     * 重新设置画笔宽度
     *
     * @param strong 画笔宽度
     */
    private void setPaiterLine(int strong) {
        mPaint.setStrokeWidth(strong);
        touchView.setPaint(mPaint);
    }
}
