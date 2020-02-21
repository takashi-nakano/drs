package models.validators;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import models.Timecard;
import models.support.TimecardSupport;
import utils.DBUtil;

public class TimecardValidators {

    public static List<String> validate(Timecard t, Boolean day_dupulicate, Boolean end_update_flag) {
        List<String> errors = new ArrayList<String>();

        String st_error = _validateSt(t.getStart_at());
        if (!st_error.equals("")) {
            errors.add(st_error);
        }

        String et_error = _validateEt(t.getEnd_at());
        if (!et_error.equals("")) {
            errors.add(et_error);
        }

        String rt_error = _validateRt(t);
        if (!rt_error.equals("")) {
            errors.add(rt_error);
        }

        if (errors.size() == 0) {

            if (!end_update_flag) {
                String day_error = _validateDay(t, day_dupulicate);
                if (!day_error.equals("")) {
                    errors.add(day_error);
                }
            }

            String sum_error = _validateSum(t);
            if (!sum_error.equals("")) {
                errors.add(sum_error);
            }
        }
        return errors;

    }

    private static String _validateSum(Timecard t) {
        try {
            Integer actual_time = TimecardSupport.sumActual_timeMinutes(t);
            if (actual_time < 10) {
                return "時間計算ができないか、勤務時間が10分未満です。入力値を確認してください";

            } else {
                return "";
            }

        } catch (Exception error) {
            return "時間計算ができません。入力値を確認してください";
        }
    }

    private static String _validateRt(Timecard t) {
        if (t.getRest_time() == null || t.getRest_time().equals("")) {
            return "休憩時間を入力してください";
        } else {
            LocalTime l_rt = t.getRest_time().toLocalTime();
            int rt_second = l_rt.toSecondOfDay();
            if (t.getComent() == null || t.getComent().equals("") && rt_second > 90 * 60) {
                return "備考欄に休憩理由を入力してください";
            }
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

    private static String _validateDay(Timecard t, Boolean day_duplicate) {
        if (t.getTimecard_day() == null || t.getTimecard_day().equals("")) {
            return "日付を入力してください";
        }
        if (day_duplicate) {
            EntityManager em = DBUtil.createEntityManager();
            long t_count = (long) em.createNamedQuery("checkTimecardDuplicate", Long.class)
                    .setParameter("employee", t.getEmployee().getId())
                    .setParameter("day", t.getTimecard_day())
                    .getSingleResult();
            em.close();
            if (t_count > 0) {
                return "同じ日付ですでにタイムカードが作成されています";
            }
        }
        Date today = new Date(System.currentTimeMillis());
        LocalTime now = LocalTime.now();
        if (t.getTimecard_day().toString().equals(today.toString())) {
            LocalTime lt_st = t.getStart_at().toLocalTime();
            LocalTime lt_et = t.getEnd_at().toLocalTime();
            if (lt_st.isAfter(now) || lt_et.isAfter(now)) {
                return "現在時刻よりも先の設定はできません";
            } else {
                return "";
            }

        } else if (t.getTimecard_day().after(today)) {
            return "現在時刻よりも先の設定はできません";
        } else {

            return "";
        }
    }

}
