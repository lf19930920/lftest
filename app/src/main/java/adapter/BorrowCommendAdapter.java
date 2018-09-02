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
 * Created by mayn on 2018/6/22.
 * 借钱Fragment -- Adapter
 */

public class BorrowCommendAdapter extends DefaultLVAdapter {

    public BorrowCommendAdapter(List mDatas, Context mContext) {
        super(mDatas, mContext);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BorrowCommendHolder holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_lv_fg_borrow,parent,false);
            holder = new BorrowCommendHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (BorrowCommendHolder) convertView.getTag();
        }
        return convertView;
    }
    public class BorrowCommendHolder{
        public ImageView iv_borrow_item_icon;
        public TextView tv_borrow_item_title;
        public ImageView iv_borrow_item_special;
        public TextView tv_borrow_item_limit;
        public BorrowCommendHolder(View itemView){
            iv_borrow_item_icon = (ImageView) itemView.findViewById(R.id.iv_borrow_item_icon);
            tv_borrow_item_title = (TextView) itemView.findViewById(R.id.tv_borrow_item_title);
            iv_borrow_item_special = (ImageView) itemView.findViewById(R.id.iv_borrow_item_special);
            tv_borrow_item_limit = (TextView) itemView.findViewById(R.id.tv_borrow_item_limit);
        }
    }
}
