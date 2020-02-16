package controllers.timecards;

import java.io.IOException;
import java.sql.Time;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Timecard;

/**
 * Servlet implementation class TimecardEndNew
 */
@WebServlet("/end_new")
public class TimecardEndNew extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TimecardEndNew() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Timecard t = (Timecard)request.getSession().getAttribute("today_timecard");

        request.setAttribute("_token", request.getSession().getId());
        request.setAttribute("now",new Time(System.currentTimeMillis()));
        request.getSession().setAttribute("timecard_id",t.getId());

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/timecards/end.jsp");
        rd.forward(request, response);

    }

}
