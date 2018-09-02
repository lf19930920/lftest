package activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.ocr.sdk.OCR;
import com.baidu.ocr.sdk.OnResultListener;
import com.baidu.ocr.sdk.exception.OCRError;
import com.baidu.ocr.sdk.model.AccessToken;
import com.baidu.ocr.sdk.model.BankCardParams;
import com.baidu.ocr.sdk.model.BankCardResult;
import com.baidu.ocr.ui.camera.CameraActivity;
import com.bumptech.glide.Glide;
import com.xykgj.tx.xinyongkaguanjia.R;

import java.io.File;

import base.BaseActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import utils.MyFileUtils;

/**
 * Created by mayn on 2018/7/23.
 * 卡片编辑Activity
 */

public class CardEditActivity extends BaseActivity {
    private static final int REQUEST_CODE_BANKCARD_CardEdit = 112;
    @BindView(R.id.ll_cardedit_back)
    LinearLayout llCardeditBack;
    @BindView(R.id.iv_cardedit_bankcardicon)
    ImageView ivCardeditBankcardicon;
    @BindView(R.id.tv_cardedit_cardnumber)
    TextView tvCardeditCardnumber;
    @BindView(R.id.btn_cardedit_save)
    Button btnCardeditSave;
    @BindView(R.id.tv_cardedit_againphoto)
    TextView tvCardeditAgainphoto;

    @Override
    public int initLayout() {
        return R.layout.activity_cardedit;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        initAccessTokenWithAkSk();
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

    @OnClick({R.id.ll_cardedit_back, R.id.btn_cardedit_save, R.id.tv_cardedit_againphoto})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_cardedit_back:
                finish();
                break;
            case R.id.btn_cardedit_save:
                break;
            case R.id.tv_cardedit_againphoto:
                Intent intent = new Intent(CardEditActivity.this, CameraActivity.class);
                intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH,
                        MyFileUtils.getSaveFile(getApplication()).getAbsolutePath());
                intent.putExtra(CameraActivity.KEY_CONTENT_TYPE,
                        CameraActivity.CONTENT_TYPE_BANK_CARD);
                startActivityForResult(intent, REQUEST_CODE_BANKCARD_CardEdit);
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 识别成功回调，银行卡识别
        if (requestCode == REQUEST_CODE_BANKCARD_CardEdit && resultCode == Activity.RESULT_OK) {
            RecBancard(CardEditActivity.this, MyFileUtils.getSaveFile(getApplicationContext()).getAbsolutePath());
        }

    }

    private void RecBancard(final Context context, final String absolutePath) {
        BankCardParams param = new BankCardParams();
        param.setImageFile(new File(absolutePath));
        OCR.getInstance(context).recognizeBankCard(param, new OnResultListener<BankCardResult>() {
            @Override
            public void onResult(BankCardResult result) {
                String res = String.format("卡号：%s\n类型：%s\n发卡行：%s",
                        result.getBankCardNumber(),
                        result.getBankCardType().name(),
                        result.getBankName());
                Glide.with(context).load(new File(absolutePath)).into(ivCardeditBankcardicon);
                tvCardeditCardnumber.setText(result.getBankCardNumber());
            }

            @Override
            public void onError(OCRError error) {

            }
        });
    }
}
