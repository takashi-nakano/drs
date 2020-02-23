package models.support;

import java.sql.Date;

import javax.persistence.EntityManager;

import models.dto.TimecardAdvance;
import utils.DBUtil;

public class WorkdaySupport {

    public static void holidayCheckAndAddFlag(TimecardAdvance ta) {
        EntityManager em = DBUtil.createEntityManager();

        long day_check = (long) em.createNamedQuery("workdayCheck", Long.class)
                .setParameter("day", ta.getTimecard().getTimecard_day())
                .getSingleResult();
        if (day_check == 0) {
            ta.setHoliday_flag(true);
        }
        em.close();
        return;

    }

    public static Boolean holidayCheck(Date day) {
        EntityManager em = DBUtil.createEntityManager();

        long day_check = (long) em.createNamedQuery("workdayCheck", Long.class)
                .setParameter("day", day)
                .getSingleResult();
        em.close();
        if (day_check == 0) {
            return true;
        }
        return false;

    }

}
