package com.ycit.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * 日期util 类
 *
 * @author xlch
 * @Date 2018-04-11 15:40
 */
public class DateUtil {

    public static LocalDateTime longToLocalDateTime(long time) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault());
    }

    public static ZonedDateTime getStartOfDate(LocalDateTime localDateTime) {
        return localDateTime.toLocalDate().atStartOfDay(ZoneId.systemDefault());
    }

    public static void main(String[]args) {

    }

}
