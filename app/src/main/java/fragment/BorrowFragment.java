package fragment;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.xykgj.tx.xinyongkaguanjia.R;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import base.BaseFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by mayn on 2018/6/19.
 * 借钱Fragment
 */

public class BorrowFragment extends BaseFragment implements TabLayout.OnTabSelectedListener{
    @BindView(R.id.ll_borrow_login)
    LinearLayout llBorrowLogin;
    @BindView(R.id.iv_borrow_message)
    ImageView ivBorrowMessage;
    @BindView(R.id.banner_borrow)
    Banner bannerBorrow;
    @BindView(R.id.tablayout_fg_borrow)
    TabLayout tablayoutFgBorrow;
    @BindView(R.id.lv_fg_borrow)
    ListView lvFgBorrow;
    Unbinder mUnbinder;
    private int[] tabNormalIcons = {R.mipmap.borrow_tuijian_true,R.mipmap.borrow_all_false};
    private int[] tabPressIcons = {R.mipmap.borrow_tuijian_true,R.mipmap.borrow_all_false};
    private List<String> titles;

    @Override
    protected View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_borrow, null);
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initData() {
        titles = new ArrayList<>();
        titles.add("推荐贷款");
        titles.add("全部贷款");
        tablayoutFgBorrow.addOnTabSelectedListener(this);
        //setupTabIcons();
    }

    @OnClick({R.id.ll_borrow_login, R.id.iv_borrow_message})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_borrow_login:
                break;
            case R.id.iv_borrow_message:
                break;
        }
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        changeTabSelect(tab);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        changeTabNormal(tab);
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    /**
     * tab选中状态下的改变
     * @param tab
     */
    private void changeTabSelect(TabLayout.Tab tab) {
        View view = tab.getCustomView();
        ImageView img_title = (ImageView) view.findViewById(R.id.img_title);
        TextView txt_title = (TextView) view.findViewById(R.id.txt_title);
        txt_title.setTextColor(Color.YELLOW);
        if (txt_title.getText().toString().equals("推荐贷款")) {
            img_title.setImageResource(R.mipmap.borrow_tuijian_true);
        } else if (txt_title.getText().toString().equals("全部贷款")) {
            img_title.setImageResource(R.mipmap.borrow_all_false);
        }
    }

    /**
     * tab正常状态下的改变
     * @param tab
     */
    private void changeTabNormal(TabLayout.Tab tab) {
        View view = tab.getCustomView();
        ImageView img_title = (ImageView) view.findViewById(R.id.img_title);
        TextView txt_title = (TextView) view.findViewById(R.id.txt_title);
        txt_title.setTextColor(Color.WHITE);
        if (txt_title.getText().toString().equals("推荐贷款")) {
            img_title.setImageResource(R.mipmap.borrow_tuijian_true);
        } else if (txt_title.getText().toString().equals("全部贷款")) {
            img_title.setImageResource(R.mipmap.borrow_all_false);
        }
    }

    /**
     * 为Tab设置图标和icon
     */
    private void setupTabIcons() {
        tablayoutFgBorrow.getTabAt(0).setCustomView(getTabView(0));
        tablayoutFgBorrow.getTabAt(1).setCustomView(getTabView(1));
    }

    /**
     * 改变tablayout的图片和字体
     * @param
     * @return
     */
    public View getTabView(int position) {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.tab_item, null);
        TextView txt_title = (TextView) view.findViewById(R.id.txt_title);
        txt_title.setText(titles.get(position));
        ImageView img_title = (ImageView) view.findViewById(R.id.img_title);
        img_title.setImageResource(tabNormalIcons[position]);
        if (position == 0) {
            txt_title.setTextColor(Color.YELLOW);
            img_title.setImageResource(tabPressIcons[position]);
        } else {
            txt_title.setTextColor(Color.BLACK);
            img_title.setImageResource(tabNormalIcons[position]);
        }
        return view;
    }

    @Override
    public void onDestroyView() {
        if (mUnbinder != null){
            mUnbinder.unbind();
        }
        super.onDestroyView();
    }
}
