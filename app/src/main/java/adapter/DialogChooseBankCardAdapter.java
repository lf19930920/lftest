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

/**
 * Created by mayn on 2018/7/19.
 *  付款Dialog --- 选择银行卡付款
 */

public class DialogChooseBankCardAdapter extends DefaultLVAdapter {
    public DialogChooseBankCardAdapter(List mDatas, Context mContext) {
        super(mDatas, mContext);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ChooseBankCardHolder holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_lv_dialog,parent,false);
            holder = new ChooseBankCardHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ChooseBankCardHolder) convertView.getTag();
        }
        return convertView;
    }
    public class ChooseBankCardHolder{
        public ImageView iv_chooseBankCard_icon;
        public TextView tv_chooseBankCard_title;
        public TextView tv_chooseBankCard_limit;
        public ImageView iv_chooseBankCard_checked;
        public ChooseBankCardHolder(View itemView){
            iv_chooseBankCard_icon = (ImageView) itemView.findViewById(R.id.iv_chooseBankCard_icon);
            tv_chooseBankCard_title = (TextView) itemView.findViewById(R.id.tv_chooseBankCard_title);
            tv_chooseBankCard_limit = (TextView) itemView.findViewById(R.id.tv_chooseBankCard_limit);
            iv_chooseBankCard_checked = (ImageView) itemView.findViewById(R.id.iv_chooseBankCard_checked);
        }
    }
}
