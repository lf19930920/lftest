package base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by mayn on 2018/6/19.
 * Fragment的基类
 */

public abstract class BaseFragment extends Fragment {
    public BaseActivity mActivity;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (BaseActivity) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = initView();
        initData();
        return view;
    }

    /**
     * 初始布局
     * @return
     */
    protected abstract View initView();

    /**
     * 加载数据
     */
    protected abstract void initData();

    /**
     * 从Fragment中跳转到指定Activity
     * @param context
     * @param clazz
     */
    protected void jumpToActivity(Context context, Class<? extends Activity> clazz){
        Intent intent = new Intent(context,clazz);
        startActivity(intent);
    }
}
