package com.cheng.plantfightzomb.view.plant;

import android.content.Context;
import android.graphics.BitmapFactory;

import com.cheng.plantfightzomb.R;

/**
 * Created by tarena on 2017/7/3.
 */

public class Nut extends BasePlant {
    public Nut(Context context) {
        super(context);
        plantBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.plant_5);

    }
}
