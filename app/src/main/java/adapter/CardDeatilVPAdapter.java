package adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import base.BaseFragment;

/**
 * Created by mayn on 2018/7/9.
 * 我的信用卡详情-- viewpager -- adapter
 */

public class CardDeatilVPAdapter extends FragmentStatePagerAdapter {
    private List<String> tabTitles;
    private List<BaseFragment> fragmentList;
    public CardDeatilVPAdapter(FragmentManager fm, List<String> tabTitles, List<BaseFragment> fragmentList) {
        super(fm);
        this.tabTitles = tabTitles;
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles.get(position);
    }
}
