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

import adapter.ChooseEBankAdapter;
import base.BaseActivity;
import bean.EBankListBean;
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
 * 选择网银Activity
 */

public class ChooseEBankActivity extends BaseActivity {
    private static final String TAG = ChooseEBankActivity.class.getSimpleName();
    @BindView(R.id.ll_chooseebank_back)
    LinearLayout llChooseebankBack;
    @BindView(R.id.lv_chooseebank)
    ListView lvChooseebank;
    private List<EBankListBean.ResultBean> eBankList = new ArrayList<>();
    private ChooseEBankAdapter adapter;

    @Override
    public int initLayout() {
        return R.layout.activity_chooseebank;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        adapter = new ChooseEBankAdapter(eBankList, ChooseEBankActivity.this);
        lvChooseebank.setAdapter(adapter);
        //获取银行列表
        getEBankList();
    }

    /**
     * 获取银行列表
     */
    private void getEBankList() {
        //获取token
        String token = SpUtils.getString(ChooseEBankActivity.this, "token");
        //获取时间戳
        String time = TimeUtils.getTime();
        //获取sign
        String md5_sign = MD5Utils.getMD5WithSalt("timestamp=" + time + "&token=" + token, NetWorkConfig.SIGN);
        OkHttpUtils
                .get()
                .url(NetWorkConfig.BaseURL+NetWorkConfig.OPEN_OpenBankList)
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
                                //解析银行列表json
                                parseEBankListJson(response);
                            }else {
                                AppUtils.showToast(ChooseEBankActivity.this, (String) jsonObject.opt("msg"));
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    /**
     * 解析银行列表json
     * @param response
     */
    private void parseEBankListJson(String response) {
        Gson gson = new Gson();
        EBankListBean eBankListBean = gson.fromJson(response, EBankListBean.class);
        List<EBankListBean.ResultBean> result = eBankListBean.getResult();
        eBankList.addAll(result);
        adapter.notifyDataSetChanged();
    }

    @OnClick(R.id.ll_chooseebank_back)
    public void onViewClicked(View clickView) {
        switch (clickView.getId()){
            case R.id.ll_chooseebank_back:
                finish();
                break;
        }
    }
}
