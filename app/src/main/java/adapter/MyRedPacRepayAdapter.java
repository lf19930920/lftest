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
import bean.RepayRedBean;

/**
 * Created by mayn on 2018/6/29.
 * 我的红包-- 还款金Adapter
 */

public class MyRedPacRepayAdapter extends DefaultLVAdapter<RepayRedBean.ResultBean> {
    public MyRedPacRepayAdapter(List mDatas, Context mContext) {
        super(mDatas, mContext);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyRedPacRepayHolder holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_lv_myredpac_repay,null);
            holder = new MyRedPacRepayHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (MyRedPacRepayHolder) convertView.getTag();
        }
        holder.tv_myredpac_repay_newvip.setText(mDatas.get(position).getRedpkg_name());
        holder.tv_myredpac_repay_duetotime.setText(mDatas.get(position).getExpire_time()+" 到期");
        holder.tv_myredpac_repay_limit.setText(mDatas.get(position).getUse_condition());
        holder.tv_myredpac_repay_support.setText(mDatas.get(position).getUse_remark());
        holder.tv_myredpac_repay_money.setText(mDatas.get(position).getAmount());
        return convertView;
    }
    public class MyRedPacRepayHolder{
        public TextView tv_myredpac_repay_newvip;
        public TextView tv_myredpac_repay_duetotime;
        public TextView tv_myredpac_repay_limit;
        public TextView tv_myredpac_repay_support;
        public TextView tv_myredpac_repay_money;
        public LinearLayout ll_myredpac_repay_usenow;
        public MyRedPacRepayHolder(View itemView){
            tv_myredpac_repay_newvip = (TextView) itemView.findViewById(R.id.tv_myredpac_repay_newvip);
            tv_myredpac_repay_duetotime = (TextView) itemView.findViewById(R.id.tv_myredpac_repay_duetotime);
            tv_myredpac_repay_limit = (TextView) itemView.findViewById(R.id.tv_myredpac_repay_limit);
            tv_myredpac_repay_support = (TextView) itemView.findViewById(R.id.tv_myredpac_repay_support);
            tv_myredpac_repay_money = (TextView) itemView.findViewById(R.id.tv_myredpac_repay_money);
            ll_myredpac_repay_usenow = (LinearLayout) itemView.findViewById(R.id.ll_myredpac_repay_usenow);
        }
    }
}
