package models.support;

import javax.persistence.EntityManager;

import models.Employee;
import utils.DBUtil;

public class EmployeeFindSupport {

    public static Employee singleEmployeeFind(int employee_id){
        EntityManager em = DBUtil.createEntityManager();

        Employee e =em.find(Employee.class, employee_id);
        em.close();
        return e;
    }

}
