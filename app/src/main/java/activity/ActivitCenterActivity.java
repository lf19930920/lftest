package activity;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.gson.Gson;
import com.xykgj.tx.xinyongkaguanjia.R;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import adapter.ActiviteCenterAdapter;
import base.BaseActivity;
import bean.IsReadBean;
import bean.MsgListBean;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import network.NetWorkConfig;
import okhttp3.Call;
import utils.AppUtils;
import utils.ListToStringUtils;
import utils.MD5Utils;
import utils.SpUtils;
import utils.TimeUtils;


/**
 * Created by mayn on 2018/6/25.
 * 消息中心 -- 活动中心Activity
 */

public class ActivitCenterActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    private static final String TAG = ActivitCenterActivity.class.getSimpleName();
    @BindView(R.id.lv_activitecenter)
    ListView lvActivitecenter;
    @BindView(R.id.ll_activitecenter_back)
    LinearLayout llActivitecenterBack;

    private static final String AC_type = "3";

    private List<MsgListBean.ResultBean> acList = new ArrayList<>();
    private ActiviteCenterAdapter adapter;

    private List<IsReadBean> tagList = new ArrayList<>();
    private String token;
    private String time;

    @Override
    public int initLayout() {
        return R.layout.activity_activitecenter;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        lvActivitecenter.setOnItemClickListener(this);
        adapter = new ActiviteCenterAdapter(acList, ActivitCenterActivity.this);
        lvActivitecenter.setAdapter(adapter);
        //获取type
        token = SpUtils.getString(ActivitCenterActivity.this, "token");
        time = TimeUtils.getTime();
        //获取消息中心，活动list
        getMSGActivitaList(token, time);
    }

    /**
     * 获取消息中心，活动list
     *
     * @param token
     * @param time
     */
    private void getMSGActivitaList(String token, String time) {
        String md5_sign = MD5Utils.getMD5WithSalt("timestamp=" + time + "&token=" + token + "&type=" + AC_type, NetWorkConfig.SIGN);
        OkHttpUtils
                .get()
                .url(NetWorkConfig.BaseURL + NetWorkConfig.USER_UserMSGList)
                .addParams("type", AC_type)
                .addParams("token", token)
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
                                //解析消息中心 -- 活动 json
                                parseMSGACJson(response);
                            } else {
                                AppUtils.showToast(ActivitCenterActivity.this, (String) jsonObject.opt("msg"));
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    /**
     * 解析消息中心 -- 活动 json
     *
     * @param response
     */
    private void parseMSGACJson(String response) {
        Gson gson = new Gson();
        MsgListBean msgListBean = gson.fromJson(response, MsgListBean.class);
        List<MsgListBean.ResultBean> result = msgListBean.getResult();
        acList.addAll(result);
        adapter.notifyDataSetChanged();
    }

    @OnClick({R.id.ll_activitecenter_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_activitecenter_back:
                finish();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        //发生条目点击事件，记录下点击消息的id
        MsgListBean.ResultBean clickBean = (MsgListBean.ResultBean) adapter.getItem(position);

        IsReadBean isReadBean = new IsReadBean();
        isReadBean.setRead(true);
        isReadBean.setId(clickBean.getId());

        String acmsgList = SpUtils.getString(ActivitCenterActivity.this, "ACMSGList");
        if (!TextUtils.isEmpty(acmsgList)){
            try {
                List<IsReadBean> readBeanList = ListToStringUtils.String2SceneList(acmsgList);
                if (!readBeanList.contains(isReadBean)){
                    readBeanList.add(isReadBean);
                    SpUtils.saveString(ActivitCenterActivity.this,"ACMSGList",ListToStringUtils.SceneList2String(readBeanList));
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }else {
            tagList.add(isReadBean);
            try {
                String list2String = ListToStringUtils.SceneList2String(tagList);
                SpUtils.saveString(ActivitCenterActivity.this,"ACMSGList",list2String);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

//        acList.set(position, clickBean);
        adapter.notifyDataSetChanged();
        Intent intent = new Intent(ActivitCenterActivity.this, MsgDetailActivity.class);
        startActivity(intent);

    }


    @Override
    protected void onResume() {
        super.onResume();

    }
}
