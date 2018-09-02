package activity;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.xykgj.tx.xinyongkaguanjia.R;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import base.BaseActivity;
import bean.LoginBean;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import evenbusbean.EventBusLoginBean;
import network.NetWorkConfig;
import okhttp3.Call;
import utils.AppUtils;
import utils.BroadcastUtils;
import utils.MD5Utils;
import utils.SpUtils;
import utils.TimeCount;
import utils.TimeUtils;

/**
 * Created by mayn on 2018/6/19.
 * 注册Activity
 */

public class RegisterActivity extends BaseActivity {


    private static final String TAG = RegisterActivity.class.getSimpleName();

//    @BindView(R.id.tv_register_login)
//    TextView tvRegisterLogin;
    @BindView(R.id.et_register_phone)
    EditText etRegisterPhone;
    @BindView(R.id.et_register_sms)
    EditText etRegisterSms;
    @BindView(R.id.btn_register_sms)
    Button btnRegisterSms;
    @BindView(R.id.et_register_pwd)
    EditText etRegisterPwd;
    @BindView(R.id.btn_register_andlogin)
    Button btnRegisterAndlogin;
    @BindView(R.id.ll_register_back)
    LinearLayout llRegisterBack;
    private TimeCount timeCount;

    @Override
    public int initLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {

        timeCount = new TimeCount(60000, 1000, btnRegisterSms);
    }

    @OnClick({R.id.ll_register_back,R.id.btn_register_sms, R.id.btn_register_andlogin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_register_back:
                finish();
                break;
            case R.id.btn_register_sms:
                //获取短信验证码
                getRegisterSMS(etRegisterPhone.getText().toString().trim());
                break;
            case R.id.btn_register_andlogin:
                EventBus.getDefault().post(new EventBusLoginBean());
                //注册并登录
//                registerAndLogin(etRegisterPhone.getText().toString().trim(),
//                        etRegisterSms.getText().toString().trim(),
//                        etRegisterPwd.getText().toString().trim());
                break;
        }
    }

    /**
     * 获取短信验证码
     *
     * @param phone
     */
    private void getRegisterSMS(String phone) {
        if (!TextUtils.isEmpty(phone)) {
            //电话号码不为空，判断格式是否正确
            if (AppUtils.isChinaPhoneTrue(phone)) {
                //获取验证码按钮开始倒计时
                timeCount.start();
                //联网获取验证码
                netGetSMS(phone);
            } else {
                AppUtils.showToast(RegisterActivity.this, "请检查您的手机号码格式");
                return;
            }
        } else {
            AppUtils.showToast(RegisterActivity.this, "手机号码不能为空");
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
     * 注册并登录
     *
     * @param phone
     * @param sms
     * @param pwd
     */
    private void registerAndLogin(String phone, String sms, String pwd) {
        if (!TextUtils.isEmpty(phone) && !TextUtils.isEmpty(sms) && !TextUtils.isEmpty(pwd)) {
            //手机、验证码、密码都不为空
            if (pwd.length() >= 6 && pwd.length() <= 16) {
                //密码长度满足要求
                if (!AppUtils.compileExChar(pwd)) {
                    //密码中没有包含特殊符号
                    //可能会先进行验证SMS短信验证码是否正确
                    netRegisterAndLogin(phone, sms, pwd);
                } else {
                    AppUtils.showToast(RegisterActivity.this, "密码中包含特殊字符，请检查");
                    return;
                }
            } else {
                AppUtils.showToast(RegisterActivity.this, "您输入的密码长度不符合规则");
                return;
            }
        } else {
            AppUtils.showToast(RegisterActivity.this, "手机号、验证码、密码任意选项均不能为空，请您核对");
            return;
        }

    }

    /**
     * 联网登录并注册
     *
     * @param phone
     * @param sms
     * @param pwd
     */
    private void netRegisterAndLogin(String phone, String sms, String pwd) {
        String time = TimeUtils.getTime();
        Log.i(TAG, "time=" + time);
        String md5_sign = MD5Utils.getMD5WithSalt("mobile=" + phone + "&pwd=" + pwd + "&timestamp=" + time + "&vcode=" + "12345", NetWorkConfig.SIGN);
        Log.i(TAG, "md5_sign=" + md5_sign);
        OkHttpUtils
                .get()
                .url(NetWorkConfig.BaseURL + NetWorkConfig.USER_RegisterURL)
                .addParams("mobile", phone)
                .addParams("pwd", pwd)
                .addParams("vcode", "12345")
                .addParams("sign", md5_sign)
                .addParams("timestamp", time)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.i(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.i(TAG, "onResponse: " + response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            int code = (int) jsonObject.opt("code");
                            if (code == 1000) {
                               // BroadcastUtils.sendFinishActivityBroadcast(RegisterActivity.this);
                                parseRegAndLoginJson(response);
                            } else {
                                AppUtils.showToast(RegisterActivity.this, (String) jsonObject.opt("msg"));
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    /**
     * 解析json
     *
     * @param response
     */
    private void parseRegAndLoginJson(String response) {
        Gson gson = new Gson();
        LoginBean loginBean = gson.fromJson(response, LoginBean.class);
        //获取到token
        String token = loginBean.getResult().getToken();
        //把token保存到SP中
        SpUtils.saveString(RegisterActivity.this, "token", token);
        //把用户手机号保存到SP中
        SpUtils.saveString(RegisterActivity.this,"userphone",etRegisterPhone.getText().toString().trim());
        EventBus.getDefault().post(new EventBusLoginBean());
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timeCount.cancel();
    }

}
