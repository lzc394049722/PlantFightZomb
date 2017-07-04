package com.cheng.plantfightzomb.view.zomb;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.cheng.plantfightzomb.view.plant.BasePlant;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by tarena on 2017/7/3.
 */

public class Zomb extends ImageView {

    protected Bitmap zombBitmap;
    public float speed;
    public int HP;
    float oldSpeed;

    public Zomb(Context context) {
        super(context);


        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(100, 150);

        setLayoutParams(lp);

    }

    public void beginAnimation() {
        AnimationDrawable ad = new AnimationDrawable();
        int w = zombBitmap.getWidth() / 8;
        for (int i = 0; i < 8; i++) {
            Bitmap subBitmap = Bitmap.createBitmap(zombBitmap, i * w, 0, w, zombBitmap.getHeight());
            BitmapDrawable bd = new BitmapDrawable(getResources(), subBitmap);
            ad.addFrame(bd, 100);
        }
        //是否重复
        ad.setOneShot(false);
        setImageDrawable(ad);
        //开始做动画
        ad.start();
        zombBitmap.recycle();
    }

    public void eatAction(final BasePlant p) {
        oldSpeed = speed;
        speed = 0;

        final Timer t = new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                p.HP--;
                if (p.HP <= 0) {
                    speed = oldSpeed;
                    if (p != null) {
                        p.deadAction();
                    }
                    t.cancel();
                }

            }
        };

        t.schedule(tt, 0, 100);
    }

}
