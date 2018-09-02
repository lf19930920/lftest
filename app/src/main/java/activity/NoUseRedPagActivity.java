package activity;

import android.content.Intent;
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

import java.util.List;

import adapter.BorrowNoUseRedPagAdapter;
import adapter.InvestNoUseRedPagAdapter;
import adapter.RepayNoPagUseAdapter;
import base.BaseActivity;
import bean.RedPagListBean;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import network.NetWorkConfig;
import okhttp3.Call;
import utils.MD5Utils;
import utils.SpUtils;
import utils.TimeUtils;

/**
 * Created by mayn on 2018/6/20.
 * 不可用红包Activity
 */

public class NoUseRedPagActivity extends BaseActivity {


    private static final String TAG = NoUseRedPagActivity.class.getSimpleName();
    @BindView(R.id.lv_nouseredpag)
    ListView lvNouseredpag;
    @BindView(R.id.ll_nouseredpag_back)
    LinearLayout llNouseredpagBack;

    @Override
    public int initLayout() {
        return R.layout.activity_nouseredpag;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        //TODO  请求不可用红包的数据
        Intent intent = getIntent();
        String redpagtype = intent.getStringExtra("redpagtype");
        if ("1".equals(redpagtype)){
            getRedCanNotUseData(redpagtype);
            //还款金不可用红包
        }else if ("2".equals(redpagtype)){
            //投资券不可用红包
            getRedCanNotUseData(redpagtype);
        }else if ("3".equals(redpagtype)){
            //贷款券不可用红包
            getRedCanNotUseData(redpagtype);
        }

    }

    private void getRedCanNotUseData(final String repayType) {
        String token = SpUtils.getString(NoUseRedPagActivity.this, "token");
        String time = TimeUtils.getTime();
        String md5_sign = MD5Utils.getMD5WithSalt("timestamp=" + time + "&token=" + token + "&type=" + repayType+"&usable="+"0", NetWorkConfig.SIGN);
        OkHttpUtils
                .get()
                .url(NetWorkConfig.BaseURL+NetWorkConfig.USER_UserRedPacList)
                .addParams("type",repayType)
                .addParams("token",token)
                .addParams("timestamp",time)
                .addParams("usable","1")
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
                                parseNoRedpagJson(response,repayType);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    private void parseNoRedpagJson(String response, String repayType) {
        Gson gson = new Gson();
        RedPagListBean redPagListBean = gson.fromJson(response, RedPagListBean.class);
        List<RedPagListBean.ResultBean> result = redPagListBean.getResult();
        if ("1".equals(repayType)){
            lvNouseredpag.setAdapter(new RepayNoPagUseAdapter(result,NoUseRedPagActivity.this));
        }else if ("2".equals(repayType)){
            lvNouseredpag.setAdapter(new InvestNoUseRedPagAdapter(result,NoUseRedPagActivity.this));
        }else if ("3".equals(repayType)){
            lvNouseredpag.setAdapter(new BorrowNoUseRedPagAdapter(result,NoUseRedPagActivity.this));
        }
    }

    @OnClick(R.id.ll_nouseredpag_back)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_nouseredpag_back:
                finish();
                break;
        }
    }

}
