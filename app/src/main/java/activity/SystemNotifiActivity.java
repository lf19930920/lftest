package activity;

import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.xykgj.tx.xinyongkaguanjia.R;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import adapter.SystemNotifiAdapter;
import base.BaseActivity;
import bean.SystemNoticeBean;
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
 * Created by mayn on 2018/6/25.
 * 消息中心 -- 系统通知Activity
 */

public class SystemNotifiActivity extends BaseActivity {
    private static final String TAG = SystemNotifiActivity.class.getSimpleName();
    @BindView(R.id.lv_systemnotifi)
    ListView lvSystemnotifi;
    @BindView(R.id.ll_systemnotifi_back)
    LinearLayout llSystemnotifiBack;

    private static final String SYN_type = "1";

    private SystemNotifiAdapter adapter;
    private List<SystemNoticeBean> groupList = new ArrayList<>();


    @Override
    public int initLayout() {
        return R.layout.activity_systemnotifi;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        adapter = new SystemNotifiAdapter(groupList, SystemNotifiActivity.this);
        lvSystemnotifi.setAdapter(adapter);
        //获取type
        String token = SpUtils.getString(SystemNotifiActivity.this, "token");
        String time = TimeUtils.getTime();
        //获取系统通知数据
        getSystemNotiFi(token,time);

    }

    /**
     * 获取系统通知数据
     * @param token
     * @param time
     */
    private void getSystemNotiFi(String token, String time) {
        String md5_sign = MD5Utils.getMD5WithSalt("timestamp=" + time + "&token=" + token + "&type=" + SYN_type, NetWorkConfig.SIGN);
        OkHttpUtils
                .get()
                .url(NetWorkConfig.BaseURL+NetWorkConfig.USER_UserMSGList)
                .addParams("type",SYN_type)
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
                                //解析消息中心 -- 系统通知json
                                parseSYNMsgListJson(response);
                            }else {
                                AppUtils.showToast(SystemNotifiActivity.this, (String) jsonObject.opt("msg"));
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    /**
     * 解析消息中心 -- 系统通知json
     * @param response
     */
    private void parseSYNMsgListJson(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONObject result = jsonObject.getJSONObject("result");
            getSystemNotifiJson(result);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.ll_systemnotifi_back)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_systemnotifi_back:
                finish();
                break;
        }
    }

    /**
     * 使用迭代器解析请求回来的数据
     * @param result
     * @return
     */
    public String getSystemNotifiJson(JSONObject result) throws JSONException {
        //这个 result就是返回的result
        if (result != null) {
            Iterator<String> it = result.keys();
            while (it.hasNext()) {
                SystemNoticeBean bean = new SystemNoticeBean();
                List<SystemNoticeBean.SystemDetailBean> childData = new ArrayList<>();
                // 这个 key就是你的日期
                String key = (String) it.next();
                Log.i(TAG, "key="+key);
                //这个 array 就是你对应日期下的 数据
                JSONArray mjo = result.optJSONArray(key);
                //对array进行循环遍历，创建子listview的bean对象，在循环结束之后添加到集合中
                for (int i = 0; i <mjo.length(); i++) {
                    SystemNoticeBean.SystemDetailBean childBean = new SystemNoticeBean.SystemDetailBean();
                    JSONObject  jsonObject = (JSONObject) mjo.get(i);
                    childBean.setTitle((String) jsonObject.opt("title"));
                    childBean.setDescription((String) jsonObject.opt("description"));
                    childBean.setUrl((String) jsonObject.opt("url"));
                    childBean.setId((Integer) jsonObject.opt("id"));
                    childBean.setCreate_time((String) jsonObject.opt("create_time"));
                    childData.add(childBean);
                }
                bean.setDate(key);
                bean.setResult(childData);
                Log.i(TAG, "bean.getResult().size()="+bean.getResult().size());
                Log.i(TAG, "mjo="+mjo);
                groupList.add(bean);
                Log.i(TAG, "groupList.size()="+groupList.size());
            }
            adapter.notifyDataSetChanged();
        }
        return "";
    }
}
