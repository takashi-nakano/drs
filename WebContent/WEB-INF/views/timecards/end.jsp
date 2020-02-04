<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <form method="POST" action="<c:url value='/end_update' />">
    <div id="day">
        <h3>日付：<fmt:formatDate value="${today_timecard.timecard_day}" pattern="yyyy年 MM月dd日 (E)" /></h3>
    </div>

    <table id="timecard">
    <tr>
        <th>始業時間</th>
        <td><fmt:formatDate value="${today_timecard.start_at}" pattern="HH:mm" /></td>
    </tr>
    <tr>
        <th>退勤時間</th>
        <td><fmt:formatDate value="${now}" pattern="HH:mm"/></td>
    </tr>
    </table>
    <br />
    <div id="rest_time_input">
        <label for="rest_time">休憩時間</label>
        <br />
        <input type="time" name="rest_time" value="01:00" >
        <br /><br />
        <input type="text" class="coment" name="coment" size="60" placeholder="休憩時間が1時間30分を超える場合は理由を記入"  value="">
    </div>

    <br />
    <button type="submit">登録</button>

            <br />
            <input type="hidden" name="_token" value="${_token}" />
            </form>
    </c:param>
</c:import>