package models.support;

import java.util.List;

import javax.persistence.EntityManager;

import models.dto.MonthList;
import utils.DBUtil;

public class WorkdayFindMonthGroup {

    public static List<MonthList> getOneYearMonthList() {
        EntityManager em = DBUtil.createEntityManager();

        List<MonthList> ml = em.createNamedQuery("getMonthList", MonthList.class)
                .setParameter("month_group", MonthGroupSupport.getCurrentMonth_group())
                .setMaxResults(12)
                .getResultList();
        em.close();

        return ml;
    }
    public static List<MonthList> getAllMonthList(){
        EntityManager em = DBUtil.createEntityManager();

        List<MonthList> ml = em.createNamedQuery("getMonthList", MonthList.class)
                .setParameter("month_group", MonthGroupSupport.getCurrentMonth_group())
                .getResultList();
        em.close();

        return ml;

    }

}
