package models.support;

import java.util.Calendar;

public class UseSupport {

    public static void main(String[] args) {

        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();

        start.set(2019, 12 - 1, 21);
        end.set(2020, 1 - 1, 20);

        int employee_id =18;

        InsertTimecard.insertTimecardStoE(employee_id, start, end);


    }

}
