package models.support;

import java.time.LocalTime;

import models.Timecard;
import models.Workday;

public class TimecardSupport {

    private Timecard timecard;
    private Workday wd;

    private LocalTime actual_time;
    private Integer over_time;
    private String str_over_time;
    private String str_total_over_time;

    private static Integer total_actual_time = 0;
    private static Integer total_over_time = 0;
    private static Integer day_count = 0;

    public TimecardSupport() {

    }

    public static void total_reset(){
        total_actual_time=0;
        total_over_time = 0;
        day_count = 0;
    }

    public void timecardSummary() {

        this.actual_time = sumActual_time(timecard);
        this.over_time = sumOver_time(actual_time);
        this.str_over_time = secondToString(over_time);
        total_over_time = total_over_time + over_time;
        total_actual_time = total_actual_time + actual_time.toSecondOfDay();
        this.str_total_over_time = secondToString(total_over_time);


        day_count ++;

    }


    public LocalTime sumActual_time(Timecard timecard) {

        LocalTime l_st = timecard.getStart_at().toLocalTime();
        LocalTime l_et = timecard.getEnd_at().toLocalTime();
        LocalTime l_rt = timecard.getRest_time().toLocalTime();

        int second = l_et.toSecondOfDay() - l_st.toSecondOfDay() - l_rt.toSecondOfDay();


        return LocalTime.ofSecondOfDay(second);

    }

    public Integer sumOver_time(LocalTime actual_time) {
        int second = actual_time.toSecondOfDay();
        Integer over_time;
        if (second < 4 * 60 * 60) {
            over_time = second;
        } else {
            over_time = second - 8 * 60 * 60;
        }
        return over_time;
    }

    public static String secondToString(int over_time) {
        String str_min;
        int absMinute = Math.abs(over_time) / 60;

        String str_hour = String.valueOf(absMinute / 60);
        int min = absMinute % 60;

        if (min <= 9) {
            str_min = "0" + String.valueOf(min);
        } else {
            str_min = String.valueOf(min);
        }

        if (over_time < 0) {
            return "-" + str_hour + ":" + str_min;
        } else {
            return str_hour + ":" + str_min;
        }

    }

    public Workday getWd() {
        return wd;
    }

    public void setWd(Workday wd) {
        this.wd = wd;
    }

    public LocalTime getActual_time() {
        return actual_time;
    }

    public void setActual_time(LocalTime actual_time) {
        this.actual_time = actual_time;
    }

    public Integer getOver_time() {
        return over_time;
    }

    public void setOver_time(Integer over_time) {
        this.over_time = over_time;
    }

    public String getStr_over_time() {
        return str_over_time;
    }

    public void setStr_over_time(String str_over_time) {
        this.str_over_time = str_over_time;
    }

    public Timecard getTimecard() {
        return timecard;
    }

    public void setTimecard(Timecard timecard) {
        this.timecard = timecard;
    }

    public String getStr_total_over_time() {
        return str_total_over_time;
    }

    public void setStr_total_over_time(String str_total_over_time) {
        this.str_total_over_time = str_total_over_time;
    }

    public static Integer getTotal_over_time() {
        return total_over_time;
    }

    public void setTotal_over_time(Integer total_over_time) {
        TimecardSupport.total_over_time = total_over_time;
    }

    public static Integer getTotal_actual_time() {
        return total_actual_time;
    }

    public void setTotal_actual_time(Integer total_actual_time) {
        TimecardSupport.total_actual_time = total_actual_time;
    }

    public static Integer getDay_count() {
        return day_count;
    }

    public static void setDay_count(Integer day_count) {
        TimecardSupport.day_count = day_count;
    }
}
