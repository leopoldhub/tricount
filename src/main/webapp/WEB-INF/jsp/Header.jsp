<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <header>
    <div class="d-flex flex-column flex-md-row align-items-center pb-1 my-3 border-bottom">
      <a href="/" class="d-flex align-items-center text-dark text-decoration-none">
        <h1>${appName}</h1>
      </a>

      <nav class="d-inline-flex mt-2 mt-md-0 ms-md-auto">
        <a class="mx-2 py-2 text-dark text-decoration-none" href="/">Accueil</a>
        <c:if test="${userInfos == null}">
          <a class="mx-2 py-2 text-dark text-decoration-none" href="/login">Login</a>
          <a class="mx-2 py-2 text-dark text-decoration-none" href="/register">Register</a>
        </c:if>
        <c:if test="${userInfos != null}">
          <a class="mx-2 py-2 text-dark text-decoration-none" href="/account">Account</a>
          <a class="mx-2 py-2 text-dark text-decoration-none" href="/logout">Logout</a>
        </c:if>
      </nav>
    </div>
  </header>