package org.ppcirgo.oa.utils;

//md5加密工具

import org.springframework.util.DigestUtils;

public class MD5Utils {
    /**
     * MD5算法加密
     */
    public static String encodeByMD5(String text) {
        return DigestUtils.md5DigestAsHex(text.getBytes());
    }

}
