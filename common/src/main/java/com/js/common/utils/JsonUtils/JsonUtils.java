package com.js.common.utils.JsonUtils;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.util.TypeUtils;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class JsonUtils {
    static {
        TypeUtils.compatibleWithJavaBean = true;
    }

    private static JsonUtils jacksonUtil = new JsonUtils();

    private static ObjectMapper mapper;

    private JsonUtils() {
        mapper = new ObjectMapper();
    }

    public static ObjectMapper getInstance() {
        return jacksonUtil.mapper;
    }


    /**
     * 功能描述:  json转化为java bean
     * Author: xuyanxiong
     * Date:   2017年3月15日 下午6:15:32
     * return  T
     */
    public static <T> T jsonToBean(String json, Class<T> valueType) {
        if (StrUtil.isNotBlank(json)) {
            try {
                return getInstance().readValue(json, valueType);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
        return null;
    }

    public static <T> T jsonToGenericBean(String json, TypeReference<T> typeReference) {
        try {
            return getInstance().readValue(json, typeReference);
        } catch (IOException e) {
        }
        return null;
    }


    /**
     * 功能描述:  java bean转化为json
     * Author: xuyanxiong
     * Date:   2017年3月15日 下午6:15:16
     * return  String
     */
    public static String beanToJson(Object bean) {
        /**
         * 此处需判断，bean为null时，会返回“null”字符，
         */
        if (bean == null) {
            return null;
        }
        try {
            return getInstance().writeValueAsString(bean);
        } catch (JsonGenerationException e) {
        } catch (JsonMappingException e) {
        } catch (IOException e) {
        }
        return null;
    }


    /**
     * 功能描述:  json转list
     * Author: xuyanxiong
     * Date:   2017年3月15日 下午6:10:54
     * return  List
     */
    public static <T> List<T> jsonToList(String json, Class<T> clazz) {
        List<T> list = null;
        if (StrUtil.isNotBlank(json)) {
            try {
                // 适配javaType=java.util.List的旧代码
                if(clazz.isAssignableFrom(List.class)) {
                    list = getInstance().readValue(json, new TypeReference<List<T>>() {});
                }
                else {
                    CollectionType listType =
                            getInstance().getTypeFactory().constructCollectionType(ArrayList.class, clazz);
                    list = getInstance().readValue(json, listType);
                }
            } catch (JsonGenerationException e) {
            } catch (JsonMappingException e) {
            } catch (IOException e) {
            }
        }
        return list;
    }

    public static String beanToJsonAndFmDate(Object channelList) {
        return beanToJson(channelList);
    }

    /**
     * 集合转json
     *
     * @param list
     * @return author lusongjiong
     */
    public static String ListToJson(List list) {
        String str = null;
        try {
            str = getInstance().writeValueAsString(list);
        } catch (JsonGenerationException e) {
        } catch (JsonMappingException e) {
        } catch (IOException e) {
        }
        return str;
    }


    /**
     * 解决从mysql的json类型List,导致List无法遍历问题
     *
     * @param list
     * @return
     */
    public static <T> T[] typeConverse(List<T> list, Class<T[]> valueType) {
        String str = ListToJson(list);
        T[] arr = null;
        try {
            arr = mapper.readValue(str, valueType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arr;
    }

    /**
     * json 转hashMap
     * @param json
     * @param valueType
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> Map<K, V> jsonToMap(String json, Class<V> valueType) {
        Map<K, V> result = null;
        JavaType javaType2 = mapper.getTypeFactory().constructParametricType(HashMap.class, String.class, valueType);
        try {
            result = mapper.readValue(json, javaType2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


}
