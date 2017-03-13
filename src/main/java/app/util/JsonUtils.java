package app.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class JsonUtils {

    private static final Logger log = LoggerFactory.getLogger(JsonUtils.class);
    public final static ObjectMapper mapper = getObjectMapper();

    private static ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }

    public static String toJsonStr(Object value) {
        try {
            return mapper.writeValueAsString(value);
        } catch (Exception e) {
            log.error("转换对象为JSON字符串异常", e);
        }
        return "";
    }

    public static Map<String, Object> toMap(String json) {
        try {
            return mapper.readValue(json, mapper.getTypeFactory().constructMapType(HashMap.class, String.class, Object.class));
        } catch (IOException e) {
            log.error("解析json出错!", e);
        }
        return Collections.emptyMap();
    }

    public static <T> T readValue(String context, Class<T> valueType) {
        try {
            return mapper.readValue(context, valueType);
        } catch (Exception e) {
            log.error("转化json字符串为对象异常", e);
        }
        return null;
    }


    public static <T> T readValue(String context, Class<? extends Collection> collectionClass, Class<?> elementCLasses) {
        try {
            //return  mapper.readValue(context, new TypeReference<SynRst<SynDepartment>>() {});
            return mapper.readValue(context, mapper.getTypeFactory().constructCollectionType(collectionClass, elementCLasses));
        } catch (Exception e) {
            log.error("转化json字符串为对象异常", e);
        }
        return null;
    }
}
