package dbsupport;

import java.sql.Time;

public class CastSupport {

    public static Time fromStrToTime(String hhmm) {

        try {
            String strTime = hhmm;
            String strFullTime = strTime + ":00";
            return Time.valueOf(strFullTime);

        } catch (Exception e) {
            return null;
        }
    }

}
