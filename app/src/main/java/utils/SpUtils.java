package utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

/**
 * Created by mayn on 2018/6/15.
 * Sp的工具类
 *
 */

public class SpUtils {

    /**
     * 保存string
     * @param context
     * @param key
     * @param value
     */
    public static void saveString(Context context,String key,String value){
        SharedPreferences sp = context.getSharedPreferences("config",context.MODE_PRIVATE);
        sp.edit().putString(key,value).commit();
    }
    /**
     * 获取String
     */
    public static String getString(Context context,String key){
        SharedPreferences sp = context.getSharedPreferences("config",context.MODE_PRIVATE);
        return sp.getString(key,"");
    }

    /**
     * 保存int
     * @param context
     * @param key
     * @param value
     */
    public static void saveInt(Context context, String key, int value){
        SharedPreferences sp = context.getSharedPreferences("config",context.MODE_PRIVATE);
        sp.edit().putInt(key,value).commit();
    }

    /**
     * 获取int
     * @param context
     * @param key
     * @return
     */
    public static int getInt(Context context, String key){
        SharedPreferences sp = context.getSharedPreferences("config",context.MODE_PRIVATE);
        return sp.getInt(key,0);
    }

    /**
     * 删除指定数据
     * @param context
     * @param key
     */
    public static void delete(Context context,String key){
        SharedPreferences sp = context.getSharedPreferences("config",context.MODE_PRIVATE);
        sp.edit().remove(key).commit();
    }

    /**
     * 清空所有数据
     * @param context
     */
    public static void clear(Context context){
        SharedPreferences sp = context.getSharedPreferences("config",context.MODE_PRIVATE);
        sp.edit().clear().commit();
    }
    /**
     * 针对复杂类型存储<对象>
     *
     * @param key
     * @param object
     */
    public static void setObject(Context context,String key, Object object) {
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);

        //创建字节输出流
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        //创建字节对象输出流
        ObjectOutputStream out = null;
        try {
            //然后通过将字对象进行64转码，写入key值为key的sp中
            out = new ObjectOutputStream(baos);
            out.writeObject(object);
            String objectVal = new String(Base64.encode(baos.toByteArray(), Base64.DEFAULT));
            SharedPreferences.Editor editor = sp.edit();
            editor.putString(key, objectVal);
            editor.commit();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T getObject(Context context,String key, Class<T> clazz) {
        SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        if (sp.contains(key)) {
            String objectVal = sp.getString(key, null);
            byte[] buffer = Base64.decode(objectVal, Base64.DEFAULT);
            //一样通过读取字节流，创建字节流输入流，写入对象并作强制转换
            ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
            ObjectInputStream ois = null;
            try {
                ois = new ObjectInputStream(bais);
                T t = (T) ois.readObject();
                return t;
            } catch (StreamCorruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (bais != null) {
                        bais.close();
                    }
                    if (ois != null) {
                        ois.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
