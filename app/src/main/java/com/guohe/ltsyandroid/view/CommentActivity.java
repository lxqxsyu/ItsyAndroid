package com.guohe.ltsyandroid.view;

import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.guohe.ltsyandroid.MvpPresenter;
import com.guohe.ltsyandroid.R;
import com.guohe.ltsyandroid.view.adapter.CommentListAdapter;
import com.guohe.ltsyandroid.view.base.BaseActivity;

import java.util.List;

/**
 * Created by shuihan on 2017/7/24.
 */

public class CommentActivity extends BaseActivity {

    private RecyclerView mRecyclerView;
    private CommentListAdapter mAdapter;

    @Override
    public void initPresenter(List<MvpPresenter> presenters) {

    }

    @Override
    protected boolean canSlidr() {
        return true;
    }

    @Override
    public void turnToOtherView() {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_comment;
    }

    @Override
    protected void initView() {
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        mRecyclerView = getView(R.id.photo_commentlist);

        bindView();
    }

    private void bindView() {
        mRecyclerView.setHasFixedSize(false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new CommentListAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                CommentActivity.this.finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initData() {

    }
}
