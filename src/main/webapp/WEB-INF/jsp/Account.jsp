<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<jsp:include page="Head.jsp"/>
<body>
<jsp:include page="Header.jsp"/>
<div class="container mt-5 p-5">
    <div class="list-group">
        <c:forEach items="${eventDifferences}" var="entry">
            <a
                    href="/event/${entry.getKey().getId()}/"
                    class="list-group-item list-group-item-action d-flex gap-3 py-3 align-items-start"
            >
                <div class="d-flex gap-2 w-100 justify-content-between">
                    <div>
                        <h6 class="mb-0"><c:out value="${entry.getKey().getTitle()}"/></h6>
                        <p class="mb-0 opacity-75"><c:out value="${entry.getKey().getDescription()}"/></p>
                    </div>
                </div>
                <c:if test="${entry.getValue()<0}">
                    <span class="badge bg-success rounded-pill"><c:out value="${entry.getValue()*-1}"/></span>
                </c:if>
                <c:if test="${entry.getValue()>0}">
                    <span class="badge bg-danger rounded-pill"><c:out value="${entry.getValue()*-1}"/></span>
                </c:if>
                <c:if test="${entry.getValue()==0}">
                    <span class="badge bg-secondary rounded-pill">0</span>
                </c:if>
            </a>
        </c:forEach>
    </div>
</div>
<jsp:include page="Toaster.jsp"/>
</body>
</html>