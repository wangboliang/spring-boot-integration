package com.company.provider.utils;

import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Json序列化工具
 * </p>
 *
 * @author wangliang
 * @since 2017/8/1
 */
@Component
public class JsonUtil {

    private static ObjectMapper mapper;

    @Autowired
    public void setMapper(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public static <T> String objectToJson(T data) {
        String json = null;
        if (data != null) {
            try {
                json = mapper.writeValueAsString(data);
            } catch (Exception e) {
                throw new RuntimeException("objectToJson method error: " + e);
            }
        }
        return json;
    }

    public static <T> T jsonToObject(String json, Class<T> cls) {
        T object = null;
        if (StringUtils.isNotEmpty(json) && cls != null) {
            try {
                if (json.startsWith("\"{")) {
                    json = json.replace("\"{", "{").replace("}\"", "}").replace("\\\"", "\"");
                }
                object = mapper.readValue(json, cls);
            } catch (Exception e) {
                throw new RuntimeException("objectToJson method error: " + e);
            }
        }
        return object;
    }

    public static List<Map<String, Object>> jsonToList(String json) {
        List<Map<String, Object>> list = null;
        if (StringUtils.isNotEmpty(json)) {
            try {
                if (json.startsWith("\"[")) {
                    json = json.replace("\"[", "[").replace("]\"", "]").replace("\\\"", "\"");
                }
                list = mapper.readValue(json, List.class);
            } catch (Exception e) {
                throw new RuntimeException("objectToJson method error: " + e);
            }
        }
        return list;
    }

    public static Map<String, Object> jsonToMap(String json) {
        Map<String, Object> maps = null;
        if (StringUtils.isNotEmpty(json)) {
            try {
                if (json.startsWith("\"{")) {
                    json = json.replace("\"{", "{").replace("}\"", "}").replace("\\\"", "\"");
                }
                maps = mapper.readValue(json, Map.class);
            } catch (Exception e) {
                throw new RuntimeException("objectToJson method error: " + e);
            }
        }
        return maps;
    }
}
