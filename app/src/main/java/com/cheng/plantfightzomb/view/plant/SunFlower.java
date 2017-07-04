package com.cheng.plantfightzomb.view.plant;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.AbsoluteLayout;
import android.widget.ImageView;

import com.cheng.plantfightzomb.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by tarena on 2017/7/3.
 */

public class SunFlower extends BasePlant {

    public SunFlower(Context context) {
        super(context);
        costSunCount = 50;
        plantBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.plant_0);
    }

    @Override
    public void beginFire() {
        fireTimer = new Timer();
        fireTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        final ImageView imageView = new ImageView(context);
                        imageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.sun));
                        AbsoluteLayout.LayoutParams layoutParams = new AbsoluteLayout.LayoutParams(70, 70, (int) getX(), (int) getY() + 70);
                        final AbsoluteLayout layout = (AbsoluteLayout) getParent();
                        layout.addView(imageView, layoutParams);
                        imageView.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                layout.removeView(imageView);
                                myCallBack.addSunCount(25);
                            }
                        });
                        new Timer().schedule(new TimerTask() {
                            @Override
                            public void run() {
                                new Handler(Looper.getMainLooper()).post(new Runnable() {
                                    @Override
                                    public void run() {
                                        layout.removeView(imageView);
                                    }
                                });
                            }
                        }, 3000);
                    }
                });
            }
        }, 5000, 5000);
    }

    @Override
    public void deadAction() {
        //当植物死掉的时候添加阳光的timer也要停止
        fireTimer.cancel();

        super.deadAction();

    }
}
