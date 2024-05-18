package com.georgeinfo.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Jackson Json封装工具类
 * 2024/5/18 22:19
 *
 * @author George
 * @version 1.0
 */
@Slf4j
public class JsonUtil {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 默认时间日期格式化格式
     */
    private static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /** Jackson 属性初始化 */
    static {
        /** 对象的所有字段全部列入序列化 */
        MAPPER.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        /** 日期时间类型，自动转化成timestamps时间戳数字 */
        MAPPER.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true);
//        /** 将日期时间类型，序列化成制定格式的字符串：yyyy-MM-dd HH:mm:ss */
//        MAPPER.setDateFormat(new SimpleDateFormat(DEFAULT_DATETIME_FORMAT));
        /** 忽略空Bean转json的错误 */
        MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        /** 忽略JSON字符串比Java对象多出来的属性 */
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }


    //将JSON字符串反序列化为Java对象
    public static <T> T parseObject(String json, Class<T> clazz) {
        if (StringUtils.isBlank(json)) {
            log.error("待转换的JSON字符串不能为空");
            return null;
        }
        T t = null;
        try {
            t = MAPPER.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            log.error("将JSON字符串：{}转换为对象时出现异常", json, e);
        }
        return t;
    }

    public static <T> T parseObject(File file, Class<T> clazz) {
        if (file == null) {
            log.error("待转换的JSON文件不能为空");
            return null;
        }
        T t = null;
        try {
            t = MAPPER.readValue(file, clazz);
        } catch (IOException e) {
            log.error("将File：{}转换为对象时出现异常：{}", file.getPath(), e);
        }
        return t;
    }

    /**
     * JSON字符串转换为LIST
     */
    public static <T> List<T> parseList(String json) {
        if (StringUtils.isBlank(json)) {
            log.error("待转换的JSON数组字符串不能为空");
            return null;
        }
        List<T> t = null;
        try {
            t = MAPPER.readValue(json, new TypeReference<List<T>>() {
            });
        } catch (JsonProcessingException e) {
            log.error("将JSON数组:{}转换为LIST时出现异常：{}", json, e);
        }
        return t;
    }

    public static <K, V> Map<K, V> parseMap(String json) {
        if (StringUtils.isBlank(json)) {
            log.error("待转换的JSON Map字符串不能为空");
            return null;
        }
        Map<K, V> t = null;
        try {
            t = MAPPER.readValue(json, new TypeReference<Map<K, V>>() {
            });
        } catch (JsonProcessingException e) {
            log.error("将JSON map：{}转换为MAP对象时出现异常", json, e);
        }
        return t;
    }


    //将Java对象序列化为JSON字符串
    public static <T> String toJSONString(T object) {
        String json = null;
        try {
            json = MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("将对象转换为JSON字符串时出现异常", e);
        }
        return json;
    }

    public static <T> String toJSONString(List<T> list) {
        String json = null;
        try {
            json = MAPPER.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            log.error("将对象list转换为JSON字符串时出现异常", e);
        }
        return json;
    }

    public static <K, V> String toJSONString(Map<K, V> map) {
        String json = null;
        try {
            json = MAPPER.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            log.error("将对象map转换为JSON字符串时出现异常", e);
        }
        return json;
    }

    public static byte[] toByteArray(Object object) {
        byte[] bytes = null;
        try {
            bytes = MAPPER.writeValueAsBytes(object);
        } catch (JsonProcessingException e) {
            log.error("将对象转化为字节数组时出现异常", e);
        }
        return bytes;
    }

    public static void objectToFile(File file, Object object) {
        try {
            MAPPER.writeValue(file, object);
        } catch (Exception e) {
            log.error("将对象写入文件时出现异常", e);
        }
    }


    //JSON辅助方法
    public static JsonNode parseJSONObject(String jsonString) {
        JsonNode jsonNode = null;
        try {
            jsonNode = MAPPER.readTree(jsonString);
        } catch (JsonProcessingException e) {
            log.error("JSONString转为JsonNode时出现异常", e);
        }
        return jsonNode;
    }

    public static JsonNode parseJSONObject(Object object) {
        JsonNode jsonNode = MAPPER.valueToTree(object);
        return jsonNode;
    }

    public static String toJSONString(JsonNode jsonNode) {
        String jsonString = null;
        try {
            jsonString = MAPPER.writeValueAsString(jsonNode);
        } catch (JsonProcessingException e) {
            log.error("JsonNode转JSONString时出现异常", e);
        }
        return jsonString;
    }

    public static ObjectNode newJSONObject() {
        return MAPPER.createObjectNode();
    }

    public static ArrayNode newJSONArray() {
        return MAPPER.createArrayNode();
    }


    public static String getString(JsonNode jsonObject, String key) {
        String s = jsonObject.get(key).asText();
        return s;
    }

    public static Integer getInteger(JsonNode jsonObject, String key) {
        Integer i = jsonObject.get(key).asInt();
        return i;
    }

    public static Boolean getBoolean(JsonNode jsonObject, String key) {
        Boolean bool = jsonObject.get(key).asBoolean();
        return bool;
    }

    public static JsonNode getJSONObject(JsonNode jsonObject, String key) {
        JsonNode json = jsonObject.get(key);
        return json;
    }
}

