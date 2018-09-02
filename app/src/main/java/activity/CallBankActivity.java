package activity;

import android.view.View;
import android.widget.LinearLayout;

import com.xykgj.tx.xinyongkaguanjia.R;

import base.BaseActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by mayn on 2018/7/10.
 * 联系银行Activity
 */

public class CallBankActivity extends BaseActivity {
    @BindView(R.id.ll_callbank_back)
    LinearLayout llCallbankBack;

    @Override
    public int initLayout() {
        return R.layout.activity_callbank;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.ll_callbank_back)
    public void onViewClicked(View clickView) {
        switch (clickView.getId()){
            case R.id.ll_callbank_back:
                finish();
                break;
        }
    }
}
