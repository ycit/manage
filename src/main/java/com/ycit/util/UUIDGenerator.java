package com.ycit.util;

import java.util.UUID;

/**
 * 随机数工具类
 *
 * @author xlch
 * @Date 2018-03-23 17:11
 */
public class UUIDGenerator {

    public static String getUUID() {
        UUID random = UUID.randomUUID();
        return random.toString().replaceAll("-", "");
    }

}
