package models.support;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import models.Employee;
import models.Timecard;
import models.dto.TimecardAdvance;
import models.dto.TimecardAllSummary;
import models.dto.TimecardSimpleSummary;
import models.dto.TotalSummary;
import utils.DBUtil;

public class TimecardFindIndex {

    public static TimecardAdvance findSingleTimecard(int id) {
        EntityManager em = DBUtil.createEntityManager();
        Timecard t = new Timecard();

        t = (Timecard) em.find(Timecard.class, id);
        if (Objects.isNull(t)) {
            em.close();
            return null;
        }

        TimecardAdvance ta = new TimecardAdvance(t);
        WorkdaySupport.holidayCheckAndAddFlag(ta);

        if (t.getEnd_at() != null) {
            if (ta.getHoliday_flag()) {
                ta.holidayCalc();
            } else {
                ta.workdayCalc();
            }
        }
        em.close();
        return ta;
    }

    public static List<TimecardSimpleSummary> findTimecardForAdmin(int month_group, int page) {
        EntityManager em = DBUtil.createEntityManager();

        List<Employee> e_list = em.createNamedQuery("getActiveEmployees", Employee.class)
                .getResultList();
        List<Date> w_list = MonthGroupSupport.getAlldayOfMonth(month_group);

        int employee_count = e_list.size();

        Iterator<Employee> itr_e = e_list.iterator();

        List<TimecardSimpleSummary> tss_list = new ArrayList<TimecardSimpleSummary>();
        while (itr_e.hasNext()) {
            Employee e = itr_e.next();
            int employee_id = e.getId();
            TimecardSimpleSummary ts = new TimecardSimpleSummary(e, month_group);
            Iterator<Date> itr_w = w_list.iterator();

            while (itr_w.hasNext()) {
                Date day = itr_w.next();
                Timecard t = new Timecard();

                try {
                    t = em.createNamedQuery("getSingleTimecard", Timecard.class)
                            .setParameter("employee", employee_id)
                            .setParameter("day", day)
                            .getSingleResult();

                } catch (NoResultException ex) {
                    continue;
                }
                if (t.getEnd_at() != null) {
                    ts.updateSummary(t);
                }
            }
            if (ts.getWorkday_count() != 0) {
                ts.lastUpdateSummary();
            }
            tss_list.add(ts);
        }
        em.close();

        tss_list.sort(Comparator.comparing(TimecardSimpleSummary::getOver_time_status)
                .thenComparing(TimecardSimpleSummary::getHoliday_over_time)
                .thenComparing(TimecardSimpleSummary::getWorkday_count)
                .reversed());

        int from = (15 * (page - 1));
        int end = ((16 * page) - page);
        if (end > employee_count) {
            end = employee_count;
        }
        List<TimecardSimpleSummary> tss_sub = tss_list.subList(from, end);
        return tss_sub;
    }

    public static TimecardAllSummary findPersonalIndex(Integer employee_id, Integer month) {
        EntityManager em = DBUtil.createEntityManager();

        TimecardAllSummary tas = new TimecardAllSummary(month);
        List<TimecardAdvance> ta_list = new ArrayList<TimecardAdvance>();

        List<Date> days = MonthGroupSupport.getAlldayOfMonth(month);
        Iterator<Date> itr_days = days.iterator();
        TotalSummary workday_total = new TotalSummary(month);
        TotalSummary holiday_total = new TotalSummary(month);

        while (itr_days.hasNext()) {
            Timecard t = new Timecard();
            Date day = itr_days.next();

            try {
                t = em.createNamedQuery("getSingleTimecard", Timecard.class)
                        .setParameter("employee", employee_id)
                        .setParameter("day", day)
                        .getSingleResult();

            } catch (NoResultException ex) {
                continue;
            }
            TimecardAdvance ta = new TimecardAdvance(t);
            WorkdaySupport.holidayCheckAndAddFlag(ta);
            if (t.getEnd_at() != null) {
                if (ta.getHoliday_flag()) {
                    ta.holidayCalc();
                    holiday_total.updateTotal(ta);
                } else {
                    ta.workdayCalc();
                    workday_total.updateTotal(ta);
                }
            }
            ta_list.add(ta);
        }
        tas.setTimecard_advs(ta_list);
        if (workday_total.getDay_count() != 0) {
            workday_total.addStatus();
        }
        if (holiday_total.getDay_count() != 0) {
            holiday_total.holidayLastCalc(workday_total);
        }

        tas.setWorkday_total(workday_total);
        tas.setHoliday_total(holiday_total);

        em.close();
        return tas;
    }

}
