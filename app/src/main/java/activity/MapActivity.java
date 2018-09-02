package activity;

import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import com.xykgj.tx.xinyongkaguanjia.R;

import base.BaseActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fragment.MaplistFragment;
import fragment.MapmapFragment;
import view.MyRadioButton;

/**
 * Created by mayn on 2018/7/12.
 * 地图Activity
 */

public class MapActivity extends BaseActivity {


    @BindView(R.id.ll_map_back)
    LinearLayout llMapBack;
    @BindView(R.id.rb_map_map)
    MyRadioButton rbMapMap;
    @BindView(R.id.rb_map_list)
    MyRadioButton rbMapList;
    @BindView(R.id.rg_map_tab)
    RadioGroup rgMapTab;
    @BindView(R.id.ll_map_linemap)
    LinearLayout llMapLinemap;
    @BindView(R.id.ll_map_linelist)
    LinearLayout llMapLinelist;
    @BindView(R.id.fl_map_container)
    FrameLayout flMapContainer;
    private FragmentManager manager;
    private MapmapFragment mapmapFragment;
    private MaplistFragment maplistFragment;
    @Override
    public int initLayout() {
        return R.layout.activity_map;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        rgMapTab.check(R.id.rb_map_map);
        llMapLinemap.setVisibility(View.VISIBLE);
        llMapLinelist.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void initData() {
        if (mapmapFragment == null){
            mapmapFragment = new MapmapFragment();
        }
        manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.fl_map_container,mapmapFragment).commit();
    }


    @OnClick({R.id.ll_map_back, R.id.rb_map_map, R.id.rb_map_list})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_map_back:
                finish();
                break;
            case R.id.rb_map_map:
                llMapLinemap.setVisibility(View.VISIBLE);
                llMapLinelist.setVisibility(View.INVISIBLE);
                if (mapmapFragment == null){
                    mapmapFragment = new MapmapFragment();
                }
                manager.beginTransaction().replace(R.id.fl_map_container,mapmapFragment).commit();
                break;
            case R.id.rb_map_list:
                llMapLinemap.setVisibility(View.INVISIBLE);
                llMapLinelist.setVisibility(View.VISIBLE);
                if (maplistFragment == null){
                    maplistFragment = new MaplistFragment();
                }
                manager.beginTransaction().replace(R.id.fl_map_container,maplistFragment).commit();
                break;
        }
    }
}
