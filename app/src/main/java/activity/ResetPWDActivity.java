package activity;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.xykgj.tx.xinyongkaguanjia.R;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import base.BaseActivity;
import bean.ForgetPwdBean;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import evenbusbean.EventBusLoginBean;
import network.NetWorkConfig;
import okhttp3.Call;
import utils.AppUtils;
import utils.MD5Utils;
import utils.SpUtils;
import utils.TimeCount;
import utils.TimeUtils;

/**
 * Created by mayn on 2018/6/19.
 * 忘记密码Activity
 */

public class ResetPWDActivity extends BaseActivity {


    private static final String TAG = ResetPWDActivity.class.getSimpleName();
    @BindView(R.id.tv_resetpwd_login)
    TextView tvResetpwdLogin;
    @BindView(R.id.et_resetpwd_phone)
    EditText etResetpwdPhone;
    @BindView(R.id.et_resetpwd_sms)
    EditText etResetpwdSms;
    @BindView(R.id.btn_resetpwd_sms)
    Button btnResetpwdSms;
    @BindView(R.id.et_resetpwd_pwd)
    EditText etResetpwdPwd;
    @BindView(R.id.btn_resetpwd_andlogin)
    Button btnResetpwdAndlogin;
    @BindView(R.id.ll_resetpwd_back)
    LinearLayout llResetpwdBack;
    private TimeCount timeCount;

    @Override
    public int initLayout() {
        return R.layout.activity_resetpwd;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        timeCount = new TimeCount(60000, 1000, btnResetpwdSms);
    }

    @OnClick({R.id.ll_resetpwd_back, R.id.tv_resetpwd_login, R.id.btn_resetpwd_sms, R.id.btn_resetpwd_andlogin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_resetpwd_back:
                finish();
                break;
            case R.id.tv_resetpwd_login:
                break;
            case R.id.btn_resetpwd_sms:
                //获取忘记密码验证码
                getResetSMS(etResetpwdPhone.getText().toString().trim());
                break;
            case R.id.btn_resetpwd_andlogin:
                commitAndLogin(etResetpwdPhone.getText().toString().trim(),
                        etResetpwdSms.getText().toString().trim(),
                        etResetpwdPwd.getText().toString().trim());
                break;
        }
    }

    /**
     * 获取忘记密码验证码
     *
     * @param phone
     */
    private void getResetSMS(String phone) {
        if (!TextUtils.isEmpty(phone)) {
            //手机号码不为空
            if (AppUtils.isChinaPhoneTrue(phone)) {
                //开始倒计时
                timeCount.start();
                netGetSMS(phone);
            } else {
                AppUtils.showToast(ResetPWDActivity.this, "手机号码格式错误，请核对");
                return;
            }
        } else {
            AppUtils.showToast(ResetPWDActivity.this, "手机号码不能为空");
            return;
        }
    }

    /**
     * 联网获取验证码
     *
     * @param phone
     */
    private void netGetSMS(String phone) {
        //TODO
    }

    /**
     * 提交并登录
     *
     * @param phone
     * @param sms
     * @param pwd
     */
    private void commitAndLogin(String phone, String sms, String pwd) {
        if (!TextUtils.isEmpty(phone) && !TextUtils.isEmpty(sms) && !TextUtils.isEmpty(pwd)) {
            //手机、验证码、密码都不为空
            //提交并登录
            editAndLogin(phone, sms, pwd);
        } else {
            AppUtils.showToast(ResetPWDActivity.this, "手机、验证码、密码不能为空");
            return;
        }
    }

    /**
     * 提交并登录
     *
     * @param phone
     * @param sms
     * @param pwd
     */
    private void editAndLogin(String phone, String sms, String pwd) {
        //时间戳
        String time = TimeUtils.getTime();
        //获取sign
        String md5_sign = MD5Utils.getMD5WithSalt("mobile=" + phone + "&pwd=" + pwd + "&timestamp=" + time + "&vcode=" + "12345", NetWorkConfig.SIGN);
        OkHttpUtils
                .get()
                .url(NetWorkConfig.BaseURL + NetWorkConfig.USER_ForgetPWDURL)
                .addParams("mobile", phone)
                .addParams("pwd", pwd)
                .addParams("vcode", "12345")
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
                                AppUtils.showToast(ResetPWDActivity.this, (String) jsonObject.opt("msg"));
                                parseForgetPwdJson(response);
                            } else {
                                AppUtils.showToast(ResetPWDActivity.this, (String) jsonObject.opt("msg"));
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    private void parseForgetPwdJson(String response) {
        Gson gson = new Gson();
        ForgetPwdBean forgetPwdBean = gson.fromJson(response, ForgetPwdBean.class);
        String token = forgetPwdBean.getResult().getToken();
        //把用户手机号保存到SP中
        SpUtils.saveString(ResetPWDActivity.this,"userphone",etResetpwdPhone.getText().toString().trim());
        //把token保存到sp中
        SpUtils.saveString(ResetPWDActivity.this,"token",token);
        //发送登录成功的消息
        EventBus.getDefault().post(new EventBusLoginBean());
        goToActivity(ResetPWDActivity.this,MainActivity.class);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timeCount.cancel();
    }

}
