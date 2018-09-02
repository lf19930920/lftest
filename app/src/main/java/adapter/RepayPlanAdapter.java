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
 * Created by mayn on 2018/7/31.
 *  还款计划Activity
 */

public class RepayPlanAdapter extends DefaultLVAdapter {
    public RepayPlanAdapter(List mDatas, Context mContext) {
        super(mDatas, mContext);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RepayPlanHolder holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_lv_repayplan,parent,false);
            holder = new RepayPlanHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (RepayPlanHolder) convertView.getTag();
        }
        return convertView;
    }
    public class RepayPlanHolder{
        public TextView tv_repayplan_money;
        public TextView tv_repayplan_desc;
        public TextView tv_repayplan_time;
        public RepayPlanHolder(View itemView){
            tv_repayplan_money = (TextView)itemView.findViewById(R.id.tv_repayplan_money);
            tv_repayplan_desc = (TextView)itemView.findViewById(R.id.tv_repayplan_desc);
            tv_repayplan_time = (TextView)itemView.findViewById(R.id.tv_repayplan_time);
        }
    }
}
