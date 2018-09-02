package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.xykgj.tx.xinyongkaguanjia.R;

import java.util.List;

import base.DefaultLVAdapter;

/**
 * Created by mayn on 2018/7/12.
 * 贷款列表Adapter
 */

public class LoanListAdapter extends DefaultLVAdapter {
    public LoanListAdapter(List mDatas, Context mContext) {
        super(mDatas, mContext);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LoanHolder loanHolder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_lv_loanlist,parent,false);
            loanHolder = new LoanHolder(convertView);
            convertView.setTag(loanHolder);
        }else {
            loanHolder = (LoanHolder) convertView.getTag();
        }
        return convertView;
    }
    public class LoanHolder{
        public ImageView iv_loanlist_item_icon;
        public TextView tv_loanlist_item_title;
        public TextView tv_loanlist_item_monthinterest;
        public TextView tv_loanlist_item_applynum;
        public TextView tv_loanlist_item_maxlimit;
        public TextView tv_loanlist_item_accounttime;
        public Button btn_loanlist_item_applynow;
        public LoanHolder(View itemView){
            iv_loanlist_item_icon = (ImageView) itemView.findViewById(R.id.iv_loanlist_item_icon);
            tv_loanlist_item_title = (TextView) itemView.findViewById(R.id.tv_loanlist_item_title);
            tv_loanlist_item_monthinterest = (TextView) itemView.findViewById(R.id.tv_loanlist_item_monthinterest);
            tv_loanlist_item_applynum = (TextView) itemView.findViewById(R.id.tv_loanlist_item_applynum);
            tv_loanlist_item_maxlimit = (TextView) itemView.findViewById(R.id.tv_loanlist_item_maxlimit);
            tv_loanlist_item_accounttime = (TextView) itemView.findViewById(R.id.tv_loanlist_item_accounttime);
            btn_loanlist_item_applynow = (Button) itemView.findViewById(R.id.btn_loanlist_item_applynow);
        }
    }
}
