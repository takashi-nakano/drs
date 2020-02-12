<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <h3>おはようございます</h3>
        <c:if test="${not_fin_timecards != null}">
            <h4>
                適切に処理されていないタイムカードがあります。<br />上長に連絡してください
            </h4>
            <table>
                <tr>
                    <th>日付</th>
                    <th>始業時間</th>
                </tr>

                <c:forEach var="not_fin_timecards" items="${not_fin_timecards}"
                    varStatus="status">
                    <tr class="row${status.count%2}">
                        <td>
                            <fmt:formatDate	value="${not_fin_timecards.timecard_day}" pattern="MM/dd(E)" />
                        </td>
                        <td><fmt:formatDate value="${not_fin_timecards.start_at}"
                                pattern="H:mm" /></td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
        <form method="POST" action="<c:url value='/start' />">
            <br /> <input class="btn"  type="hidden" name="_token" value="${_token}" />
            <button>勤務開始</button>
        </form>
    </c:param>
</c:import>