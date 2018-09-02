package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xykgj.tx.xinyongkaguanjia.R;
import com.zhouwei.mzbanner.holder.MZViewHolder;

/**
 * Created by mayn on 2018/7/3.
 * MZBannerView的ViewHolder,注意泛型
 */

public class MZBannerViewHolder implements MZViewHolder{
    private ImageView mImageView;
    private TextView mTextView;
    @Override
    public View createView(Context context) {
        // 返回页面布局
        View view = LayoutInflater.from(context).inflate(R.layout.mzbanner_item,null);
        mImageView = (ImageView) view.findViewById(R.id.mzbanner_image);
        mTextView = (TextView) view.findViewById(R.id.mzbanner_title);
        return view;
    }

    @Override
    public void onBind(Context context, int i, Object o) {
        // 数据绑定
//        mImageView.setImageResource(data);
//        mTextView.setText(titles.get(position));
    }
}
