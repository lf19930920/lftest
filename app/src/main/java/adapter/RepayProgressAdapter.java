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
 *  还款进度adapter
 */

public class RepayProgressAdapter extends DefaultLVAdapter {
    public RepayProgressAdapter(List mDatas, Context mContext) {
        super(mDatas, mContext);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RepayProgressHolder holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_lv_repayprogress,parent,false);
            holder = new RepayProgressHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (RepayProgressHolder) convertView.getTag();
        }
        return convertView;
    }
    public class RepayProgressHolder{
        public TextView tv_repayprogress_money;
        public TextView tv_repayprogress_desc;
        public TextView tv_repayprogress_progress;
        public TextView tv_repayprogress_time;
        public RepayProgressHolder(View itemView){
            tv_repayprogress_money = (TextView) itemView.findViewById(R.id.tv_repayprogress_money);
            tv_repayprogress_desc = (TextView) itemView.findViewById(R.id.tv_repayprogress_desc);
            tv_repayprogress_progress = (TextView) itemView.findViewById(R.id.tv_repayprogress_progress);
            tv_repayprogress_time = (TextView) itemView.findViewById(R.id.tv_repayprogress_time);
        }
    }
}
