package com.guohe.ltsyandroid.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.facebook.drawee.view.SimpleDraweeView;
import com.guohe.ltsyandroid.MvpPresenter;
import com.guohe.ltsyandroid.R;
import com.guohe.ltsyandroid.manage.config.GlobalConfigManage;
import com.guohe.ltsyandroid.util.FrescoUtils;
import com.guohe.ltsyandroid.util.LogUtil;
import com.guohe.ltsyandroid.util.ToastUtil;
import com.guohe.ltsyandroid.view.base.BaseActivity;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoImpl;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.InvokeParam;
import com.jph.takephoto.model.LubanOptions;
import com.jph.takephoto.model.TContextWrap;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.permission.InvokeListener;
import com.jph.takephoto.permission.PermissionManager;
import com.jph.takephoto.permission.TakePhotoInvocationHandler;

import java.util.List;

import me.gujun.android.taggroup.TagGroup;

/**
 * Created by 水寒 on 2017/7/19.
 * 添加图片
 */

public class AddPhotoActivity extends BaseActivity implements View.OnClickListener,TakePhoto.TakeResultListener,InvokeListener {

    private InvokeParam mInvokeParam;
    private TakePhoto mTakePhoto;
    private TagGroup mTagGroup;
    private Button mAddPhotoButton;
    private ImageButton mAddPhotoEditButton;
    private SimpleDraweeView mAddPhotoImage;
    private Button mAddPhotoTagButton;

    @Override
    public void initPresenter(List<MvpPresenter> presenters) {

    }

    @Override
    public void turnToOtherView() {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_addphoto;
    }

    @Override
    protected void initView() {
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        mTagGroup = getView(R.id.tag_group);
        mAddPhotoButton = getView(R.id.addphoto_button);
        mAddPhotoButton.setOnClickListener(this);
        mAddPhotoImage = getView(R.id.addphoto_image);
        mAddPhotoEditButton = getView(R.id.addphoto_edit);
        mAddPhotoEditButton.setOnClickListener(this);
        mAddPhotoTagButton = getView(R.id.addphoto_tag_button);
        mAddPhotoTagButton.setOnClickListener(this);

        getView(R.id.addphoto_descript).setOnClickListener(this);
        getView(R.id.addphoto_category).setOnClickListener(this);
        getView(R.id.addphoto_divice).setOnClickListener(this);
        getView(R.id.addphoto_address).setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    public static void startActivity(Context context){
        Intent intent = new Intent(context, AddPhotoActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        doBack();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_photo_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                doBack();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void doBack(){
        new MaterialDialog.Builder(this)
                .title("返回提醒")
                .content("您正处于编辑状态返回会丢失数据")
                .positiveText("继续返回")
                .negativeText("我再想想")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        AddPhotoActivity.this.finish();
                    }
                })
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.addphoto_button:
            case R.id.addphoto_edit:
                mTakePhoto.onPickFromDocuments();
                break;
            case R.id.addphoto_tag_button:
                /*new MaterialDialog.Builder(this)
                        .title("添加标签")
                        .content("给你的作品添加标签，更多人可以看到")
                        .inputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL)
                        .input("请输入...", "", new MaterialDialog.InputCallback() {
                            @Override
                            public void onInput(MaterialDialog dialog, CharSequence input) {
                                LogUtil.d("setTag == " + input);
                                mTagGroup.setTag(input);
                                mTagGroup.submitTag();
                            }
                       }).show();*/
                if(mTagGroup.getTags().length >= 5){
                    ToastUtil.showToast("最多添加5个标签");
                    return;
                }
                mTagGroup.submitTag();
                break;
            case R.id.addphoto_descript:
                AddPhotoDescriptActivity.startActivity(this);
                break;
            case R.id.addphoto_category:
                AddPhotoCategoryActivity.startActivity(this);
                break;
            case R.id.addphoto_divice:
                AddPhotoChooseCameraActivity.startActivity(this);
                break;
            case R.id.addphoto_address:
                AddPhotoAddressActivity.startActivity(this);
                break;
        }
    }

    /**
     *  获取TakePhoto实例
     * @return
     */
    public TakePhoto getTakePhoto(){
        if (mTakePhoto == null){
            mTakePhoto = (TakePhoto) TakePhotoInvocationHandler.of(this).bind(new TakePhotoImpl(this,this));
            //mTakePhoto.onEnableCompress(getCompressConfig(), true);
        }
        return mTakePhoto;
    }

    private CompressConfig getCompressConfig(){
        int maxSize = 1024 * 200;  //200kb
        int width = 1000;
        int height = 1000;
        LubanOptions option = new LubanOptions.Builder()
                .setMaxHeight(height)
                .setMaxWidth(width)
                .setMaxSize(maxSize)
                .create();
        CompressConfig  config = CompressConfig.ofLuban(option);
        config.enableReserveRaw(false);
        return config;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.TPermissionType type=PermissionManager.onRequestPermissionsResult(requestCode,permissions,grantResults);
        PermissionManager.handlePermissionsResult(this, type, mInvokeParam, this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        getTakePhoto().onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        getTakePhoto().onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        getTakePhoto().onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void takeSuccess(TResult result) {
        String imgPath = result.getImage().getOriginalPath();
        int width = GlobalConfigManage.getInstance().getScreenWidth();
        int height = width / 3 * 2;
        mAddPhotoButton.setVisibility(View.GONE);
        mAddPhotoEditButton.setVisibility(View.VISIBLE);
        FrescoUtils.loadFile(mAddPhotoImage, imgPath, null, width, height, null);
    }

    @Override
    public void takeFail(TResult result,String msg) {

    }
    @Override
    public void takeCancel() {
        LogUtil.d(getResources().getString(R.string.msg_operation_canceled));
    }

    @Override
    public PermissionManager.TPermissionType invoke(InvokeParam invokeParam) {
        PermissionManager.TPermissionType type=PermissionManager.checkPermission(TContextWrap.of(this),invokeParam.getMethod());
        if(PermissionManager.TPermissionType.WAIT.equals(type)){
            this.mInvokeParam = invokeParam;
        }
        return type;
    }

}
