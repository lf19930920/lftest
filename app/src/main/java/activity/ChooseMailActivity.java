package activity;

import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.gson.Gson;
import com.xykgj.tx.xinyongkaguanjia.R;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import adapter.ChooseMailAdapter;
import base.BaseActivity;
import bean.MailListBean;
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
 * Created by mayn on 2018/7/12.
 * 选择邮箱Activity
 */

public class ChooseMailActivity extends BaseActivity {
    private static final String TAG = ChooseMailActivity.class.getSimpleName();
    @BindView(R.id.ll_choosemail_back)
    LinearLayout llChoosemailBack;
    @BindView(R.id.lv_choosemail)
    ListView lvChoosemail;
    private List<MailListBean.ResultBean> mailList = new ArrayList<>();
    private ChooseMailAdapter adapter;

    @Override
    public int initLayout() {
        return R.layout.activity_choosemail;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        adapter = new ChooseMailAdapter(mailList, ChooseMailActivity.this);
        lvChoosemail.setAdapter(adapter);
        //获取邮箱列表
        getMailList();
    }

    /**
     * 获取邮箱列表
     */
    private void getMailList() {
        //获取token
        String token = SpUtils.getString(ChooseMailActivity.this, "token");
        //获取时间戳
        String time = TimeUtils.getTime();
        //拼接sign
        String md5_sign = MD5Utils.getMD5WithSalt("timestamp=" + time + "&token=" + token, NetWorkConfig.SIGN);
        OkHttpUtils
                .get()
                .url(NetWorkConfig.BaseURL+NetWorkConfig.OPEN_OpenMailList)
                .addParams("timestamp",time)
                .addParams("token",token)
                .addParams("sign",md5_sign)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.i(TAG, "onError="+e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.i(TAG, "onResponse="+response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            int code = (int) jsonObject.opt("code");
                            if (code == 1000){
                                //解析邮箱列表json
                                parseMailListJson(response);
                            }else {
                                AppUtils.showToast(ChooseMailActivity.this, (String) jsonObject.opt("msg"));
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    /**
     * 解析邮箱列表json
     * @param response
     */
    private void parseMailListJson(String response) {
        Gson gson = new Gson();
        MailListBean mailListBean = gson.fromJson(response, MailListBean.class);
        List<MailListBean.ResultBean> result = mailListBean.getResult();
        mailList.addAll(result);
        adapter.notifyDataSetChanged();
    }

    @OnClick(R.id.ll_choosemail_back)
    public void onViewClicked(View clickView) {
        switch (clickView.getId()){
            case R.id.ll_choosemail_back:
                finish();
                break;
        }
    }
}
