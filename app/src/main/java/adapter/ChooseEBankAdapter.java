package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xykgj.tx.xinyongkaguanjia.R;

import java.util.List;

import base.DefaultLVAdapter;
import bean.EBankListBean;

/**
 * Created by mayn on 2018/7/12.
 * 选择网银Adapter
 */

public class ChooseEBankAdapter extends DefaultLVAdapter<EBankListBean.ResultBean> {
    public ChooseEBankAdapter(List mDatas, Context mContext) {
        super(mDatas, mContext);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ChooseEBankHolder holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_lv_chooseebank,parent,false);
            holder = new ChooseEBankHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ChooseEBankHolder) convertView.getTag();
        }
        Glide.with(mContext).load(mDatas.get(position).getIcon()).into(holder.iv_chooseebank_icon);
        holder.tv_chooseebank_bankname.setText(mDatas.get(position).getBank_name());
        return convertView;
    }
    public class ChooseEBankHolder{
        public ImageView iv_chooseebank_icon;
        public TextView tv_chooseebank_bankname;
        public ChooseEBankHolder(View itemView){
            iv_chooseebank_icon = (ImageView) itemView.findViewById(R.id.iv_chooseebank_icon);
            tv_chooseebank_bankname = (TextView) itemView.findViewById(R.id.tv_chooseebank_bankname);
        }
    }
}
