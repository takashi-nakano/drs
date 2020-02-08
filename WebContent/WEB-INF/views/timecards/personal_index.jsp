<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">

        <h2>勤退 一覧</h2>
        <div class="select_month">
        <form action="<c:url value='/timecard/index_personal' />" method="GET">
            <select name="month">
                <c:forEach var="month_list" items="${month_list}">
                    <option value="${month_list.month_group}" <c:if test="${month_list.month_group ==month_parameter}"> selected </c:if>><c:out value="${month_list.str_month}" />
                </c:forEach>
            </select>
            &nbsp;
            <input type="submit" value="表示">
            </form>


        </div>
        <br />
        <div class="month_index">
            <p>合計実働時間：<c:out value="${month_data.str_total_actual_time}"/></p>
            <p>時間外労働時間：<c:out value="${month_data.str_total_over_time}"/> / <c:out value="${month_data.day_count}"/>:00 目標時間</p>

            <div class="status_coment"><h3><c:out value="${month_data.status}"/></h3></div>

        </div>


        <table id="tamecard">
            <tr>
                <th>日付</th>
                <th>始業時間</th>
                <th>退勤時間</th>
                <th>実働時間</th>
                <th>時間外勤務</th>
                <th>時間外累計</th>
            </tr>

            <c:forEach var="timecards" items="${timecards}" varStatus="status">
                <tr class="row${status.count%2}">
                    <td>
                        <a href="<c:url value='/timecard/show?id=${timecards.timecard.id}' />">
                        <fmt:formatDate value="${timecards.timecard.timecard_day}" pattern="MM/dd(E)" /></a>
                    </td>
                    <td><fmt:formatDate value="${timecards.timecard.start_at}" pattern="H:mm" /></td>
                    <td><fmt:formatDate value="${timecards.timecard.end_at}" pattern="H:mm" /></td>
                    <td><c:out value="${timecards.actual_time}" /></td>
                    <td><c:out value="${timecards.str_over_time}" /></td>
                    <td>
                    <c:out value="${timecards.str_total_over_time}" />
                    </td>



                </tr>
            </c:forEach>
        </table>
    </c:param>
</c:import>