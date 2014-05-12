<div class="row">
    <div class="span12">
        <h2>Update User</h2>
        <form:form action="${pageContext.request.contextPath}/user/update"
                   modelAttribute="userForm" class="form-horizontal">
            <div class="control-group">
                <form:label path="userId" class="control-label">User Id</form:label>
                <div class="controls">
                        ${f:h(userForm.userId)}
                    <form:input path="userId" type="hidden"/>
                </div>
            </div>
            <div class="control-group">
                <form:label path="firstName" class="control-label">First Name</form:label>
                <div class="controls">
                        ${f:h(userForm.firstName)}
                    <form:input path="firstName" type="hidden"/>
                </div>
            </div>
            <div class="control-group">
                <form:label path="lastName" class="control-label">Last Name</form:label>
                <div class="controls">
                        ${f:h(userForm.lastName)}
                    <form:input path="lastName" type="hidden"/>
                </div>
            </div>
            <div class="control-group">
                <form:label path="familyId" class="control-label">Family</form:label>
                <div class="controls">
                        ${f:h(familyMap[userForm.familyId])}
                    <form:hidden path="familyId"/>
                </div>
            </div>
            <div class="control-group">
                <form:label path="email" class="control-label">E-mail</form:label>
                <div class="controls">
                        ${f:h(userForm.email)}
                    <form:hidden path="email"/>
                </div>
            </div>
            <div class="control-group">
                <form:label path="password" class="control-label">User Password</form:label>
                <div class="controls">
                    ******
                    <form:input path="password" type="hidden"/>
                </div>
            </div>
            <div class="control-group">
                <form:label path="confirmPassword" class="control-label">User Password (Confirm)</form:label>
                <div class="controls">
                    ******
                    <form:input path="confirmPassword" type="hidden"/>
                </div>
            </div>
            <div class="form-actions">
                <form:hidden path="userId"/>
                <form:hidden path="version"/>
                <input type="submit" class="btn btn-primary" name="update"
                       value="Update"/> <input type="submit" class="btn"
                                               name="form" value="Back to Form"/>
            </div>
        </form:form>
    </div>
</div>
