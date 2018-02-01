package com.it.onex.layerdrawable;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Linsa on 2018/2/1:10:51.
 * des:
 */

public class ProgressTextUtils {

    /**
     * 获取过度的时间
     * @param time
     * @return
     */
    public static String getProgressText(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(time));
        double minute = calendar.get(Calendar.MINUTE);
        double second = calendar.get(Calendar.SECOND);

        DecimalFormat format = new DecimalFormat("00");
        return format.format(minute) + ":" + format.format(second);
    }
}
