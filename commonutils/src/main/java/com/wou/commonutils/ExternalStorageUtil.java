package com.wou.commonutils;
/*
 * Copyright (c) 2014, 西安蘑菇科技有限公司 All rights reserved.
 * File Name：EditTextShakeHelper.java
 * Version：V1.0
 * Author：zshu 
 * Date： 创建时间：2015/12/14 10:55
 */


import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * 外部存儲卡工具类
 * 需要添加权限
 * android.permission.WRITE_EXTERNAL_STORAGE
 * android.permission.MOUNT_UNMOUNT_FILESYSTEMS
 *
 * @author lizhangqu
 * @version 1.0
 */
public class ExternalStorageUtil {
    public static final String PACKAGE_NAMES = ".Zhnx";

    /**
     * 是否可写
     *
     * @return 可写性
     */
    public static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /**
     * 是否可读
     *
     * @return 可读性
     */
    public static boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)
                || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    /**
     * 获得程序定义根路径
     *
     * @return 外置内存卡根路径
     */
    public static StringBuilder getExternalStoragePath() {
        if (isExternalStorageWritable()) {
            StringBuilder path = new StringBuilder();
            path.append(Environment.getExternalStorageDirectory().getAbsolutePath())
                    .append(File.separator)
                    .append(PACKAGE_NAMES)
                    .append(File.separator);
            if (!FileUtils.isFolderExist(path.toString())) {
                FileUtils.makeDirs(path.toString());
            }
            return path;
        } else {
            return null;
        }
    }

    /**
     * 获得下载目录路径
     *
     * @return 外置内存卡下载路径
     */
    public static String getExternalDownloadPath() {
        return Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
    }

    /**
     * 向根路径写文件
     *
     * @param fileName 文件名
     * @param content  上下文
     * @return 是否写入成功
     */
    public static boolean write(String fileName, String content) {
        return write("/", fileName, content);
    }

    /**
     * 向根目录写字节
     *
     * @param fileName 文件名
     * @param bytes    文件字节数组
     * @return 是否写入成功
     */
    public static boolean writeBytes(String fileName, byte[] bytes) {
        return writeBytes("/", fileName, bytes);
    }

    /**
     * 向指定目录的文件中写入字符串,路径以/开始/结尾
     *
     * @param path     相对于根路径的路径，路径以/开始，以/结尾
     * @param fileName 文件名
     * @param content  文件内容
     * @return 是否写入成功
     */
    public static boolean write(String path, String fileName, String content) {
        return writeBytes(path, fileName, content.getBytes());
    }

    /**
     * 向指定目录的文件写入字节数组,路径以/开始/结尾
     *
     * @param path     相对于根路径的路径，路径以/开始，以/结尾
     * @param fileName 文件名
     * @param bytes    字节数组
     * @return
     */
    public static boolean writeBytes(String path, String fileName, byte bytes[]) {
        boolean flag = false;
        if (!path.equals("/")) {
            File dir = new File(getExternalStoragePath() + path);
            if (!dir.exists()) {
                if (!(dir.mkdir() || dir.isDirectory())) {
                    // 文件目录创建失败或者不是一个目录
                    return false;
                }
            }
        }
        File file = new File(getExternalStoragePath() + path + fileName);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file, false);
            fos.write(bytes);
            flag = true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return flag;
    }

    /**
     * 从根路径读字节
     *
     * @param fileName 文件名
     * @return 字节数组
     */
    public static byte[] readBytes(String fileName) {
        return readBytes("/", fileName);
    }

    /**
     * 从指定目录读字节,路径以/开始/结尾
     *
     * @param path     相对于根路径的路径，路径以/开始，以/结尾
     * @param fileName 文件名
     * @return 字节数组
     */
    public static byte[] readBytes(String path, String fileName) {
        File file = new File(getExternalStoragePath() + path + fileName);
        if (!file.isFile()) {
            return null;
        } else {
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(file);
                int length = fis.available();
                byte[] buffer = new byte[length];
                fis.read(buffer);
                return buffer;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return null;
        }

    }

    /**
     * 从根目录读文本
     *
     * @param fileName 文件名
     * @return 字符串
     */
    public static String read(String fileName) {
        return read("/", fileName);
    }

    /**
     * 从指定目录读文本,路径以/开始/结尾
     *
     * @param path     相对于根路径的路径，路径以/开始，以/结尾
     * @param fileName 文件名
     * @return 字符串
     */
    public static String read(String path, String fileName) {
        try {
            byte[] readBytes = readBytes(path, fileName);
            if (readBytes == null) {
                return null;
            }
            return new String(readBytes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 从根目录删除
     *
     * @param fileName 文件名
     * @return 是否删除成功
     */
    public static boolean delete(String fileName) {
        return delete("/", fileName);
    }

    /**
     * 从指定目录删除,路径以/开始/结尾
     *
     * @param path     相对于根路径的路径，路径以/开始，以/结尾
     * @param fileName 文件名
     * @return 是否删除成功
     */
    public static boolean delete(String path, String fileName) {
        File file = new File(getExternalStoragePath() + path + fileName);
        if (file.exists())
            return file.delete();
        else
            return true;
    }


    /**
     * 检测Sdcard是否存在
     *
     * @return
     */
    public static boolean isExistSdcard() {
        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }
}

