<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset='utf-8'>
  <meta http-equiv='X-UA-Compatible' content='IE=edge'>
  <title>${pageName}</title>
  <meta name='viewport' content='width=device-width, initial-scale=1'>
</head>
<body>
  <c:out value="${event}"/>
  <c:out value="${event.getParticipes()}"/>
  <form action="./addUser" method="post">
    <span>
        <label for="add-username">Username: </label>
        <input id="add-username" name="username" type="text" min="6" max="16" required/>
    </span>
    <span>
        <label for="add-email">email: </label>
        <input id="add-email" name="email" type="email"/>
    </span>
    <button type="submit"></button>
  </form>
</html>