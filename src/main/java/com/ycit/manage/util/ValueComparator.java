package com.ycit.manage.util;

import java.util.Comparator;
import java.util.Map;

/**
 * 按值排序
 * <p>
 * Created by xlch at 2018/5/12
 */
public class ValueComparator implements Comparator {

    private Map<Object, Comparable> map;

    public ValueComparator(Map map) {
        this.map = map;
    }

    @Override
    public int compare(Object o1, Object o2) {
        if (!map.containsKey(o1) || !map.containsKey(o2)) {
            return 0;
        }
        return map.get(o2).compareTo(map.get(o1));
    }
}
