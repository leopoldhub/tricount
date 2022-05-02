<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<jsp:include page="Head.jsp"/>
<body>
<jsp:include page="Header.jsp"/>
<div class="container mt-5 p-5">
    <div class="row mb-3">
        <c:if test="${isOwner}">
            <form method="post" action="./config">
                <input
                        type="hidden"
                        name="publicEntries"
                        value="${!isPublicEntries}"
                />
                <button type="submit" class="btn btn-warning">
                    Make entry input <c:out value="${isPublicEntries?\"private\":\"public\"}"/>
                </button>
            </form>
            <form method="post" action="./deleteEvent">
                <button type="submit" class="btn btn-danger">
                    Delete event (irreversible)
                </button>
            </form>
        </c:if>
    </div>
    <div class="row">
        <div class="col">
            <div class="container mb-5">
                <div class="d-flex gap-3">
                    <h4>Total amount:</h4>
                    <h4 class="text-info"><c:out value="${totalAmount}"/></h4>
                </div>
                <div class="list-group">
                    <c:forEach items="${debts}" var="debt">
                        <div class="list-group-item py-1">
                            <div class="d-flex gap-3">
                                <img
                                        src="https://yt3.ggpht.com/SY5Z1yXLHTqRQUZRk3EGsjBtYuwDD4DKYfEtU5pkTnwlzZLkBuDU0DWO9BNMnNuFjodHfVg_YQw=s900-c-k-c0x00ffffff-no-rj"
                                        alt="twbs"
                                        class="rounded-circle flex-shrink-0"
                                        width="32"
                                        height="32"
                                />
                                <div class="d-flex gap-2 w-100 justify-content-between">
                                    <div>
                                        <div class="d-flex gap-1 mb-2">
                                            <h5><c:out value="${debt.getInDebt().getUsername()}"/></h5>
                                            <h6 class="text-muted">@<c:out
                                                    value="${debt.getInDebt().getUser().getUser()!=null?debt.getInDebt().getUser().getUser().getEmail():debt.getInDebt().getUser().getId()}"/></h6>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="d-flex gap-1">
                                <p>Owe</p>
                                <p class="text-danger"><c:out value="${debt.getAmount()}"/></p>
                                <p>to:</p>
                            </div>
                            <div class="d-flex gap-3">
                                <img
                                        src="https://yt3.ggpht.com/SY5Z1yXLHTqRQUZRk3EGsjBtYuwDD4DKYfEtU5pkTnwlzZLkBuDU0DWO9BNMnNuFjodHfVg_YQw=s900-c-k-c0x00ffffff-no-rj"
                                        alt="twbs"
                                        class="rounded-circle flex-shrink-0"
                                        width="32"
                                        height="32"
                                />
                                <div class="d-flex gap-2 w-100 justify-content-between">
                                    <div>
                                        <div class="d-flex gap-1 mb-2">
                                            <h5><c:out value="${debt.getReceiver().getUsername()}"/></h5>
                                            <h6 class="text-muted">@<c:out
                                                    value="${debt.getReceiver().getUser().getUser()!=null?debt.getReceiver().getUser().getUser().getEmail():debt.getReceiver().getUser().getId()}"/></h6>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
            <div class="container">
                <h4>Entries:</h4>
                <div class="list-group">
                    <c:forEach items="${entries}" var="entry">
                        <div class="list-group-item d-flex gap-3 py-3">
                            <img
                                    src="https://yt3.ggpht.com/SY5Z1yXLHTqRQUZRk3EGsjBtYuwDD4DKYfEtU5pkTnwlzZLkBuDU0DWO9BNMnNuFjodHfVg_YQw=s900-c-k-c0x00ffffff-no-rj"
                                    alt="twbs"
                                    class="rounded-circle flex-shrink-0"
                                    width="32"
                                    height="32"
                            />
                            <div class="d-flex gap-2 w-100 justify-content-between">
                                <div>
                                    <div class="d-flex gap-1 mb-2">
                                        <h5><c:out value="${entry.getParticipeEntity().getUsername()}"/></h5>
                                        <h6 class="text-muted">@<c:out
                                                value="${entry.getParticipeEntity().getUser().getUser()!=null?entry.getParticipeEntity().getUser().getUser().getEmail():entry.getParticipeEntity().getUser().getId()}"/></h6>
                                    </div>
                                    <div class="d-flex gap-1">
                                        <h6 class="text-muted">[<c:out value="${entry.getLocalDateTime()}"/>]</h6>
                                        <h6><c:out value="${entry.getDescription()}"/></h6>
                                    </div>
                                    <p class="mb-0 text-success"><c:out value="${entry.getAmount()}"/></p>
                                </div>
                            </div>
                            <c:if test="${(isOwner || isPublicEntries) && userInfos != null}">
                                <form class="badge" method="post" action="./deleteEntry">
                                    <input type="hidden" name="entryId" value="${entry.getId()}"/>
                                    <button type="submit" class="btn btn-danger">delete</button>
                                </form>
                            </c:if>
                        </div>
                    </c:forEach>
                </div>
                <c:if test="${(isOwner || isPublicEntries) && userInfos != null}">
                    <form method="post" action="./addEntry">
                        <div class="form-group my-3">
                            <label for="user">User</label>
                            <select id="user" name="userId" class="form-select">
                                <c:forEach items="${participants}" var="participe">
                                    <option value="${participe.getUser().getId()}">
                                        <c:out value="${participe.getUsername()}"/> - @<c:out
                                            value="${participe.getUser().getUser()!=null?participe.getUser().getUser().getEmail():participe.getUser().getId()}"/>
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group my-3">
                            <label for="description">Description</label>
                            <input
                                    type="text"
                                    class="form-control"
                                    name="description"
                                    id="description"
                                    placeholder="Description"
                            />
                        </div>
                        <div class="form-group my-3">
                            <label for="amount">Amount</label>
                            <input
                                    type="number"
                                    class="form-control"
                                    id="amount"
                                    name="amount"
                                    placeholder="0"
                                    required
                            />
                        </div>
                        <button type="submit" class="btn btn-primary">Add entry</button>
                    </form>
                </c:if>
            </div>
        </div>
        <div class="col">
            <div class="container mb-5">
                <h4>Users:</h4>
                <div class="list-group">
                    <c:forEach items="${participants}" var="participe">
                        <div class="list-group-item d-flex gap-3 py-3">
                            <img
                                    src="https://www.clubster-nsl.com/wp-content/uploads/sites/2/2020/10/Logo-Universite-de-Lille.png"
                                    alt="twbs"
                                    class="rounded-circle flex-shrink-0"
                                    width="32"
                                    height="32"
                            />
                            <div class="d-flex gap-2 w-100 justify-content-between">
                                <div>
                                    <div class="d-flex gap-1 mb-2">
                                        <h5><c:out value="${participe.getUsername()}"/></h5>
                                        <h6 class="text-muted">@<c:out
                                                value="${participe.getUser().getUser()!=null?participe.getUser().getUser().getEmail():participe.getUser().getId()}"/></h6>
                                    </div>
                                </div>
                            </div>
                            <c:if test="${isOwner && !participe.getUser().getId().equals(userInfos.getId())}">
                                <form class="badge" method="post" action="./deleteUser">
                                    <input type="hidden" name="userId" value="${participe.getUser().getId()}"/>
                                    <button type="submit" class="btn btn-danger">delete</button>
                                </form>
                            </c:if>
                        </div>
                    </c:forEach>
                    <c:if test="${isOwner}">
                        <form method="post" action="./addUser">
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
                            <div class="form-group my-3">
                                <label for="email">Email (?)</label>
                                <input
                                        type="email"
                                        class="form-control"
                                        id="email"
                                        placeholder="Email"
                                        name="email"
                                />
                            </div>
                            <button type="submit" class="btn btn-primary">Add user</button>
                        </form>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
    <jsp:include page="Toaster.jsp"/>
</body>
</html>
