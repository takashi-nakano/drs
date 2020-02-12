package models;

import java.sql.Date;

import models.support.MonthGroupSupport;

public class MonthList {

    private Date workday;
    private Integer month_group;
    private String str_month;



    public MonthList(Integer month_group) {
        this.month_group = month_group;
        this.str_month = intMonthToString(month_group);
    }

    private static String intMonthToString(Integer month_group) {

        if (month_group.equals(MonthGroupSupport.getCurrentMonth_group())) {
            return " 当月 ";
        } else {
            String str_ym = Integer.toString(month_group);

            String str_y = str_ym.substring(0, 4) + "年";
            String str_m = str_ym.substring(4) + "月";
            return str_y + str_m;
        }
    }

    public MonthList(Date workday, Integer month_group) {
        this.workday = workday;
        this.month_group = month_group;
    }

    public Integer getMonth_group() {
        return month_group;
    }

    public void setMonth_group(Integer month_group) {
        this.month_group = month_group;
    }

    public Date getWorkday() {
        return workday;
    }

    public void setWorkday(Date workday) {
        this.workday = workday;
    }

    public String getStr_month() {
        return str_month;
    }

    public void setStr_month(String str_month) {
        this.str_month = str_month;
    }

}
