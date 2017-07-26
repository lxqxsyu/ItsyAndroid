package com.guohe.ltsyandroid.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;
import com.guohe.ltsyandroid.MvpPresenter;
import com.guohe.ltsyandroid.R;
import com.guohe.ltsyandroid.util.FrescoUtils;
import com.guohe.ltsyandroid.view.adapter.CollectionCardAdapter;
import com.guohe.ltsyandroid.view.base.BaseActivity;
import com.view.jameson.library.CardScaleHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shuihan on 2017/7/18.
 */

public class PhotoCollectionActivity extends BaseActivity {

    private RecyclerView mRecyclerView;
    private SimpleDraweeView mBlurView;
    private CardScaleHelper mCardScaleHelper = null;
    private List<Integer> mList = new ArrayList<>();
    private CollectionCardAdapter mAdapter;
        //return  activity_photocollection;
        //photocollection_card_item



    public static void startActivity(Context context){
        Intent intent = new Intent(context, PhotoCollectionActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void initPresenter(List<MvpPresenter> presenters) {

    }

    @Override
    public void turnToOtherView() {

    }

    @Override
    protected void initWindow() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_photocollection;
    }

    @Override
    protected void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.photo_collection_recyclerView);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mAdapter = new CollectionCardAdapter(mList);
        mRecyclerView.setAdapter(mAdapter);
        // mRecyclerView绑定scale效果
        mCardScaleHelper = new CardScaleHelper();
        mCardScaleHelper.setCurrentItemPos(2);
        mCardScaleHelper.attachToRecyclerView(mRecyclerView);

        initBlurBackground();
    }

    private void initBlurBackground() {
        mBlurView = (SimpleDraweeView) findViewById(R.id.collection_blurView);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    //notifyBackgroundChange();
                }
            }
        });
        FrescoUtils.loadRes(mBlurView, R.mipmap.ic_default_collection_bg, null, 0, 0, null);
        //notifyBackgroundChange();
    }

   /* private void notifyBackgroundChange() {
        if (mLastPos == mCardScaleHelper.getCurrentItemPos()) return;
        mLastPos = mCardScaleHelper.getCurrentItemPos();
        final int resId = mList.get(mCardScaleHelper.getCurrentItemPos());
        mBlurView.removeCallbacks(mBlurRunnable);
        mBlurRunnable = new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), resId);
                ViewSwitchUtils.startSwitchBackgroundAnim(mBlurView, BlurBitmapUtils.getBlurBitmap(mBlurView.getContext(), bitmap, 15));
            }
        };
        mBlurView.postDelayed(mBlurRunnable, 500);
    }*/

    @Override
    protected void initData() {
        for (int i = 0; i < 10; i++) {
            mList.add(R.mipmap.test_image1);
            mList.add(R.mipmap.test_image4);
            mList.add(R.mipmap.test_image6);
        }
        mAdapter.notifyDataSetChanged();
    }
}
