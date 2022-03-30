<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<jsp:include page="Head.jsp" />
<body>
<jsp:include page="Header.jsp" />
<div class="container mt-5 p-5">
  <div class="row justify-content-center">
    <div class="col-lg-3 col-md-8 col-sm-12">
      <form method="post">
        <div class="form-group my-3">
          <label for="email">Email address</label>
          <input
                  type="email"
                  class="form-control"
                  id="email"
                  placeholder="Email"
                  name="email"
                  required
          />
        </div>
        <div class="form-group my-3">
          <label for="password">Password</label>
          <input
                  type="password"
                  class="form-control"
                  id="password"
                  placeholder="Password"
                  name="password"
                  required
          />
        </div>
        <div class="form-group my-3">
          <label for="confirmpassword">Confirm password</label>
          <input
                  type="password"
                  class="form-control"
                  id="confirmpassword"
                  placeholder="Password"
                  name="confirmpassword"
                  required
          />
        </div>
        <button type="submit" class="btn btn-primary my-3">Register</button>
        <div>
          <a href="/password-reset" class="link"> I forgot my password </a>
        </div>
        <div>
          <a href="/login" class="link"> Already have an account? </a>
        </div>
      </form>
    </div>
  </div>
</div>
<jsp:include page="Toaster.jsp" />
</body>
</html>