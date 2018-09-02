package adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xykgj.tx.xinyongkaguanjia.R;

import java.util.List;

import activity.DialogChooseActivity;
import base.DefaultLVAdapter;
import bean.CardListBean;

/**
 * Created by mayn on 2018/6/27.
 * 首页我的信用卡Adapter
 */

public class MyCreditCardAdapter extends DefaultLVAdapter<CardListBean.ResultBean>{
    public MyCreditCardAdapter(List mDatas, Context mContext) {
        super(mDatas, mContext);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        MyCreditCardHolder holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_lv_homepage_mycard,parent,false);
            holder = new MyCreditCardHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (MyCreditCardHolder) convertView.getTag();
        }
        holder.tv_homepage_bankname.setText(mDatas.get(position).getBank_name());
        holder.tv_homepage_banktail.setText("尾号"+mDatas.get(position).getTail_no());
        holder.tv_homepage_bankdate.setText(mDatas.get(position).getRepay_time());
        holder.tv_homepage_restmoney.setText("¥"+mDatas.get(position).getUnrepay_amount());
        holder.tv_homepage_dueto.setText(mDatas.get(position).getRepay_date());
        holder.ib_homepage_bankrepay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DialogChooseActivity.class);
                intent.putExtra("repaycardinfo",mDatas.get(position).getBank_name()+" ( "+mDatas.get(position).getTail_no()+" ) "+
                        mDatas.get(position).getReal_name());
                intent.putExtra("repaycardnum",mDatas.get(position).getTail_no());
                intent.putExtra("unrepayamout",mDatas.get(position).getUnrepay_amount());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });
        holder.tv_homepage_desc.setText(mDatas.get(position).getText_tips());
        Glide.with(mContext).load(mDatas.get(position).getIcon()).into(holder.iv_homepage_mycard);
        return convertView;
    }
    public class MyCreditCardHolder{
        public RelativeLayout rl_homepage_mycard;
        public ImageView iv_homepage_mycard;
        public TextView tv_homepage_bankname;
        public TextView tv_homepage_banktail;
        public TextView tv_homepage_restmoney;
        public TextView tv_homepage_dueto;
        public TextView tv_homepage_bankdate;
        public ImageButton ib_homepage_bankrepay;
        public TextView tv_homepage_desc;
        public MyCreditCardHolder(View itemView){
            rl_homepage_mycard = (RelativeLayout) itemView.findViewById(R.id.rl_homepage_mycard);
            iv_homepage_mycard = (ImageView) itemView.findViewById(R.id.iv_homepage_mycard);
            tv_homepage_bankname = (TextView) itemView.findViewById(R.id.tv_homepage_bankname);
            tv_homepage_banktail = (TextView) itemView.findViewById(R.id.tv_homepage_banktail);
            tv_homepage_restmoney = (TextView) itemView.findViewById(R.id.tv_homepage_restmoney);
            tv_homepage_dueto = (TextView) itemView.findViewById(R.id.tv_homepage_dueto);
            tv_homepage_bankdate = (TextView) itemView.findViewById(R.id.tv_homepage_bankdate);
            ib_homepage_bankrepay = (ImageButton) itemView.findViewById(R.id.ib_homepage_bankrepay);
            tv_homepage_desc = (TextView) itemView.findViewById(R.id.tv_homepage_desc);
        }
    }
}
