package com.guohe.ltsyandroid.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.amap.api.maps.AMap;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MultiPointItem;
import com.amap.api.maps.model.MultiPointOverlay;
import com.amap.api.maps.model.MultiPointOverlayOptions;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.help.Inputtips;
import com.amap.api.services.help.InputtipsQuery;
import com.amap.api.services.help.Tip;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.guohe.ltsyandroid.MvpPresenter;
import com.guohe.ltsyandroid.R;
import com.guohe.ltsyandroid.util.LogUtil;
import com.guohe.ltsyandroid.view.base.BaseActivity;
import com.guohe.ltsyandroid.view.fragment.BaseMainFragment;
import com.guohe.ltsyandroid.view.fragment.MainFragment1;
import com.guohe.ltsyandroid.view.fragment.MainFragment2;
import com.guohe.ltsyandroid.view.fragment.MainFragment3;
import com.guohe.ltsyandroid.view.fragment.MainFragment4;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends BaseActivity implements PoiSearch.OnPoiSearchListener, Inputtips.InputtipsListener {

    private int mCurrentIndex = 0;   //当前选择
    private BaseMainFragment mCurrentFragment;
    private List<Class<? extends BaseMainFragment>> mFragmentsClass = new ArrayList<>();
    private Map<Integer, BaseMainFragment> mFragments = new HashMap<>();
    private MaterialSearchView mSearchView;
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
        mSearchView = getView(R.id.search_view);
        Toolbar toolbar = getView(R.id.toolbar);
        setSupportActionBar(toolbar);
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
        initSearch();
    }

    private void initSearch(){
        mSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String keyword) {
                if(mCurrentIndex == 0) {
                    LogUtil.d("Search_keyword == " + keyword);
                    PoiSearch.Query query = new PoiSearch.Query(keyword, "110000", "");
                    //keyWord表示搜索字符串，
                    //第二个参数表示POI搜索类型，二者选填其一，选用POI搜索类型时建议填写类型代码，码表可以参考下方（而非文字）
                    //cityCode表示POI搜索区域，可以是城市编码也可以是城市名称，也可以传空字符串，空字符串代表全国在全国范围内进行搜索
                    query.setPageSize(10);// 设置每页最多返回多少条poiitem
                    query.setPageNum(1);//设置查询页码
                    PoiSearch poiSearch = new PoiSearch(MainActivity.this, query);
                    poiSearch.setOnPoiSearchListener(MainActivity.this);
                    poiSearch.searchPOIAsyn();
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(mCurrentIndex == 0) {
                    //Do some magic
                    //第二个参数传入null或者“”代表在全国进行检索，否则按照传入的city进行检索
                    InputtipsQuery inputquery = new InputtipsQuery(newText, "");
                    inputquery.setCityLimit(true);//限制在当前城市
                    Inputtips inputTips = new Inputtips(MainActivity.this, inputquery);
                    inputTips.setInputtipsListener(MainActivity.this);
                    inputTips.requestInputtipsAsyn();
                }
                return true;
            }
        });

        mSearchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                //Do some magic
                //SearchActivity.startActivity(MainFragment1.this.getContext());
            }

            @Override
            public void onSearchViewClosed() {
                //Do some magic
            }
        });
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
        /*for(int i = 0; i < mFragments.size(); i++) {
            if(i != currentIndex) {
                fragmentTransaction.hide(mFragments.get(i));
            }
        }*/
        fragmentTransaction.hide(mCurrentFragment);
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
        MenuItem item;
        menu.clear();
        switch (mCurrentIndex){
            case 0:
                getMenuInflater().inflate(R.menu.main_menu, menu);
                item = menu.findItem(R.id.action_search);
                mSearchView.setMenuItem(item);
                mSearchView.setHint("搜索位置");
                break;
            case 1:
                getMenuInflater().inflate(R.menu.main_menu2, menu);
                item = menu.findItem(R.id.action_search);
                mSearchView.setMenuItem(item);
                mSearchView.setHint("搜索作品");
                if(MainFragment2.mCurrentType == MainFragment2.TYPE_COLLECTION){
                    item = menu.findItem(R.id.action_type);
                    item.setTitle("图集");
                    item.setIcon(R.drawable.ic_style_24px);
                }
                break;
            case 2:
                break;
            case 3:
                break;
        }
        if (mSearchView.isSearchOpen()) {
            mSearchView.closeSearch();
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        if (mSearchView.isSearchOpen()) {
            mSearchView.closeSearch();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        mCurrentFragment.onOptionsItemSelected(item);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPoiSearched(PoiResult poiResult, int j) {
        List<PoiItem> pitems = poiResult.getPois();
        //poiResult.get
        for (int i = 0; i < pitems.size(); i++) {
            //创建MultiPointItem存放，海量点中某单个点的位置及其他信息
            PoiItem pi = pitems.get(i);
            //LogUtil.d("adNmae == " + pi.getAdName());
            //LogUtil.d("city == " + pi.getCityName());
        }
        if(mCurrentIndex == 0) {
            MainFragment1 fragment = (MainFragment1) mCurrentFragment;
            if(fragment == null) return;
            AMap aMap = fragment.getAMap();
            if(aMap == null) return;
            MultiPointOverlayOptions overlayOptions = new MultiPointOverlayOptions();
            overlayOptions.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                    .decodeResource(getResources(), R.mipmap.ic_launcher_round)));//设置图标
            overlayOptions.anchor(0.5f, 0.5f); //设置锚点
            MultiPointOverlay multiPointOverlay = aMap.addMultiPointOverlay(overlayOptions);
            List<PoiItem> poiItems = poiResult.getPois();
            List<MultiPointItem> list = new ArrayList<>();
            for (int i = 0; i < poiItems.size(); i++) {
                //创建MultiPointItem存放，海量点中某单个点的位置及其他信息
                MultiPointItem multiPointItem = new MultiPointItem(
                        new LatLng(poiItems.get(i).getLatLonPoint().getLatitude(), poiItems.get(i).getLatLonPoint().getLongitude()));
                list.add(multiPointItem);
            }
            multiPointOverlay.setItems(list);//将规范化的点集交给海量点管理对象设置，待加载完毕即可看到海量点信息
        }
    }


    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {

    }

    @Override
    public void onGetInputtips(List<Tip> list, int j) {
        String[] inputtips = new String[list.size()];
        for(int i = 0; i < list.size(); i++){
            inputtips[i] = list.get(i).getAddress();
        }
        LogUtil.d("suggestion == " + inputtips);
        mSearchView.setSuggestions(inputtips);
    }
}
