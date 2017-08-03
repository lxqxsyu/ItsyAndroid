package com.guohe.ltsyandroid.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Typeface;
import android.support.annotation.LayoutRes;
import android.support.design.widget.AppBarLayout;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.guohe.ltsyandroid.MvpPresenter;
import com.guohe.ltsyandroid.R;
import com.guohe.ltsyandroid.custome.FluidLayout;
import com.guohe.ltsyandroid.manage.config.GlobalConfigManage;
import com.guohe.ltsyandroid.util.DimenUtil;
import com.guohe.ltsyandroid.util.FrescoUtils;
import com.guohe.ltsyandroid.util.LogUtil;
import com.guohe.ltsyandroid.view.base.BaseActivity;
import com.jaeger.library.StatusBarUtil;
import com.joaquimley.faboptions.FabOptions;

import java.util.List;

/**
 * Created by shuihan on 2017/7/18.
 * 照片详情
 */

public class PhotoDetailActivity extends BaseActivity implements View.OnClickListener{

    private SimpleDraweeView mDetailPhoto;
    private FluidLayout mFluidLayout;
    private AppBarLayout mAppBarLayout;
    private boolean mValuesCalculatedAlready = false;
    private int mAppBarLayoutHeight;
    private FabOptions mFabOptions;

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
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        StatusBarUtil.setTranslucentForImageView(this, 100, null);
    }

    @Override
    protected boolean canSlidr() {
        return true;
    }

    @Override
    protected void initView() {
        mDetailPhoto = getView(R.id.photodetail_photo);
        mFluidLayout = getView(R.id.fluid_layout);
        mAppBarLayout = getView(R.id.app_bar);
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (!mValuesCalculatedAlready) {
                    mAppBarLayoutHeight = mAppBarLayout.getHeight();
                    mValuesCalculatedAlready = true;
                }
                LogUtil.d("verticalOffset == " + verticalOffset);
                if(Math.abs(verticalOffset) <= mAppBarLayoutHeight / 2){
                    mFabOptions.setAlpha(1);
                }else if(Math.abs(verticalOffset) >= mAppBarLayoutHeight - DimenUtil.dp2px(40)){
                    mFabOptions.setAlpha(0);
                }else {
                    float alpha = (mAppBarLayoutHeight + verticalOffset) / (float) mAppBarLayoutHeight;
                    mFabOptions.setAlpha(alpha);
                }
            }
        });
        mFabOptions = (FabOptions) findViewById(R.id.fab_options);
        mFabOptions.setButtonsMenu(R.menu.photo_option_menu);
        mFabOptions.setOnClickListener(this);
        FluidLayout.LayoutParams params = new FluidLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(12, 12, 12, 12);
        genTag();

        TextView textView = getView(R.id.test_xiaozhuan);
        Typeface typeFace5 = Typeface.createFromAsset(getAssets(), "fonts/STXINGKA.TTF");
        textView.setTypeface(typeFace5);
        textView.setText("李小强  ABCD  abcdefgh  1324343543");
    }

    private void genTag() {
        mFluidLayout.removeAllViews();
        mFluidLayout.setGravity(Gravity.TOP);
        for (int i = 0; i < 5; i++) {
            TextView tv = new TextView(this);
            tv.setText("这里是标签内容");
            tv.setTextSize(13);
            tv.setHeight(DimenUtil.dp2px(20));
            tv.setGravity(Gravity.CENTER);
            tv.setPadding(10, 0, 0, 10);
            tv.setBackgroundResource(R.drawable.add_photo_tag_bg);
            tv.setTextColor(Color.parseColor("#ffffff"));

            FluidLayout.LayoutParams params = new FluidLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(12, 12, 12, 12);
            mFluidLayout.addView(tv, params);
        }
    }

    @Override
    protected void initData() {
        FrescoUtils.getBitmapByRes(R.mipmap.test_image4, this, GlobalConfigManage.getInstance().getScreenWidth(),
                GlobalConfigManage.getInstance().getScreenHeight(), new FrescoUtils.BitmapListener() {
                    @Override
                    public void onSuccess(Bitmap bitmap) {
                        if(bitmap == null) return;
                        int width = bitmap.getWidth();
                        int height = bitmap.getHeight();
                        if(width > height) {
                            // 创建操作图片用的matrix对象
                            Matrix matrix = new Matrix();
                            //旋转图片 动作
                            matrix.postRotate(90);
                            //创建新的图片
                            Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0,
                                    width, height, matrix, true);
                            int screenWidth = GlobalConfigManage.getInstance().getScreenWidth();
                            int showHeight = (int)((float)screenWidth / height * width);
                            setBitmap(resizedBitmap, showHeight);
                        }else{
                            int screenWidth = GlobalConfigManage.getInstance().getScreenWidth();
                            int showHeight = (int)((float)screenWidth / width * height);
                            setBitmap(bitmap, showHeight);
                        }
                    }

                    @Override
                    public void onFail() {

                    }
                });
    }

    private void setBitmap(Bitmap bitmap, int showHeight){
        ViewGroup.LayoutParams params = mDetailPhoto.getLayoutParams();
        params.width = GlobalConfigManage.getInstance().getScreenWidth();
        params.height = showHeight;
        mDetailPhoto.setLayoutParams(params);
        mDetailPhoto.setImageBitmap(bitmap);
    }

    public static void startActivity(Context context){
        Intent intent = new Intent(context, PhotoDetailActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.faboptions_favorite:  //收藏

                break;
            case R.id.faboptions_textsms:   //评论

                break;
            case R.id.faboptions_download:  //下载

                break;
            case R.id.faboptions_share:     //分享

                break;
        }
    }
}
