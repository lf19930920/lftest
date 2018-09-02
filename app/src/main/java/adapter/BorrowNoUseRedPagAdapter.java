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
import bean.RedPagListBean;

/**
 * Created by mayn on 2018/7/2.
 * 借款券不可用红包Adapter
 */

public class BorrowNoUseRedPagAdapter extends DefaultLVAdapter<RedPagListBean.ResultBean>{
    public BorrowNoUseRedPagAdapter(List mDatas, Context mContext) {
        super(mDatas, mContext);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BorrowNoUseRedPagHolder holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_lv_borrownouseredpag,parent,false);
            holder = new BorrowNoUseRedPagHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (BorrowNoUseRedPagHolder) convertView.getTag();
        }
        holder.tv_borrownouseredpag_title.setText(mDatas.get(position).getRedpkg_name());
        holder.tv_borrownouseredpag_duetotime.setText(mDatas.get(position).getExpire_time()+" 到期");
        holder.tv_borrownouseredpag_moneylimit.setText(mDatas.get(position).getUse_condition());
        holder.tv_borrownouseredpag_timelimit.setText(mDatas.get(position).getUse_remark());
        holder.tv_borrownouseredpag_money.setText(mDatas.get(position).getAmount());
        holder.tv_borrownouseredpag_typename.setText(mDatas.get(position).getUnit_type());
        if (mDatas.get(position).getStatus() == 1){
            holder.iv_borrownouseredpag_state.setBackgroundResource(R.mipmap.noredpag_used);
        }else if (mDatas.get(position).getStatus() == 2){
            holder.iv_borrownouseredpag_state.setBackgroundResource(R.mipmap.noredpag_overdue);
        }else {
            holder.iv_borrownouseredpag_state.setBackgroundResource(R.mipmap.noredpag_used);
        }
        return convertView;
    }
    public class BorrowNoUseRedPagHolder{
        public TextView tv_borrownouseredpag_title;
        public TextView tv_borrownouseredpag_duetotime;
        public TextView tv_borrownouseredpag_moneylimit;
        public TextView tv_borrownouseredpag_timelimit;
        public TextView tv_borrownouseredpag_money;
        public ImageView iv_borrownouseredpag_state;
        public TextView tv_borrownouseredpag_typename;
        public BorrowNoUseRedPagHolder(View itemView){
            tv_borrownouseredpag_title = (TextView) itemView.findViewById(R.id.tv_borrownouseredpag_title);
            tv_borrownouseredpag_duetotime = (TextView) itemView.findViewById(R.id.tv_borrownouseredpag_duetotime);
            tv_borrownouseredpag_moneylimit = (TextView) itemView.findViewById(R.id.tv_borrownouseredpag_moneylimit);
            tv_borrownouseredpag_timelimit = (TextView) itemView.findViewById(R.id.tv_borrownouseredpag_timelimit);
            tv_borrownouseredpag_money = (TextView) itemView.findViewById(R.id.tv_borrownouseredpag_money);
            iv_borrownouseredpag_state = (ImageView) itemView.findViewById(R.id.iv_borrownouseredpag_state);
            tv_borrownouseredpag_typename = (TextView) itemView.findViewById(R.id.tv_borrownouseredpag_typename);
        }
    }
}
