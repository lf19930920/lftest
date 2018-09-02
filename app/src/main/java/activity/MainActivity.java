package activity;

import android.support.annotation.IdRes;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.xykgj.tx.xinyongkaguanjia.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import base.BaseActivity;
import base.BaseFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import evenbusbean.ExitLoginBean;
import fragment.BorrowFragment2;
import fragment.HomePageFragment;
import fragment.InvestFragment;
import fragment.UserInfoFragment;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {


    @BindView(R.id.fl_main_container)
    FrameLayout flMainContainer;
    @BindView(R.id.rb_item_homepage)
    RadioButton rbItemHomepage;
    @BindView(R.id.rb_item_invest)
    RadioButton rbItemInvest;
    @BindView(R.id.rb_item_borrow)
    RadioButton rbItemBorrow;
    @BindView(R.id.rb_item_user)
    RadioButton rbItemUser;
    @BindView(R.id.rg_main_grouop)
    RadioGroup rgMainGrouop;
    HomePageFragment homePageFragment;//主页
    InvestFragment investFragment;//投资
    BorrowFragment2 borrowFragment;//借钱
    UserInfoFragment userInfoFragment;//我的
    private BaseFragment currentFragment; //当前选中的fragmeng
    private int currentId; //当前选中的buttonID
    //存放fragment的集合
    private List<BaseFragment> fragments;

    @Override
    public int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        ButterKnife.bind(this);
        fragments = new ArrayList<>();
        rgMainGrouop.setOnCheckedChangeListener(this);
        homePageFragment = new HomePageFragment();
        investFragment = new InvestFragment();
        borrowFragment = new BorrowFragment2();
        userInfoFragment = new UserInfoFragment();
        fragments.add(homePageFragment);
        fragments.add(investFragment);
        fragments.add(borrowFragment);
        fragments.add(userInfoFragment);
        //开启事务,提交事务
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        for(int i=0;i<fragments.size();i++){
            transaction.add(R.id.fl_main_container, fragments.get(i));
            transaction.hide(fragments.get(i));
        }
        transaction.show(fragments.get(0));
        transaction.commit();
        currentFragment = fragments.get(0);
        currentId = R.id.rb_item_homepage;
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId) {
            case R.id.rb_item_homepage:
                changeFragment(checkedId, fragments.get(0));
                break;
            case R.id.rb_item_invest:
                changeFragment(checkedId, fragments.get(1));
                break;
            case R.id.rb_item_borrow:
                changeFragment(checkedId, fragments.get(2));
                break;
            case R.id.rb_item_user:
                changeFragment(checkedId, fragments.get(3));
                break;
            default:
                break;
        }
    }

    public void MychangeFragment() {
        if (currentId == R.id.rb_item_user || currentFragment == fragments.get(3)) {
            return;
        }
        //开启事务
        getSupportFragmentManager().beginTransaction().hide(currentFragment)
                .show(fragments.get(3))
                .commitAllowingStateLoss();
        currentId = R.id.rb_item_user;
        //强制性改变选中的状态
        rbItemHomepage.setChecked(false);
        rbItemUser.setChecked(true);
        currentFragment = fragments.get(3);
    }

    public void changeFragment(int checkedId, BaseFragment showFragment) {
        if (currentId == checkedId || currentFragment == showFragment) {
            return;
        }
        //开启事务
        getSupportFragmentManager().beginTransaction().hide(currentFragment)
                .show(showFragment)
                .commitAllowingStateLoss();
        currentId = checkedId;
        currentFragment = showFragment;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(ExitLoginBean exitLoginBean){
        finish();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
