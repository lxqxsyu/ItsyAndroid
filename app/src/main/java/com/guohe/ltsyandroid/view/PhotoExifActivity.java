package com.guohe.ltsyandroid.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.guohe.ltsyandroid.MvpPresenter;
import com.guohe.ltsyandroid.R;
import com.guohe.ltsyandroid.view.adapter.PhotoExifAdapter;
import com.guohe.ltsyandroid.view.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shuihan on 2017/7/21.
 * 选择相机
 */

public class PhotoExifActivity extends BaseActivity {

    private List<String> mPhotoExifs = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private PhotoExifAdapter mAdapter;

    @Override
    public void initPresenter(List<MvpPresenter> presenters) {

    }

    @Override
    public void turnToOtherView() {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_addphoto_exifs;
    }

    @Override
    protected boolean canSlidr() {
        return true;
    }

    @Override
    protected void initView() {
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        mPhotoExifs.addAll((List<String>) getIntent().getSerializableExtra("exifs"));
        mRecyclerView = getView(R.id.photo_exif_recyclerview);
        bindView();
    }

    private void bindView() {
        mRecyclerView.setHasFixedSize(false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new PhotoExifAdapter(PhotoExifActivity.this, mPhotoExifs);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                PhotoExifActivity.this.finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public static void startActivity(Context context, ArrayList<String> exifs){
        Intent intent = new Intent(context, PhotoExifActivity.class);
        intent.putExtra("exifs", exifs);
        context.startActivity(intent);
    }
}
