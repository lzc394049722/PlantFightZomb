package com.cheng.plantfightzomb.view.zomb;

import android.content.Context;
import android.graphics.BitmapFactory;

import com.cheng.plantfightzomb.R;

/**
 * Created by tarena on 2017/7/3.
 */

public class ZombC extends Zomb {
    public ZombC(Context context) {
        super(context);
        speed = 3;
        zombBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.zomb_2);
        HP = 12;

    }
}
