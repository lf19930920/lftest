package activity;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.xykgj.tx.xinyongkaguanjia.R;

import java.util.ArrayList;
import java.util.List;

import adapter.RepayProgressAdapter;
import base.BaseActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by mayn on 2018/7/30.
 * 还款进度Activity
 */

public class RepayProgressActivity extends BaseActivity {
    @BindView(R.id.ll_repayprogress_back)
    LinearLayout llRepayprogressBack;
    @BindView(R.id.lv_repayprogress)
    ListView lvRepayprogress;
    @BindView(R.id.ll_repayprogress_end)
    LinearLayout llRepayprogressEnd;
    private List<String> datas = new ArrayList<>();
    @Override
    public int initLayout() {
        return R.layout.activity_repayprogress;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        initFooter();
        setData();
    }


    /**
     * 添加脚布局
     */
    private void initFooter() {
        View view = LayoutInflater.from(RepayProgressActivity.this).inflate(R.layout.lv_footer_repayprogress,null,false);
        lvRepayprogress.addFooterView(view,null,false);
    }

    private void setData() {
        for (int i = 0; i < 40; i++) {
            datas.add("1");
        }
        lvRepayprogress.setAdapter(new RepayProgressAdapter(datas,RepayProgressActivity.this));
    }

    @OnClick({R.id.ll_repayprogress_back, R.id.ll_repayprogress_end})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_repayprogress_back:
                finish();
                break;
            case R.id.ll_repayprogress_end:
                // TODO
                break;
        }
    }
}
