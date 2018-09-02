package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.xykgj.tx.xinyongkaguanjia.R;

import java.util.List;

import base.DefaultLVAdapter;
import bean.MailListBean;

/**
 * Created by mayn on 2018/7/12.
 * 选择邮箱Adapter
 */

public class ChooseMailAdapter extends DefaultLVAdapter<MailListBean.ResultBean> {
    public ChooseMailAdapter(List mDatas, Context mContext) {
        super(mDatas, mContext);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ChooseMailHolder holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_lv_choosemail,parent,false);
            holder = new ChooseMailHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ChooseMailHolder) convertView.getTag();
        }
        Glide.with(mContext).load(mDatas.get(position).getIcon()).into(holder.iv_choosemail_mailicon);
        return convertView;
    }
    public class ChooseMailHolder{
        public ImageView iv_choosemail_mailicon;
        public ChooseMailHolder(View itemView){
            iv_choosemail_mailicon = (ImageView) itemView.findViewById(R.id.iv_choosemail_mailicon);
        }
    }
}
