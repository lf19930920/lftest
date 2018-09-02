package activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.xykgj.tx.xinyongkaguanjia.R;

import java.util.ArrayList;
import java.util.List;

import adapter.DialogChooseBankCardAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import utils.AppUtils;

/**
 * Created by mayn on 2018/7/18.
 * 确认还款dialog --- Activity
 */

public class DialogChooseActivity extends Activity {


    @BindView(R.id.ll_dialogrepay_close)
    LinearLayout llDialogrepayClose;
    @BindView(R.id.tv_dialogrepay_cardnum)
    TextView tvDialogrepayCardnum;
    @BindView(R.id.tv_dialogrepay_cardmoney)
    TextView tvDialogrepayCardmoney;
    @BindView(R.id.iv_dialogrepay_chuxu)
    ImageView ivDialogrepayChuxu;
    @BindView(R.id.ll_dialogrepay_chuxu)
    LinearLayout llDialogrepayChuxu;
    @BindView(R.id.iv_dialogrepay_wechat)
    ImageView ivDialogrepayWechat;
    @BindView(R.id.ll_dialogrepay_wechat)
    LinearLayout llDialogrepayWechat;
    @BindView(R.id.iv_dialogrepay_alipay)
    ImageView ivDialogrepayAlipay;
    @BindView(R.id.ll_dialogrepay_alipay)
    LinearLayout llDialogrepayAlipay;
    @BindView(R.id.tv_dialogrepay_redpag)
    TextView tvDialogrepayRedpag;
    @BindView(R.id.cb_dialogrepay_agree)
    CheckBox cbDialogrepayAgree;
    @BindView(R.id.tv_dialogrepay_agreement)
    TextView tvDialogrepayAgreement;
    @BindView(R.id.tv_dialogrepay_time)
    TextView tvDialogrepayTime;
    @BindView(R.id.btn_dialogrepay_repaynow)
    Button btnDialogrepayRepaynow;
    @BindView(R.id.ll_dialog_choose)
    LinearLayout llDialogChoose;
    @BindView(R.id.ll_dialogdetail_cancel)
    LinearLayout llDialogdetailCancel;
    @BindView(R.id.tv_dialogdetail_whichbank)
    TextView tvDialogdetailWhichbank;
    @BindView(R.id.tv_dialogdetail_repaybank)
    TextView tvDialogdetailRepaybank;
    @BindView(R.id.ll_dialogdetail_repaybank)
    LinearLayout llDialogdetailRepaybank;
    @BindView(R.id.tv_dialogdetail_repaymoney)
    TextView tvDialogdetailRepaymoney;
    @BindView(R.id.btn_dialogdetail_pay)
    Button btnDialogdetailPay;
    @BindView(R.id.ll_dialog_detail)
    LinearLayout llDialogDetail;
    @BindView(R.id.ll_dialog_choosebankcard_close)
    LinearLayout llDialogChoosebankcardClose;
    @BindView(R.id.ll_dialog_choosebankcard)
    LinearLayout llDiaglogChoosebankcard;
    @BindView(R.id.lv_dialog_choosebank)
    ListView lvDialogChoosebank;
    private static final String RepayNow = "立即支付";
    private static final String RepayPlan = "还款计划";
    private Animation detail_in;
    private Animation detail_out;
    private Animation choose_in;
    private Animation choose_out;
    private String repaycardinfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_chooserepay);
        ButterKnife.bind(this);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        getWindow().setGravity(Gravity.BOTTOM);
        Intent intent = getIntent();
        repaycardinfo = intent.getStringExtra("repaycardinfo");
        String repaycardnum = intent.getStringExtra("repaycardnum");
        String unrepayamout = intent.getStringExtra("unrepayamout");
        tvDialogrepayCardmoney.setText("¥"+unrepayamout);
        tvDialogrepayCardnum.setText(repaycardinfo);
        //切换时的动画
        initAnimtion();

    }



    private void initAnimtion() {
        detail_in = AnimationUtils.loadAnimation(DialogChooseActivity.this, R.anim.dialog_repaydetail_in);
        detail_out = AnimationUtils.loadAnimation(DialogChooseActivity.this, R.anim.dialog_repaydetail_out);
        choose_in = AnimationUtils.loadAnimation(DialogChooseActivity.this, R.anim.dialog_choose_in);
        choose_out = AnimationUtils.loadAnimation(DialogChooseActivity.this, R.anim.dialog_choose_out);

    }

    private void setFooterForListview() {
        View view = LayoutInflater.from(DialogChooseActivity.this).inflate(R.layout.lv_footer_dialog,null,false);
        lvDialogChoosebank.addFooterView(view,null,false);
        LinearLayout ll_lv_footer_addcard = (LinearLayout) view.findViewById(R.id.ll_lv_footer_addcard);
        ll_lv_footer_addcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DialogChooseActivity.this,AddBankCardActivity.class);
                startActivity(intent);
            }
        });
        List<String> data = new ArrayList<>();
        for (int i = 0; i <5; i++) {
            data.add("1");
        }
        lvDialogChoosebank.setAdapter(new DialogChooseBankCardAdapter(data,DialogChooseActivity.this));
    }

    @OnClick({R.id.ll_dialogrepay_close, R.id.ll_dialogrepay_chuxu,
            R.id.ll_dialogrepay_wechat,R.id.ll_dialog_choosebankcard_close,
            R.id.ll_dialogrepay_alipay, R.id.tv_dialogrepay_agreement,
            R.id.btn_dialogrepay_repaynow, R.id.ll_dialogdetail_cancel,
            R.id.ll_dialogdetail_repaybank, R.id.btn_dialogdetail_pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_dialogrepay_close:
                finish();
                break;
            case R.id.ll_dialogrepay_chuxu:
                btnDialogrepayRepaynow.setText(RepayPlan);
                ivDialogrepayChuxu.setVisibility(View.VISIBLE);
                ivDialogrepayWechat.setVisibility(View.GONE);
                ivDialogrepayAlipay.setVisibility(View.GONE);
                break;
            case R.id.ll_dialogrepay_wechat:
                btnDialogrepayRepaynow.setText(RepayNow);
                ivDialogrepayChuxu.setVisibility(View.GONE);
                ivDialogrepayWechat.setVisibility(View.VISIBLE);
                ivDialogrepayAlipay.setVisibility(View.GONE);
                break;
            case R.id.ll_dialogrepay_alipay:
                btnDialogrepayRepaynow.setText(RepayNow);
                ivDialogrepayChuxu.setVisibility(View.GONE);
                ivDialogrepayWechat.setVisibility(View.GONE);
                ivDialogrepayAlipay.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_dialogrepay_agreement:
                break;
            case R.id.btn_dialogrepay_repaynow:
                //点击立即支付或者创建还款计划
                //先判断是否勾选了同意用户协议选项
                if (cbDialogrepayAgree.isChecked()){
                    //判断是创建立即支付还是创建还款计划
                    if (RepayNow.equals(btnDialogrepayRepaynow.getText().toString())){
                        //立即支付
                        llDialogChoose.startAnimation(choose_out);
                        llDialogChoose.setVisibility(View.GONE);
                        llDialogDetail.startAnimation(detail_in);
                        llDialogDetail.setVisibility(View.VISIBLE);
                    }else {
                        //创建还款计划
                        Intent createrepayplan = new Intent(DialogChooseActivity.this,CreateRepayPlanActivity.class);
                        String s = tvDialogrepayCardmoney.getText().toString();
                        String replaceFirst = s.replaceFirst("¥", "");
                        createrepayplan.putExtra("repaymoney",replaceFirst);
                        createrepayplan.putExtra("repaycardinfo",repaycardinfo);
                        startActivity(createrepayplan);
                    }
                }else {
                    AppUtils.showToast(DialogChooseActivity.this,"请先知晓并同意用户协议选项");
                    return;
                }

                break;
            case R.id.ll_dialogdetail_cancel:
                llDialogDetail.startAnimation(detail_out);
                llDialogDetail.setVisibility(View.GONE);
                llDialogChoose.startAnimation(choose_in);
                llDialogChoose.setVisibility(View.VISIBLE);
                break;
            case R.id.ll_dialogdetail_repaybank:
                llDialogDetail.startAnimation(detail_out);
                llDialogDetail.setVisibility(View.GONE);
                llDiaglogChoosebankcard.startAnimation(detail_in);
                llDiaglogChoosebankcard.setVisibility(View.VISIBLE);
                //listview设置脚布局
                setFooterForListview();
                break;
            case R.id.btn_dialogdetail_pay:
                break;
            case R.id.ll_dialog_choosebankcard_close:
                llDiaglogChoosebankcard.startAnimation(detail_out);
                llDiaglogChoosebankcard.setVisibility(View.GONE);
                llDialogDetail.startAnimation(detail_in);
                llDialogDetail.setVisibility(View.VISIBLE);
                break;
        }
    }

}
