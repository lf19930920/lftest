package base;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by mayn on 2018/6/11.
 * Activity的基类
 */

public abstract class BaseActivity extends AppCompatActivity {
    //根据需求定义自己需要关闭页面的action
    public static final String RECEIVER_ACTION_FINISH_LOGIN = "receiver_action_finish_login";
    private FinishActivityRecevier mRecevier;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        statusBarConfig();
        setContentView(initLayout());
        initView();
        initData();
        mRecevier = new FinishActivityRecevier();
        registerFinishReciver();
    }

    private void registerFinishReciver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(RECEIVER_ACTION_FINISH_LOGIN);
        registerReceiver(mRecevier, intentFilter);
    }

    private void statusBarConfig() {
        //设置状态栏为全透明
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        }
    }

    /**
     * 返回加载的布局
     *
     * @return
     */
    public abstract int initLayout();

    /**
     * 初始化控件
     */
    protected abstract void initView();


    /**
     * 加载数据的操作
     */
    protected abstract void initData();


    /**
     * 打开activity,无数据传递
     */
    protected void goToActivity(Context context, Class<? extends Activity> clazz) {
        Intent intent = new Intent(context, clazz);
        startActivity(intent);
    }

    private class FinishActivityRecevier extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) { //根据需求添加自己需要关闭页面的action
            if (RECEIVER_ACTION_FINISH_LOGIN.equals(intent.getAction())) {
                BaseActivity.this.finish();
            }
        }
    }

    @Override
    protected void onDestroy() {
        if (mRecevier != null) {
            unregisterReceiver(mRecevier);
        }
        super.onDestroy();
    }
}
