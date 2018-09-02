package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.xykgj.tx.xinyongkaguanjia.R;

import java.util.List;

/**
 * Created by mayn on 2018/7/3.
 * 信用卡申请--热门银行adapter
 */

public class HotBankAdapter extends BaseAdapter {
    private Context mContext;
    private List<Integer> icons;
    private List<String> titles;
    private List<String> content;
    public HotBankAdapter(List<Integer> icons,List<String> titles,List<String> content,Context mContext) {
        this.icons = icons;
        this.titles = titles;
        this.content = content;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return 8;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HorBankViewHolder holder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_gv_hotbank,parent,false);
            holder = new HorBankViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (HorBankViewHolder) convertView.getTag();
        }
        holder.iv_hotbank_icon.setImageResource(icons.get(position));
        holder.tv_hotbank_title.setText(titles.get(position));
        holder.tv_hotbank_sale.setText(content.get(position));
        return convertView;
    }
    public class HorBankViewHolder{
        public ImageView iv_hotbank_icon;
        public TextView tv_hotbank_title;
        public TextView tv_hotbank_sale;
        public HorBankViewHolder(View itemView){
            iv_hotbank_icon = (ImageView) itemView.findViewById(R.id.iv_hotbank_icon);
            tv_hotbank_title = (TextView) itemView.findViewById(R.id.tv_hotbank_title);
            tv_hotbank_sale = (TextView) itemView.findViewById(R.id.tv_hotbank_sale);
        }
    }
}
