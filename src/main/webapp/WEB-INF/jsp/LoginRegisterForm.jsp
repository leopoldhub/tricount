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
  <script src="https://www.google.com/recaptcha/api.js" async defer></script>
</head>
<body>
  <div class="container">
    <jsp:include page="Header.jsp" />
  </div>
  <main class="form-signin">
    <form method="post" id="form">
      <h1 class="h3 mb-3 fw-normal">${appName}</h1>
      <c:if test="${param.error != null}">
        <div class="alert alert-danger" role="alert">${param.error.isEmpty()?"Invalid username or password.":param.error}</div>
      </c:if>
      <c:if test="${param.info != null}">
        <div class="alert alert-primary" role="alert">${param.info}</div>
      </c:if>
      <c:if test="${param.logout != null}">
        <div class="alert alert-primary" role="alert">You have been logged out.</div>
      </c:if>
      <div class="form-floating">
        <input type="email" class="form-control" id="email" name="email" placeholder="email">
        <label for="floatingInput">Email</label>
      </div>
      <div class="form-floating">
        <input type="password" class="form-control" id="password" name="password" placeholder="password" minlength="6" maxlength="64">
        <label for="floatingPassword">Password</label>
      </div>
      <c:if test='${!pageName.equalsIgnoreCase("login")}'>
        <div class="form-floating">
          <input type="password" class="form-control" id="confirmpassword" name="confirmpassword" placeholder="password" minlength="6" maxlength="64">
          <label for="floatingRePassword">Password</label>
        </div>
      </c:if>
      <button class="g-recaptcha w-100 btn btn-lg btn-primary" data-sitekey="6LeKXw4fAAAAADB-74wzbFAL8luNZ16HbeNSBNuD" data-callback="onSubmit" data-action="submit">${pageName}</button>
      <c:if test='${pageName.equalsIgnoreCase("login")}'>
        <a href="/password-reset">I forgot my password...</a>
        <a href="./register">I dont have an account</a>
      </c:if>
      <c:if test='${!pageName.equalsIgnoreCase("login")}'>
        <a href="./login">I already have an account</a>
      </c:if>
    </form>
    <script type="text/javascript">
       function onSubmit(token) {
         document.getElementById("form").submit();
       }
    </script>
  </main>
</body>
</html>