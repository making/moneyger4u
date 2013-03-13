<div class="row">
  <div class="span12">
    <h2>New User</h2>
    <form:form action="${pageContext.request.contextPath}/user/create"
      modelAttribute="userForm" class="form-horizontal">
      <div class="control-group">
        <form:label path="firstName" class="control-label">First Name</form:label>
        <div class="controls">
          ${f:h(userForm.firstName)}
          <form:hidden path="firstName" />
        </div>
      </div>
      <div class="control-group">
        <form:label path="lastName" class="control-label">Last Name</form:label>
        <div class="controls">
          ${f:h(userForm.lastName)}
          <form:hidden path="lastName" />
        </div>
      </div>
      <div class="control-group">
        <form:label path="familyId" class="control-label">Family</form:label>
        <div class="controls">
          ${f:h(familyMap[userForm.familyId])}
          <form:hidden path="familyId" />
        </div>
      </div>
      <div class="control-group">
        <form:label path="email" class="control-label">E-mail</form:label>
        <div class="controls">
          ${f:h(userForm.email)}
          <form:hidden path="email" />
        </div>
      </div>
      <div class="control-group">
        <form:label path="password" class="control-label">User Password</form:label>
        <div class="controls">
          ******
          <form:hidden path="password" />
        </div>
      </div>
      <div class="control-group">
        <form:label path="confirmPassword" class="control-label">User Password (Confirm)</form:label>
        <div class="controls">
          ******
          <form:hidden path="confirmPassword" />
        </div>
      </div>
      <div class="form-actions">
        <input type="submit" class="btn btn-primary" name="create"
          value="Create" /> <input type="submit" class="btn"
          name="form" value="Back to Form" />
      </div>
    </form:form>
  </div>
</div>
