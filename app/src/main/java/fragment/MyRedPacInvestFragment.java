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
import adapter.MyRedPacInvestAdapter;
import base.BaseFragment;
import bean.RedPagListBean;
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
 * 我的红包 --  投资券fragment
 */

public class MyRedPacInvestFragment extends BaseFragment {

    Unbinder bind;
    @BindView(R.id.lv_myredpac_invest)
    ListView lvMyredpacInvest;
    private static final String MyRedPac_Invest_TYPE = "2";
    private static final String TAG = MyRedPacInvestFragment.class.getSimpleName();
    private List<RedPagListBean.ResultBean> datas = new ArrayList<>();
    private MyRedPacInvestAdapter adapter;

    @Override
    protected View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_myredpacinvest, null);
        bind = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initData() {
        datas.clear();
        adapter = new MyRedPacInvestAdapter(datas, mActivity);
        lvMyredpacInvest.setAdapter(adapter);
        //获取时间戳
        String time = TimeUtils.getTime();
        //获取token
        String token = SpUtils.getString(mActivity, "token");
        //获取sign
        String md5_sign = MD5Utils.getMD5WithSalt("timestamp=" + time + "&token=" + token + "&type=" + MyRedPac_Invest_TYPE+"&usable="+"0", NetWorkConfig.SIGN);
        //联网获取我的红包 投资券数据
        getInvestRedPac(time, token, md5_sign);
    }

    /**
     * 联网获取我的红包 投资券数据
     *
     * @param time
     * @param token
     * @param md5_sign
     */
    private void getInvestRedPac(String time, String token, String md5_sign) {
        OkHttpUtils
                .get()
                .url(NetWorkConfig.BaseURL + NetWorkConfig.USER_UserRedPacList)
                .addParams("type", MyRedPac_Invest_TYPE)
                .addParams("token", token)
                .addParams("timestamp", time)
                .addParams("usable","0")
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
                                parseInvestRedpagJson(response);
                            }else {
                                AppUtils.showToast(mActivity, (String) jsonObject.opt("msg"));
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    private void parseInvestRedpagJson(String response) {
        Gson gson = new Gson();
        RedPagListBean redPagListBean = gson.fromJson(response, RedPagListBean.class);
        List<RedPagListBean.ResultBean> result = redPagListBean.getResult();
        if (result.size()>0){
            View view = LayoutInflater.from(mActivity).inflate(R.layout.lv_footer_noredpag,null,false);
            LinearLayout ll_lv_footer_noredpag = (LinearLayout) view.findViewById(R.id.ll_lv_footer_noredpag);
            lvMyredpacInvest.addFooterView(view,null,false);
            ll_lv_footer_noredpag.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent repay = new Intent(mActivity, NoUseRedPagActivity.class);
                    repay.putExtra("redpagtype","2");
                    startActivity(repay);
                }
            });
        }
        datas.addAll(result);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        if (bind != null){
            bind.unbind();
        }
        super.onDestroyView();
    }
}
        