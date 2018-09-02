package fragment;

import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.baidu.mapapi.search.poi.PoiSortType;
import com.xykgj.tx.xinyongkaguanjia.R;

import java.util.ArrayList;
import java.util.List;

import adapter.MapListAdapter;
import base.BaseFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import message.LocationMessage;
import utils.SpUtils;

/**
 * Created by mayn on 2018/7/12.
 * 首页我的信用卡 -- 银行网点列表fragment
 */

public class MaplistFragment extends BaseFragment implements OnGetPoiSearchResultListener{
    Unbinder unbinder;
    @BindView(R.id.lv_bankmaplist)
    ListView lvBankmaplist;
    private PoiSearch poiSearch;
    private double mCurrentLat = 0.0;
    private double mCurrentLon = 0.0;

    private List<PoiInfo> mapList = new ArrayList<>();
    private MapListAdapter mapListAdapter;

    @Override
    protected View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_maplist, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initData() {
        LocationMessage location = SpUtils.getObject(mActivity, "location", LocationMessage.class);
        mCurrentLat = location.getLatitude();
        mCurrentLon = location.getLongitude();
        LatLng latLng = new LatLng(mCurrentLat,mCurrentLon);
        mapListAdapter = new MapListAdapter(mapList, mActivity,latLng);
        lvBankmaplist.setAdapter(mapListAdapter);

        //初始化poi搜索
        initPOISearch();
    }


    /**
     * 初始化poi搜索
     */
    private void initPOISearch() {
        poiSearch = PoiSearch.newInstance();
        poiSearch.setOnGetPoiSearchResultListener(this);
        poiSearch.searchNearby(new PoiNearbySearchOption()
                .keyword("银行")
                .sortType(PoiSortType.distance_from_near_to_far)
                .location(new LatLng(mCurrentLat,mCurrentLon))
                .radius(5000)
                .pageNum(1));
    }


    /**
     * 获取POI检索结果
     *
     * @param poiResult
     */
    @Override
    public void onGetPoiResult(PoiResult poiResult) {
        if (poiResult == null || poiResult.error == SearchResult.ERRORNO.RESULT_NOT_FOUND) {
            Toast.makeText(mActivity, "搜索结果为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (poiResult.error == SearchResult.ERRORNO.NO_ERROR){
            List<PoiInfo> allPoi = poiResult.getAllPoi();
            mapList.addAll(allPoi);
            mapListAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {

    }

    @Override
    public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //释放poi搜索实例
        poiSearch.destroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
