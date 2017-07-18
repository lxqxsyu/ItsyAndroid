package com.guohe.ltsyandroid.view.fragment;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MultiPointItem;
import com.amap.api.maps.model.MultiPointOverlay;
import com.amap.api.maps.model.MultiPointOverlayOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.help.Inputtips;
import com.amap.api.services.help.InputtipsQuery;
import com.amap.api.services.help.Tip;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.guohe.ltsyandroid.MvpPresenter;
import com.guohe.ltsyandroid.R;
import com.guohe.ltsyandroid.util.LogUtil;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 水寒 on 2017/7/14.
 */

public class MainFragment1 extends BaseMainFragment implements PoiSearch.OnPoiSearchListener, Inputtips.InputtipsListener {

    private MapView mMapView;
    private AMap mAMap;
    private MaterialSearchView mSearchView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        //获取地图控件引用
        mMapView = getView(R.id.main_map);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }

    @Override
    public void initPresenter(List<MvpPresenter> presenters) {

    }

    @Override
    public void turnToOtherView() {

    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_main_one;
    }

    @Override
    protected void initData() {
        initMap();
    }

    private void initMap(){
        mAMap = mMapView.getMap();

        MyLocationStyle myLocationStyle;
        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        mAMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        //aMap.getUiSettings().setMyLocationButtonEnabled(true);设置默认定位按钮是否显示，非必需设置。
        mAMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        mAMap.moveCamera(CameraUpdateFactory.zoomTo(15));



        MarkerOptions markerOption = new MarkerOptions();
        markerOption.position(new LatLng(34.2182600000, 108.9642000000));
        markerOption.title("西安市").snippet("西安市：34.341568, 108.940174");

        markerOption.draggable(true);//设置Marker可拖动
        markerOption.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                .decodeResource(getResources(),R.mipmap.ic_launcher_round)));
        // 将Marker设置为贴地显示，可以双指下拉地图查看效果
        //markerOption.setFlat(true);//设置marker平贴地图效果
        mAMap.addMarker(markerOption);
    }

    @Override
    public void onPoiSearched(PoiResult poiResult, int j) {
        MultiPointOverlayOptions overlayOptions = new MultiPointOverlayOptions();
        overlayOptions.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                .decodeResource(getResources(),R.mipmap.ic_launcher_round)));//设置图标
        overlayOptions.anchor(0.5f,0.5f); //设置锚点
        MultiPointOverlay multiPointOverlay = mAMap.addMultiPointOverlay(overlayOptions);
        List<PoiItem> poiItems = poiResult.getPois();
        List<MultiPointItem> list = new ArrayList<>();
        for(int i = 0; i < poiItems.size(); i++){
            //创建MultiPointItem存放，海量点中某单个点的位置及其他信息
            MultiPointItem multiPointItem = new MultiPointItem(
                    new LatLng(poiItems.get(i).getLatLonPoint().getLatitude(), poiItems.get(i).getLatLonPoint().getLongitude()));
            list.add(multiPointItem);
        }
        multiPointOverlay.setItems(list);//将规范化的点集交给海量点管理对象设置，待加载完毕即可看到海量点信息
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
        mSearchView.setSuggestions(inputtips);
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    public void attachActionBarView(View actionbarView) {
        if(actionbarView == null) return;
        mSearchView = (MaterialSearchView) actionbarView.findViewById(R.id.search_view);
        //mSearchView.setVoiceSearch(true); //or false
        mSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String keyword) {
                LogUtil.d("Search_keyword == " + keyword);
                PoiSearch.Query query = new PoiSearch.Query(keyword, "110000", "");
                //keyWord表示搜索字符串，
                //第二个参数表示POI搜索类型，二者选填其一，选用POI搜索类型时建议填写类型代码，码表可以参考下方（而非文字）
                //cityCode表示POI搜索区域，可以是城市编码也可以是城市名称，也可以传空字符串，空字符串代表全国在全国范围内进行搜索
                query.setPageSize(10);// 设置每页最多返回多少条poiitem
                query.setPageNum(1);//设置查询页码
                PoiSearch poiSearch = new PoiSearch(MainFragment1.this.getContext(), query);
                poiSearch.setOnPoiSearchListener(MainFragment1.this);
                poiSearch.searchPOIAsyn();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Do some magic
                //第二个参数传入null或者“”代表在全国进行检索，否则按照传入的city进行检索
                InputtipsQuery inputquery = new InputtipsQuery(newText, "");
                inputquery.setCityLimit(true);//限制在当前城市
                Inputtips inputTips = new Inputtips(MainFragment1.this.getContext(), inputquery);
                inputTips.setInputtipsListener(MainFragment1.this);
                inputTips.requestInputtipsAsyn();
                return true;
            }
        });

        mSearchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                //Do some magic
            }

            @Override
            public void onSearchViewClosed() {
                //Do some magic
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        LogUtil.d("fragment1_menu_click == " + item.getTitle());
        return super.onOptionsItemSelected(item);
    }
}
