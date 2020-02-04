package controllers.timecards;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import models.Timecard;
import utils.DBUtil;

/**
 * Servlet implementation class TimecardStart
 */
@WebServlet("/start")
public class TimecardStart extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TimecardStart() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("_token",request.getSession().getId());

        EntityManager em = DBUtil.createEntityManager();
        Employee e =(Employee)request.getSession().getAttribute("login_employee");

        List<Timecard> not_fin_timecards = em.createNamedQuery("getNotFinTimecards", Timecard.class)
                .setParameter("employee", e)
                .getResultList();
        em.close();

        if(not_fin_timecards.size() != 0){
        request.setAttribute("not_fin_timecards", not_fin_timecards);
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/timecards/start.jsp");
        rd.forward(request,response);

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = request.getParameter("_token");

        if(_token != null && _token.equals(request.getSession().getId())){

            EntityManager em = DBUtil.createEntityManager();
            Timecard t = new Timecard();

            t.setEmployee((Employee)request.getSession().getAttribute("login_employee"));

            t.setTimecard_day(new Date(System.currentTimeMillis()));
            t.setStart_at(new Time(System.currentTimeMillis()));

            em.getTransaction().begin();
            em.persist(t);
            em.getTransaction().commit();
            em.close();


            request.getSession().setAttribute("today_timecard", t);

            response.sendRedirect(request.getContextPath() +"/");

        }

    }

}
