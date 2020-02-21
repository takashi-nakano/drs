<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${timecard.timecard.id==null}">
                <h3>このタイムカードは存在しません</h3>
                <a href="<c:url value='/' />">トップページに戻る</a>
            </c:when>
            <c:when test="${sessionScope.login_employee.admin_flag == 0 and sessionScope.login_employee.id != timecard.timecard.employee.id}">
                <h3>このタイムカードは閲覧できません</h3>
                <a href="<c:url value='/' />">トップページに戻る</a>
            </c:when>
            <c:otherwise>
                <h3>${timecard.timecard.employee.name }さん<br />
                    <fmt:formatDate value="${timecard.timecard.timecard_day}"
                        pattern="MM/dd (E)" />
                    の勤務記録
                </h3><br />

                <table>
                    <tr>
                        <th>始業時間</th>
                        <td><fmt:formatDate value="${timecard.timecard.start_at}"
                                pattern="H:mm" /></td>
                    </tr>
                    <tr>
                        <th>終業時間</th>
                        <td><fmt:formatDate value="${timecard.timecard.end_at}"
                                pattern="H:mm" /></td>
                    </tr>
                    <tr>
                        <th>休憩時間</th>
                        <td><fmt:formatDate value="${timecard.timecard.rest_time}"
                                pattern="H:mm" /></td>
                    </tr>
                    <tr>
                        <th>実働時間</th>
                        <td><c:out value="${timecard.str_actual_time}" /></td>
                    </tr>
                    <tr>
                        <th>時間外労働</th>
                        <td><c:out value="${timecard.str_over_time}" /></td>
                    </tr>
                    <tr>
                        <th>備考</th>
                        <td><c:out value="${timecard.timecard.coment}" /></td>
                    </tr>
                </table>
                <br />
                <c:if test="${sessionScope.login_employee.id != timecard.timecard.employee.id}">
                <a href="<c:url value='/timecard/admin/edit?id=${timecard.timecard.id}' />" >このタイムカードを修正する</a>
                </c:if>
                <br /><br />
                <a href="<c:url value='/timecard/personal/index?id=${timecard.timecard.employee.id}&month=${month}' />">${timecard.timecard.employee.name}さんのタイムカード一覧に戻る</a>

            </c:otherwise>
        </c:choose>



    </c:param>
</c:import>