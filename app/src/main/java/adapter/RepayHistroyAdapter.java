package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.xykgj.tx.xinyongkaguanjia.R;

import java.util.List;

import bean.RepayHistroyBean;

/**
 * Created by mayn on 2018/7/6.
 * 信用卡详情--还款记录adapter
 */

public class RepayHistroyAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private List<RepayHistroyBean.ResultBean> mDatas;
    public RepayHistroyAdapter(Context mContext, List<RepayHistroyBean.ResultBean> mDatas){
        this.mContext = mContext;
        this.mDatas = mDatas;
    }
    @Override
    public int getGroupCount() {
        //父布局的数据个数
        return mDatas.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        //子布局的数据个数
        return mDatas.get(groupPosition).getList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        //返回父布局的object
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        //返回子布局的object
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        //父布局的每个条目的填充
        RepayHistroyParentHolder parentHolder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_exlv_group_repayhistroy,parent,false);
            parentHolder = new RepayHistroyParentHolder(convertView);
            convertView.setTag(parentHolder);
        }else {
            parentHolder = (RepayHistroyParentHolder) convertView.getTag();
        }
        if (mDatas.size()>0){
            parentHolder.tv_repayhistroy_group_day.setText(mDatas.get(groupPosition).getBill_cycle());
            parentHolder.tv_repayhistroy_group_month.setText(mDatas.get(groupPosition).getDate());
            parentHolder.tv_repayhistroy_group_money.setText(mDatas.get(groupPosition).getAmount());
        }
        if (isExpanded){
            parentHolder.iv_repayhistroy_group_arrow.setImageResource(R.mipmap.carddetail_arrow_down);
        }else {
            parentHolder.iv_repayhistroy_group_arrow.setImageResource(R.mipmap.carddetail_arrow_right);
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        //子布局的每个条目的填充
        RepayHistroyChildHolder childHolder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_exlv_child_repayhistroy,parent,false);
            childHolder = new RepayHistroyChildHolder(convertView);
            convertView.setTag(childHolder);
        }else {
            childHolder = (RepayHistroyChildHolder) convertView.getTag();
        }
        if (mDatas.size()>0){
            childHolder.tv_repayhistroy_child_title.setText(mDatas.get(groupPosition).getList().get(childPosition).getStatus_name());
            childHolder.tv_repayhistroy_child_time.setText(mDatas.get(groupPosition).getList().get(childPosition).getCreate_time());
            childHolder.tv_repayhistroy_child_type.setText(mDatas.get(groupPosition).getList().get(childPosition).getCard_type());
            childHolder.tv_repayhistroy_child_money.setText(mDatas.get(groupPosition).getList().get(childPosition).getAmount());
        }
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        //返回true代表子条目可以响应点击事件
        return true;
    }

    /**
     * 父布局的viewholder
     */
    public class RepayHistroyParentHolder{
        public TextView tv_repayhistroy_group_month;
        public TextView tv_repayhistroy_group_year;
        public TextView tv_repayhistroy_group_day;
        public ImageView iv_repayhistroy_group_arrow;
        public TextView tv_repayhistroy_group_money;
        public RepayHistroyParentHolder(View parentView){
            tv_repayhistroy_group_month = (TextView) parentView.findViewById(R.id.tv_repayhistroy_group_month);
            tv_repayhistroy_group_year = (TextView) parentView.findViewById(R.id.tv_repayhistroy_group_year);
            tv_repayhistroy_group_day = (TextView) parentView.findViewById(R.id.tv_repayhistroy_group_day);
            iv_repayhistroy_group_arrow = (ImageView) parentView.findViewById(R.id.iv_repayhistroy_group_arrow);
            tv_repayhistroy_group_money = (TextView) parentView.findViewById(R.id.tv_repayhistroy_group_money);
        }
    }

    /**
     * 子布局的viewholder
     */
    public class RepayHistroyChildHolder{
        public TextView tv_repayhistroy_child_title;
        public TextView tv_repayhistroy_child_time;
        public TextView tv_repayhistroy_child_money;
        public TextView tv_repayhistroy_child_type;
        public RepayHistroyChildHolder(View childView){
            tv_repayhistroy_child_title = (TextView) childView.findViewById(R.id.tv_repayhistroy_child_title);
            tv_repayhistroy_child_time = (TextView) childView.findViewById(R.id.tv_repayhistroy_child_time);
            tv_repayhistroy_child_money = (TextView) childView.findViewById(R.id.tv_repayhistroy_child_money);
            tv_repayhistroy_child_type = (TextView) childView.findViewById(R.id.tv_repayhistroy_child_type);
        }
    }
}
