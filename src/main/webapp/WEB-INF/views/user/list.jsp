
<div class="row">
  <h2>User List</h2>
  ${request}
  <div class="span12">
    <c:if test="${not empty errorMessage}">
      <div class="alert alert-danger">${f:h(errorMessage)}</div>
    </c:if>

    <div class="well">
      <a href="${pageContext.request.contextPath}/user/create?form"
        class="btn btn-primary">New User</a><br>
      <br>
      <form:form action="${pageContext.request.contextPath}/user/search"
        method="get" modelAttribute="userSearchForm" class="form-inline my-inline">
        <form:input path="name" cssClass="form-control" />
        <input type="submit" value="Search" class="btn btn-default" />
      </form:form>
    </div>

    <table class="table table-striped table-bordered table-condensed">
      <thead>
        <tr>
          <th>User Id</th>
          <th>User Name</th>
          <th>Family Name</th>
          <th>Email</th>
          <th>Actions</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach var="user" items="${page.content}">
          <tr>
            <td>${f:h(user.userId)}</td>
            <td>${f:h(user.lastName)} ${f:h(user.firstName)}</td>
            <td>${f:h(user.familyId.familyName)}</td>
            <td>${f:h(user.email)}</td>
            <td><form:form
                action="${pageContext.request.contextPath}/user"
                class="form-inline">
                <input type="hidden" name="userId" value="${f:h(user.userId)}" />
                <input type="submit" class="btn btn-default" name="redirectToUpdate"
                  value="Update" />
                <input type="submit" class="btn btn-danger"
                  name="redirectToDelete" value="Delete" />
              </form:form></td>
          </tr>
        </c:forEach>
      </tbody>
    </table>

    <div class="pagination">
      <util:pagination page="${page}" query="name=${f:h(param.name)}" />
    </div>
  </div>

</div>
