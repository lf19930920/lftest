package fragment;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.xykgj.tx.xinyongkaguanjia.R;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import adapter.BillDetailAdapter;
import base.BaseFragment;
import bean.BillDetailChildBean;
import bean.BillDetailGroupBean;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import network.NetWorkConfig;
import okhttp3.Call;
import utils.AppUtils;
import utils.MD5Utils;
import utils.SpUtils;
import utils.TimeUtils;

/**
 * Created by mayn on 2018/7/9.
 * 我的信用卡详情 -- 账单明细fragment
 */

public class BillDetailFragment extends BaseFragment implements ExpandableListView.OnGroupClickListener{
    private static final String TAG = BillDetailFragment.class.getSimpleName();
    Unbinder unbinder;
    @BindView(R.id.btn_billdetail_inputebank)
    Button btnBilldetailInputebank;
    @BindView(R.id.tv_billdetail_notoutbill)
    TextView tvBilldetailNotoutbill;
    @BindView(R.id.ex_lv_billdetail)
    ExpandableListView exLvBilldetail;
    @BindView(R.id.tv_billdetail_notoutbilltime)
    TextView tvBilldetailNotoutbillTime;


    private List<BillDetailChildBean.ResultBean> childList = new ArrayList<>();//账单明细子条目数据源

    private List<BillDetailGroupBean.ResultBean> groupList = new ArrayList<>();//账单明细父布局数据源

    private List<List<BillDetailChildBean.ResultBean>> childListData = new ArrayList<>();//账单明细子条目数据源进行二次封装
    private BillDetailAdapter adapter;
    private String time;
    private String token;

    @Override
    protected View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_billdetail, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initData() {
        exLvBilldetail.setOnGroupClickListener(this);
        adapter = new BillDetailAdapter(mActivity,groupList,childList);
        exLvBilldetail.setAdapter(adapter);
        time = TimeUtils.getTime();
        token = SpUtils.getString(mActivity, "token");
        //请求父条目数据的sign
        String md5_sign_group = MD5Utils.getMD5WithSalt("timestamp=" + time + "&token=" + token, NetWorkConfig.SIGN);
        groupList.clear();
        //获取账单父条目数据
        getGroupBillDetailData(time, token,md5_sign_group);

    }

    /**
     * 获取账单父条目数据
     * @param time
     * @param token
     * @param md5_sign_group
     */
    private void getGroupBillDetailData(String time, String token, String md5_sign_group) {
        OkHttpUtils
                .get()
                .url(NetWorkConfig.BaseURL+NetWorkConfig.USER_UserBillList)
                .addParams("timestamp",time)
                .addParams("token",token)
                .addParams("sign",md5_sign_group)
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
                                //解析父布局账单详情json
                                parseGroupBillDetailJson(response);
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
     * 解析父布局账单详情json
     * @param response
     */
    private void parseGroupBillDetailJson(String response) {
        Gson gson = new Gson();
        BillDetailGroupBean billDetailGroupBean = gson.fromJson(response, BillDetailGroupBean.class);
        List<BillDetailGroupBean.ResultBean> result = billDetailGroupBean.getResult();
        groupList.addAll(result);
        adapter.notifyDataSetChanged();
    }


    @OnClick(R.id.btn_billdetail_inputebank)
    public void onViewClicked(View clickView) {
        switch (clickView.getId()){
            case R.id.btn_billdetail_inputebank:
                break;
        }
    }

    @Override
    public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
        if (exLvBilldetail.isGroupExpanded(groupPosition)){
            //如果分组被打开 直接关闭
            exLvBilldetail.collapseGroup(groupPosition);
        }else {
            childList.clear();
            exLvBilldetail.expandGroup(groupPosition,true);
            int group_id = groupList.get(groupPosition).getId();
            Log.i(TAG, "group_id="+group_id);
            //请求子条目明细数据的sign
            String md5_sign_child = MD5Utils.getMD5WithSalt("bill_id=" + group_id + "&timestamp=" + time + "&token=" + token, NetWorkConfig.SIGN);
            //获取子条目明细数据
            getChildBillDetailData(time,token,md5_sign_child,group_id);
        }

        return true;
    }
    /**
     * 获取账单子条目详情数据
     * @param time
     * @param token
     * @param md5_sign
     * @param id
     */
    private void getChildBillDetailData(String time, String token, String md5_sign,int id) {
        OkHttpUtils
                .get()
                .url(NetWorkConfig.BaseURL+NetWorkConfig.USER_UserBillLogList)
                .addParams("bill_id",String.valueOf(id))
                .addParams("token",token)
                .addParams("timestamp",time)
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
                                parseChildBillDetailJson(response);
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
     * 解析账单子条目明细json
     * @param response
     */
    private void parseChildBillDetailJson(String response) {
        Gson gson = new Gson();
        BillDetailChildBean detailChildBean = gson.fromJson(response, BillDetailChildBean.class);
        List<BillDetailChildBean.ResultBean> result = detailChildBean.getResult();
        childList.addAll(result);
//        //子条目的数据源二次封装
//        childListData.add(result);
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
