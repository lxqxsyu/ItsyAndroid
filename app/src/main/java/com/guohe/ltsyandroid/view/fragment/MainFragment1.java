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
import com.guohe.ltsyandroid.util.FrescoUtils;
import com.guohe.ltsyandroid.util.LogUtil;
import com.stone.card.library.CardAdapter;
import com.stone.card.library.CardSlidePanel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 水寒 on 2017/7/14.
 */

public class MainFragment1 extends BaseMainFragment{

    private static final int HAND_TIP_TIME_COUNT = 0x0021;

    private CardSlidePanel.CardSwitchListener mCardSwitchListener;
    private List<CardDataItem> dataList = new ArrayList<>();
    private TextView mTipContent;
    private TextView mTipAuthor;

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

    private int imagePaths[] = {R.mipmap.test_img, R.mipmap.test_imag2, R.mipmap.test_image3,
        R.mipmap.test_image4, R.mipmap.test_image6, R.mipmap.test_image7,
        R.mipmap.test_image3}; // 12个图片资源

    private String names[] = {"秋天的校园", "别样的梅花", "蝴蝶",
            "小虫虫的故事", "成龙", "谢霆锋",
            "梁朝伟"}; // 12个人名

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



        mHandler.sendEmptyMessage(HAND_TIP_TIME_COUNT);
    }

    private void tipNext(){
        //mTipContent.setText();
        mHandler.sendEmptyMessageDelayed(HAND_TIP_TIME_COUNT, 3000);
    }

    @Override
    protected void initView(View view) {
        mTipContent = getView(R.id.main_tip_content);
        mTipAuthor = getView(R.id.main_tip_author);
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
