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

import models.Employee;
import models.dto.MonthList;
import models.dto.TimecardAdvance;
import models.dto.TimecardAllSummary;
import models.support.EmployeeFindSupport;
import models.support.MonthGroupSupport;
import models.support.TimecardFindIndex;
import models.support.WorkdayFindMonthGroup;

/**
 * Servlet implementation class TimecardPersonalIndex
 */
@WebServlet("/timecard/personal/index")
public class TimecardPersonalIndex extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TimecardPersonalIndex() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Employee e = new Employee();

        if (request.getParameter("id") != null) {
            e = EmployeeFindSupport.singleEmployeeFind(Integer.parseInt(request.getParameter("id")));
        } else {
            e = (Employee) request.getSession().getAttribute("login_employee");
        }

        Integer month;
        if (request.getParameter("month") != null) {
            month = Integer.parseInt(request.getParameter("month"));
        } else {
            month = MonthGroupSupport.getCurrentMonth_group();
        }

        TimecardAllSummary tas = new TimecardAllSummary();
        tas = TimecardFindIndex.findPersonalIndex(e.getId(), month);
        List<TimecardAdvance> tass = new ArrayList<TimecardAdvance>();
        tass = (List<TimecardAdvance>) tas.getTimecard_advs();

        List<MonthList> ml = new ArrayList<MonthList>();
        if (e.getAdmin_flag() == 1) {
            ml = WorkdayFindMonthGroup.getAllMonthList();
        } else {
            ml = WorkdayFindMonthGroup.getOneYearMonthList();
        }

        request.setAttribute("target_employee", e);
        request.setAttribute("month_list", ml);
        request.setAttribute("month_data", tas);
        if (tass.size() != 0) {
            request.setAttribute("timecards", tass);
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/timecards/personal_index.jsp");
        rd.forward(request, response);

    }

}
