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
import models.Timecard;
import utils.DBUtil;

/**
 * Servlet implementation class TimecardUpdate
 */
@WebServlet("/timecard/update")
public class TimecardUpdate extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TimecardUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = (String)request.getSession().getId();

        if(_token!=null && _token.equals(request.getParameter("_token"))){
            EntityManager em = DBUtil.createEntityManager();

            Timecard t = em.find(Timecard.class, (Integer) request.getSession().getAttribute("timecard_id"));

            Date date = Date.valueOf(request.getParameter("timecard_day"));

            t.setTimecard_day(date);
            t.setStart_at(CastSupport.fromStrToTime((String)request.getParameter("start_at")));
            t.setEnd_at(CastSupport.fromStrToTime((String)request.getParameter("end_at")));
            t.setRest_time(CastSupport.fromStrToTime((String)request.getParameter("rest_time")));
            t.setComent(request.getParameter("coment"));

            em.getTransaction().begin();
            em.getTransaction().commit();
            em.close();

            request.getSession().removeAttribute("timecard_id");

            response.sendRedirect(request.getContextPath()+"/timecard/show?id="+t.getId());


        }

    }

}
