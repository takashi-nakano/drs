package models.support;

import java.util.Calendar;

public class UseSupport {

    public static void main(String[] args) {

        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();

        start.set(2020, 1-1, 1);
        end.set(2020, 12-1, 31);

        InsertWorkday.insertWorkdays(start,end);

    }

}
