package com.ycit.manage.util;

import java.text.NumberFormat;

/**
 * 计算 util
 * <p>
 * Created by xlch at 2018/5/12
 */
public class CalculationUtil {

    public static String getPt(long divisor, long divided) {
        if (divided == 0) {
            return "-";
        }
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(5);
        return numberFormat.format((float) divisor / (float) divided);
    }

}
