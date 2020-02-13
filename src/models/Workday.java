package models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "workdays")
@NamedQueries({
    @NamedQuery(name="getWorkdays",query="SELECT w FROM Workday AS w WHERE w.month_group =:month_group ORDER BY w.workday ASC "),
    @NamedQuery(name="getMonthList",query="SELECT new models.MonthList(w.month_group) FROM Workday AS w WHERE w.month_group <=:month_group GROUP BY w.month_group ORDER BY w.month_group DESC"),
    @NamedQuery(name="workdayCheck",query="SELECT COUNT(w) FROM Workday as w WHERE w.workday =:day"),
})

@Entity
public class Workday {

    @Id
    @Column(name = "workday", nullable = false)
    private Date workday;

    @Column(name = "month_group", nullable = false)
    private Integer month_group;

    public Date getWorkday() {
        return workday;
    }

    public void setWorkday(Date workday) {
        this.workday = workday;
    }

    public Integer getMonth_group() {
        return month_group;
    }

    public void setMonth_group(Integer month_group) {
        this.month_group = month_group;
    }

}
