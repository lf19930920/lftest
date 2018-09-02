package utils;

import android.content.Context;

import java.io.File;

/**
 * Created by mayn on 2018/7/19.
 */

public class MyFileUtils {
    public static File getSaveFile(Context context) {
        File file = new File(context.getFilesDir(), "pic.jpg");
        return file;
    }
}
