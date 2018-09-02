package activity;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xykgj.tx.xinyongkaguanjia.R;

import base.BaseActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import utils.AppUtils;
import utils.BankInfoUtils;

/**
 * Created by mayn on 2018/6/20.
 * 添加银行卡Activity
 */

public class AddBankCardActivity extends BaseActivity {
    @BindView(R.id.et_addcard_cardnumber)
    EditText etAddcardCardnumber;
    @BindView(R.id.iv_addcard_close)
    ImageView ivAddcardPhoto;
    @BindView(R.id.et_addcard_userphone)
    EditText etAddcardUserphone;
    @BindView(R.id.tv_addcard_supportbank)
    TextView tvAddcardSupportbank;
    @BindView(R.id.btn_addcard_next)
    Button btnAddcardNext;
    @BindView(R.id.ll_addbankcard_back)
    LinearLayout llAddbankcardBack;
    @BindView(R.id.et_addbankcard_name)
    EditText etAddbankcardName;
    @BindView(R.id.et_addbankcard_idcard)
    EditText etAddbankcardIdcard;
    @BindView(R.id.tv_addcard_belong)
    TextView tvAddcardBelong;

    @Override
    public int initLayout() {
        return R.layout.activity_addbankcard;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        etAddcardCardnumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // 在输入数据时监听
                int input_lengh = etAddcardCardnumber.getText().toString().length();
                if (input_lengh >= 6) {
                    String bankcard_bin = etAddcardCardnumber.getText().toString();
                    char[] cardNumber = bankcard_bin.toCharArray();
                    String name = BankInfoUtils.getNameOfBank(cardNumber, 0);// 获取银行卡的信息
                    tvAddcardBelong.setText(name);
                } else {
                    tvAddcardBelong.setText(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @OnClick({R.id.ll_addbankcard_back, R.id.iv_addcard_close, R.id.tv_addcard_supportbank, R.id.btn_addcard_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_addbankcard_back:
                finish();
                break;
            case R.id.iv_addcard_close:
                etAddcardCardnumber.setText(null);
                tvAddcardBelong.setText("");
                break;
            case R.id.tv_addcard_supportbank:
                break;
            case R.id.btn_addcard_next:
                //下一步

                break;
        }
    }

    /**
     * 点击下一步
     *
     * @param cardnumber 银行卡号
     * @param userphone  银行预留电话
     */
    private void goToNext(String cardnumber, String userphone) {
        if (!TextUtils.isEmpty(cardnumber) && !TextUtils.isEmpty(userphone)) {
            if (AppUtils.isChinaPhoneTrue(userphone)) {
                //手机号码格式正确
            } else {
                AppUtils.showToast(AddBankCardActivity.this, "手机号码格式不正确，请您核对");
                return;
            }
        } else {
            AppUtils.showToast(AddBankCardActivity.this, "银行卡号、银行预留电话不能为空");
            return;
        }
    }

}
