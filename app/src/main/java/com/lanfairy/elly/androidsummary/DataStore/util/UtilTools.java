package com.lanfairy.elly.androidsummary.DataStore.util;

import java.math.BigDecimal;

public class UtilTools {
    /**
     *
     * @param place 保留几位小数
     * @return
     */
    public static double reserveDecimalDouble(double doubleNumber, int place){
        return new BigDecimal(doubleNumber).setScale(place, BigDecimal.ROUND_DOWN).doubleValue();
    }
}
