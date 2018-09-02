package activity;

import android.content.Intent;
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
import bean.LoginBean;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import evenbusbean.EventBusLoginBean;
import network.NetWorkConfig;
import okhttp3.Call;
import utils.AppUtils;
import utils.MD5Utils;
import utils.SpUtils;
import utils.TimeUtils;

/**
 * Created by mayn on 2018/6/11.
 * 登录Activity
 */

public class LoginActivity extends BaseActivity {


    private static final String TAG = LoginActivity.class.getSimpleName();

    @BindView(R.id.tv_login_register)
    TextView tvLoginRegister;
    @BindView(R.id.et_login_phone)
    EditText etLoginPhone;
    @BindView(R.id.et_login_pwd)
    EditText etLoginPwd;
    @BindView(R.id.btn_login_login)
    Button btnLoginLogin;
    @BindView(R.id.tv_login_forgetpwd)
    TextView tvLoginForgetpwd;
    @BindView(R.id.ll_login_back)
    LinearLayout llLoginBack;

    @Override
    public int initLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();

    }

    @OnClick({R.id.ll_login_back, R.id.tv_login_register, R.id.btn_login_login, R.id.tv_login_forgetpwd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_login_back:
                finish();
                break;
            case R.id.tv_login_register:
                //跳转注册界面
                goToActivity(LoginActivity.this, RegisterActivity.class);
                break;
            case R.id.btn_login_login:
                goLogin(etLoginPhone.getText().toString().trim(), etLoginPwd.getText().toString()
                        .trim());
                break;
            case R.id.tv_login_forgetpwd:
                //跳转忘记密码界面
                goToActivity(LoginActivity.this, ResetPWDActivity.class);
                break;
        }
    }

    /**
     * 登录
     *
     * @param phone
     * @param pwd
     */
    public void goLogin(String phone, String pwd) {
        if (!TextUtils.isEmpty(phone) && !TextUtils.isEmpty(pwd)) {
            //手机号和密码都不为空，判断手机号格式是否正确
            if (AppUtils.isChinaPhoneTrue(phone)) {
                //手机号码格式正确,联网登陆
                netLogin(phone, pwd);
            } else {
                //手机号码不正确
                AppUtils.showToast(LoginActivity.this, "请检查您的手机号码格式是否正确");
                return;
            }
        } else {
            //手机号码或者密码为空
            AppUtils.showToast(LoginActivity.this, "手机号码或密码不能为空");
            return;
        }
    }

    /**
     * 联网进行登录操作
     *
     * @param phone
     * @param pwd
     */
    public void netLogin(String phone, String pwd) {
        //获取当前时间戳
        String time = TimeUtils.getTime();
        //获取sign
        String md5_sign = MD5Utils.getMD5WithSalt("mobile=" + phone + "&pwd=" + pwd + "&timestamp=" + time, NetWorkConfig.SIGN);
        OkHttpUtils
                .get()
                .url(NetWorkConfig.BaseURL + NetWorkConfig.USER_LoginURL)
                .addParams("mobile", phone)
                .addParams("pwd", pwd)
                .addParams("timestamp", time)
                .addParams("sign", md5_sign)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.i(TAG, "onError=" + e.getMessage());
                        AppUtils.showToast(LoginActivity.this,"请检查网络连接");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.i(TAG, "onResponse=" + response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            int code = (int) jsonObject.opt("code");
                            if (code == 1000) {
                                parseLoginJson(response);
                            } else {
                                AppUtils.showToast(LoginActivity.this, (String) jsonObject.opt("msg"));
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
    }

    /**
     * 解析登录成功json
     *
     * @param response
     */
    private void parseLoginJson(String response) {
        Gson gson = new Gson();
        LoginBean loginBean = gson.fromJson(response, LoginBean.class);
        //获得token
        String token = loginBean.getResult().getToken();
        //把token保存到SP中
        SpUtils.saveString(LoginActivity.this, "token", token);
        //把用户手机号保存到SP中
        SpUtils.saveString(LoginActivity.this,"userphone",etLoginPhone.getText().toString().trim());
        //goToActivity(LoginActivity.this, MainActivity.class);
        etLoginPhone.setText("");
        etLoginPwd.setText("");
        EventBus.getDefault().post(new EventBusLoginBean());
        finish();
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
