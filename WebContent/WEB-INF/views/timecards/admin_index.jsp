<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">

        <h2>勤退 一覧</h2>
        <div class="select_month">
            <form action="<c:url value='/timecard/admin/index' />"
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
                   <td>
                   <a href="<c:url value='/timecard/index_personal?month=${month_parameter}&id=${timecards.employee.id}'/>">
                   <c:out value='${timecards.employee.code}' /></a>

                   </td>
                   <td><c:out value="${timecards.employee.name}" /></td>
                   <td><c:out value="${timecards.str_over_time}" /></td>
                   <td><c:out value="${timecards.status}" /></td>
                </tr>
             </c:forEach>
        </table>


        <div id="pagination">
            （全 ${employees_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((employees_count -1) /15 ) +1}"
                step="1">
                <c:choose>
                    <c:when test="${i == page }">
                        <c:out value="${i}" />&nbsp;
                </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/timecard/admin/index?month=${month_parameter}&page=${i}' />"><c:out
                                value="${i}" /></a>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>

    </c:param>
</c:import>