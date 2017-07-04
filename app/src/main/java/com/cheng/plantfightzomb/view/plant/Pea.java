package com.cheng.plantfightzomb.view.plant;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.widget.AbsoluteLayout;
import android.widget.ImageView;

import com.cheng.plantfightzomb.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by tarena on 2017/7/3.
 */

public class Pea extends BasePlant {

    public List<ImageView> bullets = new ArrayList<>();
    protected Bitmap bulletBitmap;

    public Pea(Context context) {
        super(context);
        plantBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.plant_2);
        bulletBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.bullet_0);
    }

    @Override
    public void beginFire() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        ImageView bullet = new ImageView(context);
                        bullet.setImageBitmap(bulletBitmap);
                        AbsoluteLayout.LayoutParams lp = new AbsoluteLayout.LayoutParams
                                (40, 40, (int) getX() + 60, (int) getY() + 15);
                        AbsoluteLayout layout = (AbsoluteLayout) getParent();
                        layout.addView(bullet, lp);
                        bullets.add(bullet);
                    }
                });
            }
        }, 0, 1000);
    }
}
