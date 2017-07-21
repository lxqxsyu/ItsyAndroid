package com.guohe.ltsyandroid.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.support.annotation.LayoutRes;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.guohe.ltsyandroid.MvpPresenter;
import com.guohe.ltsyandroid.R;
import com.guohe.ltsyandroid.manage.config.GlobalConfigManage;
import com.guohe.ltsyandroid.util.FrescoUtils;
import com.guohe.ltsyandroid.view.adapter.CommentListAdapter;
import com.guohe.ltsyandroid.view.base.BaseActivity;
import com.jaeger.library.StatusBarUtil;
import com.joaquimley.faboptions.FabOptions;

import java.util.List;

/**
 * Created by shuihan on 2017/7/18.
 * 照片详情
 */

public class PhotoDetailActivity extends BaseActivity implements View.OnClickListener{

    private CollapsingToolbarLayout mToolbarLayout;
    private SimpleDraweeView mDetailPhoto;
    private RecyclerView mRecyclerView;
    private CommentListAdapter mAdapter;

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
        mToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        mDetailPhoto = getView(R.id.photodetail_photo);
        mRecyclerView = getView(R.id.photo_detail_commentlist);
        FabOptions fabOptions = (FabOptions) findViewById(R.id.fab_options);
        fabOptions.setButtonsMenu(R.menu.photo_option_menu);
        fabOptions.setOnClickListener(this);

        bindView();
    }

    private void bindView() {
        mRecyclerView.setHasFixedSize(false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new CommentListAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        FrescoUtils.getBitmapByRes(R.mipmap.test_image4, this, GlobalConfigManage.getInstance().getScreenWidth(),
                GlobalConfigManage.getInstance().getScreenHeight(), new FrescoUtils.BitmapListener() {
                    @Override
                    public void onSuccess(Bitmap bitmap) {
                        if(bitmap == null) return;
                        // Palette的部分
                       /* Palette.generateAsync(bitmap, new Palette.PaletteAsyncListener() {
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
                        });*/
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
