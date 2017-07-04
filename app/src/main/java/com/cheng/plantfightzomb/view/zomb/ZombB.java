package com.cheng.plantfightzomb.view.zomb;

import android.content.Context;
import android.graphics.BitmapFactory;

import com.cheng.plantfightzomb.R;

/**
 * Created by tarena on 2017/7/3.
 */

public class ZombB extends Zomb {

    public ZombB(Context context) {
        super(context);
        speed = 2;
        zombBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.zomb_1);
        HP = 8;

    }
}
