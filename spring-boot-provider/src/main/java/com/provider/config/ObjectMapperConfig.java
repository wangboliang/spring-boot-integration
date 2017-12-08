package com.provider.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * jackson自定义配置
 * </p>
 *
 * @author wangliang
 * @since 2017/8/1
 */
@Configuration
public class ObjectMapperConfig {

    @Bean
    public ObjectMapper jsonMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS, true);
        //允许从非JSON数组值到单元素数组和集合（添加隐式“数组包装器”）的自动转换
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY,true);
        //允许空数组值（JSON中的“[]”）反序列化为空值(null)
        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);
        //允许空字符串值反序列化为常规POJO的空值(null)
        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        return objectMapper;
    }

}
