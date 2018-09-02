package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xykgj.tx.xinyongkaguanjia.R;

import java.util.List;

import base.DefaultLVAdapter;

/**
 * Created by mayn on 2018/6/29.
 * 我的红包 -- 借款券adapter
 */

public class MyRedPacBorrowAdapter extends DefaultLVAdapter {
    public MyRedPacBorrowAdapter(List mDatas, Context mContext) {
        super(mDatas, mContext);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyRedPacBorrowHolder holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_lv_myredpac_borrow,parent,false);
            holder = new MyRedPacBorrowHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (MyRedPacBorrowHolder) convertView.getTag();
        }
        return convertView;
    }
    public class MyRedPacBorrowHolder{
        public TextView tv_myredpac_borrow_title;
        public TextView tv_myredpac_borrow_duetotime;
        public TextView tv_myredpac_borrow_moneylimit;
        public TextView tv_myredpac_borrow_timelimit;
        public TextView tv_myredpac_borrow_money;
        public LinearLayout ll_myredpac_borrow_usenow;
        public MyRedPacBorrowHolder(View itemView){
            tv_myredpac_borrow_title = (TextView) itemView.findViewById(R.id.tv_myredpac_borrow_title);
            tv_myredpac_borrow_duetotime = (TextView) itemView.findViewById(R.id.tv_myredpac_borrow_duetotime);
            tv_myredpac_borrow_moneylimit = (TextView) itemView.findViewById(R.id.tv_myredpac_borrow_moneylimit);
            tv_myredpac_borrow_timelimit = (TextView) itemView.findViewById(R.id.tv_myredpac_borrow_timelimit);
            tv_myredpac_borrow_money = (TextView) itemView.findViewById(R.id.tv_myredpac_borrow_money);
            ll_myredpac_borrow_usenow = (LinearLayout) itemView.findViewById(R.id.ll_myredpac_borrow_usenow);
        }
    }
}
