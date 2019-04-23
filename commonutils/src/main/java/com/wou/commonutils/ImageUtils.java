package com.wou.commonutils;/*
 * Copyright (c) 2014, 西安蘑菇科技有限公司 All rights reserved.
 * File Name：ImageUtils.java
 * Version：V1.0
 * Author：zshu 
 * Date： 创建时间：2015/11/27 14:55
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ImageUtils {

    private ImageUtils() {
        throw new AssertionError();
    }

    /**
     * convert Bitmap to byte array
     *
     * @param b
     * @return
     */
    public static byte[] bitmapToByte(Bitmap b) {
        if (b == null) {
            return null;
        }

        ByteArrayOutputStream o = new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.PNG, 100, o);
        return o.toByteArray();
    }

    /**
     * convert byte array to Bitmap
     *
     * @param b
     * @return
     */
    public static Bitmap byteToBitmap(byte[] b) {
        return (b == null || b.length == 0) ? null : BitmapFactory.decodeByteArray(b, 0, b.length);
    }

    /**
     * convert Drawable to Bitmap
     *
     * @param d
     * @return
     */
    public static Bitmap drawableToBitmap(Drawable d) {
        return d == null ? null : ((BitmapDrawable) d).getBitmap();
    }

    /**
     * convert Bitmap to Drawable
     *
     * @param b
     * @return
     */
    public static Drawable bitmapToDrawable(Bitmap b) {
        return b == null ? null : new BitmapDrawable(b);
    }

    /**
     * convert Drawable to byte array
     *
     * @param d
     * @return
     */
    public static byte[] drawableToByte(Drawable d) {
        return bitmapToByte(drawableToBitmap(d));
    }

    /**
     * convert byte array to Drawable
     *
     * @param b
     * @return
     */
    public static Drawable byteToDrawable(byte[] b) {
        return bitmapToDrawable(byteToBitmap(b));
    }

    /**
     * scale image
     *
     * @param org
     * @param newWidth
     * @param newHeight
     * @return
     */
    public static Bitmap scaleImageTo(Bitmap org, int newWidth, int newHeight) {
        return scaleImage(org, (float) newWidth / org.getWidth(), (float) newHeight / org.getHeight());
    }

    /**
     * scale image
     *
     * @param org
     * @param scaleWidth  sacle of width
     * @param scaleHeight scale of height
     * @return
     */
    public static Bitmap scaleImage(Bitmap org, float scaleWidth, float scaleHeight) {
        if (org == null) {
            return null;
        }

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        return Bitmap.createBitmap(org, 0, 0, org.getWidth(), org.getHeight(), matrix, true);
    }

    /**
     * close inputStream
     *
     * @param s
     */
    private static void closeInputStream(InputStream s) {
        if (s == null) {
            return;
        }

        try {
            s.close();
        } catch (IOException e) {
            throw new RuntimeException("IOException occurred. ", e);
        }
    }


    public static Bitmap decodeUriAsBitmap(Context context, Uri uri) {
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream(context.getContentResolver().openInputStream(uri));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return bitmap;
    }

    /**
     * 压缩图片
     *
     * @param inPath  图片原始地址
     * @param outPath 图片压缩后保存的地址
     * @return
     */
    public static String compressImage(String inPath, String dir, String outPath) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        // 获取这个图片的宽和高
        Bitmap bitmap = BitmapFactory.decodeFile(inPath, options); //此时返回bm为空
        options.inJustDecodeBounds = false;
        //计算缩放比
        int be = (int) (options.outHeight / (float) 600);
        if (be <= 0)
            be = 1;
        options.inSampleSize = be;
        //重新读入图片，注意这次要把options.inJustDecodeBounds 设为 false哦
        bitmap = BitmapFactory.decodeFile(inPath, options);
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
//        System.out.println(w + "   " + h);
        File file = new File(dir, outPath);
        try {
            file.getParentFile().mkdirs();
            FileOutputStream out = new FileOutputStream(file);
            if (bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)) {
                out.flush();
                out.close();
            }
//            System.out.println(" outPath  " + dir + outPath);
            return dir + outPath;
        } catch (FileNotFoundException e) {
            e.printStackTrace();

            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static String compressImage1(String inPath, String dir, String outPath) {

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(inPath, options); //此时返回bm为空
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int optionsInt = 100;
        while (baos.toByteArray().length / 1024 > 200) { // 循环判断如果压缩后图片是否大于200kb,大于继续压缩
            baos.reset();// 重置baos即清空baos
            bitmap.compress(Bitmap.CompressFormat.JPEG, optionsInt, baos);// 这里压缩options%，把压缩后的数据存放到baos中
            optionsInt -= 10;// 每次都减少10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
        bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
        try {
            File file = new File(dir, outPath);
            file.getParentFile().mkdirs();
            FileOutputStream out = new FileOutputStream(file);
            if (bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)) {
                out.flush();
                out.close();
            }
            return dir + outPath;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static String saveBitmap(Bitmap bitmap, String directory) {
        File f = new File(directory, System.currentTimeMillis() + ".jpg");
        if (f.exists()) {
            f.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(f);
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.flush();
            out.close();
            return f.getAbsolutePath();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
