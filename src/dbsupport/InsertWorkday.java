package dbsupport;

import java.sql.Date;
import java.util.Calendar;

import javax.persistence.EntityManager;

import models.Workday;
import models.support.MonthGroupSupport;
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
                wd.setMonth_group(MonthGroupSupport.getThisMonth_group(date));

                System.out.println(wd.getWorkday());
                System.out.println(wd.getMonth_group());

                em.getTransaction().begin();
                em.persist(wd);
                em.getTransaction().commit();

            }
        }
        em.close();

    }


}
