package utils;

import android.content.Context;
import android.content.Intent;

import base.BaseActivity;

/**
 * Created by mayn on 2018/8/1.
 * 广播工具类
 */

public class BroadcastUtils {
    /**
     * 发送finish页面的广播 * action可以自己根据需要添加 * @param context
     */
    public static void sendFinishActivityBroadcast(Context context) {
        Intent intent = new Intent(BaseActivity.RECEIVER_ACTION_FINISH_LOGIN);
        context.sendBroadcast(intent);
    }
}
