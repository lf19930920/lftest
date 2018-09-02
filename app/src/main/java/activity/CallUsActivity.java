package activity;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.xykgj.tx.xinyongkaguanjia.R;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import base.BaseActivity;
import bean.CallUsBean;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import network.NetWorkConfig;
import okhttp3.Call;
import utils.AppUtils;
import utils.MD5Utils;
import utils.SpUtils;
import utils.TimeUtils;

/**
 * Created by mayn on 2018/6/19.
 * 联系我们Activity
 */

public class CallUsActivity extends BaseActivity {
    private static final String TAG = CallUsActivity.class.getSimpleName();
    @BindView(R.id.iv_callus_bg)
    ImageView ivCallusBg;
    @BindView(R.id.tv_callus_selfdesc)
    TextView tvCallusSelfdesc;
    @BindView(R.id.tv_callus_weburl)
    TextView tvCallusWeburl;
    @BindView(R.id.tv_callus_hotline)
    TextView tvCallusHotline;
    @BindView(R.id.tv_callus_wechat)
    TextView tvCallusWechat;
    @BindView(R.id.tv_callus_qq)
    TextView tvCallusQq;
    @BindView(R.id.ll_callus_back)
    LinearLayout llCallusBack;

    @Override
    public int initLayout() {
        return R.layout.activity_callus;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        String token = SpUtils.getString(CallUsActivity.this, "token");
        //获取联系我们相关信息
        getCallUsInfo(token);
    }

    /**
     * 获取联系我们相关信息
     *
     * @param token
     */
    private void getCallUsInfo(String token) {
        //获取时间戳
        String time = TimeUtils.getTime();
        //获取sign
        String md5_sign = MD5Utils.getMD5WithSalt("timestamp=" + time + "&token=" + token, NetWorkConfig.SIGN);
        OkHttpUtils
                .get()
                .url(NetWorkConfig.BaseURL + NetWorkConfig.OPEN_OpenBankAboutUs)
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
                                parseCallUsJson(response);
                            } else {
                                AppUtils.showToast(CallUsActivity.this, "关于App更多的信息获取失败");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    /**
     * 解析联系我们的json
     *
     * @param response
     */
    private void parseCallUsJson(String response) {
        Gson gson = new Gson();
        CallUsBean callUsBean = gson.fromJson(response, CallUsBean.class);
        String icon = callUsBean.getResult().getIcon();
        String slogan = callUsBean.getResult().getSlogan();
        tvCallusSelfdesc.setText(slogan);
        String website = callUsBean.getResult().getWebsite();
        tvCallusWeburl.setText(website);
        String hotline = callUsBean.getResult().getHotline();
        tvCallusHotline.setText(hotline);
        String wechat = callUsBean.getResult().getWechat();
        tvCallusWechat.setText(wechat);
        String qq = callUsBean.getResult().getQq();
        tvCallusQq.setText(qq);
    }


    @OnClick(R.id.ll_callus_back)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_callus_back:
                finish();
                break;
        }
    }

}
