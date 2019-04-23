package com.wou.commonutils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import java.io.File;

public class ShareUtil {

//    public static void sharePicture(Activity activity, String appname, String url) {
//
//        String[] urlSplits = url.split("\\.");
//
//        File cacheFile = ImageLoader.getInstance().getDiskCache().get(url);
//
//        //如果不存在，则使用缩略图进行分享
//        if (!cacheFile.exists()) {
//            String picUrl = url;
//            picUrl = picUrl.replace("mw600", "small").replace("mw1200", "small").replace
//                    ("large", "small");
//            cacheFile = ImageLoader.getInstance().getDiskCache().get(picUrl);
//        }
//
//        File newFile = new File(CacheUtil.getSharePicName
//                (cacheFile, urlSplits));
//
//        if (FileUtil.copyTo(cacheFile, newFile)) {
//            ShareUtil.sharePicture(activity,appname, newFile.getAbsolutePath(), "分享自wou " + url);
//        } else {
//            Toast.makeText(activity, ConstantString.LOAD_SHARE, Toast.LENGTH_SHORT).show();
//        }
//    }


    public static void shareText(Activity activity, String appname, String shareText) {

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,
                shareText);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(Intent.createChooser(intent, appname));
    }

    public static void sharePicture(Activity activity, String appname, String imgPath, String shareText) {

        Intent intent = new Intent(Intent.ACTION_SEND);
        File f = new File(imgPath);
        if (f != null && f.exists() && f.isFile()) {
            intent.setType("image/*");
            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(f));
        } else {
            Toast.makeText(activity, "分享图片不存在哦", Toast.LENGTH_SHORT).show();
            return;
        }

        //GIF图片指明出处url，其他图片指向项目地址
        if (imgPath.endsWith(".gif")) {
            intent.putExtra(Intent.EXTRA_TEXT, shareText);
        }

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(Intent.createChooser(intent, appname));
    }


}
