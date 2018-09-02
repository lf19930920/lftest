package activity;

import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.xykgj.tx.xinyongkaguanjia.R;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import base.BaseActivity;
import bean.RepayDetailBean;
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
 * Created by mayn on 2018/7/9.
 * 还款详情Activity
 */

public class RepayDetailActivity extends BaseActivity {
    private static final String TAG = RepayDetailActivity.class.getSimpleName();
    @BindView(R.id.ll_callus_back)
    LinearLayout llCallusBack;
    @BindView(R.id.tv_repaydetail_money)
    TextView tvRepaydetailMoney;
    @BindView(R.id.tv_repaydetail_progress)
    TextView tvRepaydetailProgress;
    @BindView(R.id.iv_repaydetail_saledetail)
    ImageView ivRepaydetailSaledetail;
    @BindView(R.id.tv_repaydetail_repaymoney)
    TextView tvRepaydetailRepaymoney;
    @BindView(R.id.tv_repaydetail_repaymoney_text)
    TextView tvRepaydetailRepaymoneyText;
    @BindView(R.id.tv_repaydetail_cardtype)
    TextView tvRepaydetailCardtype;
    @BindView(R.id.tv_repaydetail_carddetail)
    TextView tvRepaydetailCarddetail;
    @BindView(R.id.tv_repaydetail_progresstext)
    TextView tvRepaydetailProgresstext;
    @BindView(R.id.iv_repaydetail_commit)
    ImageView ivRepaydetailCommit;
    @BindView(R.id.tv_repaydetail_commit)
    TextView tvRepaydetailCommit;
    @BindView(R.id.tv_repaydetail_commit_time)
    TextView tvRepaydetailCommitTime;
    @BindView(R.id.ll_repaydetail_one)
    LinearLayout llRepaydetailOne;
    @BindView(R.id.iv_repaydetail_bankdeal)
    ImageView ivRepaydetailBankdeal;
    @BindView(R.id.tv_repaydetail_bankdeal)
    TextView tvRepaydetailBankdeal;
    @BindView(R.id.tv_repaydetail_bankdealtime)
    TextView tvRepaydetailBankdealtime;
    @BindView(R.id.ll_repaydetail_two)
    LinearLayout llRepaydetailTwo;
    @BindView(R.id.iv_repaydetail_success)
    ImageView ivRepaydetailSuccess;
    @BindView(R.id.tv_repaydetail_success)
    TextView tvRepaydetailSuccess;
    @BindView(R.id.tv_repaydetail_successtime)
    TextView tvRepaydetailSuccesstime;
    @BindView(R.id.tv_repaydetail_repaymessage)
    TextView tvRepaydetailRepaymessage;

    @Override
    public int initLayout() {
        return R.layout.activity_repaydetail;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        //获取时间戳
        String time = TimeUtils.getTime();
        //获取token
        String token = SpUtils.getString(RepayDetailActivity.this, "token");
        //拼接sign
        String md5_sign = MD5Utils.getMD5WithSalt("log_id=" + "1" + "&timestamp=" + time + "&token=" + token, NetWorkConfig.SIGN);

        //获取还款详情数据
        getRepayDetailData(time, token, md5_sign);
    }

    /**
     * 获取还款详情数据
     *
     * @param time
     * @param token
     * @param md5_sign
     */
    private void getRepayDetailData(String time, String token, String md5_sign) {
        OkHttpUtils
                .get()
                .url(NetWorkConfig.BaseURL + NetWorkConfig.USER_UserBillDetail)
                .addParams("log_id", "1")
                .addParams("timestamp", time)
                .addParams("token", token)
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
                                //解析还款详情json
                                parseRepayDetailJson(response);
                            } else {
                                AppUtils.showToast(RepayDetailActivity.this, (String) jsonObject.opt("msg"));
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    /**
     * 解析还款详情json
     *
     * @param response
     */
    private void parseRepayDetailJson(String response) {
        Gson gson = new Gson();
        RepayDetailBean repayDetailBean = gson.fromJson(response, RepayDetailBean.class);
        RepayDetailBean.ResultBean result = repayDetailBean.getResult();
        //还款金额
        tvRepaydetailMoney.setText(result.getAmount());
        //还款状态
        tvRepaydetailProgress.setText(result.getStatus_name());
        //还款优惠的金额
        tvRepaydetailRepaymoney.setText(result.getRedpkg_amount());
        //付款信息
        tvRepaydetailRepaymessage.setText(result.getPay_info());
        //银行卡信息
        tvRepaydetailCarddetail.setText(result.getCard_info());
        //还款进度
        int status = result.getStatus();
        if (status == 1) {
            tvRepaydetailCommit.setTextColor(Color.parseColor("#0066FF"));
            ivRepaydetailCommit.setImageResource(R.mipmap.repaydetail_progress_complete);
            ivRepaydetailBankdeal.setImageResource(R.mipmap.repaydetail_progress_wait);
            ivRepaydetailSuccess.setImageResource(R.mipmap.repaydetail_progress_wait);
        } else if (status == 2) {
            tvRepaydetailCommit.setTextColor(Color.parseColor("#0066FF"));
            tvRepaydetailBankdeal.setTextColor(Color.parseColor("#0066FF"));
            ivRepaydetailCommit.setImageResource(R.mipmap.repaydetail_progress_complete);
            ivRepaydetailBankdeal.setImageResource(R.mipmap.repaydetail_progress_complete);
            ivRepaydetailSuccess.setImageResource(R.mipmap.repaydetail_progress_wait);
        } else if (status == 3) {
            tvRepaydetailCommit.setTextColor(Color.parseColor("#0066FF"));
            tvRepaydetailBankdeal.setTextColor(Color.parseColor("#0066FF"));
            tvRepaydetailSuccess.setTextColor(Color.parseColor("#0066FF"));
            ivRepaydetailCommit.setImageResource(R.mipmap.repaydetail_progress_complete);
            ivRepaydetailBankdeal.setImageResource(R.mipmap.repaydetail_progress_complete);
            ivRepaydetailSuccess.setImageResource(R.mipmap.repaydetail_progress_complete);
        }
        tvRepaydetailCommitTime.setText(result.getCreate_time());
        tvRepaydetailBankdealtime.setText(result.getDeal_time());
        tvRepaydetailSuccesstime.setText(result.getFinish_time());

    }

    @OnClick({R.id.ll_callus_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_callus_back:
                finish();
                break;
        }
    }
}
