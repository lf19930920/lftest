package activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.xykgj.tx.xinyongkaguanjia.R;

import java.util.ArrayList;
import java.util.List;

import adapter.CreateRepayPlanAdapter;
import base.BaseActivity;
import bean.CreateRepayPlanBean;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import utils.AppUtils;

/**
 * Created by mayn on 2018/7/30.
 * 创建还款计划Activity
 */

public class CreateRepayPlanActivity extends BaseActivity {
    @BindView(R.id.ll_createrepayplan_back)
    LinearLayout llCreaterepayplanBack;
    @BindView(R.id.tv_createrepayplan_cardinfo)
    TextView tvCreaterepayplanCardinfo;
    @BindView(R.id.et_createrepayplan_repaymoney)
    EditText etCreaterepayplanRepaymoney;
    @BindView(R.id.lv_createrepayplan)
    ListView lvCreaterepayplan;
    @BindView(R.id.btn_createrepayplan)
    Button btnCreaterepayplan;
    private List<CreateRepayPlanBean> datas = new ArrayList<>();
    private CreateRepayPlanAdapter adapter;

    @Override
    public int initLayout() {
        return R.layout.activity_createrepayplan;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        String repaymoney = intent.getStringExtra("repaymoney");
        String repaycardinfo = intent.getStringExtra("repaycardinfo");
        tvCreaterepayplanCardinfo.setText(repaycardinfo);
        etCreaterepayplanRepaymoney.setText(repaymoney);
        for (int i = 0; i < 4; i++) {
            CreateRepayPlanBean bean = new CreateRepayPlanBean();
            bean.setCheck(false);
            datas.add(bean);
        }
        adapter = new CreateRepayPlanAdapter(datas, CreateRepayPlanActivity.this);
        lvCreaterepayplan.setAdapter(adapter);
        lvCreaterepayplan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                for (int i = 0; i < datas.size(); i++) {
                    datas.get(i).setCheck(false);
                }
                CreateRepayPlanBean bean = (CreateRepayPlanBean) adapter.getItem(position);
                bean.setCheck(true);
                datas.set(position,bean);
                adapter.notifyDataSetChanged();
            }
        });
    }

    @OnClick({R.id.ll_createrepayplan_back, R.id.btn_createrepayplan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_createrepayplan_back:
                finish();
                break;
            case R.id.btn_createrepayplan:
                CreateRepayPlanBean bean = null;
                for (int i = 0; i < datas.size(); i++) {
                    if (datas.get(i).isCheck()){
                        bean = datas.get(i);
                        break;
                    }else {
                        continue;
                    }
                }
                if (bean == null){
                    AppUtils.showToast(CreateRepayPlanActivity.this,"请您选择一项适合您的还款计划");
                    return;
                }else {
                    //跳转到还款计划界面
                    Intent intent = new Intent(CreateRepayPlanActivity.this,RepayPlanActivity.class);
                    startActivity(intent);
                }
                break;
        }
    }
}
