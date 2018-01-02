package com.lst.burns.pictureediting;

import android.app.Dialog;
import android.content.Context;

/**
 * 耗时操作时弹出
 */
public class LoadingDialog extends Dialog {

    public LoadingDialog(Context context) {
        super(context, R.style.dialog_operate);
        setContentView(R.layout.dialog_loading);
        setCanceledOnTouchOutside(false);
        setCancelable(true);
    }
}