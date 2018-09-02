package activity;

import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.xykgj.tx.xinyongkaguanjia.R;

import base.BaseActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import view.ListViewForScrollView;

/**
 * Created by mayn on 2018/7/12.
 * 导入账单Activity
 */

public class InputBillActivity extends BaseActivity {
    @BindView(R.id.ll_inputbill_back)
    LinearLayout llInputbillBack;
    @BindView(R.id.lv_mailinput)
    ListViewForScrollView lvMailinput;
    @BindView(R.id.ib_inputbill_othermail)
    ImageButton ibInputbillOthermail;
    @BindView(R.id.ll_inputbill_smsinput)
    LinearLayout llInputbillSmsinput;
    @BindView(R.id.lv_ebankinput)
    ListViewForScrollView lvEbankinput;
    @BindView(R.id.ib_inputbill_otherbank)
    ImageButton ibInputbillOtherbank;
    @BindView(R.id.ll_handinputbill)
    LinearLayout llHandinputbill;
    @BindView(R.id.sv_inputbill)
    ScrollView svInputbill;

    @Override
    public int initLayout() {
        return R.layout.activity_inputbill;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.ll_inputbill_back, R.id.ib_inputbill_othermail, R.id.ll_inputbill_smsinput,
            R.id.ib_inputbill_otherbank, R.id.ll_handinputbill})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_inputbill_back:
                finish();
                break;
            case R.id.ib_inputbill_othermail:
                goToActivity(InputBillActivity.this,ChooseMailActivity.class);
                break;
            case R.id.ll_inputbill_smsinput:
                break;
            case R.id.ib_inputbill_otherbank:
                goToActivity(InputBillActivity.this,ChooseEBankActivity.class);
                break;
            case R.id.ll_handinputbill:
                break;
        }
    }
}
