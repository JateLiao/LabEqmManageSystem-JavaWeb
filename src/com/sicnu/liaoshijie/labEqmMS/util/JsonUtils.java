package com.sicnu.liaoshijie.labEqmMS.util;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * json辅助类.
 * 
 * @author xuanwei
 *
 */
public final class JsonUtils {

    /**
     * Gson.
     */
    private static Gson gson = new GsonBuilder().serializeNulls().setDateFormat("yyyy-MM-dd").create();

    /**
     * 构造函数.
     * 
     */
    private JsonUtils() {
    }

    /**
     * TODO toJson.
     * 
     * @param obj
     *            转换对象
     * @return string
     */
    public static String toJson(Object obj) {
        return gson.toJson(obj);
    }

    /**
     * TODO toObject.
     * 
     * @param json
     *            字符串
     * @param classOfT
     *            类
     * @param <T>
     *            返回类型
     * @return 类
     */
    public static <T> T toObject(String json, Class<T> classOfT) {
        return gson.fromJson(json, classOfT);
    }

    /**
     * TODO toObject.
     * 
     * @param json
     *            字符串
     * @param type
     *            类型
     * @param <T>
     *            返回类型
     * @return Response<T>
     */
    public static <T> T toObject(String json, Type type) {
        return gson.fromJson(json, type);
    }
}
