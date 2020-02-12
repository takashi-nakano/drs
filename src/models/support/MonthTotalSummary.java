package models.support;

public class MonthTotalSummary {

    private int employee_id;
    private Integer month_group;

    private String status;
    private Integer total_over_time;
    private Integer total_actual_time;
    private Integer day_count;
    private Double double_status;

    private String str_total_over_time;
    private String str_total_actual_time;

    public MonthTotalSummary() {

    }

    public MonthTotalSummary(Integer total_actual_time, Integer total_over_time, Integer day_count,
            Integer month_group) {
        this.month_group = month_group;
        this.total_actual_time = total_actual_time;
        this.total_over_time = total_over_time;
        this.day_count = day_count;

    }

    public void sumMonthTotalSummary() {

        this.str_total_actual_time = TimecardSupport.secondToString(total_actual_time);
        this.str_total_over_time = TimecardSupport.secondToString(total_over_time);
        if (day_count != 0) {
            this.double_status = (total_over_time / 60 / 60.0) / day_count;
        } else {
            this.double_status = 0.0;
        }

        this.status = WorkStatus.setWorkStatus(double_status, month_group);
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public int getMonth_group() {
        return month_group;
    }

    public void setMonth_group(int month_group) {
        this.month_group = month_group;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getTotal_over_time() {
        return total_over_time;
    }

    public void setTotal_over_time(Integer total_over_time) {
        this.total_over_time = total_over_time;
    }

    public Integer getTotal_actual_time() {
        return total_actual_time;
    }

    public void setTotal_actual_time(Integer total_actual_time) {
        this.total_actual_time = total_actual_time;
    }

    public Integer getDay_count() {
        return day_count;
    }

    public void setDay_count(Integer day_count) {
        this.day_count = day_count;
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

    public Double getDouble_status() {
        return double_status;
    }

    public void setDouble_status(Double double_status) {
        this.double_status = double_status;
    }
}