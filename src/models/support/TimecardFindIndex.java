package models.support;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import models.Timecard;
import models.Workday;
import utils.DBUtil;

public class TimecardFindIndex {


    public static List<TimecardSupport> findIndex(int employee_id, int month_group) {
        EntityManager em = DBUtil.createEntityManager();

        List<TimecardSupport> tss = new ArrayList<TimecardSupport>();

        List<Workday> days = em.createNamedQuery("getWorkdays", Workday.class)
                .setParameter("month_group", month_group)
                .getResultList();

        Iterator<Workday> itr_days = days.iterator();

        TimecardSupport.total_reset();
        while (itr_days.hasNext()) {
            Timecard t = new Timecard();
            Date day = itr_days.next().getWorkday();

            try {
                t = em.createNamedQuery("getSingleTimecard", Timecard.class)
                        .setParameter("employee", employee_id)
                        .setParameter("day", day)
                        .getSingleResult();

            } catch (NoResultException ex) {
            }
            if(t.getId()!=null){
            TimecardSupport ts = new TimecardSupport();

            ts.setTimecard(t);
            if(t.getEnd_at()!=null){
                ts.timecardSummary();
            }
            tss.add(ts);
            }

        }
        em.close();
        return tss;

    }

}
