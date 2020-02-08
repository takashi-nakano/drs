package controllers.timecards;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.support.TimecardFindIndex;
import models.support.TimecardSupport;

/**
 * Servlet implementation class TimecardShow
 */
@WebServlet("/timecard/show")
public class TimecardShow extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TimecardShow() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int timecard_id = Integer.valueOf(request.getParameter("id"));

        TimecardSupport ts = TimecardFindIndex.findSingleTimecard(timecard_id);

        request.setAttribute("timecard", ts);


        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/timecards/show.jsp");
        rd.forward(request, response);

    }

}
