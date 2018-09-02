package fragment;

import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.xykgj.tx.xinyongkaguanjia.R;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import activity.LoanListActivity;
import activity.LoginActivity;
import activity.MSGCenterActivity;
import adapter.BorrowCommendAdapter;
import base.BaseFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import utils.AppUtils;
import utils.SpUtils;
import view.MyRadioButton;

/**
 * Created by mayn on 2018/7/4.
 */

public class BorrowFragment2 extends BaseFragment implements AdapterView.OnItemClickListener{
    Unbinder mUnbinder;
    @BindView(R.id.ll_borrow2_login)
    LinearLayout llBorrow2Login;
    @BindView(R.id.iv_borrow2_message)
    ImageView ivBorrow2Message;
    @BindView(R.id.banner2_borrow)
    Banner banner2Borrow;
    @BindView(R.id.myrb_borrow_recom)
    MyRadioButton myrbBorrowRecom;
    @BindView(R.id.myrb_borrow_all)
    MyRadioButton myrbBorrowAll;
    @BindView(R.id.rg_fg2_borrow)
    RadioGroup rgFg2Borrow;
    @BindView(R.id.ll_tuijian)
    LinearLayout llTuijian;
    @BindView(R.id.ll_all)
    LinearLayout llAll;
    @BindView(R.id.lv_fg2_borrow)
    ListView lvFg2Borrow;
    @BindView(R.id.tv_borrow2_login)
    TextView tvBorrow2Login;


    private List<String> borrowComList = new ArrayList<>();

    @Override
    protected View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_borrow2, null);
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initData() {
        if (TextUtils.isEmpty(SpUtils.getString(mActivity,"userphone"))){
            tvBorrow2Login.setText("请登录");
        }else {
            String userphone = SpUtils.getString(mActivity,"userphone");
            String hiddenphone = userphone.substring(0, 3) + "****" + userphone.substring(7, userphone.length());
            tvBorrow2Login.setText(hiddenphone);
        }
        lvFg2Borrow.setOnItemClickListener(this);
        rgFg2Borrow.check(R.id.myrb_borrow_recom);
        llAll.setVisibility(View.INVISIBLE);
        for (int i = 0; i < 10; i++) {
            borrowComList.add("1");
        }
        lvFg2Borrow.setAdapter(new BorrowCommendAdapter(borrowComList, mActivity));

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        AppUtils.showToast(mActivity,"当前点击了="+position);
    }

    @OnClick({R.id.ll_borrow2_login, R.id.iv_borrow2_message, R.id.myrb_borrow_recom, R.id.myrb_borrow_all})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_borrow2_login:
                if (TextUtils.isEmpty(SpUtils.getString(mActivity,"token"))){
                    jumpToActivity(mActivity, LoginActivity.class);
                }else {
                    return;
                }
                break;
            case R.id.iv_borrow2_message:
                jumpToActivity(mActivity, MSGCenterActivity.class);
                break;
            case R.id.myrb_borrow_recom:
                llAll.setVisibility(View.INVISIBLE);
                llTuijian.setVisibility(View.VISIBLE);
                break;
            case R.id.myrb_borrow_all:
                llTuijian.setVisibility(View.INVISIBLE);
                llAll.setVisibility(View.VISIBLE);
                jumpToActivity(mActivity, LoanListActivity.class);
                break;
        }
    }



    @Override
    public void onStop() {
        super.onStop();
        rgFg2Borrow.check(R.id.myrb_borrow_recom);
        llTuijian.setVisibility(View.VISIBLE);
        llAll.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }


}
