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

import adapter.NoticeAdapter;
import base.BaseActivity;
import bean.NoticeBean;
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
 * Created by mayn on 2018/6/28.
 * 消息中心 -- 公告Activity
 */

public class NoticeActivity extends BaseActivity {
    private static final String TAG = NoticeActivity.class.getSimpleName();
    @BindView(R.id.lv_notice)
    ListView lvNotice;
    @BindView(R.id.ll_notice_back)
    LinearLayout llNoticeBack;

    private static final String NOT_type = "2";


    private NoticeAdapter adapter;
    private List<NoticeBean> groupList = new ArrayList<>();
    @Override
    public int initLayout() {
        return R.layout.activity_notice;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        adapter = new NoticeAdapter(groupList, NoticeActivity.this);
        lvNotice.setAdapter(adapter);

        //获取token
        String token = SpUtils.getString(NoticeActivity.this, "token");
        //获取时间戳
        String time = TimeUtils.getTime();
        //获取消息中心公告list
        getMSGNoticeList(token,time);
    }

    /**
     * 获取消息中心公告list
     * @param token
     * @param time
     */
    private void getMSGNoticeList(String token, String time) {
        String md5_sign = MD5Utils.getMD5WithSalt("timestamp=" + time + "&token=" + token + "&type=" + NOT_type, NetWorkConfig.SIGN);
        OkHttpUtils
                .get()
                .url(NetWorkConfig.BaseURL+NetWorkConfig.USER_UserMSGList)
                .addParams("type",NOT_type)
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
                                //解析消息中心 -- 公告json
                                parseNoticeJson(response);
                            }else {
                                AppUtils.showToast(NoticeActivity.this, (String) jsonObject.opt("msg"));
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    /**
     * 解析消息中心 -- 公告json
     * @param response
     */
    private void parseNoticeJson(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONObject result = jsonObject.getJSONObject("result");
            getNoticeJsonForResult(result);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void getNoticeJsonForResult(JSONObject result) throws JSONException{
        //这个 result就是返回的result
        if (result != null) {
            Iterator<String> it = result.keys();
            while (it.hasNext()) {
                NoticeBean bean = new NoticeBean();
                List<NoticeBean.NoticeChildBean> childData = new ArrayList<>();
                // 这个 key就是你的日期
                String key = (String) it.next();
                Log.i(TAG, "key="+key);
                //这个 array 就是你对应日期下的 数据
                JSONArray mjo = result.optJSONArray(key);
                //对array进行循环遍历，创建子listview的bean对象，在循环结束之后添加到集合中
                for (int i = 0; i <mjo.length(); i++) {
                    NoticeBean.NoticeChildBean childBean = new NoticeBean.NoticeChildBean();
                    JSONObject  jsonObject = (JSONObject) mjo.get(i);
                    childBean.setTitle((String) jsonObject.opt("title"));
                    childBean.setDescription((String) jsonObject.opt("description"));
                    childBean.setUrl((String) jsonObject.opt("url"));
                    childBean.setId((Integer) jsonObject.opt("id"));
                    childBean.setCreate_time((String) jsonObject.opt("create_time"));
                    childData.add(childBean);
                }
                bean.setDate(key);
                bean.setChildList(childData);
                Log.i(TAG, "bean.getResult().size()="+bean.getChildList().size());
                Log.i(TAG, "mjo="+mjo);
                groupList.add(bean);
                Log.i(TAG, "groupList.size()="+groupList.size());
            }
            adapter.notifyDataSetChanged();
        }
    }

    @OnClick(R.id.ll_notice_back)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_notice_back:
                finish();
                break;
        }
    }

}
