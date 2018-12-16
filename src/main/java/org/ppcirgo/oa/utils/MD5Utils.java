package org.ppcirgo.oa.utils;

//md5加密工具

import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.Map;

public class MD5Utils {
    /**
     * MD5算法加密
     */
    public static String encodeByMD5(String text) {
        return DigestUtils.md5DigestAsHex(text.getBytes());
    }

    /**
     * 产生动态随机密码
     * @return
     */
    public static Map<String,String> generatorPassword(){
        String key = "xWt"+System.currentTimeMillis()+"Tmy";
        String value = encodeByMD5(key);
        Map<String,String> map = new HashMap(1);
        map.put(key,value);
        return map;
    }
}
