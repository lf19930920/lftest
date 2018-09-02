package adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xykgj.tx.xinyongkaguanjia.R;

import java.util.List;

import base.DefaultLVAdapter;
import view.MyListView;

/**
 * Created by mayn on 2018/6/29.
 * 消息中心 -- 公告adapter
 */

public class NoticeAdapter extends DefaultLVAdapter {
    public NoticeAdapter(List mDatas, Context mContext) {
        super(mDatas, mContext);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NoticeHolder holder = null;
        if (convertView == null){
            //convertView = LayoutInflater.from(mContext).inflate()
        }
        return null;
    }
    public class NoticeHolder{
        private TextView tv_notice_date;
        private MyListView lv_notice;
        public NoticeHolder(View itemView){
            tv_notice_date = (TextView)itemView.findViewById(R.id.tv_systemnotification_date);
            lv_notice = (MyListView)itemView.findViewById(R.id.lv_systemnotifi_child);
        }
    }
}
