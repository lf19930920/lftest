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
 * Created by mayn on 2018/7/4.
 * 还款金不可用红包 -- Adapter
 */

public class RepayNoPagUseAdapter extends DefaultLVAdapter<RedPagListBean.ResultBean> {

    public RepayNoPagUseAdapter(List mDatas, Context mContext) {
        super(mDatas, mContext);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RepayPagNoUsedViewHolder holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_lv_repaynouseredpag,parent,false);
            holder = new RepayPagNoUsedViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (RepayPagNoUsedViewHolder) convertView.getTag();
        }
        holder.tv_repaynoredpac_newgift.setText(mDatas.get(position).getRedpkg_name());
        holder.tv_repaynoredpac_date.setText(mDatas.get(position).getExpire_time()+" 到期");
        holder.tv_repaynoredpac_limit.setText(mDatas.get(position).getUse_condition());
        holder.tv_repaynoredpac_refund.setText(mDatas.get(position).getUse_remark());
        holder.tv_repaynoredpac_money.setText(mDatas.get(position).getAmount());
        if (mDatas.get(position).getStatus() == 1){
            holder.iv_repaynoredpac_state.setBackgroundResource(R.mipmap.noredpag_used);
        }else if (mDatas.get(position).getStatus() == 2){
            holder.iv_repaynoredpac_state.setBackgroundResource(R.mipmap.noredpag_overdue);
        }else {
            holder.iv_repaynoredpac_state.setBackgroundResource(R.mipmap.noredpag_used);
        }
        return convertView;
    }
    //还款金不可用红包holder
    public class RepayPagNoUsedViewHolder {
        public TextView tv_repaynoredpac_newgift;
        public TextView tv_repaynoredpac_date;
        public TextView tv_repaynoredpac_limit;
        public TextView tv_repaynoredpac_refund;
        public TextView tv_repaynoredpac_money;
        public TextView tv_repaynoredpac_beused;
        public ImageView iv_repaynoredpac_state;
        public RepayPagNoUsedViewHolder(View itemView){
            tv_repaynoredpac_newgift = (TextView) itemView.findViewById(R.id.tv_repaynoredpac_newgift);
            tv_repaynoredpac_date = (TextView) itemView.findViewById(R.id.tv_repaynoredpac_date);
            tv_repaynoredpac_limit = (TextView) itemView.findViewById(R.id.tv_repaynoredpac_limit);
            tv_repaynoredpac_refund = (TextView) itemView.findViewById(R.id.tv_repaynoredpac_refund);
            tv_repaynoredpac_money = (TextView) itemView.findViewById(R.id.tv_repaynoredpac_money);
            tv_repaynoredpac_beused = (TextView) itemView.findViewById(R.id.tv_repaynoredpac_beused);
            iv_repaynoredpac_state = (ImageView) itemView.findViewById(R.id.iv_repaynoredpac_state);
        }
    }
}
