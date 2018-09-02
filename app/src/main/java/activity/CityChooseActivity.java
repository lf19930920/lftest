package activity;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.xykgj.tx.xinyongkaguanjia.R;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import adapter.CityChooseHotAdapter;
import adapter.SortAdapter;
import base.BaseActivity;
import bean.CityChooseBean;
import bean.SortModel;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import network.NetWorkConfig;
import okhttp3.Call;
import utils.AppUtils;
import utils.CharacterParser;
import utils.MD5Utils;
import utils.PinyinComparator;
import utils.SpUtils;
import utils.TimeUtils;
import view.GridViewForScrollView;
import view.SideBar;

/**
 * Created by mayn on 2018/7/23.
 * 选择城市Activity
 */

public class CityChooseActivity extends BaseActivity {
    private static final String TAG = CityChooseActivity.class.getSimpleName();
    @BindView(R.id.ll_citychoose_back)
    LinearLayout llCitychooseBack;
    @BindView(R.id.lv_citychoose)
    ListView lvCitychoose;
    @BindView(R.id.sidebar_city)
    SideBar sideBarCity;
    @BindView(R.id.tv_city_dialog)
    TextView tvCityDialog;
    /**
     * 显示字母的TextView
     */
    private TextView dialog;
    /**
     * 汉字转换成拼音的类
     */
    private CharacterParser characterParser;
    private List<SortModel> SourceDataList = new ArrayList<>();

    /**
     * 根据拼音来排列ListView里面的数据类
     */
    private PinyinComparator pinyinComparator;
    private List<CityChooseBean.ResultBean.HotBean> hot_list = new ArrayList<>();
    private List<CityChooseBean.ResultBean.CityBean> city_list = new ArrayList<>();
    private CityChooseHotAdapter hotAdapter;//热门城市的数据源
    private SortAdapter sortAdapter;
    private static final int RESULTCODE = 504;

    @Override
    public int initLayout() {
        return R.layout.activity_citychoose;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        //实例化汉字转拼音类
        characterParser = CharacterParser.getInstance();

        pinyinComparator = new PinyinComparator();
        sideBarCity.setTextView(tvCityDialog);
        //设置右侧触摸监听
        sideBarCity.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s) {
                //该字母首次出现的位置
                int position = sortAdapter.getPositionForSection(s.charAt(0));
                if(position != -1){
                    lvCitychoose.setSelection(position);
                }
            }
        });
        lvCitychoose.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SortModel sortmodel = (SortModel) sortAdapter.getItem(position-1);
                AppUtils.showToast(CityChooseActivity.this,sortmodel.getName());
                SpUtils.saveString(CityChooseActivity.this,"city",sortmodel.getName());
                Intent intent = new Intent();
                intent.putExtra("city",sortmodel.getName());
                setResult(RESULTCODE,intent);
                finish();
            }
        });

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        String locationcity = intent.getStringExtra("locationcity");

        //listview的头布局
        initHead(locationcity);
        //获取城市列表
        getCityData();
    }

    private void initHead(String locationcity) {
        View headView = LayoutInflater.from(CityChooseActivity.this).inflate(R.layout.lv_citychoose_head,null,false);
        TextView tv_citychoose_current = (TextView) headView.findViewById(R.id.tv_citychoose_current);
        tv_citychoose_current.setText(locationcity);
        GridViewForScrollView gv_hot = (GridViewForScrollView) headView.findViewById(R.id.gv_citychoose_hot);
        hotAdapter = new CityChooseHotAdapter(hot_list, CityChooseActivity.this);
        gv_hot.setAdapter(hotAdapter);
        gv_hot.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CityChooseBean.ResultBean.HotBean hotBean = (CityChooseBean.ResultBean.HotBean) hotAdapter.getItem(position);
                Intent intent = new Intent();
                intent.putExtra("city",hotBean.getName());
                setResult(RESULTCODE,intent);
                finish();
            }
        });
        //listview添加headview
        lvCitychoose.addHeaderView(headView,null,false);

    }

    private void getCityData() {
        String token = SpUtils.getString(CityChooseActivity.this, "token");
        String time = TimeUtils.getTime();
        String md5_sign = MD5Utils.getMD5WithSalt("show=" + "all" + "&timestamp=" + time + "&token=" + token, NetWorkConfig.SIGN);
        OkHttpUtils
                .get()
                .url(NetWorkConfig.BaseURL+NetWorkConfig.OPEN_OpenCityList)
                .addParams("show","all")
                .addParams("timestamp",time)
                .addParams("token",token)
                .addParams("sign",md5_sign)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.i(TAG, "onError = "+e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.i(TAG, "onResponse = "+response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            int code = (int) jsonObject.opt("code");
                            if (code == 1000){
                                //获取数据成功
                                parseCityChooseJson(response);
                            }else {
                                //获取数据失败
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    /**
     *  解析城市选择数据
     * @param response
     */
    private void parseCityChooseJson(String response) {
        Gson gson = new Gson();
        CityChooseBean cityChooseBean = gson.fromJson(response, CityChooseBean.class);
        //热门城市的数据源
        List<CityChooseBean.ResultBean.HotBean> hot = cityChooseBean.getResult().getHot();
        hot_list.addAll(hot);
        hotAdapter.notifyDataSetChanged();
        //全部城市的数据源
        List<CityChooseBean.ResultBean.CityBean> city = cityChooseBean.getResult().getCity();
        Log.i(TAG, "city.size()="+city.size());
        for (int i = 0; i <city.size(); i++) {
            Log.i(TAG, "i=="+i);
            List<CityChooseBean.ResultBean.CityBean.SonBean> son = city.get(i).getSon();
            for (int j = 0; j <son.size(); j++) {
                    SortModel sortModel = new SortModel();
                    sortModel.setName(son.get(j).getName());
                    SourceDataList.add(sortModel);
                }
            }
        List<SortModel> sortModelList = filledData(SourceDataList);
        // 根据a-z进行排序源数据
        Collections.sort(sortModelList, pinyinComparator);
        sortAdapter = new SortAdapter(CityChooseActivity.this,sortModelList);
        lvCitychoose.setAdapter(sortAdapter);
    }

    @OnClick(R.id.ll_citychoose_back)
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.ll_citychoose_back:
                finish();
                break;
        }
    }

    /**
     * 为ListView填充数据
     * @param
     * @return
     */
    private List<SortModel> filledData(List<SortModel> data){
        List<SortModel> mSortList = new ArrayList<SortModel>();
        for(int i=0; i<data.size(); i++){
            SortModel sortModel = new SortModel();
            sortModel.setName(data.get(i).getName());
            //汉字转换成拼音
            String pinyin = characterParser.getSelling(data.get(i).getName());
            String sortString = pinyin.substring(0, 1).toUpperCase();
            // 正则表达式，判断首字母是否是英文字母
            if(sortString.matches("[A-Z]")){
                sortModel.setSortLetters(sortString.toUpperCase());
            }else{
                sortModel.setSortLetters("#");
            }
            mSortList.add(sortModel);
        }
        return mSortList;
    }
}
