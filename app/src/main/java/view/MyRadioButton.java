package view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * Created by mayn on 2018/7/4.
 */

public class MyRadioButton extends RadioButton {


    public MyRadioButton(Context context) {
        super(context);
    }

    public MyRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    public void toggle() {
        setChecked(!isChecked());
        if (!isChecked()) {
            ((RadioGroup) getParent()).clearCheck();
        }
    }
    @Override
    protected void onDraw(Canvas canvas) {
        //获取设置的图片
        Drawable[] drawables = getCompoundDrawables();
        if (drawables != null) {
            //第一个是left
            Drawable drawableLeft = drawables[0];
            if (drawableLeft != null) {
                //获取文字的宽度
                float textWidth = getPaint().measureText(getText().toString());
                int drawablePadding = getCompoundDrawablePadding();
                int drawableWidth = 0;
                drawableWidth = drawableLeft.getIntrinsicWidth();
                float bodyWidth = textWidth + drawableWidth + drawablePadding;
                int y = getWidth();
                canvas.translate((getWidth() - bodyWidth) / 2, 0);
            }
        }
        super.onDraw(canvas);
//        Drawable[] drawables = getCompoundDrawables();
//        Drawable drawable = drawables[0];
//        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
//        int gravity = getGravity();
//        int left = 0;
//        if (gravity == Gravity.CENTER) {
//            left = ((int) (getWidth() / 2 - getPaint().measureText(getText().toString())));
//        }
//        drawable.setBounds(left,0,left+drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight());
    }
}
