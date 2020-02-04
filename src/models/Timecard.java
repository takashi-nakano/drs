package models;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "timecards")
@NamedQueries({
    @NamedQuery(name="getNotFinTimecards",query="SELECT t FROM Timecard AS t WHERE t.employee =:employee AND t.end_at IS NULL"),
    @NamedQuery(name="getTodayTimecard",query="SELECT t FROM Timecard AS t WHERE t.employee =:employee AND t.timecard_day =:day AND t.end_at IS NULL")

})
@Entity
public class Timecard {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Column(name = "timacard_day", nullable = false)
    private Date timecard_day;

    @Column(name = "start_at", nullable = false)
    private Time start_at;

    @Column(name = "end_at")
    private Time end_at;

    @Column(name = "rest_time")
    private Time rest_time;

    @Column(name = "coment")
    private String coment;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Date getTimecard_day() {
        return timecard_day;
    }

    public void setTimecard_day(Date timecard_day) {
        this.timecard_day = timecard_day;
    }

    public Time getStart_at() {
        return start_at;
    }

    public void setStart_at(Time start_at) {
        this.start_at = start_at;
    }

    public Time getEnd_at() {
        return end_at;
    }

    public void setEnd_at(Time end_at) {
        this.end_at = end_at;
    }

    public Time getRest_time() {
        return rest_time;
    }

    public void setRest_time(Time rest_time) {
        this.rest_time = rest_time;
    }

    public String getComent() {
        return coment;
    }

    public void setComent(String coment) {
        this.coment = coment;
    }

}
