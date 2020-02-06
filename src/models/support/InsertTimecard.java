package models.support;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Random;

import javax.persistence.EntityManager;

import models.Employee;
import models.Timecard;
import utils.DBUtil;

public class InsertTimecard {

    public static void insertTimecardStoE(int employee_id, Calendar start, Calendar end) {

        EntityManager em = DBUtil.createEntityManager();

        Employee e = em.find(Employee.class, employee_id);

        for (Calendar date = start; date.before(end); date.add(Calendar.DAY_OF_MONTH, 1)) {

            if (date.get(Calendar.DAY_OF_WEEK) == 1 || date.get(Calendar.DAY_OF_WEEK) == 7) {
                continue;
            } else {
                Timecard t = new Timecard();

                Date sql_date = new Date(date.getTime().getTime());

                LocalTime l_sT = LocalTime.of(7, minuteRandom(35, 55));
                LocalTime l_eT = LocalTime.of(17, minuteRandom(5, 25));
                LocalTime l_rT = LocalTime.of(1, 0);

                String c = "";

                t.setEmployee(e);
                t.setTimecard_day(sql_date);
                t.setStart_at(Time.valueOf(l_sT));
                t.setEnd_at(Time.valueOf(l_eT));
                t.setRest_time(Time.valueOf(l_rT));
                t.setComent(c);

                System.out.println("日付" + t.getTimecard_day() + " 開始" + t.getStart_at() + " 終了" + t.getEnd_at() + " 休憩"
                        + t.getRest_time() + " コメント" + t.getComent());

                em.getTransaction().begin();
                em.persist(t);
                em.getTransaction().commit();
            }
        }

        em.close();
    }

    public static int minuteRandom() {

        Random r = new Random();
        int m = r.nextInt(60);
        return m;
    }

    public static int minuteRandom(int from, int end) {

        Random r = new Random();
        int seed = end - from;

        return r.nextInt(seed) + from;

    }

}
