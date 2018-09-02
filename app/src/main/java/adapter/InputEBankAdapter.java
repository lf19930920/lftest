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
 * Created by mayn on 2018/7/13.
 * 账单导入 -- 网银导入adapter
 */

public class InputEBankAdapter extends DefaultLVAdapter {
    public InputEBankAdapter(List mDatas, Context mContext) {
        super(mDatas, mContext);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        InputEBankHolder holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_lv_chooseebank,parent,false);
            holder = new InputEBankHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (InputEBankHolder) convertView.getTag();
        }
        return convertView;
    }
    public class InputEBankHolder{
        public ImageView iv_inputebank_icon;
        public TextView tv_inputebank_name;
        public InputEBankHolder(View itemView){
            iv_inputebank_icon = (ImageView) itemView.findViewById(R.id.iv_chooseebank_icon);
            tv_inputebank_name = (TextView) itemView.findViewById(R.id.tv_chooseebank_bankname);
        }
    }
}
