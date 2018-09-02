package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xykgj.tx.xinyongkaguanjia.R;

import java.util.List;

import base.DefaultLVAdapter;

/**
 * Created by mayn on 2018/7/9.
 *  我的信用卡详情 -- 账单明细Listview  Adapter
 */

public class BillDetailLVAdapter extends DefaultLVAdapter {
    public BillDetailLVAdapter(List mDatas, Context mContext) {
        super(mDatas, mContext);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BillDetailLVHolder billDetailLVHolder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_lv_billdetail,parent,false);
            billDetailLVHolder = new BillDetailLVHolder(convertView);
            convertView.setTag(billDetailLVHolder);
        }else {
            billDetailLVHolder = (BillDetailLVHolder) convertView.getTag();
        }
        return convertView;
    }
    public class BillDetailLVHolder{
        public TextView tv_billdetail_item_title;
        public TextView tv_billdetail_item_time;
        public TextView tv_billdetail_item_type;
        public TextView tv_billdetail_item_money;
        public BillDetailLVHolder(View itemView){
            tv_billdetail_item_title = (TextView) itemView.findViewById(R.id.tv_billdetail_item_title);
            tv_billdetail_item_time = (TextView) itemView.findViewById(R.id.tv_billdetail_item_time);
            tv_billdetail_item_type = (TextView) itemView.findViewById(R.id.tv_billdetail_item_type);
            tv_billdetail_item_money = (TextView) itemView.findViewById(R.id.tv_billdetail_item_money);
        }
    }
}
