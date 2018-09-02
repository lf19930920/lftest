package activity;

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

import java.util.ArrayList;
import java.util.List;

import adapter.MSGCenterAdapter;
import base.BaseActivity;
import bean.MsgTypeBean;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import network.NetWorkConfig;
import okhttp3.Call;
import utils.MD5Utils;
import utils.SpUtils;
import utils.TimeUtils;

/**
 * Created by mayn on 2018/6/27.
 * 消息中心Activity
 */

public class MSGCenterActivity extends BaseActivity implements AdapterView.OnItemClickListener{
    private static final String TAG = MSGCenterActivity.class.getSimpleName();
    @BindView(R.id.ll_msgcenter_back)
    LinearLayout llMsgcenterBack;
    @BindView(R.id.lv_msgcenter)
    ListView lvMsgcenter;
    //消息中心list
    private List<MsgTypeBean.ResultBean> msgList = new ArrayList<>();
    private MSGCenterAdapter adapter;

    @Override
    public int initLayout() {
        return R.layout.activity_msgcenter;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        adapter = new MSGCenterAdapter(msgList, MSGCenterActivity.this);
        lvMsgcenter.setAdapter(adapter);
        lvMsgcenter.setOnItemClickListener(this);
        //获取token
        String token = SpUtils.getString(MSGCenterActivity.this, "token");
        //获取消息分类
        getMsgType(token);

    }

    /**
     * 获取消息的分类
     *
     * @param token
     */
    private void getMsgType(String token) {
        //获取时间戳
        String time = TimeUtils.getTime();
        //获取sign
        String md5_sign = MD5Utils.getMD5WithSalt("timestamp=" + time + "&token=" + token, NetWorkConfig.SIGN);
        OkHttpUtils
                .get()
                .url(NetWorkConfig.BaseURL + NetWorkConfig.USER_UserMSGType)
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
                                parseMsgTypeJson(response);
                            } else {

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    /**
     * 解析消息类型json
     *
     * @param response
     */
    private void parseMsgTypeJson(String response) {
        Gson gson = new Gson();
        MsgTypeBean msgTypeBean = gson.fromJson(response, MsgTypeBean.class);
        List<MsgTypeBean.ResultBean> result = msgTypeBean.getResult();
        msgList.addAll(result);
        adapter.notifyDataSetChanged();
    }

    @OnClick(R.id.ll_msgcenter_back)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_msgcenter_back:
                finish();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position == 0){
            goToActivity(MSGCenterActivity.this,SystemNotifiActivity.class);
        }else if (position == 1){
            //goToActivity(MSGCenterActivity.this,NoticeActivity.class);
        }else if (position == 2){
            goToActivity(MSGCenterActivity.this,ActivitCenterActivity.class);
        }
    }
}
