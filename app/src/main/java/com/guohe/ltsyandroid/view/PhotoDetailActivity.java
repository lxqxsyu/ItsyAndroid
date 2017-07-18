package com.guohe.ltsyandroid.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.graphics.Palette;
import android.view.Window;

import com.guohe.ltsyandroid.MvpPresenter;
import com.guohe.ltsyandroid.R;
import com.guohe.ltsyandroid.view.base.BaseActivity;

import java.util.List;

import static com.wou.commonutils.ColorGenerator.colorBurn;

/**
 * Created by shuihan on 2017/7/18.
 * 照片详情
 */

public class PhotoDetailActivity extends BaseActivity{

    private CollapsingToolbarLayout mToolbarLayout;

    @Override
    public void initPresenter(List<MvpPresenter> presenters) {

    }

    @Override
    public void turnToOtherView() {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_photodetail;
    }

    @Override
    protected void initView() {
        mToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.test_image1);

        // Palette的部分
        Palette.generateAsync(bitmap, new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                Palette.Swatch vibrant = palette.getVibrantSwatch();
                mToolbarLayout.setContentScrimColor(colorBurn(vibrant.getRgb()));
                //mPagerSlidingTabStrip.setTextColor(vibrant.getTitleTextColor());
                if (android.os.Build.VERSION.SDK_INT >= 21) {
                    Window window = getWindow();
                    window.setStatusBarColor(colorBurn(vibrant.getRgb()));
                    window.setNavigationBarColor(colorBurn(vibrant.getRgb()));
                }
            }
        });
    }

    @Override
    protected void initData() {

    }

    public static void startActivity(Context context){
        Intent intent = new Intent(context, PhotoDetailActivity.class);
        context.startActivity(intent);
    }
}
