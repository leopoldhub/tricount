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
</head>
<body>
  <div class="container">
    <jsp:include page="Header.jsp" />
  </div>
  <div class="container">
    <div class="row">
      <div class="col">
        <h2>${userDetails.getUsername()}</h2>
        <div class="row">
          <div class="col card mb-4 rounded-3 shadow-sm">
            <div class="card-header py-3">
              <h4 class="my-0 fw-normal">Vous devez</h4>
            </div>
            <div class="card-body">
              <h1 class="card-title pricing-card-title text-danger">25€</h1>
              <div class="d-flex flex-column align-items-stretch flex-shrink-0 bg-white">
                <div class="list-group list-group-flush border-bottom scrollarea">
                  <a href="#" class="list-group-item list-group-item-action py-3 lh-tight" aria-current="true">
                    <div class="d-flex w-100 align-items-center justify-content-between">
                      <strong class="mb-1">Event xxx</strong>
                      <small>10€</small>
                    </div>
                  </a>
                  <a href="#" class="list-group-item list-group-item-action py-3 lh-tight" aria-current="true">
                    <div class="d-flex w-100 align-items-center justify-content-between">
                      <strong class="mb-1">Event xxx</strong>
                      <small>10€</small>
                    </div>
                  </a>
                  <a href="#" class="list-group-item list-group-item-action py-3 lh-tight" aria-current="true">
                    <div class="d-flex w-100 align-items-center justify-content-between">
                      <strong class="mb-1">Event xxx</strong>
                      <small>5€</small>
                    </div>
                  </a>
                </div>
              </div>
            </div>
          </div>
          <div class="col card mb-4 rounded-3 shadow-sm">
            <div class="card-header py-3">
              <h4 class="my-0 fw-normal">On vous doit</h4>
            </div>
            <div class="card-body">
              <h1 class="card-title pricing-card-title text-success">75€</h1>
              <div class="d-flex flex-column align-items-stretch flex-shrink-0 bg-white">
                <div class="list-group list-group-flush border-bottom scrollarea">
                  <a href="#" class="list-group-item list-group-item-action py-3 lh-tight" aria-current="true">
                    <div class="d-flex w-100 align-items-center justify-content-between">
                      <strong class="mb-1">Event xxx</strong>
                      <small>30€</small>
                    </div>
                  </a>
                  <a href="#" class="list-group-item list-group-item-action py-3 lh-tight" aria-current="true">
                    <div class="d-flex w-100 align-items-center justify-content-between">
                      <strong class="mb-1">Event xxx</strong>
                      <small>25€</small>
                    </div>
                  </a>
                  <a href="#" class="list-group-item list-group-item-action py-3 lh-tight" aria-current="true">
                    <div class="d-flex w-100 align-items-center justify-content-between">
                      <strong class="mb-1">Event xxx</strong>
                      <small>20€</small>
                    </div>
                  </a>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="col-4 d-md-block bg-light sidebar">
        <div class="d-flex flex-column align-items-stretch flex-shrink-0 bg-white">
          <a href="/" class="d-flex align-items-center flex-shrink-0 p-3 link-dark text-decoration-none border-bottom">
            <svg class="bi me-2" width="30" height="24"><use xlink:href="#bootstrap"></use></svg>
            <span class="fs-5 fw-semibold">Mes evennements</span>
          </a>
          <div class="list-group list-group-flush border-bottom scrollarea">
            <c:forEach items="${userDebts.getEventDebts()}" var="eventDebt">
              <a href="/event/${eventDebt.getEvent().getId()}" class="list-group-item list-group-item-action py-3 lh-tight" aria-current="true">
                <div class="d-flex w-100 align-items-center justify-content-between">
                  <strong class="mb-1">${eventDebt.getEvent().getTitle()}</strong>
                  <div>
                    <c:if test="${eventDebts.getDebts().size()>0}">
                      <span class="badge text-danger">${eventDebt.obtainUserDueAmount() > 0 ? eventDebt.obtainUserDueAmount() : eventDebt.obtainUserOwingDueAmount()}</span>
                    </c:if>
                    <span class="badge bg-secondary">x pers</span>
                  </div>
                </div>
              </a>
            </c:forEach>
            <a href="/event" type="button" class="btn btn-primary">Creer un evennement</a>
          </div>
        </div>
      </div>
    </div>
  </div>
</body>
</html>