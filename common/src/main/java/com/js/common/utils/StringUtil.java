package com.js.common.utils;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;

import java.util.List;

public class StringUtil {
    /**
     * 是不是以*开头
     */
    public static boolean startWith2List(String str, List<String> list){
        if (StrUtil.isBlank(str) || CollUtil.isEmpty(list)){
            return false;
        }
        for (String s : list) {
            if (str.startsWith(s)){
                return true;
            }
        }
        return false;
    }
}
