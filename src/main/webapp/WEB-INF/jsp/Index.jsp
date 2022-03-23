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
  <link href="./css/Index.css" rel="stylesheet">
</head>
<body>
  <div class="container">
    <jsp:include page="Header.jsp" />
  </div>
  <div class="container">
    <div class="position-relative overflow-hidden p-3 p-md-5 m-md-3 text-center bg-light">
      <div class="col-md-5 p-lg-5 mx-auto my-5">
        <h1 class="display-4 fw-normal">Tricount</h1>
        <p class="lead fw-normal">Gérez et partagez votre budget</p>
        <a class="btn btn-outline-secondary" href="/account">Mon compte</a>
        <a class="btn btn-outline-secondary" href="/event">Creer un evennement</a>
      </div>
    </div>
  </div>
</html>