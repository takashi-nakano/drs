package controllers.timecards;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbsupport.CastSupport;
import models.Employee;
import models.Timecard;
import models.support.MonthGroupSupport;
import models.validators.TimecardValidators;
import utils.DBUtil;

/**
 * Servlet implementation class TimecardCreate
 */
@WebServlet("/timecard/admin/create")
public class TimecardCreate extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TimecardCreate() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String _token = (String)request.getParameter("_token");
        if(_token!=null && _token.equals(request.getSession().getId())){
            EntityManager em = DBUtil.createEntityManager();

            Employee e = (Employee)request.getSession().getAttribute("target_employee");
            Timecard t = new Timecard();
            Boolean day_duplicate = true;
            Boolean end_update_flag = false;


            t.setEmployee(e);
            try{
            t.setTimecard_day(Date.valueOf(request.getParameter("timecard_day")));
            }catch(Exception error){
                t.setTimecard_day(null);
            }
            t.setStart_at(CastSupport.fromStrToTime(request.getParameter("start_at")));
            t.setEnd_at(CastSupport.fromStrToTime(request.getParameter("end_at")));
            t.setRest_time(CastSupport.fromStrToTime(request.getParameter("rest_time")));
            t.setComent(request.getParameter("coment"));

            List <String> errors = TimecardValidators.validate(t, day_duplicate, end_update_flag);

            if(errors.size() > 0){
                em.close();
                request.setAttribute("errors", errors);
                request.setAttribute("timecard", t);
                request.setAttribute("_token", request.getSession().getId());

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/timecards/new.jsp");
                rd.forward(request, response);

            }

            em.getTransaction().begin();
            em.persist(t);
            em.getTransaction().commit();
            em.close();

            request.getSession().removeAttribute("target_employee");

            int month = MonthGroupSupport.getMonth_groupFromDate(t.getTimecard_day());

            response.sendRedirect(request.getContextPath() + "/timecard/personal/index?month="+month+"&id=" + t.getEmployee().getId());


        }

    }

}
