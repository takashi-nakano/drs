package controllers.timecards;

import java.io.IOException;
import java.sql.Date;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbsupport.CastSupport;
import models.Employee;
import models.Timecard;
import utils.DBUtil;

/**
 * Servlet implementation class TimecardCreate
 */
@WebServlet("/timecard/create")
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

            t.setEmployee(e);
            t.setTimecard_day(Date.valueOf(request.getParameter("timecard_day")));
            t.setStart_at(CastSupport.fromStrToTime((String)request.getParameter("start_at")));
            t.setEnd_at(CastSupport.fromStrToTime((String)request.getParameter("end_at")));
            t.setRest_time(CastSupport.fromStrToTime((String)request.getParameter("rest_time")));
            t.setComent(request.getParameter("coment"));

            em.getTransaction().begin();
            em.persist(t);
            em.getTransaction().commit();
            em.close();

            request.getSession().removeAttribute("target_employee");

            response.sendRedirect(request.getContextPath()+"/timecard/show?id="+t.getId());


        }

    }

}
