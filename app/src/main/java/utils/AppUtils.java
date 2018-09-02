package utils;

import android.content.Context;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Created by mayn on 2018/6/19.
 */

public class AppUtils {
    /**
     * 校验手机号格式是否正确
     * @param str
     * @return
     * @throws PatternSyntaxException
     */
    public static boolean isChinaPhoneTrue(String str)
            throws PatternSyntaxException {
        String regExp = "^((13[0-9])|(15[^4])|(166)|(17[0-8])|(18[0-9])|(19[8-9])|(147,145))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 展示tosat
     * @param context
     * @param content
     */
    public static void showToast(Context context, String content){
        Toast.makeText(context,content, Toast.LENGTH_SHORT).show();
    }

    /**
     *   判断密码中是否包含特殊字符
     * @prama: str 要判断是否包含特殊字符的目标字符串
     *
     */
    public static boolean compileExChar(String str){
        String limitEx="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern pattern = Pattern.compile(limitEx);
        Matcher m = pattern.matcher(str);
        return m.find();
    }
}
