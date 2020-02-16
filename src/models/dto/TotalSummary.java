package models.dto;

import models.Timecard;
import models.support.TimecardSupport;
import models.support.WorkStatus;

public class TotalSummary {
    private Integer month_group;

    private Integer total_actual_time = 0; //分
    private Integer total_over_time = 0; //分
    private Integer day_count = 0;

    private String str_total_actual_time;
    private String str_total_over_time;

    private String status;
    private Double double_status = 0.0;

    public TotalSummary() {

    }

    public TotalSummary(Integer month_group) {
        this.month_group = month_group;
    }

    public void updateTimecard(TimecardAdvance t_adv) {

        this.total_actual_time = t_adv.getTotal_actual_time();
        this.total_over_time = t_adv.getTotal_over_time();

        this.str_total_actual_time = TimecardSupport.minutesToString(total_actual_time);
        this.str_total_over_time = TimecardSupport.minutesToString(total_over_time);

        this.day_count++;
        if (!t_adv.getHoliday_flag()) {
            this.double_status = (total_over_time / 60.0) / day_count; //  hour / 日数

            this.status = WorkStatus.setWorkStatus(double_status, month_group);
        }

    }

    public void updateTotal(Integer actual_time, Integer over_time) {

        this.total_actual_time = total_actual_time + actual_time;
        this.total_over_time = total_over_time + over_time;

        this.str_total_actual_time = TimecardSupport.minutesToString(total_actual_time);
        this.str_total_over_time = TimecardSupport.minutesToString(total_over_time);

        this.day_count++;
    }

    public void addStatus() {

        this.double_status = (total_over_time / 60.0) / day_count; //  hour / 日数
        this.status = WorkStatus.setWorkStatus(double_status, month_group);

    }

    public void addTimecard(Timecard t) {

    }

    public Integer getMonth_group() {
        return month_group;
    }

    public void setMonth_group(Integer month_group) {
        this.month_group = month_group;
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

    public Integer getDay_count() {
        return day_count;
    }

    public void setDay_count(Integer day_count) {
        this.day_count = day_count;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getDouble_status() {
        return double_status;
    }

    public void setDouble_status(Double double_status) {
        this.double_status = double_status;
    }

    public String getStr_total_actual_time() {
        return str_total_actual_time;
    }

    public void setStr_total_actual_time(String str_total_actual_time) {
        this.str_total_actual_time = str_total_actual_time;
    }

    public String getStr_total_over_time() {
        return str_total_over_time;
    }

    public void setStr_total_over_time(String str_total_over_time) {
        this.str_total_over_time = str_total_over_time;
    }

}
