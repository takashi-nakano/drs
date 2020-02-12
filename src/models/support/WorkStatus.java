package models.support;

public class WorkStatus {

    public static String setWorkStatus(double double_status,int month_group) {
        if(double_status ==0.0 ){
            return "データなし";
        }

        if (month_group == MonthGroupSupport.getCurrentMonth_group()) {

            if (double_status < 0) {
                return "要確認";
            } else if (double_status >= 0 && double_status < 0.75) {
                return "順調";
            } else if (double_status >= 0.75 && double_status < 1.25) {
                return "注意";
            } else if (double_status >= 1.25 && double_status < 2.0) {
                return "要対策";
            } else {
                return "危険";

            }
        } else {
            if(double_status < 0){
                return "短縮勤務";
            }else if (double_status <= 1) {
                return "達成";
            } else {
                return "不達成";
            }
        }
    }

}
