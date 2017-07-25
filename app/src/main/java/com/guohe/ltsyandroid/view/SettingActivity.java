package com.guohe.ltsyandroid.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.guohe.ltsyandroid.MvpPresenter;
import com.guohe.ltsyandroid.R;
import com.guohe.ltsyandroid.manage.config.GlobalConfigManage;
import com.guohe.ltsyandroid.util.SnackbarUtils;
import com.guohe.ltsyandroid.view.base.BaseActivity;

import org.polaric.colorful.ColorPickerDialog;
import org.polaric.colorful.Colorful;

import java.util.List;

/**
 * Created by 水寒 on 2017/7/19.
 */

public class SettingActivity extends BaseActivity implements View.OnClickListener{

    private TextView mThemeDark;
    private View mThemePrimary;
    private View mThemeSecondry;

    @Override
    public void initPresenter(List<MvpPresenter> presenters) {

    }

    @Override
    public void turnToOtherView() {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_setting;
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
        getView(R.id.setting_theme_dark).setOnClickListener(this);
        getView(R.id.setting_theme_primary).setOnClickListener(this);
        getView(R.id.setting_theme_second).setOnClickListener(this);
        getView(R.id.setting_catch).setOnClickListener(this);
        getView(R.id.setting_check_update).setOnClickListener(this);
        getView(R.id.setting_evaluate).setOnClickListener(this);
        getView(R.id.setting_theme_recover).setOnClickListener(this);
        mThemeDark = getView(R.id.setting_theme_dark_value);
        mThemePrimary = getView(R.id.setting_theme_primary_value);
        mThemeSecondry = getView(R.id.setting_theme_second_value);
    }

    @Override
   protected void initData() {
        boolean randomTheme = GlobalConfigManage.getInstance().getRandomTheme();
        mThemeDark.setText(randomTheme ? "开" : "关");
        mThemePrimary.setBackgroundResource(Colorful.getThemeDelegate().getPrimaryColor().getColorRes());
        mThemeSecondry.setBackgroundResource(Colorful.getThemeDelegate().getAccentColor().getColorRes());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                SettingActivity.this.finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public static void startActivity(Context context){
        Intent intent = new Intent(context, SettingActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        ColorPickerDialog dialog;
        switch (view.getId()){
            case R.id.setting_theme_dark:
                new MaterialDialog.Builder(this)
                        .title("随机主题开关")
                        .items(R.array.theme_category)
                        .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                            @Override
                            public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                if("随机".equals(text)){
                                    GlobalConfigManage.getInstance().setRandomTheme(true);
                                    mThemeDark.setText("开");
                                }else{
                                    GlobalConfigManage.getInstance().setRandomTheme(false);
                                    mThemeDark.setText("关");
                                }

                                return true;
                            }
                        })
                        .positiveText("更改")
                        .show();
                break;
            case R.id.setting_theme_primary:
                dialog = new ColorPickerDialog(SettingActivity.this);
                dialog.setOnColorSelectedListener(new ColorPickerDialog.OnColorSelectedListener() {
                    @Override
                    public void onColorSelected(Colorful.ThemeColor color) {
                        Colorful.config(SettingActivity.this)
                                .primaryColor(color)
                                .accentColor(Colorful.getThemeDelegate().getAccentColor())
                                .translucent(false)
                                .dark(Colorful.getThemeDelegate().isDark())
                                .apply();
                        mThemePrimary.setBackgroundResource(color.getColorRes());
                    }
                });
                dialog.show();
                break;
            case R.id.setting_theme_second:
                dialog = new ColorPickerDialog(SettingActivity.this);
                dialog.setOnColorSelectedListener(new ColorPickerDialog.OnColorSelectedListener() {
                    @Override
                    public void onColorSelected(Colorful.ThemeColor color) {
                        Colorful.config(SettingActivity.this)
                                .primaryColor(Colorful.getThemeDelegate().getAccentColor())
                                .accentColor(color)
                                .translucent(false)
                                .dark(Colorful.getThemeDelegate().isDark())
                                .apply();
                        mThemeSecondry.setBackgroundResource(color.getColorRes());
                    }
                });
                dialog.show();
                break;
            case R.id.setting_theme_recover:
                Colorful.config(SettingActivity.this)
                        .primaryColor(Colorful.ThemeColor.DEFAULT_PRIMARY)
                        .accentColor(Colorful.ThemeColor.DEFAULT_SECODARY)
                        .translucent(false)
                        .dark(false)
                        .apply();
                SnackbarUtils.with(view)
                        .setMessage("恢复默成功，退出后重进生效")
                        .show();
                break;
            case R.id.setting_catch:
                break;
            case R.id.setting_evaluate:

                break;
            case R.id.setting_check_update:

                break;
        }
    }
}
