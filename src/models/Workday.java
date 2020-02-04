package models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "workdays")

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
