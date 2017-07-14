package com.guohe.ltsyandroid.view;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.guohe.ltsyandroid.MvpPresenter;
import com.guohe.ltsyandroid.R;
import com.guohe.ltsyandroid.util.LogUtil;
import com.guohe.ltsyandroid.view.base.BaseActivity;
import com.guohe.ltsyandroid.view.fragment.BaseMainFragment;
import com.guohe.ltsyandroid.view.fragment.MainFragment1;
import com.guohe.ltsyandroid.view.fragment.MainFragment2;
import com.guohe.ltsyandroid.view.fragment.MainFragment3;
import com.guohe.ltsyandroid.view.fragment.MainFragment4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends BaseActivity {

    private int mCurrentIndex = 0;   //当前选择
    private BaseMainFragment mCurrentFragment;
    private List<Class<? extends BaseMainFragment>> mFragmentsClass = new ArrayList<>();
    private Map<Integer, BaseMainFragment> mFragments = new HashMap<>();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_menu1:
                    setFragment(0);
                    return true;
                case R.id.navigation_menu2:
                    setFragment(1);
                    return true;
                case R.id.navigation_menu3:
                    setFragment(2);
                    return true;
                case R.id.navigation_menu4:
                    setFragment(3);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mFragmentsClass.add(MainFragment1.class);
        mFragmentsClass.add(MainFragment2.class);
        mFragmentsClass.add(MainFragment3.class);
        mFragmentsClass.add(MainFragment4.class);
        mCurrentIndex = 0;
        getFragmentInstance();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, mCurrentFragment)
                .show(mCurrentFragment)
                .commit();
        customeActionBar();
    }

    @Override
    protected void initData() {

    }

    /**
     * 设置Fragment显示
     */
    private void setFragment(int currentIndex) {
        if(mCurrentIndex == currentIndex) return;
        mCurrentIndex = currentIndex;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.hide(mCurrentFragment);
        if(!mFragments.containsKey(mCurrentIndex)){
            fragmentTransaction.add(R.id.fragment_container, getFragmentInstance(), String.valueOf(mCurrentIndex));
        }else{
            BaseMainFragment fragment = getFragmentInstance();
            fragment.setUserVisibleHint(true);
            fragmentTransaction.show(fragment);
        }
        fragmentTransaction.commit();
    }

    private BaseMainFragment getFragmentInstance() {
        if(!mFragments.containsKey(mCurrentIndex)) {
            try {
                mCurrentFragment = mFragmentsClass.get(mCurrentIndex).newInstance();
                mFragments.put(mCurrentIndex, mCurrentFragment);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        mCurrentFragment = mFragments.get(mCurrentIndex);
        return mCurrentFragment;
    }

    private void customeActionBar(){
        ActionBar actionBar = getSupportActionBar();
        LogUtil.d("actionbar == " + actionBar);
        if (actionBar != null) {
            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM); //Enable自定义的View
            View view = getLayoutInflater().inflate(R.layout.actionbar_main_fragment1,
                    null);
            ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT,
                    ActionBar.LayoutParams.WRAP_CONTENT);
            actionBar.setCustomView(view, layoutParams);//设置自定义的布局：actionbar_custom
            SearchView searchView=(SearchView)view.findViewById(R.id.main_searchview);
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    return false;
                }
            });
        }
    }

    @Override
    public void initPresenter(List<MvpPresenter> presenters) {

    }

    @Override
    public void turnToOtherView() {

    }

    public static void startActivity(Context context){
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

        }
        return super.onOptionsItemSelected(item);
    }
}
