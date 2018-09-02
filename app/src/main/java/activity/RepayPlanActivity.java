package activity;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.xykgj.tx.xinyongkaguanjia.R;

import java.util.ArrayList;
import java.util.List;

import adapter.RepayPlanAdapter;
import base.BaseActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by mayn on 2018/7/30.
 * 还款计划Activity
 */

public class RepayPlanActivity extends BaseActivity {
    @BindView(R.id.ll_repayplan_back)
    LinearLayout llRepayplanBack;
    @BindView(R.id.lv_repayplan)
    ListView lvRepayplan;
    @BindView(R.id.ll_repayplan_pay)
    LinearLayout llRepayplanPay;
    private List<String> data = new ArrayList<>();
    @Override
    public int initLayout() {
        return R.layout.activity_repayplan;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        initListViewFooter();
        setData();
    }



    /**
     * 为listview添加脚布局
     */
    private void initListViewFooter() {
        View footer = LayoutInflater.from(RepayPlanActivity.this).inflate(R.layout.lv_footer_repayplan,null,false);
        lvRepayplan.addFooterView(footer,null,false);
    }

    private void setData() {
        for (int i = 0; i < 40; i++) {
            data.add("1");
        }
        lvRepayplan.setAdapter(new RepayPlanAdapter(data,RepayPlanActivity.this));
    }
    @OnClick({R.id.ll_repayplan_back, R.id.ll_repayplan_pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_repayplan_back:
                finish();
                break;
            case R.id.ll_repayplan_pay:
                goToActivity(RepayPlanActivity.this,RepayProgressActivity.class);
                break;
        }
    }
}
