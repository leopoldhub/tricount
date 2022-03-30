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
      <h3>Create event</h3>
      <form method="post">
        <div class="form-group my-3">
          <label for="title">Title</label>
          <input
                  type="text"
                  class="form-control"
                  id="title"
                  placeholder="Title"
                  name="title"
                  required
          />
        </div>
        <div class="form-group my-3">
          <label for="description">Description</label>
          <input
                  type="text"
                  class="form-control"
                  id="description"
                  placeholder="Description"
                  name="description"
                  required
          />
        </div>
        <div class="form-group my-3">
          <label for="username">Username</label>
          <input
                  type="text"
                  class="form-control"
                  id="username"
                  placeholder="Username"
                  name="username"
                  required
          />
        </div>
        <button type="submit" class="btn btn-primary my-3">
          Create event
        </button>
      </form>
    </div>
  </div>
</div>
<jsp:include page="Toaster.jsp" />
</body>
</html>