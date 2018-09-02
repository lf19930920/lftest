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
import bean.MsgListBean;
import utils.ListToStringUtils;
import utils.SpUtils;

/**
 * Created by mayn on 2018/6/25.
 * 消息中心 -- 活动中心adapter
 */

public class ActiviteCenterAdapter extends DefaultLVAdapter<MsgListBean.ResultBean> {
    public ActiviteCenterAdapter(List mDatas, Context mContext) {
        super(mDatas, mContext);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ActiviteCenterHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_lv_activitecenter, parent, false);
            holder = new ActiviteCenterHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ActiviteCenterHolder) convertView.getTag();
        }
        String acmsgList_Str = SpUtils.getString(mContext, "ACMSGList");
        if (!TextUtils.isEmpty(acmsgList_Str)) {
            try {
                List<IsReadBean> list = ListToStringUtils.String2SceneList(acmsgList_Str);
                Log.i("adapter中从Sp取出的list", "list=" + list.size());
                for (int i = 0; i < list.size(); i++) {
                    if (mDatas.get(position).getId() == list.get(i).getId()) {
                        Log.i("当前条目的循环", "当前条目包含在存进的集合中");
                        holder.tv_activitecenter_title.setTextColor(Color.parseColor("#e6e6e6"));
                        holder.tv_activitecenter_time.setTextColor(Color.parseColor("#e6e6e6"));
                        holder.tv_activitecenter_content.setTextColor(Color.parseColor("#e6e6e6"));
                        continue;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }

        holder.tv_activitecenter_title.setText(mDatas.get(position).getTitle());
        holder.tv_activitecenter_time.setText(mDatas.get(position).getCreate_time());
        holder.tv_activitecenter_content.setText(mDatas.get(position).getDescription());
        return convertView;
    }

    public class ActiviteCenterHolder {
        public ImageView iv_activitecenter_icon;
        public TextView tv_activitecenter_title;
        public TextView tv_activitecenter_content;
        public TextView tv_activitecenter_time;

        public ActiviteCenterHolder(View itemView) {
            iv_activitecenter_icon = (ImageView) itemView.findViewById(R.id.iv_activitecenter_icon);
            tv_activitecenter_title = (TextView) itemView.findViewById(R.id.tv_activitecenter_title);
            tv_activitecenter_content = (TextView) itemView.findViewById(R.id.tv_activitecenter_content);
            tv_activitecenter_time = (TextView) itemView.findViewById(R.id.tv_activitecenter_time);
        }
    }
}
