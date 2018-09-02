package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xykgj.tx.xinyongkaguanjia.R;

import java.util.List;

import base.DefaultLVAdapter;
import bean.CityChooseBean;

/**
 * Created by mayn on 2018/7/23.
 * 城市选择 -- 热门城市Adapter
 */

public class CityChooseHotAdapter extends DefaultLVAdapter<CityChooseBean.ResultBean.HotBean> {


    public CityChooseHotAdapter(List mDatas, Context mContext) {
        super(mDatas, mContext);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CityHotHolder hotHolder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.gv_hotcity_item,parent,false);
            hotHolder = new CityHotHolder(convertView);
            convertView.setTag(hotHolder);
        }else {
            hotHolder = (CityHotHolder) convertView.getTag();
        }
        hotHolder.tv_hotcity_item.setText(mDatas.get(position).getName());
        return convertView;
    }
    public class CityHotHolder{
        public TextView tv_hotcity_item;
        public CityHotHolder(View itemView){
            tv_hotcity_item = (TextView) itemView.findViewById(R.id.tv_hotcity_item);
        }
    }
}
