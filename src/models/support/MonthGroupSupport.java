package models.support;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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

    public static Integer getMonth_groupFromDate(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);;

        return getThisMonth_group(cal);

    }

    public static List<Date> getAlldayOfMonth(int month_group){
        String str_mg = String.valueOf(month_group);
        String str_yyyy=str_mg.substring(0,4);
        String str_mm=str_mg.substring(4,6);

        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();

        start.set(Integer.parseInt(str_yyyy),Integer.parseInt(str_mm)-2,21);
        end.set(Integer.parseInt(str_yyyy),Integer.parseInt(str_mm)-1,21);

        List<Date> allday_list = new ArrayList<Date>();

        for (Calendar date = start; date.before(end); date.add(Calendar.DAY_OF_MONTH, 1)) {
            Date date_date=new Date(date.getTime().getTime());

            allday_list.add(date_date);


        }

        return allday_list;
    }


    public static Date getFirstdayOfMonth(int month_group) {
        String str_mg = String.valueOf(month_group);
        String str_yyyy=str_mg.substring(0,4);
        String str_mm=str_mg.substring(4,6);

        Calendar start = Calendar.getInstance();
        start.set(Integer.parseInt(str_yyyy),Integer.parseInt(str_mm)-2,20);

        return new Date (start.getTime().getTime());
    }


    public static Date getEnddayOfMonth(int month_group) {
        String str_mg = String.valueOf(month_group);
        String str_yyyy=str_mg.substring(0,4);
        String str_mm=str_mg.substring(4,6);

        Calendar end = Calendar.getInstance();
        end.set(Integer.parseInt(str_yyyy),Integer.parseInt(str_mm)-1,21);

        return new Date (end.getTime().getTime());


    }

}
