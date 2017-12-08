package com.provider.utils;

import com.google.protobuf.Descriptors;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.Message;
import com.google.protobuf.MessageOrBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.PropertyAccessor;
import org.springframework.beans.PropertyAccessorFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * <p>
 * GRpc对象和普通对象转换工具类
 * </p>
 *
 * @author wangliang
 * @since 2017/7/6
 */
@Slf4j
public class GRpcMessageConverter {

    private static Map<String, Callable<?>> primitivesMap;

    static {
        primitivesMap = new HashMap();
        primitivesMap.put("java.lang.Boolean", () -> new Boolean(false));
        primitivesMap.put("java.lang.Character", () -> new Character('0'));
        primitivesMap.put("java.lang.Byte", () -> new Byte("0"));
        primitivesMap.put("java.lang.Short", () -> new Short((short) 0));
        primitivesMap.put("java.lang.Integer", () -> new Integer(0));
        primitivesMap.put("java.lang.Long", () -> new Long(0L));
        primitivesMap.put("java.lang.Float", () -> new Float(0.0));
        primitivesMap.put("java.lang.Double", () -> new Double(0.0));
    }

    protected static Object getField(PropertyAccessor propertyAccessor, String name) {
        Object value = null;
        try {
            value = propertyAccessor.getPropertyValue(name);
            log.trace("Get Object field: {}, value: {}", name, value);
        } catch (Exception e) {
            log.trace("Get object field exception: {}", e);
        }
        return value;
    }

    protected static void setField(PropertyAccessor propertyAccessor, String name, Object value) {
        try {
            log.trace("Set Object field: {}, value: {}", name, value);
            propertyAccessor.setPropertyValue(name, value);
        } catch (Exception e) {
            log.trace("Set object field exception: {}", e);
        }
    }

    protected static void setField(GeneratedMessageV3.Builder builder, Descriptors.FieldDescriptor field, Object value) {
        try {
            log.trace("Set gRpc field: {}, value: {}", field.getName(), value);
            builder.setField(field, value);
        } catch (Exception e) {
            log.trace("Set gRpc field exception: {}", e);
        }
    }

    protected static void setRepeatedField(GeneratedMessageV3.Builder builder, Descriptors.FieldDescriptor field, Object value) {
        try {
            log.trace("Set gRpc repeated field: {}, value: {}", field.getName(), value);
            builder.addRepeatedField(field, value);
        } catch (Exception e) {
            log.trace("Set gRpc field exception: {}", e);
        }
    }

    protected static MessageOrBuilder getFieldBuilder(GeneratedMessageV3.Builder builder, Descriptors.FieldDescriptor field) {
        try {
            log.trace("Get gRpc field builder: {}", field.getName());
            return builder.newBuilderForField(field);
        } catch (Exception e) {
            log.trace("Set gRpc field exception: {}", e);
        }
        return null;
    }

    private static Object newInstance(Class<?> clazz) {
        Object object = null;

        Callable<?> getPrimitiveInstance = null;
        if (null != clazz) {
            getPrimitiveInstance = primitivesMap.get(clazz.getTypeName());
        }
        if (null != getPrimitiveInstance) {
            try {
                return getPrimitiveInstance.call();
            } catch (Exception e) {
                log.trace("{}", e);
            }
        }

        try {
            object = BeanUtils.instantiate(clazz);
        } catch (Exception e) {
            log.trace("{}", e);
        }

        return object;
    }


    public static List<Object> fromGRpcMessages(List<MessageOrBuilder> messages, Class<?> clazz) {
        List<Object> objects = new ArrayList<>();

        int i = 0;
        for (MessageOrBuilder message : messages) {
            Object value = fromGRpcMessage(message, clazz);
            objects.add(i++, value);
        }
        return objects;
    }

    public static Object fromGRpcMessage(MessageOrBuilder message, Class<?> clazz) {
        return fromGRpcMessage(message, clazz, null);
    }

    public static Object fromGRpcMessage(MessageOrBuilder message, Class<?> clazz, Class<?> genericClazz) {

        Object object = null;
        try {
            object = newInstance(clazz);
            PropertyAccessor propertyAccessor = PropertyAccessorFactory.forDirectFieldAccess(object);
            Descriptors.Descriptor fieldDescriptor = message.getDescriptorForType();
            for (Descriptors.FieldDescriptor field : fieldDescriptor.getFields()) {
                Object value = message.getField(field);
                String fieldName = field.getName();
                if (Descriptors.FieldDescriptor.Type.MESSAGE == field.getType()) {
                    String valueTypeName = value.getClass().getName();
                    if (("java.util.Collections$EmptyList").equals(valueTypeName)) {
                        continue;
                    } else if (("java.util.Collections$UnmodifiableRandomAccessList").equals(valueTypeName)) {
                        value = fromGRpcMessages((List<MessageOrBuilder>) value, genericClazz);
                    } else {
                        if ("".equals(value.toString())) {
                            continue;
                        }
                        Class<?> fieldType = propertyAccessor.getPropertyType(fieldName);
                        if (null != genericClazz && null != fieldType && ("java.lang.Object").equals(fieldType.getName())) {
                            fieldType = genericClazz;
                        }
                        value = fromGRpcMessage((MessageOrBuilder) value, fieldType, genericClazz);
                    }
                }

                setField(propertyAccessor, fieldName, value);
            }
        } catch (Exception e) {
            log.debug("Exception: {}", e);
        }

        return object;
    }

    public static void toGRpcMessages(List<?> objects, GeneratedMessageV3.Builder builder, Descriptors.FieldDescriptor field) {

        for (Object object : objects) {
            Message value = null;
            try {
                value = toGRpcMessage(object, getFieldBuilder(builder, field));
            } catch (Exception e) {
                log.debug("{}", e);
            }
            setRepeatedField(builder, field, value);
        }
    }

    public static Message toGRpcMessage(Object object, MessageOrBuilder message) {
        GeneratedMessageV3.Builder builder = (GeneratedMessageV3.Builder) message;
        try {

            if (null == object) {
                return builder.build().getDefaultInstanceForType();
            }
            PropertyAccessor propertyAccessor = PropertyAccessorFactory.forDirectFieldAccess(object);
            Descriptors.Descriptor fieldDescriptor = message.getDescriptorForType();
            for (Descriptors.FieldDescriptor field : fieldDescriptor.getFields()) {
                Object value = getField(propertyAccessor, field.getName());
                if (null == value) {
                    continue;
                }
                Boolean isArray = value.getClass().getName().equals("java.util.ArrayList");
                if (Descriptors.FieldDescriptor.Type.MESSAGE == field.getType()) {
                    GeneratedMessageV3.Builder fieldBuilder = (GeneratedMessageV3.Builder) getFieldBuilder(builder, field);
                    if (isArray) {
                        toGRpcMessages((List<?>) value, builder, field);
                    } else if (null != fieldBuilder) {
                        Object nestedValue = toGRpcMessage(value, fieldBuilder);
                        setField(builder, field, nestedValue);
                    }
                } else {
                    setField(builder, field, value);
                }
            }
        } catch (Exception e) {
            log.debug("{}", e);
        }

        return builder.build();
    }
}
