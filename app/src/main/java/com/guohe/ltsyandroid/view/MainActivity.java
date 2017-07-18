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
    private View mActionBarView;
    private View mActionBarFragment1;
    private View mActionBarFragment3;
    private View mActionBarFragment4;

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
        customeActionBar();
        mCurrentIndex = 0;
        getFragmentInstance();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, mCurrentFragment)
                .show(mCurrentFragment)
                .commit();
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
        for(int i = 0; i < mFragments.size(); i++) {
            if(i != currentIndex) {
                fragmentTransaction.hide(mFragments.get(i));
            }
        }
        if(!mFragments.containsKey(mCurrentIndex)){
            fragmentTransaction.add(R.id.fragment_container, getFragmentInstance(), String.valueOf(mCurrentIndex));
        }else{
            BaseMainFragment fragment = getFragmentInstance();
            fragment.setUserVisibleHint(true);
            fragmentTransaction.show(fragment);
        }
        fragmentTransaction.commit();
        changeActionBar();
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
        mCurrentFragment.attachActionBarView(mActionBarView);
        return mCurrentFragment;
    }

    private void customeActionBar(){
        ActionBar actionBar = getSupportActionBar();
        LogUtil.d("actionbar == " + actionBar);
        if (actionBar != null) {
            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM); //Enable自定义的View
            mActionBarView = getLayoutInflater().inflate(R.layout.actionbar_main,
                    null);
            ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT,
                    ActionBar.LayoutParams.WRAP_CONTENT);
            actionBar.setCustomView(mActionBarView, layoutParams);//设置自定义的布局：actionbar_custom
            SearchView searchView=(SearchView) mActionBarView.findViewById(R.id.main_searchview);
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
            mActionBarFragment1 = mActionBarView.findViewById(R.id.actionbar_fragment1);
            mActionBarFragment3 = mActionBarView.findViewById(R.id.actionbar_fragment3);
            mActionBarFragment4 = mActionBarView.findViewById(R.id.actionbar_fragment4);
        }
    }

    private void changeActionBar(){
        LogUtil.d("changeActionbar_index == " + mCurrentIndex);
        switch (mCurrentIndex){
            case 0:
                mActionBarFragment1.setVisibility(View.VISIBLE);
                mActionBarFragment3.setVisibility(View.GONE);
                mActionBarFragment4.setVisibility(View.GONE);
                break;
            case 1:
                mActionBarFragment1.setVisibility(View.VISIBLE);
                mActionBarFragment3.setVisibility(View.GONE);
                mActionBarFragment4.setVisibility(View.GONE);
                break;
            case 2:
                mActionBarFragment3.setVisibility(View.VISIBLE);
                mActionBarFragment1.setVisibility(View.GONE);
                mActionBarFragment4.setVisibility(View.GONE);
                break;
            case 3:
                mActionBarFragment4.setVisibility(View.VISIBLE);
                mActionBarFragment3.setVisibility(View.GONE);
                mActionBarFragment1.setVisibility(View.GONE);
                break;
        }
        invalidateOptionsMenu();
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
    public boolean onPrepareOptionsMenu(Menu menu) {
        LogUtil.d("preparedOption_index == " + mCurrentIndex);
        menu.clear();
        switch (mCurrentIndex){
            case 0:
                getMenuInflater().inflate(R.menu.main_menu, menu);
                break;
            case 1:
                getMenuInflater().inflate(R.menu.main_menu2, menu);
                break;
            case 2:
                break;
            case 3:
                break;
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        mCurrentFragment.onOptionsItemSelected(item);
        return super.onOptionsItemSelected(item);
    }
}
