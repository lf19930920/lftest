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
 * 投资券不可用红包Adapter
 */

public class InvestNoUseRedPagAdapter extends DefaultLVAdapter<RedPagListBean.ResultBean>{
    public InvestNoUseRedPagAdapter(List mDatas, Context mContext) {
        super(mDatas, mContext);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        InvestNoUseRedPagHolder holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_lv_investnouseredpag,parent,false);
            holder = new InvestNoUseRedPagHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (InvestNoUseRedPagHolder) convertView.getTag();
        }
        holder.tv_investnouseredpag_title.setText(mDatas.get(position).getRedpkg_name());
        holder.tv_investnouseredpag_duetotime.setText(mDatas.get(position).getExpire_time());
        holder.tv_investnouseredpag_money.setText(mDatas.get(position).getAmount());
        holder.tv_investnouseredpag_project.setText(mDatas.get(position).getUse_remark());
        holder.tv_investnouseredpag_welfare.setText(mDatas.get(position).getUse_condition());
        holder.tv_investnouseredpag_typename.setText(mDatas.get(position).getUnit_type());
        if (mDatas.get(position).getStatus() == 1){
            holder.iv_investnouseredpag_state.setBackgroundResource(R.mipmap.noredpag_used);
        }else if (mDatas.get(position).getStatus() == 2){
            holder.iv_investnouseredpag_state.setBackgroundResource(R.mipmap.noredpag_overdue);
        }else {
            holder.iv_investnouseredpag_state.setBackgroundResource(R.mipmap.noredpag_used);
        }
        return convertView;
    }
    public class InvestNoUseRedPagHolder{
        public TextView tv_investnouseredpag_title;
        public TextView tv_investnouseredpag_duetotime;
        public TextView tv_investnouseredpag_welfare;
        public TextView tv_investnouseredpag_project;
        public TextView tv_investnouseredpag_money;
        public ImageView iv_investnouseredpag_state;
        public TextView tv_investnouseredpag_typename;
        public InvestNoUseRedPagHolder(View itemView){
            tv_investnouseredpag_title = (TextView) itemView.findViewById(R.id.tv_investnouseredpag_title);
            tv_investnouseredpag_duetotime = (TextView) itemView.findViewById(R.id.tv_investnouseredpag_duetotime);
            tv_investnouseredpag_welfare = (TextView) itemView.findViewById(R.id.tv_investnouseredpag_welfare);
            tv_investnouseredpag_project = (TextView) itemView.findViewById(R.id.tv_investnouseredpag_project);
            tv_investnouseredpag_money = (TextView) itemView.findViewById(R.id.tv_investnouseredpag_money);
            iv_investnouseredpag_state = (ImageView) itemView.findViewById(R.id.iv_investnouseredpag_state);
            tv_investnouseredpag_typename = (TextView)itemView.findViewById(R.id.tv_investnouseredpag_typename);
        }
    }
}
