package fragment;

import android.view.View;

import base.BaseFragment;
import butterknife.Unbinder;

/**
 * Created by mayn on 2018/6/19.
 * 投资Fragment
 */

public class InvestFragment extends BaseFragment {
    private Unbinder mUnbinder;
    @Override
    protected View initView() {
        return null;
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //mUnbinder.unbind();
    }
}
