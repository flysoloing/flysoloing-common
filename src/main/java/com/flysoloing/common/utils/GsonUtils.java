package com.flysoloing.common.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Reader;
import java.lang.reflect.Type;

/**
 * Gson工具类<br>
 * User: flysoloing <br>
 * Date: 15-3-6 <br>
 * Time: 上午12:48
 */
public class GsonUtils {

    private static final Gson GSON = createGson(true);

    private static final Gson GSON_NO_NULLS = createGson(false);

    /**
     * Create the standard {@link Gson} configuration
     *
     * @return created gson, never null
     */
    public static Gson createGson() {
        return createGson(true);
    }

    /**
     * Create the standard {@link Gson} configuration
     *
     * @param serializeNulls
     *            whether nulls should be serialized
     *
     * @return created gson, never null
     */
    public static Gson createGson(final boolean serializeNulls) {
        final GsonBuilder builder = new GsonBuilder();
        //移除注册日期类型适配器
        //builder.registerTypeAdapter(Date.class, new DateFormatter());
        //移除注册事件类型适配器
        //builder.registerTypeAdapter(Event.class, new EventFormatter());
        //移除字段命名策略
        //builder.setFieldNamingPolicy(LOWER_CASE_WITH_UNDERSCORES);
        if (serializeNulls)
            builder.serializeNulls();
        return builder.create();
    }

    /**
     * Get reusable pre-configured {@link Gson} instance
     *
     * @return Gson instance
     */
    public static Gson getGson() {
        return GSON;
    }

    /**
     * Get reusable pre-configured {@link Gson} instance
     *
     * @param serializeNulls
     * @return Gson instance
     */
    public static Gson getGson(final boolean serializeNulls) {
        return serializeNulls ? GSON : GSON_NO_NULLS;
    }

    /**
     * Convert object to json
     *
     * @param object
     * @return json string
     */
    public static String toJson(final Object object) {
        return toJson(object, true);
    }

    /**
     * Convert object to json
     *
     * @param object
     * @param includeNulls
     * @return json string
     */
    public static String toJson(final Object object,
                                      final boolean includeNulls) {
        return includeNulls ? GSON.toJson(object) : GSON_NO_NULLS
                .toJson(object);
    }

    /**
     * Convert string to given type
     *
     * @param json
     * @param type
     * @return instance of type
     */
    public static <V> V fromJson(String json, Class<V> type) {
        return GSON.fromJson(json, type);
    }

    /**
     * Convert string to given type
     *
     * @param json
     * @param type
     * @return instance of type
     */
    public static <V> V fromJson(String json, Type type) {
        return GSON.fromJson(json, type);
    }

    /**
     * Convert content of reader to given type
     *
     * @param reader
     * @param type
     * @return instance of type
     */
    public static <V> V fromJson(Reader reader, Class<V> type) {
        return GSON.fromJson(reader, type);
    }

    /**
     * Convert content of reader to given type
     *
     * @param reader
     * @param type
     * @return instance of type
     */
    public static <V> V fromJson(Reader reader, Type type) {
        return GSON.fromJson(reader, type);
    }
}
