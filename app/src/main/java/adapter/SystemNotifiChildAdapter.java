package adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xykgj.tx.xinyongkaguanjia.R;

import java.io.IOException;
import java.util.List;

import base.DefaultLVAdapter;
import bean.IsReadBean;
import bean.SystemNoticeBean;
import utils.ListToStringUtils;
import utils.SpUtils;

/**
 * Created by mayn on 2018/8/1.
 *  系统通知子条目adapter
 */

public class SystemNotifiChildAdapter extends DefaultLVAdapter<SystemNoticeBean.SystemDetailBean> {
    public SystemNotifiChildAdapter(List mDatas, Context mContext) {
        super(mDatas, mContext);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SystemNotifiChildHolder childHolder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_lv_systemnotifi_child,parent,false);
            childHolder = new SystemNotifiChildHolder(convertView);
            convertView.setTag(childHolder);
        }else {
            childHolder = (SystemNotifiChildHolder) convertView.getTag();
        }
        String acmsgList_Str = SpUtils.getString(mContext, "SYSMSGList");
        if (!TextUtils.isEmpty(acmsgList_Str)) {
            try {
                List<IsReadBean> list = ListToStringUtils.String2SceneList(acmsgList_Str);
                Log.i("adapter中从Sp取出的list", "list=" + list.size());
                for (int i = 0; i < list.size(); i++) {
                    if (mDatas.get(position).getId() == list.get(i).getId()) {
                        Log.i("当前条目的循环", "当前条目包含在存进的集合中");
                        childHolder.tv_systemnotification_title.setTextColor(Color.parseColor("#e6e6e6"));
                        childHolder.tv_systemnotification_daoqi.setTextColor(Color.parseColor("#e6e6e6"));
                        childHolder.tv_systemnotification_desc.setTextColor(Color.parseColor("#e6e6e6"));
                        childHolder.iv_systemnotification_isreadtag.setVisibility(View.GONE);
                        continue;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
        childHolder.tv_systemnotification_title.setText(mDatas.get(position).getTitle());
        childHolder.tv_systemnotification_daoqi.setText(mDatas.get(position).getCreate_time());
        childHolder.tv_systemnotification_desc.setText(mDatas.get(position).getDescription());
        return convertView;
    }
    public class SystemNotifiChildHolder{
        public ImageView iv_systemnotification_isreadtag;
        public TextView tv_systemnotification_title;
        public TextView tv_systemnotification_daoqi;
        public TextView tv_systemnotification_desc;
        public SystemNotifiChildHolder(View itemView){
            iv_systemnotification_isreadtag = (ImageView) itemView.findViewById(R.id.iv_systemnotification_isreadtag);
            tv_systemnotification_title = (TextView) itemView.findViewById(R.id.tv_systemnotification_title);
            tv_systemnotification_daoqi = (TextView) itemView.findViewById(R.id.tv_systemnotification_daoqi);
            tv_systemnotification_desc = (TextView) itemView.findViewById(R.id.tv_systemnotification_desc);
        }
    }
}
