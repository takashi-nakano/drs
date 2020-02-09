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
import models.MonthList;
import models.support.EmployeeFindSupport;
import models.support.MonthGroupSupport;
import models.support.MonthTotalSummary;
import models.support.TimecardFindIndex;
import models.support.TimecardSupport;
import models.support.WorkdayFindMonthGroup;

/**
 * Servlet implementation class TimecardIndex
 */
@WebServlet("/timecard/index_personal")
public class TimecardIndex extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TimecardIndex() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Employee e = new Employee();

        if(request.getParameter("id")!=null){
            e = EmployeeFindSupport.singleEmployeeFind(Integer.parseInt(request.getParameter("id")));

        }else{
            e = (Employee) request.getSession().getAttribute("login_employee");
        }
        Integer month;

        if ((request.getParameter("month") != null)) {
            month = Integer.parseInt(request.getParameter("month"));
        } else {

            month = MonthGroupSupport.getCurrentMonth_group();
        }

        List<TimecardSupport> tss = new ArrayList<TimecardSupport>();
        tss = TimecardFindIndex.findIndex(e.getId(), month);
        MonthTotalSummary mts = new MonthTotalSummary(TimecardSupport.getTotal_actual_time(),
                TimecardSupport.getTotal_over_time(), TimecardSupport.getDay_count(), month);
        mts.sumMonthTotalSummary();

        List<MonthList> ml = new ArrayList<MonthList>();

        if(e.getAdmin_flag()==1){
            ml = WorkdayFindMonthGroup.getAllMonthList();
        }else{
            ml = WorkdayFindMonthGroup.getOneYearMonthList();
        }
        request.setAttribute("target_employee", e);
        request.setAttribute("timecards", tss);
        request.setAttribute("month_data", mts);
        request.setAttribute("month_list", ml);
        request.setAttribute("month_parameter", month);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/timecards/personal_index.jsp");
        rd.forward(request, response);

    }

}
