<div class="row">
  <div class="span12">
    <h2>Delete User</h2>
    <form:form action="${pageContext.request.contextPath}/user/delete"
      modelAttribute="userForm" class="form-horizontal">
      <div class="control-group">
        <label class="control-label">First Name</label>
        <div class="controls">${f:h(user.firstName)}</div>
      </div>
      <div class="control-group">
        <label class="control-label">Last Name</label>
        <div class="controls">${f:h(user.lastName)}</div>
      </div>
      <div class="control-group">
        <label class="control-label">E-mail</label>
        <div class="controls">${f:h(user.email)}</div>
      </div>
      <div class="control-group">
        <label class="control-label">User Password</label>
        <div class="controls">******</div>
      </div>
      <div class="form-actions">
        <form:hidden path="userId" value="${f:h(user.userId)}" />
        <form:hidden path="version" value="${f:h(user.version)}" />
        <input type="submit" class="btn btn-danger" name="delete"
          value="Delete" /> <input type="submit" class="btn"
          name="redirectToList" value="Back to List" />
      </div>
    </form:form>
  </div>
</div>
