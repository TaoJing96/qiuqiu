package qiuqiu.util;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

/**
 * @author Jing Tao
 * @date 2023/1/11 21:57
 */
public class JsonUtil {

    private static final Gson GSON = new Gson();

    public static String toJson(Object obj) {
        return GSON.toJson(obj);
    }

    public static <T> T fromJson(String json, Class<T> clz) {
        return GSON.fromJson(json, clz);
    }

    public static <T> List<T> listFromJson(String json, Class<T[]> clz) {
        T[] arr = (T[]) GSON.fromJson(json, clz);
        return Arrays.asList(arr);
    }
}
