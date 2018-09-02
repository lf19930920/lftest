package activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.baidu.ocr.sdk.OCR;
import com.baidu.ocr.sdk.OnResultListener;
import com.baidu.ocr.sdk.exception.OCRError;
import com.baidu.ocr.sdk.model.AccessToken;
import com.baidu.ocr.sdk.model.BankCardParams;
import com.baidu.ocr.sdk.model.BankCardResult;
import com.baidu.ocr.ui.camera.CameraActivity;
import com.xykgj.tx.xinyongkaguanjia.R;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

import base.BaseActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import evenbusbean.CardDetailEventBusBean;
import network.NetWorkConfig;
import okhttp3.Call;
import utils.AppUtils;
import utils.MD5Utils;
import utils.MyFileUtils;
import utils.SpUtils;
import utils.TimeUtils;

/**
 * Created by mayn on 2018/6/22.
 * 补全信用卡信息Activity
 */

public class CompletCardActivity extends BaseActivity {
    private static final int REQUEST_CODE_BANKCARD = 111;
    private static final String TAG = CompletCardActivity.class.getSimpleName();
    @BindView(R.id.ll_completcard_back)
    LinearLayout llCompletcardBack;
    @BindView(R.id.et_completcard_banknumber)
    EditText etCompletcardBanknumber;
    @BindView(R.id.iv_completcard_photo)
    ImageView ivCompletcardPhoto;
    @BindView(R.id.btn_completcard_save)
    Button btnCompletcardSave;
    private String cardtail;
    private int creditcardId;

    @Override
    public int initLayout() {
        return R.layout.activity_completcard;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        initAccessTokenWithAkSk();
        cardtail = getIntent().getStringExtra("cardtail");
        creditcardId = getIntent().getIntExtra("creditcardId", 0);
        etCompletcardBanknumber.setHint("请输入尾号为"+ cardtail +"的银行卡");
    }

    private void initAccessTokenWithAkSk() {
        OCR.getInstance(this).initAccessToken(new OnResultListener<AccessToken>() {
            @Override
            public void onResult(AccessToken accessToken) {
                String token = accessToken.getAccessToken();
            }

            @Override
            public void onError(OCRError error) {
                error.printStackTrace();
            }
        }, getApplicationContext());
    }


    @OnClick({R.id.ll_completcard_back, R.id.iv_completcard_photo, R.id.btn_completcard_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_completcard_back:
                finish();
                break;
            case R.id.iv_completcard_photo:
                Intent intent = new Intent(CompletCardActivity.this, CameraActivity.class);
                intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH,
                        MyFileUtils.getSaveFile(getApplication()).getAbsolutePath());
                intent.putExtra(CameraActivity.KEY_CONTENT_TYPE,
                        CameraActivity.CONTENT_TYPE_BANK_CARD);
                startActivityForResult(intent, REQUEST_CODE_BANKCARD);
                break;
            case R.id.btn_completcard_save:
                if (!TextUtils.isEmpty(etCompletcardBanknumber.getText().toString().trim())){
                    //保存的操作
                    //补全更新信用卡信息
                    completionCard();
                }else {
                    AppUtils.showToast(CompletCardActivity.this,"银行卡号不能为空");
                    return;
                }
                break;
        }
    }

    /**
     * 补全更新信用卡信息
     */
    private void completionCard() {
        String token = SpUtils.getString(CompletCardActivity.this, "token");
        String time = TimeUtils.getTime();
        String md5_sign = MD5Utils.getMD5WithSalt("card_id=" + creditcardId + "&card_no=" + etCompletcardBanknumber.getText().toString().trim() +
                "&timestamp=" + time + "&token=" + token, NetWorkConfig.SIGN);
        OkHttpUtils
                .get().url(NetWorkConfig.BaseURL+NetWorkConfig.USER_UserCardEdit)
                .addParams("card_id",String.valueOf(creditcardId))
                .addParams("card_no",etCompletcardBanknumber.getText().toString().trim())
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
                                EventBus.getDefault().post(new CardDetailEventBusBean());
                                AppUtils.showToast(CompletCardActivity.this,"更新成功");
                                finish();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 识别成功回调，银行卡识别
        if (requestCode == REQUEST_CODE_BANKCARD && resultCode == Activity.RESULT_OK) {
            RecBancard(CompletCardActivity.this, MyFileUtils.getSaveFile(getApplicationContext()).getAbsolutePath());
        }

    }
    private void RecBancard(Context ctx, String filePath){
        BankCardParams param = new BankCardParams();
        param.setImageFile(new File(filePath));
        OCR.getInstance(ctx).recognizeBankCard(param, new OnResultListener<BankCardResult>() {
            @Override
            public void onResult(BankCardResult result) {
                String res = String.format("卡号：%s\n类型：%s\n发卡行：%s",
                        result.getBankCardNumber(),
                        result.getBankCardType().name(),
                        result.getBankName());
                etCompletcardBanknumber.setText(result.getBankCardNumber());
            }

            @Override
            public void onError(OCRError error) {

            }
        });
    }
}
