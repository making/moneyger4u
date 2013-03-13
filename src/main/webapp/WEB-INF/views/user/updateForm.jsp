<div class="row">
  <div class="span12">
    <h2>Update User</h2>
    <form:form action="${pageContext.request.contextPath}/user/update"
      modelAttribute="userForm" class="form-horizontal">
      <div class="control-group">
        <form:label path="userId" class="control-label">User Id</form:label>
        <div class="controls">
          ${f:h(userForm.userId)}
          <form:input path="userId" type="hidden" />
          <form:errors path="userId" cssClass="text-error" />
        </div>
      </div>
      <div class="control-group">
        <form:label path="firstName" class="control-label">First Name</form:label>
        <div class="controls">
          <form:input path="firstName" type="text" />
          <form:errors path="firstName" cssClass="text-error" />
        </div>
      </div>
      <div class="control-group">
        <form:label path="lastName" class="control-label">Last Name</form:label>
        <div class="controls">
          <form:input path="lastName" type="text" />
          <form:errors path="firstName" cssClass="text-error" />
        </div>
      </div>
      <div class="control-group">
        <form:label path="familyId" class="control-label">Family</form:label>
        <div class="controls">
          <form:select path="familyId" items="${familyMap}" />
          <form:errors path="familyId" cssClass="text-error" />
        </div>
      </div>
      <div class="control-group">
        <form:label path="email" class="control-label">E-mail</form:label>
        <div class="controls">
          <form:input path="email" type="text" />
          <form:errors path="email" cssClass="text-error" />
        </div>
      </div>
      <div class="control-group">
        <form:label path="password" class="control-label">User Password</form:label>
        <div class="controls">
          <form:input path="password" type="password" value="" />
          <form:errors path="password" cssClass="text-error" />
        </div>
      </div>
      <div class="control-group">
        <form:label path="confirmPassword" class="control-label">User Password (Confirm)</form:label>
        <div class="controls">
          <form:input path="confirmPassword" type="password" value="" />
          <form:errors path="confirmPassword" cssClass="text-error" />
        </div>
      </div>
      <div class="form-actions">
        <form:hidden path="version" />
        <form:errors path="version" cssClass="text-error" />
        <input type="submit" class="btn btn-primary" name="confirm"
          value="Confirm" /> <input type="submit" class="btn"
          name="redirectToList" value="Back to List" />
      </div>
    </form:form>
  </div>
</div>
