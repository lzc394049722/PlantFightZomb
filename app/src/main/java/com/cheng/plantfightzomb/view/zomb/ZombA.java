package com.cheng.plantfightzomb.view.zomb;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.cheng.plantfightzomb.R;

/**
 * Created by tarena on 2017/7/3.
 */

public class ZombA extends Zomb {
    public ZombA(Context context) {
        super(context);

    zombBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.zomb_0);
    speed = 1;
    HP = 4;
    }
}
