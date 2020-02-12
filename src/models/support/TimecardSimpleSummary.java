package models.support;

import java.time.LocalTime;

import models.Employee;
import models.Timecard;

public class TimecardSimpleSummary {

    private Employee employee;
    private Integer month_group;

    private Integer over_time;
    private Double over_time_status = 0.0;
    private String str_over_time;
    private String status;
    private Integer day_count = 0;


    public TimecardSimpleSummary() {

    }

    public TimecardSimpleSummary(Employee employee, Integer month_group) {
        this.employee = employee;
        this.month_group = month_group;
    }

    public void updateSummary(Timecard timecard) {
        LocalTime oneday_at=TimecardSupport.sumActual_time(timecard);
        if(this.over_time==null){
            this.over_time=TimecardSupport.sumOver_time(oneday_at);
        }else{
            this.over_time=over_time+TimecardSupport.sumOver_time(oneday_at);
        }

        this.str_over_time=TimecardSupport.secondToString(over_time);

        this.day_count++;
        this.over_time_status = ((double) over_time / 60 / 60) / day_count;
        this.status = WorkStatus.setWorkStatus(over_time_status, month_group);
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Integer getOver_time() {
        return over_time;
    }

    public void setOver_time(Integer over_time) {
        this.over_time = over_time;
    }

    public Double getOver_time_status() {
        return over_time_status;
    }

    public void setOver_time_status(Double over_time_status) {
        this.over_time_status = over_time_status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getDay_count() {
        return day_count;
    }

    public void setDay_count(Integer day_count) {
        this.day_count = day_count;
    }

    public Integer getMonth_group() {
        return month_group;
    }

    public void setMonth_group(Integer month_group) {
        this.month_group = month_group;
    }

    public String getStr_over_time() {
        return str_over_time;
    }

    public void setStr_over_time(String str_over_time) {
        this.str_over_time = str_over_time;
    }

}
