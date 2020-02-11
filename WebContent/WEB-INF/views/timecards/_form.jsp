<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:if test="${errors != null}">
    <div id="flush_error">入力内容にエラーがあります<br />
        <c:forEach var="error" items="${errors}">
        ・<c:out value="${error}" />
            <br />
        </c:forEach>
    </div>
</c:if>

<div id="day">
    <label>日付：</label> <input type="date" name="timecard_day" value="<fmt:formatDate value='${timecard.timecard_day}' pattern='yyyy-MM-dd' />" />
</div>

    <label for="start_at">始業時間</label>
    <br />
    <input type="time" name="start_at" value="<fmt:formatDate value='${timecard.start_at}' pattern='HH:mm' />" />
    <br />
    <label for="end_at">退勤時間</label>
    <br />
    <input type="time" name="end_at" value="<fmt:formatDate value='${timecard.end_at}' pattern='HH:mm'/>" />
    <br />
    <div id="rest_time_input">
    <label for="rest_time">休憩時間</label>
    <br />
    <input type="time" name="rest_time" value="<fmt:formatDate value='${timecard.rest_time}' pattern='HH:mm' />" >

     <br /> <br />
        <label for="coment">備考</label><br />
        <input type="text" class="coment" name="coment" size="60" placeholder="休憩時間が1時間30分を超える場合等に理由を記入" value="${timecard.coment}">
     </div>

<br />
<button type="submit">登録</button>

<br />
<input type="hidden" name="_token" value="${_token}" />