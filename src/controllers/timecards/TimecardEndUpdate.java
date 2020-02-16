package controllers.timecards;

import java.io.IOException;
import java.sql.Time;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbsupport.CastSupport;
import models.Timecard;
import models.validators.TimecardValidators;
import utils.DBUtil;

/**
 * Servlet implementation class TimecardEndUpdate
 */
@WebServlet("/end_update")
public class TimecardEndUpdate extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TimecardEndUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = (String)request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())){
            EntityManager em = DBUtil.createEntityManager();

            Timecard t = em.find(Timecard.class, (Integer)request.getSession().getAttribute("timecard_id"));

            t.setEnd_at(new Time(System.currentTimeMillis()));
            t.setRest_time(CastSupport.fromStrToTime((String)request.getParameter("rest_time")));
            t.setComent(request.getParameter("coment"));

            List <String> errors =TimecardValidators.validate(t, false, true);

            if(errors.size() >0){
                em.close();
                request.setAttribute("errors", errors);
                request.setAttribute("now",new Time(System.currentTimeMillis()));
                request.setAttribute("_token", request.getSession().getId());

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/timecards/end.jsp");
                rd.forward(request, response);
            }else{

            em.getTransaction().begin();
            em.getTransaction().commit();
            em.close();


            request.getSession().removeAttribute("today_timecard");
            request.getSession().removeAttribute("timecard_id");
            request.getSession().setAttribute("flush","お疲れさまでした。終業時間が打刻されました");

            response.sendRedirect(request.getContextPath()+ "/");

        }
        }
    }

}
