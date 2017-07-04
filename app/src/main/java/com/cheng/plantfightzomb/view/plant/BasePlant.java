package com.cheng.plantfightzomb.view.plant;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Looper;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.util.Timer;

/**
 * Created by Administrator on 2017/7/3 0003.
 */

@SuppressLint("AppCompatCustomView")
public class BasePlant extends ImageView {

    protected Bitmap plantBitmap;
    protected Context context;
    protected MyCallBack myCallBack;
    public Boolean isLive = false;
    public int costSunCount;
    public int HP;
    Timer fireTimer;

    public void setMyCallBack(MyCallBack myCallBack) {
        this.myCallBack = myCallBack;
    }

    public interface MyCallBack {
        void addSunCount(int count);

        void deleteWithPlant(BasePlant p);
    }

    public BasePlant(Context context) {
        super(context);
        HP = 50;
        setScaleType(ScaleType.FIT_XY);
        this.context = context;
    }

    public void beginAnimation() {
        AnimationDrawable ad = new AnimationDrawable();
        int w = plantBitmap.getWidth() / 8;
        for (int i = 0; i < 8; i++) {
            Bitmap subBitmap = Bitmap.createBitmap(plantBitmap, i * w, 0, w, plantBitmap.getHeight());
            BitmapDrawable bd = new BitmapDrawable(getResources(), subBitmap);
            ad.addFrame(bd, 100);
        }
        //是否重复
        ad.setOneShot(false);
        setImageDrawable(ad);
        //开始做动画
        ad.start();
        plantBitmap.recycle();
    }

    public void beginFire() {
    }

    public void deadAction() {

        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                //让坑和植物断绝关系 不然坑里不能再次添加植物
                FrameLayout boxFl = (FrameLayout) getTag();
                boxFl.setTag(null);
                myCallBack.deleteWithPlant(BasePlant.this);
            }
        });

    }
}
