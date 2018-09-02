package fragment;

import android.view.View;
import android.widget.LinearLayout;

import com.xykgj.tx.xinyongkaguanjia.R;

import activity.CallBankActivity;
import activity.MapActivity;
import base.BaseFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by mayn on 2018/7/9.
 * 我的信用卡详情 -- 银行服务fragment
 */

public class BankServiceFragment extends BaseFragment {

    Unbinder unbinder;
    @BindView(R.id.ll_bankservice_callbank)
    LinearLayout llBankserviceCallbank;
    @BindView(R.id.ll_bankservice_banklocation)
    LinearLayout llBankserviceBanklocation;


    @Override
    protected View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_bankservice, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.ll_bankservice_callbank, R.id.ll_bankservice_banklocation})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_bankservice_callbank:
                //跳转到联系银行
                jumpToActivity(mActivity, CallBankActivity.class);
                break;
            case R.id.ll_bankservice_banklocation:
                //跳转到银行网点
                jumpToActivity(mActivity, MapActivity.class);
                break;
        }
    }
}
