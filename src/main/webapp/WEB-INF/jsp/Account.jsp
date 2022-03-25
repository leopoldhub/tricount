<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset='utf-8'>
  <meta http-equiv='X-UA-Compatible' content='IE=edge'>
  <title>${pageName}</title>
  <meta name='viewport' content='width=device-width, initial-scale=1'>
  <jsp:include page="Bootstrap.jsp" />
</head>
<body>
  <div class="container">
    <jsp:include page="Header.jsp" />
  </div>
  <div class="container">
    <h2><c:out value="${userDetails.getEmail()}"/></h2>
    <ul>
      <c:forEach items="${events}" var="event">
        <li>
          <a href="/event/${event.getId()}/"><c:out value="${event.getTitle()}"/></a>
        </li>
      </c:forEach>
    </ul>
  </div>
</body>
</html>