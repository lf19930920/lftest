package fragment;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.xykgj.tx.xinyongkaguanjia.R;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import activity.CreditApplyActivity;
import activity.InputBillActivity;
import activity.LoginActivity;
import activity.MSGCenterActivity;
import activity.MainActivity;
import activity.MyCardDetailActivity;
import activity.MyRedPagActivity;
import adapter.MyCreditCardAdapter;
import base.BaseFragment;
import bean.CardListBean;
import bean.HomeBannerBean;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import evenbusbean.EventBusLoginBean;
import network.NetWorkConfig;
import okhttp3.Call;
import utils.AppUtils;
import utils.BannerImageLoader;
import utils.MD5Utils;
import utils.SpUtils;
import utils.TimeUtils;
import view.ListViewForScrollView;

/**
 * Created by mayn on 2018/6/19.
 * 首页Fragment
 */

public class HomePageFragment extends BaseFragment implements AdapterView.OnItemClickListener{
    @BindView(R.id.sv_homepage)
    ScrollView svHomePage;
    @BindView(R.id.ll_homepage_login)
    LinearLayout llHomepageLogin;
    @BindView(R.id.iv_homepage_message)
    ImageView ivHomepageMessage;
    @BindView(R.id.banner_homepage)
    Banner bannerHomepage;
    @BindView(R.id.ll_homepage_getcard)
    LinearLayout llHomepageGetcard;
    @BindView(R.id.ll_homepage_redpac)
    LinearLayout llHomepageRedpac;
    @BindView(R.id.ll_homepage_guest)
    LinearLayout llHomepageGuest;
    @BindView(R.id.ll_homepage_active)
    LinearLayout llHomepageActive;
    @BindView(R.id.iv_homepage_advert)
    ImageView ivHomepageAdvert;
    @BindView(R.id.ib_homepage_addcard)
    ImageButton ibHomepageAddcard;
    @BindView(R.id.ll_homepage_handlecard)
    LinearLayout llHomepageHandlecard;
    @BindView(R.id.ll_homepage_mycard)
    LinearLayout llHomepageMycard;
    @BindView(R.id.tv_homepage_mycreditcard)
    TextView tvHomepageMycreditcard;
    @BindView(R.id.lv_homepage_mycard)
    ListViewForScrollView lvHomepageMycard;
    @BindView(R.id.tv_homepage_userphone)
    TextView tvHomepageUserphone;
    Unbinder mUnbinder;

    private static final String TAG = HomePageFragment.class.getSimpleName();
    private List<CardListBean.ResultBean> cardList = new ArrayList<>();
    private MyCreditCardAdapter cardAdapter;
    private List<String> images = new ArrayList<>();
    private List<String> titles = new ArrayList<>();
    @Override
    protected View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_homepage, null);
        mUnbinder = ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        lvHomepageMycard.setOnItemClickListener(this);
        //手动把scrollview滚动到顶部，避免被自定义的listview抢焦点
        svHomePage.smoothScrollTo(0, 0);
        return view;
    }

    @Override
    protected void initData() {
        initBanner();
        initTVIsLogin();
        if (!TextUtils.isEmpty(SpUtils.getString(mActivity,"token"))){
            getCardList();
        }else {
            llHomepageMycard.setVisibility(View.GONE);
        }
        cardAdapter = new MyCreditCardAdapter(cardList, mActivity);
        lvHomepageMycard.setAdapter(cardAdapter);

    }

    private void initTVIsLogin() {
        if (TextUtils.isEmpty(SpUtils.getString(mActivity,"userphone"))){
            tvHomepageUserphone.setText("请登录");
        }else {
            String userphone = SpUtils.getString(mActivity,"userphone");
            String hiddenphone = userphone.substring(0, 3) + "****" + userphone.substring(7, userphone.length());
            tvHomepageUserphone.setText(hiddenphone);
        }
    }

    private void initBanner() {
        String time = TimeUtils.getTime();
        String md5sign_banner = MD5Utils.getMD5WithSalt("timestamp=" + time, NetWorkConfig.SIGN);
        OkHttpUtils
                .get().url(NetWorkConfig.BaseURL+NetWorkConfig.OPEN_HomeBanner)
                .addParams("timestamp",time)
                .addParams("sign",md5sign_banner)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.i(TAG, "banner onError: "+e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.i(TAG, "banner onResponse: "+response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            int code = (int) jsonObject.opt("code");
                            if (code == 1000){
                                parseBannerJson(response);
                            }else {
                                AppUtils.showToast(mActivity,"服务器错误");
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });


    }

    private void parseBannerJson(String response) {
        Gson gson = new Gson();
        HomeBannerBean homeBannerBean = gson.fromJson(response, HomeBannerBean.class);
        List<HomeBannerBean.ResultBean> result = homeBannerBean.getResult();
        for (int i = 0; i <result.size(); i++) {
            images.add(result.get(i).getImg());
            titles.add(result.get(i).getTitle());
        }
        bannerHomepage.setBannerTitles(titles);
        bannerHomepage.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        bannerHomepage.isAutoPlay(true);
        bannerHomepage.setImageLoader(new BannerImageLoader());
        bannerHomepage.setImages(images);
        bannerHomepage.setDelayTime(1500);
        bannerHomepage.setIndicatorGravity(BannerConfig.CENTER);
        bannerHomepage.start();
    }


    @OnClick({R.id.ll_homepage_login, R.id.iv_homepage_message, R.id.ll_homepage_getcard,
            R.id.ll_homepage_redpac, R.id.ll_homepage_guest, R.id.ll_homepage_active,R.id.ib_homepage_addcard})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_homepage_login:
                if (TextUtils.isEmpty(SpUtils.getString(mActivity,"token"))){
                    Intent intent = new Intent(mActivity, LoginActivity.class);
                    startActivity(intent);
                }else {
                    MainActivity activity = (MainActivity) getActivity();
                    activity.MychangeFragment();
                    return;
                }
                break;
            case R.id.iv_homepage_message:
                if (TextUtils.isEmpty(SpUtils.getString(mActivity,"token"))){
                    Intent intent = new Intent(mActivity, LoginActivity.class);
                    startActivity(intent);
                }else {
                    //跳转到消息中心
                    jumpToActivity(mActivity, MSGCenterActivity.class);
                    return;
                }
                break;
            case R.id.ll_homepage_getcard:
                //办卡
                jumpToActivity(mActivity, CreditApplyActivity.class);
                break;
            case R.id.ll_homepage_redpac:
                if (TextUtils.isEmpty(SpUtils.getString(mActivity,"token"))){
                    //跳转到登录
                    Intent intent = new Intent(mActivity, LoginActivity.class);
                    startActivity(intent);
                }else {
                    //红包
                    jumpToActivity(mActivity, MyRedPagActivity.class);
                }
                break;
            case R.id.ll_homepage_guest:
                //赚客
                break;
            case R.id.ll_homepage_active:
                //活动
                break;
            case R.id.ib_homepage_addcard:
                if (TextUtils.isEmpty(SpUtils.getString(mActivity,"token"))){

                }else {

                }
                //添加信用卡
                jumpToActivity(mActivity, InputBillActivity.class);
                break;
        }
    }

    /**
     * 获取卡片列表
     */
    private void getCardList() {
        //获取时间戳
        String time = TimeUtils.getTime();
        //获取token
        String token = SpUtils.getString(mActivity, "token");
        //获取拼接的sign
        String md5_sgin = MD5Utils.getMD5WithSalt("timestamp=" + time + "&token=" + token, NetWorkConfig.SIGN);
        OkHttpUtils
                .get()
                .url(NetWorkConfig.BaseURL+NetWorkConfig.USER_UserCardList)
                .addParams("token",token)
                .addParams("timestamp",time)
                .addParams("sign",md5_sgin)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.i(TAG, "onError="+e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.i(TAG, "onResponse="+response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            int code = (int) jsonObject.opt("code");
                            if (code == 1000){
                                //获取数据成功
                                parseCardListJson(response);
                            }else {
                                AppUtils.showToast(mActivity,"获取银行卡数据失败");
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    /**
     *
     * @param response
     */
    private void parseCardListJson(String response) {
        cardList.clear();
        Gson gson = new Gson();
        CardListBean cardListBean = gson.fromJson(response, CardListBean.class);
        List<CardListBean.ResultBean> dataList = cardListBean.getResult();
        Log.i(TAG, "parseCardListJson: dataList.size()="+dataList.size());
//        if (dataList.size()>0){
//            ivHomepageAdvert.setVisibility(View.GONE);
//        }else {
//            ivHomepageAdvert.setVisibility(View.VISIBLE);
//        }
        tvHomepageUserphone.setText("hahahahahah");
        cardList.addAll(dataList);
        cardAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        CardListBean.ResultBean cardBean = (CardListBean.ResultBean) cardAdapter.getItem(position);
        Intent intent = new Intent(mActivity, MyCardDetailActivity.class);
        Log.i(TAG, "onItemClick: cardBean.getId()="+cardBean.getId());
        intent.putExtra("cardId",cardBean.getId());
        startActivity(intent);
    }

    @Override
    public void onStart() {
        super.onStart();
        //开始轮播
        bannerHomepage.startAutoPlay();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventBusLoginBean event) {
        /* Do something */
        Log.i(TAG, "onEvent: ----------->");
        //llHomepageMycard.setVisibility(View.VISIBLE);
        initTVIsLogin();
        getCardList();
    }
    @Override
    public void onStop() {
        super.onStop();
        //结束轮播
        bannerHomepage.stopAutoPlay();
    }

    @Override
    public void onDestroyView() {
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

}
