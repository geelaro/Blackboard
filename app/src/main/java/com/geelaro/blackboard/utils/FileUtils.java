package com.geelaro.blackboard.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by geelaro on 2018/1/30.
 * 文件工具类
 */

public class FileUtils {
    private static final String TAG = "FileUtils";

    /**
     * 创建目录
     *
     * @param context
     * @param uniqueName 文件夹名
     * @return
     */
    public static File getCacheDir(Context context, String uniqueName) {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) ||
                !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        File fileDir = new File(cachePath + File.separator + uniqueName);
        if (!fileDir.exists()) {
            boolean isCreated = fileDir.mkdirs();
            SunLog.i(TAG, cachePath + " is created. " + isCreated);
        }

        return fileDir;
    }

    public static void savaBitmapToDisk(File dir, String fileName, Bitmap bitmap) {
        if (bitmap == null) {
            return;
        }
        File file = new File(dir, fileName);

        try {
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);

            //关闭
            fos.flush();
            fos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
