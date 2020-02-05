package models.support;

import java.sql.Date;
import java.util.Calendar;

import javax.persistence.EntityManager;

import models.Workday;
import utils.DBUtil;

public class InsertWorkday {

    public static void insertWorkdays(Calendar start, Calendar end) {
        EntityManager em = DBUtil.createEntityManager();

        for (Calendar date = start; date.before(end); date.add(Calendar.DAY_OF_MONTH, 1)) {

            if (date.get(Calendar.DAY_OF_WEEK) == 1 || date.get(Calendar.DAY_OF_WEEK) == 7) {
                continue;
            } else {

                Workday wd = new Workday();
                Date sql_day = new Date(date.getTime().getTime());

                wd.setWorkday(sql_day);
                wd.setMonth_group(makeMonth_group(date));

                System.out.println(wd.getWorkday());
                System.out.println(wd.getMonth_group());

                em.getTransaction().begin();
                em.persist(wd);
                em.getTransaction().commit();

            }
        }
        em.close();

    }

    public static Integer makeMonth_group(Calendar day) {

        Calendar date = day;
        int y = date.get(Calendar.YEAR);
        int m = date.get(Calendar.MONTH);

        if (date.get(Calendar.DATE) <= 20) {
            m = m + 1;
        } else {
            m = m + 2;
            if(m == 13){
                m = 1;
                y = y+1;
            }
        }
        String month = String.valueOf(m);

        if (month.length() == 1) {
            month = "0" + month;
        }
        String year = String.valueOf(y);
        String mg = year + month;

        return Integer.valueOf(mg);
    }

}
