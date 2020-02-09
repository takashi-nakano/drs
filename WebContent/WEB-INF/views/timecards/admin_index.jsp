<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">

        <h2>勤退 一覧</h2>
        <div class="select_month">
            <form action="<c:url value='/timecard/index_admin' />"
                method="GET">
                <select name="month">
                    <c:forEach var="month_list" items="${month_list}">
                        <option value="${month_list.month_group}"
                            <c:if test="${month_list.month_group ==month_parameter}"> selected </c:if>><c:out
                                value="${month_list.str_month}" />
                    </c:forEach>
                </select> &nbsp; <input type="submit" value="表示">
            </form>
        </div>

        <table>
            <tr>
                <th>社員番号</th>
                <th>名前</th>
                <th>時間外労働</th>
                <th>状況</th>
            </tr>

            <c:forEach var="timecards" items="${timecards}" varStatus="status">
                <tr class="row${status.count%2}">
                   <td><c:out value="${timecards.employee.code}" /></td>
                   <td><c:out value="${timecards.employee.name}" /></td>
                   <td><c:out value="${timecards.str_over_time}" /></td>
                   <td><c:out value="${timecards.status}" /></td>
                </tr>
             </c:forEach>
        </table>


    </c:param>
</c:import>