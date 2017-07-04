package com.cheng.plantfightzomb.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.AbsoluteLayout;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.cheng.plantfightzomb.R;
import com.cheng.plantfightzomb.view.plant.BasePlant;
import com.cheng.plantfightzomb.view.plant.IcePea;
import com.cheng.plantfightzomb.view.plant.Nut;
import com.cheng.plantfightzomb.view.plant.Pea;
import com.cheng.plantfightzomb.view.plant.SunFlower;
import com.cheng.plantfightzomb.view.zomb.Zomb;
import com.cheng.plantfightzomb.view.zomb.ZombA;
import com.cheng.plantfightzomb.view.zomb.ZombB;
import com.cheng.plantfightzomb.view.zomb.ZombC;
import com.cheng.plantfightzomb.view.zomb.ZombD;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements BasePlant.MyCallBack {

    @BindView(R.id.sunCountTV)
    TextView sunCountTV;
    @BindViews({R.id.plant1, R.id.plant2, R.id.plant3, R.id.plant4})
    List<ImageView> plantIVs;
    @BindView(R.id.activity_main)
    AbsoluteLayout activityMain;
    @BindView(R.id.gv_main)
    GridLayout gridLayout;

    private BasePlant dragPlant;
    private List<BasePlant> plants = new ArrayList<>();
    private List<Zomb> zombs = new ArrayList<>();
    private int zombCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        addZomb();
        initUI();
        //计时器，用于运行
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        //僵尸
                        for (Zomb zomb : zombs) {
                            zomb.setX(zomb.getX() - zomb.speed);
                            if (zomb.getX() < -zomb.getWidth()) {
                                activityMain.removeView(zomb);
                                zombs.remove(zomb);
                                break;
                            }
                        }
                        //
                        //植物
                        for (BasePlant plant : plants) {
                            if (plant instanceof Pea) {
                                Pea pea = (Pea) plant;
                                for (ImageView bullet : pea.bullets) {
                                    bullet.setX(bullet.getX() + 5);
                                    //判断子弹是否离开屏幕
                                    if (bullet.getX() > activityMain.getWidth()) {
                                        activityMain.removeView(bullet);
                                        pea.bullets.remove(bullet);
                                        break;
                                    }

                                    for (Zomb zomb : zombs) {
                                        Rect zombRect = new Rect();
                                        Rect bulletRect = new Rect();
                                        bullet.getGlobalVisibleRect(bulletRect);
                                        zomb.getGlobalVisibleRect(zombRect);
                                        //判断是否有交集
                                        if (zombRect.intersect(bulletRect)) {
                                            activityMain.removeView(bullet);
                                            pea.bullets.remove(bullet);
                                            zomb.HP--;
                                            //判断是否是寒冰射手如果是 则子弹为寒冰射手的子弹，且未被减速
                                            if (pea instanceof IcePea && zomb.getAlpha() == 1) {
                                                zomb.speed *= 0.8f;
                                                zomb.setAlpha(0.8f);
                                                zomb.setColorFilter(R.color.lightcolor, PorterDuff.Mode.SRC_ATOP);
                                            }
                                            //没血，死掉
                                            if (zomb.HP <= 0) {
                                                zombs.remove(zomb);
                                                activityMain.removeView(zomb);
                                            }
                                            return;
                                        }
                                    }


                                }
                            }
                        }
                    }
                });
            }
        }, 0, 1000 / 60);
    }

    private void addZomb() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {

                        Zomb z = null;
                        // 0-29  0 A
                        // 30-59 1  B
                        // 60-89 2 C
                        // 90以上 3 D

                        int type = zombCount / 30;
                        switch (type) {

                            case 0:
                                z = new ZombA(MainActivity.this);
                                break;
                            case 1:
                                z = new ZombB(MainActivity.this);

                                break;
                            case 2:
                                z = new ZombC(MainActivity.this);
                                break;
                            default:
                                z = new ZombD(MainActivity.this);
                        }
                        int y = (int) (Math.random() * 5 + 1) * 110;
                        z.setX(activityMain.getWidth());
                        z.setY(y);
                        zombs.add(z);
                        activityMain.addView(z);
                        z.beginAnimation();
                        zombCount++;
                    }
                });
            }
        }, 0, 1000);

    }

    private void initUI() {
        Bitmap plantsBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.seedpackets);
        int w = plantsBitmap.getWidth() / 18;
        for (int i = 0; i < plantIVs.size(); i++) {
            ImageView plantIV = plantIVs.get(i);
            int x = 0;
            switch (i) {
                case 1:
                    x = 2 * w;
                    break;
                case 2:
                    x = 3 * w;
                    break;
                case 3:
                    x = 5 * w;
                    break;
            }
            Bitmap plantBitmap = Bitmap.createBitmap(plantsBitmap, x, 0, w, plantsBitmap
                    .getHeight());
            plantIV.setImageBitmap(plantBitmap);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                for (int i = 0; i < plantIVs.size(); i++) {
                    ImageView plantIV = plantIVs.get(i);
                    Rect r = new Rect();
                    plantIV.getGlobalVisibleRect(r);
                    if (r.contains((int) event.getRawX(), (int) event.getRawY())) {
                        switch (i) {
                            case 0:
                                dragPlant = new SunFlower(this);
                                break;
                            case 1:
                                dragPlant = new Pea(this);
                                break;
                            case 2:
                                dragPlant = new IcePea(this);
                                break;
                            case 3:
                                dragPlant = new Nut(this);
                                break;
                        }
                        activityMain.addView(dragPlant, plantIV.getLayoutParams());
                        dragPlant.beginAnimation();
                        dragPlant.setMyCallBack(this);
                    }
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (dragPlant != null) {
                    dragPlant.setX(event.getRawX() - dragPlant.getWidth() / 2);
                    dragPlant.setY(event.getRawY() - dragPlant.getHeight() / 2);
                }
                break;
            case MotionEvent.ACTION_UP:
                if (dragPlant != null) {
                    Rect rect = new Rect();
                    gridLayout.getGlobalVisibleRect(rect);
                    if (rect.contains((int) event.getRawX(), (int) event.getRawY())) {
                        for (int i = 0; i < gridLayout.getChildCount(); i++) {
                            FrameLayout f = (FrameLayout) gridLayout.getChildAt(i);
                            Rect r = new Rect();
                            f.getGlobalVisibleRect(r);
                            if (r.contains((int) event.getRawX(), (int) event.getRawY()) && f.getTag() == null) {
                                dragPlant.setX(r.left + r.width() / 2 - dragPlant.getWidth() / 2);
                                dragPlant.setY(r.top + r.height() / 2 - dragPlant.getHeight() / 2);
                                f.setTag(dragPlant);
                                dragPlant.beginFire();
                                plants.add(dragPlant);
                                dragPlant = null;
                                break;
                            }
                        }
                        if (dragPlant != null) {
                            activityMain.removeView(dragPlant);
                            dragPlant = null;
                        }
                    } else {
                        activityMain.removeView(dragPlant);
                    }
                }

                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void addSunCount(int count) {
        int totalCount = Integer.parseInt(sunCountTV.getText().toString()) + count;

        sunCountTV.setText("" + totalCount);
    }
}
