package models.support;

import java.util.ArrayList;
import java.util.List;

import models.Timecard;

public class TimecardAllSummary {

    private List<TimecardAdvance> timecard_advance;

    private TotalSummary holiday_total;
    private TotalSummary workday_total;

    private Integer month_group;

    public TimecardAllSummary() {

    }

    public TimecardAllSummary(Integer month_group) {
        this.month_group = month_group;
        this.timecard_advance = new ArrayList<TimecardAdvance>();
        this.holiday_total = new TotalSummary(month_group);
        this.workday_total = new TotalSummary(month_group);
    }

    public void addTimecard(Timecard t, Boolean holiday_check) {
        TimecardAdvance t_adv = new TimecardAdvance(t);
        t_adv.setHoliday_flag(holiday_check);

        if (t.getEnd_at() != null) {
            t_adv.calcTimes();
            if (holiday_check) {
                holiday_total.updateTimecard(t_adv);
            } else {
                workday_total.updateTimecard(t_adv);
            }
        }
        timecard_advance.add(t_adv);
    }

    public List<TimecardAdvance> getTimecard_advs() {
        return timecard_advance;
    }

    public void setTimecard_advs(List<TimecardAdvance> timecard_advs) {
        this.timecard_advance = timecard_advs;
    }

    public TotalSummary getHoliday_total() {
        return holiday_total;
    }

    public void setHoliday_total(TotalSummary holiday_total) {
        this.holiday_total = holiday_total;
    }

    public TotalSummary getWorkday_total() {
        return workday_total;
    }

    public void setWorkday_total(TotalSummary workday_total) {
        this.workday_total = workday_total;
    }

    public Integer getMonth_group() {
        return month_group;
    }

    public void setMonth_group(Integer month_group) {
        this.month_group = month_group;
    }


}
