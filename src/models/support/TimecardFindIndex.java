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
import models.Workday;
import utils.DBUtil;

public class TimecardFindIndex {

    public static List<TimecardSupport> findIndex(int employee_id, int month_group) {
        EntityManager em = DBUtil.createEntityManager();

        List<TimecardSupport> tss = new ArrayList<TimecardSupport>();
        List<Date> days = MonthGroupSupport.getAlldayOfMonth(month_group);
        Iterator<Date> itr_days = days.iterator();

        TimecardSupport.total_reset();
        while (itr_days.hasNext()) {
            Timecard t = new Timecard();
            Date day = itr_days.next();

            try {
                t = em.createNamedQuery("getSingleTimecard", Timecard.class)
                        .setParameter("employee", employee_id)
                        .setParameter("day", day)
                        .getSingleResult();

            } catch (NoResultException ex) {
            }
            if (t.getId() != null) {
                TimecardSupport ts = new TimecardSupport();
                long day_check = (long) em.createNamedQuery("workdayCheck", Long.class)
                        .setParameter("day", t.getTimecard_day())
                        .getSingleResult();

                ts.setTimecard(t);
                if (day_check == 0) {
                    ts.setHoliday_flag(true);
                }

                if (t.getEnd_at() != null) {
                    ts.timecardSummary();
                }

                tss.add(ts);
            }

        }
        em.close();
        return tss;

    }

    public static TimecardSupport findSingleTimecard(int id) {
        EntityManager em = DBUtil.createEntityManager();
        Timecard t = new Timecard();

        t = (Timecard) em.find(Timecard.class, id);
        if (Objects.isNull(t)) {
            return null;
        }
        TimecardSupport ts = new TimecardSupport();
        ts.setTimecard(t);
        if (t.getEnd_at() != null) {
            ts.timecardSummary();
        }
        em.close();

        return ts;

    }

    public static List<TimecardSimpleSummary> findTimecardForAdmin(int month_group, int page) {
        EntityManager em = DBUtil.createEntityManager();

        List<Employee> e_list = em.createNamedQuery("getActiveEmployees", Employee.class)
                .getResultList();

        List<Workday> w_list = em.createNamedQuery("getWorkdays", Workday.class)
                .setParameter("month_group", month_group)
                .getResultList();

        Date first_day = MonthGroupSupport.getFirstdayOfMonth(month_group);
        Date end_day = MonthGroupSupport.getEnddayOfMonth(month_group);

        int employee_count = e_list.size();

        Iterator<Employee> itr_e = e_list.iterator();

        List<TimecardSimpleSummary> tss = new ArrayList<TimecardSimpleSummary>();
        while (itr_e.hasNext()) {
            Employee e = itr_e.next();
            int employee_id = e.getId();
            TimecardSimpleSummary ts = new TimecardSimpleSummary(e, month_group);
            Iterator<Workday> itr_w = w_list.iterator();

            while (itr_w.hasNext()) {
                Date day = itr_w.next().getWorkday();
                Timecard t = new Timecard();

                try {
                    t = em.createNamedQuery("getSingleTimecard", Timecard.class)
                            .setParameter("employee", employee_id)
                            .setParameter("day", day)
                            .getSingleResult();

                } catch (NoResultException ex) {
                }
                if (t.getEnd_at() != null) {
                    ts.updateSummary(t);
                }

            }
            long allday_count = (long) em.createNamedQuery("checkAllTimecardsOfMonth", Long.class)
                    .setParameter("employee", e.getId())
                    .setParameter("first_day", first_day)
                    .setParameter("end_day", end_day)
                    .getSingleResult();

            ts.setHoliday_count((int) allday_count - ts.getWorkday_count());

            tss.add(ts);

        }
        em.close();

        tss.sort(Comparator.comparing(TimecardSimpleSummary::getOver_time_status).reversed());

        int from = (15 * (page - 1));
        int end = ((16 * page) - 1);
        if (end > employee_count) {
            end = employee_count;
        }
        List<TimecardSimpleSummary> tss_sub = tss.subList(from, end);

        return tss_sub;
    }

}
