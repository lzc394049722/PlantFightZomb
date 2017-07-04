package com.cheng.plantfightzomb.view.plant;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

/**
 * Created by Administrator on 2017/7/3 0003.
 */

@SuppressLint("AppCompatCustomView")
public class BasePlant extends ImageView {

    protected Bitmap plantBitmap;
    protected Context context;
    protected MyCallBack myCallBack;

    public void setMyCallBack(MyCallBack myCallBack) {
        this.myCallBack = myCallBack;
    }

    public interface  MyCallBack{
        void addSunCount(int count);
    }

    public BasePlant(Context context) {
        super(context);
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
}
