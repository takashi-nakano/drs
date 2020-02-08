package models.support;

import java.util.Calendar;

public class MonthGroupSupport {

    public static Integer getThisMonth_group(Calendar day){

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


    public static Integer getCurrentMonth_group(){

        Calendar date = Calendar.getInstance();

        return getThisMonth_group(date);
    }

}
