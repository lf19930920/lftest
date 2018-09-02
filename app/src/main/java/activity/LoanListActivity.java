package activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.xykgj.tx.xinyongkaguanjia.R;

import java.util.ArrayList;
import java.util.List;

import adapter.LoanListAdapter;
import base.BaseActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by mayn on 2018/6/25.
 * 贷款列表Activity
 */

public class LoanListActivity extends BaseActivity {
    @BindView(R.id.lv_loanlist)
    ListView lvLoanlist;
    @BindView(R.id.ll_loanlist_back)
    LinearLayout llLoanlistBack;
    @BindView(R.id.tv_loanlist_sort)
    TextView tvLoanlistSort;
    @BindView(R.id.tv_loanlist_mininterest)
    TextView tvLoanlistMininterest;
    @BindView(R.id.tv_loanlist_maxlimit)
    TextView tvLoanlistMaxlimit;
    @BindView(R.id.ll_loanlist_filter)
    LinearLayout llLoanlistFilter;
    @BindView(R.id.ll_loanlist_one)
    LinearLayout llLoanlistOne;
    @BindView(R.id.ll_loanlist_two)
    LinearLayout llLoanlistTwo;
    @BindView(R.id.ll_loanlist_three)
    LinearLayout llLoanlistThree;
    @BindView(R.id.ll_loanlist_four)
    LinearLayout llLoanlistFour;
    private List<String> datas = new ArrayList<>();
    private PopupWindow window;

    @Override
    public int initLayout() {
        return R.layout.activity_loanlist;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        setDefaultLine();
        for (int i = 0; i < 15; i++) {
            datas.add("1");
        }
        lvLoanlist.setAdapter(new LoanListAdapter(datas,LoanListActivity.this));
    }

    /**
     * 设置默认的指示线
     */
    private void setDefaultLine() {
        llLoanlistOne.setBackgroundColor(Color.parseColor("#FF6600"));
        llLoanlistTwo.setBackgroundColor(Color.parseColor("#cccccc"));
        llLoanlistThree.setBackgroundColor(Color.parseColor("#cccccc"));
        llLoanlistFour.setBackgroundColor(Color.parseColor("#cccccc"));
    }

    @OnClick({R.id.ll_loanlist_back,R.id.tv_loanlist_sort, R.id.tv_loanlist_mininterest, R.id.tv_loanlist_maxlimit, R.id.ll_loanlist_filter})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_loanlist_back:
                finish();
                break;
            case R.id.tv_loanlist_sort:
                setDefaultLine();
                if (window!=null){
                    window.dismiss();
                }
                break;
            case R.id.tv_loanlist_mininterest:
                if (window!=null){
                    window.dismiss();
                }
                llLoanlistOne.setBackgroundColor(Color.parseColor("#cccccc"));
                llLoanlistTwo.setBackgroundColor(Color.parseColor("#FF6600"));
                llLoanlistThree.setBackgroundColor(Color.parseColor("#cccccc"));
                llLoanlistFour.setBackgroundColor(Color.parseColor("#cccccc"));
                break;
            case R.id.tv_loanlist_maxlimit:
                if (window!=null){
                    window.dismiss();
                }
                llLoanlistOne.setBackgroundColor(Color.parseColor("#cccccc"));
                llLoanlistTwo.setBackgroundColor(Color.parseColor("#cccccc"));
                llLoanlistThree.setBackgroundColor(Color.parseColor("#FF6600"));
                llLoanlistFour.setBackgroundColor(Color.parseColor("#cccccc"));
                break;
            case R.id.ll_loanlist_filter:
                llLoanlistOne.setBackgroundColor(Color.parseColor("#cccccc"));
                llLoanlistTwo.setBackgroundColor(Color.parseColor("#cccccc"));
                llLoanlistThree.setBackgroundColor(Color.parseColor("#cccccc"));
                llLoanlistFour.setBackgroundColor(Color.parseColor("#FF6600"));
                showFilterDialog();
                break;
        }
    }

    /**
     * 展示筛选条件的diglog
     */
    private void showFilterDialog() {
        View view = LayoutInflater.from(LoanListActivity.this).inflate(R.layout.popupwindow_loanlist,null,false);
        window = new PopupWindow(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT );
        window.setContentView(view);
        window.setFocusable(false);//设置popupwindow可以获取焦点
        //给pupupwindow设置一个背景 透明的,以便点击popupwindow外边时候可以实现消失
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.setOutsideTouchable(true);//设置点击外部消失
        window.setTouchable(true);// 设置PopupWindow是否能响应点击事件
        window.setAnimationStyle(R.style.AnimDown);
        window.showAsDropDown(llLoanlistFour,0,0);
    }
}
