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
 * Created by mayn on 2018/7/23.
 *  信用卡申请 --- 全部信用卡Adapter
 */

public class AllCreditAdapter extends DefaultLVAdapter {
    public AllCreditAdapter(List mDatas, Context mContext) {
        super(mDatas, mContext);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AllCreditHolder holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_lv_allcredit,parent,false);
            holder = new AllCreditHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (AllCreditHolder) convertView.getTag();
        }
        return convertView;
    }
    public class AllCreditHolder{
        public ImageView iv_allcredit_lv_icon;
        public TextView tv_allcredit_lv_title;
        public TextView tv_allcredit_lv_gift;
        public Button btn_allcredit_lv_applynow;
        public AllCreditHolder(View itemView){
            iv_allcredit_lv_icon = (ImageView) itemView.findViewById(R.id.iv_allcredit_lv_icon);
            tv_allcredit_lv_title = (TextView) itemView.findViewById(R.id.tv_allcredit_lv_title);
            tv_allcredit_lv_gift = (TextView) itemView.findViewById(R.id.tv_allcredit_lv_gift);
            btn_allcredit_lv_applynow = (Button) itemView.findViewById(R.id.btn_allcredit_lv_applynow);
        }
    }
}
