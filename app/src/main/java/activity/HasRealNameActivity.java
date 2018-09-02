package activity;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xykgj.tx.xinyongkaguanjia.R;

import base.BaseActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by mayn on 2018/6/22.
 * 已实名认证Activity
 */

public class HasRealNameActivity extends BaseActivity {
    @BindView(R.id.tv_hasrealname_name)
    TextView tvHasrealnameName;
    @BindView(R.id.tv_hasrealname_idnumber)
    TextView tvHasrealnameIdnumber;
    @BindView(R.id.ll_hasrealname_back)
    LinearLayout llHasrealnameBack;

    @Override
    public int initLayout() {
        return R.layout.activity_hasrealname;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.ll_hasrealname_back)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_hasrealname_back:
                finish();
                break;
        }
    }

}
