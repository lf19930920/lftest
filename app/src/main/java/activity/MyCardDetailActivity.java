package activity;

import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.xykgj.tx.xinyongkaguanjia.R;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import adapter.CardDeatilVPAdapter;
import base.BaseActivity;
import base.BaseFragment;
import bean.CardDetailBean;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import evenbusbean.CardDetailEventBusBean;
import fragment.BankServiceFragment;
import fragment.BillDetailFragment;
import fragment.RepayHistroyFragment;
import network.NetWorkConfig;
import okhttp3.Call;
import utils.AppUtils;
import utils.MD5Utils;
import utils.SpUtils;
import utils.TimeUtils;
import view.CustomNumKeyView;

/**
 * Created by mayn on 2018/7/5.
 * 我的信用卡详情Activity
 */

public class MyCardDetailActivity extends BaseActivity implements CustomNumKeyView.CallBack{
    private static final String TAG = MyCardDetailActivity.class.getSimpleName();
    @BindView(R.id.tv_mycarddetail_username)
    TextView tvMycarddetailUsername;
    @BindView(R.id.tv_mycarddetail_usercardnum)
    TextView tvMycarddetailUsercardnum;
    @BindView(R.id.iv_mycarddetail_cardsee)
    ImageView ivMycarddetailCardsee;
    @BindView(R.id.tv_mycarddetail_duetotime)
    TextView tvMycarddetailDuetotime;
    @BindView(R.id.tv_mycarddetail_moneytext)
    TextView tvMycarddetailMoneytext;
    @BindView(R.id.tv_mycarddetail_money)
    TextView tvMycarddetailMoney;
    @BindView(R.id.tv_mycarddetail_repayday)
    TextView tvMycarddetailRepayday;
    @BindView(R.id.tv_mycarddetail_chargeday)
    TextView tvMycarddetailChargeday;
    @BindView(R.id.tv_mycarddetail_interestfree)
    TextView tvMycarddetailInterestfree;
    @BindView(R.id.tv_mycarddetail_totalamount)
    TextView tvMycarddetailTotalamount;
    @BindView(R.id.ll_usercarddetail_back)
    LinearLayout llUsercarddetailBack;
    @BindView(R.id.tv_mycarddetail_title)
    TextView tvMycarddetailTitle;
    @BindView(R.id.tablayout_usercarddetail)
    TabLayout tablayoutUsercarddetail;
    @BindView(R.id.vp_usercarddetail)
    ViewPager vpUsercarddetail;
    @BindView(R.id.btn_usercarddetail_repaynow)
    Button btnUsercarddetailRepaynow;
    @BindView(R.id.tv_mycarddetail_minrepay_money)
    TextView tvMycarddetailMinrepayMoney;
    @BindView(R.id.ll_usercarddetail_mail)
    LinearLayout llUsercarddetailMail;
    @BindView(R.id.ll_usercarddetail_flag)
    LinearLayout llUsercarddetailFlag;
    @BindView(R.id.ll_usercarddetail_complete)
    LinearLayout llUsercarddetailComplete;
    @BindView(R.id.iv_mycarddetail_editcard)
    ImageView ivMycarddetaileditcard;
    private List<String> tabTitles = new ArrayList<>();//tabLayout的标题
    private List<BaseFragment> fragmentList = new ArrayList<>();//填充viewpager的Fragment集合
    private Bundle bundle;
    private String token;
    private int cardId;
    private EditText et_mycarddetailflag_money;
    private CardDetailBean.ResultBean result;

    @Override
    public int initLayout() {
        return R.layout.activity_usercarddetail;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initData() {
        cardId = getIntent().getIntExtra("cardId", 0);
        bundle = new Bundle();
        bundle.putInt("cardId", cardId);
        Log.i(TAG, "initData: cardId="+cardId);
        //token
        token = SpUtils.getString(MyCardDetailActivity.this, "token");

        //获取个人信用卡详情的数据
        getMyCardDetailData(cardId, token);
        //设置tabLayout的标题
        setTabLayoutTitles();
        //tabLayout与viewpager进行绑定
        tablayoutUsercarddetail.setupWithViewPager(vpUsercarddetail);
        //设置viewpager中的fragment
        initFragment();
        vpUsercarddetail.setAdapter(new CardDeatilVPAdapter(getSupportFragmentManager(), tabTitles, fragmentList));


    }

    /**
     * 获取个人信用卡详情的数据
     *
     * @param cardId
     *
     * @param token
     *
     */
    private void getMyCardDetailData(int cardId, String token) {
        //时间戳
        String time = TimeUtils.getTime();
        //获取sign
        String md5_sign = MD5Utils.getMD5WithSalt("card_id=" + cardId + "&timestamp=" + time + "&token=" + token, NetWorkConfig.SIGN);
        OkHttpUtils
                .get()
                .url(NetWorkConfig.BaseURL + NetWorkConfig.USER_UserCardDetail)
                .addParams("card_id", String.valueOf(cardId))
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
                                //解析我的信用卡详情json
                                parseMyCardDetailJson(response);
                            } else {
                                AppUtils.showToast(MyCardDetailActivity.this, (String) jsonObject.opt("msg"));
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    /**
     * 解析我的信用卡详情json
     *
     * @param response
     */
    private void parseMyCardDetailJson(String response) {
        Gson gson = new Gson();
        CardDetailBean cardDetailBean = gson.fromJson(response, CardDetailBean.class);
        result = cardDetailBean.getResult();
        tvMycarddetailTitle.setText(result.getBank_name());
        tvMycarddetailUsername.setText(result.getReal_name());
        //判断银行卡号码的显示状态
        if (TextUtils.isEmpty(result.getCard_no_mask())){
            tvMycarddetailUsercardnum.setText(result.getTail_no_mask());
            ivMycarddetaileditcard.setVisibility(View.VISIBLE);
            ivMycarddetailCardsee.setVisibility(View.GONE);
        }else {
            tvMycarddetailUsercardnum.setText(result.getCard_no_mask());
            ivMycarddetaileditcard.setVisibility(View.GONE);
            ivMycarddetailCardsee.setVisibility(View.VISIBLE);
        }

        tvMycarddetailMoney.setText(result.getUnrepay_amount());
        tvMycarddetailMinrepayMoney.setText("最低应还：" + result.getMin_repay_amount());
        tvMycarddetailInterestfree.setText("免息日：" + result.getFree_interest_days() + "天");
        tvMycarddetailRepayday.setText("还款日：" + result.getRepay_time());
        tvMycarddetailChargeday.setText("出账日：" + result.getBill_time());
        tvMycarddetailTotalamount.setText("总额度：" + result.getTotal_amount());
        tvMycarddetailDuetotime.setText(result.getRepay_remaining_days() + "天后到期");

    }


    /**
     * 设置tabLayout的标题
     */
    private void setTabLayoutTitles() {
        tabTitles.add("账单明细");
        tabTitles.add("还款记录");
        tabTitles.add("银行服务");
    }

    /**
     * 设置viewpager中的fragment
     */
    private void initFragment() {
        RepayHistroyFragment repayHistroyFragment = new RepayHistroyFragment();
        repayHistroyFragment.setArguments(bundle);
        fragmentList.add(new BillDetailFragment());
        fragmentList.add(repayHistroyFragment);
        fragmentList.add(new BankServiceFragment());

    }

    @OnClick({R.id.ll_usercarddetail_back, R.id.ll_usercarddetail_mail, R.id.ll_usercarddetail_flag,
            R.id.ll_usercarddetail_complete, R.id.btn_usercarddetail_repaynow,R.id.iv_mycarddetail_cardsee,
            R.id.iv_mycarddetail_editcard})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_usercarddetail_back:
                finish();
                break;
            case R.id.ll_usercarddetail_mail:
                break;
            case R.id.ll_usercarddetail_flag:
                //展示标记已还部分dialog
                showFlagDialog();
                break;
            case R.id.ll_usercarddetail_complete:
                //标记已还清
                getRepayOff();
                break;
            case R.id.btn_usercarddetail_repaynow:
                String tail_no = result.getTail_no();
                String substring = tail_no.substring(tail_no.length() - 4, tail_no.length());
                Intent intent = new Intent(MyCardDetailActivity.this,DialogChooseActivity.class);
                intent.putExtra("unrepayamout",result.getUnrepay_amount());
                intent.putExtra("repaycardinfo",result.getBank_name()+" ( "+substring+" ) "+result.getReal_name());
                startActivity(intent);
                break;
            case R.id.iv_mycarddetail_cardsee:
                showCardNumPopupWindow();
                break;
            case R.id.iv_mycarddetail_editcard:
                Intent buquan = new Intent(MyCardDetailActivity.this,CompletCardActivity.class);
                buquan.putExtra("cardtail",result.getTail_no());
                buquan.putExtra("creditcardId",cardId);
                startActivity(buquan);
                break;
        }
    }

    /**
     * 展示标记已还部分dialog
     */
    private void showFlagDialog() {
        final Dialog dialog = new Dialog(MyCardDetailActivity.this,R.style.EditIconDialogStyle);
        View view = LayoutInflater.from(MyCardDetailActivity.this).inflate(R.layout.dialog_flag,null,false);
        et_mycarddetailflag_money = (EditText) view.findViewById(R.id.et_mycarddetailflag_money);
        TextView tv_mycarddetailflag_complete = (TextView) view.findViewById(R.id.tv_mycarddetailflag_complete);
        CustomNumKeyView mycarddetail_flag = (CustomNumKeyView) view.findViewById(R.id.mycarddetail_flag);
        et_mycarddetailflag_money.setInputType(InputType.TYPE_NULL);
        tv_mycarddetailflag_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(et_mycarddetailflag_money.getText().toString())){
                    Toast.makeText(MyCardDetailActivity.this, "请输入金额", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    try {
                        double i = Double.valueOf(et_mycarddetailflag_money.getText().toString().trim()).doubleValue();
                        Log.i(TAG, "i="+i);
                        if (i>0){
                            //小数点后两位
                            BigDecimal bg = new BigDecimal(i);
                            double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                            Toast.makeText(MyCardDetailActivity.this, "完成提交", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            //标记已还部分
                            String s = String.valueOf(f1);
                            getRepayPart(s);
                        }else {
                            Toast.makeText(MyCardDetailActivity.this, "输入的金额必须大于0", Toast.LENGTH_SHORT).show();
                            et_mycarddetailflag_money.setText(null);
                            return;
                        }
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        Toast.makeText(MyCardDetailActivity.this, "输入的金额有误", Toast.LENGTH_SHORT).show();
                        et_mycarddetailflag_money.setText(null);
                        return;
                    }
                }
            }
        });
        mycarddetail_flag.setOnCallBack(this);
        dialog.setContentView(view);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = -1;
        window.setGravity(Gravity.BOTTOM);
        window.setAttributes(params);
        dialog.show();
    }

    /**
     * 标记已还部分
     * @param s
     */
    private void getRepayPart(String s) {
        //获取时间戳
        String time = TimeUtils.getTime();
        //拼接sign
        String md5_sign = MD5Utils.getMD5WithSalt("bill_id=" + "1" +"&repayed_amount=" + s + "&timestamp=" + time + "&token=" + token,
                NetWorkConfig.SIGN);
        OkHttpUtils
                .get()
                .url(NetWorkConfig.BaseURL+NetWorkConfig.USER_MarkRepayedAmount)
                .addParams("repayed_amount",s)
                .addParams("bill_id","1")
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
                                AppUtils.showToast(MyCardDetailActivity.this, (String) jsonObject.opt("msg"));
                            }else {
                                AppUtils.showToast(MyCardDetailActivity.this, (String) jsonObject.opt("msg"));
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

    }

    /**
     * 标记已还清
     */
    private void getRepayOff() {
        //获取时间戳
        String time = TimeUtils.getTime();
        //拼接sign
        String md5_sign = MD5Utils.getMD5WithSalt("&bill_id=" + "1" + "&timestamp=" + time + "&token=" + token, NetWorkConfig.SIGN);
        OkHttpUtils
                .get()
                .url(NetWorkConfig.BaseURL+NetWorkConfig.USER_MarkRepayOff)
                .addParams("bill_id","1")
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
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }


    /**
     * 展示银行卡号操作的Popupwindow
     */
    private void showCardNumPopupWindow(){
        final PopupWindow popupWindow = new PopupWindow(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        View view = LayoutInflater.from(MyCardDetailActivity.this).inflate(R.layout.popupwindow_carddetail,null,false);
        TextView tv_carddetail_popup_edit =(TextView) view.findViewById(R.id.tv_carddetail_popup_edit);
        TextView tv_carddetail_popup_copy =(TextView) view.findViewById(R.id.tv_carddetail_popup_copy);
        final TextView tv_carddetail_popup_show =(TextView) view.findViewById(R.id.tv_carddetail_popup_show);
        tv_carddetail_popup_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                Intent buquan = new Intent(MyCardDetailActivity.this,CompletCardActivity.class);
                buquan.putExtra("cardtail",result.getTail_no());
                buquan.putExtra("creditcardId",cardId);
                startActivity(buquan);
            }
        });
        tv_carddetail_popup_copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取剪贴板管理器：
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                // 创建普通字符型ClipData
                ClipData mClipData = ClipData.newPlainText(null,result.getCard_no());
                // 将ClipData内容放到系统剪贴板里。
                cm.setPrimaryClip(mClipData);
            }
        });
        tv_carddetail_popup_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("显示".equals(tv_carddetail_popup_show.getText().toString())
                        && tvMycarddetailUsercardnum.getText().toString().equals(result.getCard_no_mask())){
                    tvMycarddetailUsercardnum.setText(result.getCard_no());
                    tv_carddetail_popup_show.setText("隐藏");
                }else if ("隐藏".equals(tv_carddetail_popup_show.getText().toString())
                        &&tvMycarddetailUsercardnum.getText().toString().equals(result.getCard_no())){
                    tvMycarddetailUsercardnum.setText(result.getCard_no_mask());
                    tv_carddetail_popup_show.setText("显示");
                }else {
                    AppUtils.showToast(MyCardDetailActivity.this,"您还没有补全信用卡信息");
                    return;
                }
            }
        });
        popupWindow.setContentView(view);
        popupWindow.setFocusable(false);//设置popupwindow不获取焦点，点击外部的控件时可以直接响应事件，不用点击两次
        //给pupupwindow设置一个背景 透明的,以便点击popupwindow外边时候可以实现消失
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setOutsideTouchable(true);//设置点击外部消失
        popupWindow.setTouchable(true);// 设置PopupWindow是否能响应点击事件
        popupWindow.showAsDropDown(tvMycarddetailUsername,0,0);
    }

    @Override
    public void clickNum(String num) {
        if (".".equals(num)){
            if (TextUtils.isEmpty(et_mycarddetailflag_money.getText().toString().trim())){
                return;
            }else {
                if (et_mycarddetailflag_money.getText().toString().trim().contains(".")){
                    return;
                }else {
                    et_mycarddetailflag_money.append(num);
                }
            }
        }else {
            String str = et_mycarddetailflag_money.getText().toString();
            if (str.contains(".")){
                Log.i(TAG, "clickNum: str="+str);
                String substring = str.substring(str.indexOf(".") + 1, str.length());
                Log.i(TAG, "clickNum: substring="+substring);
                if (substring.length()>=2){
                    return;
                }else {
                    et_mycarddetailflag_money.append(num);
                }
            }else {
                et_mycarddetailflag_money.append(num);
            }
        }
    }

    @Override
    public void deleteNum() {
        int last = et_mycarddetailflag_money.getText().length();
        if (last > 0) {
            //删除最后一位
            et_mycarddetailflag_money.getText().delete(last - 1, last);
        }
    }

    //订阅方法，当接收到事件的时候，会调用该方法
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(CardDetailEventBusBean messageEvent){
        //获取个人信用卡详情的数据
        getMyCardDetailData(cardId, token);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
