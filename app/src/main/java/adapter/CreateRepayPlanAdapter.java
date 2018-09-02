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
import bean.CreateRepayPlanBean;

/**
 * Created by mayn on 2018/7/31.
 */

public class CreateRepayPlanAdapter extends DefaultLVAdapter<CreateRepayPlanBean> {
    public CreateRepayPlanAdapter(List mDatas, Context mContext) {
        super(mDatas, mContext);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CreateRepayPlanHolder holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_lv_createrepayplan,parent,false);
            holder = new CreateRepayPlanHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (CreateRepayPlanHolder) convertView.getTag();
        }
        if (mDatas.get(position).isCheck()){
            holder.iv_createrepayplan_item_check.setBackgroundResource(R.mipmap.iv_createplan_true);
        }else {
            holder.iv_createrepayplan_item_check.setBackgroundResource(R.mipmap.iv_createplan_false);
        }
        return convertView;
    }
    public class CreateRepayPlanHolder{
        public TextView tv_createrepayplan_item_limit;
        public TextView tv_createrepayplan_item_poundage;
        public TextView tv_createrepayplan_item_time;
        public ImageView iv_createrepayplan_item_check;
        public CreateRepayPlanHolder(View itemView){
            tv_createrepayplan_item_limit = (TextView)itemView.findViewById(R.id.tv_createrepayplan_item_limit);
            tv_createrepayplan_item_poundage = (TextView)itemView.findViewById(R.id.tv_createrepayplan_item_poundage);
            tv_createrepayplan_item_time = (TextView)itemView.findViewById(R.id.tv_createrepayplan_item_time);
            iv_createrepayplan_item_check = (ImageView)itemView.findViewById(R.id.iv_createrepayplan_item_check);
        }
    }
}
