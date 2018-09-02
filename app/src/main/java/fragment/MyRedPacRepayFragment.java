package fragment;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
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

import activity.NoUseRedPagActivity;
import adapter.MyRedPacRepayAdapter;
import base.BaseFragment;
import bean.RepayRedBean;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import network.NetWorkConfig;
import okhttp3.Call;
import utils.AppUtils;
import utils.MD5Utils;
import utils.SpUtils;
import utils.TimeUtils;

/**
 * Created by mayn on 2018/6/29.
 * 我的红包--还款金fragment
 */

public class MyRedPacRepayFragment extends BaseFragment {

    @BindView(R.id.lv_myredpac_repay)
    ListView lvMyredpacRepay;
    Unbinder bind;
    private static final String MyRedPac_Repay_TYPE = "1";
    private static final String TAG = MyRedPacRepayFragment.class.getSimpleName();
    private List<RepayRedBean.ResultBean> repayList = new ArrayList<>();
    private MyRedPacRepayAdapter adapter;

    @Override
    protected View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_myredpacrepay, null);
        bind = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initData() {
        adapter = new MyRedPacRepayAdapter(repayList, mActivity);
        lvMyredpacRepay.setAdapter(adapter);
        String token = SpUtils.getString(mActivity, "token");
        Log.i(TAG, "token="+token);
        String time = TimeUtils.getTime();
        String md5_sign = MD5Utils.getMD5WithSalt("timestamp=" + time + "&token=" + token + "&type=" + MyRedPac_Repay_TYPE+"&usable="+"0", NetWorkConfig.SIGN);
        //联网获取 我的红包 还款金红包
        getRepayRedPac(token,time,md5_sign);
    }

    /**
     * 联网获取 我的红包 还款金红包
     * @param token
     * @param time
     * @param md5_sign
     */
    private void getRepayRedPac(String token, String time, String md5_sign) {
        OkHttpUtils
                .get()
                .url(NetWorkConfig.BaseURL+NetWorkConfig.USER_UserRedPacList)
                .addParams("type",MyRedPac_Repay_TYPE)
                .addParams("token",token)
                .addParams("timestamp",time)
                .addParams("usable","0")
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
                                //解析获取到还款金红包数据的json
                                parseRepayJson(response);
                            }else {
                                AppUtils.showToast(mActivity, (String) jsonObject.opt("msg"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    /**
     * 解析获取到还款金红包数据的json
     * @param response
     */
    private void parseRepayJson(String response) {
        Gson gson = new Gson();
        RepayRedBean repayRedBean = gson.fromJson(response, RepayRedBean.class);
        List<RepayRedBean.ResultBean> result = repayRedBean.getResult();
        if (result.size()>0){
            View view = LayoutInflater.from(mActivity).inflate(R.layout.lv_footer_noredpag,null,false);
            LinearLayout ll_lv_footer_noredpag = (LinearLayout) view.findViewById(R.id.ll_lv_footer_noredpag);
            lvMyredpacRepay.addFooterView(view,null,false);
            ll_lv_footer_noredpag.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent repay = new Intent(mActivity, NoUseRedPagActivity.class);
                    repay.putExtra("redpagtype","1");
                    startActivity(repay);
                }
            });
        }
        repayList.addAll(result);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        if (bind != null) {
            bind.unbind();
        }
        super.onDestroyView();
    }

}
