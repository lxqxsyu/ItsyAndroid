package com.wou.commonutils;

import android.content.Context;
import android.provider.Settings;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.UUID;

public class Installation {
    private static String sID = null;
    private static final String INSTALLATION = "INSTALLATION-"+ UUID.nameUUIDFromBytes("androidkit".getBytes());
    /**
     * 返回该设备在此程序上的唯一标识符。
     * @param context
     *            Context对象。
     * @return 表示该设备在此程序上的唯一标识符。
     */
    public synchronized static String getID(Context context) {
        if (sID == null) {
            File installation = new File(context.getFilesDir(), INSTALLATION);
            try {
                if (!installation.exists()) {
                    writeInstallationFile(context, installation);
                }
                sID = readInstallationFile(installation);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sID;
    }
    /**
     * 将表示此设备在该程序上的唯一标识符写入程序文件系统中。
     * @param installation
     *            保存唯一标识符的File对象。
     * @return 唯一标识符。
     * @throws IOException
     *             IO异常。
     */
    private static String readInstallationFile(File installation)
            throws IOException {
        RandomAccessFile accessFile = new RandomAccessFile(installation, "r");
        byte[] bs = new byte[(int) accessFile.length()];
        accessFile.readFully(bs);
        accessFile.close();
        return new String(bs);
    }
    /**
     * 读出保存在程序文件系统中的表示该设备在此程序上的唯一标识符。
     * @param context
     *            Context对象。
     * @param installation
     *            保存唯一标识符的File对象。
     * @throws IOException
     *             IO异常。
     */
    private static void writeInstallationFile(Context context, File installation)
            throws IOException {
        FileOutputStream out = new FileOutputStream(installation);
        String uuid = UUID.nameUUIDFromBytes(
                Settings.Secure.getString(context.getContentResolver(),
                        Settings.Secure.ANDROID_ID).getBytes()).toString();
        out.write(uuid.getBytes());
        out.close();
    }
}