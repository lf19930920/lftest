package activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.xykgj.tx.xinyongkaguanjia.R;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.zhouwei.mzbanner.MZBannerView;

import java.util.ArrayList;
import java.util.List;

import adapter.HotBankAdapter;
import adapter.HotCardSortAdapter;
import base.BaseActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import utils.AppUtils;
import utils.BannerImageLoader;
import utils.SpUtils;
import view.GridViewForScrollView;
import view.ListViewForScrollView;

/**
 * Created by mayn on 2018/6/27.
 * 信用卡申请Activity
 */

public class CreditApplyActivity extends BaseActivity{
    private static final String TAG = CreditApplyActivity.class.getSimpleName();
    @BindView(R.id.tv_creditapply_location)
    TextView tvCreditapplyLocation;
    @BindView(R.id.ll_creditapply_location)
    LinearLayout llCreditapplyLocation;
    @BindView(R.id.banner_creditapply_ad)
    Banner bannerCreditapplyAd;
    @BindView(R.id.ll_creditapply_search)
    LinearLayout llCreditapplySearch;
    @BindView(R.id.ll_creditapply_activa)
    LinearLayout llCreditapplyActiva;
    @BindView(R.id.ll_creditapply_friend)
    LinearLayout llCreditapplyFriend;
    @BindView(R.id.ll_creditapply_other)
    LinearLayout llCreditapplyOther;
    @BindView(R.id.banner_creditapply_todayrecom)
    MZBannerView bannerCreditapplyTodayrecom;
    @BindView(R.id.ll_creditapply_allbank)
    LinearLayout llCreditapplyAllbank;
    @BindView(R.id.gv_creditapply_hotbank)
    GridViewForScrollView gvCreditapplyHotbank;
    @BindView(R.id.ll_creditapply_allcard)
    LinearLayout llCreditapplyAllcard;
    @BindView(R.id.lv_creditapply_hotcard)
    ListViewForScrollView lvCreditapplyHotcard;
    @BindView(R.id.sv_creditapply)
    ScrollView svCreditapply;
    @BindView(R.id.ll_creditapply_back)
    LinearLayout llCreditapplyBack;

    private List<String> gv_hotbank_title = new ArrayList<>();
    private List<String> gv_hotbank_content = new ArrayList<>();
    private List<Integer> gv_hotbank_icon = new ArrayList<>();
    // 定位相关
    LocationClient creApplyLocClient;
    public CreDitApplyLocationListenner myListener = new CreDitApplyLocationListenner();
    private List<String> lv_hotcard = new ArrayList<>();
    private final static int REQUESTCODE = 503; // 返回的结果码
    private String bdLocationCity;

    @Override
    public int initLayout() {
        return R.layout.activity_creditapply;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        Log.i(TAG, "initData: ------->");
        //手动把scrollview滚动到顶部，避免被自定义的listview抢焦点
        svCreditapply.smoothScrollTo(0, 0);

        //定位
        initLocation();

        //广告轮播图
        initBannerAD();

        //今日推荐轮播图
        initTodayBannerCard();

        //热门银行
        initHotBank();

        //热卡排行
        initHotCard();

    }

    /**
     * 初始化定位
     */
    private void initLocation() {
        Log.i(TAG, "initLocation: ------->");
        // 定位初始化
        creApplyLocClient = new LocationClient(getApplicationContext());
        creApplyLocClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);
        option.setIsNeedAddress(true);
        creApplyLocClient.setLocOption(option);
        creApplyLocClient.start();//开始定位
    }


    /**
     * 广告轮播图
     */
    private void initBannerAD() {
        bannerCreditapplyAd.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        bannerCreditapplyAd.isAutoPlay(true);
        bannerCreditapplyAd.setImageLoader(new BannerImageLoader());
        //设置数据
        //bannerCreditapplyAd.setImages(images);
        bannerCreditapplyAd.setDelayTime(1500);
        bannerCreditapplyAd.setIndicatorGravity(BannerConfig.CENTER);
        bannerCreditapplyAd.start();
    }

    /**
     * 今日推荐轮播图
     */
    private void initTodayBannerCard() {
        // 设置数据
//        bannerCreditapplyTodayrecom.setPages(list, new MZHolderCreator<MZBannerViewHolder>() {
//            @Override
//            public MZBannerViewHolder createViewHolder() {
//                return new MZBannerViewHolder();
//            }
//        });
    }

    /**
     * 热门银行
     */
    private void initHotBank() {
        gv_hotbank_title.add("招商银行");
        gv_hotbank_title.add("交通银行");
        gv_hotbank_title.add("中兴银行");
        gv_hotbank_title.add("广发银行");
        gv_hotbank_title.add("农业银行");
        gv_hotbank_title.add("中国银行");
        gv_hotbank_title.add("浦发银行");
        gv_hotbank_title.add("建设银行");
        gv_hotbank_icon.add(R.mipmap.zs);
        gv_hotbank_icon.add(R.mipmap.jt);
        gv_hotbank_icon.add(R.mipmap.zx);
        gv_hotbank_icon.add(R.mipmap.gf);
        gv_hotbank_icon.add(R.mipmap.ny);
        gv_hotbank_icon.add(R.mipmap.zg);
        gv_hotbank_icon.add(R.mipmap.pf);
        gv_hotbank_icon.add(R.mipmap.js);
        gv_hotbank_content.add("周三半价");
        gv_hotbank_content.add("周三半价");
        gv_hotbank_content.add("9积分兑星巴克");
        gv_hotbank_content.add("周三半价");
        gv_hotbank_content.add("周五半价");
        gv_hotbank_content.add("周三半价");
        gv_hotbank_content.add("周一减年费");
        gv_hotbank_content.add("周三减半");
        gvCreditapplyHotbank.setAdapter(new HotBankAdapter(gv_hotbank_icon,gv_hotbank_title,gv_hotbank_content
        ,CreditApplyActivity.this));
    }

    /**
     * 热卡排行
     */
    private void initHotCard() {
        for (int i = 0; i <5; i++) {
            lv_hotcard.add("1");
        }
        HotCardSortAdapter adapter = new HotCardSortAdapter(lv_hotcard, CreditApplyActivity.this);
        lvCreditapplyHotcard.setAdapter(adapter);

    }
    @OnClick({R.id.ll_creditapply_back, R.id.ll_creditapply_location, R.id.ll_creditapply_search,
            R.id.ll_creditapply_activa, R.id.ll_creditapply_friend, R.id.ll_creditapply_other,
            R.id.ll_creditapply_allbank, R.id.ll_creditapply_allcard})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_creditapply_back:
                finish();
                break;
            case R.id.ll_creditapply_location:
                if (!TextUtils.isEmpty(SpUtils.getString(CreditApplyActivity.this,"token"))){
                    //跳转到选择城市的界面
                    Intent intent = new Intent(CreditApplyActivity.this,CityChooseActivity.class);
                    intent.putExtra("locationcity",bdLocationCity);
                    startActivityForResult(intent,REQUESTCODE);
                }else {
                    AppUtils.showToast(CreditApplyActivity.this,"请您先登录");
                    return;
                }
                break;
            case R.id.ll_creditapply_search:
                break;
            case R.id.ll_creditapply_activa:
                break;
            case R.id.ll_creditapply_friend:
                break;
            case R.id.ll_creditapply_other:
                break;
            case R.id.ll_creditapply_allbank:
                break;
            case R.id.ll_creditapply_allcard:
                //跳转到全部信用卡界面
                goToActivity(CreditApplyActivity.this,AllCreditCardActivity.class);
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        //开始轮播
        bannerCreditapplyAd.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        //结束轮播
        bannerCreditapplyAd.stopAutoPlay();
    }
    public class CreDitApplyLocationListenner implements BDLocationListener{

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            Log.i(TAG, "onReceiveLocation: ------->");
            bdLocationCity = bdLocation.getCity();
            //先判断当前定位的城市是否为空
            if (!TextUtils.isEmpty(bdLocationCity)){
                //判断Sp中是否存有之前选择的城市
                Log.i(TAG, "Sp中的city="+SpUtils.getString(CreditApplyActivity.this,"city"));
                if (!TextUtils.isEmpty(SpUtils.getString(CreditApplyActivity.this,"city"))){
                    //判断当前定位的城市跟之前手动选择的城市是否为同一个
                    if (!bdLocationCity.equals(SpUtils.getString(CreditApplyActivity.this,"city"))){
                        // 创建构建器
                        AlertDialog.Builder builder = new AlertDialog.Builder(CreditApplyActivity.this);
                        builder.setMessage("检测到您当前定位的城市是："+ bdLocationCity +" 是否切换？")
                                .setPositiveButton("切换", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        tvCreditapplyLocation.setText(bdLocationCity);
                                        dialog.dismiss();
                                    }
                                }).setNegativeButton("不切换", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                tvCreditapplyLocation.setText(SpUtils.getString(CreditApplyActivity.this,"city"));
                                dialog.dismiss();
                            }
                        });
                        builder.create().show();
                    }else {
                        tvCreditapplyLocation.setText(bdLocationCity);
                    }
                }else {
                    tvCreditapplyLocation.setText(bdLocationCity);
                }
            }else {
                AppUtils.showToast(CreditApplyActivity.this,"没有获取到您的定位信息，请确定您是否开启了定位权限");
                return;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 504){
            if (requestCode == REQUESTCODE){
                if (data != null){
                    String city = data.getStringExtra("city");
                    tvCreditapplyLocation.setText(city);
                    SpUtils.saveString(CreditApplyActivity.this,"city",city);
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        creApplyLocClient.stop();
    }
}
