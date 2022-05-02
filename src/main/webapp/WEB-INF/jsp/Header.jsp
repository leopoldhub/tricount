<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
  <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-primary">
    <div class="container-fluid">
      <img src="https://identite.univ-lille.fr/fileadmin/user_upload/identite/Images/Logo.sans.baseline-Horizontal-RVB-Noir.svg" style="height: 50px" />
      <a class="navbar-brand" href="${pageContext.request.contextPath}"><c:out value="${appName}"/></a>
      <button
              class="navbar-toggler"
              type="button"
              data-bs-toggle="collapse"
              data-bs-target="#navbarCollapse"
      >
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarCollapse">
        <ul class="navbar-nav me-auto mb-2 mb-md-0">
          <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}">Home</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/event">Create event</a>
          </li>
        </ul>
        <ul class="navbar-nav mb-2 mb-md-0">
          <c:if test="${userInfos == null}">
            <li class="nav-item">
              <a class="nav-link" href="${pageContext.request.contextPath}/login">Login</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="${pageContext.request.contextPath}/register">Register</a>
            </li>
          </c:if>
          <c:if test="${userInfos != null}">
            <li class="nav-item">
              <a class="nav-link" href="${pageContext.request.contextPath}/account">Account</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="${pageContext.request.contextPath}/logout">Logout</a>
            </li>
          </c:if>
        </ul>
      </div>
    </div>
  </nav>
</header>