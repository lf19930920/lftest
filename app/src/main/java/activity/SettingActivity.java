package activity;

import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.xykgj.tx.xinyongkaguanjia.R;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import base.BaseActivity;
import bean.CheckVersionBean;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import evenbusbean.ExitLoginBean;
import network.NetWorkConfig;
import okhttp3.Call;
import utils.APKVersionCodeUtils;
import utils.AppUtils;
import utils.DataCleanManager;
import utils.MD5Utils;
import utils.SpUtils;
import utils.TimeUtils;

/**
 * Created by mayn on 2018/6/19.
 * 设置Activity
 */

public class SettingActivity extends BaseActivity {
    private static final String TAG = SettingActivity.class.getSimpleName();
    @BindView(R.id.rl_setting_editpwd)
    RelativeLayout rlSettingEditpwd;
    @BindView(R.id.rl_setting_checkupdate)
    RelativeLayout rlSettingCheckupdate;
    @BindView(R.id.tv_setting_cache)
    TextView tvSettingCache;
    @BindView(R.id.rl_setting_delcache)
    RelativeLayout rlSettingDelcache;
    @BindView(R.id.ll_setting_exitlogin)
    LinearLayout llSettingExitlogin;
    @BindView(R.id.ll_setting_back)
    LinearLayout llSettingBack;
    private String s;
    private String token;

    @Override
    public int initLayout() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        //获取token
        token = SpUtils.getString(SettingActivity.this, "token");
        int i = APKVersionCodeUtils.packageCode(SettingActivity.this);
        s = APKVersionCodeUtils.packageName(SettingActivity.this);
        Log.i(TAG, "版本code=" + i);
        Log.i(TAG, "版本name=" + s);
        //获取app缓存
        getAppCache();
    }

    /**
     * 获取app缓存
     */
    private void getAppCache() {
        try {
            String cacheSize = DataCleanManager.getCacheSize(getCacheDir());
            tvSettingCache.setText(cacheSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.ll_setting_back, R.id.rl_setting_editpwd, R.id.rl_setting_checkupdate, R.id.rl_setting_delcache, R.id.ll_setting_exitlogin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_setting_back:
                finish();
                break;
            case R.id.rl_setting_editpwd:
                goToActivity(SettingActivity.this, EditPWDActivity.class);
                break;
            case R.id.rl_setting_checkupdate:
                checkAppVersion();
                break;
            case R.id.rl_setting_delcache:
                DataCleanManager.cleanInternalCache(SettingActivity.this);
                AppUtils.showToast(SettingActivity.this, "本次清理了" + tvSettingCache.getText().toString() + "缓存");
                tvSettingCache.setText("0 KB");
                break;
            case R.id.ll_setting_exitlogin:
                //退出登录
                exitLogin();
                break;
        }
    }

    /**
     * 检查app版本
     */
    private void checkAppVersion() {
        //获取时间戳
        String time = TimeUtils.getTime();
        //获取sign
        String md5_sign = MD5Utils.getMD5WithSalt("timestamp=" + time + "&token=" + token, NetWorkConfig.SIGN);
        OkHttpUtils
                .get()
                .url(NetWorkConfig.BaseURL + NetWorkConfig.OPEN_OpenVersionCheck)
                .addParams("token", token)
                .addParams("timestamp", time)
                .addParams("sign", md5_sign)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.i(TAG, "onError=" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.i(TAG, "onResponse=" + response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            int code = (int) jsonObject.opt("code");
                            if (code == 1000) {
                                parseVersionJson(response);
                            } else {
                                AppUtils.showToast(SettingActivity.this, (String) jsonObject.opt("msg"));
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    /**
     * 解析版本号json
     *
     * @param response
     */
    private void parseVersionJson(String response) {
        Gson gson = new Gson();
        CheckVersionBean checkVersionBean = gson.fromJson(response, CheckVersionBean.class);
        String version = checkVersionBean.getResult().getVersion();
        if (!s.equals(version)) {
            //说明有新版本，进行后续的操作
            //TODO
        } else {
            //说明没有新版本
            AppUtils.showToast(SettingActivity.this, "目前已经是最新的版本");
            return;
        }
    }

    /**
     * 退出登录
     */
    private void exitLogin() {
        //获取退出登录的时间戳
        String time = TimeUtils.getTime();
        //获取拼接的sign
        String md5_sign = MD5Utils.getMD5WithSalt("timestamp=" + time + "&token=" + token, NetWorkConfig.SIGN);
        OkHttpUtils
                .get()
                .url(NetWorkConfig.BaseURL + NetWorkConfig.USER_LoginOutURL)
                .addParams("timestamp", time)
                .addParams("token", token)
                .addParams("sign", md5_sign)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.i(TAG, "onError=" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.i(TAG, "onResponse=" + response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            int code = (int) jsonObject.opt("code");
                            if (code == 1000) {
                                //请求退出登录成功
                                //清除sp中保存的信息
                                SpUtils.clear(SettingActivity.this);
                                EventBus.getDefault().post(new ExitLoginBean());
                                finish();
                            } else {
                                //请求退出失败
                                AppUtils.showToast(SettingActivity.this, (String) jsonObject.opt("msg"));
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

}
