package org.sqlite.utils.core;

import android.util.Log;

import org.sqlite.utils.annotations.DataMark;

import java.lang.reflect.Field;

/**
 * @Annotation 通用数据模型工具
 */
public class DataUtil {

    //使用这个函数来的到一个数据对象的数据标记
    public static String getMark(Object bean) {
        if (bean == null) {
            return "NULL";
        }
        Class[] classes = new Class[]{
                String.class,
                Integer.class, int.class, Long.class, long.class,
                Double.class, double.class, Float.class, float.class,
                Byte.class, byte.class, Byte[].class, byte.class
        };
        for (Class clazz : classes) {
            if (bean.getClass() == clazz) {
                return String.valueOf(bean);
            }
        }

        Field[] fields = bean.getClass().getFields();
        StringBuilder sb = new StringBuilder(bean.getClass().getSimpleName());
        for (Field f : fields) {
            if (!f.isAccessible()) {
                f.setAccessible(true);
            }
            String name = f.getName(); //这个属性的名字
            if (f.isAnnotationPresent(DataMark.class)) {
                try {
                    Object value = f.get(bean); //这个属性的值
                    sb.append("@").append(name).append("#{").append(getMark(value)).append("}");
                } catch (IllegalAccessException e) {
                    Log.e("getMark",
                            String.format("[getMark ignore%s DataMark] %s for no access right, " +
                            "please define it as public or private member",
                            bean.getClass().getSimpleName(), name));
                }
            }
        }
        return sb.toString();
    }
}
