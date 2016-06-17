package com.qypt.just.justson_beautiful_wallpaper.Utils;

import android.app.Activity;
import android.app.Dialog;
import android.view.WindowManager;

import com.qypt.just.justson_beautiful_wallpaper.R;

/**
 * Created by Administrator on 2016/6/16.
 */
public class DialogManager {


    private Dialog dialog;

    public DialogManager(){

    }


    public void initDialog(int resId, Activity activity){

        dialog = new Dialog(activity, R.style.progressdialog);
        dialog.setContentView(resId);
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.setCancelable(false);
    }

    public void onShow(){
        dialog.show();
    }

    public void onDismiss(){

        dialog.cancel();
    }
}
