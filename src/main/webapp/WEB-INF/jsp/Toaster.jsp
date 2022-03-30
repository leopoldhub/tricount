<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="position-fixed bottom-0 end-0 p-3" style="z-index: 11">
    <c:if test="${param.error != null}">
        <div
                class="toast show align-items-center text-white bg-danger border-0"
                role="alert"
        >
            <div class="d-flex">
                <div class="toast-body"><c:out value="${param.error.isEmpty()?\"Invalid email or password\":param.error}"/></div>
                <button
                        type="button"
                        class="btn-close btn-close-white me-2 m-auto"
                        data-bs-dismiss="toast"
                ></button>
            </div>
        </div>
    </c:if>
    <c:if test="${param.warning != null}">
        <div
                class="toast show align-items-center text-white bg-warning border-0"
                role="alert"
        >
            <div class="d-flex">
                <div class="toast-body"><c:out value="${param.warning}"/></div>
                <button
                        type="button"
                        class="btn-close btn-close-white me-2 m-auto"
                        data-bs-dismiss="toast"
                ></button>
            </div>
        </div>
    </c:if>
    <c:if test="${param.info != null}">
        <div
                class="toast show align-items-center text-white bg-info border-0"
                role="alert"
        >
            <div class="d-flex">
                <div class="toast-body"><c:out value="${param.info}"/></div>
                <button
                        type="button"
                        class="btn-close btn-close-white me-2 m-auto"
                        data-bs-dismiss="toast"
                ></button>
            </div>
        </div>
    </c:if>
</div>