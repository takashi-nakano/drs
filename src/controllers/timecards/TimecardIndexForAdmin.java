package controllers.timecards;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.MonthList;
import models.support.MonthGroupSupport;
import models.support.TimecardFindIndex;
import models.support.TimecardSimpleSummary;
import models.support.WorkdayFindMonthGroup;

/**
 * Servlet implementation class TimecardIndexForAdmin
 */
@WebServlet("/timecard/admin/index")
public class TimecardIndexForAdmin extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TimecardIndexForAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        long st = System.currentTimeMillis();

        Integer month;

        if ((request.getParameter("month") != null)) {
            month = Integer.parseInt(request.getParameter("month"));
        } else {

            month = MonthGroupSupport.getCurrentMonth_group();
        }

        List<TimecardSimpleSummary> tss = new ArrayList<TimecardSimpleSummary>();
        tss = TimecardFindIndex.findTimecardForAdmin(month);

        List<MonthList> ml = WorkdayFindMonthGroup.getAllMonthList();

        request.setAttribute("month_list", ml);
        request.setAttribute("timecards", tss);
        request.setAttribute("month_parameter", month);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/timecards/admin_index.jsp");
        rd.forward(request, response);

        System.out.println(((long) System.currentTimeMillis() - st) + "ミリ秒");

    }

}
