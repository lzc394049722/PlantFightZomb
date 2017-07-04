package com.cheng.plantfightzomb.view.zomb;

import android.content.Context;
import android.graphics.BitmapFactory;

import com.cheng.plantfightzomb.R;

/**
 * Created by tarena on 2017/7/3.
 */

public class ZombD extends Zomb {
    public ZombD(Context context) {
        super(context);
        speed = 4;
        zombBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.zomb_3);

        HP = 15;
    }
}
