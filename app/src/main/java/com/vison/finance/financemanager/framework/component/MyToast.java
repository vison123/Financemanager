package com.vison.finance.financemanager.framework.component;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.vison.finance.financemanager.R;
import com.vison.finance.financemanager.framework.base.BaseApplication;

/**
 * Created by vison on 16/6/25.
 */
public class MyToast {

    private static Toast toast;
    private static View mLayout;
    private static TextView mText;
    private static ImageView mImageView;
    private static Context context = BaseApplication.getContext();

    /**
     * 显示Toast
     *
     * @param tvString
     */

    public static void logoShow(String tvString) {
        mLayout = LayoutInflater.from(context).inflate(R.layout.layout_toast, null);
        mText = (TextView) mLayout.findViewById(R.id.tv_toast);
        mImageView = (ImageView) mLayout.findViewById(R.id.iv_toast);
        mImageView.setBackgroundResource(R.mipmap.ic_launcher);
        mText.setText(tvString);
        toast = new Toast(context);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(mLayout);
        toast.show();
    }

    public static void bitmapShow(String tvString, int bitmapId) {
        mLayout = LayoutInflater.from(context).inflate(R.layout.layout_toast, null);
        mText = (TextView) mLayout.findViewById(R.id.tv_toast);
        mImageView = (ImageView) mLayout.findViewById(R.id.iv_toast);
        mImageView.setBackgroundResource(bitmapId);
        mText.setText(tvString);
        toast = new Toast(context);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(mLayout);
        toast.show();
    }

    public static void show(String tvString) {
        mLayout = LayoutInflater.from(context).inflate(R.layout.layout_toast, null);
        mText = (TextView) mLayout.findViewById(R.id.tv_toast);
        mImageView = (ImageView) mLayout.findViewById(R.id.iv_toast);
        mImageView.setVisibility(View.GONE);
        mText.setText(tvString);
        toast = new Toast(context);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(mLayout);
        toast.show();
    }
}
