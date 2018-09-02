package activity;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;

import com.xykgj.tx.xinyongkaguanjia.R;

import java.util.ArrayList;
import java.util.List;

import adapter.AllCreditAdapter;
import base.BaseActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import view.MyRadioButton;

/**
 * Created by mayn on 2018/6/29.
 * 全部信用卡Activity
 */

public class AllCreditCardActivity extends BaseActivity {
    @BindView(R.id.lv_allcreditcard)
    ListView lvAllcreditcard;
    @BindView(R.id.ll_allcreditcard_back)
    LinearLayout llAllcreditcardBack;
    @BindView(R.id.rg_allcreditcard)
    RadioGroup rgAllcreditcard;
    @BindView(R.id.rb_allcreditcard_allbank)
    MyRadioButton rbAllcreditcardAllbank;
    @BindView(R.id.rb_allcreditcard_alllevel)
    MyRadioButton rbAllcreditcardAlllevel;
    @BindView(R.id.rb_allcreditcard_alluse)
    MyRadioButton rbAllcreditcardAlluse;
    private List<String> allCreditCardList = new ArrayList<>();

    @Override
    public int initLayout() {
        return R.layout.activity_allcreditcard;
    }
    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        for (int i = 0; i < 10; i++) {
            allCreditCardList.add("1");
        }
        lvAllcreditcard.setAdapter(new AllCreditAdapter(allCreditCardList, AllCreditCardActivity.this));
    }

    @OnClick({R.id.ll_allcreditcard_back, R.id.rb_allcreditcard_allbank,
            R.id.rb_allcreditcard_alllevel, R.id.rb_allcreditcard_alluse})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_allcreditcard_back:
                finish();
                break;
            case R.id.rb_allcreditcard_allbank:

                break;
            case R.id.rb_allcreditcard_alllevel:

                break;
            case R.id.rb_allcreditcard_alluse:

                break;
        }
    }
}
