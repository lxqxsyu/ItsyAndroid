package com.guohe.ltsyandroid.view;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import com.guohe.ltsyandroid.MvpPresenter;
import com.guohe.ltsyandroid.R;
import com.guohe.ltsyandroid.view.base.BaseActivity;
import com.wou.commonutils.StringUtils;

import java.util.List;

import cafe.adriel.androidaudiorecorder.AndroidAudioRecorder;
import cafe.adriel.androidaudiorecorder.model.AudioChannel;
import cafe.adriel.androidaudiorecorder.model.AudioSampleRate;
import cafe.adriel.androidaudiorecorder.model.AudioSource;

import static com.guohe.ltsyandroid.util.PermissionUtil.requestPermission;

/**
 * Created by shuihan on 2017/7/25.
 * 添加秘密
 */

public class AddSecretActivity extends BaseActivity implements View.OnClickListener{

    private static final int REQUEST_RECORD_AUDIO = 0;
    private static final String AUDIO_FILE_PATH =
            Environment.getExternalStorageDirectory().getPath() + "/recorded_audio.wav";

    private AutoCompleteTextView mSecretQuestion;
    private EditText mSecretKeyAnswer;

    @Override
    public void initPresenter(List<MvpPresenter> presenters) {

    }

    @Override
    protected boolean canSlidr() {
        return true;
    }

    @Override
    public void turnToOtherView() {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_add_secret;
    }

    @Override
    protected void initView() {
        requestPermission(this, Manifest.permission.RECORD_AUDIO);
        requestPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        mSecretQuestion = getView(R.id.secret_question);
        mSecretKeyAnswer = getView(R.id.secret_answer);
        getView(R.id.add_secret_voice_button).setOnClickListener(this);
        getView(R.id.secret_save_button).setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                AddSecretActivity.this.finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public static void startActivity(Context context){
        Intent intent = new Intent(context, AddSecretActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.add_secret_voice_button:
                recordAudio();
                break;
            case R.id.secret_save_button:
                if(StringUtils.isEmpty(mSecretQuestion.getText())){
                    mSecretQuestion.setError("设置一个问题吧(比如：我想体现什么？)");
                }else {
                    mSecretKeyAnswer.setError("开启关键词不能超过6位");
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_RECORD_AUDIO) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Audio recorded successfully!", Toast.LENGTH_SHORT).show();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Audio was not recorded", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void recordAudio() {
        AndroidAudioRecorder.with(this)
                // Required
                .setFilePath(AUDIO_FILE_PATH)
                .setColor(ContextCompat.getColor(this, R.color.colorSecondary))
                .setRequestCode(REQUEST_RECORD_AUDIO)

                // Optional
                .setSource(AudioSource.MIC)
                .setChannel(AudioChannel.STEREO)
                .setSampleRate(AudioSampleRate.HZ_48000)
                .setAutoStart(false)
                .setKeepDisplayOn(true)

                // Start recording
                .record();
    }
}
