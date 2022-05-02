<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<jsp:include page="Head.jsp" />
<body>
<jsp:include page="Header.jsp" />
<div class="container mt-5 p-5">
  <div class="p-5 p-md-5 mb-4 text-white rounded bg-dark text-center">
    <h1><c:out value="${appName}"/></h1>
    <p class="lead my-3"><c:out value="${appDescription}"/></p>
    <p class="lead mb-0">
      <a href="${pageContext.request.contextPath}/event" class="btn btn-light">Create an event</a>
    </p>
  </div>
</div>
<jsp:include page="Toaster.jsp" />
</body>
</html>
