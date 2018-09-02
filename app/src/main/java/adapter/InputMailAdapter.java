package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.xykgj.tx.xinyongkaguanjia.R;

import java.util.List;

import base.DefaultLVAdapter;

/**
 * Created by mayn on 2018/7/13.
 * 账单导入 -- 邮箱导入adapter
 */

public class InputMailAdapter extends DefaultLVAdapter {

    public InputMailAdapter(List mDatas, Context mContext) {
        super(mDatas, mContext);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        InputMailHolder holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_lv_choosemail,parent,false);
            holder = new InputMailHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (InputMailHolder) convertView.getTag();
        }
        return convertView;
    }
    public class InputMailHolder{
        public ImageView iv_inputmail_icon;
        public InputMailHolder(View itemView){
            iv_inputmail_icon = (ImageView) itemView.findViewById(R.id.iv_choosemail_mailicon);
        }
    }
}
