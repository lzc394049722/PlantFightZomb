package com.cheng.plantfightzomb.view.plant;

import android.content.Context;
import android.graphics.BitmapFactory;

import com.cheng.plantfightzomb.R;

/**
 * Created by tarena on 2017/7/3.
 */

public class IcePea extends Pea {

    public IcePea(Context context) {
        super(context);
        plantBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.plant_3);
        bulletBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.bullet_1);
    }
}
