package adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.xykgj.tx.xinyongkaguanjia.R;

import java.util.List;

import bean.BillDetailChildBean;
import bean.BillDetailGroupBean;

/**
 * Created by mayn on 2018/7/9.
 *  我的信用卡详情 -- 账单明细EXpandableListViewAdapter
 */

public class BillDetailAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private List<BillDetailGroupBean.ResultBean> groupList;
    private List<BillDetailChildBean.ResultBean> childList;
    public BillDetailAdapter(Context mContext, List<BillDetailGroupBean.ResultBean> groupList,
                             List<BillDetailChildBean.ResultBean> childList){
        this.mContext = mContext;
        this.groupList = groupList;
        this.childList = childList;
    }
    @Override
    public int getGroupCount() {
        return groupList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        Log.i("exlistview adapter", "childList="+childList.size());
        return childList.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childList.get(childPosition);
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
        BillGroupHolder groupHolder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.group_lv_billdetail,parent,false);
            groupHolder = new BillGroupHolder(convertView);
            convertView.setTag(groupHolder);
        }else {
            groupHolder = (BillGroupHolder) convertView.getTag();
        }
        groupHolder.tv_billdetail_group_month.setText(groupList.get(groupPosition).getMonth()+"月");
        groupHolder.tv_billdetail_group_year.setText(groupList.get(groupPosition).getYear()+"");
        groupHolder.tv_billdetail_group_day.setText(groupList.get(groupPosition).getBill_cycle());
        groupHolder.tv_billdetail_group_money.setText(groupList.get(groupPosition).getBill_amount());
        if (isExpanded){
            groupHolder.iv_billdetail_group_arrow.setImageResource(R.mipmap.carddetail_arrow_down);
        }else {
            groupHolder.iv_billdetail_group_arrow.setImageResource(R.mipmap.carddetail_arrow_right);
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        BillChildHolder childHolder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_lv_billdetail,parent,false);
            childHolder = new BillChildHolder(convertView);
            convertView.setTag(childHolder);
        }else {
            childHolder = (BillChildHolder) convertView.getTag();
        }
        if (childList.size()>0){
            childHolder.tv_billdetail_item_title.setText(childList.get(childPosition).getTrade_info());
            childHolder.tv_billdetail_item_time.setText(childList.get(childPosition).getTrade_time());
            childHolder.tv_billdetail_item_type.setText(childList.get(childPosition).getType_name());
            childHolder.tv_billdetail_item_money.setText(childList.get(childPosition).getAmount());
        }
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    /**
     * 父布局holder
     */
    public class BillGroupHolder{
        public TextView tv_billdetail_group_month;
        public TextView tv_billdetail_group_year;
        public TextView tv_billdetail_group_day;
        public ImageView iv_billdetail_group_arrow;
        public TextView tv_billdetail_group_money;
        public BillGroupHolder(View groupView){
            tv_billdetail_group_month = (TextView) groupView.findViewById(R.id.tv_billdetail_group_month);
            tv_billdetail_group_year = (TextView) groupView.findViewById(R.id.tv_billdetail_group_year);
            tv_billdetail_group_day = (TextView) groupView.findViewById(R.id.tv_billdetail_group_day);
            iv_billdetail_group_arrow = (ImageView) groupView.findViewById(R.id.iv_billdetail_group_arrow);
            tv_billdetail_group_money = (TextView) groupView.findViewById(R.id.tv_billdetail_group_money);
        }
    }
    /**
     * 子布局holder
     */
    public class BillChildHolder{
        public TextView tv_billdetail_item_title;
        public TextView tv_billdetail_item_time;
        public TextView tv_billdetail_item_type;
        public TextView tv_billdetail_item_money;
        public BillChildHolder(View itemView){
            tv_billdetail_item_title = (TextView) itemView.findViewById(R.id.tv_billdetail_item_title);
            tv_billdetail_item_time = (TextView) itemView.findViewById(R.id.tv_billdetail_item_time);
            tv_billdetail_item_type = (TextView) itemView.findViewById(R.id.tv_billdetail_item_type);
            tv_billdetail_item_money = (TextView) itemView.findViewById(R.id.tv_billdetail_item_money);
        }
    }
}
