package com.vison.finance.financemanager;

import android.content.Intent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.ImageView;

import com.vison.finance.financemanager.framework.base.BaseActivity;

import butterknife.BindView;

public class SplashActivity extends BaseActivity {


    @BindView(R.id.iv_splash)
    ImageView ivSplash;
    private AnimationSet set;

    @Override
    public void initParams() {

    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_splash);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        set = new AnimationSet(false);//动画集合--旋转和缩放同时进行
        AlphaAnimation alpna = new AlphaAnimation(1, 1);
        alpna.setDuration(1000);//设置动画时间
        alpna.setFillAfter(true);//保持动画状态
        set.addAnimation(alpna);
        ivSplash.startAnimation(set);
    }

    @Override
    public void initListeners() {
        //设置动画监听
        set.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            //动画执行结束
            @Override
            public void onAnimationEnd(Animation animation) {
                toPage();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    @Override
    public void setClick(View view) {

    }

    //跳转至下一个页面
    private void toPage() {
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        finish();
    }
}
