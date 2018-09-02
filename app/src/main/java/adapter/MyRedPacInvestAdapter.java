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
import bean.RedPagListBean;

/**
 * Created by mayn on 2018/6/29.
 * 我的红包-- 投资券Adapter
 */

public class MyRedPacInvestAdapter extends DefaultLVAdapter<RedPagListBean.ResultBean>{
    public MyRedPacInvestAdapter(List mDatas, Context mContext) {
        super(mDatas, mContext);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyRedPacInvestHolder holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_lv_myredpac_invest,parent,false);
            holder = new MyRedPacInvestHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (MyRedPacInvestHolder) convertView.getTag();
        }
        holder.tv_myredpac_invest_title.setText(mDatas.get(position).getRedpkg_name());
        holder.tv_myredpac_invest_duetotime.setText(mDatas.get(position).getExpire_time()+" 到期");
        holder.tv_myredpac_invest_welfare.setText(mDatas.get(position).getUse_condition());
        holder.tv_myredpac_invest_project.setText(mDatas.get(position).getUse_remark());
        holder.tv_myredpac_invest_redtype.setText(mDatas.get(position).getUnit_type());
        holder.tv_myredpac_invest_money.setText(mDatas.get(position).getAmount());
        holder.ll_myredpac_invest_usenow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return convertView;
    }

    public class MyRedPacInvestHolder{
        public TextView tv_myredpac_invest_title;
        public TextView tv_myredpac_invest_duetotime;
        public TextView tv_myredpac_invest_welfare;
        public TextView tv_myredpac_invest_project;
        public TextView tv_myredpac_invest_money;
        public LinearLayout ll_myredpac_invest_usenow;
        public TextView tv_myredpac_invest_redtype;
        public  MyRedPacInvestHolder(View itemView){
            tv_myredpac_invest_title = (TextView) itemView.findViewById(R.id.tv_myredpac_invest_title);
            tv_myredpac_invest_duetotime = (TextView) itemView.findViewById(R.id.tv_myredpac_invest_duetotime);
            tv_myredpac_invest_welfare = (TextView) itemView.findViewById(R.id.tv_myredpac_invest_welfare);
            tv_myredpac_invest_project = (TextView) itemView.findViewById(R.id.tv_myredpac_invest_project);
            tv_myredpac_invest_money = (TextView) itemView.findViewById(R.id.tv_myredpac_invest_money);
            ll_myredpac_invest_usenow = (LinearLayout) itemView.findViewById(R.id.ll_myredpac_invest_usenow);
            tv_myredpac_invest_redtype = (TextView)itemView.findViewById(R.id.tv_myredpac_invest_redtype);
        }
    }
}
