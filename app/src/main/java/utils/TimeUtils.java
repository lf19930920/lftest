package utils;

/**
 * Created by mayn on 2018/6/26.
 * 时间工具类
 */

public class TimeUtils {
    //获取13位字符串格式的时间戳
    public static String getTime13() {

        long time = System.currentTimeMillis();

        String str13 = String.valueOf(time);

        return str13;

    }
    //获取10位字符串格式的时间戳
    public static String getTime() {

        long time = System.currentTimeMillis() / 1000;//获取系统时间的10位的时间戳

        String str = String.valueOf(time);

        return str;

    }
}
