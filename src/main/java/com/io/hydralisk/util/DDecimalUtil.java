package com.io.hydralisk.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class DDecimalUtil {
    private static final DecimalFormat formatInt = new DecimalFormat("#");
    private static final DecimalFormat format = new DecimalFormat("#.##");
    public static Integer toInt(BigDecimal bigDecimal){
        return Integer.parseInt(formatInt.format(bigDecimal));
    }
    public static String format (BigDecimal bigDecimal){
        return format.format(bigDecimal);
    }
}