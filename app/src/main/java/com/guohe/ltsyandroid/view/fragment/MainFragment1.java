package com.guohe.ltsyandroid.view.fragment;

import android.graphics.Rect;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.guohe.ltsyandroid.MvpPresenter;
import com.guohe.ltsyandroid.R;
import com.guohe.ltsyandroid.custome.WeakRefrenceHandler;
import com.guohe.ltsyandroid.model.entry.TipMessageBean;
import com.guohe.ltsyandroid.util.FrescoUtils;
import com.guohe.ltsyandroid.util.LogUtil;
import com.stone.card.library.CardAdapter;
import com.stone.card.library.CardSlidePanel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by 水寒 on 2017/7/14.
 */

public class MainFragment1 extends BaseMainFragment{

    private static final int HAND_TIP_TIME_COUNT = 0x0021;

    private CardSlidePanel.CardSwitchListener mCardSwitchListener;
    private List<CardDataItem> dataList = new ArrayList<>();
    private List<TipMessageBean> mTips = new ArrayList<>();
    private TextView mTipTitle;
    private TextView mTipContent;
    private int mCurrentTipIndex;

    private int imagePaths[] = {R.mipmap.test_img, R.mipmap.test_imag2, R.mipmap.test_image3,
        R.mipmap.test_image4, R.mipmap.test_image6, R.mipmap.test_image7,
        R.mipmap.test_image3}; // 12个图片资源

    private String names[] = {"秋天的校园", "别样的梅花", "蝴蝶",
            "小虫虫的故事", "成龙", "谢霆锋",
            "梁朝伟"}; // 12个人名

    private WeakRefrenceHandler<MainFragment1> mHandler = new WeakRefrenceHandler<MainFragment1>(this) {
        @Override
        protected void handleMessage(MainFragment1 ref, Message msg) {
            switch (msg.what){
                case HAND_TIP_TIME_COUNT:
                    tipNext();
                    break;
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        return view;
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
        TipMessageBean tip1 = new TipMessageBean(0, "这是一个系统的测试消息");
        TipMessageBean tip2 = new TipMessageBean(1, "马丁•帕尔", "不要让照片上的人保持微笑状态，除非你只想拍快照", "");
        TipMessageBean tip3 = new TipMessageBean(2, "马丁•帕尔", "当你拍摄他人的时候，越靠近越好", "");
        TipMessageBean tip4 = new TipMessageBean(3, "马丁•帕尔", "选择合适的拍摄环境，我的意思是仅仅适合于拍摄对象的拍摄环境", "");
        TipMessageBean tip5 = new TipMessageBean(4, "马丁•帕尔", "然后，不要让他们笑了，这是业余拍摄者拍摄的最大误区", "");
        TipMessageBean tip6 = new TipMessageBean(5, "马丁•帕尔", "对于偷拍，只要你锲而不舍，幸运女神总会降临的", "");

        mTips.add(tip1);
        mTips.add(tip2);
        mTips.add(tip3);
        mTips.add(tip4);
        mTips.add(tip5);
        mTips.add(tip6);

        mCurrentTipIndex = new Random().nextInt(mTips.size());
        showNextTip();

        mHandler.sendEmptyMessage(HAND_TIP_TIME_COUNT);
    }

    private void tipNext(){
        //mTipContent.setText();
        mHandler.sendEmptyMessageDelayed(HAND_TIP_TIME_COUNT, 3000);
    }

    @Override
    protected void initView(View view) {
        mTipTitle = getView(R.id.main_tip_author);
        mTipContent = getView(R.id.main_tip_content);
        final CardSlidePanel slidePanel = (CardSlidePanel) getView(R.id.image_slide_panel);
        // 左右滑动监听
        mCardSwitchListener = new CardSlidePanel.CardSwitchListener() {

            @Override
            public void onShow(int index) {
                Log.d("Card", "正在显示-" + dataList.get(index).userName);
                if(dataList.size() - index <= 3){
                    appendDataList();
                    slidePanel.getAdapter().notifyDataSetChanged();
                }
            }

            @Override
            public void onCardVanish(int index, int type) {
                Log.d("Card", "正在消失-" + dataList.get(index).userName + " 消失type=" + type);
            }
        };
        slidePanel.setCardSwitchListener(mCardSwitchListener);


        // 2. 绑定Adapter
        prepareDataList();
        slidePanel.setAdapter(new CardAdapter() {
            @Override
            public int getLayoutId() {
                return R.layout.card_slide_item;
            }

            @Override
            public int getCount() {
                return dataList.size();
            }

            @Override
            public void bindView(View view, int index) {
                Object tag = view.getTag();
                ViewHolder viewHolder;
                if (null != tag) {
                    viewHolder = (ViewHolder) tag;
                } else {
                    viewHolder = new ViewHolder(view);
                    view.setTag(viewHolder);
                }

                viewHolder.bindData(dataList.get(index));
            }

            @Override
            public Rect obtainDraggableArea(View view) {
                // 可滑动区域定制，该函数只会调用一次
                View contentView = view.findViewById(R.id.card_item_content);
                View topLayout = view.findViewById(R.id.card_top_layout);
                View bottomLayout = view.findViewById(R.id.card_bottom_layout);
                int left = view.getLeft() + contentView.getPaddingLeft() + topLayout.getPaddingLeft();
                int right = view.getRight() - contentView.getPaddingRight() - topLayout.getPaddingRight();
                int top = view.getTop() + contentView.getPaddingTop() + topLayout.getPaddingTop();
                int bottom = view.getBottom() - contentView.getPaddingBottom() - bottomLayout.getPaddingBottom();
                return new Rect(left, top, right, bottom);
            }
        });


       /* // 3. notifyDataSetChanged调用
        findViewById(R.id.notify_change).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendDataList();
                slidePanel.getAdapter().notifyDataSetChanged();
            }
        });*/
    }

    private void showNextTip(){
        if(mTipContent == null) return;
        mCurrentTipIndex++;
        if(mCurrentTipIndex >= mTips.size()){
            mCurrentTipIndex = 0;
        }
        TipMessageBean tipBean = mTips.get(mCurrentTipIndex);
        mTipTitle.setText(tipBean.getTipTitle());
        mTipContent.setText(tipBean.getTipContent());
    }

    @Override
    public void onChoosed() {
        if(mTips.isEmpty()) return;
        showNextTip();
    }

    private void prepareDataList() {
        int num = imagePaths.length;
        for (int i = 0; i < num; i++) {
            CardDataItem dataItem = new CardDataItem();
            dataItem.userName = names[i];
            dataItem.imagePath = imagePaths[i];
            dataItem.likeNum = (int) (Math.random() * 10);
            dataItem.imageNum = (int) (Math.random() * 6);
            dataList.add(dataItem);
        }
    }

    private void appendDataList() {
        for (int i = 0; i < 6; i++) {
            CardDataItem dataItem = new CardDataItem();
            dataItem.userName = "From Append";
            dataItem.imagePath = imagePaths[i];
            dataItem.likeNum = (int) (Math.random() * 10);
            dataItem.imageNum = (int) (Math.random() * 6);
            dataList.add(dataItem);
        }
    }

    @Override
    public void attachActionBarView(View actionbarView) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        LogUtil.d("fragment1_menu_click == " + item.getTitle());
        return super.onOptionsItemSelected(item);
    }

    public class CardDataItem {
        int imagePath;
        String userName;
        int likeNum;
        int imageNum;
    }

    class ViewHolder {

        SimpleDraweeView imageView;
        View maskView;
        TextView userNameTv;
        TextView imageNumTv;
        TextView likeNumTv;

        public ViewHolder(View view) {
            imageView = (SimpleDraweeView) view.findViewById(R.id.card_image_view);
            maskView = view.findViewById(R.id.maskView);
            userNameTv = (TextView) view.findViewById(R.id.card_user_name);
            imageNumTv = (TextView) view.findViewById(R.id.card_pic_num);
            likeNumTv = (TextView) view.findViewById(R.id.card_like);
        }

        public void bindData(CardDataItem itemData) {
            FrescoUtils.loadRes(imageView, itemData.imagePath, null, 0, 0, null);
            userNameTv.setText(itemData.userName);
            imageNumTv.setText(itemData.imageNum + "10");
            likeNumTv.setText(itemData.likeNum + "298");
        }
    }
}
