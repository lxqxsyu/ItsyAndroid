package com.guohe.ltsyandroid.view.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.graphics.Palette;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.guohe.ltsyandroid.MvpPresenter;
import com.guohe.ltsyandroid.R;
import com.guohe.ltsyandroid.util.FrescoUtils;
import com.guohe.ltsyandroid.view.AddPhotoActivity;
import com.guohe.ltsyandroid.view.FeedbackActivity;
import com.guohe.ltsyandroid.view.FollowdMeActivity;
import com.guohe.ltsyandroid.view.MyFavoriteActivity;
import com.guohe.ltsyandroid.view.MyFollowedActivity;
import com.guohe.ltsyandroid.view.PersonalCollectionActivity;
import com.guohe.ltsyandroid.view.PersonalPhotoActivity;
import com.guohe.ltsyandroid.view.SettingActivity;

import java.util.List;

import static com.wou.commonutils.ColorGenerator.colorBurn;

/**
 * Created by 水寒 on 2017/7/14.
 */

public class MainFragment4 extends BaseMainFragment implements View.OnClickListener{

    private SimpleDraweeView mAuthorBg;
    private SimpleDraweeView mAuthorHead;
    private TextView mNotifyMsg;

    @Override
    public void initPresenter(List<MvpPresenter> presenters) {

    }

    @Override
    public void turnToOtherView() {

    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_main_four;
    }

    @Override
    protected void initData() {
        FrescoUtils.loadRes(mAuthorBg, R.mipmap.test_image6, null, 0, 0, null);
        FrescoUtils.setCircle(mAuthorHead, 0);
        FrescoUtils.loadRes(mAuthorHead, R.mipmap.test_image5, null, 0, 0, null);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.test_image6);
        // Palette的部分
        Palette.generateAsync(bitmap, new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                Palette.Swatch vibrant = palette.getVibrantSwatch();
                mNotifyMsg.setBackgroundColor(colorBurn(vibrant.getRgb()));
            }
        });
    }

    @Override
    protected void initView(View view) {
        mAuthorBg = getView(R.id.personal_authorbg);
        mAuthorHead = getView(R.id.personal_authorhead);
        mNotifyMsg = getView(R.id.personal_notify_msg);
        getView(R.id.personal_menu_contactus).setOnClickListener(this);
        getView(R.id.personal_menu_favorite).setOnClickListener(this);
        getView(R.id.personal_menu_feedback).setOnClickListener(this);
        getView(R.id.personal_menu_followme).setOnClickListener(this);
        getView(R.id.personal_menu_msgcenter).setOnClickListener(this);
        getView(R.id.personal_menu_mycollection).setOnClickListener(this);
        getView(R.id.personal_menu_myfollow).setOnClickListener(this);
        getView(R.id.personal_menu_myphoto).setOnClickListener(this);
        getView(R.id.personal_menu_mymap).setOnClickListener(this);
        FloatingActionButton fab = getView(R.id.fab_edit);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                AddPhotoActivity.startActivity(MainFragment4.this.getContext());
            }
        });
    }

    @Override
    public void attachActionBarView(View actionbarView) {
        if(actionbarView == null) return;
        ImageButton settingButton = (ImageButton) actionbarView.findViewById(R.id.personal_setting_button);
        ImageButton shareButton = (ImageButton) actionbarView.findViewById(R.id.personal_share_button);
        settingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SettingActivity.startActivity(MainFragment4.this.getContext());
            }
        });
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "我想要分享", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.personal_menu_contactus:

                break;
            case R.id.personal_menu_favorite:
                MyFavoriteActivity.startActivity(this.getContext());
                break;
            case R.id.personal_menu_feedback:
                FeedbackActivity.startActivity(this.getContext());
                break;
            case R.id.personal_menu_followme:
                FollowdMeActivity.startActivity(this.getContext());
                break;
            case R.id.personal_menu_msgcenter:

                break;
            case R.id.personal_menu_mycollection:
                PersonalCollectionActivity.startActivity(this.getContext());
                break;
            case R.id.personal_menu_myfollow:
                MyFollowedActivity.startActivity(this.getContext());
                break;
            case R.id.personal_menu_myphoto:
                PersonalPhotoActivity.startActivity(this.getContext());
                break;
            case R.id.personal_menu_mymap:

                break;
        }
    }
}
