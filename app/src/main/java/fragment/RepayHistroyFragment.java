package fragment;

import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;

import com.google.gson.Gson;
import com.xykgj.tx.xinyongkaguanjia.R;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import adapter.RepayHistroyAdapter;
import base.BaseFragment;
import bean.RepayHistroyBean;
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
 * Created by mayn on 2018/7/6.
 * 我的信用卡详情 -- 还款记录fragment
 */

public class RepayHistroyFragment extends BaseFragment implements ExpandableListView.OnChildClickListener {
    private static final String TAG = RepayHistroyFragment.class.getSimpleName();
    Unbinder unbinder;
    @BindView(R.id.ex_lv_repayhistroy)
    ExpandableListView exLvRepayhistroy;

    private List<RepayHistroyBean.ResultBean> parent_Data = new ArrayList<>();//父布局的数据源
    private RepayHistroyAdapter adapter;
    private int cardId;

    @Override
    protected View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_repayhistroy, null);
        cardId = getArguments().getInt("cardId", 0);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initData() {
        exLvRepayhistroy.setOnChildClickListener(this);
        //联网获取还款记录数据
        getRepayHistroy();
        adapter = new RepayHistroyAdapter(mActivity,parent_Data);
        exLvRepayhistroy.setAdapter(adapter);
    }

    /**
     * 联网获取还款记录数据
     */
    private void getRepayHistroy() {
        String token = SpUtils.getString(mActivity, "token");
        String time = TimeUtils.getTime();
        String md5_sign = MD5Utils.getMD5WithSalt("card_id=" + cardId + "&timestamp=" + time + "&token=" + token, NetWorkConfig.SIGN);
        OkHttpUtils
                .get()
                .url(NetWorkConfig.BaseURL+NetWorkConfig.USER_UserRepayList)
                .addParams("card_id",String.valueOf(cardId))
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
                                //解析还款记录json
                                parseRepayHistroyJson(response);
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

    /**
     * 解析还款记录json
     * @param response
     */
    private void parseRepayHistroyJson(String response) {
        Gson gson = new Gson();
        RepayHistroyBean repayHistroyBean = gson.fromJson(response, RepayHistroyBean.class);
        List<RepayHistroyBean.ResultBean> result = repayHistroyBean.getResult();
        parent_Data.addAll(result);
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

        return false;
    }
}
