package models.validators;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import models.Employee;
import models.Timecard;
import models.support.TimecardSupport;
import utils.DBUtil;

public class TimecardValidators {

    public static List<String> validate(Timecard t, Boolean day_dupulicate) {
        List<String> errors = new ArrayList<String>();

        String day_error = _validateDay(t.getEmployee(), t.getTimecard_day(), day_dupulicate);
        if (!day_error.equals("")) {
            errors.add(day_error);
        }

        String st_error = _validateSt(t.getStart_at());
        if (!st_error.equals("")) {
            errors.add(st_error);
        }

        String et_error = _validateEt(t.getEnd_at());
        if (!et_error.equals("")) {
            errors.add(et_error);
        }

        String rt_error = _validateRt(t.getRest_time());
        if (!rt_error.equals("")) {
            errors.add(rt_error);
        }

        String sum_error = _validateSum(t);
        if (!sum_error.equals("")) {
            errors.add(sum_error);
        }

        return errors;
    }

    private static String _validateSum(Timecard t) {
        try {
            LocalTime actual_time = TimecardSupport.sumActual_time(t);
            if (actual_time.toSecondOfDay() < 60*10) {
                return "勤務時間が10分未満です。入力値を確認してください";

            } else {
                return "";
            }

        } catch (Exception error) {
            return "時間計算ができません。入力値を確認してください";
        }
    }

    private static String _validateRt(Time rest_time) {
        if (rest_time == null || rest_time.equals("")) {
            return "休憩時間を入力してください";
        }
        return "";
    }

    private static String _validateEt(Time end_at) {
        if (end_at == null || end_at.equals("")) {
            return "終業時間を入力してください";
        }
        return "";
    }

    private static String _validateSt(Time start_at) {
        if (start_at == null || start_at.equals("")) {
            return "始業時間を入力してください";
        }
        return "";
    }

    private static String _validateDay(Employee e, Date timecard_day, Boolean day_duplicate) {
        if (timecard_day == null || timecard_day.equals("")) {
            return "日付を入力してください";
        }
        if (day_duplicate) {
            EntityManager em = DBUtil.createEntityManager();
            long t_count = (long) em.createNamedQuery("checkTimecardDuplicate", Long.class)
                    .setParameter("employee", e.getId())
                    .setParameter("day", timecard_day)
                    .getSingleResult();
            em.close();
            if (t_count > 0) {
                return "同じ日付ですでにタイムカードが作成されています";
            }
        }
        Date today= new Date(System.currentTimeMillis());
        if(timecard_day.toString().equals(today.toString())){
            return "当日のタイムカードは作成出来ません";
        }else if(timecard_day.after(today)){
            return "明日以降のタイムカードは作成できません";
        }

        return "";
    }
}
