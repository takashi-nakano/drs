package controllers.timecards;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import models.support.EmployeeFindSupport;

/**
 * Servlet implementation class TimecardNew
 */
@WebServlet("/timecard/admin/new")
public class TimecardNew extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TimecardNew() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int employee_id = Integer.parseInt(request.getParameter("id"));
        Employee target_e = EmployeeFindSupport.singleEmployeeFind(employee_id);
        Employee login_e = (Employee) request.getSession().getAttribute("login_employee");

        if (target_e.getId() == login_e.getId()) {
            response.sendRedirect(request.getContextPath() + "/");
        } else {

            request.setAttribute("_token", request.getSession().getId());
            request.getSession().setAttribute("target_employee", target_e);

            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/timecards/new.jsp");
            rd.forward(request, response);
        }
    }

}
