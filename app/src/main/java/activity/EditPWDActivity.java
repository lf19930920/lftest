package activity;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.xykgj.tx.xinyongkaguanjia.R;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import base.BaseActivity;
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
 * 修改密码Activity
 */

public class EditPWDActivity extends BaseActivity {


    private static final String TAG = EditPWDActivity.class.getSimpleName();
    @BindView(R.id.et_editpwd_old)
    EditText etEditpwdOld;
    @BindView(R.id.et_editpwd_new)
    EditText etEditpwdNew;
    @BindView(R.id.et_editpwd_again)
    EditText etEditpwdAgain;
    @BindView(R.id.btn_editpwd_commit)
    Button btnEditpwdCommit;
    @BindView(R.id.ll_editpwd_back)
    LinearLayout llEditpwdBack;

    @Override
    public int initLayout() {
        return R.layout.activity_editpwd;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.ll_editpwd_back, R.id.btn_editpwd_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_editpwd_back:
                finish();
                break;
            case R.id.btn_editpwd_commit:
                editPWD(etEditpwdOld.getText().toString().trim(),
                        etEditpwdNew.getText().toString().trim(),
                        etEditpwdAgain.getText().toString().trim());
                break;
        }
    }

    /**
     * 提交修改密码
     *
     * @param old
     * @param newpwd
     * @param againpwd
     */
    private void editPWD(String old, String newpwd, String againpwd) {
        if (!TextUtils.isEmpty(old) && !TextUtils.isEmpty(newpwd) && !TextUtils.isEmpty(againpwd)) {
            //原密码、新密码、确认新密码都不为空
            if (newpwd.equals(againpwd)) {
                //新密码和确认新密码一致,提交
                netEditPWDCommit(old, newpwd, againpwd);
            } else {
                AppUtils.showToast(EditPWDActivity.this, "两次输入的密码不一致，请核对");
                return;
            }
        } else {
            AppUtils.showToast(EditPWDActivity.this, "原密码、新密码、确认新密码不能为空");
            return;
        }
    }

    /**
     * 提交修改密码
     *
     * @param old
     * @param newpwd
     * @param againpwd
     */
    private void netEditPWDCommit(String old, String newpwd, String againpwd) {
        //获取当前时间戳
        String time = TimeUtils.getTime();
        //获取token
        String token = SpUtils.getString(EditPWDActivity.this, "token");
        //获取sign
        String md5_sign = MD5Utils.getMD5WithSalt("newPwd=" + newpwd + "&pwd=" + old + "&reNewPwd=" + againpwd + "&timestamp=" + time + "&token=" + token, NetWorkConfig.SIGN);
        OkHttpUtils
                .get()
                .url(NetWorkConfig.BaseURL + NetWorkConfig.USER_UpdatePWDURL)
                .addParams("pwd", old)
                .addParams("newPwd", newpwd)
                .addParams("reNewPwd", againpwd)
                .addParams("token", token)
                .addParams("sign", md5_sign)
                .addParams("timestamp", time)
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
                                //修改成功
                            } else {
                                AppUtils.showToast(EditPWDActivity.this, (String) jsonObject.opt("msg"));
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });


    }

}
