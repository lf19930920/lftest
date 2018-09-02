package adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xykgj.tx.xinyongkaguanjia.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import activity.MsgDetailActivity;
import bean.IsReadBean;
import bean.SystemNoticeBean;
import utils.ListToStringUtils;
import utils.SpUtils;
import view.MyListView;

/**
 * Created by mayn on 2018/6/25.
 * 系统通知adapter
 */

public class SystemNotifiAdapter extends BaseAdapter {
    private List<IsReadBean> sysList = new ArrayList<>();
    private List<SystemNoticeBean> data;
    private Context context;
    public SystemNotifiAdapter(List<SystemNoticeBean> data,Context context){
        this.data = data;
        this.context = context;
    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SystemNotifiHolder holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_lv_systemnotification,parent,false);
            holder = new SystemNotifiHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (SystemNotifiHolder) convertView.getTag();
        }
        holder.tv_systemnotification_date.setText(data.get(position).getDate());
        final SystemNotifiChildAdapter childAdapter = new SystemNotifiChildAdapter(data.get(position).getResult(), context);
        holder.lv_systemnotifi_child.setAdapter(childAdapter);
        holder.lv_systemnotifi_child.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SystemNoticeBean.SystemDetailBean childbean = (SystemNoticeBean.SystemDetailBean) parent.getAdapter().getItem(position);
                IsReadBean isReadBean = new IsReadBean();
                isReadBean.setId(childbean.getId());
                String sysmsgList = SpUtils.getString(context, "SYSMSGList");
                if (!TextUtils.isEmpty(sysmsgList)){
                    try {
                        List<IsReadBean> readBeanList = ListToStringUtils.String2SceneList(sysmsgList);
                        if (!readBeanList.contains(isReadBean)){
                            readBeanList.add(isReadBean);
                            SpUtils.saveString(context,"SYSMSGList",ListToStringUtils.SceneList2String(readBeanList));
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }else {
                    sysList.add(isReadBean);
                    try {
                        String list2String = ListToStringUtils.SceneList2String(sysList);
                        SpUtils.saveString(context,"SYSMSGList",list2String);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                childAdapter.notifyDataSetChanged();
                Intent systemnotifi = new Intent(context, MsgDetailActivity.class);
                systemnotifi.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(systemnotifi);
            }
        });
        return convertView;
    }

    public class SystemNotifiHolder{
        public TextView tv_systemnotification_date;
        public MyListView lv_systemnotifi_child;
        public SystemNotifiHolder(View itemView){
            tv_systemnotification_date = (TextView) itemView.findViewById(R.id.tv_systemnotification_date);
            lv_systemnotifi_child =(MyListView) itemView.findViewById(R.id.lv_systemnotifi_child);
        }
    }
}
