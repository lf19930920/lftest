package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xykgj.tx.xinyongkaguanjia.R;

import java.util.List;

import base.DefaultLVAdapter;

/**
 * Created by mayn on 2018/6/29.
 * 信用卡申请-- 热卡排行adapter
 */

public class HotCardSortAdapter extends DefaultLVAdapter {
    public HotCardSortAdapter(List mDatas, Context mContext) {
        super(mDatas, mContext);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HotCardSortHolder holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_lv_hotcardsort,parent,false);
            holder = new HotCardSortHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (HotCardSortHolder) convertView.getTag();
        }
        switch (position){
            case 0:
                holder.iv_allcreditcard_sort.setImageResource(R.mipmap.hotcardsort_1);
                break;
            case 1:
                holder.iv_allcreditcard_sort.setImageResource(R.mipmap.hotcardsort_2);
                break;
            case 2:
                holder.iv_allcreditcard_sort.setImageResource(R.mipmap.hotcardsort_3);
                break;
            default:
                break;
        }
        return convertView;
    }
    public class HotCardSortHolder {
        public ImageView iv_allcreditcard_icon;
        public ImageView iv_allcreditcard_sort;
        public TextView tv_allcreditcard_title;
        public TextView tv_allcreditcard_gift;
        public TextView tv_allcreditcard_desc;
        public HotCardSortHolder(View itemView){
            iv_allcreditcard_icon = (ImageView) itemView.findViewById(R.id.iv_allcreditcard_icon);
            iv_allcreditcard_sort = (ImageView) itemView.findViewById(R.id.iv_allcreditcard_sort);
            tv_allcreditcard_title = (TextView) itemView.findViewById(R.id.tv_allcreditcard_title);
            tv_allcreditcard_gift = (TextView) itemView.findViewById(R.id.tv_allcreditcard_gift);
            tv_allcreditcard_desc = (TextView) itemView.findViewById(R.id.tv_allcreditcard_desc);
        }
    }
}
