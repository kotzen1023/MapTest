package com.macauto.maptest.Data;

import android.os.Environment;
import android.util.Log;

import java.io.File;



public class FileOperation {
    private static final String TAG = FileOperation.class.getName();

    public static File RootDirectory = new File("/");

    public static boolean init_camera_folder() {
        boolean ret = true;

        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            //path = Environment.getExternalStorageDirectory();
            RootDirectory = Environment.getExternalStorageDirectory();
        }

        //check folder
        File folder = new File(RootDirectory.getAbsolutePath() + "/DCIM/Camera");

        if(!folder.exists()) {
            Log.i(TAG, "Camera folder not exist");
            ret = folder.mkdirs();
            if (!ret) {
                Log.e(TAG, "init_camera_folder: failed to mkdir " + folder.getAbsolutePath());

            }
        }

        return ret;
    }
}
