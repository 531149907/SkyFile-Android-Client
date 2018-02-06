package work.hin.skyfileclient.util;

import android.util.Log;

/**
 * Created by zhouzhixuan on 2017/8/20.
 */

public class LogUtil {
    private LogUtil() {
    }

    public static void show(String tag, String value) {
        Log.wtf(tag, value);
    }

    public static void show(String value) {
        Log.wtf("Undefined", value);
    }

    public static void show(String tag, int value) {
        Log.wtf(tag, value + "");
    }

    public static void show(int value) {
        Log.wtf("Undefined", value + "");
    }

    public static void show(String tag, float value) {
        Log.wtf(tag, value + "");
    }

    public static void show(float value) {
        Log.wtf("Undefined", value + "");
    }


}
