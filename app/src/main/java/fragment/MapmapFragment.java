package fragment;

import android.graphics.Color;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.CircleOptions;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.Stroke;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchOption;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.baidu.mapapi.search.poi.PoiSortType;
import com.xykgj.tx.xinyongkaguanjia.R;

import base.BaseFragment;
import message.LocationMessage;
import utils.PoiOverlay;
import utils.SpUtils;

/**
 * Created by mayn on 2018/7/12.
 * 首页我的信用卡 -- 银行网点地图fragment
 */

public class MapmapFragment extends BaseFragment implements OnGetPoiSearchResultListener{
    private MapView mapViewBaidu;
    // 定位相关
    LocationClient mLocClient;
    private BaiduMap map;
    private int mCurrentDirection = 0;
    private double mCurrentLat = 0.0;
    private double mCurrentLon = 0.0;
    private MyLocationData locData;
    private LatLng center;
    private float mCurrentAccracy;
    public MyLocationListenner myListener = new MyLocationListenner();
    //poi搜索
    private PoiSearch poiSearch;
    @Override
    protected View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_mapmap, null);
        mapViewBaidu = (MapView) view.findViewById(R.id.mapView_baidu);
        return view;
    }

    @Override
    protected void initData() {
        initLocation();
    }

    /**
     * 初始化定位配置信息
     */
    private void initLocation() {
        map = mapViewBaidu.getMap();
        //开启定位图层
        map.setMyLocationEnabled(true);
        // 定位初始化
        mLocClient = new LocationClient(mActivity);
        mLocClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);

        mLocClient.setLocOption(option);
        mLocClient.start();//开始定位
    }

    /**
     * 定位SDK监听函数
     */
    public class MyLocationListenner implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // map view 销毁后不在处理新接收的位置
            if (location == null || mapViewBaidu == null) {
                return;
            }
            mCurrentLat = location.getLatitude();
            mCurrentLon = location.getLongitude();
            LocationMessage locationMessage = new LocationMessage();
            locationMessage.setLatitude(mCurrentLat);
            locationMessage.setLongitude(mCurrentLon);
            SpUtils.setObject(mActivity,"location",locationMessage);
            mCurrentAccracy = location.getRadius();
            locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(mCurrentDirection).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            map.setMyLocationData(locData);

            LatLng ll = new LatLng(location.getLatitude(),
                    location.getLongitude());
            MapStatus.Builder builder = new MapStatus.Builder();
            builder.target(ll).zoom(18.0f);
            map.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
            initPoiSearch();
        }

        public void onReceivePoi(BDLocation poiLocation) {

        }
    }

    /**
     * 初始化poi搜索配置信息
     */
    private void initPoiSearch() {
        //创建poi检索实例
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
            map.clear();
            center = new LatLng(mCurrentLat,mCurrentLon);
            PoiOverlay overlay = new MyPoiOverlay(map);
            map.setOnMarkerClickListener(overlay);
            overlay.setData(poiResult);
            overlay.addToMap();
            overlay.zoomToSpan();
            showNearbyArea(center,5000);
        }
    }

    /**
     * 获取Place详情页检索结果
     *
     * @param poiDetailResult
     */
    @Override
    public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {
        if (poiDetailResult.error != SearchResult.ERRORNO.NO_ERROR) {
            Toast.makeText(mActivity, "抱歉，未找到结果", Toast.LENGTH_SHORT)
                    .show();
        } else {
            TextView textView = new TextView(mActivity);
            textView.setText(poiDetailResult.getName() + "\n" + poiDetailResult.getAddress());
            textView.setBackgroundResource(R.mipmap.popup);
            textView.setPadding(5,5,5,10);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,10);
            LatLng location = poiDetailResult.getLocation();
            //创建InfoWindow , 传入 view， 地理坐标， y 轴偏移量
            InfoWindow mInfoWindow = new InfoWindow(textView, location, -47);
            map.showInfoWindow(mInfoWindow);
        }
    }

    @Override
    public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

    }

    private void showNearbyArea(LatLng center, int radius) {

//        BitmapDescriptor centerBitmap = BitmapDescriptorFactory
//                .fromResource(R.drawable.icon_geo);
//        MarkerOptions ooMarker = new MarkerOptions().position(center).icon(centerBitmap);
//        map.addOverlay(ooMarker);
        OverlayOptions ooCircle = new CircleOptions().fillColor(Color.parseColor("#00000000"))
                .center(center).stroke(new Stroke(3, Color.parseColor("#1169EE")))
                .radius(radius);
        map.addOverlay(ooCircle);
    }

    /**
     * 自定义poi覆盖物类
     */
    private class MyPoiOverlay extends PoiOverlay {

        public MyPoiOverlay(BaiduMap baiduMap) {
            super(baiduMap);
        }

        @Override
        public boolean onPoiClick(int index) {
            super.onPoiClick(index);
            PoiInfo poi = getPoiResult().getAllPoi().get(index);
            // if (poi.hasCaterDetails) {
            poiSearch.searchPoiDetail((new PoiDetailSearchOption())
                    .poiUid(poi.uid));
            // }
            return true;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mapViewBaidu.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapViewBaidu.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapViewBaidu.onDestroy();
        //释放poi搜索实例
        poiSearch.destroy();
    }
}
