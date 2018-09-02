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
import bean.MsgTypeBean;

/**
 * Created by mayn on 2018/7/5.
 * 消息中心-- Adapter
 */

public class MSGCenterAdapter extends DefaultLVAdapter<MsgTypeBean.ResultBean> {
    public MSGCenterAdapter(List mDatas, Context mContext) {
        super(mDatas, mContext);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MSGCenterHolder holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_lv_msgcenter,parent,false);
            holder = new MSGCenterHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (MSGCenterHolder) convertView.getTag();
        }
        switch (position){
            case 0:
                holder.iv_msgcenter_icon.setImageResource(R.mipmap.msg_systemnotifi);
                break;
            case 1:
                holder.iv_msgcenter_icon.setImageResource(R.mipmap.msg_notice);
                break;
            case 2:
                holder.iv_msgcenter_icon.setImageResource(R.mipmap.msg_activacenter);
                break;
        }
        holder.tv_msgcenter_title.setText(mDatas.get(position).getName());
        holder.tv_msgcenter_content.setText(mDatas.get(position).getNewX().getTitle());
        holder.tv_msgcenter_date.setText(mDatas.get(position).getNewX().getCreate_time());
        return convertView;
    }
    public class MSGCenterHolder{
        public ImageView iv_msgcenter_icon;
//        public TextView tv_msgcenter_num;
        public TextView tv_msgcenter_title;
        public TextView tv_msgcenter_date;
        public TextView tv_msgcenter_content;
        public MSGCenterHolder(View itemView){
            iv_msgcenter_icon = (ImageView) itemView.findViewById(R.id.iv_msgcenter_icon);
//            tv_msgcenter_num = (TextView) itemView.findViewById(R.id.tv_msgcenter_num);
            tv_msgcenter_title = (TextView) itemView.findViewById(R.id.tv_msgcenter_title);
            tv_msgcenter_date = (TextView) itemView.findViewById(R.id.tv_msgcenter_date);
            tv_msgcenter_content = (TextView) itemView.findViewById(R.id.tv_msgcenter_content);
        }
    }
}
