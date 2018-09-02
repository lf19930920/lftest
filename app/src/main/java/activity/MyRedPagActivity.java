package activity;

import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.xykgj.tx.xinyongkaguanjia.R;
import com.youth.banner.Banner;

import java.util.ArrayList;

import base.BaseActivity;
import base.BaseFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fragment.MyRedPacBorrowFragment;
import fragment.MyRedPacInvestFragment;
import fragment.MyRedPacRepayFragment;

/**
 * Created by mayn on 2018/7/2.
 * 我的红包Activity
 */

public class MyRedPagActivity extends BaseActivity{
    private static final String TAG = MyRedPagActivity.class.getSimpleName();
    @BindView(R.id.ll_myredpac_back)
    LinearLayout llMyredpacBack;
    @BindView(R.id.rb_myredpag_repay)
    RadioButton rbMyredpagRepay;
    @BindView(R.id.rb_myredpag_invest)
    RadioButton rbMyredpagInvest;
    @BindView(R.id.rb_myredpag_borrow)
    RadioButton rbMyredpagBorrow;
    @BindView(R.id.rg_myredpag)
    RadioGroup rgMyredpag;
    @BindView(R.id.banner_myredpag)
    Banner bannerMyredpag;
    @BindView(R.id.fl_myredpag_container)
    FrameLayout frameLayout;

    private MyRedPacRepayFragment repayFragment;
    private MyRedPacInvestFragment investFragment;
    private MyRedPacBorrowFragment borrowFragment;
    private FragmentTransaction transaction;
    private int currentIndex = 0;
    private ArrayList<BaseFragment> fragmentArrayList;
    private BaseFragment mCurrentFrgment;
    @Override
    public int initLayout() {
        return R.layout.activity_myredpag;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        //默认选中还款金
        rgMyredpag.check(R.id.rb_myredpag_repay);
        //初始化fragment
        initFragment();
    }

    /**
     * 初始化fragment
     */
    private void initFragment() {
        fragmentArrayList = new ArrayList(3);
        fragmentArrayList.add(new MyRedPacRepayFragment());
        fragmentArrayList.add(new MyRedPacInvestFragment());
        fragmentArrayList.add(new MyRedPacBorrowFragment());
        rbMyredpagRepay.setTag(0);
        rbMyredpagInvest.setTag(1);
        rbMyredpagBorrow.setTag(2);
        changeTab(0);
    }

    @Override
    protected void initData() {
//        transaction = getSupportFragmentManager().beginTransaction();
//        if (repayFragment == null){
//            repayFragment = new MyRedPacRepayFragment();
//        }
//        transaction.replace(R.id.fl_myredpag_container,repayFragment).commit();
    }



    @OnClick({R.id.ll_myredpac_back,R.id.rb_myredpag_repay,R.id.rb_myredpag_invest,R.id.rb_myredpag_borrow})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.ll_myredpac_back:
                finish();
                break;
            case R.id.rb_myredpag_repay:
                changeTab((Integer) rbMyredpagRepay.getTag());
//                transaction.replace(R.id.fl_myredpag_container,repayFragment).commit();
                break;
            case R.id.rb_myredpag_invest:
                changeTab((Integer) rbMyredpagInvest.getTag());
//                if (investFragment == null){
//                    investFragment = new MyRedPacInvestFragment();
//                }
//                transaction.replace(R.id.fl_myredpag_container,investFragment).commit();
                break;
            case R.id.rb_myredpag_borrow:
                changeTab((Integer) rbMyredpagBorrow.getTag());
//                if (borrowFragment == null){
//                    borrowFragment = new MyRedPacBorrowFragment();
//                }
//                transaction.replace(R.id.fl_myredpag_container,borrowFragment).commit();
                break;
        }
    }
    private void changeTab(int index) {
        currentIndex = index;
        rbMyredpagRepay.setSelected(index == 0);
        rbMyredpagInvest.setSelected(index == 1);
        rbMyredpagBorrow.setSelected(index == 2);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        //判断当前的Fragment是否为空，不为空则隐藏
        if (null != mCurrentFrgment) {
            ft.hide(mCurrentFrgment);
        }
        //先根据Tag从FragmentTransaction事物获取之前添加的Fragment
        BaseFragment fragment = (BaseFragment) getSupportFragmentManager().findFragmentByTag(fragmentArrayList.get(currentIndex).getClass().getName());

        if (null == fragment) {
            //如fragment为空，则之前未添加此Fragment。便从集合中取出
            fragment = fragmentArrayList.get(index);
        }
        mCurrentFrgment = fragment;

        //判断此Fragment是否已经添加到FragmentTransaction事物中
        if (!fragment.isAdded()) {
            ft.add(R.id.fl_myredpag_container, fragment, fragment.getClass().getName());
        } else {
            ft.show(fragment);
        }
        ft.commit();
    }
}
