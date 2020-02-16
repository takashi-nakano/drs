package models.support;

import java.time.LocalTime;

import models.Timecard;

public class TimecardSupport {

    public static String minutesToString(Integer minute) {
        int absMinute = Math.abs(minute);
        String str_min;

        String str_hour = String.valueOf(absMinute / 60);
        int min = absMinute % 60;

        if (min <= 9) {
            str_min = "0" + String.valueOf(min);
        } else {
            str_min = String.valueOf(min);
        }

        if (minute < 0) {
            return "-" + str_hour + ":" + str_min;
        } else {
            return str_hour + ":" + str_min;
        }
    }

    public static Integer sumActual_timeMinutes(Timecard timecard) {
        LocalTime l_st = timecard.getStart_at().toLocalTime();
        LocalTime l_et = timecard.getEnd_at().toLocalTime();
        LocalTime l_rt = timecard.getRest_time().toLocalTime();

        int second = l_et.toSecondOfDay() - l_st.toSecondOfDay() - l_rt.toSecondOfDay();

        return second / 60;

    }

    public static Integer sumOver_timeMinutes(Integer actual_time) {
        int min = actual_time;
        Integer over_time;
        if (min < 4 * 60) {
            over_time = min;
        } else {
            over_time = min - 8 * 60;
        }
        return over_time;

    }
}
