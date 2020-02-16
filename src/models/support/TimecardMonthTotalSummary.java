package models.support;

import java.time.LocalTime;

public class TimecardMonthTotalSummary {

    private int month_group = 0;

    private Integer total_actual_time = null;
    private Integer total_over_time = null;
    private Integer day_count = 0;
    private Double double_status = 0.0;

    private String str_total_over_time;
    private String str_total_actual_time;
    private String status;

    public TimecardMonthTotalSummary() {

    }

    public void sumTotal_actual_time(Integer actual_time) {
        if(this.total_actual_time==null){
            this.setTotal_actual_time(actual_time);
        }
        this.total_actual_time = total_actual_time+actual_time;
    }

    public void sumTotal_over_time(Integer over_time) {
        if(this.total_over_time==null){
            this.setTotal_over_time(over_time);
        }
        this.total_over_time= total_over_time + over_time;

    }



    public void sumMonthSummary(LocalTime actual_time, Integer over_time) {
        this.total_actual_time = total_actual_time + actual_time.toSecondOfDay();
        this.total_over_time = total_over_time + over_time;

        this.day_count++;

        this.str_total_actual_time = TimecardSupport.secondToString(total_actual_time);
        this.str_total_over_time = TimecardSupport.secondToString(total_over_time);
        if (this.day_count != 0) {
            this.double_status = (total_over_time / 60 / 60.0) / day_count;
        }
        this.status = (WorkStatus.setWorkStatus(double_status, month_group));

    }

    public int getMonth_group() {
        return month_group;
    }

    public void setMonth_group(int month_group) {
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

    public Double getDouble_status() {
        return double_status;
    }

    public void setDouble_status(Double double_status) {
        this.double_status = double_status;
    }

    public String getStr_total_over_time() {
        return str_total_over_time;
    }

    public void setStr_total_over_time(String str_total_over_time) {
        this.str_total_over_time = str_total_over_time;
    }

    public String getStr_total_actual_time() {
        return str_total_actual_time;
    }

    public void setStr_total_actual_time(String str_total_actual_time) {
        this.str_total_actual_time = str_total_actual_time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



}
