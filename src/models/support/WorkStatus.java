package models.support;

public class WorkStatus {

    public static String setWorkStatus(Double double_status,int month_group) {

        if (month_group == MonthGroupSupport.getCurrentMonth_group()) {

            if (double_status < 0) {
                return "要確認";
            } else if (double_status >= 0 && double_status < 0.5) {
                return "順調";
            } else if (double_status >= 0.5 && double_status < 1.0) {
                return "注意";
            } else if (double_status >= 1.0 && double_status < 1.5) {
                return "警戒";
            } else if (double_status >= 1.5 && double_status < 2.0) {
                return "要対策";
            } else {
                return "危険";

            }
        } else {
            if (double_status <= 1) {
                return "達成";
            } else {
                return "不達成";
            }
        }
    }

}
