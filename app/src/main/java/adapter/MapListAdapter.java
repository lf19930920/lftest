package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.utils.DistanceUtil;
import com.xykgj.tx.xinyongkaguanjia.R;

import java.util.List;

import base.DefaultLVAdapter;

/**
 * Created by mayn on 2018/7/12.
 * 我的信用卡 -- 银行列表Adapter
 */

public class MapListAdapter extends DefaultLVAdapter<PoiInfo> {
    private LatLng latLng;
    public MapListAdapter(List mDatas, Context mContext,LatLng latLng) {
        super(mDatas, mContext);
        this.latLng = latLng;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MapListHolder holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_lv_bankmaplist,parent,false);
            holder = new MapListHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (MapListHolder) convertView.getTag();
        }
        holder.tv_bankmaplist_title.setText(mDatas.get(position).name);
        holder.tv_bankmaplist_location.setText(mDatas.get(position).address);
        LatLng location = mDatas.get(position).location;
        double d_distance = DistanceUtil.getDistance(latLng, location);
        String s_distance = String.valueOf(d_distance);
        holder.tv_bankmaplist_distance.setText(s_distance.substring(0,s_distance.indexOf("."))+"米");
        return convertView;
    }
    public class MapListHolder{
        public TextView tv_bankmaplist_title;
        public TextView tv_bankmaplist_location;
        public TextView tv_bankmaplist_distance;
        public MapListHolder(View itemView){
            tv_bankmaplist_title = (TextView) itemView.findViewById(R.id.tv_bankmaplist_title);
            tv_bankmaplist_location = (TextView) itemView.findViewById(R.id.tv_bankmaplist_location);
            tv_bankmaplist_distance = (TextView) itemView.findViewById(R.id.tv_bankmaplist_distance);
        }
    }
}
