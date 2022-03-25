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
  <link href="./css/Form.css" rel="stylesheet">
</head>
<body>
  <div class="container">
    <jsp:include page="Header.jsp" />
  </div>
  <main class="form-signin">
    <form method="post">
      <h1 class="h3 mb-3 fw-normal">${appName}</h1>
      <c:if test="${param.error != null}">
        <div class="alert alert-danger" role="alert">${param.error}</div>
      </c:if>
      <c:if test="${param.info != null}">
        <div class="alert alert-primary" role="alert">${param.info}</div>
      </c:if>
      <div class="form-floating">
        <input type="password" class="form-control" id="password" name="password" placeholder="password" minlength="6" maxlength="64">
        <label for="floatingPassword">Password</label>
      </div>
      <div class="form-floating">
        <input type="password" class="form-control" id="confirmpassword" name="confirmpassword" placeholder="password" minlength="6" maxlength="64">
        <label for="floatingRePassword">Password</label>
      </div>
      <button class="w-100 btn btn-lg btn-primary" type="submit">reset password</button>
    </form>
  </main>
</body>
</html>