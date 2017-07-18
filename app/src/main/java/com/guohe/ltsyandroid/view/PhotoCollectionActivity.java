package com.guohe.ltsyandroid.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.guohe.ltsyandroid.MvpPresenter;
import com.guohe.ltsyandroid.R;
import com.guohe.ltsyandroid.view.base.BaseActivity;
import com.guohe.ltsyandroid.view.base.BaseFragment;

import java.util.List;

import static com.wou.commonutils.ColorGenerator.colorBurn;

/**
 * Created by shuihan on 2017/7/18.
 */

public class PhotoCollectionActivity extends BaseActivity {

    private ViewPager mViewPager;
    public Toolbar mToolbar;
    private SectionsPagerAdapter mSectionsPagerAdapter;

    @Override
    public void initPresenter(List<MvpPresenter> presenters) {

    }

    @Override
    public void turnToOtherView() {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_photocollection;
    }

    @Override
    protected void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar = toolbar;
        setSupportActionBar(toolbar);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container_viewpager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_comment);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    @Override
    protected void initData() {

    }

    public static void startActivity(Context context){
        Intent intent = new Intent(context, PhotoCollectionActivity.class);
        context.startActivity(intent);
    }


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PhotoCollectionFragment extends BaseFragment {

        private static final String ARG_SECTION_NUMBER = "section_number";

        public static PhotoCollectionFragment newInstance(int sectionNumber) {
            PhotoCollectionFragment fragment = new PhotoCollectionFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public void initPresenter(List<MvpPresenter> presenters) {

        }

        @Override
        public void turnToOtherView() {

        }

        @Override
        protected int getContentView() {
            return R.layout.fragment_photocollection;
        }

        @Override
        protected void initData() {

        }

        @Override
        protected void initView(View view) {
            ImageView imageView = (ImageView) getView(R.id.showimage_image);
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.test_image1);
            imageView.setImageBitmap(bitmap);

            // Palette的部分
            Palette.generateAsync(bitmap, new Palette.PaletteAsyncListener() {
                @Override
                public void onGenerated(Palette palette) {
                    Palette.Swatch vibrant = palette.getVibrantSwatch();
                    //mPagerSlidingTabStrip.setTextColor(vibrant.getTitleTextColor());
                    PhotoCollectionActivity activity  = (PhotoCollectionActivity) PhotoCollectionFragment.this.getActivity();
                    if (activity != null && android.os.Build.VERSION.SDK_INT >= 21) {
                        Window window = activity.getWindow();
                        int burnColor = colorBurn(vibrant.getRgb());
                        window.setStatusBarColor(burnColor);
                        window.setNavigationBarColor(burnColor);
                        activity.mToolbar.setBackgroundColor(burnColor);
                    }
                }
            });
        }
    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return PhotoCollectionFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
            }
            return null;
        }
    }
}
