package dbsupport;

import java.sql.Time;

public class CastSupport {

    public static Time fromStrToTime(String hhmm){
        String strTime= hhmm;
        String strFullTime = strTime + ":00";
        System.out.println(strFullTime);
        return Time.valueOf(strFullTime);
    }

}
