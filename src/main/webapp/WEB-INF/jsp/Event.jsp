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
  <c:forEach items="${event.getParticipes()}" var="participation">
    <div>
      <c:out value="${participation.getUsername()}"/>
      [<c:out value="${participation.getUser().getUser() != null?participation.getUser().getUser().getEmail():participation.getUser().getId()}"/>]
      <form action="addEntry" method="post">
        <input type="hidden" name="userId" value="${participation.getUser().getId()}"/>
        <input type="number" step="0.1" name="amount" value="1"/>
        <button type="submit">add entry</button>
      </form>
      <ul>
        <c:forEach items="${participation.getEntriesEntities()}" var="entry">
          <li>
            <c:out value="${entry.getAmount()}"/>
          </li>
        </c:forEach>
      </ul>
    </div>
  </c:forEach>
  <form action="config" method="post">
    <input type="hidden" name="publicEntries" value="${!event.isPublicEntries()}"/>
    <button type="submit">Set public entries to <c:out value="${!event.isPublicEntries()}"/></button>
  </form>
  <form action="./addUser" method="post">
    <span>
        <label for="add-username">Username: </label>
        <input id="add-username" name="username" type="text" min="6" max="16" required/>
    </span>
    <span>
        <label for="add-email">email: </label>
        <input id="add-email" name="email" type="email"/>
    </span>
    <button type="submit">add user</button>
  </form>
</html>