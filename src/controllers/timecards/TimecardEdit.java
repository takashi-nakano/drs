package controllers.timecards;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Timecard;
import models.support.MonthGroupSupport;
import utils.DBUtil;

/**
 * Servlet implementation class TimecardEdit
 */
@WebServlet("/timecard/admin/edit")
public class TimecardEdit extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TimecardEdit() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();
        int timecard_id = Integer.valueOf(request.getParameter("id"));

        Timecard t = (Timecard) em.find(Timecard.class, timecard_id);
        int month = MonthGroupSupport.getMonth_groupFromDate(t.getTimecard_day());

        request.setAttribute("timecard", t);
        request.setAttribute("month", month);
        request.getSession().setAttribute("timecard_id", t.getId());
        request.setAttribute("_token", request.getSession().getId());

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/timecards/edit.jsp");
        rd.forward(request, response);
    }
}
