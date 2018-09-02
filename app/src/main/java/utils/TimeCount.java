package utils;

import android.os.CountDownTimer;
import android.widget.Button;

import com.xykgj.tx.xinyongkaguanjia.R;

/**
 * Created by mayn on 2018/6/19.
 */

public class TimeCount extends CountDownTimer {
    private Button button;
    public TimeCount(long millisInFuture, long countDownInterval, Button button) {
        super(millisInFuture, countDownInterval);
        this.button = button;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        button.setClickable(false);
        button.setText(millisUntilFinished/1000+"S");
        //更换背景
        button.setBackgroundResource(R.drawable.btn_daojishi_bg);
    }

    @Override
    public void onFinish() {
        button.setText("重新获取");
        button.setClickable(true);
        //更换背景
        button.setBackgroundResource(R.drawable.btn_allcredit_bg);
    }
}
