package models.dto;

import models.Timecard;
import models.support.TimecardSupport;

public class TimecardAdvance {
    private Timecard timecard;

    private Integer actual_time; //分
    private Integer over_time; //分

    private String str_actual_time;
    private String str_over_time;

    private Integer total_actual_time ;
    private Integer total_over_time;
    private String str_total_over_time;

    private Boolean holiday_flag = false;

    public TimecardAdvance() {

    }

    public TimecardAdvance(Timecard t) {
        this.timecard = t;
    }

    public void calcTimes() {
        this.actual_time = TimecardSupport.sumActual_timeMinutes(timecard);
        this.over_time = TimecardSupport.sumOver_timeMinutes(actual_time);
        this.str_actual_time=TimecardSupport.minutesToString(actual_time);
        this.str_over_time=TimecardSupport.minutesToString(over_time);
    }

    public Timecard getTimecard() {
        return timecard;
    }

    public void setTimecard(Timecard timecard) {
        this.timecard = timecard;
    }

    public Integer getActual_time() {
        return actual_time;
    }

    public void setActual_time(Integer actual_time) {
        this.actual_time = actual_time;
    }

    public Integer getOver_time() {
        return over_time;
    }

    public void setOver_time(Integer over_time) {
        this.over_time = over_time;
    }

    public Integer getTotal_actual_time() {
        return total_actual_time;
    }

    public void setTotal_actual_time(Integer total_actual_time) {
        this.total_actual_time = total_actual_time;
    }

    public Integer getTotal_over_time() {
        return total_over_time;
    }

    public void setTotal_over_time(Integer total_over_time) {
        this.total_over_time = total_over_time;
    }

    public Boolean getHoliday_flag() {
        return holiday_flag;
    }

    public void setHoliday_flag(Boolean holiday_flag) {
        this.holiday_flag = holiday_flag;
    }

    public String getStr_actual_time() {
        return str_actual_time;
    }

    public void setStr_actual_time(String str_actual_time) {
        this.str_actual_time = str_actual_time;
    }

    public String getStr_over_time() {
        return str_over_time;
    }

    public void setStr_over_time(String str_over_time) {
        this.str_over_time = str_over_time;
    }

    public String getStr_total_over_time() {
        return str_total_over_time;
    }

    public void setStr_total_over_time(String str_total_over_time) {
        this.str_total_over_time = str_total_over_time;
    }


}
